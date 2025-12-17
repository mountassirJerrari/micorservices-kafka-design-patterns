package org.example;

public class Tv implements Hdmi {
    @Override
    public void view(byte[] data) {
        System.out.println("TV (HDMI) displaying content: " + new String(data));
    }
}
