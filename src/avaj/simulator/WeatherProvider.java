package avaj.simulator;

public class WeatherProvider {
    private static WeatherProvider instance = new WeatherProvider();
    private final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getInstance() {
        if (instance == null)
            instance = new WeatherProvider();
        return instance;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int value = Math.abs(
                coordinates.getLongitude() * 35
                + coordinates.getLatitude() * 19
                + coordinates.getHeight() * 24
        ) % 4;

        return switch (value) {
            case 0 -> "SUN";
            case 1 -> "RAIN";
            case 2 -> "FOG";
            default -> "SNOW";
        };
    }
}
