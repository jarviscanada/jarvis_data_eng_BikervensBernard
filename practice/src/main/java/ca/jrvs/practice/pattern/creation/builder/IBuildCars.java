package ca.jrvs.practice.pattern.creation.builder;

/**
 * Builder interface defines all possible ways to configure a product.
 */
public interface IBuildCars{
    public void setCarType(String type);
    public void setSeats(int seats);
    public void setEngine(String engine);
    public void setTransmission(String transmission);
    public void setTripComputer(String tripComputer);
    public void setGPSNavigator(String gpsNavigator);
}