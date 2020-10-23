package com.kenez92.service;

import com.kenez92.domain.MatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final RestTemplate restTemplate;
    @Value("${root.application.url}")
    private String rootUrl;

    public List<MatchDto> todayMatches() {
        MatchDto[] matchesResponse = restTemplate.getForObject(rootUrl + "/v1/matches/date=" +
                LocalDate.now(), MatchDto[].class);
        return Arrays.asList(ofNullable(matchesResponse).orElse(new MatchDto[0]));
    }
}
