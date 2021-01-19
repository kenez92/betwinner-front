package com.kenez92.views.competitions.germany;

import com.kenez92.config.Consts;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.views.competitions.CompetitionView;
import com.kenez92.views.components.CouponComponent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "germany/bundesliga")
@PageTitle(Consts.GERMANY_BUNDESLIGA_NAME)
public class BundesligaView extends CompetitionView {
    public BundesligaView(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                          CompetitionTableService competitionTableService, CouponComponent couponComponent) {
        super(competitionService, currentMatchDayService, competitionTableService,
                Consts.GERMANY_BUNDESLIGA_NAME.toLowerCase(), couponComponent);
    }
}
