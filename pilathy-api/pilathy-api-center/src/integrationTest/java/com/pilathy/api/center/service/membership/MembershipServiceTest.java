package com.pilathy.api.center.service.membership;

import com.pilathy.api.center.service.membership.dto.request.MembershipRegisterRequest;
import com.pilathy.api.center.service.membership.dto.request.MembershipUpdateRequest;
import com.pilathy.api.center.service.membership.dto.response.MembershipResponse;
import com.pilathy.domain.rds.domain.account.admin.Admin;
import com.pilathy.domain.rds.domain.account.admin.AdminFixture;
import com.pilathy.domain.rds.domain.account.admin.AdminRepository;
import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.account.user.UserFixture;
import com.pilathy.domain.rds.domain.account.user.UserRepository;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.center.CenterFixture;
import com.pilathy.domain.rds.domain.center.CenterRepository;
import com.pilathy.domain.rds.domain.membership.Membership;
import com.pilathy.domain.rds.domain.membership.MembershipFixture;
import com.pilathy.domain.rds.domain.membership.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MembershipServiceTest {

    @Autowired
    MembershipService membershipService;

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CenterRepository centerRepository;

    User user;
    Admin admin;
    Center center;

    @BeforeEach
    void beforeAll() {
        User createUser = UserFixture.create();
        Admin createAdmin = AdminFixture.create();
        Center createCenter = CenterFixture.create(createAdmin);
        createAdmin.connectCenter(createCenter);

        user = userRepository.save(createUser);
        admin = adminRepository.save(createAdmin);
        center = centerRepository.save(createCenter);
    }

    @Test
    void 멤버십_등록() {
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
        assertThat(response.getStartDate()).isEqualTo(membership.getDateInterval().getStartDate());
        assertThat(response.getEndDate()).isEqualTo(membership.getDateInterval().getEndDate());
        assertThat(response.getRemainCount()).isEqualTo(membership.getRemainCount());

        assertThat(response.getUserId()).isEqualTo(user.getId());
        assertThat(response.getCenterId()).isEqualTo(center.getId());
    }

    @Test
    void 멤버십_수정() {
        //given
        Membership membership = MembershipFixture.create(user, center);
        membershipRepository.save(membership);

        LocalDate endDate = LocalDate.of(2023, 9, 2);
        int remainCount = membership.getRemainCount() + 30;
        MembershipUpdateRequest request = MembershipUpdateRequest.builder()
                .startDate(membership.getDateInterval().getStartDate())
                .endDate(endDate)
                .remainCount(remainCount)
                .build();

        //when
        MembershipResponse response = membershipService.updateMembership(membership.getId(), request);

        //then
        Membership updatedMembership = membershipRepository.findMembershipById(membership.getId());
        assertThat(response.getEndDate()).isEqualTo(endDate);
        assertThat(response.getRemainCount()).isEqualTo(remainCount);

    }

    @Test
    void 멤버십_삭제() {
        //given
        Membership membership = MembershipFixture.create(user, center);
        membershipRepository.save(membership);

        //when
        membershipService.deleteMembership(membership.getId());

        //then
        assertThat(membershipRepository.findMembershipById(membership.getId())).isNull();

    }

}