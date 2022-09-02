package com.hackerrank.weather.model;

import java.util.List;

public class GenresWrapperClass {
    List<Genre> genres;

    public GenresWrapperClass(List<Genre> genres) {
        this.genres = genres;
    }

    public GenresWrapperClass() {
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "GenresWrapperClass{" +
                "genres=" + genres +
                '}';
    }
}
