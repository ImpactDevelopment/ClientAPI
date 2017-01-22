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
public interface IModule {

    default void enable() {}

    default void disable() {}

    void toggle();

    void setState(boolean state);

    boolean getState();

    String getName();

    String getDescription();

    ModuleType getType();
}
