package my.demo;

import my.demo.repositories.PersonInMemoryRepository;

import java.util.List;
import java.util.UUID;

public class HardcodedFlowApp {
    public static void start() {

        PersonInMemoryRepository personRepository = PersonInMemoryRepository.getInstance();

        personRepository.add(new Person("John", "Doe", UUID.randomUUID().toString(), "john.doe@example.com"));
        personRepository.add(new Person("Jane", "Smith", UUID.randomUUID().toString(), "jane.smith@example.com"));
        personRepository.add(new Person("Alice", "Johnson", UUID.randomUUID().toString(), "alice.johnson@example.com"));

        // Read all persons from the file
        List<Person> readPersons = personRepository.getAll();
        for (Person person : readPersons) {
            System.out.println(person);
        }

        // Read a single person by email address
        Person person = personRepository.getByEmail("alice.johnson@example.com");
        if (person != null) {
            System.out.println("Found person: " + person);
        } else {
            System.out.println("Person not found.");
        }
    }
}
