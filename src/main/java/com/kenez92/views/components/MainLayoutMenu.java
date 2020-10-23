package com.kenez92.views.components;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;

public class MainLayoutMenu extends MenuBar {
    public MainLayoutMenu() {
        this.setOpenOnHover(true);

        MenuItem competitions = this.addItem("Competitions");
        MenuItem account = this.addItem("Account");

        SubMenu competitionsSubMenu = competitions.getSubMenu();
        MenuItem germany = competitionsSubMenu.addItem("Germany");
        MenuItem netherland = competitionsSubMenu.addItem("Netherland");
        MenuItem spain = competitionsSubMenu.addItem("Spain");
        MenuItem portugal = competitionsSubMenu.addItem("Portugal");
        MenuItem england = competitionsSubMenu.addItem("England");
        MenuItem france = competitionsSubMenu.addItem("France");

        SubMenu germanySubMenu = germany.getSubMenu();
        MenuItem bundesliga = germanySubMenu.addItem("Bundesliga");

        SubMenu netherlandSubMenu = netherland.getSubMenu();
        MenuItem eredivisie = netherlandSubMenu.addItem("Eredivisie");

        SubMenu spainSubMenu = spain.getSubMenu();
        MenuItem laLiga = spainSubMenu.addItem("LaLiga");

        SubMenu portugalSubMenu = portugal.getSubMenu();
        MenuItem primeiraLiga = portugalSubMenu.addItem("Primeira liga");

        SubMenu englandSubMenu = england.getSubMenu();
        MenuItem premierLeague = englandSubMenu.addItem("Premier league");

        SubMenu franceSubMenu = france.getSubMenu();
        MenuItem ligue1 = franceSubMenu.addItem("Ligue 1");
    }

}
