package Car;

import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractCarTest {

    public final SmallCar testCar = new SmallCar();
    public final LargeCar testCar2 = new LargeCar();

    @Test
    public void testGetRegNo() {

        //Cannot test what is expected as reg numbers are randomly generated so we can only check the format is correct

        //Checks the reg number is 5 characters long
        assertTrue(testCar.getRegNo().toString().length() == 5);

        //Checks the character at index 0 is a letter
        assertTrue(testCar.getRegNo().toString().charAt(0) >= 'A');
        assertTrue(testCar.getRegNo().toString().charAt(0) <= 'Z');

        //Checks the characters at index 1-4 are numbers
        for (int i = 0; i > 5; i++) {
            assertTrue(testCar.getRegNo().toString().charAt(i) >= '0');
            assertTrue(testCar.getRegNo().toString().charAt(i) <= '9');
        }
    }

    @Test
    public void testGetFuelCapacity() {

        assertEquals(49, testCar.getFuelCapacity());
    }

    @Test
    public void testGetFuelAmount() {

        assertEquals(49, testCar.getFuelAmount());
    }

    @Test
    public void testIsAvailable() {

        assertTrue(testCar.isAvailable());
    }

    @Test
    public void testIsTankFull() {

        assertTrue(testCar.isTankFull());
    }

    @Test
    public void testIsSmallCar() {

        assertTrue(testCar.isSmallCar(testCar));
        assertFalse(testCar2.isSmallCar(testCar2));
    }

    @Test
    public void testSetFuelAmount() {

        testCar.setFuelAmount(25);
        assertTrue(testCar.getFuelAmount() == 25);
    }

    @Test
    public void testSetIsAvailable() {

        testCar.setIsAvailable(false);
        assertTrue(testCar.isAvailable() == false);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddNegativeFuel() {

        //Tests if an exception is thrown if a negative value or if 0 is passed in the parameter for the add fuel method
        testCar.addFuel(-5);
        testCar.addFuel(0);
    }

    @Test
    public void testAddFuel() {

        //Initialise the fuel amount as 0
        testCar.setFuelAmount(0);

        //Tests adding fuel to an available car which is not allowed
        testCar.setIsAvailable(true);
        assertEquals(0, testCar.addFuel(5));

        //Tests adding fuel to a full tank
        testCar.setFuelAmount(49);
        testCar.setIsAvailable(false);
        assertEquals(0, testCar.addFuel(5));

        //Tests if the amount given would take the fuel amount over the tank's capacity then only the amount required to
        //fill the tank is added and returned
        testCar.setFuelAmount(48);
        assertEquals(1, testCar.addFuel(2));

        //Tests if the amount added is less than the amount needed to fill the tank then the amount added is returned
        testCar.setFuelAmount(45);
        assertEquals(3, testCar.addFuel(3));

    }
}