package com.hackerrank.weather.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherRequest {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull
    private Float lat;
    @NotNull
    private Float lon;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private List<Double> temperatures;
}
