package com.kenez92.service.competitions;

import com.kenez92.domain.competitions.CompetitionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompetitionService {
    private final RestTemplate restTemplate;
    @Value("${root.application.url}")
    private String rootUrl;

    public CompetitionDto getCompetition(String name) {
        CompetitionDto response = null;
        try {
            response = restTemplate.getForObject(rootUrl + "/v1/competitions/name/" +
                    name, CompetitionDto.class);
        } catch (Exception ex) {
            log.error("Error while fetching competitions. Exception :  " + ex);
        }
        return response;
    }
}