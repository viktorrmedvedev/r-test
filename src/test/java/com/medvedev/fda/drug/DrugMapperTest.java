package com.medvedev.fda.drug;

import com.medvedev.fda.client.dto.FdaResponseDto.FdaRecordDto;
import com.medvedev.fda.client.dto.FdaResponseDto.FdaRecordDto.FdaProductDto;
import com.medvedev.fda.drug.dto.DrugDto;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrugMapperTest {

    @Test
    void shouldMapFdaRecordToDto() {
        // Given

        final var fdaRecord = new FdaRecordDto()
                .setApplication_number("12345")
                .setOpenfda(new FdaRecordDto.OpenfdaDto()
                        .setSubstance_name(Collections.singletonList("Substance1"))
                        .setBrand_name(List.of("Brand1", "Brand2"))
                        .setManufacturer_name(List.of("Manufacturer1", "Manufacturer2"))
                )
                .setProducts(
                        List.of(
                                new FdaProductDto().setProduct_number("P1"),
                                new FdaProductDto().setProduct_number("P2"))
                );

        // When
        final var drugDto = DrugMapper.fromFdaRecord(fdaRecord);

        // Then
        assertEquals("12345", drugDto.getApplicationNumber());
        assertEquals("Substance1", drugDto.getSubstanceName());
        assertEquals("Brand1, Brand2", drugDto.getBrandName());
        assertEquals("Manufacturer1, Manufacturer2", drugDto.getManufacturerName());
        assertEquals("P1, P2", drugDto.getProductNumbers());
    }

    @Test
    void shouldMapDrugDomainToDto() {
        // Given
        final var drugRecord = DrugRecord.builder()
                .applicationNumber("54321")
                .substanceName("Substance2")
                .manufacturerName("Manufacturer2")
                .brandName("Brand3")
                .productNumbers("P3, P4")
                .build();

        // When
        final var drugDto = DrugMapper.toDto(drugRecord);

        // Then
        assertEquals("54321", drugDto.getApplicationNumber());
        assertEquals("Substance2", drugDto.getSubstanceName());
        assertEquals("Manufacturer2", drugDto.getManufacturerName());
        assertEquals("Brand3", drugDto.getBrandName());
        assertEquals("P3, P4", drugDto.getProductNumbers());
    }

    @Test
    void shouldMapDrugDtoToDomain() {
        // Given
        final var drugDto = DrugDto.builder()
                .applicationNumber("98765")
                .substanceName("Substance3")
                .manufacturerName("Manufacturer3")
                .brandName("Brand4")
                .productNumbers("P5, P6")
                .build();

        // When
        final var drugRecord = DrugMapper.toDomain(drugDto);

        // Then
        assertEquals("98765", drugRecord.getApplicationNumber());
        assertEquals("Substance3", drugRecord.getSubstanceName());
        assertEquals("Manufacturer3", drugRecord.getManufacturerName());
        assertEquals("Brand4", drugRecord.getBrandName());
        assertEquals("P5, P6", drugRecord.getProductNumbers());
    }
}