package com.ties456.service.movie;

import com.ties456.model.movie.Movie;

import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
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
