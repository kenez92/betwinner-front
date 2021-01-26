package com.kenez92.views.coupons;

import com.kenez92.common.enums.Status;
import com.kenez92.config.Consts;
import com.kenez92.domain.coupon.CouponDto;
import com.kenez92.service.coupon.CouponService;
import com.kenez92.views.components.CouponComponent;
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

    public MyCouponsView(CouponService couponService, CouponComponent couponComponent) {
        super(couponComponent, couponService);
        createContent();
    }

    private void createContent() {
        List<CouponDto> couponDtoList = getCouponService().getUserCoupons();

        List<CouponDto> activeCouponDtoList = getCouponsByStatus(Status.ACTIVE, couponDtoList);
        List<CouponDto> winCouponDtoList = getCouponsByStatus(Status.WIN, couponDtoList);
        List<CouponDto> lostCouponDtoList = getCouponsByStatus(Status.LOST, couponDtoList);

        Tab waitingTab = new Tab(Status.ACTIVE.name());
        Div waitingDiv = new Div();

        Tab winTab = new Tab(Status.WIN.name());
        Div winDiv = new Div();
        winDiv.setVisible(false);

        Tab lostTab = new Tab(Status.LOST.name());
        Div lostDiv = new Div();
        lostDiv.setVisible(false);

        waitingDiv.add(createGrid(activeCouponDtoList));
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

    private List<CouponDto> getCouponsByStatus(Status status, List<CouponDto> couponDtoList) {
        List<CouponDto> resultList = new ArrayList<>();
        for (CouponDto couponDto : couponDtoList) {
            if (couponDto.getStatus().equals(status)) {
                resultList.add(couponDto);
            }
        }
        return resultList;
    }

    private Grid createGrid(List<CouponDto> couponDtoList) {
        Grid<CouponDto> grid = new Grid<>();
        grid.setItems(couponDtoList);
        grid.addComponentColumn(couponDto -> new Anchor("coupons/myCoupon/" + couponDto.getId(),
                couponDto.getId().toString())).setHeader("ID");
        grid.addColumn(CouponDto::getCourse).setHeader("Course");
        grid.addColumn(CouponDto::getRate).setHeader("Rate");
        grid.addColumn(CouponDto::getResult).setHeader("Result");
        grid.addColumn(CouponDto::getStatus).setHeader("Status");
        return grid;
    }
}
