package Licence;

import java.util.*;

/**
 * <h1>Licence.LicenceNumber</h1>
 * This is a class for objects of Licence.LicenceNumber type which is used to generate a unique driving licence number
 * required for the creation of a Licence.DrivingLicence object.
 *
 * @author  Thomas Harwin
 * @version 1.0
 * @since   2018-02-12
 */

public final class LicenceNumber {

    private static final Map<String, LicenceNumber> LICENCE_NUMS = new HashMap<>();
    private final String stringRep;


    private LicenceNumber(String stringRep) {

        this.stringRep = stringRep;
    }

    /**
     * A method for getting an instance of a driving licence number. This method contains a flag which means the
     * method is only exited once a unique number has been created.
     *
     * @param name A name object containing the first name and surname
     * @param dateOfIssue A date object containing the date of issue of the licence
     * @return     A licence number in the format AA-1990-123456 (Initials - Year Of Issue - Serial No)
     */

    public static LicenceNumber getInstance(Name name, Date dateOfIssue) {

        LicenceNumber licenceNo = null;
        Boolean isUnique = false;

        while (!isUnique) {

            String initials = name.getInitials();
            int issueYear = getYear(dateOfIssue);
            int serialNo = generateNumber();
            String stringRep = initials + "-" + issueYear + "-" + serialNo;
            licenceNo = new LicenceNumber(stringRep);


            if (!LICENCE_NUMS.containsKey(stringRep)) {
                isUnique = true;
                LICENCE_NUMS.put(stringRep, licenceNo);
            }
        }
        return licenceNo;

    }

    @Override
    public String toString() {

        return stringRep;
    }

    /**
     * A method for extracting the year from a given date in an integer format for use in a licence number.
     *
     * @param date A date of date type.
     * @return     An integer representation of the year from the date given.
     */

    public static int getYear(Date date) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * A method for randomly generating a six digit serial number for use in a licence number.
     *
     * @return     A six digit number.
     */

    public static int generateNumber() {

        Random r = new Random();
        return r.nextInt(900000) + 100000;
    }
}
