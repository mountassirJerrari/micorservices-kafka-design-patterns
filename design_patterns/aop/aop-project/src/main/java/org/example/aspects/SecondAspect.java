package org.example.aspects;

import org.aspectj.lang.annotation.*;


@Aspect
public class SecondAspect {

    @Pointcut("execution(* org.example.test.Application.main(..))")
    public void mainMethodPointcut() {}

    @Before("mainMethodPointcut()")
    public void beforeMain() {
        System.out.println(">>> [ANNOTATION] BEFORE main");
    }

    @After("mainMethodPointcut()")
    public void afterMain() {
        System.out.println(">>> [ANNOTATION] AFTER main");
    }

    @AfterReturning("mainMethodPointcut()")
    public void afterReturningMain() {
        System.out.println(">>> [ANNOTATION] AFTER RETURNING main");
    }

    @AfterThrowing("mainMethodPointcut()")
    public void afterThrowingMain() {
        System.out.println(">>> [ANNOTATION] AFTER THROWING main (exception occurred)");
    }
}