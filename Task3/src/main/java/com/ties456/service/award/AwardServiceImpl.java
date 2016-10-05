package com.ties456.service.award;

import com.ties456.model.award.Award;
import com.ties456.model.director.Director;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by V.Tsybulko on 10/2/2016.
 */
@Service("awardSerice")
public class AwardServiceImpl implements AwardService {

    private static final List<Award> awards = new ArrayList<Award>(Arrays.asList(
            new Award(1, 1, "Golden Globe", 2004),
            new Award(2, 1, "Oscar", 2011),
            new Award(3, 2, "Gold palm branch", 2010)
    ));

    @Override
    public List<Award> getAll() {
        return awards;
    }

    @Override
    public Award getById(long id) {
        for (Award award : awards) {
            if (award.getId() == id) {
                return award;
            }
        }
        return null;
    }

    @Override
    public List<Award> getByDirectorId(long directorId) {
        List<Award> result = new ArrayList<>();
        for (Award award : awards) {
            if (award.getDirectorId() == directorId) {
                result.add(award);
            }
        }
        return result;
    }

    @Override
    public Award getByDirectorIdAndId(long directorId, long id) {
        for (Award award : awards) {
            if ((award.getDirectorId() == directorId) && (award.getId() == id)) {
                return award;
            }
        }
        return null;
    }

    @Override
    public boolean isAwardExist(long directorId, long id) {
        return getByDirectorIdAndId(directorId, id) != null;
    }

    @Override
    public boolean isAwardExist(long id) {
        return getById(id) != null;
    }

    @Override
    public Award saveAward(Award award) {
        awards.add(award);
        return award;
    }

    @Override
    public void updateAward(Award award) {
        awards.set(awards.indexOf(getByDirectorIdAndId(award.getDirectorId(), award.getId())), award);
    }

    @Override
    public void deleteAwardById(long directorId, long id) {
        Iterator<Award> iter = awards.iterator();
        while (iter.hasNext()) {
            if ((iter.next().getDirectorId() == directorId) && (iter.next().getId() == id)) {
                iter.remove();
            }
        }
    }

    @Override
    public void deleteAwardById(long id) {
        Iterator<Award> iter = awards.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == id) {
                iter.remove();
            }
        }
    }

    @Override
    public void deleteAllAwards() {
        awards.clear();
    }
}