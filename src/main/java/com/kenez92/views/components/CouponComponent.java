package com.kenez92.views.components;

import com.kenez92.domain.coupon.CouponDto;
import com.kenez92.service.coupon.CouponService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Getter
@Setter
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CouponComponent {
    private CouponDto couponDto;
    private final CouponService couponService;
    private final LoggedUserComponent loggedUserComponent;

    public CouponComponent(CouponService couponService, LoggedUserComponent loggedUserComponent) {
        this.couponService = couponService;
        this.loggedUserComponent = loggedUserComponent;
        refreshCoupon();
    }

    public CouponDto refreshCoupon() {
        if (loggedUserComponent.isUserLoggedIn()) {
            couponDto = couponService.createEmptyCouponOrReturnOpenedCoupon(loggedUserComponent.getToken());
        }
        return couponDto;
    }
}
