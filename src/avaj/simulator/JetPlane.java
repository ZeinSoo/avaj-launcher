package avaj.simulator;

public class JetPlane extends Aircraft{
    public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        switch (this.weatherTower.getWeather(this.coordinates)) {
            case "SUN" -> {
                System.out.printf("%s: Clear skies! Let's put the pedal to the metal.\n", this);
                this.coordinates.move(0, 10, 2);
            }
            case "RAIN" -> {
                System.out.printf("%s: It's raining! Better watch out for lightning!\n", this);
                this.coordinates.move(0, 5, 0);
            }
            case "FOG" -> {
                System.out.printf("%s: Visibility is terrible. Time to trust the instruments!\n", this);
                this.coordinates.move(0, 1, 0);
            }
            case "SNOW" -> {
                System.out.printf("%s: Winter is coming! This flight is getting chilly.\n", this);
                this.coordinates.move(0, 0, -7);
            }
        }

        if (this.coordinates.getHeight() <= 0) {
            System.out.printf("%s landing.", this);
            this.weatherTower.unregister(this);
        }
    }
}