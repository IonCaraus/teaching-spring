package my.demo.repositories;


import my.demo.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    public Person mapRow(ResultSet resultSet, int i) throws SQLException {

        Person person = new Person();
//        person.setId(resultSet.getLong("id"));
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setPersonNumber(resultSet.getString("person_number"));
        person.setEmailAddress(resultSet.getString("email_address"));
        return person;
    }
}