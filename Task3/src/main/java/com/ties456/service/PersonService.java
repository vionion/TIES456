package com.ties456.service;


import com.ties456.model.Person;

import java.util.List;

/**
 * Created by V.Tsybulko on 30.09.2016.
 */
public interface PersonService {

    List<Person> getAll();

    Person getById(long id);

    boolean isPersonExist(long id);

    Person savePerson(Person person);

    void updatePerson(Person person);

    void deletePersonById(long id);

    void deleteAllPersons();

}
