package com.hackerrank.weather.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonAlias("adult")
    private boolean adult;

    @JsonAlias("backdrop_path")
    private String backdropPath;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movies_collections_id", referencedColumnName = "id")
    @JsonAlias("belongs_to_collection")
    private MovieCollection belongsToCollection;

    @JsonAlias("budget")
    private String budget;

    @ElementCollection
    @JsonAlias("genres")
    private List<Genre> genres;

    @JsonAlias("homepage")
    private String homepage;

    @JsonAlias("imdb_id")
    private String ImdbId;

    @JsonAlias("original_language")
    private String originalLanguage;

    @JsonAlias("original_title")
    private String originalTitle;

    @JsonAlias("overview")
    @Column(length = 1000)
    private String overview;

    @JsonAlias("popularity")
    private Integer popularity;

    @JsonAlias("poster_path")
    private String posterPath;
}
