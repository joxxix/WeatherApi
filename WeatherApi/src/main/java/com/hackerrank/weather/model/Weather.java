package com.hackerrank.weather.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
    //The @Temporal annotation must be specified for persistent fields or properties of type java.util.Date and java.util.Calendar.
    //It may only be specified for fields or properties of these types.
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;
    @NotNull
    @Column(columnDefinition = "Decimal(10,2)")
    private Float lat;
    @NotNull
    @Column(columnDefinition = "Decimal(10,2)")
    private Float lon;
    @NotNull
    private String city;
    @NotNull
    private String state;

    //@OneToMany(targetEntity=Double.class, mappedBy="weather", fetch=FetchType.EAGER)
    @NotNull
    @ElementCollection
    private List<Double> temperatures;

    public Weather(Integer id, Date date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
        this.id = id;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.city = city;
        this.state = state;
        this.temperatures = temperatures;
    }

    public Weather(Date date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
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
