package com.hackerrank.weather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    //@Value injects the API Key to this string variable from the application.property file.
    //api.key = 0593ba3faa4885b391b88919d9cda544
    @Value("${api.key}")
    private String apiKey;

    //This method returns a string value of (api key)
    public String apiKey() {
        return apiKey;
    }
}
