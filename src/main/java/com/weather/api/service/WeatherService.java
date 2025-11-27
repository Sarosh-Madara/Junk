package com.weather.api.service;

import com.weather.api.exception.WeatherServiceException;
import com.weather.api.model.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Service class to fetch weather data from Open-Meteo API.
 * Open-Meteo is a free, open-source weather API that doesn't require an API key.
 */
@Service
public class WeatherService {

    private static final String OPEN_METEO_API_URL = 
        "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches current weather for the given coordinates.
     *
     * @param latitude  the latitude coordinate
     * @param longitude the longitude coordinate
     * @return WeatherResponse containing current weather data
     * @throws WeatherServiceException if weather data cannot be fetched
     */
    public WeatherResponse getWeather(double latitude, double longitude) {
        String url = String.format(OPEN_METEO_API_URL, latitude, longitude);
        
        Map<String, Object> response;
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> rawResponse = restTemplate.getForObject(url, Map.class);
            response = rawResponse;
        } catch (RestClientException e) {
            throw new WeatherServiceException(
                "Failed to fetch weather data from Open-Meteo API for coordinates: " + latitude + ", " + longitude, e);
        }
        
        if (response == null) {
            throw new WeatherServiceException(
                "Empty response from Open-Meteo API for coordinates: " + latitude + ", " + longitude);
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> currentWeather = (Map<String, Object>) response.get("current_weather");
        
        if (currentWeather == null) {
            throw new WeatherServiceException(
                "Missing current_weather data in API response for coordinates: " + latitude + ", " + longitude);
        }
        
        Number temperature = (Number) currentWeather.get("temperature");
        Number windSpeed = (Number) currentWeather.get("windspeed");
        Number weatherCode = (Number) currentWeather.get("weathercode");
        
        if (temperature == null || windSpeed == null || weatherCode == null) {
            throw new WeatherServiceException(
                "Missing required weather fields in API response for coordinates: " + latitude + ", " + longitude);
        }
        
        @SuppressWarnings("unchecked")
        Map<String, Object> currentWeatherUnits = (Map<String, Object>) response.get("current_weather_units");
        
        String tempUnit = currentWeatherUnits != null ? 
            (String) currentWeatherUnits.get("temperature") : "°C";
        String windUnit = currentWeatherUnits != null ? 
            (String) currentWeatherUnits.get("windspeed") : "km/h";

        return new WeatherResponse(
            latitude,
            longitude,
            temperature.doubleValue(),
            windSpeed.doubleValue(),
            tempUnit,
            windUnit,
            getWeatherDescription(weatherCode.intValue())
        );
    }

    /**
     * Converts WMO weather code to human-readable description.
     * Based on WMO Weather interpretation codes (WW).
     */
    private String getWeatherDescription(int weatherCode) {
        return switch (weatherCode) {
            case 0 -> "Clear sky";
            case 1, 2, 3 -> "Partly cloudy";
            case 45, 48 -> "Foggy";
            case 51, 53, 55 -> "Drizzle";
            case 56, 57 -> "Freezing drizzle";
            case 61, 63, 65 -> "Rain";
            case 66, 67 -> "Freezing rain";
            case 71, 73, 75 -> "Snowfall";
            case 77 -> "Snow grains";
            case 80, 81, 82 -> "Rain showers";
            case 85, 86 -> "Snow showers";
            case 95 -> "Thunderstorm";
            case 96, 99 -> "Thunderstorm with hail";
            default -> "Unknown";
        };
    }
}
