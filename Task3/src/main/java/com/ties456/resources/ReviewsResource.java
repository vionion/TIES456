package com.ties456.resources;


import com.ties456.common.Constants;
import com.ties456.error.exception.MyNotFoundException;
import com.ties456.model.review.Review;
import com.ties456.service.review.ReviewService;
import com.ties456.service.review.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class ReviewsResource {

    private ReviewService reviewService = new ReviewServiceImpl();

    @GET
    public List<Review> getReviews(@PathParam(Constants.MOVIE_ID) long movieId) {
        return reviewService.getByMovieId(movieId);
    }

    @GET
    @Path("/{" + Constants.REVIEW_ID + "}")
    public Review getReview(@PathParam(Constants.MOVIE_ID) long movieId,
                            @PathParam(Constants.REVIEW_ID) long reviewId) {
        return reviewService.getByMovieIdAndId(movieId, reviewId);
    }
}