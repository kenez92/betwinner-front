package com.kenez92.domain.match;

import lombok.Data;

@Data
public class MatchScoreDto {
    private String winner;
    private String status;
    private String duration;
    private Integer fullTimeHomeTeam;
    private Integer fullTimeAwayTeam;
    private Integer halfTimeHomeTeam;
    private Integer halfTimeAwayTeam;
    private Integer extraTimeHomeTeam;
    private Integer extraTimeAwayTeam;
    private Integer penaltiesHomeTeam;
    private Integer penaltiesAwayTeam;
}
