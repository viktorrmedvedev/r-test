package com.medvedev.fda.drug;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder(toBuilder = true)
@Table("drug_records")
public class DrugRecord {
    @Id
    private String applicationNumber;
    private String manufacturerName;
    private String brandName;
    private String substanceName;
    private String productNumbers;
}
