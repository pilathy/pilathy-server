package com.pilathy.domain.rds.domain.account.user.repository;

import com.pilathy.domain.rds.domain.account.user.User;

public interface UserRepositoryCustom {

    User findUserById(Long userId);

    boolean existsUserByEmail(String email);

    User findUserByEmail(String email);
}
