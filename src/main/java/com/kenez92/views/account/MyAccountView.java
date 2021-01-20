package com.kenez92.views.account;

import com.kenez92.config.Consts;
import com.kenez92.service.coupon.CouponService;
import com.kenez92.views.components.CouponComponent;
import com.kenez92.views.components.LoggedUserComponent;
import com.kenez92.views.components.MainLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "account/myAccount")
@PageTitle(Consts.MY_ACCOUNT_PAGE_TITLE)
public class MyAccountView extends MainLayout {
    private final LoggedUserComponent loggedUserComponent;

    public MyAccountView(LoggedUserComponent loggedUserComponent, CouponComponent couponComponent, CouponService couponService) {
        super(couponComponent, couponService);
        this.loggedUserComponent = loggedUserComponent;
        refreshCoupon();
        if (loggedUserComponent.isUserLoggedIn()) {
            createContent();
        }
    }

    private void createContent() {
        Label firstName = new Label("First name: " + loggedUserComponent.getUserDto().getFirstName());
        Label lastName = new Label("Last name: " + loggedUserComponent.getUserDto().getLastName());
        Label email = new Label("Email: " + loggedUserComponent.getUserDto().getEmail());
        Label login = new Label("Username: " + loggedUserComponent.getUserDto().getLogin());
        Label money = new Label("money: " + loggedUserComponent.getUserDto().getMoney());
        Label strategy = new Label("strategy: " + loggedUserComponent.getUserDto().getUserStrategy());
        this.getContent().add(firstName, lastName, email, login, money, strategy);
    }
}
