package avaj.simulator;

import avaj.simulator.exceptions.InvalidScenarioFile;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int p_longitude, int p_latitude, int p_height) {
        if (p_height < 0)
            throw new InvalidScenarioFile("The height can't be negative");
        this.longitude = p_longitude;
        this.latitude = p_latitude;
        this.height = p_height;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }

    public void move(int longitude, int latitude, int height) {
        this.longitude += longitude;
        this.latitude += latitude;
        this.height += height;

        if (this.height < 0)
            this.height = 0;
        else if (this.height > 100)
            this.height = 100;
    }
}
