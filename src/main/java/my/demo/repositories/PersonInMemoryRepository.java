package my.demo.repositories;

import my.demo.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Primary
@Qualifier("personRepository")
@Component
public class PersonInMemoryRepository implements PersonRepository {
    private static final PersonInMemoryRepository INSTANCE = new PersonInMemoryRepository();
    private PersonInMemoryRepository() {}
    private List<Person> persons = new ArrayList<>();

    public static PersonInMemoryRepository getInstance() {
        return INSTANCE;
    }

    public Person getByEmail( String email) {
        return getAll().stream()
                .filter(person -> person.getEmailAddress().equals(email))
                .findFirst().orElse(null);
    }

    public void add(Person newPerson) {
        List<Person> allPersons = getAll();
        allPersons.stream()
                .filter(person -> person.getPersonNumber().equals(newPerson.getPersonNumber()))
                .findFirst().ifPresent(allPersons::remove);
        allPersons.add(newPerson);
        addAll(allPersons);
    }

    private void addAll(List<Person> persons) {
        this.persons.clear();
        this.persons.addAll(persons);
    }

    public List<Person> getAll() {
        return new ArrayList<>(persons);
    }
}