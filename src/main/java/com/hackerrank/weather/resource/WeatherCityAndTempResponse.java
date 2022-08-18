package com.hackerrank.weather.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherCityAndTempResponse {
    @NotNull
    private String city;
    @NotNull
    private List<Double> temperatures;
}
