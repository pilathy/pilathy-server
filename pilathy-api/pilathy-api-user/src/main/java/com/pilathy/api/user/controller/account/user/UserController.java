package com.pilathy.api.user.controller.account.user;

import com.pilathy.api.user.dto.account.user.request.CreateUserRequest;
import com.pilathy.api.user.dto.account.user.request.UpdateUserInfoRequest;
import com.pilathy.api.user.dto.account.user.request.UpdateUserPasswordRequest;
import com.pilathy.api.user.dto.account.user.response.UserResponse;
import com.pilathy.api.user.service.account.user.UserService;
import com.pilathy.common.model.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     *
     * @param request CreateUserRequest
     * @return UserResponse
     */
    @PostMapping("/v1/user")
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse response = userService.createUser(request);
        return ApiResponse.success(response);
    }

    /**
     * 회원 정보 수정
     *
     * @param userId
     * @param request UpdateUserInfoRequest
     * @return UserResponse
     */
    @PutMapping("/v1/user/{userId}")
    public ApiResponse<UserResponse> updateUserInfo(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserInfoRequest request
    ) {
        UserResponse response = userService.updateUserInfo(request, userId);
        return ApiResponse.success(response);
    }

    /**
     * 회원 비밀번호 수정
     *
     * @param userId
     * @param request UpdateUserPasswordRequest
     * @return String
     */
    @PutMapping("/v1/user/{userId}/password")
    public ApiResponse<String> updateUserPassword(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserPasswordRequest request
    ) {
        userService.updateUserPassword(request, userId);
        return ApiResponse.OK;
    }

    /**
     * 회원 탈퇴
     *
     * @param userId
     * @return String
     */
    @DeleteMapping("/v1/user/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ApiResponse.OK;
    }


    /**
     * 회원 정보 조회
     *
     * @param userId
     * @return UserResponse
     */
    @GetMapping("/v1/user/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable Long userId) {
        UserResponse response = userService.getUserInfo(userId);
        return ApiResponse.success(response);
    }

}
