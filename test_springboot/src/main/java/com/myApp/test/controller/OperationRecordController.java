package com.myApp.test.controller;

import com.myApp.test.model.OperationRecord;
import com.myApp.test.service.OperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/operationRecords")
public class OperationRecordController {
    @Autowired
    private OperationRecordService operationRecordService;

    @PostMapping("/add")
    public ResponseEntity<?> addOperationRecord(@RequestBody OperationRecord record) {
        OperationRecord savedRecord = operationRecordService.addOperationRecord(record.getUserId(), record.getPlantName(), record.getOperation());
        if (savedRecord != null) {
            return ResponseEntity.ok("Operation record added successfully");
        } else {
            return ResponseEntity.badRequest().body("Error adding operation record");
        }
    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentOperationRecords(@RequestParam("plantName") String plantName) {
        List<OperationRecord> records = operationRecordService.getRecentOperationRecords(plantName);
        if (records != null && !records.isEmpty()) {
            return ResponseEntity.ok(records);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}