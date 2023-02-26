package com.pilathy.api.user.controller.user;

import com.pilathy.api.user.dto.user.UserRequest;
import com.pilathy.api.user.dto.user.UserResponse;
import com.pilathy.api.user.service.user.UserService;
import com.pilathy.common.model.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    /**
     * 회원가입
     *
     * @param request
     * @return
     */
    @PostMapping("/user")
    public ApiResponse<String> createUser(@Valid @RequestBody UserRequest request) {
        // TODO : 세션 유효성 체크
        String userEmail = userService.createUser(request);
        return ApiResponse.success(userEmail);
    }

    /**
     * 회원 탈퇴
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/user")
    public ApiResponse<String> deleteUser(@NotNull @RequestParam("id") Long userId) {
        userService.deleteUser(userId);
        return ApiResponse.OK;
    }

    /**
     * 회원 정보 가져오기
     *
     * @param userId
     * @return
     */
    @GetMapping("/user")
    public ApiResponse<UserResponse> getUser(@NotNull @RequestParam("id") Long userId) {
        UserResponse user = userService.findUserById(userId);
        return ApiResponse.success(user);
    }
}
