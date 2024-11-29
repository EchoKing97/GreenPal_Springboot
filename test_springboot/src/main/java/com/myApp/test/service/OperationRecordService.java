package com.myApp.test.service;

import com.myApp.test.model.OperationRecord;
import com.myApp.test.model.Plant;
import com.myApp.test.repository.OperationRecordRepository;
import com.myApp.test.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OperationRecordService {
    @Autowired
    private OperationRecordRepository operationRecordRepository;
    @Autowired
    private PlantRepository plantRepository;

    @Transactional
    public OperationRecord addOperationRecord(int userId, String plantName, String operation) {
        Plant plant = plantRepository.findByPlantName(plantName);
        if (plant == null) {
            return null;
        }
        OperationRecord record = new OperationRecord();
        record.setUserId(userId);
        record.setPlantId(plant.getPlant_id());
        record.setPlantName(plantName);
        record.setOperation(operation);
        record.setOperationTime(new Date());
        return operationRecordRepository.save(record);
    }

    public List<OperationRecord> getRecentOperationRecords(String plantName) {
        Plant plant = plantRepository.findByPlantName(plantName);
        if (plant == null) {
            return new ArrayList<>();
        }
        Date oneWeekAgo = new Date(System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000);
        return operationRecordRepository.findByPlantIdAndOperationTimeGreaterThanEqualOrderByOperationTimeDesc(plant.getPlant_id(), oneWeekAgo);
    }
}