package com.hackerrank.weather.resource;

import com.hackerrank.weather.model.Movie;
import com.hackerrank.weather.model.MovieDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie of(MovieDTOResponse movieDTOResponse);
}
