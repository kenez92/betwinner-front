package com.kenez92.views.competitions;

import com.kenez92.domain.competitions.CompetitionSeasonDto;
import com.kenez92.domain.competitions.CompetitionTableDto;
import com.kenez92.domain.competitions.CompetitionTableElementDto;
import com.kenez92.domain.competitions.CurrentMatchDayDto;
import com.kenez92.service.competitions.CompetitionService;
import com.kenez92.service.competitions.CompetitionTableService;
import com.kenez92.service.competitions.CurrentMatchDayService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetitionView extends VerticalLayout {
    private final String competitionName;
    private final CompetitionService competitionService;
    private final CurrentMatchDayService currentMatchDayDtoService;
    private final CompetitionTableService competitionTableService;
    private ComboBox<CompetitionSeasonDto> comboBoxSeason = new ComboBox<>();
    private ComboBox<CurrentMatchDayDto> comboBoxRounds = new ComboBox<>();

    public CompetitionView(CompetitionService competitionService, CurrentMatchDayService currentMatchDayService,
                           CompetitionTableService competitionTableService, String competitionName) {
        this.competitionName = competitionName;
        this.competitionService = competitionService;
        this.currentMatchDayDtoService = currentMatchDayService;
        this.competitionTableService = competitionTableService;
        createComponents();
    }

    private void createComponents() {
        comboBoxSeason.setLabel("Select season");
        refreshComboBoxSeason();
        add(comboBoxSeason);
        comboBoxRounds.setLabel("Select round");
        add(comboBoxRounds);
    }

    private void refreshComboBoxSeason() {
        List<CompetitionSeasonDto> seasons = competitionService.getCompetition(competitionName).getCompetitionSeasonList();
        comboBoxSeason.setPlaceholder("Select season");
        comboBoxSeason.setItemLabelGenerator(CompetitionSeasonDto::getYears);
        comboBoxSeason.setItems(seasons);
        comboBoxSeason.addValueChangeListener(
                event -> refreshRounds(event.getValue().getId()));
    }

    private void refreshRounds(Long competitionSeasonId) {
        List<CurrentMatchDayDto> rounds = currentMatchDayDtoService.getRounds(competitionSeasonId);
        comboBoxSeason.setPlaceholder("Select round");
        comboBoxRounds.setItemLabelGenerator(CurrentMatchDayDto::getRound);
        comboBoxRounds.setItems(rounds);
        comboBoxRounds.addValueChangeListener(
                event -> createTabs(event.getValue()));
    }

    private void createTabs(CurrentMatchDayDto currentMatchDayDto) {
        Tab totalTab = new Tab("Total");
        Div totalDiv = new Div();

        Tab homeTab = new Tab("Home");
        Div homeDiv = new Div();
        homeDiv.setVisible(false);

        Tab awayTab = new Tab("Away");
        Div awayDiv = new Div();
        awayDiv.setVisible(false);

        for (CompetitionTableDto competitionTableDto : currentMatchDayDto.getCompetitionTableList()) {
            CompetitionTableDto competitionTable = competitionTableService
                    .getCompetitionTableDto(competitionTableDto.getId());
            switch (competitionTableDto.getType()) {
                case "TOTAL":
                    totalDiv.add(createGrid(competitionTable));
                    break;
                case "HOME":
                    homeDiv.add(createGrid(competitionTable));
                    break;
                case "AWAY":
                    awayDiv.add(createGrid(competitionTable));
                    break;
            }
        }

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(totalTab, totalDiv);
        tabsToPages.put(homeTab, homeDiv);
        tabsToPages.put(awayTab, awayDiv);
        Tabs tabs = new Tabs(totalTab, homeTab, awayTab);
        tabs.setFlexGrowForEnclosedTabs(1);
        tabs.setSizeFull();
        Div pages = new Div(totalDiv, homeDiv, awayDiv);
        pages.setSizeFull();

        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });

        add(tabs, pages);
    }

    private Grid createGrid(CompetitionTableDto competitionTableDto) {
        Grid grid = new Grid<>(CompetitionTableElementDto.class);
        grid.setColumns("position", "name", "playedGames", "won", "draw", "lost", "points", "goalsFor",
                "goalsAgainst", "goalDifference");
        grid.setItems(competitionTableDto.getCompetitionTableElements());
        return grid;
    }
}