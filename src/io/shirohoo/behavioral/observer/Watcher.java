package io.shirohoo.behavioral.observer;

public class Watcher {

    private final Friends listener;

    public Watcher(final Friends listener) {
        this.listener = listener;
    }

    public void publish(final String event){
        listener.listening(event);
    }

}
