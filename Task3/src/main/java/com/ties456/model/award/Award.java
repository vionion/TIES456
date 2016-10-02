package com.ties456.model.award;

/**
 * Created by chinhnk on 10/2/2016.
 */
public class Award {

    private long id;
    private long directorId;
    private String name;
    private int year;

    public Award(long id, long directorId, String name, int year) {
        this.id = id;
        this.directorId = directorId;
        this.name = name;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(long directorId) {
        this.directorId = directorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
