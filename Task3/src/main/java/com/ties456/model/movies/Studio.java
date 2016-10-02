package com.ties456.model.movies;

import com.ties456.model.Movie;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public class Studio {

    private long id;
    private String name;
    private List<Movie> movies;

    public Studio() {}

    public Studio(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Studio(long id, String name, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
