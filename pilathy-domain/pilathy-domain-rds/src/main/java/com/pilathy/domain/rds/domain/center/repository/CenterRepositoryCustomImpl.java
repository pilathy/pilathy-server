package com.pilathy.domain.rds.domain.center.repository;

import com.pilathy.domain.rds.domain.center.Center;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.pilathy.domain.rds.domain.center.QCenter.center;

@RequiredArgsConstructor
public class CenterRepositoryCustomImpl implements CenterRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Center findCenterById(Long centerId) {
        return queryFactory.selectFrom(center)
                .where(
                        center.id.eq(centerId)
                ).fetchOne();
    }

}
