package Car;

import org.junit.Test;
import static org.junit.Assert.*;

public class SmallCarTest {

    public final SmallCar testCar = new SmallCar();

    @Test (expected = IllegalArgumentException.class)
    public void testNegativeDrive() {

        //Tests if an exception is thrown if a negative value or if 0 is passed in the parameter for the drive method
        assertEquals(0, testCar.drive(-1));
        assertEquals(0, testCar.drive(0));
    }

    @Test
    public void testAvailableDrive() {

        //Tests if 0 is returned when the car is not being rented
        testCar.setIsAvailable(true);
        assertEquals(0, testCar.drive(5));
    }

    @Test
    public void testExceedingDrive() {

        //Tests if the amount of fuel required for the journey exceeds the amount of fuel in the tank
        testCar.setIsAvailable(false);
        assertEquals(0, testCar.drive(5000));
    }

    @Test
    public void testDrive() {

        //Tests if the amount of fuel required to drive is correct for the car's KPL (kilometres per litre)
        testCar.setIsAvailable(false);
        assertEquals(2, testCar.drive(40));
    }



}