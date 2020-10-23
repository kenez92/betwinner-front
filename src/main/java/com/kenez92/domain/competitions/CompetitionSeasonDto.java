package com.kenez92.domain.competitions;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CompetitionSeasonDto {
    private Long id;
    private Long footballId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String winner;
    private CompetitionDto competition;
    private List<CurrentMatchDayDto> currentMatchDayList;

    public String getYears() {
        return startDate.getYear() + " - " + endDate.getYear();
    }
}
