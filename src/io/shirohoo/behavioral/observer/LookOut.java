package io.shirohoo.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class LookOut {
    private final List<LittleRascals> friends;

    public LookOut() {
        this.friends = new ArrayList<>();
    }

    public void registerObserver(LittleRascals friend) {
        friends.add(friend);
    }

    public void notifyObservers(boolean teacherIsComing) {
        friends.forEach(friend -> friend.notify(teacherIsComing));
    }
}
