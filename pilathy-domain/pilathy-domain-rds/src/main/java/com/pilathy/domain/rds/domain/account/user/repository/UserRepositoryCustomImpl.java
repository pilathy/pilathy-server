package com.pilathy.domain.rds.domain.account.user.repository;

import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.common.Email;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import static com.pilathy.domain.rds.domain.account.user.QUser.user;
import static org.springframework.util.StringUtils.hasText;

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
    public boolean existsUserByEmail(String email) {
        return queryFactory.selectFrom(user)
                .where(
                        emailEq(email)
                )
                .fetchFirst() != null;
    }

    @Override
    public User findUserByEmail(String email) {
        return queryFactory.selectFrom(user)
                .where(
                        emailEq(email)
                )
                .fetchOne();
    }

    private BooleanExpression emailEq(String email) {
        return hasText(email) ? user.email.eq(Email.of(email)) : null;
    }

}
