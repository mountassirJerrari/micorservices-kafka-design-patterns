package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculateurIGRFactoryTest {

    @Test
    void testCreerMaroc() {
        CalculateurIGR calc = CalculateurIGRFactory.getCalculateur("MAROC");

        assertNotNull(calc);
        assertEquals("MAROC", calc.getNomPays());
    }

    @Test
    void testCreerAlgerie() {
        CalculateurIGR calc = CalculateurIGRFactory.getCalculateur("ALGERIE");

        assertNotNull(calc);
        assertEquals("ALGERIE", calc.getNomPays());
    }

    @Test
    void testPaysInconnu() {
        assertThrows(IllegalArgumentException.class, () -> {
            CalculateurIGRFactory.getCalculateur("FRANCE");
        });
    }

    @Test
    void testPaysNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CalculateurIGRFactory.getCalculateur(null);
        });
    }
}