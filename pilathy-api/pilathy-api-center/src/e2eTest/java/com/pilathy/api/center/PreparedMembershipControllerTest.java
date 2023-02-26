package com.pilathy.api.center;

import com.pilathy.domain.rds.domain.account.admin.Admin;
import com.pilathy.domain.rds.domain.account.admin.AdminFixture;
import com.pilathy.domain.rds.domain.account.admin.AdminRepository;
import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.account.user.UserFixture;
import com.pilathy.domain.rds.domain.account.user.UserRepository;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.center.CenterFixture;
import com.pilathy.domain.rds.domain.center.CenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PreparedMembershipControllerTest extends ControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CenterRepository centerRepository;

    @Autowired
    AdminRepository adminRepository;

    protected User user;
    protected Admin admin;
    protected Center center;

    @BeforeEach
    void setup() {
        user = UserFixture.create();
        admin = AdminFixture.create();
        center = CenterFixture.create(admin);

        userRepository.save(user);
        adminRepository.save(admin);
        centerRepository.save(center);
    }

}
