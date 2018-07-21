package Licence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class NameTest {

    private final String firstName = "John";
    private final String surname = "Smith";
    private Name testName;

    @Before
    public void setUp() {

        testName = new Name(firstName, surname);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testGetFirstName() {

        assertEquals(firstName, testName.getFirstName());
    }

    @Test
    public void testGetSurname() {

        assertEquals(surname, testName.getSurname());
    }

    @Test (expected = NullPointerException.class)
    public void testNullName() {

        final Name nullFirstName = new Name(null, surname);
        final Name nullSurname = new Name(firstName, null);
        final Name nullFullName = new Name(null, null);

    }

    @Test
    public void testGetInitials() {

        assertEquals("JS", testName.getInitials());

        //Test name with lower case initials
        Name testName2 = new Name("john", "smith");
        assertEquals("JS", testName2.getInitials());
    }

    @Test
    public void testToString() {

        assertEquals(firstName + " " + surname, testName.toString());
    }
}
