package com.kenez92.domain.match;

import lombok.Data;

@Data
public class MatchStatsDto {
    private Integer homeTeamWins;
    private Integer draws;
    private Integer awayTeamWins;
    private Integer gamesPlayed;
    private Integer homeTeamPositionInTable;
    private Integer awayTeamPositionInTable;
    private Double homeTeamChance;
    private Double awayTeamChance;
    private Double homeTeamCourse;
    private Double drawCourse;
    private Double awayTeamCourse;
}
