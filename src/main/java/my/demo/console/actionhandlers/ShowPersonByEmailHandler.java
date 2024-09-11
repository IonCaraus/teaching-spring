package my.demo.console.actionhandlers;

import my.demo.Person;
import my.demo.repositories.PersonInMemoryRepository;

import java.util.Scanner;

public class ShowPersonByEmailHandler {
    private final PersonInMemoryRepository personRepository = PersonInMemoryRepository.getInstance();

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