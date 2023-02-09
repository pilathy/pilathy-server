package com.pilathy.api.center.service.membership;

import com.pilathy.api.center.service.membership.dto.request.MembershipRegisterRequest;
import com.pilathy.api.center.service.membership.dto.request.MembershipUpdateRequest;
import com.pilathy.api.center.service.membership.dto.response.MembershipResponse;
import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.account.user.UserRepository;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.center.CenterRepository;
import com.pilathy.domain.rds.domain.membership.Membership;
import com.pilathy.domain.rds.domain.membership.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MembershipService {

    private final UserRepository userRepository;
    private final CenterRepository centerRepository;
    private final MembershipRepository membershipRepository;

    @Transactional
    public MembershipResponse registerMembership(MembershipRegisterRequest request, Long userId, Long centerId) {
        User user = userRepository.findUserById(userId);
        Center center = centerRepository.findCenterById(centerId);
        // TODO: request 구조 변경 고려. userId, centerId를 request 안으로..?
        Membership membership = membershipRepository.save(request.toEntity(user, center));
        return MembershipResponse.of(membership);
    }

    @Transactional
    public MembershipResponse updateMembership(Long membershipId, MembershipUpdateRequest request) {
        Membership membership = membershipRepository.findMembershipById(membershipId);
        membership.updateMembership(request.getStartDate(), request.getEndDate(), request.getRemainCount());
        return MembershipResponse.of(membership);
    }

    @Transactional
    public void deleteMembership(Long membershipId) {
        Membership membership = membershipRepository.findMembershipById(membershipId);
        membershipRepository.delete(membership);
    }
}
