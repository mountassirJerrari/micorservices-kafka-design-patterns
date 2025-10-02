package org.example;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class CalculateurIGRFactory {

    public static CalculateurIGR getCalculateur(String pays) {
        if (pays == null) {
            throw new IllegalArgumentException("Le pays ne peut pas être null");
        }

        switch(pays.trim().toUpperCase()) {
            case "MAROC":
                return new CalculateurIGRMaroc();
            case "ALGERIE":
                return new CalculateurIGRAlgerie();
            case "TUNISIE":
                return new CalculateurIGRTunisie();
            default:
                throw new IllegalArgumentException("Pays non supporté: " + pays);
        }
    }
}