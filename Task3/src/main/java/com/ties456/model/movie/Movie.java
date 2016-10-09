package com.ties456.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ties456.common.Constants;
import com.ties456.model.Link;
import com.ties456.model.review.Review;
import com.ties456.resources.DirectorsResource;
import com.ties456.resources.MoviesResource;

import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
 */
public class Movie {

    private long id;
    private String name;
    private int releaseYear;
    private long directorId;

    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    private List<Link> links = new ArrayList<>();

    public Movie() {
    }

    public Movie(long id, String name, int releaseYear, long directorId) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.directorId = directorId;
    }

    public Movie(long id, String name) {
        this.id = id;
        this.name = name;
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

    public long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(long directorId) {
        this.directorId = directorId;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel) {
        Link link = new Link();
        link.setHref(url);
        link.setRel(rel);
        links.add(link);
    }

    public void addHATEOAS(UriInfo uriInfo) {
        if (this.links.isEmpty()) {
            this.addLink(uriInfo.getBaseUriBuilder()
                    .path(MoviesResource.class)
                    .path(Long.toString(this.getId()))
                    .build()
                    .toString(), "self");
            this.addLink(uriInfo.getBaseUriBuilder()
                    .path(DirectorsResource.class)
                    .path(Long.toString(this.getDirectorId()))
                    .build()
                    .toString(), "director");
            this.addLink(uriInfo.getBaseUriBuilder()
                    .path(MoviesResource.class)
                    .path(MoviesResource.class, "getReviewsResource")
                    .resolveTemplate(Constants.MOVIE_ID, this.getId())
                    .build()
                    .toString(), "reviews");
        }
    }
}
