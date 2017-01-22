package me.zero.client.api.module;

/**
 * Base for Module
 *
 * @see me.zero.client.api.module.Module
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
interface IModule {

    /**
     * Called when the Module is enabled
     *
     * @since 1.0
     */
    default void enable() {}

    /**
     * Called when the Module is disabled
     *
     * @since 1.0
     */
    default void disable() {}

    /**
     * Used to toggle the state of the Module
     *
     * @since 1.0
     */
    void toggle();

    /**
     * Directly sets the state of the Module
     *
     * @since 1.0
     */
    void setState(boolean state);

    /**
     * Returns the current state
     *
     * @since 1.0
     *
     * @return The state of the Module
     */
    boolean getState();

    /**
     * @since 1.0
     *
     * @return The name of the Module
     */
    String getName();

    /**
     * @since 1.0
     *
     * @return The description of the Module
     */
    String getDescription();

    /**
     * @since 1.0
     *
     * @return The type of the Module
     */
    ModuleType getType();
}
