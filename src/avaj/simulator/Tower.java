package avaj.simulator;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    Tower() {}

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        System.out.printf("Tower says: %s registered to weather tower.\n", p_flyable);
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        System.out.printf("Tower says: %s unregistered from weather tower.\n", p_flyable);
    }

    protected void conditionChanged() {
        List<Flyable> currentObservers = new ArrayList<>(observers);
        currentObservers.forEach(Flyable::updateConditions);
    }
}
