package com.hackerrank.weather.controller;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.resource.WeatherCityAndTempResponse;
import com.hackerrank.weather.resource.WeatherRequest;
import com.hackerrank.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;;

@RestController
@RequestMapping(path = "/weather")
public class WeatherApiRestController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherApiRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(path = "/today")
    public List<Weather> getWeather() {
        return weatherService.getWeather();
    }

    @GetMapping("/id/{id}")
    public Weather getWeatherById(@PathVariable("id") Integer id, HttpServletRequest request,HttpServletResponse response) {
        return weatherService.getWeather(id, request,response);
    }

    @GetMapping("/name/{name}")
    public List<Weather> getWeatherByCityName(@PathVariable("name") String cityName) {
        return weatherService.getWeatherByCityName(cityName);
    }

    @PostMapping
    public void addWeather(@Valid @RequestBody WeatherRequest weather) {
        weatherService.addWeather(weather);
    }

    @GetMapping
    public List<Weather> weather(@RequestParam("date") String date) {
        //return weatherService.getWeather(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return weatherService.getWeather(LocalDate.parse(date));
    }

    @GetMapping("/city/{cityName}")
    public ArrayList<Double> getWeatherTemperatureByCityName(@PathVariable(name = "cityName") String cityName){
        return weatherService.getWeatherTemperatureByCityName(cityName);
    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteWeatherById(@PathVariable(name = "id") Integer id){
        weatherService.deleteWeatherById(id);
    }

    @DeleteMapping("/delete/name/{cityName}")
    public void deleteFirstInstanceOfWeatherByCity(@PathVariable(name = "cityName")String cityName){
        weatherService.deleteFirstInstanceOfWeatherByCity(cityName);
    }

    @GetMapping("/city/{city}/{state}")
    public List<Weather> getWeathersByCityOrState(@PathVariable(name = "city") String cityName, @PathVariable(name = "state") String stateName){
        return weatherService.getWeatherS(cityName,stateName);
    }

    @GetMapping("/temp/{cityName}")
    public List<WeatherCityAndTempResponse> getTheThirdTempByCityName(@PathVariable(name = "cityName")String cityName){
       return weatherService.getCityNameAndTemp(cityName);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, NoSuchElementException.class})
    public ResponseEntity handleExceptions() {
        return ResponseEntity.notFound().build();
    }

//    @ExceptionHandler({MethodArgumentTypeMismatchException.class, NoSuchElementException.class})
//    public ResponseEntity<ErrorResponse> handleExceptionsWithWitablePage(Exception e) {
//        System.out.println("ERROR!");
//        return new ModelAndView("error");
//    }
}
