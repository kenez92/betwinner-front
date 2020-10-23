package com.kenez92.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MatchDto {
    private String homeTeam;
    private String awayTeam;
    private Double homeTeamCourse;
    private Double drawCourse;
    private Double awayTeamCourse;
    private int homeTeamChance;
    private int awayTeamChance;
    private Date date;
}
