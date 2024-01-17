package com.medvedev.fda.drug;

import com.medvedev.fda.client.FdaService;
import com.medvedev.fda.client.dto.FdaResponseDto;
import com.medvedev.fda.drug.dto.DrugDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.medvedev.fda.drug.DrugTestData.aDrugRecord;
import static com.medvedev.fda.drug.DrugTestData.aDrugDto;
import static com.medvedev.fda.drug.DrugTestData.anFdaRecord;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class DrugRecordControllerTest {

    private final FdaService fdaService = Mockito.mock(FdaService.class);
    private final DrugRecordService drugRecordService = Mockito.mock(DrugRecordService.class);
    private final DrugRecordController drugRecordController = new DrugRecordController(fdaService, drugRecordService);

    @BeforeEach
    void setUp() {
        // Reset mocks before each test
        Mockito.reset(fdaService, drugRecordService);
    }

    @Test
    void testGetDrugRecordsFromFda() {
        // Given
        final var expectedDtos = List.of(aDrugDto());
        when(fdaService.getDrugRecords("Manufacturer1", null, 1, 1))
                .thenReturn(new FdaResponseDto().setResults(List.of(anFdaRecord())));

        // When
        final List<DrugDto> drugDtos = drugRecordController.getDrugRecordsFromFda("Manufacturer1", null, 1, 1);

        // Then
        assertEquals(expectedDtos, drugDtos);
    }

    @Test
    void testGetStoredFdaRecords() {
        // Given
        final var expectedDtos = List.of(aDrugDto());
        when(drugRecordService.findAll()).thenReturn(List.of(aDrugRecord()));

        // When
        final var drugDtos = drugRecordController.getStoredFdaRecords();

        // Then
        assertEquals(expectedDtos, drugDtos);
    }

    @Test
    void testStore() {
        // Given
        final var drugDto = aDrugDto();

        final var drugRecord = aDrugRecord();

        doNothing().when(drugRecordService).create(drugRecord);
        when(drugRecordService.getByApplicationNumber("54321")).thenReturn(drugRecord);

        // When
        final var responseEntity = drugRecordController.store(drugDto);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(drugDto, responseEntity.getBody());
    }

    @Test
    void testDeleteStoredRecord() {
        // Given
        final var applicationNumber = "12345";
        doNothing().when(drugRecordService).deleteByApplicationNumber(applicationNumber);

        // When
        final var responseEntity = drugRecordController.delete(applicationNumber);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }




}
