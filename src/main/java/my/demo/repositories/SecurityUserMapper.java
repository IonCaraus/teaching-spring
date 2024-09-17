package my.demo.repositories;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecurityUserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        String authorities = resultSet.getString("authorities");
        return new User(resultSet.getString("username"),
                resultSet.getString("password"),
                getAuthorities(authorities));
    }

    private static List<SimpleGrantedAuthority> getAuthorities(String authorities) {
        if(authorities == null || authorities.equals("")) {
            return new ArrayList<>();
        }
        if(authorities.contains(",")){
            List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
            for(String authority : authorities.split(",")){
                authoritiesList.add(new SimpleGrantedAuthority(authority));
            }
            return authoritiesList;
        }
        return List.of(new SimpleGrantedAuthority(authorities));
    }
}