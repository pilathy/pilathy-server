package com.pilathy.api.center.service.membership;

import com.pilathy.api.center.PreparedMembershipIntegrationTest;
import com.pilathy.api.center.service.membership.dto.request.MembershipRegisterRequest;
import com.pilathy.api.center.service.membership.dto.request.MembershipUpdateRequest;
import com.pilathy.api.center.service.membership.dto.response.MembershipResponse;
import com.pilathy.api.center.service.membership.support.MembershipTestUtils;
import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.InvalidException;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.domain.rds.domain.membership.Membership;
import com.pilathy.domain.rds.domain.membership.MembershipFixture;
import com.pilathy.domain.rds.domain.membership.MembershipRepository;
import com.pilathy.domain.service.service.membership.MembershipServiceHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.pilathy.common.lib.RandomGenerator.generateLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MembershipServiceTest extends PreparedMembershipIntegrationTest {

    @Autowired
    MembershipService membershipService;

    @Autowired
    MembershipRepository membershipRepository;

    @DisplayName("멤버십 등록")
    @Nested
    class RegisterMembershipTest {

        @Test
        void 성공() {
            //given
            MembershipRegisterRequest request = MembershipRegisterRequest.builder()
                    .startDate(LocalDate.of(2023, 2, 7))
                    .endDate(LocalDate.of(2023, 5, 7))
                    .remainCount(36)
                    .build();

            //when
            MembershipResponse response = membershipService.registerMembership(request, user.getId(), center.getId());

            //then
            Membership findMembership = MembershipServiceHelper.findMembershipById(membershipRepository, response.getMembershipId());
            MembershipTestUtils.assertMembershipResponse(response, findMembership);
        }

        @Test
        void 실패_시작일자가_종료일자보다_뒤() {
            //given
            MembershipRegisterRequest request = MembershipRegisterRequest.builder()
                    .startDate(LocalDate.of(2023, 5, 7))
                    .endDate(LocalDate.of(2023, 2, 7))
                    .remainCount(36)
                    .build();

            //when & then
            InvalidException exception = assertThrows(InvalidException.class, () -> membershipService.registerMembership(request, user.getId(), center.getId()));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E400_INVALID_DATE_TIME_INTERVAL);
        }

        @Test
        void 실패_잘못된_userId() {
            //given
            MembershipRegisterRequest request = MembershipRegisterRequest.builder()
                    .startDate(LocalDate.of(2023, 2, 7))
                    .endDate(LocalDate.of(2023, 5, 7))
                    .remainCount(36)
                    .build();

            //when & then
            NotFoundException exception = assertThrows(NotFoundException.class, () -> membershipService.registerMembership(request, user.getId() + generateLong(), center.getId()));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_USER);
        }

        @Test
        void 실패_잘못된_centerId() {
            //given
            MembershipRegisterRequest request = MembershipRegisterRequest.builder()
                    .startDate(LocalDate.of(2023, 2, 7))
                    .endDate(LocalDate.of(2023, 5, 7))
                    .remainCount(36)
                    .build();

            //when & then
            NotFoundException exception = assertThrows(NotFoundException.class, () -> membershipService.registerMembership(request, user.getId(), center.getId() + generateLong()));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_CENTER);
        }

    }

    @DisplayName("멤버십 수정")
    @Nested
    class UpdateMembershipTest {

        @Test
        void 성공() {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);

            MembershipUpdateRequest request = MembershipUpdateRequest.builder()
                    .startDate(membership.getDateInterval().getStartDate())
                    .endDate(LocalDate.of(2023, 9, 2))
                    .remainCount(membership.getRemainCount() + 30)
                    .build();

            //when
            MembershipResponse response = membershipService.updateMembership(request, membership.getId());

            //then
            Membership findMembership = MembershipServiceHelper.findMembershipById(membershipRepository, response.getMembershipId());
            MembershipTestUtils.assertMembershipResponse(response, findMembership);
        }

        @Test
        void 실패_시작일자가_종료일자보다_뒤() {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);

            MembershipUpdateRequest request = MembershipUpdateRequest.builder()
                    .startDate(membership.getDateInterval().getStartDate())
                    .endDate(membership.getDateInterval().getStartDate().minusDays(1L))
                    .remainCount(membership.getRemainCount() + 30)
                    .build();

            //when & then
            InvalidException exception = assertThrows(InvalidException.class, () -> membershipService.updateMembership(request, membership.getId()));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E400_INVALID_DATE_TIME_INTERVAL);
        }

        @Test
        void 실패_잘못된_membershipId() {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);

            MembershipUpdateRequest request = MembershipUpdateRequest.builder()
                    .startDate(membership.getDateInterval().getStartDate())
                    .endDate(membership.getDateInterval().getEndDate().plusMonths(1L))
                    .remainCount(membership.getRemainCount() + 30)
                    .build();

            //when & then
            NotFoundException exception = assertThrows(NotFoundException.class, () -> membershipService.updateMembership(request, membership.getId() + generateLong()));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_MEMBERSHIP);
        }

        // TODO: 멤버십 수정 파라미터에 admin 추가해야함

    }

    @DisplayName("멤버십 삭제")
    @Nested
    class DeleteMembershipTest {

        @Test
        void 성공() {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);

            //when
            membershipService.deleteMembership(membership.getId());

            //then
            assertThat(membershipRepository.findMembershipById(membership.getId())).isNull();
        }

        @Test
        void 실패_잘못된_membershipId() {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);

            //when & then
            NotFoundException exception = assertThrows(NotFoundException.class, () -> membershipService.deleteMembership(membership.getId() + generateLong()));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_MEMBERSHIP);
        }

    }

}