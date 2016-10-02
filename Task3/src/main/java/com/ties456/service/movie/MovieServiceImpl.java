package com.ties456.service.movie;

import com.ties456.model.movie.Movie;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
@Service("movieService")
//@Transactional
public class MovieServiceImpl implements MovieService {

    private static final List<Movie> movies = new ArrayList<Movie>(Arrays.asList(new Movie(1, "Harry Potter"), new Movie(2, "Star Wars")));

    @Override
    public List<Movie> getAll() {
        return movies;
    }

    @Override
    public Movie getById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public boolean isMovieExist(int id) {
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
    public void deleteMovieById(int id) {
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