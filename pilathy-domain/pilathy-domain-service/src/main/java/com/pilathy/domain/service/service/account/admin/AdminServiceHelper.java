package com.pilathy.domain.service.service.account.admin;

import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.domain.rds.domain.account.admin.Admin;
import com.pilathy.domain.rds.domain.account.admin.AdminRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminServiceHelper {

    public static Admin findAdminById(AdminRepository adminRepository, Long adminId) {
        Admin admin = adminRepository.findAdminById(adminId);
        if (admin == null) {
            throw new NotFoundException(String.format("해당하는 관리자(%s)는 존재하지 않습니다.", adminId), ErrorCode.E404_NOT_EXISTS_ADMIN);
        }
        return admin;
    }

}
