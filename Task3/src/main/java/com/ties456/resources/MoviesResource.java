package com.ties456.resources;


import com.ties456.error.exception.MyNotFoundException;
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

    @PUT
    public ResponseEntity<Studio> createStudio(@RequestBody Studio studio) {
        if (studioService.isStudioExist(studio.getId())) {
            return new ResponseEntity<Studio>(HttpStatus.CONFLICT);
        }
        Studio result = studioService.saveStudio(studio);
        return new ResponseEntity<Studio>(result, HttpStatus.CREATED);
    }

    @POST
    @Path("/{id}")
    public ResponseEntity<Studio> updateStudio(@PathParam("id") long id, @RequestBody Studio studioToUpdate) {
        Studio currentStudio = studioService.getById(id);
        if (currentStudio == null) {
            return new ResponseEntity<Studio>(HttpStatus.NOT_FOUND);
        }
        currentStudio.setName(studioToUpdate.getName());
        studioService.updateStudio(currentStudio);
        return new ResponseEntity<Studio>(currentStudio, HttpStatus.OK);
    }

    @DELETE
    @Path("/{id}")
    public ResponseEntity<Studio> deleteStudio(@PathParam("id") long id) {
        if (id < 0) {
            throw new MyNotFoundException("Dude, try to be more positive!");
        }
        Studio studio = studioService.getById(id);
        if (studio == null) {
            throw new MyNotFoundException("There is no studio with such strange id");
//            return new ResponseEntity<Studio>(HttpStatus.NOT_FOUND);
        }
        studioService.deleteStudioById(id);
        return new ResponseEntity<Studio>(HttpStatus.NO_CONTENT);
    }

    @DELETE
    public ResponseEntity<Studio> deleteAllStudios() {
        studioService.deleteAllStudios();
        return new ResponseEntity<Studio>(HttpStatus.NO_CONTENT);
    }

    @GET
    @Path("/error")
    public ResponseEntity<Studio> getServerError() throws Throwable {
        throw new Throwable("Did you asked me for some errors? I have one");
    }

}