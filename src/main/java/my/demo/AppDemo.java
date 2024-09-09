package my.demo;

import my.demo.console.ConsoleGui;
import my.demo.console.actionhandlers.AddPersonHandler;
import my.demo.console.actionhandlers.LoadDataHandler;
import my.demo.console.actionhandlers.ShowAllPersonHandler;
import my.demo.console.actionhandlers.ShowPersonByEmailHandler;
import my.demo.repositories.PersonFileRepository;
import my.demo.repositories.PersonInMemoryRepository;
import my.demo.repositories.PersonRepository;

public class AppDemo {

    public static void main(String[] args) {
        PersonRepository personRepository = PersonInMemoryRepository.getInstance();
//        PersonRepository personRepository = PersonFileRepository.getInstance();

        ShowAllPersonHandler showAllPersonHandler = new ShowAllPersonHandler(personRepository);
        ShowPersonByEmailHandler showPersonByEmailHandler = new ShowPersonByEmailHandler(personRepository);
        AddPersonHandler addPersonHandler = new AddPersonHandler(personRepository);
        LoadDataHandler loadDataHandler = new LoadDataHandler(personRepository);

        new ConsoleGui(showAllPersonHandler, showPersonByEmailHandler, addPersonHandler, loadDataHandler)
                .startApp();
    }


}