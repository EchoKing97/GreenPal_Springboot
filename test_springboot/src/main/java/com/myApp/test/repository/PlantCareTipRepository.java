package com.myApp.test.repository;

import com.myApp.test.model.PlantCareTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlantCareTipRepository extends JpaRepository<PlantCareTip, Long> {
    @Query(value = "SELECT * FROM plant_care_tips ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<PlantCareTip> findRandom5();
}