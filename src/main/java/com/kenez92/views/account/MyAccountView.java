package com.kenez92.views.account;

import com.kenez92.config.Consts;
import com.kenez92.config.LoggedUserComponent;
import com.kenez92.views.MainLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "account/myAccount", layout = MainLayout.class)
@PageTitle(Consts.MY_ACCOUNT_PAGE_TITLE)
public class MyAccountView extends VerticalLayout {
    private final LoggedUserComponent loggedUserComponent;

    public MyAccountView(LoggedUserComponent loggedUserComponent) {
        this.loggedUserComponent = loggedUserComponent;
        if (loggedUserComponent.isUserLoggedIn()) {
            createContent();
        } else {
            Notification.show(Consts.ERR_YOU_ARE_NOT_LOGGED_IN);
        }
    }

    private void createContent() {
        Label firstName = new Label("First name: " + loggedUserComponent.getUserDto().getFirstName());
        Label lastName = new Label("Last name: " + loggedUserComponent.getUserDto().getLastName());
        Label email = new Label("Email: " + loggedUserComponent.getUserDto().getEmail());
        Label login = new Label("Username: " + loggedUserComponent.getUserDto().getLogin());
        Label money = new Label("money: " + loggedUserComponent.getUserDto().getMoney());
        Label strategy = new Label("strategy: " + loggedUserComponent.getUserDto().getUserStrategy());
        add(firstName, lastName, email, login, money, strategy);
    }
}
