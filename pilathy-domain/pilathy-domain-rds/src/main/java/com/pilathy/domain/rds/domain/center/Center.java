package com.pilathy.domain.rds.domain.center;

import com.pilathy.domain.rds.domain.account.admin.Admin;
import com.pilathy.domain.rds.domain.common.BaseEntity;
import com.pilathy.domain.rds.domain.instructor.Instructor;
import com.pilathy.domain.rds.domain.membership.Membership;
import com.pilathy.domain.rds.domain.notice.Notice;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Center extends BaseEntity {

    @OneToOne(mappedBy = "center")
    private Admin admin;

    @OneToMany(mappedBy = "center")
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany(mappedBy = "center")
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "center")
    private List<Membership> memberships = new ArrayList<>();

}
