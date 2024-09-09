package my.demo.repositories;

import my.demo.Person;

import java.util.List;

public interface PersonRepository {

    Person getByEmail( String email);

    void add(Person newPerson);

    List<Person> getAll();
}
