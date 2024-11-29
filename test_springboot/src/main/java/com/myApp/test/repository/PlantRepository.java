package com.myApp.test.repository;

import com.myApp.test.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Integer> {
    public List<Plant> findByUserId(int user_id);

    @Query("SELECT p FROM Plant p WHERE p.plant_name = :plantName")
    Plant findByPlantName(@Param("plantName") String plantName);
}