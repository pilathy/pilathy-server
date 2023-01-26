package com.pilathy.domain.rds.domain.center;

import com.pilathy.domain.rds.domain.account.admin.Admin;
import com.pilathy.domain.rds.domain.common.Address;
import com.pilathy.domain.rds.domain.common.BaseEntity;
import com.pilathy.domain.rds.domain.instructor.Instructor;
import com.pilathy.domain.rds.domain.membership.Membership;
import com.pilathy.domain.rds.domain.notice.Notice;
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
public class Center extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String name;

    @Embedded
    private Address address;

    @Column(length = 15)
    private String phone;

    private String img;

    private String description;

    @OneToOne(mappedBy = "center")
    private Admin admin;

    @OneToMany(mappedBy = "center")
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany(mappedBy = "center")
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "center")
    private List<Membership> memberships = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Center(Admin admin, String name, Address address, String phone, String img, String description) {
        this.admin = admin;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.img = img;
        this.description = description;
    }

    public static Center of(Admin admin, String name, Address address, String phone, String img, String description) {
        return Center.builder()
                .admin(admin)
                .name(name)
                .address(address)
                .phone(phone)
                .img(img)
                .description(description)
                .build();
    }

}
