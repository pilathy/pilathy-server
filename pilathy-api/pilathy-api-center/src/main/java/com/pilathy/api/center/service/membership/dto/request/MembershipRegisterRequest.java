package com.pilathy.api.center.service.membership.dto.request;

import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.common.DateInterval;
import com.pilathy.domain.rds.domain.membership.Membership;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MembershipRegisterRequest {

    private LocalDate startDate;

    private LocalDate endDate;

    private int remainCount;

    @Builder
    private MembershipRegisterRequest(LocalDate startDate, LocalDate endDate, int remainCount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.remainCount = remainCount;
    }

    public Membership toEntity(User user, Center center) {
        return Membership.of(startDate, endDate, remainCount, user, center);
    }

}
