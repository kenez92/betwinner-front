package com.kenez92.views;

import com.kenez92.config.Consts;
import com.kenez92.config.LoggedUserComponent;
import com.kenez92.domain.match.MatchDto;
import com.kenez92.views.components.MainLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MainLayout extends AppLayout {
    private MainLayoutMenu menu = new MainLayoutMenu();
    private final LoggedUserComponent loggedUserComponent;

    public MainLayout(LoggedUserComponent loggedUserComponent) {
        this.loggedUserComponent = loggedUserComponent;
        createHeader();
        createCoupon();
    }

    private void createHeader() {
        H1 logo = new H1(Consts.PROJECT_NAME);
        Image image = new Image(Consts.LOGO_URL, "");
        image.setMaxHeight(Consts.LOGO_SIZE);
        image.setMaxWidth(Consts.LOGO_SIZE);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), image, logo, menu);
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth(Consts.HEADER_SIZE);
        addToNavbar(header);
    }

    private void createCoupon() {
        if(loggedUserComponent.isUserLoggedIn()) {
            Grid grid = new Grid(MatchDto.class);
            grid.setColumns("homeTeam", "awayTeam");
            addToDrawer(new VerticalLayout(grid));
        } else {
            Label label = new Label("If you want to create a coupon you have to log in first");
            addToDrawer(label);
        }
    }
}
