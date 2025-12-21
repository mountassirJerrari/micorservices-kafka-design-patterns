package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

@Aspect
public class LoggingAspect {
    private Logger logger;

    public LoggingAspect() {
        try {
            logger = Logger.getLogger(LoggingAspect.class.getName());

            FileHandler fileHandler = new FileHandler("log.xml");
            fileHandler.setFormatter(new XMLFormatter());
            logger.addHandler(fileHandler);

            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Pointcut("execution(* org.example.metier.*.*(..))")
    public void pc1() {}

    @Around("pc1()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();

        logger.info("*************************************");
        logger.info("Avant exécution de la méthode: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        long t2 = System.currentTimeMillis();
        logger.info("Après exécution de la méthode: " + joinPoint.getSignature());
        logger.info("Durée d'exécution: " + (t2 - t1) + " ms");
        logger.info("*************************************");

        return result;
    }
}