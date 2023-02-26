package com.pilathy.domain.rds.domain.account.user;

import com.pilathy.domain.rds.domain.common.BaseEntity;
import com.pilathy.domain.rds.domain.common.Email;
import com.pilathy.domain.rds.domain.membership.Membership;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, length = 50)
    private Email email;

    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    private LocalDate birthDate;

    @Column(length = 15)
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Membership> memberships = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    private User(String email, String password, String name, LocalDate birthDate, String phone) {
        this.email = Email.of(email);
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public static User of(String email, String password, String name, LocalDate birthDate, String phone) {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .birthDate(birthDate)
                .phone(phone)
                .build();
    }

}
