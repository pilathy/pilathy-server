package com.pilathy.api.user.service.user;

import com.pilathy.api.user.dto.user.UserRequest;
import com.pilathy.common.exception.CustomException;
import com.pilathy.common.exception.ErrorCode;
import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.account.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String createUser(UserRequest request) {
        if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.E409_DUPLICATE_EMAIL);
        }
        User user = userRepository.save(request.toEntity());
        return user.getEmail();
    }
}
