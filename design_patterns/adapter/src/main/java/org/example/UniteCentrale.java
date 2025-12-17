package org.example;

public class UniteCentrale {
    private Hdmi ecran;

    public void setEcran(Hdmi ecran) {
        this.ecran = ecran;
    }

    public void print(String message) {
        System.out.println("UniteCentrale sending signal via HDMI...");
        ecran.view(message.getBytes());
    }
}
