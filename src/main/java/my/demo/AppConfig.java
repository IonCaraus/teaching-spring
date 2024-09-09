package my.demo;

import my.demo.repositories.PersonFileRepository;
import my.demo.repositories.PersonInMemoryRepository;
import my.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Profile("local")
    @Bean
    @Qualifier("personRepository")
    public PersonRepository personInMemoryRepository(){
        return new PersonInMemoryRepository();
    }

    @Profile("dev")
    @Bean
    @Qualifier("personRepository")
    public PersonRepository personFileRepository(){
        return new PersonFileRepository();
    }
}
