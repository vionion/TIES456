package com.ties456.resources;


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
    public List<Review> getAll() {
        return reviewService.getAll();
    }

    @GET
    @Path("/{id}")
    public Review getOne(@PathParam("id") long id) {
        Review result = reviewService.getById(id);
        if (result == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            return result;
        }
    }

    @PUT
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        if (reviewService.isReviewExist(review.getId())) {
            return new ResponseEntity<Review>(HttpStatus.CONFLICT);
        }
        Review result = reviewService.saveReview(review);
        return new ResponseEntity<Review>(result, HttpStatus.CREATED);
    }

    @POST
    @Path("/{id}")
    public ResponseEntity<Review> updateReview(@PathParam("id") long id, @RequestBody Review reviewToUpdate) {
        Review currentReview = reviewService.getById(id);
        if (currentReview == null) {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
        currentReview.setDetails(reviewToUpdate.getDetails());
        currentReview.setRating(reviewToUpdate.getRating());
        reviewService.updateReview(currentReview);
        return new ResponseEntity<Review>(currentReview, HttpStatus.OK);
    }

    @DELETE
    @Path("/{id}")
    public ResponseEntity<Review> deleteReview(@PathParam("id") long id) {
        if (id < 0) {
            throw new MyNotFoundException("Dude, try to be more positive!");
        }
        Review review = reviewService.getById(id);
        if (review == null) {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
        reviewService.deleteReviewById(id);
        return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
    }

    @DELETE
    public ResponseEntity<Review> deleteAllReviews() {
        reviewService.deleteAllReviews();
        return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
    }

}