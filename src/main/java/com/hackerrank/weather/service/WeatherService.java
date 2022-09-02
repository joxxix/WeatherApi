package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import com.hackerrank.weather.resource.WeatherCityAndTempResponse;
import com.hackerrank.weather.resource.WeatherMapper;
import com.hackerrank.weather.resource.WeatherRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository, WeatherMapper weatherMapper) {
        this.weatherRepository = weatherRepository;
        this.weatherMapper = weatherMapper;
    }

    public List<Weather> getWeather() {
        log.info("Searched for all of the weathers.");
        return weatherRepository.findAll();
    }

    //needs to be reworked with:
    //@RequestParam(date = "date", default = "today's date") Date date
    //input in the uls is = to "/weather?date=2019-02-02"
    public List<Weather> getWeather(LocalDate date) {
        log.info("Searched for a weather with date: {}", date);
        List<Weather> weathers = weatherRepository.findAll();

        List<Weather> finalWeathers = new ArrayList<>();

        weathers.forEach(w -> {
            if (date.equals(w.getDate())) {
                finalWeathers.add(w);
            }
        });


        return finalWeathers;
    }

    public Weather getWeather(Integer id, HttpServletRequest request, HttpServletResponse response) {
        log.info("Searched for a weather by id: {}", id);

        Optional<Weather> weather = weatherRepository.findById(id);

        if (weather.isPresent()) {
            return weather.get();
        } else {
            log.error("Failed to access {} With Status: {}", request.getRequestURL(), response.getStatus());
            throw new NoSuchElementException();
        }
    }

    public List<Weather> getWeatherByCityName(String cityName) {
        List<Weather> weather = weatherRepository.findAll();

        List<Weather> finalWeathers = new ArrayList<>();

        weather.forEach(
                w -> {
                    if (cityName.equalsIgnoreCase(w.getCity())) {
                        finalWeathers.add(w);
                    }
                }
        );

        log.info("Searched for a weather by city name: {}", cityName);
        return finalWeathers;
    }

    public void addWeather(WeatherRequest weather) {
        Weather w = weatherMapper.of(weather);

        weatherRepository.save(w);
        log.info("Created a new Weather with: {}", w.toString());
    }

    //Returns an ArrayList<Double> with all of the temperatures for the one city.
    public ArrayList<Double> getWeatherTemperatureByCityName(String cityName) {
        log.info("Getting all temperatures for: {}", cityName);
        final StringBuilder stringBuilder = new StringBuilder();

        final List<Weather> weather = weatherRepository.findAll();

        final ArrayList<Double> temperatures = new ArrayList<>();

        weather.forEach(
                w -> {
                    if (cityName.equalsIgnoreCase(w.getCity())) {
                        temperatures.addAll(w.getTemperatures());
                    }
                }
        );
        if (temperatures.isEmpty()){
            log.error("City does not exist {}",cityName);
            throw new NoSuchElementException();
        }

        temperatures.forEach(
                t -> stringBuilder.append(t).append(" ")
        );
        log.info("All temperatures for [{}] are: {}", cityName, stringBuilder);

        return temperatures;
    }

    public void deleteWeatherById(Integer id) {
        weatherRepository.deleteById(id);
    }

    public void deleteFirstInstanceOfWeatherByCity(String cityName) {
//      Deletes all the instance of a weather with a given city name. e.g. "Nashville".

//        List<Weather> weathers = weatherRepository.findAll();
//        weathers.forEach(w->{
//            if (cityName.equalsIgnoreCase(w.getCity())){
//               weatherRepository.delete(w);
//            }
//        });

        //Deletes the first instance of a weather with a given name. e.g. "Nashville"
        weatherRepository.deleteByCity(cityName);
    }

    //Getting a weather where the city name is given and/or the state name is given and a visible in the DB.
    public List<Weather> getWeatherS(String cityName, String stateName) {
        log.info("Getting all Weathers where the city name is: {} and the state name is {}",cityName,stateName);
        log.info(weatherRepository.findByCityOrState(cityName,stateName).toString());
        List<Weather> weathers = weatherRepository.findByCityOrState(cityName,stateName);
        if (weathers.isEmpty()){
            log.error("City \"{}\" and/or state \"{}\" does not exist!",cityName,stateName);
            throw new NoSuchElementException();
        }
        return weatherRepository.findByCityOrState(cityName,stateName);
    }

    //Gets a city name and gives back a weather model but only the city name and a list of all the temperatures.
    public List<WeatherCityAndTempResponse> getCityNameAndTemp(String cityName) {
        log.info("Getting only the temperatures for: {}",cityName);
        List<Weather> weather = weatherRepository.findAllByCity(cityName);
        if (weather.isEmpty()){
            log.error("Can't find \"{}\" as a city",cityName);
            throw new NoSuchElementException();
        }
        return weatherMapper.of(weather);
    }
}
