package com.pilathy.api.center.service.membership.dto.response;

import com.pilathy.domain.rds.domain.membership.Membership;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MembershipResponse {

    private Long membershipId;

    private Long userId;

    private Long centerId;

    private LocalDate startDate;

    private LocalDate endDate;

    private int remainCount;

    @Builder(access = AccessLevel.PRIVATE)
    private MembershipResponse(Long membershipId, Long userId, Long centerId, LocalDate startDate, LocalDate endDate, int remainCount) {
        this.membershipId = membershipId;
        this.userId = userId;
        this.centerId = centerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remainCount = remainCount;
    }

    public static MembershipResponse of(Membership membership) {
        MembershipResponse response = MembershipResponse.builder()
                .membershipId(membership.getId())
                .userId(membership.getUser().getId())
                .centerId(membership.getCenter().getId())
                .startDate(membership.getDateInterval().getStartDate())
                .endDate(membership.getDateInterval().getEndDate())
                .remainCount(membership.getRemainCount())
                .build();
        // TODO: domain-service 만들기
//        response.setAuditingTimeByEntity(membership);
        return response;
    }
}
