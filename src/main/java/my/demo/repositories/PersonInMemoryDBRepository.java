package my.demo.repositories;

import my.demo.Person;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PersonInMemoryDBRepository implements PersonRepository {
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_PERSON_BY_EMAIL = "select * from persons where email_address = ?";
    private final String SQL_FIND_PERSON_BY_PERSON_NUMBER = "select * from persons where person_number = ?";
    private final String SQL_DELETE_PERSON = "delete from persons where person_number = ?";
    private final String SQL_COUNT_PERSON_BY_PERSON_NUMBER = "select count(1) from persons where person_number = ?";
    private final String SQL_UPDATE_PERSON = "update persons set first_name = ?, last_name = ?, email_address  = ? where  = ?";
    private final String SQL_GET_ALL = "select * from persons";
    private final String SQL_INSERT_PERSON = "insert into persons(first_name, last_name, person_number, email_address) values(?,?,?,?)";


    public PersonInMemoryDBRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        System.out.println("In memory DB Repository");
    }

    public Person getByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_FIND_PERSON_BY_EMAIL, new Object[] { email }, new PersonMapper());
    }

    public Person getByPersonNumber(String personNumber) {
        return jdbcTemplate.queryForObject(SQL_FIND_PERSON_BY_PERSON_NUMBER, new Object[] { personNumber }, new PersonMapper());
    }

    public void add(Person newPerson) {
        Integer count = jdbcTemplate.queryForObject(SQL_COUNT_PERSON_BY_PERSON_NUMBER, Integer.class, newPerson.getPersonNumber());
        if(count < 1) {
            jdbcTemplate.update(SQL_INSERT_PERSON, newPerson.getFirstName(), newPerson.getLastName(),
                    newPerson.getPersonNumber(), newPerson.getEmailAddress());
        } else {
            jdbcTemplate.update(SQL_UPDATE_PERSON, newPerson.getFirstName(), newPerson.getLastName(),
                    newPerson.getPersonNumber(), newPerson.getEmailAddress());
        }
    }

    public boolean deletePerson(Person person) {
        return jdbcTemplate.update(SQL_DELETE_PERSON, person.getPersonNumber()) > 0;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new PersonMapper());
    }
}
