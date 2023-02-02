package com.pilathy.domain.rds.domain.account.user;

import com.pilathy.domain.rds.domain.account.user.repository.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
