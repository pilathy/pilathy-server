package com.pilathy.domain.rds.domain.account.user.repository;

import com.pilathy.domain.rds.domain.account.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.pilathy.domain.rds.domain.account.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public User findUserById(Long userId) {
        return queryFactory.selectFrom(user)
                .where(
                        user.id.eq(userId)
                )
                .fetchOne();
    }

    @Override
    public boolean existsUserByEmail(String userEmail) {
        return queryFactory.selectFrom(user)
                .where(
                        user.email.eq(userEmail)
                )
                .fetchFirst() != null;
    }

    @Override
    public User findUserByEmail(String userEmail) {
        return queryFactory.selectFrom(user)
                .where(
                        user.email.eq(userEmail)
                )
                .fetchOne();
    }
}
