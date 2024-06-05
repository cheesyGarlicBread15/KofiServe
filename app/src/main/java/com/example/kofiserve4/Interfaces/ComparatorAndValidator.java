package com.example.kofiserve4.Interfaces;

public interface ComparatorAndValidator<T> {
    /**
     * implemented by inner classes of object to be compared, each data type comparator
     * is one inner class
     */

    /**
     * Compare object (T), getters of object are utilized to retrieve desireed
     * attribute to compare
     * @param o1 first object
     * @param o2 second object
     * @return 1 if o1 precedes than o2, -1 if o2 precedes than o2, 0 if both have same precedence
     */
    public int compare(T o1, T o2);

    /**
     * Validate attribute to be compared,
     * @param o object to validate the attribute
     * @return object, null otherwise
     */
    public T validate(T o);
}
