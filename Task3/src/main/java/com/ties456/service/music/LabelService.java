package com.ties456.service.music;

import com.ties456.model.songs.Label;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public interface LabelService {

    List<Label> getAll();

    Label getById(long id);

    boolean isLabelExist(long id);

    Label saveLabel(Label label);

    void updateLabel(Label label);

    void deleteLabelById(long id);

    void deleteAllLabels();
    
}
