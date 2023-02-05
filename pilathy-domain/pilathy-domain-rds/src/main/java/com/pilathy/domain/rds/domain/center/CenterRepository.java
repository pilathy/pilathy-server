package com.pilathy.domain.rds.domain.center;

import com.pilathy.domain.rds.domain.center.repository.CenterRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<Center, Long>, CenterRepositoryCustom {
}
