package com.kenez92.views.coupons;

import com.kenez92.config.Consts;
import com.kenez92.domain.coupon.CouponDto;
import com.kenez92.service.coupon.CouponService;
import com.kenez92.views.components.CouponComponent;
import com.kenez92.views.components.LoggedUserComponent;
import com.kenez92.views.components.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.*;

import java.util.List;

@Route(value = "coupons/myCoupon")
@PageTitle(Consts.MY_COUPON_PAGE_TITLE)
public class MyCouponView extends MainLayout implements HasUrlParameter<Long> {
    private Long couponId;

    private final LoggedUserComponent loggedUserComponent;
    private final CouponService couponService;
    private final ComboBox<CouponDto> couponDtoComboBox = new ComboBox<>();

    public MyCouponView(LoggedUserComponent loggedUserComponent, CouponService couponService, CouponComponent couponComponent) {
        super(couponComponent);
        this.loggedUserComponent = loggedUserComponent;
        this.couponService = couponService;
        if (loggedUserComponent.isUserLoggedIn()) {
            createContent();
        }
    }

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter Long parameter) {
        if (parameter != null) {
            this.couponId = parameter;
        }
    }

    private void createContent() {
        createCouponIdsComboBox();
    }

    private void createCouponIdsComboBox() {
        List<CouponDto> couponDtoList = couponService.getUserCoupons(loggedUserComponent.getToken());
        if (couponId != null) {
            couponDtoComboBox.setPlaceholder(couponId.toString());
        } else {
            couponDtoComboBox.setPlaceholder("Select coupon");
        }
        couponDtoComboBox.setItemLabelGenerator(couponDto -> couponDto.getId().toString());
        couponDtoComboBox.setItems(couponDtoList);
        couponDtoComboBox.addValueChangeListener(event -> createCouponDtoGrid(event.getValue()));
        this.getContent().add(couponDtoComboBox);
    }

    private void createCouponDtoGrid(CouponDto couponDto) {
        CouponDto dbCouponDto = couponService.getCouponById(couponDto.getId(), loggedUserComponent.getToken());
        Grid<CouponDto> grid = new Grid<>();


    }
}
