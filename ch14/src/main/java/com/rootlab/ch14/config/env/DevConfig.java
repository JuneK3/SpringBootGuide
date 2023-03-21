package com.rootlab.ch14.config.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevConfig implements EnvConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(DevConfig.class);

    @Value("${log.loading.message}")
    private String message;

    @Override
    public String getMessage() {
        LOGGER.info("[getMessage] DevConfiguration 입니다.");
        return message;
    }
}