package org.example;

public class EcranVga implements Vga {
    @Override
    public void print(String message) {
        System.out.println("Ecran VGA displaying: " + message);
    }
}
