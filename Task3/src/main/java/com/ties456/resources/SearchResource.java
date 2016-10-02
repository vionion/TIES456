package com.ties456.resources;


import com.ties456.common.Constants;
import com.ties456.model.director.Director;
import com.ties456.model.movie.Movie;
import com.ties456.service.director.DirectorService;
import com.ties456.service.director.DirectorServiceImpl;
import com.ties456.service.movie.MovieService;
import com.ties456.service.movie.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class SearchResource {

    private MovieService studioService = new MovieServiceImpl();
    private DirectorService directorService = new DirectorServiceImpl();

    @GET
    @Path("/search")
    public ResponseEntity<List<Object>> search(@QueryParam("mode") Integer mode,
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
    }
}