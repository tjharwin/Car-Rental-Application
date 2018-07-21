package Car;

/**
 * <h1>Car.Car Interface</h1>
 * This is an interface class for objects of Car.Car which features methods that all sub-classes of car must utilise.
 * This class also features a getInstance() factory method for generating instances of car.
 *
 * @author  Thomas Harwin
 * @version 1.0
 * @since   2018-02-12
 */

public interface Car {

    public static Car getInstance(String type) {

        type.toLowerCase();

        switch (type) {

            case "small":
                return new SmallCar();
            case "large":
                return new LargeCar();
            default:
                throw new IllegalArgumentException("You must enter either small or large as a type");
        }
    }

    public RegistrationNumber getRegNo();

    public int getFuelCapacity();

    public int getFuelAmount();

    public boolean isAvailable();

    public boolean isTankFull();

    public boolean isSmallCar(Car car);

    public void setFuelAmount(int fuelAmount);

    public void setIsAvailable(boolean isAvailable);

    public int addFuel(int fuelToAdd);

}
