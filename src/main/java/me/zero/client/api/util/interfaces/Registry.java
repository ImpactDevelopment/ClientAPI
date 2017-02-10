package me.zero.client.api.util.interfaces;

/**
 * Sort of the offspring of a Manager and a Map.
 *
 * @see me.zero.client.api.manage.Manager
 * @see java.util.Map
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
public interface Registry<K, V> {

    /**
     * Loads an Object into the Registry
     *
     * @since 1.0
     *
     * @param obj Object being loaded
     */
    void load(K obj);

    /**
     * Unloads an Object from the Registry
     *
     * @since 1.0
     *
     * @param obj Object being unloaded
     */
    void unload(K obj);

    /**
     * Unloads, and then reloads an Object in the Registry
     *
     * @since 1.0
     *
     * @param obj Object being reloaded
     */
    default void reload(K obj) { unload(obj); reload(obj); }

    /**
     * @since 1.0
     *
     * @param obj The key
     * @return The Value in the Registry belonging to the Key
     */
    V getEntry(K obj);

    /**
     * @since 1.0
     *
     * @param obj The key
     * @return Whether or not the Key has a Value in the Registry
     */
    boolean hasEntry(K obj);
}
