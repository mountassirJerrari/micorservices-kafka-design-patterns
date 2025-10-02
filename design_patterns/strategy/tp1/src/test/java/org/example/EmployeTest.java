package org.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


import static org.junit.jupiter.api.Assertions.*;


class EmployeTest {

    @Test
    @DisplayName("Devrait créer un employé avec constructeur simple")
    void testConstructeurSimple() {
        Employe emp = new Employe("AB123", 5000);

        assertEquals("AB123", emp.getCin());
        assertEquals(5000, emp.getSalaireBrutMensuel());
    }

    @Test
    @DisplayName("Devrait créer un employé avec Builder")
    void testBuilder() {
        Employe emp = Employe.builder()
                .cin("CD456")
                .salaireBrutMensuel(8000)
                .build();

        assertNotNull(emp);
        assertEquals("CD456", emp.getCin());
    }

    @Test
    @DisplayName("Devrait lancer exception si calculateur null")
    void testExceptionCalculateurNull() {
        Employe emp = new Employe("EF789", 5000);

        assertThrows(IllegalStateException.class, emp::calculerIGR);
    }

    @Test
    @DisplayName("Devrait calculer IGR correctement")
    void testCalculIGR() {
        Employe emp = Employe.builder()
                .cin("GH012")
                .salaireBrutMensuel(5000)
                .calculateurIGR(new CalculateurIGRMaroc())
                .build();

        double igr = emp.calculerIGR();
        assertEquals(12000, igr, 0.01);
    }

    @Test
    @DisplayName("Devrait calculer salaire net correctement")
    void testSalaireNet() {
        Employe emp = Employe.builder()
                .cin("IJ345")
                .salaireBrutMensuel(10000)
                .calculateurIGR(new CalculateurIGRMaroc())
                .build();

        double salaireNet = emp.getSalaireNetMensuel();
        assertEquals(5800, salaireNet, 0.01);
    }
}
