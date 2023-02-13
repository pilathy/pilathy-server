package com.pilathy.domain.rds.config.jpa;

import com.pilathy.domain.rds.domain.RdsDomainRoot;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.pilathy.domain.rds.config.jpa.JpaConfig.TRANSACTION_MANAGER;

@EntityScan(basePackageClasses = {RdsDomainRoot.class})
@EnableJpaRepositories(
        basePackageClasses = {RdsDomainRoot.class},
        transactionManagerRef = TRANSACTION_MANAGER
)
@EnableJpaAuditing
@Configuration
public class JpaConfig {

    public static final String TRANSACTION_MANAGER = "dbTransactionManager";

    @PersistenceContext
    private EntityManager entityManager;

    @Primary
    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager dbTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        return transactionManager;
    }
}
