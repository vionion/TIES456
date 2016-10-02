package com.ties456.service.review;

import com.ties456.model.review.Review;

import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
 */
public interface ReviewService {

    List<Review> getAll();

    List<Review> getByMovieId(int movieId);

    Review getByMovieIdAndId(int movieId, int id);

    boolean isReviewExist(int movieId, int id);

    Review saveReview(Review movie);

    void updateReview(Review movie);

    void deleteReviewById(int movieId, int id);

    void deleteAllReviews();

}
