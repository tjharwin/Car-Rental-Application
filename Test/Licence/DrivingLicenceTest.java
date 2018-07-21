package Licence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.Calendar;
import static org.junit.Assert.*;

public class DrivingLicenceTest {

    private DrivingLicence testDrivingLicence;
    private final String firstName = "John";
    private final String surname = "Smith";
    private Name testName;
    private Date testDOB;
    private Date testDOI;
    private LicenceNumber testLicenceNo;
    private Boolean testIsFullLicence;

    @Before
    public void setUp() {

        testName = new Name(firstName, surname);

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

        testDrivingLicence = new DrivingLicence(testName, testDOB, testLicenceNo, testDOI, testIsFullLicence);
    }

    @After
    public void tearDown() {

    }

    @Test (expected = IllegalArgumentException.class)
    public void testDrivingLicence() {

        //Testing the driving licence constructor with a DOB in the future to ensure the exception is thrown

        //Setting up a date of birth object with date as 01/01/2025
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JANUARY, 1);
        testDOB = cal.getTime();

        testDrivingLicence = new DrivingLicence(testName, testDOB, testLicenceNo, testDOI, testIsFullLicence);
    }

    @Test
    public void testGetName() {

        assertEquals(testName, testDrivingLicence.getName());
    }

    @Test
    public void testGetDateOfBirth() {

        assertEquals(testDOB, testDrivingLicence.getDateOfBirth());
    }

    @Test
    public void testGetLicenceNo() {

        assertEquals(testLicenceNo, testDrivingLicence.getLicenceNo());
    }

    @Test
    public void testGetDateOfIssue() {

        assertEquals(testDOI, testDrivingLicence.getDateOfIssue());
    }

    @Test
    public void testIsFullLicence() {

        assertEquals(testIsFullLicence, testDrivingLicence.isFullLicence());
    }

    @Test
    public void testToString() {

        assertEquals("DrivingLicence{" + "name=" + testName + ", licenceNo=" + testLicenceNo + ", isFullLicence="
                + testIsFullLicence + '}', testDrivingLicence.toString());
    }

    @Test
    public void testGetAge() {

        assertEquals(23, testDrivingLicence.getAge(testDOB));
        assertEquals(3, testDrivingLicence.getAge(testDOI));
    }

}