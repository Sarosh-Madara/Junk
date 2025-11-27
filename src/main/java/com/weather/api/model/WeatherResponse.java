package com.weather.api.model;

/**
 * Model class representing weather information.
 */
public class WeatherResponse {
    
    private double latitude;
    private double longitude;
    private double temperature;
    private double windSpeed;
    private String temperatureUnit;
    private String windSpeedUnit;
    private String description;

    public WeatherResponse() {
    }

    public WeatherResponse(double latitude, double longitude, double temperature, 
                          double windSpeed, String temperatureUnit, String windSpeedUnit, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.temperatureUnit = temperatureUnit;
        this.windSpeedUnit = windSpeedUnit;
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String getWindSpeedUnit() {
        return windSpeedUnit;
    }

    public void setWindSpeedUnit(String windSpeedUnit) {
        this.windSpeedUnit = windSpeedUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
