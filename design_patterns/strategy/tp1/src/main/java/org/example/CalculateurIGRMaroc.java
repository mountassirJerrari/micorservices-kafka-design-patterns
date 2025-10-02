package org.example;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class CalculateurIGRMaroc implements CalculateurIGR {

    @Override
    public double calculerIGR(double salaireAnnuel) {
        double igr;
        if (salaireAnnuel < 40000) {
            igr = salaireAnnuel * 0.05;
        } else if (salaireAnnuel < 120000) {
            igr = salaireAnnuel * 0.20;
        } else {
            igr = salaireAnnuel * 0.42;
        }
        return igr;
    }

    @Override
    public String getNomPays() {
        return "MAROC";
    }
}