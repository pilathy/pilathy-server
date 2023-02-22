package com.pilathy.api.user.service.user;

import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.ConflictException;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.account.user.UserRepository;

public class UserServiceHelper {
    public static void checkNotExistUserByEmail(UserRepository userRepository, String email) {
        if (userRepository.existsUserByEmail(email)) {
            throw new ConflictException(ErrorCode.E409_DUPLICATE_EMAIL);
        }

    }

    public static User findByUserEmail(UserRepository userRepository, String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new NotFoundException(ErrorCode.E404_NOT_EXISTS_USER);
        }
        return user;
    }

    public static User findByUserId(UserRepository userRepository, Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new NotFoundException(ErrorCode.E404_NOT_EXISTS_USER);
        }
        return user;
    }
}
