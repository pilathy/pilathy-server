package com.pilathy.api.center.service.membership;

import com.pilathy.api.center.PreparedMembershipIntegrationTest;
import com.pilathy.api.center.service.membership.dto.request.MembershipRegisterRequest;
import com.pilathy.api.center.service.membership.dto.request.MembershipUpdateRequest;
import com.pilathy.api.center.service.membership.dto.response.MembershipResponse;
import com.pilathy.common.exception.model.InvalidException;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.domain.rds.domain.membership.Membership;
import com.pilathy.domain.rds.domain.membership.MembershipFixture;
import com.pilathy.domain.rds.domain.membership.MembershipRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.pilathy.common.lib.RandomGenerator.generateLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

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
            Membership membership = membershipRepository.findMembershipById(response.getMembershipId());
            assertAll(
                    () -> assertThat(response.getStartDate()).isEqualTo(membership.getDateInterval().getStartDate()),
                    () -> assertThat(response.getEndDate()).isEqualTo(membership.getDateInterval().getEndDate()),
                    () -> assertThat(response.getRemainCount()).isEqualTo(membership.getRemainCount()),
                    () -> assertThat(response.getUserId()).isEqualTo(user.getId()),
                    () -> assertThat(response.getCenterId()).isEqualTo(center.getId())
            );
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
            assertThatThrownBy(() -> membershipService.registerMembership(request, user.getId(), center.getId()))
                    .isInstanceOf(InvalidException.class);
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
            assertThatThrownBy(() -> membershipService.registerMembership(request, user.getId() + generateLong(), center.getId()))
                    .isInstanceOf(NotFoundException.class);
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
            assertThatThrownBy(() -> membershipService.registerMembership(request, user.getId(), center.getId() + generateLong()))
                    .isInstanceOf(NotFoundException.class);
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
            Membership updatedMembership = membershipRepository.findMembershipById(membership.getId());
            assertAll(
                    () -> assertThat(response.getEndDate()).isEqualTo(updatedMembership.getDateInterval().getEndDate()),
                    () -> assertThat(response.getRemainCount()).isEqualTo(updatedMembership.getRemainCount())
            );
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
            assertThatThrownBy(() -> membershipService.updateMembership(request, membership.getId()))
                    .isInstanceOf(InvalidException.class);
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
            assertThatThrownBy(() -> membershipService.updateMembership(request, membership.getId() + generateLong()))
                    .isInstanceOf(NotFoundException.class);
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
            assertThatThrownBy(() -> membershipService.deleteMembership(membership.getId() + generateLong()))
                    .isInstanceOf(NotFoundException.class);
        }

    }

}