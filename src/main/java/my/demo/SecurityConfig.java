package my.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    public static final String LIST_PERSONS = "LIST_PERSONS";
    public static final String SHOW_PERSON = "SHOW_PERSON";
    public static final String CREATE_PERSON = "CREATE_PERSON";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        //or http basic
//        http.httpBasic(Customizer.withDefaults());
        http.formLogin(customizer -> customizer
                .defaultSuccessUrl("/persons", true)
        );

        http.authorizeHttpRequests(customizer -> customizer
                .requestMatchers(HttpMethod.GET, "/persons").hasAuthority(LIST_PERSONS)
                .requestMatchers(HttpMethod.GET, "/persons/search").hasAuthority(LIST_PERSONS)
                .requestMatchers(HttpMethod.GET, "/persons/{personNumber:^[0-9]*$}").hasAuthority(SHOW_PERSON)
                .requestMatchers("/persons/add").hasAuthority(CREATE_PERSON));

//        http.csrf(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
