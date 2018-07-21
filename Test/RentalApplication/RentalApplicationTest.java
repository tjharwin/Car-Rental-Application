package RentalApplication;
import Car.*;
import Licence.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class RentalApplicationTest {


    private final RentalApplication testRentalApplication = RentalApplication.getInstance();
    private final String firstName = "John";
    private final String surname = "Smith";
    private Name testName = new Name(firstName, surname);
    private Date testDOB;
    private Date testDOI;
    private LicenceNumber testLicenceNo;
    private Boolean testIsFullLicence;
    private DrivingLicence testDrivingLicence;
    private Car testSmallCar = Car.getInstance("small");
    private Car testLargeCar = Car.getInstance("large");

    @Before
    public void setUp() {

        //Setting up a date of birth object with date as 01/01/1995
        Calendar cal = Calendar.getInstance();
        cal.set(1995, Calendar.JANUARY, 1);
        testDOB = cal.getTime();

        //Setting up a date of issue object with date as 01/01/2015
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2015, Calendar.JANUARY, 1);
        testDOI = cal2.getTime();

        testLicenceNo = LicenceNumber.getInstance(testName, testDOI);

        testIsFullLicence = true;

        testDrivingLicence = new DrivingLicence(testName, testDOB, testLicenceNo, testDOI,
                testIsFullLicence);
    }

    @After
    public void tearDown() {

        testRentalApplication.terminateRental(testDrivingLicence);

    }

    @Test
    public void testAvailableCars() {

        assertEquals(30, testRentalApplication.availableCars(true));
        assertEquals(20, testRentalApplication.availableCars(false));
    }

    @Test
    public void testGetFleet() {

        //Asserting the fleet size equals 50

        assertEquals(50, testRentalApplication.getFleet().size());

    }

    @Test
    public void testGetRentedCars() {

        //Add 1 car to the testRentalApplication's fleet and then tests the size of the fleet is 1.
        testRentalApplication.issueCar(testDrivingLicence, true);
        assertEquals(1, testRentalApplication.getRentedCars().size());
    }

    @Test
    public void addFleetCar() {

        //Checks fleet size then adds a car to the fleet and checks fleet size has increased by 1
        assertEquals(50, testRentalApplication.getFleet().size());
        RegistrationNumber testRegNo = testSmallCar.getRegNo();
        testRentalApplication.addFleetCar(testRegNo, testSmallCar);
        assertEquals(51, testRentalApplication.getFleet().size());

    }

    @Test
    public void testGetCar() {

        //Issues a small car to a driving licence, then uses the get car method and asserts the car is a small car.
        testRentalApplication.issueCar(testDrivingLicence, true);
        Car issuedCar = testRentalApplication.getCar(testDrivingLicence);
        assertTrue(issuedCar.isSmallCar(issuedCar));

    }

    @Test
    public void testIssueCar()  {

        //Tests a small car can be issued to a driving licence of a driver aged 23 with a licence issued 3 years ago
        assertTrue(testRentalApplication.issueCar(testDrivingLicence, true));

        //Tests the car just issued is a small car
        Car issuedCar = testRentalApplication.getCar(testDrivingLicence);
        assertTrue(issuedCar.isSmallCar(issuedCar));

        //Tests that a large car cannot be issued to a driving licence of a driver aged 23
        assertFalse(testRentalApplication.issueCar(testDrivingLicence, false));

        //Setting up a driving licence object with date of birth as 01/01/1992 and date of issue as 01/01/2015
        Calendar cal = Calendar.getInstance();
        cal.set(1992, Calendar.JANUARY, 1);
        testDOB = cal.getTime();
        DrivingLicence testDrivingLicence2 = new DrivingLicence(testName, testDOB, testLicenceNo, testDOI,
                testIsFullLicence);

        //Tests that a driving licence aged 26 but only held for 3 years cannot rent a large car
        assertFalse(testRentalApplication.issueCar(testDrivingLicence2, false));
    }

    @Test
    public void testTerminateRental() {

        //Issues a car to a driving licence and checks it has been issued
        testRentalApplication.issueCar(testDrivingLicence, true);
        assertTrue(testRentalApplication.getCar(testDrivingLicence) instanceof SmallCar);

        //Drives the car rented
        Car carToTerminate = testRentalApplication.getCar(testDrivingLicence);
        ((SmallCar)carToTerminate).drive(20);

        //Terminates a rental from a driving licence and checks it has been terminated by making sure the method doesn't
        //return 0 as the car's tank will not be full after driving
        assertTrue(1 == testRentalApplication.terminateRental(testDrivingLicence));

        //Terminates a rental (again) from a driving licence which is no longer associated with a car to ensure the
        //method returns 0
        assertTrue(0 == testRentalApplication.terminateRental(testDrivingLicence));
    }
}