package com.hackerrank.weather.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


//this class was made because I wanted to see the limitations of @JsonAlias annotation.
@Entity
@Table(name = "partial_movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartialMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonAlias("title")
    private String movieTitle;

    @JsonAlias("runtime")
    private Integer runtime;

    @JsonAlias("status")
    private String status;
}
