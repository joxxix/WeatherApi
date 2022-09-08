package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
}
