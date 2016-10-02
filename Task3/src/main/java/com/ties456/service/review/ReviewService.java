package com.ties456.service.review;

import com.ties456.model.movie.Movie;
import com.ties456.model.review.Review;

import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
 */
public interface ReviewService {

    List<Review> getAll();

    Review getById(long id);

    List<Review> getByMovieId(long movieId);

    Review getByMovieIdAndId(long movieId, long id);

    boolean isReviewExist(long movieId, long id);

    boolean isReviewExist(long id);

    Review saveReview(Review movie);

    void updateReview(Review movie);

    void deleteReviewById(long movieId, long id);

    void deleteReviewById(long id);

    void deleteAllReviews();

}
