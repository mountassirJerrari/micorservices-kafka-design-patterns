package org.example;


import org.example.metier.Compte;
import org.example.metier.IMetierBanque;
import org.example.metier.MetierBanqueImpl;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        new Application().start();
    }

    public void start() {
        System.out.println("Démarrage de l'application...");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Code du compte: ");
        Long code = scanner.nextLong();

        System.out.print("Solde initial: ");
        double solde = scanner.nextDouble();

        IMetierBanque metier = new MetierBanqueImpl();
        metier.addCompte(new Compte(code, solde));

        scanner.nextLine(); // consommer le retour à la ligne

        while (true) {
            try {
                System.out.print("\nType d'opération (versement/retrait/quitter): ");
                String typeOp = scanner.nextLine();

                if (typeOp.equalsIgnoreCase("quitter")) {
                    break;
                }

                System.out.print("Montant: ");
                double montant = scanner.nextDouble();
                scanner.nextLine();

                if (typeOp.equalsIgnoreCase("versement")) {
                    metier.verser(code, montant);
                } else if (typeOp.equalsIgnoreCase("retrait")) {
                    metier.retirer(code, montant);
                }

                Compte compte = metier.consulter(code);
                System.out.println("Solde actuel: " + compte);

            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }

        System.out.println("Fin de l'application");
    }
}