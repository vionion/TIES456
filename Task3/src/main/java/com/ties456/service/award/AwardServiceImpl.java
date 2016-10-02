package com.ties456.service.award;

import com.ties456.model.award.Award;
import com.ties456.model.movie.Movie;
import com.ties456.service.award.AwardService;
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

    private static final List<Award> awards = Arrays.asList(
            new Award(1, "Golden Globe", 2004),
            new Award(2, "Oscar", 2011),
            new Award(2, "Gold palm branch", 2010)
    );

    @Override
    public List<Award> getAll() {
        return awards;
    }

    @Override
    public Award getById(int id) {
        for (Award award : awards) {
            if (award.getId() == id) {
                return award;
            }
        }
        return null;
    }

    @Override
    public List<Award> searchByName(String name) {
        List<Award> result = new ArrayList<>();
        for (Award award : awards) {
            if (award.getName() == name) {
                result.add(award);
            }
        }
        return result;
    }
    
    @Override
    public boolean isAwardExist(int id) {
        return getById(id) != null;
    }

    @Override
    public Award saveAward(Award award) {
        awards.add(award);
        return award;
    }

    @Override
    public void updateAward(Award award) {
        awards.set(awards.indexOf(getById(award.getId())), award);
    }

    @Override
    public void deleteAwardById(int id) {
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