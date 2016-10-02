package com.ties456.resources;


import com.ties456.model.award.Award;
import com.ties456.model.director.Director;
import com.ties456.model.movie.Movie;
import com.ties456.model.review.Review;
import com.ties456.service.AwardService;
import com.ties456.service.director.DirectorService;
import com.ties456.service.director.DirectorServiceImpl;
import com.ties456.service.movie.MovieService;
import com.ties456.service.movie.MovieServiceImpl;
import com.ties456.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class SearchResource {

    private MovieService movieService = new MovieServiceImpl();
    private DirectorService directorService = new DirectorServiceImpl();
    @Autowired
    private AwardService awardService;
    @Autowired
    private ReviewService reviewService;

    @GET
    @Path("/search")
    public ResponseEntity<List<Object>> search(@QueryParam("q") String searchQuery) {
        List<Object> resultList = new ArrayList<>();

        List<Director> directorList = directorService.getAll();
        for (Director director : directorList) {
            if (director.getName().indexOf(searchQuery) >= 0) {
                resultList.add(director);
            }
        }
        List<Movie> movieList = movieService.getAll();
        for (Movie movie : movieList) {
            if (movie.getName().indexOf(searchQuery) >= 0) {
                resultList.add(movie);
            }
        }
        List<Review> reviewList = reviewService.getAll();
        for (Review review : reviewList) {
            if (review.getTitle().indexOf(searchQuery) >= 0) {
                resultList.add(review);
            }
        }
        List<Award> awardList = awardService.getAll();
        for (Award award : awardList) {
            if (award.getName().indexOf(searchQuery) >= 0) {
                resultList.add(award);
            }
        }

        return new ResponseEntity<List<Object>>(resultList, HttpStatus.FOUND);
    }
    /*public ResponseEntity<List<Object>> search(@QueryParam("mode") Integer mode,
                                               @QueryParam("name") String name,
                                               @QueryParam("birthYear") Integer birthYear,
                                               @QueryParam("releaseYear") Integer releaseYear) {

        if ((mode != null) || (name == null) || (name.isEmpty())) {
            if (mode == Constants.SEARCH_MODE_MOVIE) {
                if (birthYear != null) {
                    return new ResponseEntity<List<Object>>(HttpStatus.BAD_REQUEST);
                }

//          Just temporary before merge
                List<Movie> movieList = new LinkedList<Movie>();
//                List<Movie> movieList = movieService.get(name, releaseYear);
                List<Object> resultList = new ArrayList();

                for (Movie movie : movieList) {
                    resultList.add((Object) movie);
                }

                return new ResponseEntity<List<Object>>(resultList, HttpStatus.BAD_REQUEST);
            } else if (mode == Constants.SEARCH_MODE_DIRECTOR) {
                if (releaseYear != null) {
                    return new ResponseEntity<List<Object>>(HttpStatus.BAD_REQUEST);
                }

                List<Director> directorList = directorService.get(name, birthYear);
                List<Object> resultList = new ArrayList();

                for (Director director : directorList) {
                    resultList.add((Object) director);
                }

                return new ResponseEntity<List<Object>>(resultList, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<List<Object>>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<List<Object>>(HttpStatus.BAD_REQUEST);
        }
    }*/

//    public Response addPublication(Director publication, @Context UriInfo uriInfo) {
//        Director director = directorService.add(publication);
//        String uri = uriInfo.getBaseUriBuilder()
//                .path(SearchResource.class)
//                .path(Long.toString(publication.getId()))
//                .build()
//                .toString();
//        director.addLink(uri, "self");
//        // do similarly for ”profile” link…
//        uri = uriInfo.getBaseUriBuilder()
//                .path(SearchResource.class)
//                .path(DirectorResource.class, "getAwardResource")
//                .path(AwardResource.class)
//                .resolveTemplate("publicationId", publication.getId())
//                .build()
//                .toString();
//        director.addLink(uri, "comments");
//        String newId = String.valueOf(director.getId());
//
//        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
//        return Response.created(uri)
//                .entity(director)
//                .build();
//    }
}