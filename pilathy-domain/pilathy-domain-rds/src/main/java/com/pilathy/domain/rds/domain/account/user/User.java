package com.pilathy.domain.rds.domain.account.user;

import com.pilathy.domain.rds.domain.common.BaseEntity;
import com.pilathy.domain.rds.domain.lesson.Enrollment;
import com.pilathy.domain.rds.domain.membership.Membership;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User extends BaseEntity {

    @OneToMany(mappedBy = "user")
    private List<Membership> memberships = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Enrollment> enrollments = new ArrayList<>();

}
