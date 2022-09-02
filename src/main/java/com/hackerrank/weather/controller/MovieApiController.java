package com.hackerrank.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hackerrank.weather.model.Movie;
import com.hackerrank.weather.model.PartialMovie;
import com.hackerrank.weather.repository.MovieRepository;
import com.hackerrank.weather.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieApiController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieApiController(MovieService movieService, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    //This method uses get method to return (api key)
    @GetMapping("/apiKey")
    public String helloMovies() {
        return movieService.apiKey();
    }

    //                             |tmdb domain/category/movieId/apiKey
    //Example URL: https://api.themoviedb.org/3/movie/550?api_key=0593ba3faa4885b391b88919d9cda544    Fight Club
    //Example URL: https://api.themoviedb.org/3/movie/1865?api_key=0593ba3faa4885b391b88919d9cda544  Pirates of the Caribbean
    @GetMapping("/{movieId}")
    public String movieId(@PathVariable("movieId") int movieID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + movieService.apiKey();

        Movie movie = restTemplate.getForObject(url, Movie.class);
        System.out.println(movie);

        PartialMovie partialMovie = restTemplate.getForObject(url, PartialMovie.class);
        System.out.println(partialMovie.getId());
        System.out.println(partialMovie.getMovieTitle());
        System.out.println(partialMovie.getRuntime());
        System.out.println(partialMovie.getStatus());
        System.out.println(partialMovie);

        movieRepository.save(movie);


        return "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + movieService.apiKey();
    }


    //This method returns the movie by the movie ID that is in our h2 database
    @GetMapping("/movie/{movieId}")
    public Optional<Movie> findMovieById(@PathVariable("movieId") Integer movieId){
        return movieRepository.findById(movieId);
    }
}
