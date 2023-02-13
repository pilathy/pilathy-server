package com.pilathy.domain.rds.domain.membership;

import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.common.BaseEntity;
import com.pilathy.domain.rds.domain.common.DateInterval;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Membership extends BaseEntity {

    @Embedded
    private DateInterval dateInterval;

    @Column(nullable = false)
    private int remainCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @Builder(access = AccessLevel.PACKAGE)
    private Membership(LocalDate startDate, LocalDate endDate, int remainCount, User user, Center center) {
        this.dateInterval = DateInterval.of(startDate, endDate);
        this.remainCount = remainCount;
        this.user = user;
        this.center = center;
    }

    public static Membership of(LocalDate startDate, LocalDate endDate, int remainCount, User user, Center center) {
        return Membership.builder()
                .startDate(startDate)
                .endDate(endDate)
                .remainCount(remainCount)
                .user(user)
                .center(center)
                .build();
    }

    public void updateMembership(LocalDate startDate, LocalDate endDate, int remainCount) {
        this.dateInterval = DateInterval.of(startDate, endDate);
        this.remainCount = remainCount;
    }

}