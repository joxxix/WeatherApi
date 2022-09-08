package com.hackerrank.weather.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


//this class was made because I wanted to see the limitations of @JsonAlias annotation.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTOResponse {

    private boolean adult;

    @JsonAlias("backdrop_path")
    private String backdropPath;

    @JsonAlias("belongs_to_collection")
    private MovieCollection belongsToCollection;

    private String budget;

    private List<Genre> genres;

    private String homepage;

    @JsonAlias("imdb_id")
    private String ImdbId;

    @JsonAlias("id")
    private Integer tmdbId;

    @JsonAlias("original_language")
    private String originalLanguage;

    @JsonAlias("original_title")
    private String originalTitle;

//    private String overview;

    private Integer popularity;

    @JsonAlias("poster_path")
    private String posterPath;
}
