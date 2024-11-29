package com.myApp.test.repository;

import com.myApp.test.model.OperationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface OperationRecordRepository extends JpaRepository<OperationRecord, Integer> {
    List<OperationRecord> findByPlantIdAndOperationTimeGreaterThanEqualOrderByOperationTimeDesc(int plantId, Date startDate);
}