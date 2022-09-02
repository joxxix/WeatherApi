package com.hackerrank.weather.config;

import com.hackerrank.weather.model.Genre;
import com.hackerrank.weather.model.GenresWrapperClass;
import com.hackerrank.weather.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class GenreConfig {
    //Make this automatic form the tmdb list of genres.
//    @Bean
//    CommandLineRunner commandLineRunner(GenreRepository genreRepository) {
//        return args -> {
//            Genre action = new Genre(28, "Action");
//            Genre adventure = new Genre(14, "Adventure");
//            Genre animation = new Genre(16, "Animation");
//            Genre comedy = new Genre(35, "Comedy");
//            Genre crime = new Genre(80, "Crime");
//            Genre documentary = new Genre(99, "Documentary");
//            Genre drama = new Genre(18, "Drama");
//            Genre family = new Genre(10751, "Family");
//            Genre fantasy = new Genre(12, "Fantasy");
//            Genre history = new Genre(36, "History");
//            Genre horror = new Genre(27, "Horror");
//            Genre music = new Genre(10402, "Music");
//            Genre mystery = new Genre(9648, "Mystery");
//            Genre romance = new Genre(10749, "Romance");
//            Genre scienceFiction = new Genre(878, "Science Fiction");
//            Genre tvMovie = new Genre(10770, "TV Movie");
//            Genre thriller = new Genre(53, "Thriller");
//            Genre war = new Genre(10752, "War");
//            Genre western = new Genre(37, "Western");
//            genreRepository.saveAll(Arrays.asList(
//                    action
//                    , adventure
//                    , animation
//                    , comedy
//                    , crime
//                    , documentary
//                    , drama
//                    , family
//                    , fantasy
//                    , history
//                    , horror
//                    , music
//                    , mystery
//                    , romance
//                    , scienceFiction
//                    , tvMovie
//                    , thriller
//                    , war
//                    , western
//            ));
//        };
//    }
    @Value("${api.key}")
    String apiKey;

    @Bean
    CommandLineRunner commandLineRunner(GenreRepository genreRepository){
        return args -> {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + apiKey;

            GenresWrapperClass genres = restTemplate.getForObject(url, GenresWrapperClass.class);

            List<Genre> genreList = genres.getGenres();
            genreRepository.saveAll(genreList);
        };
    }
}
