package com.kenez92.views.coupons;

import com.kenez92.common.enums.CouponStatus;
import com.kenez92.config.Consts;
import com.kenez92.domain.coupon.CouponDto;
import com.kenez92.service.coupon.CouponService;
import com.kenez92.views.components.CouponComponent;
import com.kenez92.views.components.LoggedUserComponent;
import com.kenez92.views.components.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "coupons/myCoupons")
@PageTitle(Consts.MY_COUPONS_PAGE_TITLE)
public class MyCouponsView extends MainLayout {
    private final LoggedUserComponent loggedUserComponent;
    private final CouponService couponService;

    public MyCouponsView(LoggedUserComponent loggedUserComponent, CouponService couponService, CouponComponent couponComponent) {
        super(couponComponent);
        this.loggedUserComponent = loggedUserComponent;
        this.couponService = couponService;
        if (loggedUserComponent.isUserLoggedIn()) {
            createContent();
        }
    }

    private void createContent() {
        List<CouponDto> couponDtoList = couponService.getUserCoupons(loggedUserComponent.getToken());

        List<CouponDto> waitingCouponDtoList = getCouponsByStatus(CouponStatus.WAITING, couponDtoList);
        List<CouponDto> winCouponDtoList = getCouponsByStatus(CouponStatus.WIN, couponDtoList);
        List<CouponDto> lostCouponDtoList = getCouponsByStatus(CouponStatus.LOST, couponDtoList);

        Tab waitingTab = new Tab(CouponStatus.WAITING.name());
        Div waitingDiv = new Div();

        Tab winTab = new Tab(CouponStatus.WIN.name());
        Div winDiv = new Div();
        winDiv.setVisible(false);

        Tab lostTab = new Tab(CouponStatus.LOST.name());
        Div lostDiv = new Div();
        lostDiv.setVisible(false);

        waitingDiv.add(createGrid(waitingCouponDtoList));
        winDiv.add(createGrid(winCouponDtoList));
        lostDiv.add(createGrid(lostCouponDtoList));


        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(waitingTab, waitingDiv);
        tabsToPages.put(winTab, winDiv);
        tabsToPages.put(lostTab, lostDiv);
        Tabs tabs = new Tabs(waitingTab, winTab, lostTab);
        tabs.setFlexGrowForEnclosedTabs(1);
        tabs.setSizeFull();
        Div pages = new Div(waitingDiv, winDiv, lostDiv);
        pages.setSizeFull();

        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });
        this.getContent().add(tabs, pages);
    }

    private List<CouponDto> getCouponsByStatus(CouponStatus couponStatus, List<CouponDto> couponDtoList) {
        List<CouponDto> resultList = new ArrayList<>();
        for (CouponDto couponDto : couponDtoList) {
            if (couponDto.getCouponStatus().equals(couponStatus)) {
                resultList.add(couponDto);
            }
        }
        return resultList;
    }

    private Grid createGrid(List<CouponDto> couponDtoList) {
        Grid<CouponDto> grid = new Grid<>();
        grid.setItems(couponDtoList);
        grid.addComponentColumn(couponDto -> new Anchor("", couponDto.getId().toString())).setHeader("ID");
        grid.addColumn(CouponDto::getCourse).setHeader("Course");
        grid.addColumn(CouponDto::getRate).setHeader("Rate");
        grid.addColumn(CouponDto::getResult).setHeader("Result");
        grid.addColumn(CouponDto::getCouponStatus).setHeader("Status");
        return grid;
    }
}
