package com.pilathy.api.center.controller.membership;

import com.pilathy.api.center.PreparedMembershipControllerTest;
import com.pilathy.api.center.controller.membership.support.MembershipApiTestClient;
import com.pilathy.api.center.controller.membership.support.MembershipTestUtils;
import com.pilathy.api.center.service.membership.dto.request.MembershipRegisterRequest;
import com.pilathy.api.center.service.membership.dto.request.MembershipUpdateRequest;
import com.pilathy.api.center.service.membership.dto.response.MembershipResponse;
import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.common.model.dto.response.ApiResponse;
import com.pilathy.domain.rds.domain.membership.Membership;
import com.pilathy.domain.rds.domain.membership.MembershipFixture;
import com.pilathy.domain.rds.domain.membership.MembershipRepository;
import com.pilathy.domain.service.service.membership.MembershipServiceHelper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static com.pilathy.common.lib.RandomGenerator.generateLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MembershipControllerTest extends PreparedMembershipControllerTest {

    private MembershipApiTestClient membershipApiTestClient;

    @Autowired
    private MembershipRepository membershipRepository;

    @BeforeEach
    void setUp() {
        membershipApiTestClient = new MembershipApiTestClient(mockMvc, objectMapper);
    }

    @AfterEach
    void afterEach() {
        super.cleanUp();
        membershipRepository.deleteAll();
    }

    @DisplayName("POST /v1/membership : 멤버십 등록")
    @Nested
    class RegisterMembershipApiTest {

        @Test
        void 성공() throws Exception {
            //given
            MembershipRegisterRequest request = MembershipRegisterRequest.builder()
                    .startDate(LocalDate.of(2023, 2, 12))
                    .endDate(LocalDate.of(2023, 5, 12))
                    .remainCount(21)
                    .build();

            //when
            ApiResponse<MembershipResponse> response = membershipApiTestClient.registerMembership(request, user.getId(), center.getId(), HttpStatus.OK.value());

            //then
            Membership findMembership = assertDoesNotThrow(() -> MembershipServiceHelper.findMembershipById(membershipRepository, response.getData().getMembershipId()));
            MembershipTestUtils.assertMembershipResponse(response.getData(), findMembership);
        }

        @Test
        void 실패_시작일자가_종료일자보다_뒤() throws Exception {
            //given
            MembershipRegisterRequest request = MembershipRegisterRequest.builder()
                    .startDate(LocalDate.of(2023, 2, 12))
                    .endDate(LocalDate.of(2023, 1, 12))
                    .remainCount(21)
                    .build();

            //when
            ApiResponse<MembershipResponse> response = membershipApiTestClient.registerMembership(request, user.getId(), center.getId(), HttpStatus.BAD_REQUEST.value());

            //then
            assertThat(response.getResultCode()).isEqualTo(ErrorCode.E400_INVALID_DATE_TIME_INTERVAL.getCode());
        }

        // TODO: RegistMembershipRequest에 @Valid 달기 && 관련 실패 테스트
    }

    @DisplayName("PUT /v1/membership/{membershipId} : 멤버십 수정")
    @Nested
    class UpdateMembershipApiTest {

        @Test
        void 성공() throws Exception {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);

            LocalDate endDate = membership.getDateInterval().getEndDate().plusMonths(1L);
            int remainCount = membership.getRemainCount() + 7;

            MembershipUpdateRequest request = MembershipUpdateRequest.builder()
                    .startDate(membership.getDateInterval().getStartDate())
                    .endDate(endDate)
                    .remainCount(remainCount)
                    .build();

            //when
            ApiResponse<MembershipResponse> response = membershipApiTestClient.updateMembership(request, membership.getId(), HttpStatus.OK.value());

            //then
            Membership findMembership = assertDoesNotThrow(() -> MembershipServiceHelper.findMembershipById(membershipRepository, response.getData().getMembershipId()));
            MembershipTestUtils.assertMembershipResponse(response.getData(), findMembership);
        }

        @Test
        void 실패_시작일자가_종료일자보다_뒤() throws Exception {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);

            MembershipUpdateRequest request = MembershipUpdateRequest.builder()
                    .startDate(membership.getDateInterval().getStartDate())
                    .endDate(membership.getDateInterval().getStartDate().minusDays(1L))
                    .remainCount(membership.getRemainCount())
                    .build();

            //when
            ApiResponse<MembershipResponse> response = membershipApiTestClient.updateMembership(request, membership.getId(), HttpStatus.BAD_REQUEST.value());

            //then
            assertThat(response.getResultCode()).isEqualTo(ErrorCode.E400_INVALID_DATE_TIME_INTERVAL.getCode());
        }

        @Test
        void 실패_잘못된_membershipId() throws Exception {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);

            MembershipUpdateRequest request = MembershipUpdateRequest.builder()
                    .startDate(membership.getDateInterval().getStartDate())
                    .endDate(membership.getDateInterval().getEndDate().plusMonths(1L))
                    .remainCount(membership.getRemainCount() + 7)
                    .build();

            //when
            ApiResponse<MembershipResponse> response = membershipApiTestClient.updateMembership(request, membership.getId() + generateLong(), HttpStatus.NOT_FOUND.value());

            //then
            assertThat(response.getResultCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_MEMBERSHIP.getCode());
        }

    }

    @DisplayName("DELETE /v1/membership/{membershipId} : 멤버십 삭제")
    @Nested
    class DeleteMembershipApiTest {

        @Test
        void 성공() throws Exception {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);
            Long membershipId = membership.getId();

            //when
            membershipApiTestClient.deleteMembership(membership.getId(), HttpStatus.OK.value());

            //then
            NotFoundException exception = assertThrows(NotFoundException.class, () -> MembershipServiceHelper.findMembershipById(membershipRepository, membershipId));
            assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_MEMBERSHIP);
        }

        @Test
        void 실패_잘못된_membershipId() throws Exception {
            //given
            Membership membership = MembershipFixture.create(user, center);
            membershipRepository.save(membership);

            //when
            ApiResponse<String> response = membershipApiTestClient.deleteMembership(membership.getId() + generateLong(), HttpStatus.NOT_FOUND.value());

            //then
            assertThat(response.getResultCode()).isEqualTo(ErrorCode.E404_NOT_EXISTS_MEMBERSHIP.getCode());
        }

    }

}