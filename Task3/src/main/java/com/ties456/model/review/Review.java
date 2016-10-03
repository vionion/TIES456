package com.ties456.model.review;

/**
 * Created by chinhnk on 10/2/2016.
 */
public class Review {

    private long id;
    private long movieId;
    private int rating;
    private String title;
    private String details;

    public Review(long id, long movieId, int rating, String title) {
        this.id = id;
        this.movieId = movieId;
        this.rating = rating;
        this.title = title;
    }

    public Review(long id, long movieId, int rating, String title, String details) {
        this.id = id;
        this.movieId = movieId;
        this.rating = rating;
        this.title = title;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
