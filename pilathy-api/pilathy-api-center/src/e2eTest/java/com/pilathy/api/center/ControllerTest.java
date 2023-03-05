package com.pilathy.api.center;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilathy.domain.rds.lib.DatabaseCleaner;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected DatabaseCleaner databaseCleaner;

    @AfterEach
    protected void cleanUp() {
        databaseCleaner.cleanUp();
    }

}
