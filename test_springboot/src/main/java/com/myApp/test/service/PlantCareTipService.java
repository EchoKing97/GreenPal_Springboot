package com.myApp.test.service;

import com.myApp.test.model.PlantCareTip;
import com.myApp.test.repository.PlantCareTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlantCareTipService {

    @Autowired
    private PlantCareTipRepository plantCareTipRepository;

    public List<PlantCareTip> getRandom5Tips() {
        return plantCareTipRepository.findRandom5();
    }
}