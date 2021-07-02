package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 1.2 (harder)
 */
public class Q1Alternating {
    /**
     * <pre>
     * Given a word, if the word is an alternating word, return the
     * "alternated" form of the word, or the empty string if the word is not an
     * alternating word.
     *
     * An alternating word:
     *   a) is composed only of lower case letters, either vowels ('a', 'e',
     *      'i', 'o', 'u') or consonants (all non-vowel lower case letters),
     *   b) either has a vowel as the first letter and a consonant as the last
     *      letter, or a consonant as the first letter and a vowel as the last
     *      letter,
     *   c) after removing the first and last letters, the remaining letters
     *      must either make up an alternating word, or be one character long
     *      or be the empty string.
     *
     * The "alternated" form of an alternating word is created as follows:
     *   1. Remove the first and last letters from the alternating word
     *   2. Arrange them such that the consonant is before the vowel
     *   3. After removing the first and last letters, prepend these in
     *      front of the alternating form of the remaining letters.
     *   4. If the remaining letters are one character long, you can just
     *      append this to the end of the resulting string
     *
     * A few examples of alternating words and their alternated form:
     *   quite -> qetui ("qe" + "tu" + "i")
     *   rate -> reta ("re" + "ta")
     *   implies -> simepil ("si" + "me" + "pi" + "l")
     *   ideas -> sidae ("si" + "da" + "e")
     *   recursive -> revecisur ("re" + "ve" + "ci" + "su" + "r")
     *
     * @param word A word
     * @return the alternated form of the word if it is an alternating word
     *         (see above), otherwise the empty string
     */
    public static String alternating(String word) {
        // FIXME complete this method
        return null;
    }
}
