package com.kenez92.domain.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenez92.common.enums.Status;
import com.kenez92.domain.account.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CouponDto {
    private Long id;
    private Double course;
    private Double rate;
    private Double result;
    @JsonProperty("couponStatus")
    private Status status;
    private UserDto user;
    private List<CouponTypeDto> couponTypeList;
}
