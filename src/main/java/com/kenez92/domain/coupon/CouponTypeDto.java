package com.kenez92.domain.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenez92.common.enums.Status;
import com.kenez92.common.enums.MatchType;
import com.kenez92.domain.match.MatchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CouponTypeDto {
    private Long id;
    private MatchType matchType;
    private MatchDto match;

    @JsonProperty("couponStatus")
    private Status status;
    private CouponDto coupon;
}
