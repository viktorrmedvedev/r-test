package com.medvedev.fda.client;

import com.medvedev.fda.client.dto.FdaResponseDto;
import com.medvedev.fda.client.dto.FdaResponseDto.FdaRecordDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FdaServiceTest {

    private final FdaApiClient fdaApiClient = Mockito.mock(FdaApiClient.class);

    private final FdaService fdaService = new FdaService(fdaApiClient);


    @Test
    public void shouldReturnDrugRecords() {
        final var manufacturerName = "Manufacturer1";
        final var brandName = "Brand1";
        final var paramsMap = Map.of(
                "openfda.manufacturer_name", manufacturerName,
                "openfda.brand_name", brandName
        );

        final var expectedResponse = new FdaResponseDto()
                .setResults(
                        List.of(
                                new FdaRecordDto()
                                .setApplication_number("12344")
                        )
                );

        when(fdaApiClient.getDrugData(eq(paramsMap), eq(0), eq(1)))
                .thenReturn(expectedResponse);

        final var actualResponse = fdaService.getDrugRecords(manufacturerName, brandName, null, null);

        assertEquals(expectedResponse, actualResponse);

        verify(fdaApiClient).getDrugData(eq(paramsMap), eq(0), eq(1));
    }
}
