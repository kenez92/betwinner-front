package com.kenez92.views;

import com.kenez92.config.Input;
import com.kenez92.domain.MatchDto;
import com.kenez92.service.MatchService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = MainLayout.class)
@PageTitle(Input.PROJECT_NAME)
public class MainView extends VerticalLayout {
    private final MatchService matchService;
    private Grid grid = new Grid<>(MatchDto.class);

    public MainView(MatchService matchService) {
        this.matchService = matchService;
        grid.setColumns("homeTeam", "awayTeam", "homeTeamCourse", "drawCourse", "awayTeamCourse", "homeTeamChance",
                "awayTeamChance", "date");
        add(grid);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(matchService.todayMatches());
    }
}
