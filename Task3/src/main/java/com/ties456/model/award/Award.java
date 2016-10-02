package com.ties456.model.award;

/**
 * Created by chinhnk on 10/2/2016.
 */
public class Award {

    private int id;
    private String name;
    private int year;

    public Award() {
    }

    public Award(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
