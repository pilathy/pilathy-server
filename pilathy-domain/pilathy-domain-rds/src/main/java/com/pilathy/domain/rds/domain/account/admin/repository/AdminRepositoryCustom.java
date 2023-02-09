package com.pilathy.domain.rds.domain.account.admin.repository;

import com.pilathy.domain.rds.domain.account.admin.Admin;

public interface AdminRepositoryCustom {

    Admin findAdminById(Long adminId);

}
