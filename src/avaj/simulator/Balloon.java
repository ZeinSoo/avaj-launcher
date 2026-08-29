package avaj.simulator;

public class Balloon extends Aircraft{
    public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        switch (this.weatherTower.getWeather(this.coordinates)) {
            case "SUN" -> {
                System.out.printf("%s: Let's enjoy the good weather and take some pics.\n", this);
                this.coordinates.move(2, 0, 2);
            }
            case "RAIN" -> {
                System.out.printf("%s: Rain?! My balloon is definitely not waterproof!\n", this);
                this.coordinates.move(0, 0, -5);
            }
            case "FOG" -> {
                System.out.printf("%s: I can't see a thing! Are we still flying?\n", this);
                this.coordinates.move(0, 0, -3);
            }
            case "SNOW" -> {
                System.out.printf("%s: This is getting cold... and my balloon hates it!\n", this);
                this.coordinates.move(0, 0, -15);
            }
        }

        if (this.coordinates.getHeight() <= 0) {
            System.out.printf("%s landing.\n", this);
            this.weatherTower.unregister(this);
        }
    }
}