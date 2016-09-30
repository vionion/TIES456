package com.ties456.model.songs;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public class Song {

    private Long id;
    private String name;
    private String duraction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuraction() {
        return duraction;
    }

    public void setDuraction(String duraction) {
        this.duraction = duraction;
    }
}
