package com.ties456.service.user;

import com.ties456.model.user.User;
import com.ties456.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chinhnk on 10/5/2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final List<User> users = Arrays.asList(
            new User(1, "vonion", "mrsipuli", "vonion@vadc.com", "Vitalii", "Tsybulko"),
            new User(2, "adriendefarge", "debaguette", "adriendefarge@vadc.com", "Adrien", "Defarge"),
            new User(3, "minhdcle1", "icancanacan", "minhdcle1@vadc.com", "Minh Duc", "Le"),
            new User(4, "chinhnguyenkim", "idontknow", "chinhnguyenkim@vadc.com", "Chinh", "Nguyen Kim")
    );

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean isUserExist(long id) {
        return getById(id) != null;
    }

    @Override
    public User saveUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        users.set(users.indexOf(getById(user.getId())), user);
    }

    @Override
    public void deleteUserById(long id) {
        Iterator<User> iter = users.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == id) {
                iter.remove();
            }
        }
    }

    @Override
    public void deleteAllUsers() {
        users.clear();
    }
}