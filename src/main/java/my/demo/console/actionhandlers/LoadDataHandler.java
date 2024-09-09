package my.demo.console.actionhandlers;

import my.demo.Person;
import my.demo.repositories.PersonRepository;

import java.util.UUID;

public class LoadDataHandler {
    private final  PersonRepository  personRepository;

    public LoadDataHandler(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void loadSomeData() {
        personRepository.add(new Person("John", "Doe", UUID.randomUUID().toString(), "john.doe@example.com"));
        personRepository.add(new Person("Jane", "Smith", UUID.randomUUID().toString(), "jane.smith@example.com"));
        personRepository.add(new Person("Alice", "Johnson", UUID.randomUUID().toString(), "alice.johnson@example.com"));
        personRepository.add(new Person("Peter", "Pan", UUID.randomUUID().toString(), "peter.pan@example.com"));
        personRepository.add(new Person("Tom", "Cruse", UUID.randomUUID().toString(), "tom.cruse@example.com"));
    }
}