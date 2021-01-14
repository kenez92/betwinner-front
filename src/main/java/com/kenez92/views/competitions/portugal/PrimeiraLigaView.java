package com.kenez92.views.competitions.portugal;

import com.kenez92.config.Consts;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.views.MainLayout;
import com.kenez92.views.competitions.CompetitionView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "portugal/primeira_liga", layout = MainLayout.class)
@PageTitle(Consts.PORTUGAL_PRIMEIRA_LIGA_NAME)
public class PrimeiraLigaView extends CompetitionView {
    public PrimeiraLigaView(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                            CompetitionTableService competitionTableService) {
        super(competitionService, currentMatchDayService, competitionTableService, Consts.PORTUGAL_PRIMEIRA_LIGA_NAME.toLowerCase());
    }
}
