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

    @Builder(access = AccessLevel.PRIVATE)
    private Membership(DateInterval dateInterval, int remainCount, User user, Center center) {
        this.dateInterval = dateInterval;
        this.remainCount = remainCount;
        this.user = user;
        this.center = center;
    }

    public static Membership of(DateInterval dateInterval, int remainCount, User user, Center center) {
        return Membership.builder()
                .dateInterval(dateInterval)
                .remainCount(remainCount)
                .user(user)
                .center(center)
                .build();
    }

}