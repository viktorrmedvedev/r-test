package com.medvedev.fda.drug;

import com.medvedev.fda.client.dto.FdaResponseDto.FdaRecordDto;
import com.medvedev.fda.client.dto.FdaResponseDto.FdaRecordDto.FdaProductDto;
import com.medvedev.fda.drug.dto.DrugDto;

import java.util.stream.Collectors;

public class DrugMapper {

    public static DrugDto fromFdaRecord(FdaRecordDto fdaRecordDto) {
        return DrugDto.builder()
                .applicationNumber(fdaRecordDto.getApplication_number())
                .substanceName(String.join(", ", fdaRecordDto.getOpenfda().getSubstance_name()))
                .brandName(String.join(", ", fdaRecordDto.getOpenfda().getBrand_name()))
                .manufacturerName(String.join(", ", fdaRecordDto.getOpenfda().getManufacturer_name()))
                .productNumbers(String.join(", ", fdaRecordDto.getProducts()
                        .stream()
                        .map(FdaProductDto::getProduct_number)
                        .collect(Collectors.joining(", "))))
                .build();
    }

    public static DrugDto toDto(DrugRecord drugRecord) {
        return DrugDto.builder()
                .applicationNumber(drugRecord.getApplicationNumber())
                .substanceName(drugRecord.getSubstanceName())
                .manufacturerName(drugRecord.getManufacturerName())
                .brandName(drugRecord.getBrandName())
                .productNumbers(drugRecord.getProductNumbers())
                .build();
    }

    public static DrugRecord toDomain(DrugDto drugDto) {
        return DrugRecord.builder()
                .applicationNumber(drugDto.getApplicationNumber())
                .substanceName(drugDto.getSubstanceName())
                .manufacturerName(drugDto.getManufacturerName())
                .brandName(drugDto.getBrandName())
                .productNumbers(drugDto.getProductNumbers())
                .build();
    }
}
