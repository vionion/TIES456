package com.ties456.resources;

import com.ties456.common.Constants;
import com.ties456.model.review.Review;
import com.ties456.service.review.ReviewService;
import com.ties456.service.review.ReviewServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {
    private ReviewService reviewService = new ReviewServiceImpl();

    private long movieId;

    public ReviewResource(long movieId) {
        this.movieId = movieId;
    }

    @GET
    public List<Review> getReviews() {
        return reviewService.getByMovieId(this.movieId);
    }

    @GET
    @Path("/{" + Constants.REVIEW_ID + "}")
    public Review getReview(@PathParam(Constants.REVIEW_ID) int reviewId) {
        return reviewService.getByMovieIdAndId(this.movieId, reviewId);
    }
}
