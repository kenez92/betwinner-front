package com.kenez92.views.competitions.germany;

import com.kenez92.config.Input;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.kenez92.views.MainLayout;
import com.kenez92.views.competitions.CompetitionView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "germany/bundesliga", layout = MainLayout.class)
@PageTitle(Input.GERMANY_BUNDESLIGA_NAME)
public class BundesligaView extends CompetitionView {
    public BundesligaView(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                          CompetitionTableService competitionTableService) {
        super(competitionService, currentMatchDayService, competitionTableService, Input.GERMANY_BUNDESLIGA_NAME.toLowerCase());
    }
}
