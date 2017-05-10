package me.zero.client.api.manage;

import me.zero.client.api.util.interfaces.Loadable;
import me.zero.client.api.util.interfaces.Saveable;

import java.util.*;

/**
 * Manager used to store arrays of information.
 * Just a way to keep things consistent.
 *
 * @see Loadable
 * @see Saveable
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public abstract class Manager<T> implements Loadable, Saveable {

    /**
     * The list of all of the entries that this Manager contains
     */
    private final List<T> data = new ArrayList<>();

    /**
     * Cache of classes and their corresponding entries
     */
    private final Map<Class<T>, T> classCache = new HashMap<>();

    /**
     * The name of the Manager
     */
    private final String name;

    public Manager(String name) {
        this.name = name;
    }

    /**
     * Resets all of the data in this manager
     *
     * @since 1.0
     */
    public final void reset(){
        this.data.clear();
    }
    /**
     * Adds multiple entries into the data array
     *
     * @since 1.0
     *
     * @param data The entries
     */
    @SafeVarargs
    protected final void addData(T... data) {
        this.data.addAll(Arrays.asList(data));
    }

    /**
     * Adds a list of entries into the data array
     *
     * @since 1.0
     *
     * @param data The entries
     */
    protected final void addData(List<T> data) {
        this.data.addAll(data);
    }

    /**
     * Removes an entry, if present, from the data array
     *
     * @since 1.0
     *
     * @param data The entry
     */
    protected final void removeData(T data) {
        this.data.remove(data);
    }

    /**
     * Retrieves an entry by the entry's class
     *
     * @param clazz The class
     * @return The retrieved entry
     */
    @SuppressWarnings("unchecked")
    public final <I extends T> I get(Class<I> clazz) {
        if (clazz == null)
            return null;

        return (I) classCache.computeIfAbsent((Class<T>) clazz,
                c -> this.data.stream().filter(data -> data.getClass().equals(c)).findFirst().orElse(null));
    }

    /**
     * @since 1.0
     *
     * @return All of the entries that this manager holds
     */
    public final List<T> getData() {
        return new ArrayList<>(this.data);
    }

    /**
     * @since 1.0
     *
     * @return The name of this manager
     */
    public String getName() {
        return this.name;
    }
}