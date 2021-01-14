package com.kenez92.views.competitions.netherland;

import com.kenez92.config.Consts;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.views.MainLayout;
import com.kenez92.views.competitions.CompetitionView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "netherland/eredivisie", layout = MainLayout.class)
@PageTitle(Consts.NETHERLAND_EREDIVISIE_NAME)
public class EredivisieView extends CompetitionView {
    public EredivisieView(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                  CompetitionTableService competitionTableService) {
        super(competitionService, currentMatchDayService, competitionTableService, Consts.NETHERLAND_EREDIVISIE_NAME.toLowerCase());
    }
}
