package com.ties456.model.director;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ties456.model.Link;
import com.ties456.model.award.Award;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
 */
public class Director {

    private int id;
    private String name;
    private int birthYear;

    @JsonIgnore
    private List<Integer> movieIdList = new ArrayList<>();
    @JsonIgnore
    private List<Award> awards = new ArrayList<>();

    private List<Link> links = new ArrayList<>();

    public Director(int id, String name, int birthYear, List<Integer> movieIdList, List<Award> awards) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.movieIdList = movieIdList;
        this.awards = awards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<Integer> getMovieIdList() {
        return movieIdList;
    }

    public void setMovieIdList(List<Integer> movieIdList) {
        this.movieIdList = movieIdList;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
