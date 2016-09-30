package com.ties456.service.movie;

import com.ties456.model.movies.Movie;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public interface MovieService {

    List<Movie> getAll();

    Movie getById(long id);

    boolean isMovieExist(long id);

    Movie saveMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovieById(long id);

    void deleteAllMovies();
    
}
