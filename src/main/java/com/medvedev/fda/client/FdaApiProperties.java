package com.medvedev.fda.client;

import feign.Request;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "fda")
public record FdaApiProperties(String baseUrl, long connectTimeoutMillis, long readTimeoutMillis) {

    public Request.Options requestOptions() {
        return new Request.Options(
                connectTimeoutMillis, TimeUnit.MILLISECONDS,
                readTimeoutMillis, TimeUnit.MILLISECONDS,
                true);
    }
}
