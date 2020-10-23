package com.kenez92.views.competitions.france;

import com.kenez92.config.Input;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.views.MainLayout;
import com.kenez92.views.competitions.CompetitionView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "france/ligue1", layout = MainLayout.class)
@PageTitle(Input.FRANCE_LIGUE1)
public class Ligue1View extends CompetitionView {
    public Ligue1View(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                      CompetitionTableService competitionTableService) {
        super(competitionService, currentMatchDayService, competitionTableService, Input.FRANCE_LIGUE1_NAME);
    }
}
