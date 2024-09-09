package my.demo;

import my.demo.console.ConsoleGui;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppDemo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ConsoleGui consoleGui = context.getBean(ConsoleGui.class);
        consoleGui.startApp();
    }
}