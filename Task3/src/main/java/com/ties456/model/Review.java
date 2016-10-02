package com.ties456.model;

/**
 * Created by chinhnk on 10/2/2016.
 */
public class Review {

    private int id;
    private int rating;
    private String details;

    public Review(int id, int rating, String details) {
        this.id = id;
        this.rating = rating;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
