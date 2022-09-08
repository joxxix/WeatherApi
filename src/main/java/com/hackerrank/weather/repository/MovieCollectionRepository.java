package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.MovieCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCollectionRepository extends JpaRepository<MovieCollection,Integer> {
}
