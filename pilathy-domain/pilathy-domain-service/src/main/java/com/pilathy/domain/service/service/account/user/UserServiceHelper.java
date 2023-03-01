package com.pilathy.domain.service.service.account.user;

import com.pilathy.common.exception.ErrorCode;
import com.pilathy.common.exception.model.ConflictException;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.domain.rds.domain.account.user.User;
import com.pilathy.domain.rds.domain.account.user.UserRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceHelper {

    public static void checkNotExistUserByEmail(UserRepository userRepository, String email) {
        if (userRepository.existsUserByEmail(email)) {
            throw new ConflictException(ErrorCode.E409_DUPLICATE_EMAIL);
        }
    }

    public static User findUserByEmail(UserRepository userRepository, String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new NotFoundException(ErrorCode.E404_NOT_EXISTS_USER);
        }
        return user;
    }

    public static User findUserById(UserRepository userRepository, Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new NotFoundException(String.format("해당하는 사용자(%s)는 존재하지 않습니다.", userId), ErrorCode.E404_NOT_EXISTS_USER);
        }
        return user;
    }

}
