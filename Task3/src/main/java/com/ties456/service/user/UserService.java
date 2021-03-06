package com.ties456.service.user;

import com.ties456.model.user.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by chinhnk on 10/5/2016.
 */
public interface UserService extends UserDetailsService {

    List<User> getAll();

    User getById(long id);

    boolean isUserExist(long id);

    User saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    void deleteAllUsers();
}
