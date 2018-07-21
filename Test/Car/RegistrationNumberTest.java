package Car;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegistrationNumberTest {


    @Test
    public void testGetInstance() {

        RegistrationNumber testRegNo = RegistrationNumber.getInstance();
        RegistrationNumber testRegNo2 = RegistrationNumber.getInstance();

        //Tests that the two generated registration numbers are not the same to ensure uniqueness
        assertNotEquals(testRegNo, testRegNo2);
    }

    @Test
    public void testGenerateCharacter() {

        //Generates 100 characters and checks they are between A and Z
        for (int i = 0; i < 100; i++) {
            char testChar = RegistrationNumber.generateCharacter();
            assertTrue(testChar >= 'A');
            assertTrue(testChar <= 'Z');
        }
    }

    @Test
    public void testGenerateNumber() {

        //Generates 100 numbers and checks they are within the correct range
        for (int i = 0; i < 100; i++) {
            int testNo = RegistrationNumber.generateNumber();
            assertTrue(testNo >= 1000 && testNo <= 9999);
        }
    }

    @Test
    public void testGetFirstComponent() {

        //Creates an instance of RegistrationNumber then gets the first component and checks it is a char in range
        RegistrationNumber testRegNo = RegistrationNumber.getInstance();
        assertTrue(testRegNo.getFirstComponent() >= 'A');
        assertTrue(testRegNo.getFirstComponent() <= 'Z');
        }

    @Test
    public void testGetSecondComponent() {

        //Creates an instance of RegistrationNumber then gets the second component and checks it is a number in range
        RegistrationNumber testRegNo = RegistrationNumber.getInstance();
        assertTrue(testRegNo.getSecondComponent() >= 1000);
        assertTrue(testRegNo.getSecondComponent() <= 9999);
        }


    @Test
    public void testToString() {

        RegistrationNumber testRegNo = RegistrationNumber.getInstance();
        assertEquals(testRegNo.getFirstComponent() + "" + testRegNo.getSecondComponent(), testRegNo.toString());
    }
}