package comp1110.exam;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@TestMethodOrder(MethodOrderer.MethodName.class)
@org.junit.jupiter.api.Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
public class P1HelloWorldTest {
	@Test
	public void testHasHello() {
	    P1HelloWorld.main(null);
	    assertTrue((outContent.toString().toLowerCase().lastIndexOf("hello") >= 0), "Does not print hello");
	}
	@Test
	public void testHasWorld() {
	    P1HelloWorld.main(null);
	    assertTrue((outContent.toString().toLowerCase().lastIndexOf("world") >= 0), "Does not print world");
	}
	@Test
	public void testHasExactlyOneSpace() {
	    P1HelloWorld.main(null);
	    assertTrue((outContent.toString().contains(" ") && outContent.toString().lastIndexOf(" ") == outContent.toString().indexOf(" ")), "Has wrong number of spaces");
	}
	@Test
	public void testEndsWithExclamation() {
	    P1HelloWorld.main(null);
	    Pattern p = Pattern.compile("!$");
	    Matcher m = p.matcher( outContent.toString().toLowerCase() );
	    assertTrue(m.find(), "Does not end with exclamation mark");
	}
	@Test
	public void testCorrectCase() {
	    P1HelloWorld.main(null);
	    assertTrue((outContent.toString().lastIndexOf("Hello") >= 0 && outContent.toString().lastIndexOf("world") >= 0), "Incorrect use of upper and lower case");
	}
	@Test
	public void testExactlyCorrect() {
	    P1HelloWorld.main(null);
	    Pattern p = Pattern.compile("^Hello world!\\s+$");
	    Matcher m = p.matcher( outContent.toString());
	    assertTrue(m.matches(), "Text not precisely the same"+"-"+outContent.toString()+"-");
	}
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@BeforeEach
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	@AfterEach
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
}
