package com.kenez92.views.competitions.england;

import com.kenez92.config.Consts;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.service.coupon.CouponService;
import com.kenez92.views.competitions.CompetitionView;
import com.kenez92.views.components.CouponComponent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "england/premier_league")
@PageTitle(Consts.ENGLAND_PREMIER_LEAGUE_NAME)
public class PremierLeagueView extends CompetitionView {
    public PremierLeagueView(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                             CompetitionTableService competitionTableService, CouponComponent couponComponent,
                             CouponService couponService) {
        super(competitionService, currentMatchDayService, competitionTableService,
                Consts.ENGLAND_PREMIER_LEAGUE_NAME.toLowerCase(), couponComponent, couponService);
    }
}
