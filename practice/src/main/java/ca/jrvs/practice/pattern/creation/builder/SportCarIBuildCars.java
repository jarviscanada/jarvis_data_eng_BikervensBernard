package ca.jrvs.practice.pattern.creation.builder;

public class SportCarIBuildCars implements IBuildCars{
    private String type;
    private int seats;
    private String engine;
    private String transmission;
    private String tripComputer;
    private String gpsNavigator;

    public void setCarType(String type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(String tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(String gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public SportCar getResult() {
        return new SportCar(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}
