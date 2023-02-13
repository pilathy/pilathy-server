package com.pilathy.api.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static com.pilathy.common.constants.PackageConstants.BASE_PACKAGE;

@ConfigurationPropertiesScan(basePackages = BASE_PACKAGE)
@SpringBootApplication(scanBasePackages = BASE_PACKAGE)
public class PilathyApiCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PilathyApiCenterApplication.class, args);
    }
}
