package com.medvedev.fda.drug.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugDto {
    private String applicationNumber;
    private String manufacturerName;
    private String substanceName;
    private String brandName;
    private String productNumbers;
}
