package design_pattern;

/**
 * It's a container for iterator design pattern.
 * @author Nico Tandyo
 */
public interface Container {

    /**
     * Get an iterator.
     *
     * @param theKey searching keyword.
     * @return an iterator.
     */
    public Iterator getIterator(final String theKey);

    /**
     * Size of the iterator.
     *
     * @return a size.
     */
    public int getSize();
}
