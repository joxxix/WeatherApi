package com.hackerrank.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hackerrank.weather.model.Movie;
import com.hackerrank.weather.model.MovieCollection;
import com.hackerrank.weather.model.MovieDTOResponse;
import com.hackerrank.weather.repository.MovieCollectionRepository;
import com.hackerrank.weather.repository.MovieRepository;
import com.hackerrank.weather.resource.MovieMapper;
import com.hackerrank.weather.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
public class MovieApiController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final MovieCollectionRepository movieCollectionRepository;

    @Autowired
    public MovieApiController(MovieService movieService, MovieRepository movieRepository, MovieMapper movieMapper, MovieCollectionRepository movieCollectionRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.movieCollectionRepository = movieCollectionRepository;
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

        MovieDTOResponse movie = restTemplate.getForObject(url, MovieDTOResponse.class);
        System.out.println(movie);


        //search tmdb's database for harry potter it returns a list of partial movies
//        https://api.themoviedb.org/3/search/movie?query=harry%20potter&api_key=0593ba3faa4885b391b88919d9cda544
        Movie movie1 = movieMapper.of(movie);
        MovieCollection movieCollection = movie1.getBelongsToCollection();

        movieCollectionRepository.save(movieCollection);
        movieRepository.save(movie1);


        return "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + movieService.apiKey();
    }


    //This method returns the movie by the movie ID that is in our h2 database
//    @GetMapping("/{movieId}")
//    public Optional<Movie> findMovieById(@PathVariable("movieId") Integer movieId){
//        return movieRepository.findBymoId(movieId);
//    }
}
