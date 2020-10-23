package com.kenez92.service.competitions;

import com.kenez92.domain.competitions.CompetitionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CompetitionService {
    private final RestTemplate restTemplate;
    @Value("${root.application.url}")
    private String rootUrl;

    public CompetitionDto getCompetition(String name) {
        CompetitionDto response = null;
        try {
            response = restTemplate.getForObject(rootUrl + "/v1/competitions/name/" +
                    name, CompetitionDto.class);
        } catch (HttpClientErrorException.BadRequest e) {
            System.out.println("Exception" + e);
        }
        return response;
    }
}
