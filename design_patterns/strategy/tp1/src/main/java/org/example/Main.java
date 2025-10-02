package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== GESTION DES EMPLOYÉS ===\n");

        System.out.print("CIN: ");
        String cin = scanner.nextLine();

        System.out.print("Salaire mensuel: ");
        double salaire = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Pays (MAROC/ALGERIE/TUNISIE): ");
        String pays = scanner.nextLine();

        Employe emp = Employe.builder()
                .cin(cin)
                .salaireBrutMensuel(salaire)
                .calculateurIGR(CalculateurIGRFactory.getCalculateur(pays))
                .build();

        System.out.println("\n--- RÉSULTATS ---");
        System.out.println("Salaire Brut: " + emp.getSalaireBrutMensuel() + " DH");
        System.out.println("IGR: " + emp.calculerIGR() + " DH");
        System.out.println("Salaire Net: " + emp.getSalaireNetMensuel() + " DH");

        scanner.close();
    }
}