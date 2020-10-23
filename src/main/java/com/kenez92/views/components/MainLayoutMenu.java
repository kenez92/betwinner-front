package com.kenez92.views.components;

import com.kenez92.config.Input;
import com.kenez92.views.competitions.england.PremierLeagueView;
import com.kenez92.views.competitions.france.Ligue1View;
import com.kenez92.views.competitions.germany.BundesligaView;
import com.kenez92.views.competitions.netherland.EredivisieView;
import com.kenez92.views.competitions.portugal.PrimeiraLigaView;
import com.kenez92.views.competitions.spain.LaLigaView;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.RouterLink;

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
        //MenuItem bundesliga = germanySubMenu.addItem("Bundesliga");
        MenuItem bundesliga = germanySubMenu.addItem(new RouterLink(Input.GERMANY_BUNDESLIGA, BundesligaView.class));

        SubMenu netherlandSubMenu = netherland.getSubMenu();
        MenuItem eredivisie = netherlandSubMenu.addItem(new RouterLink(Input.NETHERLAND_EREDIVISIE, EredivisieView.class));

        SubMenu spainSubMenu = spain.getSubMenu();
        MenuItem laLiga = spainSubMenu.addItem(new RouterLink(Input.SPAIN_LALIGA, LaLigaView.class));

        SubMenu portugalSubMenu = portugal.getSubMenu();
        MenuItem primeiraLiga = portugalSubMenu.addItem(new RouterLink(Input.PORTUGAL_PRIMEIRA_LIGA, PrimeiraLigaView.class));

        SubMenu englandSubMenu = england.getSubMenu();
        MenuItem premierLeague = englandSubMenu.addItem(new RouterLink(Input.ENGLAND_PREMIER_LEAGUE, PremierLeagueView.class));

        SubMenu franceSubMenu = france.getSubMenu();
        MenuItem ligue1 = franceSubMenu.addItem(new RouterLink(Input.FRANCE_LIGUE1, Ligue1View.class));
    }

}
