package com.ties456.model.songs;

import com.ties456.model.Person;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public class Label {

    private Long id;
    private String name;
    private List<Album> albums;
    private List<Person> founders;
    private String webSite;

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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Person> getFounders() {
        return founders;
    }

    public void setFounders(List<Person> founders) {
        this.founders = founders;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
