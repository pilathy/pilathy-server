package com.pilathy.api.center.controller.membership;

import com.pilathy.api.center.service.membership.MembershipService;
import com.pilathy.api.center.service.membership.dto.request.MembershipRegisterRequest;
import com.pilathy.api.center.service.membership.dto.request.MembershipUpdateRequest;
import com.pilathy.api.center.service.membership.dto.response.MembershipResponse;
import com.pilathy.common.model.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping("/v1/membership")
    public ApiResponse<MembershipResponse> registerMembership(
            @RequestBody MembershipRegisterRequest request,
            Long userId,
            Long centerId
    ) {
        MembershipResponse response = membershipService.registerMembership(request, userId, centerId);
        // TODO: registerMembership validate
        return ApiResponse.success(response);
    }

    @PutMapping("/v1/membership/{membershipId}")
    public ApiResponse<MembershipResponse> updateMembership(
            @PathVariable Long membershipId,
            @RequestBody MembershipUpdateRequest request
    ) {
        MembershipResponse response = membershipService.updateMembership(membershipId, request);
        // TODO : updateMembership validate
        return ApiResponse.success(response);
    }

    @DeleteMapping("/v1/membership/{membershipId}")
    public ApiResponse<String> deleteMembership(@PathVariable Long membershipId) {
        // TODO: @Auth 만들어서 인증된 admin인지 검증
        membershipService.deleteMembership(membershipId);
        return ApiResponse.OK;
    }
}
