package com.ties456.service.movie;

import com.ties456.model.movie.Movie;

import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
 */
public interface MovieService {

    List<Movie> getAll();

    Movie getById(int id);

    boolean isMovieExist(int id);

    Movie saveMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovieById(int id);

    void deleteAllMovies();

}
