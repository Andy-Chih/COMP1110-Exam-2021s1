package comp1110.exam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * COMP1110 Final Exam, Question 2
 */
public class Q2AsciiSum {
    /**
     * Q2 Part I (2 marks)
     * <p>
     * Open the specified input file. If the 'ascii' boolean is false,
     * copy all the bytes from the input file to the specified output file.
     * Part II covers the case where the 'ascii' boolean is true.
     * <p>
     * For example: ascii boolean = False.
     * Input characters on left -> output characters on right.
     * - "hello world" -> "hello world"
     * - "1234{56789}" -> "1234{56789}"
     * <p>
     * Q2 Part II (3 marks)
     * <p>
     * Open the specified input file. If the 'ascii' boolean is true then copy
     * all bytes from the input file directly to the output file, except the bytes
     * wrapped by the curly braces ('{}'). Such bytes should be viewed as
     * characters. Your task is to calculate the sum of their value using the
     * ASCII representation and put the result in the curly braces when printing
     * to output file.
     * <p>
     * For example: input characters on left -> output characters on right
     * - "1234{56}" -> "1234{107}"
     * '"56" becomes "107" because the ASCII representation of '5' is 53, and '6' is 54,
     * and the sum of these (53 + 54) = 107.
     * <p>
     * Further Examples (input file characters on left, output characters on right):
     * - "1234{56789}" -> "1234{275}"
     * - "hello world" -> "hello world"
     * - "hello {world}" -> "hello {552}"
     * - "hello{ world}" -> "hello{584}"
     *
     * @param input  The name of the input file
     * @param output The name of the output file
     * @param ascii  if true, calculate the sum of the value of bytes wrapped by
     *               curly braces using their ASCII representation
     */
    public static void asciiSum(String input, String output, boolean ascii) {
        // FIXME complete this method
    }
}
