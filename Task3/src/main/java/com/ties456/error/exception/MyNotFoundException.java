package com.ties456.error.exception;

/**
 * Created by V.Tsybulko on 02.10.2016.
 */
public class MyNotFoundException extends RuntimeException{

    public MyNotFoundException(String message) {
        super("My custom exception says: " + message);
    }
}
