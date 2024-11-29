package com.myApp.test.service;

import com.myApp.test.model.Plant;
import com.myApp.test.model.User;
import com.myApp.test.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService {
    @Autowired
    private PlantRepository plantRepository;

    @Transactional
    public Plant addPlant(Plant plant, MultipartFile imageFile) throws IOException {
        plant.setCreatedDate(new Date());
        if (!imageFile.isEmpty()) {
            plant.setPlant_image(imageFile.getBytes());
        }
        return plantRepository.save(plant);
    }

    public Plant addPlantWithUser(int userId, String plantName, String category, String preferences, MultipartFile imageFile) throws IOException {
        User user = new User();
        user.setId(userId);
        Plant plant = new Plant(plantName, category, preferences, user, imageFile.getBytes());
        return addPlant(plant, imageFile);
    }

    public List<Plant> getUserPlants(int userId) {
        return plantRepository.findByUserId(userId);
    }

    public Plant getPlantId(int id) {
        Optional<Plant> plant = plantRepository.findById(id);
        return plant.orElse(null);
    }
}