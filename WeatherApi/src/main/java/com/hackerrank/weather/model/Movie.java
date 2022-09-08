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

    private boolean adult;

    @JsonAlias("backdrop_path")
    private String backdropPath;

    @ManyToOne
    @JoinColumn(name = "movies_collections_id", referencedColumnName = "id")
    private MovieCollection belongsToCollection;

    private String budget;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Genre> genres;

    private String homepage;

    private String ImdbId;

    private Integer tmdbId;

    private String originalLanguage;

    private String originalTitle;

//    @Column(length = 1000)
//    private String overview;

    private Integer popularity;

    private String posterPath;
}