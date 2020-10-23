package com.kenez92.domain.competitions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompetitionDto {
    private Long id;
    private Long footballId;
    private String name;
    private List<CompetitionSeasonDto> competitionSeasonList;
}
