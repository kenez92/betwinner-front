package com.kenez92.service.competitions;

import com.kenez92.domain.competitions.CompetitionTableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CompetitionTableService {
    private final RestTemplate restTemplate;
    @Value("${root.application.url}")
    private String rootUrl;

    public CompetitionTableDto getCompetitionTableDto(Long tableId) {
        CompetitionTableDto response = restTemplate.getForObject(rootUrl + "/v1/competitions/seasons/rounds/tables/"
                + tableId, CompetitionTableDto.class);
        if (response == null) {
            return new CompetitionTableDto();
        }
        return response;
    }
}
