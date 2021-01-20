package com.kenez92.views.competitions.france;

import com.kenez92.config.Consts;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.service.coupon.CouponService;
import com.kenez92.views.competitions.CompetitionView;
import com.kenez92.views.components.CouponComponent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "france/ligue1")
@PageTitle(Consts.FRANCE_LIGUE1_NAME)
public class Ligue1View extends CompetitionView {
    public Ligue1View(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                      CompetitionTableService competitionTableService, CouponComponent couponComponent,
                      CouponService couponService) {
        super(competitionService, currentMatchDayService, competitionTableService,
                Consts.FRANCE_LIGUE1_NAME.toLowerCase(), couponComponent, couponService);
    }
}
