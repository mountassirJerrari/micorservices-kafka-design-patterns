package org.example;

public class Main {
    public static void main(String[] args) {
        UniteCentrale uniteCentrale = new UniteCentrale();

        // 1. Standard HDMI usage
        System.out.println("--- Test 1: Standard HDMI (TV) ---");
        Tv tv = new Tv();
        uniteCentrale.setEcran(tv);
        uniteCentrale.print("Hello from HDMI!");

        // 2. Adapter usage (VGA Screen)
        System.out.println("\n--- Test 2: Adapter HDMI -> VGA (Old Monitor) ---");
        EcranVga ecranVga = new EcranVga();
        HdmiVgaAdapter adapter = new HdmiVgaAdapter(ecranVga);
        uniteCentrale.setEcran(adapter);
        uniteCentrale.print("Hello from VGA via Adapter!");
    }
}