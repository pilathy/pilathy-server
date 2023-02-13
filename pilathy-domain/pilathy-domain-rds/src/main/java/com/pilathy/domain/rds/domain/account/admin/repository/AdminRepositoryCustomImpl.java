package com.pilathy.domain.rds.domain.account.admin.repository;

import com.pilathy.domain.rds.domain.account.admin.Admin;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.pilathy.domain.rds.domain.account.admin.QAdmin.admin;

@RequiredArgsConstructor
public class AdminRepositoryCustomImpl implements AdminRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Admin findAdminById(Long adminId) {
        return queryFactory.selectFrom(admin)
                .where(
                        admin.id.eq(adminId)
                )
                .fetchOne();
    }

}
