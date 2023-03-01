package com.pilathy.api.user.service.account.user;

import com.pilathy.api.user.dto.account.user.request.CreateUserRequest;
import com.pilathy.api.user.dto.account.user.request.UpdateUserInfoRequest;
import com.pilathy.api.user.dto.account.user.request.UpdateUserPasswordRequest;
import com.pilathy.api.user.dto.account.user.response.UserResponse;
import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.account.user.UserRepository;
import com.pilathy.domain.service.service.account.user.UserServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        UserServiceHelper.checkNotExistUserByEmail(userRepository, request.getEmail());
        request.encodePassword(passwordEncoder, request.getPassword());
        User user = userRepository.save(request.toEntity());
        return UserResponse.of(user);
    }

    @Transactional
    public UserResponse updateUserInfo(UpdateUserInfoRequest request, Long userId) {
        User user = UserServiceHelper.findUserById(userRepository, userId);
        user.updateUserInfo(request.getEmail(), request.getName(), request.getBirthDate(), request.getPhone());
        return UserResponse.of(user);
    }

    @Transactional
    public void updateUserPassword(UpdateUserPasswordRequest request, Long userId) {
        User user = UserServiceHelper.findUserById(userRepository, userId);
        user.updateUserPassword(passwordEncoder.encode(request.getPassword()));
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = UserServiceHelper.findUserById(userRepository, userId);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserInfo(Long userId) {
        User user = UserServiceHelper.findUserById(userRepository, userId);
        return UserResponse.of(user);
    }
}
