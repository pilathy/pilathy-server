package com.pilathy.api.center;

import com.pilathy.domain.rds.domain.account.admin.Admin;
import com.pilathy.domain.rds.domain.account.admin.AdminFixture;
import com.pilathy.domain.rds.domain.account.admin.AdminRepository;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.center.CenterFixture;
import com.pilathy.domain.rds.domain.center.CenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class PreparedCenterIntegrationTest extends IntegrationTest {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CenterRepository centerRepository;

    protected Admin admin;
    protected Center center;

    @BeforeEach
    void setup() {
        admin = AdminFixture.create();
        center = CenterFixture.create(admin);

        adminRepository.save(admin);
        centerRepository.save(center);
    }

}
