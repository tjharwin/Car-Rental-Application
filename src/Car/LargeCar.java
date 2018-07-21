package Car;

/**
 * <h1>Car.LargeCar</h1>
 * This is an subclass of Car.AbstractCar which inherits its methods. A large car consumes fuel at the rate of 10
 * Kilometres/Litre for the first 50 Kilometres of a journey and then at the rate of 15 Kilometres/Litre for the
 * remainder of the journey.
 *
 * @author  Thomas Harwin
 * @version 1.0
 * @since   2018-02-12
 */

public final class LargeCar extends AbstractCar {

    private static final int FUEL_CAPACITY = 60;
    private static final int FIRST_KM_PER_LITRE = 10;
    private static final int LAST_KM_PER_LITRE = 15;


    public LargeCar (){

        super(FUEL_CAPACITY);
    }

    /**
     * A method for driving a large car. A large car consumes fuel at one set rate for the first 50km of a journey and
     * then at a second set rate for the remainder of the journey.
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

            int fuelUsed;

            //If the journey is 50km or less
            if (kilometres <= 50) {
                fuelUsed = kilometres / FIRST_KM_PER_LITRE;
            }

            //If the journey is over 50km
            else {
                int excessKilometres = kilometres - 50;
                fuelUsed = (50 / FIRST_KM_PER_LITRE) + (excessKilometres / LAST_KM_PER_LITRE);
            }

            //If the distance to drive exceeds the amount of fuel in the car's tank, the drive does not happen.
            if (fuelUsed > super.getFuelAmount()) {
                return 0;
            }

            else {
                super.setFuelAmount(super.getFuelAmount() - fuelUsed);
                return fuelUsed;
            }
        }

        else return 0;
    }
}