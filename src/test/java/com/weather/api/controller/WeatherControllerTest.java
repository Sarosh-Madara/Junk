package com.weather.api.controller;

import com.weather.api.model.WeatherResponse;
import com.weather.api.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void getWeather_ReturnsWeatherResponse() throws Exception {
        WeatherResponse mockResponse = new WeatherResponse(
            52.52, 13.41, 15.5, 10.2, "°C", "km/h", "Clear sky"
        );
        
        when(weatherService.getWeather(anyDouble(), anyDouble())).thenReturn(mockResponse);

        mockMvc.perform(get("/api/weather")
                .param("latitude", "52.52")
                .param("longitude", "13.41"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.latitude").value(52.52))
                .andExpect(jsonPath("$.longitude").value(13.41))
                .andExpect(jsonPath("$.temperature").value(15.5))
                .andExpect(jsonPath("$.windSpeed").value(10.2))
                .andExpect(jsonPath("$.description").value("Clear sky"));
    }

    @Test
    void getWeather_MissingParameters_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/weather"))
                .andExpect(status().isBadRequest());
    }
}
