package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Scanner;

@Aspect
public class SecurityAspect {

    @Pointcut("execution(* org.example.Application.start(..))")
    public void startApp() {}

    @Around("startApp()")
    public void authenticate(ProceedingJoinPoint joinPoint) throws Throwable {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals("root") && password.equals("1234")) {
            joinPoint.proceed();
        } else {
            throw new RuntimeException("Access Denied");
        }
    }
}
