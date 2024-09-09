package my.demo.console.actionhandlers;

import my.demo.Person;
import my.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShowPersonByEmailHandler {
    private final PersonRepository personRepository;

    public ShowPersonByEmailHandler(@Qualifier("personRepository") PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void showPersonByEmail(Scanner scanner) {
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        Person person = personRepository.getByEmail(email);
        if (person == null) {
            System.out.println("Person not found.");
        } else {
            System.out.println(person);
        }
    }
}