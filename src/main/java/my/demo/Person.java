package my.demo;

public class Person {
    private String firstName;
    private String lastName;
    private String personNumber;
    private String emailAddress;

    public Person(String firstName, String lastName, String personNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNumber = personNumber;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return firstName + "," + lastName + ","+ personNumber+ "," + emailAddress;
    }

    public static Person fromString(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Person(parts[0], parts[1], parts[2], parts[3]);
    }
}