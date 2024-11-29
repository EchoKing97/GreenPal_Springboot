package com.myApp.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private static final String apiKey = "275a2bc88e8970fd6a06d3c34202d00e";
    @Autowired
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getIpLocation() {
        String url = "https://restapi.amap.com/v3/ip?key=" + apiKey + "&output=JSON";
        return restTemplate.getForObject(url, String.class);
    }

    public String getWeatherByAdcode(String city) {
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?key=" + apiKey + "&city=" + city + "&output=JSON";
        return restTemplate.getForObject(url, String.class);
    }
}