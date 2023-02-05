package com.pilathy.domain.rds.domain.membership.repository;

import com.pilathy.domain.rds.domain.membership.Membership;

public interface MembershipRepositoryCustom {

    Membership findMembershipById(Long membershipId);

}
