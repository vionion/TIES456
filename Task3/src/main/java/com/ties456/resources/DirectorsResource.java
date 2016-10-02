package com.ties456.resources;


import com.ties456.error.exception.MyNotFoundException;
import com.ties456.model.director.Director;
import com.ties456.service.director.DirectorService;
import com.ties456.service.director.DirectorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/director")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class DirectorsResource {

    private DirectorService directorService = new DirectorServiceImpl();

    @GET
    public List<Director> getAll() {
        return directorService.getAll();
    }

    @GET
    @Path("/{id}")
    public Director getOne(@PathParam("id") long id) {
        Director result = directorService.getById(id);
        if (result == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            return result;
        }
    }

    @PUT
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        if (directorService.isDirectorExist(director.getId())) {
            return new ResponseEntity<Director>(HttpStatus.CONFLICT);
        }
        Director result = directorService.saveDirector(director);
        return new ResponseEntity<Director>(result, HttpStatus.CREATED);
    }

    @POST
    @Path("/{id}")
    public ResponseEntity<Director> updateDirector(@PathParam("id") long id, @RequestBody Director directorToUpdate) {
        Director currentDirector = directorService.getById(id);
        if (currentDirector == null) {
            return new ResponseEntity<Director>(HttpStatus.NOT_FOUND);
        }
        currentDirector.setName(directorToUpdate.getName());
        currentDirector.setBirthYear(directorToUpdate.getBirthYear());
        currentDirector.setAwards(directorToUpdate.getAwards());
        currentDirector.setLinks(directorToUpdate.getLinks());
        currentDirector.setMovieIdList(directorToUpdate.getMovieIdList());
        directorService.updateDirector(currentDirector);
        return new ResponseEntity<Director>(currentDirector, HttpStatus.OK);
    }

    @DELETE
    @Path("/{id}")
    public ResponseEntity<Director> deleteDirector(@PathParam("id") long id) {
        if (id < 0) {
            throw new MyNotFoundException("Dude, try to be more positive!");
        }
        Director director = directorService.getById(id);
        if (director == null) {
            throw new MyNotFoundException("There is no director with such strange id");
//            return new ResponseEntity<Director>(HttpStatus.NOT_FOUND);
        }
        directorService.deleteDirectorById(id);
        return new ResponseEntity<Director>(HttpStatus.NO_CONTENT);
    }

    @DELETE
    public ResponseEntity<Director> deleteAllDirectors() {
        directorService.deleteAllDirectors();
        return new ResponseEntity<Director>(HttpStatus.NO_CONTENT);
    }

    @GET
    @Path("/error")
    public ResponseEntity<Director> getServerError() throws Throwable {
        throw new Throwable("Did you asked me for some errors? I have one");
    }

}