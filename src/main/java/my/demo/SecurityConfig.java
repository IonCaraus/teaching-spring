package my.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public static final String LIST_PERSONS = "LIST_PERSONS";
    public static final String SHOW_PERSON = "SHOW_PERSON";
    public static final String CREATE_PERSON = "CREATE_PERSON";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //or http basic
//        http.httpBasic(Customizer.withDefaults());
        http.formLogin(customizer -> customizer
                .defaultSuccessUrl("/persons", true)
        );

        http.authorizeHttpRequests(customizer -> customizer
                .requestMatchers(HttpMethod.GET, "/persons").hasAuthority(LIST_PERSONS)
                .requestMatchers(HttpMethod.GET, "/persons/{personNumber:^[0-9]*$}").hasAuthority(SHOW_PERSON)
                .requestMatchers(HttpMethod.POST, "/persons/add").hasAuthority(CREATE_PERSON));

        http.csrf(Customizer.withDefaults());
//        http.authenticationProvider(...)
//        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        String password = passwordEncoder.encode("pass");
        userDetailsManager.createUser(User.withUsername("listUser").password(password).authorities(LIST_PERSONS).build());
        userDetailsManager.createUser(User.withUsername("showPerson").password(password).authorities(SHOW_PERSON).build());
        userDetailsManager.createUser(User.withUsername("createPerson").password(password).authorities(CREATE_PERSON).build());
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
