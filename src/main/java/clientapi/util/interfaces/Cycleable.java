package clientapi.util.interfaces;

/**
 * @author Brady
 * @since 4/12/2018
 */
public interface Cycleable<T> {

    /**
     * @return The current selected element
     */
    T current();

    /**
     * Sets the current element to the one succeeding it.
     *
     * @return The next element
     */
    T next();

    /**
     * Sets the current element to the one preceding it.
     *
     * @return The next element
     */
    T last();

    /**
     * Peeks at the element succeeding the current one
     * without setting the current one to it.
     *
     * @return The next element
     */
    T peekNext();

    /**
     * Peeks at the element preceding the current one
     * without setting the current one to it.
     *
     * @return The next element
     */
    T peekLast();

    /**
     * @return All of the elements in this {@code Cycleable};
     */
    T[] getElements();
}
