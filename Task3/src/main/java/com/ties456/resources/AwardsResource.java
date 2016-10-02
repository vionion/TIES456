package com.ties456.resources;


import com.ties456.error.exception.MyNotFoundException;
import com.ties456.model.award.Award;
import com.ties456.service.award.AwardService;
import com.ties456.service.award.AwardServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/award")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class AwardsResource {

    private AwardService awardService = new AwardServiceImpl();

    @GET
    public List<Award> getAll() {
        return awardService.getAll();
    }

    @GET
    @Path("/{id}")
    public Award getOne(@PathParam("id") long id) {
        Award result = awardService.getById(id);
        if (result == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            return result;
        }
    }

    @PUT
    public ResponseEntity<Award> createAward(@RequestBody Award award) {
        if (awardService.isAwardExist(award.getId())) {
            return new ResponseEntity<Award>(HttpStatus.CONFLICT);
        }
        Award result = awardService.saveAward(award);
        return new ResponseEntity<Award>(result, HttpStatus.CREATED);
    }

    @POST
    @Path("/{id}")
    public ResponseEntity<Award> updateAward(@PathParam("id") long id, @RequestBody Award awardToUpdate) {
        Award currentAward = awardService.getById(id);
        if (currentAward == null) {
            return new ResponseEntity<Award>(HttpStatus.NOT_FOUND);
        }
        currentAward.set
        awardService.updateAward(currentAward);
        return new ResponseEntity<Award>(currentAward, HttpStatus.OK);
    }

    @DELETE
    @Path("/{id}")
    public ResponseEntity<Award> deleteAward(@PathParam("id") long id) {
        if (id < 0) {
            throw new MyNotFoundException("Dude, try to be more positive!");
        }
        Award award = awardService.getById(id);
        if (award == null) {
            return new ResponseEntity<Award>(HttpStatus.NOT_FOUND);
        }
        awardService.deleteAwardById(id);
        return new ResponseEntity<Award>(HttpStatus.NO_CONTENT);
    }

    @DELETE
    public ResponseEntity<Award> deleteAllAwards() {
        awardService.deleteAllAwards();
        return new ResponseEntity<Award>(HttpStatus.NO_CONTENT);
    }

}