package com.kenez92.service.competitions;

import com.kenez92.domain.competitions.CompetitionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CompetitionService {
    private final RestTemplate restTemplate;
    @Value("${root.application.url}")
    private String rootUrl;

    public CompetitionDto getCompetition(String name) {
        CompetitionDto response = restTemplate.getForObject(rootUrl + "/v1/competitions/name/" +
                name, CompetitionDto.class);
        if (response == null) {
            return new CompetitionDto();
        } else {
            return response;
        }
    }
}
