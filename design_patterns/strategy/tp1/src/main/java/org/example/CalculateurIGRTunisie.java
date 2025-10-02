package org.example;
import lombok.extern.slf4j.Slf4j;

@Slf4j

class CalculateurIGRTunisie implements CalculateurIGR {

    @Override
    public double calculerIGR(double salaireAnnuel) {
        if (salaireAnnuel < 50000) {
            return salaireAnnuel * 0.10;
        } else {
            return salaireAnnuel * 0.30;
        }
    }

    @Override
    public String getNomPays() {
        return "TUNISIE";
    }
}
