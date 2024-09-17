package my.demo;

import my.demo.repositories.SecurityUserMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MySqlUserDetailsService implements UserDetailsService {
    private final String SQL_FIND_USER_BY_USERNAME = "select * from SECURITY_USERS where username = ?";
    private JdbcTemplate jdbcTemplate;

    public MySqlUserDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return jdbcTemplate.<User>queryForObject(SQL_FIND_USER_BY_USERNAME, new Object[]{username}, new SecurityUserMapper());
        } catch (DataAccessException e) {
//            throw new UsernameNotFoundException(e.getMessage());
            throw new BadCredentialsException("Invalid username or password",e);
        }
    }
}


