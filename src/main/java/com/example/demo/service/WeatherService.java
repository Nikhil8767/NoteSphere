package com.example.demo.service;
import com.example.demo.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static final String API_KEY = "478461f9212ffa143cdd733a949eab48";
   private static final String API_URL =
    "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apikey}&units=metric";

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(String city) {
        ResponseEntity<WeatherResponse> response =
                restTemplate.exchange(API_URL, HttpMethod.GET, null, WeatherResponse.class, city, API_KEY);

        return response.getBody();
    }
}

