package comp1110.exam;

/**
 * COMP1110/6710 Exam, Question 4
 * <p>
 * This class represents a special container called a "replicator",
 * to which elements can be added and from which they can be removed in a
 * first in, first out (FIFO) order. Duplicate elements are permitted.
 * Only objects that implement the Replicatable interface can be added to the
 * replicator.
 * The replicator has the unusual property that when an element is added, it is
 * immediately copied (using the `replicate()` method), after which both copies are
 * held in the replicator.
 * Thus after adding a single element to the replicator, it actually contains two
 * elements; the element that was added, and its clone.
 * The remove() method may then be called twice to remove both elements.
 * When a replicator is first created, it contains no elements.
 * The replicator storage can grow to fit new elements as required.
 * Attempting to remove an element from an empty replicator throws an
 * EmptyReplicatorException, and does not result in any modification to the replicator.
 * The replicator is implemented using an array data structure (a regular Java
 * array), and does not use any of the Java Collection classes.
 */
public class Q4Replicator<T extends Object & Q4Replicator.Replicatable> {
    /**
     * Objects that implement this interface can be replicated.
     * Do not modify this interface.
     */
    public interface Replicatable {
        /**
         * @return a new object which is an exact copy of this object
         */
        public Replicatable replicate();
    }

    /**
     * An exception that is thrown when trying to remove an element or sneak a
     * peek from an empty replicator. Do not modify this class.
     */
    public static class EmptyReplicatorException extends RuntimeException {
        public EmptyReplicatorException() {
        }
    }

    /**
     * @return the number of elements currently held in the replicator
     */
    public int size() {
        // FIXME complete this method
        return -1;
    }

    /**
     * Add the given value to the front of the replicator.
     *
     * @param value the value to add to the replicator
     */
    public void add(T value) {
        // FIXME complete this method
    }

    /**
     * Remove the value from the back of the replicator and return it.
     *
     * @return the value that was taken from the back of the replicator
     * @throws EmptyReplicatorException if the replicator is currently empty
     */
    public T remove() throws EmptyReplicatorException {
        // FIXME complete this method
        return null;
    }

    /**
     * Get the value that is currently at the back of the replicator,
     * but do not remove it from the replicator.
     *
     * @return the value at the back of the replicator
     * @throws EmptyReplicatorException if the replicator is currently empty
     */
    public T sneakPeek() throws EmptyReplicatorException {
        // FIXME complete this method
        return null;
    }

    /**
     * Check whether a given element is contained in this replicator.
     * Specifically, returns true if value is not null and
     * an element e is contained in the replicator such that e.equals(value).
     *
     * @param value the value to search for
     * @return true if the value is contained in this replicator
     */
    public boolean contains(T value) {
        // FIXME complete this method
        return false;
    }

    /**
     * Create a String representation of this replicator.
     * Elements in the replicator are listed in order from front to back,
     * separated by commas (without spaces).
     * If the replicator is empty, an empty string is returned.
     * For example, a replicator containing the elements (from front to back)
     * "a", "a", "b", "b", and "c" would be represented as "a,a,b,b,c".
     *
     * @return a String representation of this replicator
     */
    public String toString() {
        // FIXME complete this method
        return null;
    }
}
