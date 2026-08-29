package avaj.simulator;

import avaj.simulator.exceptions.IllegalAircraftType;

public class AircraftFactory {
    static int id = 0;
    private AircraftFactory() {}

    static public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        id++;

        Flyable flyable;

        switch (p_type) {
            case "Helicopter" -> flyable = new Helicopter(id, p_name, p_coordinates);
            case "JetPlane" -> flyable = new JetPlane(id, p_name, p_coordinates);
            case "Balloon" -> flyable = new Balloon(id, p_name, p_coordinates);
            default -> throw new IllegalAircraftType("Unknown aircraft type: " + p_type);
        }
        return flyable;
    }
}
