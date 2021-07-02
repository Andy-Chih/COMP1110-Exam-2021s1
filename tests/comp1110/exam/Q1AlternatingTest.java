package comp1110.exam;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.MethodName.class)
@org.junit.jupiter.api.Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
public class Q1AlternatingTest {

    @Test
    public void testEmpty() {
        test("", "");
    }

    @Test
    public void testOne() {
        test("a", "a");
        test("e", "e");
        test("i", "i");
        test("o", "o");
        test("u", "u");
        test("b", "b");
        test("z", "z");
        test("c", "c");
    }

    @Test
    public void testTwo() {
        test("as", "sa");
        test("if", "fi");
        test("we", "we");
        test("oo", "");
        test("ai", "");
        test("no", "no");
        test("zz", "");
    }

    @Test
    public void testThree() {
        test("rye", "rey");
        test("why", "");
        test("arm", "mar");
        test("mow", "");
        test("pie", "pei");
        test("tan", "");
        test("air", "rai");
        test("ale", "");
        test("elk", "kel");
    }

    @Test
    public void testFive() {
        test("force", "fecor");
        test("plane", "");
        test("optic", "copit");
        test("queue", "");
        test("solve", "sevol");
    }

    private void test(String s, String e) {
        String r = Q1Alternating.alternating(s);
        assertNotNull(r, "For string \"" + s + "\"");
        if (e.isEmpty()) {
            assertTrue(r.isEmpty(), "For string \"" + s + "\", expected the empty string but got '" + r + "'");
        } else {
            assertTrue(e.equals(r), "For string \"" + s + "\", expected '" + e + "' but got '" + r + "'");
        }
    }
}
