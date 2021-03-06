package com.ties456.resources;


import com.ties456.common.Constants;
import com.ties456.error.exception.MyNotFoundException;
import com.ties456.model.movie.Movie;
import com.ties456.service.movie.MovieService;
import com.ties456.service.movie.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class MoviesResource {

    private MovieService movieService = new MovieServiceImpl();

    @GET
    public List<Movie> getAll(@Context UriInfo uriInfo) {
        List<Movie> resultList = movieService.getAll();
        for (Movie movie : resultList) {
            movie.addHATEOAS(uriInfo);
        }
        return resultList;
    }

    @GET
    @Path("/{id}")
    public Movie getOne(@PathParam("id") long id, @Context UriInfo uriInfo) {
        Movie result = movieService.getById(id);
        if (result == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            result.addHATEOAS(uriInfo);
            return result;
        }
    }

    @POST
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie,
                                             @Context UriInfo uriInfo) {
        if (movieService.isMovieExist(movie.getId())) {
            return new ResponseEntity<Movie>(HttpStatus.CONFLICT);
        }
        Movie result = movieService.saveMovie(movie);
        result.addHATEOAS(uriInfo);
        return new ResponseEntity<Movie>(result, HttpStatus.CREATED);
    }

    @PUT
    @Path("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathParam("id") long id,
                                             @RequestBody Movie movieToUpdate,
                                             @Context UriInfo uriInfo) {
        Movie currentMovie = movieService.getById(id);
        if (currentMovie == null) {
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
        currentMovie.setName(movieToUpdate.getName());
        currentMovie.setLinks(movieToUpdate.getLinks());
        currentMovie.setReleaseYear(movieToUpdate.getReleaseYear());
        currentMovie.setReviews(movieToUpdate.getReviews());
        currentMovie.addHATEOAS(uriInfo);
        movieService.updateMovie(currentMovie);
        return new ResponseEntity<Movie>(currentMovie, HttpStatus.OK);
    }

    @DELETE
    @Path("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathParam("id") long id) {
        if (id < 0) {
            throw new MyNotFoundException("Dude, try to be more positive!");
        }
        Movie movie = movieService.getById(id);
        if (movie == null) {
            throw new MyNotFoundException("There is no movie with such strange id");
//            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
        movieService.deleteMovieById(id);
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }

    @DELETE
    public ResponseEntity<Movie> deleteAllMovies() {
        movieService.deleteAllMovies();
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }

    @GET
    @Path("/error")
    public ResponseEntity<Movie> getServerError() throws Throwable {
        throw new Throwable("Did you asked me for some errors? I have one");
    }

    @Path("/{" + Constants.MOVIE_ID + "}/reviews")
    public ReviewsResource getReviewsResource() {
        return new ReviewsResource();
    }
}