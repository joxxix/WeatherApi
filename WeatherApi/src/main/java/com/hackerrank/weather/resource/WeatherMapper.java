package com.hackerrank.weather.resource;

import com.hackerrank.weather.model.Weather;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    Weather of(WeatherRequest weatherRequest);

    WeatherCityAndTempResponse of(Weather weather);
    List<WeatherCityAndTempResponse> of(List<Weather> weathers);
}
