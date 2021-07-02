package comp1110.exam;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
public class Q5MonarchTest {

    public static final int HASH_ITERATIONS = 100;

    static Q5Monarch[] monarchs = new Q5Monarch[200 * HASH_ITERATIONS];

    @Test
    public void testHashCodeDeterministic() {
        checkNotObjectHashCode();
        for (Q5Monarch monarch : monarchs) {
            int hash = monarch.hashCode();
            for (int i = 0; i < 10; i++) {
                int hash2 = monarch.hashCode();
                assertTrue(hash == hash2, "monarch " + monarch + "  returned different values for hashCode(): " + hash + ", " + hash2);
            }
        }
    }

    @Test
    public void testAllFields() {
        checkNotObjectHashCode();
        Q5Monarch monarch1 = new Q5Monarch("Edward", 1, "England", "Longshanks");
        Q5Monarch monarch2 = new Q5Monarch("Edward", 1, "England", "the Hammer of the Scots");
        assertEquals(monarch1.hashCode(), monarch2.hashCode(), "monarchs " + monarch1 + " and " + monarch2 + " returned different hashCode(), but they are equal");
        monarch2 = new Q5Monarch("Edward", 1, "Saxony", "Longshanks");
        testDifferent(monarch1, monarch2);
        testDifferent(monarch1, monarch2);
        monarch2 = new Q5Monarch("Edward", 2, "England", "Longshanks");
        testDifferent(monarch1, monarch2);
        testDifferent(monarch1, monarch2);
        monarch2 = new Q5Monarch("Henry", 1, "England", "Beauclerc");
        testDifferent(monarch1, monarch2);
        testDifferent(monarch1, monarch2);
        monarch2 = new Q5Monarch("England", 1, "Edward", "Longshanks");
        testDifferent(monarch1, monarch2);
        testDifferent(monarch1, monarch2);
    }

    @Test
    public void testEquals() {
        Q5Monarch monarch1 = new Q5Monarch("Edward", 1, "England", "Longshanks");
        assertTrue(monarch1.equals(monarch1), "monarch1.equals(monarch1) returned false; should have returned true for " + monarch1);
        Q5Monarch monarch2 = new Q5Monarch("Edward", 1, "England", "the Hammer of the Scots");
        assertEquals(monarch1, monarch2, "monarchs " + monarch1 + " and " + monarch2 + " are equal, but monarch1.equals(monarch2) returned false");
        monarch2 = new Q5Monarch("Edward", 1, "Nowhere", "the Hammer of the Scots");
        assertNotEquals(monarch1, monarch2, "monarchs " + monarch1 + " and " + monarch2 + " are not equal, but monarch1.equals(monarch2) returned true");
        monarch2.name = "George";
        assertNotEquals(monarch1, monarch2, "monarchs " + monarch1 + " and " + monarch2 + " are not equal, but monarch1.equals(monarch2) returned true");
        monarch2.name = null;
        assertNotEquals(monarch1, monarch2, "monarchs " + monarch1 + " and " + monarch2 + " are not equal, but monarch1.equals(monarch2) returned true");
        monarch2.name = "Edward";
        monarch1.name = null;
        assertNotEquals(monarch1, monarch2, "monarchs " + monarch1 + " and " + monarch2 + " are not equal, but monarch1.equals(monarch2) returned true");
        monarch1.name = "Edward";
        monarch2.number = 2;
        assertNotEquals(monarch1, monarch2, "monarchs " + monarch1 + " and " + monarch2 + " are not equal, but monarch1.equals(monarch2) returned true");
        monarch2.number = 1;
        monarch1.country = "Liechtenstein";
        assertNotEquals(monarch1, monarch2, "monarchs " + monarch1 + " and " + monarch2 + " are not equal, but monarch1.equals(monarch2) returned true");
        monarch1.country = null;
        assertNotEquals(monarch1, monarch2, "monarchs " + monarch1 + " and " + monarch2 + " are not equal, but monarch1.equals(monarch2) returned true");
    }

