package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Subject {
    private String channelName;
    private List<Observer> observers = new ArrayList<>();

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void uploadVideo(String title) {
        notifyObservers("New video on " + channelName + ": " + title);
    }
}
