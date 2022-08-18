package com.hackerrank.weather.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "weather")
@Data
public class Weather {


//    @SequenceGenerator(
//            name = "weather_sequence",
//            sequenceName = "weather_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "weather_sequence"
//    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate date;
    @NotNull
    private Float lat;
    @NotNull
    private Float lon;
    @NotNull
    private String city;
    @NotNull
    private String state;

    //@OneToMany(targetEntity=Double.class, mappedBy="weather", fetch=FetchType.EAGER)
    @NotNull
    @ElementCollection
    private List<Double> temperatures;

    public Weather(Integer id, LocalDate date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
        this.id = id;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.city = city;
        this.state = state;
        this.temperatures = temperatures;
    }

    public Weather(LocalDate date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.city = city;
        this.state = state;
        this.temperatures = temperatures;
    }

    public Weather() {
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", date=" + date +
                ", lat=" + lat +
                ", lon=" + lon +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", temperatures=" + temperatures +
                '}';
    }
}
