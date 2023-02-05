package com.pilathy.domain.rds.domain.membership;

import com.pilathy.domain.rds.domain.membership.repository.MembershipRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long>, MembershipRepositoryCustom {
}
