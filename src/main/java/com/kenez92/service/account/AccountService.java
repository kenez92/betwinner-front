package com.kenez92.service.account;

import com.kenez92.config.Consts;
import com.kenez92.domain.account.LoginCredentials;
import com.kenez92.domain.account.UserDto;
import com.kenez92.views.components.CouponComponent;
import com.kenez92.views.components.LoggedUserComponent;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {
    private final RestTemplate restTemplate;
    private final LoggedUserComponent loggedUserComponent;
    private final CouponComponent couponComponent;
    @Value("${root.application.url}")
    private String rootUrl;
    private final static String TOKEN_PREFIX = "Bearer ";

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

    public void loginUser(LoginCredentials loginCredentials) {
        HttpEntity<LoginCredentials> request = new HttpEntity(loginCredentials);
        HttpEntity<String> httpEntity = restTemplate.exchange(rootUrl + "/login", HttpMethod.POST,
                request, String.class);
        String token = httpEntity.getHeaders().getFirst("Authorization");
        loggedUserComponent.setToken(token);
        loggedUserComponent.setUserDto(getUserDtoByToken(token));
        couponComponent.refreshCoupon();
        Notification.show(Consts.INF_LOGGED_IN);
    }

    private UserDto getUserDtoByToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        UserDto userDto = restTemplate.exchange(rootUrl + "/v1/users/loggedIn", HttpMethod.POST, request, UserDto.class).getBody();
        return userDto;
    }
}
