package com.hackerrank.weather.config;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class WeatherConfig {

    @Bean
    CommandLineRunner commandLineRunner(WeatherRepository weatherRepository){
        return args -> {
            Weather weather = new Weather(
                    LocalDate.of(2019,02,02)
                    , 36.1189F
                    , -86.6892F
                    , "Nashville"
                    , "Tennessee"
                    , Arrays.asList(17.3, 16.8, 16.4, 16.0, 15.6, 15.3, 15.0, 14.9)
            );
            Weather weather4 = new Weather(
                    LocalDate.of(2019,02,02)
                    , 57.248F
                    , -123.13F
                    , "Nashville"
                    , "Tennessee"
                    , Arrays.asList(99.99, 34.23, 12.12)
            );
            Weather weather1 = new Weather(
                    LocalDate.now()
                    ,25.36F
                    ,-33.33F
                    ,"Austin"
                    ,"Texas"
                    ,Arrays.asList(22.23, 33.33, 44.44, 56.66, 11.1)
            );
            Weather weather2 = new Weather(
                    LocalDate.now()
                    ,66.66F
                    ,-11.11F
                    ,"London"
                    ,"England"
                    ,Arrays.asList(235.142, 124.412, 142.568, 82.12, 11.1)
            );
            Weather weather22 = new Weather(
                    LocalDate.now()
                    ,66.66F
                    ,-11.11F
                    ,"London"
                    ,"Texas"
                    ,Arrays.asList(631.12, 12.12, 333.333, 44.44, 7878.1)
            );
            weatherRepository.saveAll(
                    Arrays.asList(
                            weather,
                            weather1,
                            weather2,
                            weather4,
                            weather22
                    )
            );
        };
    }

}
