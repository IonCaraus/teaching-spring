package my.demo.console.actionhandlers;

import my.demo.Person;
import my.demo.repositories.PersonInMemoryRepository;

import java.util.List;

public class ShowAllPersonHandler {
    private final PersonInMemoryRepository personRepository = new PersonInMemoryRepository();

    public void showAllPersons() {
        List<Person> persons = personRepository.getAll();
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
        } else {
            for (Person person : persons) {
                System.out.println(person);
            }
        }
    }
}