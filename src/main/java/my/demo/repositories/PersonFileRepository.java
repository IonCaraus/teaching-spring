package my.demo.repositories;
import my.demo.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonFileRepository implements PersonRepository {
    private static final String FILE_NAME = "persons.csv";
    String fileName;

    private static final PersonFileRepository INSTANCE = new PersonFileRepository();
    public static PersonFileRepository getInstance() {return INSTANCE;}

    private PersonFileRepository() {
        this.fileName = FILE_NAME;
        System.out.println("File Repository used");
    }

    private PersonFileRepository(String fileName) {
        this.fileName = fileName;
    }

    public Person getByEmail(String email) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Person person : persons) {
                writer.write(person.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                persons.add(Person.fromString(line));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons;
    }
}
