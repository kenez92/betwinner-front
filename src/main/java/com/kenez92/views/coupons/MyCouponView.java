package com.kenez92.views.coupons;

import com.kenez92.common.enums.MatchType;
import com.kenez92.common.enums.Status;
import com.kenez92.config.Consts;
import com.kenez92.domain.coupon.CouponDto;
import com.kenez92.domain.coupon.CouponTypeDto;
import com.kenez92.service.coupon.CouponService;
import com.kenez92.views.components.CouponComponent;
import com.kenez92.views.components.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.stream.Collectors;

@Route(value = "coupons/myCoupon")
@PageTitle(Consts.MY_COUPON_PAGE_TITLE)
public class MyCouponView extends MainLayout implements HasUrlParameter<Long> {
    private final ComboBox<CouponDto> couponDtoComboBox = new ComboBox<>();
    private final Grid<CouponTypeDto> grid = new Grid<>();
    private Long couponId;

    public MyCouponView(CouponService couponService, CouponComponent couponComponent) {
        super(couponComponent, couponService);
        createContent();
    }

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter Long parameter) {
        if (parameter != null) {
            this.couponId = parameter;
        }
    }

    private void createContent() {
        createCouponIdsComboBox();
    }

    private void createCouponIdsComboBox() {
        List<CouponDto> couponDtoList = getCouponService().getUserCoupons()
                .stream()
                .filter(e -> e.getStatus() != Status.WAITING)
                .collect(Collectors.toList());
        if (couponId != null) {
            couponDtoComboBox.setPlaceholder(couponId.toString());
        } else {
            couponDtoComboBox.setPlaceholder("Select coupon");
        }
        couponDtoComboBox.setItemLabelGenerator(couponDto -> couponDto.getId().toString());
        couponDtoComboBox.setItems(couponDtoList);
        couponDtoComboBox.addValueChangeListener(event -> createCouponDtoGrid(event.getValue()));
        this.getContent().add(couponDtoComboBox);
        if (couponId != null) {
            for (CouponDto couponDto : couponDtoList) {
                if (couponDto.getId().equals(couponId)) {
                    createCouponDtoGrid(couponDto);
                    return;
                }
            }
        }
    }

    private void createCouponDtoGrid(CouponDto couponDto) {
        CouponDto dbCouponDto = getCouponService().getCouponById(couponDto.getId());
        if (dbCouponDto != null && dbCouponDto.getCouponTypeList() != null) {
            grid.removeAllColumns();
            grid.setItems(dbCouponDto.getCouponTypeList());
            grid.addColumn(e -> e.getMatch() != null && e.getMatch().getHourOfDate() != null ? e.getMatch().getDate() : "")
                    .setHeader("Time").setAutoWidth(true);

            grid.addColumn(e -> e.getMatch() != null && e.getMatch().getHomeTeam() != null ? e.getMatch().getHomeTeam() : "")
                    .setHeader("Home team").setAutoWidth(true);

            grid.addColumn(e -> e.getMatch() != null && e.getMatch().getAwayTeam() != null ? e.getMatch().getAwayTeam() : "")
                    .setHeader("Away team").setAutoWidth(true);

            grid.addColumn(e -> e.getMatchType() == MatchType.HOME_TEAM ? MatchType.HOME_TEAM :
                    (e.getMatchType() == MatchType.AWAY_TEAM ? MatchType.AWAY_TEAM : MatchType.DRAW))
                    .setHeader("Type").setAutoWidth(true);

            grid.addColumn(e -> e.getMatch() != null && e.getMatch().getMatchStats() != null ? e.getMatchType() == MatchType.HOME_TEAM ? e.getMatch().getMatchStats().getHomeTeamCourse() :
                    (e.getMatchType() == MatchType.AWAY_TEAM ? e.getMatch().getMatchStats().getAwayTeamCourse() :
                            e.getMatch().getMatchStats().getDrawCourse()) : "").setHeader("Course").setAutoWidth(true);

            grid.addColumn(CouponTypeDto::getStatus).setHeader("Status").setAutoWidth(true);
        }
        this.getContent().add(grid);
    }
}
