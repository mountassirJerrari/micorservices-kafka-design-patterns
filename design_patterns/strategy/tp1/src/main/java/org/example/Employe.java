package org.example;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Employe {
    private String cin;
    private double salaireBrutMensuel;
    private CalculateurIGR calculateurIGR;

    public Employe(String cin, double salaireBrutMensuel) {
        this.cin = cin;
        this.salaireBrutMensuel = salaireBrutMensuel;
    }

    public double calculerIGR() {
        if (calculateurIGR == null) {
            throw new IllegalStateException("Aucun calculateur IGR n'est défini pour cet employé");
        }
        double salaireAnnuel = salaireBrutMensuel * 12;
        double igr = calculateurIGR.calculerIGR(salaireAnnuel);
        return igr;
    }

    public double getSalaireNetMensuel() {
        double igrAnnuel = calculerIGR();
        double igrMensuel = igrAnnuel / 12;
        return salaireBrutMensuel - igrMensuel;
    }

    @Override
    public String toString() {
        return String.format("Employé [CIN: %s, Salaire Brut: %.2f DH, IGR Annuel: %.2f DH, Salaire Net: %.2f DH, Pays: %s]",
                cin, salaireBrutMensuel, calculerIGR(), getSalaireNetMensuel(),
                calculateurIGR != null ? calculateurIGR.getNomPays() : "N/A");
    }
}