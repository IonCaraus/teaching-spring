package my.demo.console;

import my.demo.console.actionhandlers.AddPersonHandler;
import my.demo.console.actionhandlers.LoadDataHandler;
import my.demo.console.actionhandlers.ShowAllPersonHandler;
import my.demo.console.actionhandlers.ShowPersonByEmailHandler;

import java.util.Scanner;

public class ConsoleGui {
    private final ShowAllPersonHandler showAllPersonHandler;
    private final ShowPersonByEmailHandler showPersonByEmailHandler;
    private final AddPersonHandler addPersonHandler;
    private final LoadDataHandler loadDataHandler;

    public ConsoleGui(ShowAllPersonHandler showAllPersonHandler, ShowPersonByEmailHandler showPersonByEmailHandler, AddPersonHandler addPersonHandler, LoadDataHandler loadDataHandler) {
        this.showAllPersonHandler = showAllPersonHandler;
        this.showPersonByEmailHandler = showPersonByEmailHandler;
        this.addPersonHandler = addPersonHandler;
        this.loadDataHandler = loadDataHandler;
    }

    public void startApp() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("=================================");
            if (executeSelectedAction(choice, scanner)) return;
        }
    }

    private boolean executeSelectedAction(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                showAllPersonHandler.showAllPersons();
                break;
            case 2:
                showPersonByEmailHandler.showPersonByEmail(scanner);
                break;
            case 3:
                addPersonHandler.addPerson(scanner);
                break;
            case 4:
                loadDataHandler.loadSomeData();
                break;
            case 5:
                System.out.println("Exiting...");
                scanner.close();
                return true;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }

    private void showMenu() {
        System.out.println("=================================");
        System.out.println("Select an action:");
        System.out.println("1. Show all persons");
        System.out.println("2. Show person by email");
        System.out.println("3. Add person");
        System.out.println("4. Load some data");
        System.out.println("5. Exit");
        System.out.println("=================================");
        System.out.print("Enter your choice: ");
    }
}
