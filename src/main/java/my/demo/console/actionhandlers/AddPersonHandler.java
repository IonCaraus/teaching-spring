package my.demo.console.actionhandlers;

import my.demo.Person;
import my.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddPersonHandler {
    private final PersonRepository personRepository;

    public AddPersonHandler(@Qualifier("personRepository") PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPerson(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter personal number: ");
        String personalNumber = scanner.nextLine();
        System.out.print("Enter email address: ");
        String emailAddress = scanner.nextLine();

        Person person = new Person(firstName, lastName, personalNumber, emailAddress);
        personRepository.add(person);
    }
}