package com.kenez92.views.account;

import com.kenez92.config.Consts;
import com.kenez92.domain.account.LoginCredentials;
import com.kenez92.service.account.AccountService;
import com.kenez92.views.components.CouponComponent;
import com.kenez92.views.components.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "account/login")
@PageTitle(Consts.LOGIN_PAGE_TITLE)
public class LoginView extends MainLayout {
    private final AccountService accountService;

    public LoginView(AccountService accountService, CouponComponent couponComponent) {
        super(couponComponent);
        this.accountService = accountService;
        couponComponent.refreshCoupon();

        TextField usernameField = new TextField();
        usernameField.setLabel(Consts.Username);
        usernameField.setRequiredIndicatorVisible(true);

        PasswordField passwordField = new PasswordField();
        passwordField.setLabel(Consts.PASSWORD);
        passwordField.setRequiredIndicatorVisible(true);

        Button submitButton = new Button(Consts.LOGIN_BUTTON);

        submitButton.addClickListener(event -> {
            if (usernameField.getValue() != null && passwordField.getValue() != null) {
                accountService.loginUser(new LoginCredentials(usernameField.getValue(), passwordField.getValue()));
            } else {
                Notification.show(Consts.ERR_BAD_CREDENTIALS);
            }
        });
        this.getContent().add(usernameField);
        this.getContent().add(passwordField);
        this.getContent().add(submitButton);
    }
}
