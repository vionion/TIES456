package com.ties456.service.award;

import com.ties456.model.award.Award;

import java.util.List;

/**
 * Created by chinhnk on 10/2/2016.
 */
public interface AwardService {

    List<Award> getAll();

    Award getById(long id);

    List<Award> getByDirectorId(long directorId);

    Award getByDirectorIdAndId(long directorId, long id);

    boolean isAwardExist(long directorId, long id);

    boolean isAwardExist(long id);

    Award saveAward(Award director);

    void updateAward(Award director);

    void deleteAwardById(long directorId, long id);

    void deleteAwardById(long id);

    void deleteAllAwards();
}
