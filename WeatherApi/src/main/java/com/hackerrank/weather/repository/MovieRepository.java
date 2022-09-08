package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
