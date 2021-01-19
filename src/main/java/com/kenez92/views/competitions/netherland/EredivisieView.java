package com.kenez92.views.competitions.netherland;

import com.kenez92.config.Consts;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.views.competitions.CompetitionView;
import com.kenez92.views.components.CouponComponent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "netherland/eredivisie")
@PageTitle(Consts.NETHERLAND_EREDIVISIE_NAME)
public class EredivisieView extends CompetitionView {
    public EredivisieView(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                          CompetitionTableService competitionTableService, CouponComponent couponComponent) {
        super(competitionService, currentMatchDayService, competitionTableService,
                Consts.NETHERLAND_EREDIVISIE_NAME.toLowerCase(), couponComponent);
    }
}
