package my.demo.repositories;

import my.demo.Person;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PersonDBRepository implements PersonRepository {
    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_PERSON_BY_EMAIL = "select * from PERSONS where email_address = ?";
    private final String SQL_FIND_PERSON_BY_PERSON_NUMBER = "select * from PERSONS where person_number = ?";
    private final String SQL_DELETE_PERSON = "delete from PERSONS where person_number = ?";
    private final String SQL_COUNT_PERSON_BY_PERSON_NUMBER = "select count(1) from PERSONS where person_number = ?";
    private final String SQL_UPDATE_PERSON = "update PERSONS set first_name = ?, last_name = ?, email_address  = ? where person_number = ?";
    private final String SQL_GET_ALL = "select * from PERSONS";
    private final String SQL_INSERT_PERSON = "insert into PERSONS(first_name, last_name, person_number, email_address) values(?,?,?,?)";


    public PersonDBRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        System.out.println("Real DB Repository");
    }

    public List<Person> getByEmail(String email) {
        return jdbcTemplate.query(SQL_FIND_PERSON_BY_EMAIL, new Object[] { email }, new PersonMapper());
        //FIXME string concatenation creates an "opportunity" for SQL injection if user specifies in email the value "' or ''='"
//        String sql = "select * from PERSONS where email_address ='" + email + "'";
//        System.out.println(sql);
//        return jdbcTemplate.query(sql, new PersonMapper());
    }

    @Override
    public Person getByPersonNumber(String personNumber) {
//        return jdbcTemplate.queryForObject(SQL_FIND_PERSON_BY_PERSON_NUMBER, new Object[] { personNumber }, new PersonMapper());
        return jdbcTemplate.queryForObject("select * from PERSONS where person_number = "+ personNumber, new PersonMapper());
    }

    public void add(Person newPerson) {
        Integer count = jdbcTemplate.queryForObject(SQL_COUNT_PERSON_BY_PERSON_NUMBER, Integer.class, newPerson.getPersonNumber());
        if(count < 1) {
            jdbcTemplate.update(SQL_INSERT_PERSON,
                    newPerson.getFirstName(), newPerson.getLastName(),
                    newPerson.getPersonNumber(), newPerson.getEmailAddress());
            // NOT SAFE QUERY, it allows SQL injection, use
//            jdbcTemplate.execute("update persons set first_name = "+newPerson.getFirstName()
//                    +", last_name = "+newPerson.getLastName()
//                    +", email_address  = "+newPerson.getPersonNumber()
//                    +" where  = "+ newPerson.getEmailAddress());
        } else {
            jdbcTemplate.update(SQL_UPDATE_PERSON, newPerson.getFirstName(), newPerson.getLastName(),
                    newPerson.getEmailAddress(), newPerson.getPersonNumber());
        }
    }

    public boolean deletePerson(Person person) {
        return jdbcTemplate.update(SQL_DELETE_PERSON, person.getPersonNumber()) > 0;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new PersonMapper());
    }
}
