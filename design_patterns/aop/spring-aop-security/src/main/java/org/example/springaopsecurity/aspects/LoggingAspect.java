package org.example.springaopsecurity.aspects;


import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("@annotation(Log)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Before: " + proceedingJoinPoint.getSignature());

        long t1 = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long t2 = System.currentTimeMillis();

        logger.info("After: " + proceedingJoinPoint.getSignature());
        logger.info("Execution duration: " + (t2 - t1) + " ms");

        return result;
    }
}