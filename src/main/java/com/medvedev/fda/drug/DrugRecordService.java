package com.medvedev.fda.drug;

import com.medvedev.fda.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrugRecordService {
    private final DrugRecordRepository drugRecordRepository;

    public void create(DrugRecord drugRecord) {
        drugRecordRepository.insertRecord(drugRecord);
    }

    public List<DrugRecord> findAll() {
        return Streams.of(drugRecordRepository.findAll()).toList();
    }

    public DrugRecord getByApplicationNumber(String applicationNumber) {
        return drugRecordRepository.findById(applicationNumber)
                .orElseThrow(() -> new NotFoundException("Drug record with applicationNumber=%s not found".formatted(applicationNumber)));
    }

    public void deleteByApplicationNumber(String applicationNumber) {
        drugRecordRepository.deleteById(applicationNumber);
    }
}