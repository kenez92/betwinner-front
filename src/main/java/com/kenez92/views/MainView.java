package com.kenez92.views;

import com.kenez92.config.Consts;
import com.kenez92.domain.match.MatchDto;
import com.kenez92.service.MatchService;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;


@Route(layout = MainLayout.class)
@PageTitle(Consts.PROJECT_NAME)
public class MainView extends VerticalLayout {
    private final MatchService matchService;
    private final Grid<MatchDto> grid = new Grid<>();
    private final DatePicker datePicker = new DatePicker();

    public MainView(MatchService matchService) {
        this.matchService = matchService;
        add(datePicker);
        datePicker.setValue(LocalDate.now());
        datePicker.addValueChangeListener(e -> grid.setItems(matchService.getMatchesByLocalDate(datePicker.getValue())));
        add(grid);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(matchService.getMatchesByLocalDate(datePicker.getValue()));
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.addColumn(MatchDto::getHourOfDate).setHeader("Time");
        grid.addColumn(MatchDto::getHomeTeam).setHeader("Home team");
        grid.addColumn(MatchDto::getAwayTeam).setHeader("Away team");
        grid.addColumn(matchDto -> matchDto.getMatchStats().getHomeTeamChance()).setHeader("Home team chance");
        grid.addColumn(matchDto -> matchDto.getMatchStats().getAwayTeamChance()).setHeader("Away team chance");

        grid.addComponentColumn(matchDto -> new Anchor("", matchDto.getMatchStats().getHomeTeamCourse().toString())).setHeader("1");
        grid.addComponentColumn(matchDto -> new Anchor("", matchDto.getMatchStats().getDrawCourse().toString())).setHeader("X");
        grid.addComponentColumn(matchDto -> new Anchor("", matchDto.getMatchStats().getAwayTeamCourse().toString())).setHeader("2");
    }
}
