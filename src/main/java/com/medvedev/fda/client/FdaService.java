package com.medvedev.fda.client;

import com.medvedev.fda.client.FdaApiClient;
import com.medvedev.fda.client.dto.FdaResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FdaService {
    private final FdaApiClient fdaApiClient;
    private static final String MANUFACTURER_NAME_PARAM = "openfda.manufacturer_name";
    private static final String BRAND_NAME_PARAM = "openfda.brand_name";
    private static final Integer DEFAULT_PAGE_SIZE = 1;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;

    public FdaResponseDto getDrugRecords(String manufacturerName, String brandName, Integer page, Integer pageSize) {
        final var searchParams = defineSearchParams(manufacturerName, brandName);
        final var actualPageSize = definePageSize(pageSize);
        final var actualSkipValue = defineSkipValue(page, actualPageSize);
        return fdaApiClient.getDrugData(searchParams, actualSkipValue, actualPageSize);
    }

    private static int definePageSize(Integer pageSize) {
        return Integer.max(ObjectUtils.firstNonNull(pageSize, DEFAULT_PAGE_SIZE), DEFAULT_PAGE_SIZE);
    }

    private static int defineSkipValue(Integer page, int actualPageSize) {
        final var actualPageNumber = Integer.max(ObjectUtils.firstNonNull(page, DEFAULT_PAGE_NUMBER), DEFAULT_PAGE_NUMBER);
        return (actualPageNumber - 1) * actualPageSize;
    }

    private Map<String, String> defineSearchParams(String manufacturerName, String brandName) {
        final var searchParams = new HashMap<String, String>();
        searchParams.put(MANUFACTURER_NAME_PARAM, manufacturerName);
        if (StringUtils.isNotBlank(brandName)) {
            searchParams.put(BRAND_NAME_PARAM, brandName);
        }
        return searchParams;
    }
}
