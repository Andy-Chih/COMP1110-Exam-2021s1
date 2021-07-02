package comp1110.exam;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
//@org.junit.jupiter.api.Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class Q4ReplicatorTest {
    static class Thing implements Q4Replicator.Replicatable {
        final String name;

        public Thing(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Thing " + name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Thing thing = (Thing) o;
            return Objects.equals(name, thing.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public Thing replicate() {
            return new Thing(name);
        }
    }

    @Test
    public void testAddSize() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        assertEquals(0, replicator.size(), "Size of newly-created replicator");
        Thing thing1 = new Thing("One");
        replicator.add(thing1);
        assertEquals(2, replicator.size(), "replicator.size() after adding one element");
        Thing thing2 = new Thing("Two");
        replicator.add(thing2);
        assertEquals(4, replicator.size(), "replicator.size() after adding two elements");
    }

    @Test
    public void testAddTwoString() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        checkToString(replicator, "");
        Thing thing1 = new Thing("One");
        replicator.add(thing1);
        checkToString(replicator, "Thing One,Thing One");
        Thing thing2 = new Thing("Two");
        replicator.add(thing2);
        checkToString(replicator, "Thing Two,Thing Two,Thing One,Thing One");
    }

    private <T extends Q4Replicator.Replicatable> void checkToString(Q4Replicator<T> replicator, String expected) {
        assertEquals(expected, replicator.toString(), "Incorrect output from toString().");
    }

    @Test
    public void testRemoveOne() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thingToDo = new Thing("to do");
        replicator.add(thingToDo);
        checkRemove(replicator, thingToDo);
        assertEquals(1, replicator.size(), "replicator.size() after one add and one remove");
    }

    @Test
    public void testRemoveOneString() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thingToDo = new Thing("to do");
        replicator.add(thingToDo);
        Thing thingToSee = new Thing("to see");
        replicator.add(thingToSee);
        replicator.remove();
        checkToString(replicator, "Thing to see,Thing to see,Thing to do");
    }

    @Test
    public void testRemoveTwo() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thing1 = new Thing("One");
        replicator.add(thing1);
        Thing thing2 = new Thing("Two");
        replicator.add(thing2);
        checkRemove(replicator, thing1);
        assertEquals(3, replicator.size(), "replicator.size() after two adds and one remove");
        checkRemove(replicator, thing1);
        assertEquals(2, replicator.size(), "replicator.size() after two adds and two removes");
        checkRemove(replicator, thing2);
        assertEquals(1, replicator.size(), "replicator.size() after two adds and three removes");
    }

    @Test
    public void testRemoveTwoString() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thing1 = new Thing("One");
        replicator.add(thing1);
        Thing thing2 = new Thing("Two");
        replicator.add(thing2);
        replicator.remove();
        checkToString(replicator, "Thing Two,Thing Two,Thing One");
        replicator.remove();
        checkToString(replicator, "Thing Two,Thing Two");
    }

    @Test
    public void testRemoveEmpty() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        assertThrows(Q4Replicator.EmptyReplicatorException.class, () -> {
            replicator.remove();
        });
    }

    @Test
    public void testEmptyString() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        checkToString(replicator, "");
    }

    @Test
    public void testSneakPeekOne() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thingToEat = new Thing("to eat");
        replicator.add(thingToEat);
        Thing v = replicator.sneakPeek();
        assertEquals(thingToEat, v, "Incorrect value returned from replicator.sneakPeek()");
        assertEquals(2, replicator.size(), "replicator.size() after one add and one sneakPeek");
    }

    @Test
    public void testSneakPeekTwo() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing a = new Thing("a");
        replicator.add(a);
        Thing b = new Thing("b");
        replicator.add(b);
        Thing v1 = replicator.sneakPeek();
        assertEquals(a, v1, "Incorrect value returned from replicator.sneakPeek()");
        assertEquals(4, replicator.size(), "replicator.size() after two adds and one sneakPeek");
        Thing v2 = replicator.sneakPeek();
        assertEquals(a, v2, "Incorrect value returned from replicator.sneakPeek()");
        assertEquals(4, replicator.size(), "replicator.size() after two adds and two sneakPeeks");
        replicator.remove(); // throw away back value
        Thing v3 = replicator.sneakPeek();
        assertEquals(a, v3, "Incorrect value returned from replicator.sneakPeek()");
        replicator.remove(); // throw away clone
        Thing v4 = replicator.sneakPeek();
        assertEquals(b, v4, "Incorrect value returned from replicator.sneakPeek()");
    }

    @Test
    public void testAddMultiple() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thing1 = new Thing("One");
        replicator.add(thing1);
        Thing thing2 = new Thing("Two");
        replicator.add(thing2);
        replicator.add(thing2);
        checkRemove(replicator, thing1);
        checkRemove(replicator, thing1);
        checkRemove(replicator, thing2);
        checkRemove(replicator, thing2);
        checkRemove(replicator, thing2);
        checkRemove(replicator, thing2);
    }

    @Test
    public void testAddMultipleString() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thing1 = new Thing("One");
        replicator.add(thing1);
        Thing thing2 = new Thing("Two");
        replicator.add(thing2);
        checkToString(replicator, "Thing Two,Thing Two,Thing One,Thing One");
        replicator.add(thing2);
        checkToString(replicator, "Thing Two,Thing Two,Thing Two,Thing Two,Thing One,Thing One");
        replicator.remove();
        checkToString(replicator, "Thing Two,Thing Two,Thing Two,Thing Two,Thing One");
        replicator.remove();
        checkToString(replicator, "Thing Two,Thing Two,Thing Two,Thing Two");
        replicator.remove();
        checkToString(replicator, "Thing Two,Thing Two,Thing Two");
    }

    @Test
    public void testSneakPeekEmpty() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        assertThrows(Q4Replicator.EmptyReplicatorException.class, () -> {
            replicator.sneakPeek();
        });
    }

    @Test
    public void testNoMemoryLeaks() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thing1 = new Thing("One");
        replicator.add(thing1);
        WeakReference<Thing> reference1 = new WeakReference<>(thing1);
        Thing thing2 = new Thing("Two");
        replicator.add(thing2);
        WeakReference<Thing> reference2 = new WeakReference<>(thing2);
        checkRemove(replicator, thing1);
        checkRemove(replicator, thing1);
        checkRemove(replicator, thing2);
        checkRemove(replicator, thing2);
        thing1 = null;
        thing2 = null;

        Runtime.getRuntime().gc();
        assertNull(reference1.get(), "A reference still exists to thing1 somewhere in the replicator!");
        assertNull(reference2.get(), "A reference still exists to thing2 somewhere in the replicator!");
    }

    @Test
    public void testAddRemoveLarge() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        final int LENGTH = 500;
        List<Thing> range = IntStream.range(0, LENGTH).boxed().map(i -> new Thing(String.valueOf(i))).collect(Collectors.toList());
        for (Thing item : range) {
            replicator.add(item);
        }
        for (int i = 0; i < LENGTH; i++) {
            assertEquals((LENGTH - i) * 2, replicator.size(), "replicator.size() after removing " + i * 2 + " elements");
            // remove the original object
            Thing result = replicator.remove();
            Thing expected = new Thing(String.valueOf(i));
            assertEquals(expected, result, "Incorrect value returned from replicator.remove()");
            // remove the clone
            result = replicator.remove();
            expected = new Thing(String.valueOf(i));
            assertEquals(expected, result, "Incorrect value returned from replicator.remove()");
        }
    }

    @Test
    public void testContainsTop() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thing1 = new Thing("One");
        Thing thing2 = new Thing("Two");
        assertFalse(replicator.contains(thing1), "replicator.contains(thing1) returned true, expected false");
        assertFalse(replicator.contains(thing2), "replicator.contains(thing2) returned true, expected false");
        replicator.add(thing1);
        assertTrue(replicator.contains(thing1), "replicator.contains(thing1) returned false, expected true");
        replicator.add(thing2);
        assertTrue(replicator.contains(thing2), "replicator.contains(thing2) returned false, expected true");
        replicator.remove();
        assertTrue(replicator.contains(thing1), "replicator.contains(thing1) returned false, expected true");
        assertTrue(replicator.contains(thing2), "replicator.contains(thing2) returned false, expected true");
        replicator.remove();
        assertFalse(replicator.contains(thing1), "replicator.contains(thing1) returned true, expected false");
        assertTrue(replicator.contains(thing2), "replicator.contains(thing2) returned false, expected true");
        replicator.remove();
        replicator.remove();
        assertFalse(replicator.contains(thing2), "replicator.contains(thing2) returned true, expected false");
        assertFalse(replicator.contains(null), "replicator.contains(null) returned true, expected false");
    }

    @Test
    public void testContainsBuried() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thing1 = new Thing("One");
        Thing thing2 = new Thing("Two");
        assertFalse(replicator.contains(thing1), "replicator.contains(thing1) returned true, expected false");
        assertFalse(replicator.contains(thing2), "replicator.contains(thing2) returned true, expected false");
        replicator.add(thing1);
        assertTrue(replicator.contains(thing1), "replicator.contains(thing1) returned false, expected true");
        assertFalse(replicator.contains(thing2), "replicator.contains(thing2) returned true, expected false");
        replicator.add(thing2);
        assertTrue(replicator.contains(thing1), "replicator.contains(thing1) returned false, expected true");
        assertTrue(replicator.contains(thing2), "replicator.contains(thing2) returned false, expected true");
        replicator.remove();
        assertTrue(replicator.contains(thing1), "replicator.contains(thing1) returned false, expected true");
        assertTrue(replicator.contains(thing2), "replicator.contains(thing2) returned false, expected true");
        replicator.remove();
        assertFalse(replicator.contains(thing1), "replicator.contains(thing1) returned true, expected false");
        assertTrue(replicator.contains(thing2), "replicator.contains(thing2) returned false, expected true");
        replicator.remove();
        assertFalse(replicator.contains(thing1), "replicator.contains(thing1) returned true, expected false");
        assertTrue(replicator.contains(thing2), "replicator.contains(thing2) returned false, expected true");
        replicator.remove();
        assertFalse(replicator.contains(thing1), "replicator.contains(thing1) returned true, expected false");
        assertFalse(replicator.contains(thing2), "replicator.contains(thing2) returned true, expected false");
    }

    @Test
    public void testContainsDuplicateRemoved() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thing1 = new Thing("One");
        Thing thing2 = new Thing("Two");
        replicator.add(thing1);
        replicator.add(thing2);
        replicator.add(thing1);
        replicator.remove();
        replicator.remove(); // remove clone
        assertTrue(replicator.contains(thing1), "replicator.contains(thing1) returned false, expected true");
    }

    @Test
    public void testRemoveEmptyString() {
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        Thing thingvellir = new Thing("vellir");
        replicator.add(thingvellir);
        replicator.remove();
        try {
            replicator.remove();
            replicator.remove();
        } catch (Q4Replicator.EmptyReplicatorException e) {
            // ignore the exception
        }
        checkToString(replicator, "");
        replicator.add(thingvellir);
        checkToString(replicator, "Thing vellir,Thing vellir");
    }

    @Test
    public void testContainsDeep() {
        final int SIZE = 874;
        Q4Replicator<Thing> replicator = new Q4Replicator<>();
        for (int i = 0; i < SIZE; i++) {
            replicator.add(new Thing(String.valueOf(i)));
        }
        assertFalse(replicator.contains(null), "replicator.contains(null) returned true, expected false");
        for (int i = 0; i < SIZE; i++) {
            Thing v1 = new Thing(String.valueOf(i));
            assertTrue(replicator.contains(v1), "replicator.contains(" + v1 + ") returned false, expected true");
        }
    }

    private void checkRemove(Q4Replicator<Thing> replicator, Thing expected) {
        Thing result = replicator.remove();
        assertEquals(expected, result, "Incorrect result returned from replicator.remove()");
    }
}
