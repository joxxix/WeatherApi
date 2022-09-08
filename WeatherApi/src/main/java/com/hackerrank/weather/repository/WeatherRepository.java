package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    Optional<Weather> findByCity(String cityName);
    void deleteByCity(String cityName);
    List<Weather> findByCityOrState (String cityName,String state);

    List<Weather> findAllByCity(String cityName);
}
