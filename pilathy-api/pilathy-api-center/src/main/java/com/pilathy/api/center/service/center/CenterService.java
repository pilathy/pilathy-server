package com.pilathy.api.center.service.center;

import com.pilathy.api.center.service.center.dto.request.CreateCenterRequest;
import com.pilathy.api.center.service.center.dto.request.UpdateCenterRequest;
import com.pilathy.api.center.service.center.dto.response.CenterResponse;
import com.pilathy.domain.rds.domain.account.admin.Admin;
import com.pilathy.domain.rds.domain.account.admin.AdminRepository;
import com.pilathy.domain.rds.domain.center.Center;
import com.pilathy.domain.rds.domain.center.CenterRepository;
import com.pilathy.domain.rds.domain.membership.Membership;
import com.pilathy.domain.rds.domain.membership.MembershipRepository;
import com.pilathy.domain.service.service.account.admin.AdminServiceHelper;
import com.pilathy.domain.service.service.center.CenterServiceHelper;
import com.pilathy.domain.service.service.membership.MembershipServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CenterService {

    private final CenterRepository centerRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public CenterResponse createCenter(CreateCenterRequest request, Long adminId) {
        Admin admin = AdminServiceHelper.findAdminById(adminRepository, adminId);
        Center center = request.toEntity(admin);
        centerRepository.save(center);
        return CenterResponse.of(center);
    }

    @Transactional
    public CenterResponse updateCenter(UpdateCenterRequest request, Long centerId) {
        Center center = CenterServiceHelper.findCenterById(centerRepository, centerId);
        center.updateCenter(request.getName(), request.getZipcode(), request.getDefaultAddress(), request.getDetailAddress(), request.getPhone(), request.getImg(), request.getDescription());
        return CenterResponse.of(center);
    }

    @Transactional
    public void deleteCenter(Long centerId) {
        Center center = CenterServiceHelper.findCenterById(centerRepository, centerId);
        centerRepository.delete(center);
    }

}
