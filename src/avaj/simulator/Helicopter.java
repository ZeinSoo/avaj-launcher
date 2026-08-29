package avaj.simulator;

public class Helicopter extends Aircraft{
    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        switch (this.weatherTower.getWeather(this.coordinates)) {
            case "SUN" -> {
                System.out.printf("%s: Perfect weather for a little helicopter ride.\n", this);
                this.coordinates.move(10, 0, 2);
            }
            case "RAIN" -> {
                System.out.printf("%s: Rain? My rotor blades are not umbrellas!\n", this);
                this.coordinates.move(5, 0, 0);
            }
            case "FOG" -> {
                System.out.printf("%s: I can't see anything! Hopefully the ground is still down there!\n", this);
                this.coordinates.move(1, 0, 0);
            }
            case "SNOW" -> {
                System.out.printf("%s: My rotor is freezing! Someone bring me a heater!\n", this);
                this.coordinates.move(0, 0, -12);
            }
        }

        if (this.coordinates.getHeight() <= 0) {
            System.out.printf("%s landing.", this);
            this.weatherTower.unregister(this);
        }
    }
}
