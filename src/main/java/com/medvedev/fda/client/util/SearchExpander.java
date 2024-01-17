package com.medvedev.fda.client.util;

import feign.Param;
import org.apache.commons.lang3.Validate;

import java.util.Map;
import java.util.stream.Collectors;

public class SearchExpander implements Param.Expander {
    @Override
    public String expand(Object value) {

        if (value instanceof Map) {
            return ((Map<?, ?>) value).entrySet()
                    .stream()
                    .peek(entry -> Validate.validState(entry.getKey() instanceof String && entry.getValue() instanceof String))
                    .map(entry -> "%s:%s".formatted(entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining("+"));
        }

        throw new IllegalStateException("Search param must be of type java.lang.Map<String, String>, but was %s".formatted(value.getClass()));
    }
}
