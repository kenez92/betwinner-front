package com.kenez92.views.components;

import com.kenez92.config.Consts;
import com.kenez92.views.MainView;
import com.kenez92.views.account.CreateAccountView;
import com.kenez92.views.account.LoginView;
import com.kenez92.views.account.MyAccountView;
import com.kenez92.views.competitions.england.PremierLeagueView;
import com.kenez92.views.competitions.france.Ligue1View;
import com.kenez92.views.competitions.germany.BundesligaView;
import com.kenez92.views.competitions.netherland.EredivisieView;
import com.kenez92.views.competitions.portugal.PrimeiraLigaView;
import com.kenez92.views.competitions.spain.LaLigaView;
import com.kenez92.views.coupons.MyCouponView;
import com.kenez92.views.coupons.MyCouponsView;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.RouterLink;

public class MainLayoutMenu extends MenuBar {
    public MainLayoutMenu() {
        this.setOpenOnHover(true);
        this.getStyle().set("height", "6vh");
        this.getStyle().set("width", "65vw");
        this.getStyle().set("margin-left", "35vw");
        this.getStyle().set("position", "absolute");
        this.getStyle().set("overflow", "auto");

        MenuItem todayMatches = this.addItem(new RouterLink("Matches", MainView.class));
        MenuItem competitions = this.addItem("Competitions");
        MenuItem account = this.addItem("Account");
        MenuItem coupons = this.addItem("Coupons");

        SubMenu competitionsSubMenu = competitions.getSubMenu();
        MenuItem germany = competitionsSubMenu.addItem(Consts.GERMANY);
        MenuItem netherland = competitionsSubMenu.addItem(Consts.NETHERLAND);
        MenuItem spain = competitionsSubMenu.addItem(Consts.SPAIN);
        MenuItem portugal = competitionsSubMenu.addItem(Consts.PORTUGAL);
        MenuItem england = competitionsSubMenu.addItem(Consts.ENGLAND);
        MenuItem france = competitionsSubMenu.addItem(Consts.FRANCE);

        SubMenu germanySubMenu = germany.getSubMenu();
        MenuItem bundesliga = germanySubMenu.addItem(new RouterLink(Consts.GERMANY_BUNDESLIGA_NAME, BundesligaView.class));

        SubMenu netherlandSubMenu = netherland.getSubMenu();
        MenuItem eredivisie = netherlandSubMenu.addItem(new RouterLink(Consts.NETHERLAND_EREDIVISIE_NAME, EredivisieView.class));

        SubMenu spainSubMenu = spain.getSubMenu();
        MenuItem laLiga = spainSubMenu.addItem(new RouterLink(Consts.SPAIN_LALIGA_NAME, LaLigaView.class));

        SubMenu portugalSubMenu = portugal.getSubMenu();
        MenuItem primeiraLiga = portugalSubMenu.addItem(new RouterLink(Consts.PORTUGAL_PRIMEIRA_LIGA_NAME, PrimeiraLigaView.class));

        SubMenu englandSubMenu = england.getSubMenu();
        MenuItem premierLeague = englandSubMenu.addItem(new RouterLink(Consts.ENGLAND_PREMIER_LEAGUE_NAME, PremierLeagueView.class));

        SubMenu franceSubMenu = france.getSubMenu();
        MenuItem ligue1 = franceSubMenu.addItem(new RouterLink(Consts.FRANCE_LIGUE1_NAME, Ligue1View.class));

        SubMenu accountSubMenu = account.getSubMenu();
        MenuItem createAccount = accountSubMenu.addItem(new RouterLink(Consts.CREATE_ACCOUNT, CreateAccountView.class));
        MenuItem login = accountSubMenu.addItem(new RouterLink(Consts.LOGIN, LoginView.class));
        MenuItem myAccount = accountSubMenu.addItem(new RouterLink(Consts.MY_ACCOUNT, MyAccountView.class));

        SubMenu couponsSubMenu = coupons.getSubMenu();
        MenuItem myCoupons = couponsSubMenu.addItem(new RouterLink(Consts.MY_COUPONS, MyCouponsView.class));
        MenuItem myCoupon = couponsSubMenu.addItem(new RouterLink(Consts.MY_COUPON, MyCouponView.class));
//        MenuItem myAccount = accountSubMenu.addItem(new RouterLink(Consts.MY_ACCOUNT, MyAccountView.class));
    }

}
