package com.pilathy.domain.rds.domain.account.admin;

import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id", unique = true)
    private Center center;

    @Builder(access = AccessLevel.PRIVATE)
    private Admin(Center center, String email, String password) {
        this.center = center;
        this.email = email;
        this.password = password;
    }

    public static Admin of(Center center, String email, String password) {
        return Admin.builder()
                .center(center)
                .email(email)
                .password(password)
                .build();
    }

}
