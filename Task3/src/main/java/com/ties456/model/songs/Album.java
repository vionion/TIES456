package com.ties456.model.songs;

import com.ties456.model.Person;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public class Album {

    private Long id;
    private String name;
    private Person autor;
    private List<Song> songs;
    private int duraction;

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

    public Person getAutor() {
        return autor;
    }

    public void setAutor(Person autor) {
        this.autor = autor;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public int getDuraction() {
        return duraction;
    }

    public void setDuraction(int duraction) {
        this.duraction = duraction;
    }
}
