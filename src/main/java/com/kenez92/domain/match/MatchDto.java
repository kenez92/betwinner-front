package com.kenez92.domain.match;

import com.kenez92.domain.weather.WeatherDto;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public String getHourOfDate() {
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return localDateTime.getHour() + ":" + localDateTime.getMinute();
    }
}
