package com.ties456.model.movies;

import com.ties456.model.Person;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public class Movie {

    private Long id;
    private String name;
    private List<Person> actors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }
}
