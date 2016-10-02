package com.ties456.service;


import com.ties456.model.Director;
import com.ties456.model.Director;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public interface DirectorService {

    List<Director> getAll();

    Director getById(long id);

    boolean isDirectorExist(long id);

    Director saveDirector(Director person);

    void updateDirector(Director person);

    void deleteDirectorById(long id);

    void deleteAllDirectors();

    List<Director> get(String name, Integer birthYear);
}
