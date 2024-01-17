package com.medvedev.fda.client.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class DisableEncodingRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.uri(template.path().replaceAll("%2B", "+"));
    }
}