package com.weather.api.controller;

import com.weather.api.model.WeatherResponse;
import com.weather.api.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for weather API endpoints.
 */
@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Get current weather for given coordinates.
     * 
     * Example: GET /api/weather?latitude=52.52&longitude=13.41
     * (Returns weather for Berlin, Germany)
     *
     * @param latitude  the latitude coordinate (required)
     * @param longitude the longitude coordinate (required)
     * @return WeatherResponse with current weather data
     */
    @GetMapping
    public ResponseEntity<WeatherResponse> getWeather(
            @RequestParam double latitude,
            @RequestParam double longitude) {
        
        WeatherResponse weather = weatherService.getWeather(latitude, longitude);
        return ResponseEntity.ok(weather);
    }
}
