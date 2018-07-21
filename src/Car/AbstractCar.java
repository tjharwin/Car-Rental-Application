package Car;

/**
 * <h1>Car.AbstractCar</h1>
 * This is an abstract super class of Car.Car featuring methods used by all Car.Car subclasses.
 *
 * @author  Thomas Harwin
 * @version 1.0
 * @since   2018-02-12
 */

public abstract class AbstractCar implements Car {

    private final RegistrationNumber regNo;
    private final int fuelCapacity;
    private int fuelAmount;
    private boolean isAvailable;


    public AbstractCar(int fuelCapacity) {

        regNo = RegistrationNumber.getInstance();
        this.fuelCapacity = fuelCapacity;
        fuelAmount = fuelCapacity; //Cars are generated with a full tank of fuel
        isAvailable = true; //Cars are generated as being available to rent
    }

    public RegistrationNumber getRegNo() {

        return regNo;
    }

    public int getFuelCapacity() {

        return fuelCapacity;
    }

    public int getFuelAmount() {

        return fuelAmount;
    }

    public boolean isAvailable() {

        return isAvailable;
    }

    public boolean isTankFull() {

        if (fuelAmount == fuelCapacity) {

            return true;
        }

        else {

            return false;
        }
    }

    public boolean isSmallCar(Car car) {

        if (car instanceof SmallCar) {

            return true;
        }
        else {

            return false;
        }
    }

    public void setFuelAmount(int fuelAmount) {

        this.fuelAmount = fuelAmount;
    }

    public void setIsAvailable(boolean isAvailable) {

        this.isAvailable = isAvailable;
    }

    /**
     * A method for adding fuel to a car.
     *
     * @param fuelToAdd The amount of fuel (in litres) to be added to a car's fuel tank.
     * @return     An integer representing the amount of fuel (in litres) which is added to the car.
     */

    public int addFuel(int fuelToAdd) {

        //If the amount given is less than or equal to 0, an exception is thrown
        if (fuelToAdd <= 0) {
            throw new IllegalArgumentException("The amount specified must be a value greater than zero.");
        }

        //If the car is currently not being rented, no fuel is added to the tank
        else if (isAvailable()) {
            return 0;
        }

        //If the car's tank is at full capacity, no fuel is added to the tank
        else if (isTankFull()){
            return 0;
        }

        //If the amount given would take the fuel amount over the tank's capacity, only the amount required to fill the
        // tank is added

        else if ((fuelAmount + fuelToAdd) > fuelCapacity) {
            int fuelToFill = (fuelCapacity - fuelAmount);
            fuelAmount = fuelCapacity;
            return fuelToFill;
        }

        //If the amount given is less than or equal to the amount needed to fill the car
        else {
            fuelAmount += fuelToAdd;
            return fuelToAdd;
        }
    }
}