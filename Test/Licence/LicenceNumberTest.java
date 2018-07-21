package Licence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.Calendar;
import static org.junit.Assert.*;

public class LicenceNumberTest {

    private final String firstName = "John";
    private final String surname = "Smith";
    private Name testName;
    private Date testDate;

    @Before
    public void setUp() {

        testName = new Name(firstName, surname);

        //Setting up a date object with date as 01/01/2015
        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.JANUARY, 1);
        testDate = cal.getTime();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testGetInstance() {

        LicenceNumber testLicenceNo = LicenceNumber.getInstance(testName, testDate);
        LicenceNumber testLicenceNo2 = LicenceNumber.getInstance(testName, testDate);
        System.out.println(testLicenceNo);
        System.out.println(testLicenceNo2);

        //Tests that the licence number generated is in the correct format
        assertEquals((testLicenceNo.toString().substring(0,8)), "JS-2015-");
        assertTrue (testLicenceNo.toString().length() == 14);

        //Tests that the two generated licence numbers are not the same despite using the same name and DOB
        assertNotEquals(testLicenceNo, testLicenceNo2);

    }

    @Test
    public void testGetYear() {

    assertEquals(2015, LicenceNumber.getYear(testDate));

    }

    @Test
    public void testGenerateNumber() {

        //Generates 100 numbers and checks they are within the correct range
        for (int i = 0; i < 100; i++) {
            int testLicenceNo = LicenceNumber.generateNumber();
            assertTrue(testLicenceNo >= 100000 && testLicenceNo <= 999999);
        }
    }
}
