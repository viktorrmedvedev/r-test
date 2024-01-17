package com.medvedev.fda.drug;

import com.medvedev.fda.client.dto.FdaResponseDto;
import com.medvedev.fda.drug.dto.DrugDto;

import java.util.Collections;
import java.util.List;

public class DrugTestData {

    public static DrugDto aDrugDto() {
        return DrugDto.builder()
                .applicationNumber("54321")
                .substanceName("Substance1")
                .manufacturerName("Manufacturer1")
                .brandName("Brand1")
                .productNumbers("P1")
                .build();
    }


    public static DrugRecord aDrugRecord() {
        return DrugRecord.builder()
                .applicationNumber("54321")
                .substanceName("Substance1")
                .manufacturerName("Manufacturer1")
                .brandName("Brand1")
                .productNumbers("P1")
                .build();
    }

    public static FdaResponseDto.FdaRecordDto anFdaRecord() {
        return new FdaResponseDto.FdaRecordDto()
                .setApplication_number("54321")
                .setOpenfda(new FdaResponseDto.FdaRecordDto.OpenfdaDto()
                        .setSubstance_name(Collections.singletonList("Substance1"))
                        .setBrand_name(List.of("Brand1"))
                        .setManufacturer_name(List.of("Manufacturer1"))
                )
                .setProducts(
                        List.of(
                                new FdaResponseDto.FdaRecordDto.FdaProductDto().setProduct_number("P1"))
                );
    }
}
