package com.kenez92.views;

import com.kenez92.common.enums.MatchType;
import com.kenez92.config.Consts;
import com.kenez92.domain.match.MatchDto;
import com.kenez92.service.MatchService;
import com.kenez92.service.coupon.CouponService;
import com.kenez92.views.components.CouponComponent;
import com.kenez92.views.components.LoggedUserComponent;
import com.kenez92.views.components.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;


@Route()
@PageTitle(Consts.PROJECT_NAME)
public class MainView extends MainLayout {
    private final MatchService matchService;
    private final Grid<MatchDto> grid = new Grid<>();
    private final DatePicker datePicker = new DatePicker();
    private final LoggedUserComponent loggedUserComponent;

    public MainView(MatchService matchService, CouponService couponService, LoggedUserComponent loggedUserComponent,
                    CouponComponent couponComponent) {
        super(couponComponent, couponService);
        this.matchService = matchService;
        this.loggedUserComponent = loggedUserComponent;
        this.getCouponComponent().refreshCoupon();
        this.getContent().add(datePicker);
        datePicker.setValue(LocalDate.now());
        datePicker.addValueChangeListener(e -> grid.setItems(matchService.getMatchesByLocalDate(datePicker.getValue())));
        this.getContent().add(grid);
        refresh();
    }

    public void refresh() {
        grid.getStyle().set("background-color", Consts.BACKGROUND_COLOR);
        grid.setItems(matchService.getMatchesByLocalDate(datePicker.getValue()));
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addColumn(MatchDto::getHourOfDate).setHeader("Time").setAutoWidth(true);
        grid.addColumn(MatchDto::getHomeTeam).setHeader("Home team").setAutoWidth(true);
        grid.addColumn(MatchDto::getAwayTeam).setHeader("Away team").setAutoWidth(true);
        grid.addColumn(matchDto -> matchDto.getMatchStats().getHomeTeamChance()).setHeader("Home team chance").setAutoWidth(true);
        grid.addColumn(matchDto -> matchDto.getMatchStats().getAwayTeamChance()).setHeader("Away team chance").setAutoWidth(true);

        grid.addComponentColumn(matchDto -> new Button(matchDto.getMatchStats().getHomeTeamCourse().toString(), event -> {
            if (loggedUserComponent.isUserLoggedIn() && this.getCouponComponent().getCouponDto() != null) {
                getCouponService().addType(this.getCouponComponent().getCouponDto().getId(), matchDto.getId(), MatchType.HOME_TEAM);
                refreshCoupon();
            } else {
                Notification.show("Log in first");
            }
        })).setAutoWidth(true).setHeader("1");

        grid.addComponentColumn(matchDto -> new Button(matchDto.getMatchStats().getDrawCourse().toString(), event -> {
            if (loggedUserComponent.isUserLoggedIn() && this.getCouponComponent().getCouponDto() != null) {
                getCouponService().addType(this.getCouponComponent().getCouponDto().getId(), matchDto.getId(), MatchType.DRAW);
                refreshCoupon();
            } else {
                Notification.show("Log in first");
            }
        })).setAutoWidth(true).setHeader("0");

        grid.addComponentColumn(matchDto -> new Button(matchDto.getMatchStats().getAwayTeamCourse().toString(), event -> {
            if (loggedUserComponent.isUserLoggedIn() && this.getCouponComponent().getCouponDto() != null) {
                getCouponService().addType(this.getCouponComponent().getCouponDto().getId(), matchDto.getId(), MatchType.AWAY_TEAM);
                refreshCoupon();
            } else {
                Notification.show("Log in first");
            }
        })).setAutoWidth(true).setHeader("2");
        grid.setSizeFull();
    }
}
