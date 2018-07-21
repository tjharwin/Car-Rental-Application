package Car;

/**
 * <h1>Car.SmallCar</h1>
 * This is an subclass of Car.AbstractCar which inherits its methods. A small car consumes fuel at the rate of 20
 * Kilometres/Litre.
 *
 * @author  Thomas Harwin
 * @version 1.0
 * @since   2018-02-12
 */

public final class SmallCar extends AbstractCar {

    private static final int FUEL_CAPACITY = 49;
    private static final int KM_PER_LITRE = 20;


    public SmallCar () {

        super(FUEL_CAPACITY);
    }

    /**
     * A method for driving a small car. A small car consumes fuel at the same rate for the duration of a journey.
     *
     * @param kilometres The distance (in kilometres) that a car is to drive.
     * @return     An integer representing the amount of fuel (in litres) that a car has consumed whilst driving.
     *             If the car is not driven then 0 is returned.
     */

    public int drive(int kilometres) {

        //If the amount given is less than or equal to 0, an exception is thrown
        if (kilometres <= 0) {
            throw new IllegalArgumentException("The amount specified must be a value greater than zero.");
        }

        else if (!super.isAvailable()) {

            int fuelUsed = kilometres / KM_PER_LITRE;

            //If the distance to drive exceeds the amount of fuel in the car's tank, the drive does not happen
            if (fuelUsed > super.getFuelAmount()) {
                return 0;
            } else {
                super.setFuelAmount(super.getFuelAmount() - fuelUsed);
                return fuelUsed;
            }
        }

        else return 0;
    }
}
