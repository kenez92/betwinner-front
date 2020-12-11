package com.kenez92.service.account;

import com.kenez92.domain.account.UserDto;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {
    private final RestTemplate restTemplate;
    @Value("${root.application.url}")
    private String rootUrl;

    public UserDto createUser(UserDto userDto) {
        UserDto response = null;
        try {
            response = restTemplate.postForObject(rootUrl + "/v1/users/", userDto, UserDto.class);
        } catch (HttpClientErrorException.BadRequest e) {
            Notification.show(e.getResponseBodyAsString());
            log.error("Exception " + e + " userDto : " + userDto);
        } catch (Exception ex) {
            log.error("Exception in account service: " + ex);
        }
        return response;
    }
}
