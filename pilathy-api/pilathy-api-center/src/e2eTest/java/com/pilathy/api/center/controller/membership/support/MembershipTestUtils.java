package com.pilathy.api.center.controller.membership.support;

import com.pilathy.api.center.service.membership.dto.response.MembershipResponse;
import com.pilathy.common.TestUtils;
import com.pilathy.domain.rds.domain.membership.Membership;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestUtils
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MembershipTestUtils {

    public static void assertMembershipResponse(MembershipResponse response, Membership membership) {
        assertMembershipResponse(
                response,
                membership.getDateInterval().getStartDate(),
                membership.getDateInterval().getEndDate(),
                membership.getRemainCount()
        );
        assertAll(
                () -> assertThat(response.getMembershipId()).isEqualTo(membership.getId()),
                () -> assertThat(response.getUserId()).isEqualTo(membership.getUser().getId()),
                () -> assertThat(response.getCenterId()).isEqualTo(membership.getCenter().getId())
        );
    }

    public static void assertMembershipResponse(MembershipResponse response, LocalDate startDate, LocalDate endDate, int remainCount) {
        assertAll(
                () -> assertThat(response.getStartDate()).isEqualTo(startDate),
                () -> assertThat(response.getEndDate()).isEqualTo(endDate),
                () -> assertThat(response.getRemainCount()).isEqualTo(remainCount)
        );
    }

    public static void assertMembershipDeleteResponse(Membership membership) {
        assertAll(
                () -> assertThat(membership).isNull()
        );
    }

}
