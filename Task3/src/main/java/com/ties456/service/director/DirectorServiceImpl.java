package com.ties456.service.director;

import com.ties456.model.director.Director;
import com.ties456.model.review.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chinhnk on 10/2/2016.
 */
@Service("directorService")
//@Transactional
public class DirectorServiceImpl implements DirectorService {

    private static final List<Director> directors = new ArrayList<Director>(Arrays.asList(
            new Director(1, "Peter Jackson", 1961),
            new Director(2, "Christopher Nolan", 1970)
    ));

    @Override
    public List<Director> getAll() {
        return directors;
    }

    @Override
    public Director getById(long id) {
        for (Director director : directors) {
            if (director.getId() == id) {
                return director;
            }
        }
        return null;
    }

    @Override
    public boolean isDirectorExist(long id) {
        return getById(id) != null;
    }

    @Override
    public Director saveDirector(Director director) {
        directors.add(director);
        return director;
    }

    @Override
    public void updateDirector(Director director) {
        directors.set(directors.indexOf(getById(director.getId())), director);
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

    @Override
    public Director getByMovieId(long movieId) {
        for (Director director : directors) {
            if (director.getMovieIdList().contains(Long.valueOf(movieId))) {
                return director;
            }
        }
        return null;
    }
}