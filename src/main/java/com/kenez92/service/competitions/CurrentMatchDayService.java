package com.kenez92.service.competitions;

import com.kenez92.domain.competitions.CurrentMatchDayDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrentMatchDayService {
    private final RestTemplate restTemplate;
    @Value("${root.application.url}")
    private String rootUrl;

    public List<CurrentMatchDayDto> getRounds(Long competitionSeasonId) {
        try {
            CurrentMatchDayDto[] response = restTemplate.getForObject(rootUrl + "/v1/competitions/seasons/rounds/season/" +
                    competitionSeasonId, CurrentMatchDayDto[].class);
            return Arrays.asList(Optional.ofNullable(response).orElse(new CurrentMatchDayDto[0]));
        } catch (Exception ex) {
            log.error("Error while fetching current match day. Exception: " + ex);
        }
        return new ArrayList<>();
    }
}
