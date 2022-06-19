package com.timbar.uam.wmi.oop.boxoffice.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieCatalog {

    private final Map<Integer, Movie> moviesId = new HashMap<>();
    private final MovieAdapter movieAdapter;

    public MovieCatalog(MovieAdapter movieAdapter) {
        this.movieAdapter = movieAdapter;
    }

    public Movie getMovie(int id) {
        Movie movie = moviesId.get(id);
        if (movie != null) {
            return movie;
        }
        movie = movieAdapter.getMovieById(id);
        if (movie != null) {
            moviesId.put(id, movie);
            return movie;
        }

        throw new MovieInfoUnavailableException();
    }

}
