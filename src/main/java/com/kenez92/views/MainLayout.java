package com.kenez92.views;

import com.kenez92.config.Input;
import com.kenez92.domain.match.MatchDto;
import com.kenez92.views.components.MainLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MainLayout extends AppLayout {
    private MainLayoutMenu menu = new MainLayoutMenu();

    public MainLayout() {
        createHeader();
        createCoupon();
    }

    private void createHeader() {
        H1 logo = new H1(Input.PROJECT_NAME);
        Image image = new Image(Input.LOGO_URL, "");
        image.setMaxHeight(Input.LOGO_SIZE);
        image.setMaxWidth(Input.LOGO_SIZE);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), image, logo, menu);
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth(Input.HEADER_SIZE);
        addToNavbar(header);
    }

    private void createCoupon() {
        Grid grid = new Grid(MatchDto.class);
        grid.setColumns("homeTeam", "awayTeam");
        addToDrawer(new VerticalLayout(grid));
    }
}
