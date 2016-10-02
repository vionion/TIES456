package com.ties456.service.review;

import com.ties456.model.review.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chinhnk on 10/2/2016.
 */
@Service("reviewService")
//@Transactional
public class ReviewServiceImpl implements ReviewService {

    private static final List<Review> reviews = Arrays.asList(
            new Review(1, 1, 5, "Awesome"),
            new Review(2, 1, 3, "So so")
    );

    @Override
    public List<Review> getAll() {
        return reviews;
    }

    @Override
    public List<Review> getByMovieId(int movieId) {
        List<Review> result = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getMovieId() == movieId) {
                result.add(review);
            }
        }
        return result;
    }

    @Override
    public Review getByMovieIdAndId(int movieId, int id) {
        for (Review review : reviews) {
            if ((review.getMovieId() == movieId) && (review.getId() == id)) {
                return review;
            }
        }
        return null;
    }

    @Override
    public boolean isReviewExist(int movieId, int id) {
        return getByMovieIdAndId(movieId, id) != null;
    }

    @Override
    public Review saveReview(Review review) {
        reviews.add(review);
        return review;
    }

    @Override
    public void updateReview(Review review) {
        reviews.set(reviews.indexOf(getByMovieIdAndId(review.getMovieId(), review.getId())), review);
    }

    @Override
    public void deleteReviewById(int movieId, int id) {
        Iterator<Review> iter = reviews.iterator();
        while (iter.hasNext()) {
            if ((iter.next().getMovieId() == movieId) && (iter.next().getId() == id)) {
                iter.remove();
            }
        }
    }

    @Override
    public void deleteAllReviews() {
        reviews.clear();
    }
}