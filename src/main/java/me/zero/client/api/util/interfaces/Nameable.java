package me.zero.client.api.util.interfaces;

/**
 * Gives objects a name, alias, and description
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
public interface Nameable {

    /**
     * @return Name of this object
     */
    String getName();

    /**
     * @return Description of this object
     */
    String getDescription();
}
