package com.ridehub.user.config;

import ai.z.openapi.ZaiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties(ZaiChatbotProperties.class)
@ConditionalOnProperty(prefix = "zai.chatbot", name = "enabled", havingValue = "true")
public class ZaiChatbotConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ZaiChatbotConfiguration.class);

    @Bean
    public ZaiClient zaiClient(ZaiChatbotProperties properties) {
        if (StringUtils.hasText(properties.getApiKey())) {
            LOG.info("ZaiClient initialized with base URL: {}", properties.getBaseUrl());
            return ZaiClient.builder()
                    .apiKey(properties.getApiKey())
                    .baseUrl(properties.getBaseUrl())
                    .build();
        }
        LOG.warn("ZAI API key is not configured. Z.ai chatbot features will be disabled.");
        return null;
    }
}