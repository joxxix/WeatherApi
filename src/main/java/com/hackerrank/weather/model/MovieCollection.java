package com.hackerrank.weather.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies_collections")
@Data
@AllArgsConstructor
@NoArgsConstructor
//This class is not the full Movie Collection class that TMDB provides.
//This is just the data we need for the Movie class.
public class MovieCollection {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonAlias("name")
    private String name;

    @JsonAlias("poster_path")
    private String posterPath;

    @JsonAlias("backdrop_path")
    private String backdropPath;

}
