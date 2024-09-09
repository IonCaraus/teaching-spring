package my.demo;

import my.demo.console.ConsoleGui;
import my.demo.console.actionhandlers.AddPersonHandler;
import my.demo.console.actionhandlers.LoadDataHandler;
import my.demo.console.actionhandlers.ShowAllPersonHandler;
import my.demo.console.actionhandlers.ShowPersonByEmailHandler;
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

    @Bean
    public AddPersonHandler addPersonHandler(@Qualifier("personRepository")PersonRepository personRepository){
        return new AddPersonHandler(personRepository);
    }

    @Bean
    public LoadDataHandler loadDataHandler(@Qualifier("personRepository")PersonRepository personRepository){
        return new LoadDataHandler(personRepository);
    }

    @Bean
    public ShowAllPersonHandler showAllPersonHandler(@Qualifier("personRepository")PersonRepository personRepository){
        return new ShowAllPersonHandler(personRepository);
    }

    @Bean
    public ShowPersonByEmailHandler showPersonByEmailHandler(@Qualifier("personRepository")PersonRepository personRepository){
        return new ShowPersonByEmailHandler(personRepository);
    }

    @Bean
    public ConsoleGui consoleGui(ShowAllPersonHandler showAllPersonHandler, ShowPersonByEmailHandler showPersonByEmailHandler,
                                 AddPersonHandler addPersonHandler, LoadDataHandler loadDataHandler){
        return new ConsoleGui(showAllPersonHandler, showPersonByEmailHandler, addPersonHandler, loadDataHandler);
    }
}
