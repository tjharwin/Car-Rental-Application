package Licence;

/**
 * <h1>Licence.Name</h1>
 * This is a class for objects of Name type which is used to store the first name and surname of clients using the car
 * rental company.
 *
 * @author  Thomas Harwin
 * @version 1.0
 * @since   2018-02-12
 */

public class Name {

    private final String firstName;
    private final String surname;


    public Name(String firstName, String surname) {

        if ((firstName == null) || (surname == null)) {
            throw new NullPointerException("Name fields cannot be null. You must specify a first name and surname.");
        }

        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getSurname() {

        return surname;
    }

    @Override
    public String toString() {

        return firstName + " " + surname;
    }

    /**
     * A method for getting both the initial of the first name and surname and concatenating them into a String.
     *
     * @return     A String of two upper-case letters.
     */

    public String getInitials() {

        char firstInitial = firstName.charAt(0);
        char secondInitial = surname.charAt(0);
        return (firstInitial + "" + secondInitial).toUpperCase();
    }
}