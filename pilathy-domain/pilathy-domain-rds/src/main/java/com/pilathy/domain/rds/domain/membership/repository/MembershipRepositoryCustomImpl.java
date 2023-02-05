package com.pilathy.domain.rds.domain.membership.repository;

import com.pilathy.domain.rds.domain.membership.Membership;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.pilathy.domain.rds.domain.membership.QMembership.membership;

@RequiredArgsConstructor
public class MembershipRepositoryCustomImpl implements MembershipRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Membership findMembershipById(Long membershipId) {
        return queryFactory.selectFrom(membership)
                .where(
                        membership.id.eq(membershipId)
                )
                .fetchOne();
    }

}
