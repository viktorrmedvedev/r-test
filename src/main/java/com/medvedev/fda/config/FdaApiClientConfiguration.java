package com.medvedev.fda.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medvedev.fda.client.FdaApiClient;
import com.medvedev.fda.client.FdaApiProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FdaApiClientConfiguration {

    @Bean
    public FdaApiClient fdaApiClient(FdaApiProperties fdaApiProperties, ObjectMapper objectMapper) {
        return FdaApiClient.Factory.createApi(fdaApiProperties, objectMapper);
    }
}