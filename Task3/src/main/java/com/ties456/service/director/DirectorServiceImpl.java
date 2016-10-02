package com.ties456.service.director;

import com.ties456.model.director.Director;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chinhnk on 10/2/2016.
 */
@Service("studioService")
//@Transactional
public class DirectorServiceImpl implements DirectorService {

    private static final List<Director> directors = new ArrayList<>();

    @Override
    public List<Director> getAll() {
        return directors;
    }

    @Override
    public Director getById(long id) {
        for (Director studio : directors) {
            if (studio.getId() == id) {
                return studio;
            }
        }
        return null;
    }

    @Override
    public boolean isDirectorExist(long id) {
        return getById(id) != null;
    }

    @Override
    public Director saveDirector(Director studio) {
        directors.add(studio);
        return studio;
    }

    @Override
    public void updateDirector(Director studio) {
        directors.set(directors.indexOf(getById(studio.getId())), studio);
    }

    @Override
    public void deleteDirectorById(long id) {
        Iterator<Director> iter = directors.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == id) {
                iter.remove();
            }
        }

    }

    @Override
    public void deleteAllDirectors() {
        directors.clear();
    }

    @Override
    public List<Director> get(String name, Integer birthYear) {
        return null;
    }
}