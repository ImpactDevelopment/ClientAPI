package me.zero.client.api.util.interfaces.annotation;

/**
 * Basic label for fields. Gives them
 * a name and a description.
 *
 * @since 1.0
 *
 * Created by Brady on 2/11/2017.
 */
public @interface Label {

    /**
     * @since 1.0
     *
     * @return The name
     */
    String name();

    /**
     * @since 1.0
     *
     * @return The description
     */
    String description();
}
