package com.weather.api.exception;

/**
 * Custom exception for weather service errors.
 */
public class WeatherServiceException extends RuntimeException {
    
    public WeatherServiceException(String message) {
        super(message);
    }
    
    public WeatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
