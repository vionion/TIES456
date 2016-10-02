package com.ties456.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ties456.model.Link;
import com.ties456.model.review.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
 */
public class Movie {

    private int id;
    private String name;
    private int releaseYear;

    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    private List<Link> links = new ArrayList<>();

    public Movie(int id, String name, int releaseYear, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.reviews = reviews;
    }

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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
