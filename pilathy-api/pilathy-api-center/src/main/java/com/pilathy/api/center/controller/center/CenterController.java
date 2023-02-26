package com.pilathy.api.center.controller.center;

import com.pilathy.api.center.service.center.CenterService;
import com.pilathy.api.center.service.center.dto.request.CreateCenterRequest;
import com.pilathy.api.center.service.center.dto.request.UpdateCenterRequest;
import com.pilathy.api.center.service.center.dto.response.CenterResponse;
import com.pilathy.common.model.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CenterController {

    private final CenterService centerService;

    @PostMapping("/v1/admin/{adminId}/center")
    public ApiResponse<CenterResponse> createCenter(
            @PathVariable Long adminId,
            @RequestBody CreateCenterRequest request
    ) {
        CenterResponse response = centerService.createCenter(request, adminId);
        return ApiResponse.success(response);
    }


    @PutMapping("/v1/center/{centerId}")
    public ApiResponse<CenterResponse> updateCenter(
            @PathVariable Long centerId,
            @RequestBody UpdateCenterRequest request
    ) {
        CenterResponse response = centerService.updateCenter(request, centerId);
        return ApiResponse.success(response);
    }

    @DeleteMapping("/v1/center/{centerId}")
    public ApiResponse<String> deleteCenter(@PathVariable Long centerId) {
        centerService.deleteCenter(centerId);
        return ApiResponse.OK;
    }

}
