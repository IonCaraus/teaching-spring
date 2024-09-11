package my.demo.repositories;

import my.demo.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> getByEmail( String email);

    default Person getByPersonNumber(String personNumber) {
        return null;
    }

    void add(Person newPerson);

    List<Person> getAll();
}
