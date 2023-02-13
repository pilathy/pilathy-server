package com.pilathy.domain.rds.domain.account.admin;

import com.pilathy.domain.rds.domain.account.admin.repository.AdminRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryCustom {
}
