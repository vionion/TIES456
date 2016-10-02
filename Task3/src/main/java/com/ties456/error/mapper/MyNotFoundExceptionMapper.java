package com.ties456.error.mapper;
import com.ties456.error.exception.MyNotFoundException;
import com.ties456.error.response.ApiError;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by V.Tsybulko on 02.10.2016.
 */
@Provider
public class MyNotFoundExceptionMapper implements ExceptionMapper<MyNotFoundException> {
    @Override
    public Response toResponse(MyNotFoundException ex){
        ApiError errorMessage = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return Response.status(Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
