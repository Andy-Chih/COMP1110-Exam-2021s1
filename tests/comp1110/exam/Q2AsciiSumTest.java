package comp1110.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@org.junit.jupiter.api.Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
public class Q2AsciiSumTest {

    static final String INPUT_FILENAME_BASE = "assets/Q2AsciiSum";
    static final String OUTPUT_FILENAME = "assets/Q2output";

    @BeforeEach
    public void setup() {
        try {
            Files.deleteIfExists(Paths.get(OUTPUT_FILENAME));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Test
    public void empty() {
        test("A", true, "", "");
    }

    @Test
    public void small() {
        test("B", false, "flag{ the value is not-0}", "flag{ the value is not-0}");
    }

    @Test
    public void smallAsciiSum() {
        test("B", true, "flag{1640}", "flag{ the value is not-0}");
    }

    @Test
    public void medAsciiSum() {
        test("C", true, " Enrolled students will be added to the COMP{195} team on Microsoft Teams before the start of semester.", "Enrolled students will be added to the COMP{1110} team on Microsoft Teams before the start of semester.");
    }

    @Test
    public void largeAsciiSum() {
        test("D", true, "Lorem ipsum dolor sit amet, {78744}", "Lorem ipsum dolor sit amet, {consectetur adipiscing elit. Nunc aliquet velit vel nisl sollicitudin, quis ullamcorper arcu sollicitudin. Integer erat arcu, maximus ac augue vel, bibendum gravida nisl. Proin vulputate felis at rhoncus hendrerit. Aenean ut finibus justo. Maecenas lacinia nunc dui, eu rhoncus magna bibendum non. Pellentesque interdum urna quam, at malesuada ligula rutrum ut. Nullam hendrerit sit amet elit eu aliquet. Nam facilisis, metus vel pretium tristique, enim lectus rutrum erat, vitae iaculis libero ipsum eu felis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nullam orci ex, interdum eget eleifend ac, pretium eget risus. In vitae sagittis turpis. Donec metus nibh, bibendum id ipsum in, ornare tristique purus. Suspendisse bibendum enim imperdiet ornare accumsan. Pellentesque mollis varius eros, non ullamcorper neque.}");
    }

    private void test(String insuf, boolean cs, String expected, String content) {
        Q2AsciiSum.asciiSum(INPUT_FILENAME_BASE+insuf, OUTPUT_FILENAME, cs);
        try {
            assertTrue(Files.exists(Paths.get(OUTPUT_FILENAME)),
                    "Called Q2AsciiSum.asciiSum("+INPUT_FILENAME_BASE+insuf+", "+OUTPUT_FILENAME+", "+cs+"). " +
                    "Expected file \""+OUTPUT_FILENAME+"\", but no file was found");
            String actual = Files.readString(Paths.get(OUTPUT_FILENAME));
            assertEquals(expected, actual,
                    "Called Q2AsciiSum.asciiSum(" + INPUT_FILENAME_BASE + insuf + ", " + OUTPUT_FILENAME + ", " + cs +
                            "). Expected file to contain \"" + expected + "\" but got \"" + actual + "\" when file contained \"" + content + "\".");
        } catch (IOException e) {
            System.out.println(e);
            fail(e.getMessage());
        }
    }
}
