package com.pilathy.api.user.controller.user;

import com.pilathy.api.user.dto.user.UserRequest;
import com.pilathy.api.user.service.user.UserService;
import com.pilathy.common.model.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원가입
     *
     * @param request
     * @return
     */
    @PostMapping("/user")
    public ApiResponse<String> createUser(@RequestBody @Valid UserRequest request) {
        // TODO : 세션 유효성 체크
        String userEmail = userService.createUser(request);
        return ApiResponse.success(userEmail);
    }
}
