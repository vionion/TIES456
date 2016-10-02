package com.ties456.error.mapper;
import com.ties456.error.response.ApiError;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by V.Tsybulko on 02.10.2016.
 */
@Provider
public class MyGenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable ex){
        ApiError errorMessage = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
    }
}
