package com.kenez92.service.coupon;

import com.kenez92.common.enums.MatchType;
import com.kenez92.domain.coupon.CouponDto;
import com.kenez92.views.components.LoggedUserComponent;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponService {
    private final RestTemplate restTemplate;
    private final LoggedUserComponent loggedUserComponent;
    @Value("${root.application.url}")
    private String rootUrl;

    public List<CouponDto> getUserCoupons() {
        CouponDto[] response;
        if (loggedUserComponent.isUserLoggedIn()) {
            try {
                HttpHeaders headers = createHeaders(loggedUserComponent.getToken());
                HttpEntity<String> request = new HttpEntity<>("", headers);
                response = restTemplate.exchange(rootUrl + "v1/coupons", HttpMethod.GET, request, CouponDto[].class).getBody();
                return Arrays.asList(response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public CouponDto createEmptyCouponOrReturnOpenedCoupon() {
        if (loggedUserComponent.isUserLoggedIn()) {
            try {
                HttpHeaders headers = createHeaders(loggedUserComponent.getToken());
                HttpEntity<String> reqest = new HttpEntity<>("", headers);
                CouponDto response = restTemplate.exchange(rootUrl + "v1/coupons/", HttpMethod.POST, reqest,
                        CouponDto.class).getBody();
                return response != null ? response : new CouponDto();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return new CouponDto();
    }

    public CouponDto getCouponById(Long id) {
        if (loggedUserComponent.isUserLoggedIn()) {
            try {
                HttpHeaders headers = createHeaders(loggedUserComponent.getToken());
                HttpEntity<String> reqest = new HttpEntity<>("", headers);
                CouponDto response = restTemplate.exchange(rootUrl + "v1/coupons/" + id, HttpMethod.GET, reqest,
                        CouponDto.class).getBody();
                return response != null ? response : new CouponDto();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return new CouponDto();
    }

    public CouponDto addType(Long couponId, Long matchId, MatchType matchType) {
        if (loggedUserComponent.isUserLoggedIn()) {
            try {
                HttpHeaders headers = createHeaders(loggedUserComponent.getToken());
                HttpEntity<MatchType> request = new HttpEntity<>(matchType, headers);
                CouponDto response = restTemplate.exchange(rootUrl + "v1/coupons/" + couponId + "/" + matchId,
                        HttpMethod.POST, request, CouponDto.class).getBody();
                return response != null ? response : new CouponDto();
            } catch (Exception ex) {
                if (ex.getMessage().equals("400 : [This match already exists in your coupon]")) {
                    Notification.show("This match already exists in your coupon");
                } else {
                    ex.printStackTrace();
                }
            }
        }
        return new CouponDto();
    }

    public CouponDto setRateOfCoupon(Long couponId, Double rate) {
        if (rate != null && rate > 1 && loggedUserComponent.isUserLoggedIn()) {
            try {
                HttpHeaders headers = createHeaders(loggedUserComponent.getToken());
                HttpEntity<String> request = new HttpEntity<>("", headers);
                CouponDto response = restTemplate.exchange(rootUrl + "v1/coupons/" + couponId + "/" + rate,
                        HttpMethod.PUT, request, CouponDto.class).getBody();
                return response != null ? response : new CouponDto();
            } catch (Exception ex) {
                ex.printStackTrace();
                log.error("Error while setting rate: {} of the coupon: {}", rate, couponId);
            }
        }
        return new CouponDto();
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }


}
