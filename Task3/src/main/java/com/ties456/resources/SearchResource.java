package com.ties456.resources;


import com.ties456.common.Constants;
import com.ties456.model.Director;
import com.ties456.model.Movie;
import com.ties456.model.movies.Studio;
import com.ties456.service.DirectorService;
import com.ties456.service.DirectorServiceImpl;
import com.ties456.service.MovieService;
import com.ties456.service.movie.StudioService;
import com.ties456.service.movie.StudioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/studio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class SearchResource {

    @Autowired
    private StudioService studioService = new StudioServiceImpl();
    @Autowired
    private MovieService movieService;
    @Autowired
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

                List<Movie> movieList = movieService.get(name, releaseYear);
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