package com.myApp.test.controller;

import com.myApp.test.model.PlantCareTip;
import com.myApp.test.service.PlantCareTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/plantcaretips")
public class PlantCareTipController {
    @Autowired
    private PlantCareTipService plantCareTipService;

    @GetMapping("/random5")
    public List<PlantCareTip> getRandom5Tips() {
        return plantCareTipService.getRandom5Tips();
    }
}