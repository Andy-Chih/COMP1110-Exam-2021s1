package comp1110.exam;

import java.util.Objects;

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
     *      'i', 'o', 'u') or consonants (all non-vowel lower case letters),              // 全小写
     *   b) either has a vowel as the first letter and a consonant as the last
     *      letter, or a consonant as the first letter and a vowel as the last
     *      letter,                                                                      //头元尾辅音 OR 头辅尾元
     *   c) after removing the first and last letters, the remaining letters
     *      must either make up an alternating word, or be one character long
     *      or be the empty string.                                                      //移掉头尾 仍是al OR 单char OR empty
     *
     * The "alternated" form of an alternating word is created as follows: --------------------------------------------------------------
     *   1. Remove the first and last letters from the alternating word
     *   2. Arrange them such that the consonant is before the vowel                    //辅+元
     *   3. After removing the first and last letters, prepend these in
     *      front of the alternating form of the remaining letters.                     // +剩下的al
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

        String emp = "";
        if (word == null) return emp;
        if (word.length() == 0) return emp;

        //check single char
        if (word.length() == 1) return word;
        //check lowercase
        String lowerWord = word.toLowerCase();
        if (!Objects.equals(lowerWord, word)) return emp;

        String demo = "";
        //check 头元尾辅
        if (isvowel(word.charAt(0)) && isConsonant(word.charAt(word.length() - 1))) {
                demo = demo + word.charAt(word.length() - 1) + word.charAt(0);
                String a = demo + alternating(word.substring(1, word.length() - 1));
                if (a.length()!=word.length()) return "";
                return a;

        } else if (isvowel(word.charAt(word.length() - 1)) && isConsonant(word.charAt(0))) {
            //OR 头辅尾元
            demo = demo + word.charAt(0) + word.charAt(word.length() - 1);
            String a = demo + alternating(word.substring(1, word.length() - 1));
            if (a.length()!=word.length()) return "";
            return a;

        }


        if (demo.length() != word.length()) return "";
        else return demo;


    }


    public static boolean isvowel(char in) {
        if (in == 'a' || in == 'e' || in == 'i' || in == 'o' || in == 'u') return true;
        return false;
    }

    public static boolean isConsonant(char in) {
        if (isvowel(in)) return false;

        if (in >= 'a' && in <= 'z') return true;
        return false;

    }

    /*
    public static void main(String[] args) {
        System.out.println(isConsonant('a'));
        System.out.println(isConsonant('m'));
    }

     */


}
