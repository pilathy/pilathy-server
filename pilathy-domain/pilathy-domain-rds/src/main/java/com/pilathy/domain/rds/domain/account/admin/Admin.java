package com.pilathy.domain.rds.domain.account.admin;

import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.common.BaseEntity;
import com.pilathy.domain.rds.domain.common.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin extends BaseEntity {

    @Column(nullable = false, length = 50)
    private Email email;

    private String password;

    @OneToMany(mappedBy = "admin")
    private List<Center> centers = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    private Admin(String email, String password) {
        this.email = Email.of(email);
        this.password = password;
    }

    public static Admin of(String email, String password) {
        return Admin.builder()
                .email(email)
                .password(password)
                .build();
    }

}
