package com.kenez92.views.competitions.spain;

import com.kenez92.config.Input;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.views.MainLayout;
import com.kenez92.views.competitions.CompetitionView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "spain/laliga", layout = MainLayout.class)
@PageTitle(Input.SPAIN_LALIGA_NAME)
public class LaLigaView extends CompetitionView {
    public LaLigaView(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                  CompetitionTableService competitionTableService) {
        super(competitionService, currentMatchDayService, competitionTableService, Input.SPAIN_LALIGA_NAME.toLowerCase());
    }
}
