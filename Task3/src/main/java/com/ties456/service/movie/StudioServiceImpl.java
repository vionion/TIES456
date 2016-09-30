package com.ties456.service.movie;

import com.ties456.model.movies.Studio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
@Service("studioService")
@Transactional
public class StudioServiceImpl implements StudioService {

    private static final List<Studio> studios = Arrays.asList(new Studio(1, "Dreamworks"), new Studio(2, "Warner bros."));

    @Override
    public List<Studio> getAll() {
        return studios;
    }

    @Override
    public Studio getById(long id) {
        for (Studio studio : studios) {
            if (studio.getId() == id) {
                return studio;
            }
        }
        return null;
    }

    @Override
    public boolean isStudioExist(long id) {
        return getById(id) != null;
    }

    @Override
    public Studio saveStudio(Studio studio) {
        studios.add(studio);
        return studio;
    }

    @Override
    public void updateStudio(Studio studio) {
        studios.set(studios.indexOf(getById(studio.getId())), studio);
    }

    @Override
    public void deleteStudioById(long id) {
        Iterator<Studio> iter = studios.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == id) {
                iter.remove();
            }
        }

    }

    @Override
    public void deleteAllStudios() {
        studios.clear();
    }
}