package com.ties456.service.movie;

import com.ties456.model.movies.Studio;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public interface StudioService {

    List<Studio> getAll();

    Studio getById(long id);

    boolean isStudioExist(long id);

    Studio saveStudio(Studio studio);

    void updateStudio(Studio studio);

    void deleteStudioById(long id);

    void deleteAllStudios();

}
