package org.example.springaopsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // Authenticate user
        SecurityContext.authenticate("root", "1234", new String[]{"USER", "ADMIN"});

        // Get service bean
        IMetier metier = context.getBean(IMetier.class);

        System.out.println("Class: " + metier.getClass().getSimpleName());

        try {
            metier.process();
            double result = metier.compute();
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
