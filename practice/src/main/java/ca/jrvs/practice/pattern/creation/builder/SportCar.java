package ca.jrvs.practice.pattern.creation.builder;

public class SportCar{
    private final String carType;
    private final int seats;
    private final String engine;
    private final String transmission;
    private final String tripComputer;
    private final String gpsNavigator;
    private double fuel = 0;

    public SportCar(String type, int seats, String engine, String transmission, String tripComputer, String gpsNavigator) {
        this.carType = type;
        this.seats = seats;
        this.engine = engine;
        this.transmission = transmission;
        this.tripComputer = tripComputer;
        this.gpsNavigator = gpsNavigator;
    }

    public String getCarType() {
        return carType;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getSeats() {
        return seats;
    }

    public String getEngine() {
        return engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getTripComputer() {
        return tripComputer;
    }

    public String getGpsNavigator() {
        return gpsNavigator;
    }


    public String print() {
        String info = "";
        info += "Type of car: " + carType + "\n";
        info += "Count of seats: " + seats + "\n";
        info += "Engine: volume - " + engine+ "\n";
        info += "Transmission: " + transmission + "\n";
        if (this.tripComputer != null) {
            info += "Trip Computer: Functional" + "\n";
        } else {
            info += "Trip Computer: N/A" + "\n";
        }
        if (this.gpsNavigator != null) {
            info += "GPS Navigator: Functional" + "\n";
        } else {
            info += "GPS Navigator: N/A" + "\n";
        }
        return info;
    }
}
