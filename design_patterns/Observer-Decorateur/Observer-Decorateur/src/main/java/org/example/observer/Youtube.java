package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class Youtube {
    private List<Channel> channels = new ArrayList<>();

    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    public void removeChannel(Channel channel) {
        channels.remove(channel);
    }
}
