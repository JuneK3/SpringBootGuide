package com.rootlab.ch14;

import com.rootlab.ch14.config.ProfileManager;
import com.rootlab.ch14.config.env.EnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ch14Application {
    private final Logger LOGGER = LoggerFactory.getLogger(Ch14Application.class);

    @Autowired
    public Ch14Application(EnvConfig envConfig, ProfileManager profileManager) {
        LOGGER.info(envConfig.getMessage());
        profileManager.printActiveProfiles();
    }

    public static void main(String[] args) {
        SpringApplication.run(Ch14Application.class, args);
    }
}