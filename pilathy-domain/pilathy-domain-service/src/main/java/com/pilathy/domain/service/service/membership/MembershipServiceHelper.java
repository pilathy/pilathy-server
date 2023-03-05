package com.pilathy.domain.service.service.membership;

import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.domain.rds.domain.membership.Membership;
import com.pilathy.domain.rds.domain.membership.MembershipRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MembershipServiceHelper {

    public static Membership findMembershipById(MembershipRepository membershipRepository, Long membershipId) {
        Membership membership = membershipRepository.findMembershipById(membershipId);
        if (membership == null) {
            throw new NotFoundException(String.format("해당하는 멤버십(%s)은 존재하지 않습니다", membershipId), ErrorCode.E404_NOT_EXISTS_MEMBERSHIP);
        }
        return membership;
    }

}
