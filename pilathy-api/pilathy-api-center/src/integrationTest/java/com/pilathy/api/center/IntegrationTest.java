package com.pilathy.api.center;

import com.pilathy.domain.rds.lib.DatabaseCleaner;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class IntegrationTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @AfterEach
    protected void cleanup() {
        databaseCleaner.cleanUp();
    }

}
