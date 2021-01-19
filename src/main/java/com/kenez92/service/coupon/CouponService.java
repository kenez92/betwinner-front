package com.kenez92.service.coupon;

import com.kenez92.common.enums.MatchType;
import com.kenez92.domain.coupon.CouponDto;
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
    @Value("${root.application.url}")
    private String rootUrl;

    public List<CouponDto> getUserCoupons(String token) {
        CouponDto[] response = null;
        try {
            HttpHeaders headers = createHeaders(token);
            HttpEntity<String> request = new HttpEntity<>("", headers);
            response = restTemplate.exchange(rootUrl + "v1/coupons", HttpMethod.GET, request, CouponDto[].class).getBody();
            return Arrays.asList(response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public CouponDto createEmptyCouponOrReturnOpenedCoupon(String token) {
        CouponDto response = null;
        try {
            HttpHeaders headers = createHeaders(token);
            HttpEntity<String> reqest = new HttpEntity<>("", headers);
            response = restTemplate.exchange(rootUrl + "v1/coupons/", HttpMethod.POST, reqest, CouponDto.class).getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public CouponDto getCouponById(Long id, String token) {
        CouponDto response = null;
        try {
            HttpHeaders headers = createHeaders(token);
            HttpEntity<String> reqest = new HttpEntity<>("", headers);
            response = restTemplate.exchange(rootUrl + "v1/coupons/" + id, HttpMethod.GET, reqest, CouponDto.class).getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public CouponDto addType(Long couponId, Long matchId, MatchType matchType, String token) {
        CouponDto response = null;
        try {
            HttpHeaders headers = createHeaders(token);
            HttpEntity<MatchType> request = new HttpEntity<>(matchType, headers);
            response = restTemplate.exchange(rootUrl + "v1/coupons/" + couponId + "/" + matchId,
                    HttpMethod.POST, request, CouponDto.class).getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, token);
        return headers;
    }


}
