package Car;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Car.RegistrationNumber</h1>
 * This is a class designed to generate unique registration numbers for cars. A registration number consists of two
 * components: 1 character followed by 4 numbers eg. "A1234".
 *
 * @author  Thomas Harwin
 * @version 1.0
 * @since   2018-02-12
 */

public final class RegistrationNumber {

    private static final Map<String, RegistrationNumber> REG_NUMS = new HashMap<>();
    private final char firstComponent;
    private final int secondComponent;
    private final String stringRep;


    private RegistrationNumber(char firstComponent, int secondComponent) {

    this.firstComponent = firstComponent;
    this.secondComponent = secondComponent;
    this.stringRep = firstComponent + "" + secondComponent;
    }

    public static RegistrationNumber getInstance() {

        RegistrationNumber regNo = null;
        Boolean isUnique = false;

        while(!isUnique) {

            char firstComponent = generateCharacter();
            int secondComponent = generateNumber();
            String stringRep = firstComponent + "" + secondComponent;
            regNo = new RegistrationNumber(firstComponent, secondComponent);

            if (!REG_NUMS.containsKey(stringRep)) {
                    isUnique = true;
                    REG_NUMS.put(stringRep, regNo);
            }
        }

        return regNo;
    }

    /**
     * A method for randomly generating a single, upper-case character between A and Z.
     *
     * @return     A single, upper-case character between A and Z
     */

    public static char generateCharacter() {

        Random r = new Random();
        return (char)(r.nextInt(26) + 'A');
    }

    /**
     * A method for randomly generating a four digit number.
     *
     * @return     A four digit number.
     */

    public static int generateNumber() {

        Random r = new Random();
        return r.nextInt(9000) + 1000;
    }

    public char getFirstComponent() {

        return firstComponent;
    }

    public int getSecondComponent() {

        return secondComponent;
    }

    @Override
    public String toString() {

        return stringRep;
    }
}