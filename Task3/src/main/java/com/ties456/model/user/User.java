package com.ties456.model.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinhnk on 10/5/2016.
 */
public class User implements Principal {

    private long id;
    private String userName;
    private String password;

    private String email;
    private String firstName;
    private String lastName;
    private List<String> role;

    public User(long id, String userName, String password, String email, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return this.firstName + " " + this.lastName;
    }
}