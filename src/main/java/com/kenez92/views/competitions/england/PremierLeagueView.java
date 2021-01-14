package com.kenez92.views.competitions.england;

import com.kenez92.config.Consts;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.views.MainLayout;
import com.kenez92.views.competitions.CompetitionView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "england/premier_league", layout = MainLayout.class)
@PageTitle(Consts.ENGLAND_PREMIER_LEAGUE_NAME)
public class PremierLeagueView extends CompetitionView {
    public PremierLeagueView(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                  CompetitionTableService competitionTableService) {
        super(competitionService, currentMatchDayService, competitionTableService, Consts.ENGLAND_PREMIER_LEAGUE_NAME.toLowerCase());
    }
}
