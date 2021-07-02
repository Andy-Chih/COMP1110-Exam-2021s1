package comp1110.exam;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
@org.junit.jupiter.api.Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class Q1FindAllStartingTest {

    @Test
    public void testOne() {
        test(new String[]{"awash", "hello"}, 'h', new String[]{"hello"});
        test(new String[]{"comp1110", "inconvenient"}, 'c', new String[]{"comp1110"});
        test(new String[]{"friend", "tef"}, 'f', new String[]{"friend"});
        test(new String[]{"magi", "goodbye", "friend"}, 'g', new String[]{"goodbye"});
    }

    @Test
    public void testMultiple() {
        test(new String[]{"hello", "hi"}, 'h', new String[]{"hello", "hi"});
        test(new String[]{"flower", "friend", "feast"}, 'f', new String[]{"flower", "friend", "feast"});
        test(new String[]{"exam", "test", "food", "exclaim"}, 'e', new String[]{"exam", "exclaim"});
        test(new String[]{"phone", "puppy", "basket", "java", "jovial", "joke"}, 'j', new String[]{"java", "jovial", "joke"});
    }

    @Test
    public void testEmpty() {
        test(new String[]{}, 'r', new String[]{});
        test(new String[]{}, 'q', new String[]{});
        test(null, 'S', new String[]{});
    }

    @Test
    public void testNone() {
        test(new String[]{"gift"}, 't', new String[]{});
        test(new String[]{}, 'h', new String[]{});
        test(new String[]{"great", "hope", "plant"}, 'r', new String[]{});
        test(new String[]{"fellow"}, 'f', new String[]{"fellow"});
    }

    @Test
    public void testCase() {
        test(new String[]{"gift"}, 'G', new String[]{"gift"});
        test(new String[]{"coding", "Creative", "First"}, 'c', new String[]{"coding", "Creative"});
        test(new String[]{"WeIrd", "gameS", "WONDErfUL", "JugglE"}, 'w', new String[]{"WeIrd", "WONDErfUL"});
        test(new String[]{"show", "SOLVE", "secret", "Snake"}, 'S', new String[]{"show", "SOLVE", "secret", "Snake"});
    }

    void test(String[] values, char target, String[] expected) {
        String[] result = Q1FindAllStarting.findAllStarting(values, target);
        assertArrayEquals(expected, result, "Q1FindAllStarting.findAllStarting(" + Arrays.toString(values) + ", " + target + ") returned incorrect result");
    }
}


