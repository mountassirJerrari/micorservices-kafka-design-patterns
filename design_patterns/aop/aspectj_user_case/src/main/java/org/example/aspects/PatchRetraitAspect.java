package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.metier.Compte;
import org.example.metier.MetierBanqueImpl;

@Aspect
public class PatchRetraitAspect {

    @Pointcut("execution(* org.example.metier.MetierBanqueImpl.retirer(..)) && args(code, montant)")
    public void pc1(Long code, double montant) {}

    @Around("pc1(code, montant)")
    public void around(ProceedingJoinPoint joinPoint, Long code, double montant) throws Throwable {
        MetierBanqueImpl metier = (MetierBanqueImpl) joinPoint.getTarget();
        Compte compte = metier.consulter(code);

        if (compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant");
        } else {
            joinPoint.proceed();
        }
    }
}