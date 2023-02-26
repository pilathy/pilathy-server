package com.pilathy.api.user.service.user;

import com.pilathy.api.user.dto.user.UserRequest;
import com.pilathy.api.user.dto.user.UserResponse;
import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.account.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String createUser(UserRequest request) {
        UserServiceHelper.checkNotExistUserByEmail(userRepository, request.getEmail());
        User user = userRepository.save(request.toEntity());
        return user.getEmail();
    }

    public void deleteUser(Long userId) {
        User user = UserServiceHelper.findByUserId(userRepository, userId);
        userRepository.delete(user);
    }

    public UserResponse findUserById(Long userId) {
        User user = UserServiceHelper.findByUserId(userRepository, userId);
        return UserResponse.of(user);
    }
}
