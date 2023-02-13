package com.pilathy.api.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.pilathy")
public class PilathyApiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PilathyApiUserApplication.class, args);
    }

}
