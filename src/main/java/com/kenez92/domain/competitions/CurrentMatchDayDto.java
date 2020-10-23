package com.kenez92.domain.competitions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrentMatchDayDto {
    private Long id;
    private Integer matchDay;
    private CompetitionSeasonDto competitionSeason;
    private List<CompetitionTableDto> competitionTableList;

    public String getRound() {
        return Integer.toString(matchDay);
    }
}
