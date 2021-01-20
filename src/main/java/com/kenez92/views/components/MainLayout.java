package com.kenez92.views.components;

import com.kenez92.config.Consts;
import com.kenez92.domain.coupon.CouponDto;
import com.kenez92.domain.coupon.CouponTypeDto;
import com.kenez92.service.coupon.CouponService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class MainLayout extends HorizontalLayout {
    private final CouponComponent couponComponent;
    private final CouponService couponService;
    private final VerticalLayout coupon = new VerticalLayout();
    private final VerticalLayout content = new VerticalLayout();
    private final Grid<CouponTypeDto> couponContent = new Grid<>();
    private final Button submitCouponButton = new Button("Submit");
    private final FormLayout formLayout = new FormLayout();

    public MainLayout(CouponComponent couponComponent, CouponService couponService) {
        this.couponComponent = couponComponent;
        this.couponService = couponService;
        H1 logo = new H1(Consts.PROJECT_NAME);
        logo.getStyle().set("margin-top", "0");
        logo.getStyle().set("margin-left", "10vw");
        logo.getStyle().set("position", "absolute");
        add(logo);

        this.getStyle().set("background-color", Consts.BACKGROUND_COLOR);
        this.getStyle().set("height", "100vh");
        this.getStyle().set("width", "100vw");
        this.getStyle().set("box-sizing", "border-box");
        add(new MainLayoutMenu());
        createCoupon();
        createContent();
    }

    public VerticalLayout getCoupon() {
        return coupon;
    }

    public VerticalLayout getContent() {
        return content;
    }

    public CouponComponent getCouponComponent() {
        return couponComponent;
    }

    public CouponService getCouponService() {
        return couponService;
    }

    public void createCoupon() {
        coupon.getStyle().set("border", "3px solid black");
        coupon.getStyle().set("box-shadow", "2px 2px 10px #000");
        coupon.getStyle().set("width", "28vw");
        coupon.getStyle().set("height", "87vh");
        coupon.getStyle().set("margin-top", "10vh");
        coupon.getStyle().set("margin-left", "2vw");
        coupon.getStyle().set("margin-bottom", "3vh");
        coupon.getStyle().set("overflow", "auto");
        coupon.getStyle().set("position", "absolute");

        couponContent.getStyle().set("background-color", Consts.BACKGROUND_COLOR);
        couponContent.getStyle().set("box-shadow", "2px 2px 5px #000");
        couponContent.getStyle().set("overflow", "auto");

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("25em", 2));
        TextField rateField = new TextField();
        Button changeRate = new Button("Set rate");
        changeRate.addClickListener(e -> {
            Double value = null;
            try {
                value = Double.valueOf(rateField.getValue());
            } catch (Exception ex) {
                Notification.show("Rate must be a namber value !");
            }
            if (value != null && value > 1 && couponComponent != null && couponComponent.getCouponDto() != null) {
                CouponDto couponDto = couponService.setRateOfCoupon(couponComponent.getCouponDto().getId(), value);
                if (couponDto != null) {
                    couponComponent.setCouponDto(couponDto);
                    refreshCoupon();
                }
            }
        });
        formLayout.add(rateField);
        formLayout.add(changeRate);

        refreshCoupon();
    }

    public void refreshCoupon() {
        this.couponComponent.refreshCoupon();
        coupon.removeAll();

        Label course = new Label("Course: ");
        Label rate = new Label("Rate: ");
        Label toWin = new Label("To win: ");

        coupon.add(couponContent);
        coupon.add(course);
        coupon.add(rate);
        coupon.add(toWin);
        coupon.add(formLayout);
        coupon.add(submitCouponButton);
        String rateResult;
        String courseResult;
        String toWinResult;
        if (couponComponent.getCouponDto() != null && couponComponent.getCouponDto().getCouponTypeList() != null) {
            couponContent.setItems(this.couponComponent.getCouponDto().getCouponTypeList());
            couponContent.addColumn(e -> e.getMatch().getHomeTeam() + " : " + e.getMatch().getAwayTeam()).setHeader("Match").setAutoWidth(true);
            couponContent.addColumn(e -> e.getMatchType().getType()).setHeader("Type").setAutoWidth(true);
            couponContent.addColumn(e -> e.getMatchType().getType().equals("1") ? e.getMatch().getMatchStats().getHomeTeamCourse()
                    : (e.getMatchType().getType().equals("2") ? e.getMatch().getMatchStats().getAwayTeamCourse()
                    : e.getMatch().getMatchStats().getDrawCourse()));
            rateResult = couponComponent.getCouponDto().getRate() != null ? String.valueOf(couponComponent
                    .getCouponDto().getRate()) : "0.00";
            courseResult = couponComponent.getCouponDto().getCourse() != null ? String.valueOf(couponComponent
                    .getCouponDto().getCourse()) : "0.00";
            toWinResult = couponComponent.getCouponDto().getResult() != null ? String.valueOf(couponComponent
                    .getCouponDto().getResult()) : "0.00";
        } else {
            rateResult = "0.00";
            courseResult = "0.00";
            toWinResult = "0.00";
        }
        rate.add(rateResult);
        course.add(courseResult);
        toWin.add(toWinResult);
        add(coupon);
    }

    private void createContent() {
        content.getStyle().set("background-color", Consts.BACKGROUND_COLOR);
        content.getStyle().set("border", "3px solid black");
        content.getStyle().set("box-shadow", "2px 2px 10px #000");
        content.getStyle().set("width", "68%");
        content.getStyle().set("height", "87%");
        content.getStyle().set("margin-left", "32vw");
        content.getStyle().set("margin-top", "10vh");
        content.getStyle().set("margin-bottom", "3vh");
        content.getStyle().set("overflow", "auto");
        coupon.getStyle().set("position", "absolute");
        add(content);
    }
}
