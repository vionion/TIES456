package com.ties456.service.director;


import com.ties456.model.director.Director;

import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
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
