package com.kenez92.domain.match;

import com.kenez92.domain.weather.WeatherDto;
import lombok.Data;

import java.util.Date;

@Data
public class MatchDto {
    private String homeTeam;
    private String awayTeam;
    private Date date;
    private Integer round;
    private MatchScoreDto matchScore;
    private WeatherDto weather;
    private MatchStatsDto matchStats;
}
