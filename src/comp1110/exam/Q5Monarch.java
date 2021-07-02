package comp1110.exam;

import java.util.Random;

/**
 * COMP1110 Final Exam, Question 5
 *
 * This class represents a monarch (a king or queen), who is uniquely
 * identified by a combination of first name, number, and country, for example:
 * {"Edward",7,"United Kingdom"} or {"Catherine,2,Russia"}.
 *
 * A monarch may also have a cognomen (nickname), which is not part of their
 * unique identity.
 * For example: {"Edward",1,"England"} with the cognomen "Longshanks" and
 * {"Edward",1,"England"} with the cognomen "the Hammer of the Scots" are the
 * same person, even though the cognomen is different.
 */
public class Q5Monarch {
    /**
     * The name of this monarch e.g. "Elizabeth".
     */
    String name;

    /**
     * The number of this monarch e.g. 1.
     */
    int number;

    /**
     * The country of this monarch e.g. "United Kingdom".
     */
    String country;

    /**
     * The cognomen of this monarch e.g. "the Glorious".
     * Does not form part of this monarch's unique identity.
     */
    String cognomen;

    public Q5Monarch(String name, int number, String country, String cognomen) {
        this.name = name;
        this.number = number;
        this.country = country;
        this.cognomen = cognomen;
    }

    /**
     * @return a hash code value for this monarch
     * In implementing this method, you may *not* use the default Java
     * implementations in Object.hashCode() or Objects.hash().
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        // FIXME complete this method
        return new Random().nextInt(1000);
    }

    /**
     * @return true if this monarch is equal to the provided object
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        // FIXME complete this method
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ")
                .append(number).append(" of ")
                .append(country);
        if (cognomen != null && !cognomen.isEmpty()) sb.append(" \"").append(cognomen).append("\"");
        return sb.toString();
    }

    // DO NOT DELETE OR CALL THIS METHOD
    int passThroughHash() {
        return super.hashCode();
    }
}
