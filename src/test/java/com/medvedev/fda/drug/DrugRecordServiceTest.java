package com.medvedev.fda.drug;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.medvedev.fda.drug.DrugTestData.aDrugRecord;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DrugRecordServiceTest {

    private final DrugRecordRepository drugRecordRepository = Mockito.mock(DrugRecordRepository.class);
    private final DrugRecordService drugRecordService = new DrugRecordService(drugRecordRepository);

    @BeforeEach
    void setUp() {
        // Reset mocks before each test
        Mockito.reset(drugRecordRepository);
    }

    @Test
    void shouldReturnAllRecords() {
        // Given
        final var expectedRecords = List.of(
            aDrugRecord().toBuilder().applicationNumber("1").build(),
            aDrugRecord().toBuilder().applicationNumber("2").build()

        );

        when(drugRecordRepository.findAll()).thenReturn(expectedRecords);

        // When
        final var records = drugRecordService.findAll();

        // Then
        assertEquals(expectedRecords, records);
    }

    @Test
    void testSave() {
        // Given
        final var drugRecord = aDrugRecord();

        doNothing().when(drugRecordRepository).insertRecord(drugRecord);

        // When
        drugRecordService.create(drugRecord);

        // Then
        verify(drugRecordRepository, times(1)).insertRecord(drugRecord);
    }

    @Test
    void testGetByApplicationNumber() {
        // Given
        final var expectedRecord = aDrugRecord();
        final var applicationNumber = aDrugRecord().getApplicationNumber();

        when(drugRecordRepository.findById(applicationNumber)).thenReturn(Optional.of(expectedRecord));

        // When
        final DrugRecord record = drugRecordService.getByApplicationNumber(applicationNumber);

        // Then
        assertEquals(expectedRecord, record);
    }

    @Test
    void testDeleteByApplicationNumber() {
        // Given
        final var applicationNumber = "12345";

        when(drugRecordRepository.existsById(applicationNumber)).thenReturn(true);

        // When
        drugRecordService.deleteByApplicationNumber(applicationNumber);

        // Then
        verify(drugRecordRepository, times(1)).deleteById(applicationNumber);
    }
}
