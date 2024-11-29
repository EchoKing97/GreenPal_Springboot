package com.myApp.test.controller;

import com.myApp.test.model.Plant;
import com.myApp.test.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/plants")
public class PlantController {
    @Autowired
    private PlantService plantService;

    @PostMapping("/add")
    public ResponseEntity<?> addPlant(@RequestParam("plant_name") String plantName,
                                      @RequestParam("category") String category,
                                      @RequestParam("preferences") String preferences,
                                      @RequestParam("userId") int userId,
                                      @RequestParam("image") MultipartFile imageFile) {
        try {
            Plant savedPlant = plantService.addPlantWithUser(userId, plantName, category, preferences, imageFile);
            if (savedPlant != null) {
                return ResponseEntity.ok(savedPlant);
            } else {
                return ResponseEntity.badRequest().body("Error adding plant");
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error processing the image");
        }
    }

    @GetMapping("/user/plants")
    public ResponseEntity<?> getUserPlants(@RequestParam("userId") int userId) {
        try {
            List<Plant> userPlants = plantService.getUserPlants(userId);
            if (userPlants != null && !userPlants.isEmpty()) {
                // 转换列表中的每个Plant对象的图片为Base64字符串
                List<Plant> plantsWithBase64Images = userPlants.stream()
                        .map(plant -> {
                            Plant newPlant = new Plant();
                            newPlant.setPlant_name(plant.getPlant_name());
                            newPlant.setCategory(plant.getCategory());
                            newPlant.setPreferences(plant.getPreferences());

                            byte[] decodedImage = Base64.getDecoder().decode(plant.getPlantImageBase64());
                            newPlant.setPlant_image(decodedImage);

                            return newPlant;
                        })
                        .collect(Collectors.toList());
                return ResponseEntity.ok(plantsWithBase64Images);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving user plants");
        }
    }
}