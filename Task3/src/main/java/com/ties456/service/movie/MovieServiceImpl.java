package com.ties456.service.movie;

import com.ties456.model.movie.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//import org.springframework.transaction.annotation.Transactional;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
@Service("movieService")
//@Transactional
public class MovieServiceImpl implements MovieService {

    private static final List<Movie> movies = new ArrayList<Movie>(Arrays.asList(
            new Movie(1, "The Lord of the Rings: The Return of the King", 2003, 1),
            new Movie(2, "Inception", 2010, 2)));

    @Override
    public List<Movie> getAll() {
        return movies;
    }

    @Override
    public Movie getById(long id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public boolean isMovieExist(long id) {
        return getById(id) != null;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        movies.add(movie);
        return movie;
    }

    @Override
    public void updateMovie(Movie movie) {
        movies.set(movies.indexOf(getById(movie.getId())), movie);
    }

    @Override
    public void deleteMovieById(long id) {
        Iterator<Movie> iter = movies.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == id) {
                iter.remove();
            }
        }
    }

    @Override
    public void deleteAllMovies() {
        movies.clear();
    }
}