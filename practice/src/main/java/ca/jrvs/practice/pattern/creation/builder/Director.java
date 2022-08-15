package ca.jrvs.practice.pattern.creation.builder;

public class Director{
    public void constructSportsCar(IBuildCars IBuildCars) {
        IBuildCars.setCarType("SPORTS_CAR");
        IBuildCars.setSeats(2);
        IBuildCars.setEngine("3.0");
        IBuildCars.setTransmission("SEMI_AUTOMATIC");
        IBuildCars.setTripComputer("new TripComputer()");
        IBuildCars.setGPSNavigator("new GPSNavigator()");
    }

    public void constructCityCar(IBuildCars IBuildCars) {
        IBuildCars.setCarType("CITY_CAR");
        IBuildCars.setSeats(2);
        IBuildCars.setEngine("1.2");
        IBuildCars.setTransmission("AUTOMATIC");
        IBuildCars.setTripComputer("new TripComputer()");
        IBuildCars.setGPSNavigator("new GPSNavigator()");
    }

    public void constructSUV(IBuildCars IBuildCars) {
        IBuildCars.setCarType("SUV");
        IBuildCars.setSeats(4);
        IBuildCars.setEngine("56");
        IBuildCars.setTransmission("MANUAL");
        IBuildCars.setGPSNavigator("new GPSNavigator()");
    }

    public static void main(String[] args) {

        // client knows better which builder to use to get a specific product.
        SportCarIBuildCars builder = new SportCarIBuildCars();
        ManualCarIBuildCars manualBuilder = new ManualCarIBuildCars();

        new Director().constructSportsCar(builder);// Director may know several building recipes.
        new Director().constructCityCar(manualBuilder);// Director may know several building recipes.

        // Product is often retrieved from a builder object, since
        // Director is not aware and not dependent on concrete builders and products.
        SportCar sportCar = builder.getResult();
        ManualCar carManual = manualBuilder.getResult();

        System.out.println(sportCar.print());
        System.out.println(carManual.print());
    }
}