    @Test
    public void testUniformA() {
        testHashCodeTrivial();
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=10-1, prob=0.999) ~= 27.88
        testUniformity(10, 28);
    }

    @Test
    public void testUniformB() {
        testHashCodeTrivial();
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=50-1, prob=0.999) ~= 85.35
        testUniformity(50, 86);
    }

    private void testHashCodeTrivial() {
        Q5Monarch monarch = new Q5Monarch("Edward", 1, "England", "Longshanks");
        assertTrue(monarch.hashCode() == monarch.hashCode(), "hashCode should be deterministic, but monarch.hashCode() != monarch.hashCode() for monarch " + monarch);
    }

    private void testUniformity(int buckets, double chiSqCriticalValue) {
        Random r = new Random(7);
        int[] count = new int[buckets];
        int samples = buckets * HASH_ITERATIONS;
        for (int i = 0; i < samples; i++) {
            Q5Monarch monarch = monarchs[r.nextInt(monarchs.length)];
            int h = Math.abs(monarch.hashCode()) % buckets;
            count[h]++;
        }

        double chiSq = chiSquaredUniform(samples, count);
        assertTrue(chiSq < chiSqCriticalValue, "Distribution of hash function doesn't appear to be uniform over " + buckets + " buckets (chi squared value of " + chiSq + ").\nExpected " + samples / buckets + " elements per bucket, but got " + Arrays.toString(count));
    }

    private void checkNotObjectHashCode() {
        Random r = new Random(9);
        int range = 39;
        Consumer<Function<Q5Monarch, Integer>> checkHash = (Function<Q5Monarch, Integer> hashFunction) -> {
            Set<Q5Monarch>[] myBuckets = new Set[range];
            Set<Q5Monarch>[] defaultBuckets = new Set[range];
            for (int i = 0; i < range; i++) {
                myBuckets[i] = new HashSet<>();
                defaultBuckets[i] = new HashSet<>();
            }
            for (int i = 0; i < 98 * HASH_ITERATIONS; i++) {
                Q5Monarch monarch = monarchs[r.nextInt(monarchs.length)];
                int m = Math.abs(monarch.hashCode()) % range;
                myBuckets[m].add(monarch);
                int n = Math.abs(hashFunction.apply(monarch)) % range;
                defaultBuckets[n].add(monarch);
            }
            for (Set<Q5Monarch> myBucket : myBuckets) {
                for (Set<Q5Monarch> defaultBucket : defaultBuckets) {
                    assertFalse(myBucket.equals(defaultBucket), "It looks like you're using Object.hashCode() or Objects.hash()!");
                }
            }
        };

        checkHash.accept((Q5Monarch monarch) -> monarch.passThroughHash());
        checkHash.accept((Q5Monarch monarch) -> monarch.name.hashCode());
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.name));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.number));
        checkHash.accept((Q5Monarch monarch) -> monarch.country.hashCode());
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.country));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.name, monarch.number));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.number, monarch.name));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.name, monarch.country));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.country, monarch.name));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.number, monarch.country));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.country, monarch.number));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.name, monarch.number, monarch.country));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.name, monarch.country, monarch.number));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.number, monarch.country, monarch.name));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.number, monarch.name, monarch.country));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.country, monarch.name, monarch.number));
        checkHash.accept((Q5Monarch monarch) -> Objects.hash(monarch.country, monarch.number, monarch.name));

    }

    private void testDifferent(Q5Monarch monarch1, Q5Monarch monarch2) {
        int hash1 = monarch1.hashCode();
        int hash2 = monarch2.hashCode();
        assertNotEquals(hash1, hash2, "monarchs " + monarch1 + " and " + monarch2 + " returned same hashCode(): " + hash1 + ", " + hash2);
    }

    @BeforeAll
    public static void generateMonarchs() {
        Random r = new Random(14);
        for (int i = 0; i < monarchs.length; i++) {
            String name = monarchList[r.nextInt(monarchList.length)][0] + " " + monarchList[r.nextInt(monarchList.length)][0];
            int number = Integer.valueOf(monarchList[r.nextInt(monarchList.length)][1]);
            String country = monarchList[r.nextInt(monarchList.length)][2];
            String cognomen = monarchList[r.nextInt(monarchList.length)][3];
            monarchs[i] = new Q5Monarch(name, number, country, cognomen);
        }
    }


    private static double chiSquaredUniform(int samples, int[] counts) {
        double uniformProb = 1.0 / counts.length;
        double total = 0;
        for (int count : counts) {
            double mi = ((double) samples) * uniformProb;
            total += ((double) count - mi) * ((double) count - mi) / mi;
        }
        return total;
    }

    static String[][] monarchList = {
            {"Elizabeth", "1", "England", "the Glorious"},
            {"Elizabeth", "2", "England", null},
            {"Harald", "3", "Denmark", "the Gentle"},
            {"Manuel", "1", "Portugal", "the Fortunate"},
            {"Edward", "3", "England", "King of the Seas"},
            {"Edward", "7", "the United Kingdom", "the Uncle of Europe"},
            {"Edward", "8", "the United Kingdom", null},
            {"Wilhelm", "2", "Germany", "Stupid Willy"},
            {"Wilhelm", "2", "Germany", "Kaiser Bill"},
            {"Leopold", "3", "Austria", "the Just"},
            {"Leopold", "5", "Austria", "the Virtuous"},
            {"Catherine", "2", "Russia", "the Great"},
            {"Charles", "2", "France", "the Bald"},
            {"Harald", "1", "Denmark", "Bluetooth"},
            {"Ivaylo", "1", "Bulgaria", "Cabbage"},
            {"Richard", "3", "England", "Crookback"},
            {"Louis", "1", "France", "the Debonair"},
            {"Louis", "4", " France", "from Overseas"},
            {"Louis", "5", "France", "the Indolent"},
            {"Louis", "6", "France", "the Fat"},
            {"Louis", "9", "France", "the Saint"},
            {"Louis", "11", "France", "the Cruel"},
            {"Louis", "13", "France", "the Just"},
            {"Louis", "18", "France", "the Desired"},
            {"Vlad", "2", "Wallachia", "the Devil"},
            {"Vlad", "3", "Wallachia", "the Impaler"},
            {"Vlad", "4", "Wallachia", "the Monk"},
            {"Afonso", "2", "Portugal", "the Fat"},
            {"Ferdinand", "1", "Portugal", "the Handsome"},
            {"Menelik", "2", "Ethiopia", "Mother"},
            {"Eric", "2", "Norway", "the Priest-Hater"},
            {"Ivan", "4", "Russia", "the Terrible"},
            {"John", "1", "France", "the Posthumous"},
            {"Mary", "1", "England", "Bloody"},
            {"Ludwig", "2", "Bavaria", null},
            {"Edmund", "1", "England", "the Magnificent"},
            {"Suleiman", "1", "the Ottoman Empire", "the Magnificent"},
            {"Magnus", "1", "Norway", "the Noble"},
            {"Albert", "1", "Monaco", "the Oceanographer"},
            {"Haakon", "4", "Norway", "the Old"},
            {"Mieszko", "3", "Poland", "the Old"},
            {"Louis", "2", "Bavaria", "the Strict"},
            {"George", "5", "United Kingdom", null},
            {"George", "6", "United Kingdom", null},
            {"Victoria", "1", "United Kingdom", null},
            {"Boleslaw", "1", "Poland", "the Brave"},
            {"Boleslaw", "2", "Poland", "the Bold"},
            {"Robert", "1", "Scotland", "the Bruce"},
            {"Valdemar", "2", "Denmark", "the Conqueror"},
            {"Sigurd", "1", "Norway", "the Crusader"},
            {"Ferdinand", "7", "Spain", "the Desired"},
            {"Anthony", "1", "Portugal", "the Determined"},
            {"Anthony", "1", "Portugal", "the Fighter"},
            {"Selim", "2", "the Ottoman Empire", "the Drunkard"},
            {"Vladislaus", "2", "Poland", "the Exile"},
            {"Charles", "4", "France", "the Fair"},
            {"George", "3", "United Kingdom", "Farmer"},
            {"Sweyn", "1", "Denmark", "Forkbeard"},
            {"Erik", "9", "Sweden", "the Lawgiver"},
            {"Donald", "2", "Scotland", "the Madman"},
            {"Alfonso", "5", "Aragon", "the Magnanimous"},
            {"Malcolm", "4", "Scotland", "the Maiden"},
            {"Charles", "1", "England", "the Martyr"},
            {"Geoffrey", "2", "Anjou", "the Hammer"}
    };
}
