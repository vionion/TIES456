package com.ties456.error.mapper;

import com.ties456.error.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by V.Tsybulko on 05.10.2016.
 */
@Provider
public class AccessDeniedMapper implements ExceptionMapper<AccessDeniedException> {
    @Override
    public Response toResponse(AccessDeniedException e) {
        ApiError errorMessage = new ApiError(HttpStatus.UNAUTHORIZED, e.getMessage());
        return Response.status(Status.UNAUTHORIZED)
                .entity(errorMessage)
                .build();
    }
}
