package Licence;

import java.util.Date;
import java.util.Calendar;

/**
 * <h1>Licence.DrivingLicence</h1>
 * This is a class for objects of Licence.DrivingLicence type. A driving licence object stores details about the driver
 * including their full name, date of birth, licence number, the date their licence was issued and whether or not they
 * hold a full licence.
 *
 * @author  Thomas Harwin
 * @version 1.0
 * @since   2018-02-12
 */

public final class DrivingLicence {

    private final Name name;
    private final Date dateOfBirth;
    private final LicenceNumber licenceNo;
    private final Date dateOfIssue;
    private final boolean isFullLicence;


    public DrivingLicence(Name name, Date dateOfBirth, LicenceNumber licenceNo, Date dateOfIssue,
                          boolean isFullLicence) {

        Calendar today = Calendar.getInstance();
        Date current = today.getTime();
        if (dateOfBirth.after(current) || dateOfIssue.after(current)) {

            throw new IllegalArgumentException("You cannot specify a date in the future.");
        }

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.licenceNo = licenceNo;
        this.dateOfIssue = dateOfIssue;
        this.isFullLicence = isFullLicence;
    }

    public Name getName() {

        return name;
    }

    public Date getDateOfBirth() {

        return dateOfBirth;
    }

    public LicenceNumber getLicenceNo() {

        return licenceNo;
    }

    public Date getDateOfIssue() {

        return dateOfIssue;
    }

    public boolean isFullLicence() {

        return isFullLicence;
    }

    @Override
    public String toString() {

        return "DrivingLicence{" +
                "name=" + name +
                ", licenceNo=" + licenceNo +
                ", isFullLicence=" + isFullLicence +
                '}';
    }

    /**
     * A method for calculating the age of the driver based on their date of birth or the age of a driving licence based
     * on the issue date.
     *
     * @param date A date representing the driver's date of birth or the issue date of a licence.
     * @return An integer representing a driver's age in years.
     * @author Farhan Munir, 2011 (https://stackoverflow.com/questions/1116123/how-do-i-calculate-someones-age-in-java)
     */

    public int getAge(Date date) {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(date);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than today's date (after 2 days adjustment of leap year) then decrement age one year
        if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {

            age--;
        }

        // If birth date and today's date are of same month and birth day of month is greater than today's day of
        // month then decrement age
        else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {

            age--;
        }

        return age;
    }
}