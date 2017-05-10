package me.zero.client.api.util.interfaces;

/**
 * Sort of the offspring of a Manager and a Map.
 *
 * @see me.zero.client.api.manage.Manager
 * @see java.util.Map
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public interface Registry<K, V> {

    /**
     * Loads an Object into the Registry
     *
     * @param obj Object being loaded
     */
    void load(K obj);

    /**
     * Unloads an Object from the Registry
     *
     * @param obj Object being unloaded
     */
    void unload(K obj);

    /**
     * Unloads, and then reloads an Object in the Registry
     *
     * @param obj Object being reloaded
     */
    default void reload(K obj) { unload(obj); reload(obj); }

    /**
     * @param obj The key
     * @return The Value in the Registry belonging to the Key
     */
    V getEntry(K obj);

    /**
     * @param obj The key
     * @return Whether or not the Key has a Value in the Registry
     */
    boolean hasEntry(K obj);
}
