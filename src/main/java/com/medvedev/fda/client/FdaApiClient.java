package com.medvedev.fda.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medvedev.fda.client.dto.FdaResponseDto;
import com.medvedev.fda.client.util.DisableEncodingRequestInterceptor;
import com.medvedev.fda.client.util.SearchExpander;
import feign.Feign;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface FdaApiClient {

    @RequestLine("GET /drug/drugsfda.json?search={search}&skip={skip}&limit={limit}")
    FdaResponseDto getDrugData(
            @Param(value = "search", expander = SearchExpander.class) Map<String, String> searchParams,
            @Param(value = "skip") int skip,
            @Param(value = "limit") int limit
    );

    class Factory {
        private static final int HUNDRED_MILLIS = 100;
        private static final long SECOND_IN_MILLIS = TimeUnit.SECONDS.toMillis(1L);

        private Factory() {
        }

        public static FdaApiClient createApi(FdaApiProperties properties, ObjectMapper mapper) {
            return Feign.builder()
                    .client(new OkHttpClient())
                    .encoder(new JacksonEncoder(mapper))
                    .decoder(new JacksonDecoder(mapper))
                    .options(properties.requestOptions())
                    .logger(new Slf4jLogger(FdaApiClient.class))
                    .logLevel(Logger.Level.BASIC)
                    .requestInterceptor(new DisableEncodingRequestInterceptor())
                    .retryer(new Retryer.Default(HUNDRED_MILLIS, SECOND_IN_MILLIS, 3))
                    .target(FdaApiClient.class, properties.baseUrl());
        }
    }
}