package Car;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    @Test (expected = IllegalArgumentException.class)
    public void testGetInstanceException() {

        Car testCar = Car.getInstance("popcorn");
    }

    @Test
    public void testGetInstanceSmall() {

        //Gets an instance of small car and checks it is a small car
        Car testCar = Car.getInstance("small");
        assertTrue(testCar.isSmallCar(testCar));
    }

    @Test
    public void testGetInstanceLarge() {

        //Gets an instance of large car and checks it is a large car
        Car testCar = Car.getInstance("large");
        assertTrue(!testCar.isSmallCar(testCar));
    }
}