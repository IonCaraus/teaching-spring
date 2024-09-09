package my.demo.console.actionhandlers;

import my.demo.Person;
import my.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllPersonHandler {
    private final PersonRepository personRepository;

    public ShowAllPersonHandler(@Qualifier("personRepository") PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

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