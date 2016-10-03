package com.ties456.resources;


import com.ties456.common.Constants;
import com.ties456.error.exception.MyNotFoundException;
import com.ties456.model.director.Director;
import com.ties456.service.director.DirectorService;
import com.ties456.service.director.DirectorServiceImpl;
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


@Path("/directors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class DirectorsResource {

    private DirectorService directorService = new DirectorServiceImpl();

    @GET
    public List<Director> getAll(@Context UriInfo uriInfo) {
        List<Director> resultList = directorService.getAll();
        for (Director movie : resultList) {
            movie.addHATEOAS(uriInfo);
        }
        return resultList;
    }

    @GET
    @Path("/{id}")
    public Director getOne(@PathParam("id") long id,
                           @Context UriInfo uriInfo) {
        Director result = directorService.getById(id);
        if (result == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            result.addHATEOAS(uriInfo);
            return result;
        }
    }


    @PUT
    public ResponseEntity<Director> createDirector(@RequestBody Director director,
                                                   @Context UriInfo uriInfo) {
        if (directorService.isDirectorExist(director.getId())) {
            return new ResponseEntity<Director>(HttpStatus.CONFLICT);
        }
        Director result = directorService.saveDirector(director);
        result.addHATEOAS(uriInfo);
        return new ResponseEntity<Director>(result, HttpStatus.CREATED);
    }

    @POST
    @Path("/{id}")
    public ResponseEntity<Director> updateDirector(@PathParam("id") long id,
                                                   @RequestBody Director directorToUpdate,
                                                   @Context UriInfo uriInfo) {
        Director currentDirector = directorService.getById(id);
        if (currentDirector == null) {
            return new ResponseEntity<Director>(HttpStatus.NOT_FOUND);
        }
        currentDirector.setName(directorToUpdate.getName());
        currentDirector.setBirthYear(directorToUpdate.getBirthYear());
        currentDirector.setAwards(directorToUpdate.getAwards());
        currentDirector.setLinks(directorToUpdate.getLinks());
        currentDirector.setMovieIdList(directorToUpdate.getMovieIdList());
        currentDirector.addHATEOAS(uriInfo);
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
            return new ResponseEntity<Director>(HttpStatus.NOT_FOUND);
        }
        directorService.deleteDirectorById(id);
        return new ResponseEntity<Director>(HttpStatus.NO_CONTENT);
    }

    @DELETE
    public ResponseEntity<Director> deleteAllDirectors() {
        directorService.deleteAllDirectors();
        return new ResponseEntity<Director>(HttpStatus.NO_CONTENT);
    }

    @Path("/{" + Constants.DIRECTOR_ID + "}/awards")
    public AwardsResource getAwardsResource() {
        return new AwardsResource();
    }


}