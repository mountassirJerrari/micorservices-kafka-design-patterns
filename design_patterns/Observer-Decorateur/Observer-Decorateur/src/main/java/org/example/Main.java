package org.example;

import org.example.observer.*;
import org.example.decorator.*;

public class Main {
    public static void main(String[] args) {
        Youtube youtube = new Youtube();
        Channel channel1 = new Channel("Tech Daily");
        Channel channel2 = new Channel("Cooking 101");

        youtube.addChannel(channel1);
        youtube.addChannel(channel2);

        Subscriber sub1 = new Subscriber("Alice");
        Subscriber sub2 = new Subscriber("Bob");
        Subscriber sub3 = new Subscriber("Charlie");

        channel1.subscribe(sub1);
        channel1.subscribe(sub2);
        channel2.subscribe(sub2);
        channel2.subscribe(sub3);

        System.out.println("--- First Uploads ---");
        channel1.uploadVideo("Observer Pattern Explained");
        channel2.uploadVideo("How to make Pizza");

        System.out.println("\n--- Unsubscribing Bob from Tech Daily ---");
        channel1.unsubscribe(sub2);

        System.out.println("--- Second Uploads ---");
        channel1.uploadVideo("Java Streams Tutorial");
        channel2.uploadVideo("Pasta Recipe");

        System.out.println("\n--- Decorator Pattern Demo ---");
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        beverage = new Milk(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        beverage = new Chocolate(beverage);
        System.out.println(beverage.getDescription() + " $" + beverage.cost());
    }
}