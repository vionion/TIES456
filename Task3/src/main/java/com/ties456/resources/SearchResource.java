package com.ties456.resources;


import com.ties456.model.award.Award;
import com.ties456.model.director.Director;
import com.ties456.model.movie.Movie;
import com.ties456.model.review.Review;
import com.ties456.service.award.AwardService;
import com.ties456.service.award.AwardServiceImpl;
import com.ties456.service.director.DirectorService;
import com.ties456.service.director.DirectorServiceImpl;
import com.ties456.service.movie.MovieService;
import com.ties456.service.movie.MovieServiceImpl;
import com.ties456.service.review.ReviewService;
import com.ties456.service.review.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class SearchResource {

    private MovieService movieService = new MovieServiceImpl();
    private DirectorService directorService = new DirectorServiceImpl();
    private AwardService awardService = new AwardServiceImpl();
    private ReviewService reviewService = new ReviewServiceImpl();

    @GET
    public ResponseEntity<List<Object>> search(@QueryParam("q") String searchQuery) {
        List<Object> resultList = new ArrayList<>();
        String query = searchQuery.toUpperCase();
        List<Director> directorList = directorService.getAll();
        resultList.addAll(directorList.stream().filter(director -> director.getName().toUpperCase().indexOf(query) >= 0).collect(Collectors.toList()));
        List<Movie> movieList = movieService.getAll();
        resultList.addAll(movieList.stream().filter(movie -> movie.getName().toUpperCase().indexOf(query) >= 0).collect(Collectors.toList()));
        List<Review> reviewList = reviewService.getAll();
        resultList.addAll(reviewList.stream().filter(review -> review.getTitle().toUpperCase().indexOf(query) >= 0).collect(Collectors.toList()));
        List<Award> awardList = awardService.getAll();
        resultList.addAll(awardList.stream().filter(award -> award.getName().toUpperCase().indexOf(query) >= 0).collect(Collectors.toList()));
        if (resultList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
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