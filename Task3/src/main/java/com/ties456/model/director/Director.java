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

    private long id;
    private String name;
    private int birthYear;

    @JsonIgnore
    private List<Integer> movieIdList = new ArrayList<>();
    @JsonIgnore
    private List<Award> awards = new ArrayList<>();

    private List<Link> links = new ArrayList<>();

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

    public void addLink(String url, String rel){
        Link link = new Link();
        link.setHref(url);
        link.setRel(rel);
        links.add(link);
    }
}
