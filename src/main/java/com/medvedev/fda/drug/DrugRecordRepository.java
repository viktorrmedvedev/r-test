package com.medvedev.fda.drug;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface DrugRecordRepository extends CrudRepository<DrugRecord, String> {

    /**
     * For some reason spring was trying to update record
     * during 'save' method call
     * if there was pre-defined applicationNumber
     * That's why this method exists
     *
     * @param applicationNumber
     * @param manufacturerName
     * @param brandName
     * @param substanceName
     * @param productNumbers
     */
    @Query(value = "INSERT INTO drug_records (application_number, manufacturer_name, brand_name, substance_name, product_numbers) " +
            "VALUES (:applicationNumber, :manufacturerName, :brandName, :substanceName, :productNumbers)")
    @Modifying
    void insertRecord(String applicationNumber, String manufacturerName, String brandName, String substanceName, String productNumbers);

    default void insertRecord(DrugRecord drugRecord) {
        insertRecord(
                drugRecord.getApplicationNumber(),
                drugRecord.getManufacturerName(),
                drugRecord.getBrandName(),
                drugRecord.getSubstanceName(),
                drugRecord.getProductNumbers())
        ;
    }
}