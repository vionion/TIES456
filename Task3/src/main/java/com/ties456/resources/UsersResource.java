package com.ties456.resources;


import com.ties456.common.Constants;
import com.ties456.error.exception.MyNotFoundException;
import com.ties456.model.user.User;
import com.ties456.service.user.UserService;
import com.ties456.service.user.UserServiceImpl;
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


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class UsersResource {

    private UserService userService = new UserServiceImpl();

    @GET
    public List<User> getAll(@Context UriInfo uriInfo) {
        List<User> resultList = userService.getAll();
        return resultList;
    }

    @GET
    @Path("/{id}")
    public User getOne(@PathParam("id") long id, @Context UriInfo uriInfo) {
        User result = userService.getById(id);
        if (result == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            return result;
        }
    }

    @PUT
    public ResponseEntity<User> createUser(@RequestBody User user,
                                             @Context UriInfo uriInfo) {
        if (userService.isUserExist(user.getId())) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
        User result = userService.saveUser(user);
        return new ResponseEntity<User>(result, HttpStatus.CREATED);
    }

    @POST
    @Path("/{id}")
    public ResponseEntity<User> updateUser(@PathParam("id") long id,
                                             @RequestBody User userToUpdate,
                                             @Context UriInfo uriInfo) {
        User currentUser = userService.getById(id);
        if (currentUser == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser.setUsername(userToUpdate.getUsername());
        currentUser.setPassword(userToUpdate.getPassword());
        currentUser.setFirstName(userToUpdate.getFirstName());
        currentUser.setLastName(userToUpdate.getLastName());
        currentUser.setEmail(userToUpdate.getEmail());

        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @DELETE
    @Path("/{id}")
    public ResponseEntity<User> deleteUser(@PathParam("id") long id) {
        if (id < 0) {
            throw new MyNotFoundException("Dude, try to be more positive!");
        }
        User user = userService.getById(id);
        if (user == null) {
            throw new MyNotFoundException("There is no user with such strange id");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @DELETE
    public ResponseEntity<User> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @GET
    @Path("/error")
    public ResponseEntity<User> getServerError() throws Throwable {
        throw new Throwable("Did you asked me for some errors? I have one");
    }
}