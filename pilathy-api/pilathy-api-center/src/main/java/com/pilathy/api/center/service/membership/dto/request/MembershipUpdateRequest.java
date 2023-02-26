package com.pilathy.api.center.service.membership.dto.request;

import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MembershipUpdateRequest {

    private LocalDate startDate;

    private LocalDate endDate;

    private int remainCount;

    @Builder
    private MembershipUpdateRequest(LocalDate startDate, LocalDate endDate, int remainCount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.remainCount = remainCount;
    }

}
