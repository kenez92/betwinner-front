package com.kenez92.service.competitions;

import com.kenez92.domain.competitions.CompetitionTableDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompetitionTableService {
    private final RestTemplate restTemplate;
    @Value("${root.application.url}")
    private String rootUrl;

    public CompetitionTableDto getCompetitionTableDto(final Long tableId) {
        CompetitionTableDto response = null;
        try {
            response = restTemplate.getForObject(rootUrl + "/v1/competitions/seasons/rounds/tables/"
                    + tableId, CompetitionTableDto.class);
        } catch (Exception ex) {
            log.error("Error while fetching competition table. Exception: " + ex);
        }
        return response == null ? new CompetitionTableDto() : response;
    }
}
