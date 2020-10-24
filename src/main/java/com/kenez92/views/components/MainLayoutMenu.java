package com.kenez92.views.components;

import com.kenez92.config.Input;
import com.kenez92.views.MainView;
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

        MenuItem todayMatches = this.addItem(new RouterLink("Matches", MainView.class));
        MenuItem competitions = this.addItem("Competitions");
        MenuItem account = this.addItem("Account");

        SubMenu competitionsSubMenu = competitions.getSubMenu();
        MenuItem germany = competitionsSubMenu.addItem(Input.GERMANY);
        MenuItem netherland = competitionsSubMenu.addItem(Input.NETHERLAND);
        MenuItem spain = competitionsSubMenu.addItem(Input.SPAIN);
        MenuItem portugal = competitionsSubMenu.addItem(Input.PORTUGAL);
        MenuItem england = competitionsSubMenu.addItem(Input.ENGLAND);
        MenuItem france = competitionsSubMenu.addItem(Input.FRANCE);

        SubMenu germanySubMenu = germany.getSubMenu();
        MenuItem bundesliga = germanySubMenu.addItem(new RouterLink(Input.GERMANY_BUNDESLIGA_NAME, BundesligaView.class));

        SubMenu netherlandSubMenu = netherland.getSubMenu();
        MenuItem eredivisie = netherlandSubMenu.addItem(new RouterLink(Input.NETHERLAND_EREDIVISIE_NAME, EredivisieView.class));

        SubMenu spainSubMenu = spain.getSubMenu();
        MenuItem laLiga = spainSubMenu.addItem(new RouterLink(Input.SPAIN_LALIGA_NAME, LaLigaView.class));

        SubMenu portugalSubMenu = portugal.getSubMenu();
        MenuItem primeiraLiga = portugalSubMenu.addItem(new RouterLink(Input.PORTUGAL_PRIMEIRA_LIGA_NAME, PrimeiraLigaView.class));

        SubMenu englandSubMenu = england.getSubMenu();
        MenuItem premierLeague = englandSubMenu.addItem(new RouterLink(Input.ENGLAND_PREMIER_LEAGUE_NAME, PremierLeagueView.class));

        SubMenu franceSubMenu = france.getSubMenu();
        MenuItem ligue1 = franceSubMenu.addItem(new RouterLink(Input.FRANCE_LIGUE1_NAME, Ligue1View.class));
    }

}
