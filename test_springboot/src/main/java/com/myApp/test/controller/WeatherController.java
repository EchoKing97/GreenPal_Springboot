package com.myApp.test.controller;

import com.myApp.test.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/location")
    public String getLocation() {
        return weatherService.getIpLocation();
    }

    @GetMapping("/weather")
    public String getWeather(String city) {
        return weatherService.getWeatherByAdcode(city);
    }
}
