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
    public Review getById(long id) {
        for (Review review : reviews) {
            if (review.getId() == id) {
                return review;
            }
        }
        return null;
    }

    @Override
    public List<Review> getByMovieId(long movieId) {
        List<Review> result = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getMovieId() == movieId) {
                result.add(review);
            }
        }
        return result;
    }

    @Override
    public Review getByMovieIdAndId(long movieId, long id) {
        for (Review review : reviews) {
            if ((review.getMovieId() == movieId) && (review.getId() == id)) {
                return review;
            }
        }
        return null;
    }

    @Override
    public boolean isReviewExist(long movieId, long id) {
        return getByMovieIdAndId(movieId, id) != null;
    }

    @Override
    public boolean isReviewExist(long id) {
        return getById(id) != null;
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
    public void deleteReviewById(long movieId, long id) {
        Iterator<Review> iter = reviews.iterator();
        while (iter.hasNext()) {
            if ((iter.next().getMovieId() == movieId) && (iter.next().getId() == id)) {
                iter.remove();
            }
        }
    }

    @Override
    public void deleteReviewById(long id) {
        Iterator<Review> iter = reviews.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == id) {
                iter.remove();
            }
        }
    }

    @Override
    public void deleteAllReviews() {
        reviews.clear();
    }
}