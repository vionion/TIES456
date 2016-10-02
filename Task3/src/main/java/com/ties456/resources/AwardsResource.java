package com.ties456.resources;


import com.ties456.common.Constants;
import com.ties456.model.award.Award;
import com.ties456.service.award.AwardService;
import com.ties456.service.award.AwardServiceImpl;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/awards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class AwardsResource {

    private AwardService awardService = new AwardServiceImpl();

    @GET
    public List<Award> getAwards(@PathParam(Constants.DIRECTOR_ID) long directorId) {
        return awardService.getByDirectorId(directorId);
    }

    @GET
    @Path("/{" + Constants.REVIEW_ID + "}")
    public Award getAward(@PathParam(Constants.DIRECTOR_ID) long directorId,
                            @PathParam(Constants.AWARD_ID) long awardId) {
        return awardService.getByDirectorIdAndId(directorId, awardId);
    }

}