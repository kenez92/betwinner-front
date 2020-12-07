package com.kenez92.views;

import com.kenez92.config.Input;
import com.kenez92.domain.match.MatchDto;
import com.kenez92.service.MatchService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(layout = MainLayout.class)
@PageTitle(Input.PROJECT_NAME)
public class MainView extends VerticalLayout {
    private final MatchService matchService;
    private Grid<MatchDto> grid = new Grid<>();

    public MainView(MatchService matchService) {
        this.matchService = matchService;
        add(grid);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(matchService.todayMatches());
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addColumn(MatchDto::getHomeTeam).setHeader("Home team");
        grid.addColumn(MatchDto::getAwayTeam).setHeader("Away team");
        grid.addColumn(matchDto -> matchDto.getMatchStats().getHomeTeamChance()).setHeader("Home team chance");
        grid.addColumn(matchDto -> matchDto.getMatchStats().getAwayTeamChance()).setHeader("Away team chance");

        grid.addComponentColumn(matchDto -> new Anchor("", matchDto.getMatchStats().getHomeTeamCourse().toString())).setHeader("1");
        grid.addComponentColumn(matchDto -> new Anchor("", matchDto.getMatchStats().getDrawCourse().toString())).setHeader("X");
        grid.addComponentColumn(matchDto -> new Anchor("", matchDto.getMatchStats().getAwayTeamCourse().toString())).setHeader("2");
    }
}
