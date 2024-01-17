package com.medvedev.fda.drug;

import com.medvedev.fda.client.FdaService;
import com.medvedev.fda.drug.dto.DrugDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/drug-records")
@RequiredArgsConstructor
public class DrugRecordController {
    private final FdaService fdaService;
    private final DrugRecordService drugRecordService;

    @Operation(summary = "Get drug records directly from fda-api")
    @GetMapping("/fda-api")
    public List<DrugDto> getDrugRecordsFromFda(
            @RequestParam String manufacturerName,
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "1") Integer pageSize
    ) {
        return fdaService.getDrugRecords(manufacturerName, brandName, page, pageSize)
                .getResults()
                .stream()
                .map(DrugMapper::fromFdaRecord)
                .toList();
    }

    @Operation(summary = "Get stored drug records from fda-api")
    @GetMapping("/stored")
    public List<DrugDto> getStoredFdaRecords() {
        return drugRecordService.findAll()
                .stream()
                .map(DrugMapper::toDto)
                .toList();
    }

    @Operation(summary = "Store particular drug record")
    @PostMapping("/stored")
    public ResponseEntity<DrugDto> store(@RequestBody DrugDto drug) {
         drugRecordService.create(DrugMapper.toDomain(drug));
         final var result = DrugMapper.toDto(drugRecordService.getByApplicationNumber(drug.getApplicationNumber()));
         return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete stored drug record by application number")
    @DeleteMapping("/stored/{applicationNumber}")
    public ResponseEntity<Void> delete(@PathVariable String applicationNumber) {
         drugRecordService.deleteByApplicationNumber(applicationNumber);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
