package RentalApplication;

import Car.*;
import Licence.*;

import java.util.Map;
import java.util.HashMap;

/**
 * <h1>RentalApplication</h1>
 * This class generates the cars which belong to a car rental company and keeps a record of which cars are currently
 * being rented and to which drivers they are rented to. It is also responsible for issuing cars to drivers and for
 * terminating rentals once a car has been returned.
 *
 * @author  Thomas Harwin
 * @version 1.0
 * @since   2018-02-12
 */

public class RentalApplication {

    private static RentalApplication RENTAL_APPLICATION = null;
    private static Map<RegistrationNumber, Car> FLEET = new HashMap<>();
    private static Map<DrivingLicence, Car> RENTED_CARS = new HashMap<>();
    private static final int SMALL_CARS = 30;
    private static final int LARGE_CARS = 20;

    /**
     * The constructor for Rental application generates a fleet of cars when an instance of RentalApplication is
     * instantiated. The constructor is created by a singleton getInstance() method to ensure that only one instance of
     * RentalApplication can exist at any time.
     */

    private RentalApplication() {

        //Generates all the small cars
        for (int i = 0; i < SMALL_CARS; i++) {

            final Car smallCar = Car.getInstance("small");
            RegistrationNumber regNo = smallCar.getRegNo();
            addFleetCar(regNo, smallCar);
        }

        //Generates all the large cars
        for (int i = 0; i < LARGE_CARS; i++) {

            final Car largeCar = Car.getInstance("large");
            RegistrationNumber regNo = largeCar.getRegNo();
            addFleetCar(regNo, largeCar);
        }
    }

    public static RentalApplication getInstance() {

        //Singleton pattern so only one instance of RentalApplication can be created

        if (RENTAL_APPLICATION == null) {
            RENTAL_APPLICATION = new RentalApplication();
        }
        return RENTAL_APPLICATION;
    }

    /**
     * A method for returning the amount of available cars of a given type (Small car or Large car).
     *
     * @param isSmallCar A boolean value of whether the car is small or not. If the car is not a small car, it must be a
     *                large car.
     * @return     An integer value of the number of cars of the specified type that are available to rent.
     */

    public int availableCars(Boolean isSmallCar) {

        int count = 0;
        for (Car car : FLEET.values()) {

            if ((car.isSmallCar(car) == isSmallCar) && (car.isAvailable())) {

                count++;
            }
        }

        return count;
    }

    /**
     * A method for returning a collection of cars in the rental agencies fleet.
     *
     * @return     A collection of cars being rented.
     */

    public Map<RegistrationNumber, Car> getFleet() {

        return FLEET;
    }

    /**
     * A method for returning a collection of cars which are currently being rented.
     *
     * @return     A collection of cars being rented (if any).
     */

    public Map<DrivingLicence, Car> getRentedCars() {

        return RENTED_CARS;
    }

    /**
     * A method for adding a car to the fleet of cars.
     *
     * @param regNo A RegistrationNumber object.
     * @param car A Car object.
     */

    public void addFleetCar(RegistrationNumber regNo, Car car) {

        FLEET.put(regNo, car);
    }

    /**
     * A method for returning the car a driver is currently renting (if any).
     *
     * @param licence A driving licence object belonging to the driver who is being queried.
     * @return     A car object which is currently being rented to the driver specified (if any). Returns null if the
     * driving licence specified is not currently associated with a rental car.
     */

    public Car getCar(DrivingLicence licence) {

        if (RENTED_CARS.containsKey(licence)) {

            return RENTED_CARS.get(licence);
        }

        else return null;
    }

    /**
     * A method for issuing a car to a driver. This method determines  whether  the  person  is  eligible  to  rent  a
     * car  of  the specified type and, if there is a car available, issues a car of the specified type.
     *
     *
     * @param licence A driving licence object belonging to the driver who is renting a car.
     * @param smallCar A boolean value of whether the car which is to be rented is small or not. If the car is not a
     *                   small car, it must be a large car.
     * @return     A boolean stating whether or not a car has been issued. If a car is issued, the method will return
     * true.
     */

    public boolean issueCar(DrivingLicence licence, Boolean smallCar) {

        boolean carIssued = false;

        //Checks the licence is a full licence and that the licence is not currently associated with another rental car
        if ((licence.isFullLicence()) && (!RENTED_CARS.containsKey(licence))) {

            //Checks the licence meets the criteria for renting the type of car specified:
            //Small Car - Driver must be 21 years old and held their licence for 1 year
            //Large Car - Driver must be 25 years old and held their licence for 5 years
            if (((smallCar) && (licence.getAge(licence.getDateOfBirth()) >= 21) &&
                    (licence.getAge(licence.getDateOfIssue()) >= 1)) ||
                    ((!smallCar) && (licence.getAge(licence.getDateOfBirth()) >= 25) &&
                            (licence.getAge(licence.getDateOfIssue()) >= 5))) {

                for (Car car : FLEET.values()) {

                    //Checks if there is a car available in the fleet with the specified size
                    if ((car.isAvailable()) && (car.isSmallCar(car) == smallCar)) {

                        car.setIsAvailable(false); //Sets the car to be rented as being unavailable
                        car.setFuelAmount(car.getFuelCapacity()); //Fills the car's fuel tank when issuing
                        RENTED_CARS.put(licence, car);
                        carIssued = true;
                    }
                }
            }
        }

        return carIssued;
    }

    /**
     * A method for terminating the rental contract associated with the given driving licence when a car is returned to
     * the car rental company. A driver is liable for the cost of filling the tank on returning the car and so this
     * method returns the amount of litres required to fill the tank. Upon executing this method, a rented car will then
     * become available to rent again.
     *
     * @param licence A driving licence object belonging to the driver who is renting the car to be returned.
     * @return     An integer representing the amount of fuel (in litres) required to fill the car's tank.
     */

    public int terminateRental(DrivingLicence licence) {

        if (RENTED_CARS.containsKey(licence)) {

            int fuelReqToFill = ((RENTED_CARS.get(licence).getFuelCapacity()) -
                    (RENTED_CARS.get(licence).getFuelAmount()));
            RENTED_CARS.get(licence).setIsAvailable(true);
            RENTED_CARS.remove(licence);
            return fuelReqToFill;
        }

        return 0;
    }
}