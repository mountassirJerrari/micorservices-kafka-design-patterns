package org.example;

public class HdmiVgaAdapter implements Hdmi {
    private Vga vga;

    public HdmiVgaAdapter(Vga vga) {
        this.vga = vga;
    }

    @Override
    public void view(byte[] data) {
        System.out.println("Adapter converting HDMI signal to VGA...");
        String message = new String(data);
        vga.print(message);
    }
}
