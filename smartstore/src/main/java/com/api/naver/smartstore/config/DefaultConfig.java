package com.api.naver.smartstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DefaultConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
