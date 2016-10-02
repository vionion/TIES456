package com.ties456.resources;


import com.ties456.model.movies.Studio;
import com.ties456.service.DirectorService;
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
import java.util.List;


@Path("/studio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class MoviesResource {

    @Autowired
    private StudioService studioService = new StudioServiceImpl();
    @Autowired
    private MovieService movieService;
    @Autowired
    private DirectorService actorService;

    @GET
    @Path("/")
    public List<Studio> getAll() {
        return studioService.getAll();
    }

    @GET
    @Path("/{id}")
    public Studio getOne(@PathParam("id") long id) {
        Studio result = studioService.getById(id);
        if (result == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Studio> createStudio(@RequestBody Studio studio) {
        if (studioService.isStudioExist(studio.getId())) {
            return new ResponseEntity<Studio>(HttpStatus.CONFLICT);
        }
        Studio result = studioService.saveStudio(studio);
        return new ResponseEntity<Studio>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Studio> updateStudio(@PathVariable("id") long id, @RequestBody Studio studioToUpdate) {
        Studio currentStudio = studioService.getById(id);
        if (currentStudio == null) {
            return new ResponseEntity<Studio>(HttpStatus.NOT_FOUND);
        }
        currentStudio.setName(studioToUpdate.getName());
        studioService.updateStudio(currentStudio);
        return new ResponseEntity<Studio>(currentStudio, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Studio> deleteStudio(@PathVariable("id") long id) {
        Studio studio = studioService.getById(id);
        if (studio == null) {
            return new ResponseEntity<Studio>(HttpStatus.NOT_FOUND);
        }
        studioService.deleteStudioById(id);
        return new ResponseEntity<Studio>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Studio> deleteAllStudios() {
        studioService.deleteAllStudios();
        return new ResponseEntity<Studio>(HttpStatus.NO_CONTENT);
    }

}