package com.medvedev.fda;

import com.medvedev.fda.client.FdaApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(FdaApiProperties.class)
@SpringBootApplication
public class FdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FdaApplication.class, args);
    }

}
