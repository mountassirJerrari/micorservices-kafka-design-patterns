package org.example;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class CalculateurIGRAlgerie implements CalculateurIGR {

    @Override
    public double calculerIGR(double salaireAnnuel) {
        return salaireAnnuel * 0.35;
    }

    @Override
    public String getNomPays() {
        return "ALGERIE";
    }
}