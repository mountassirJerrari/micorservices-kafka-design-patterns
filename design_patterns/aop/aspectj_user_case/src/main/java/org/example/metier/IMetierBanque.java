package org.example.metier;

public interface IMetierBanque {
    void addCompte(Compte compte);
    void verser(Long code, double montant);
    void retirer(Long code, double montant);
    Compte consulter(Long code);
}