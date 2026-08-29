package avaj.simulator;

import avaj.simulator.exceptions.IllegalAircraftType;
import avaj.simulator.exceptions.InvalidScenarioFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid arguments number, please add only 1 argument");
            return;
        }
        String filepath = args[0];

        PrintStream file;
        try {
            file = new PrintStream("simulation.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Cannot access simulation.txt");
            return;
        }
        System.setOut(file);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line = reader.readLine();

            if (line != null) {
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line);
                if (simulations < 0)
                    throw new NumberFormatException();

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ");
                    String type = parts[0];
                    String name = parts[1];
                    Coordinates coordinates = new Coordinates(
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4])
                    );

                    Flyable flyable = AircraftFactory.newAircraft(type, name, coordinates);
                    flyables.add(flyable);
                }

                for (Flyable flyable : flyables)
                    flyable.registerTower(weatherTower);

                for (int i = 1; i < simulations; i++)
                    weatherTower.changeWeather();
            } else {
                throw new InvalidScenarioFile("Invalid scenario file");
            }
            reader.close();
        } catch (InvalidScenarioFile | IllegalAircraftType e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("Invalid file");
        } catch (IOException e) {
            System.err.println("Something went wrong while reading the file");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid scenario");
        } catch (Exception e) {
            System.err.println("Something went wrong during the simulation !");
        }
        file.close();
    }
}