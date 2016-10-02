package com.ties456.service.award;

import com.ties456.model.award.Award;

import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
 */
public interface AwardService {

    List<Award> getAll();

    Award getById(int id);

    boolean isAwardExist(int id);

    Award saveAward(Award movie);

    void updateAward(Award movie);

    void deleteAwardById(int id);

    void deleteAllAwards();

    List<Award> searchByName(String name);
}
