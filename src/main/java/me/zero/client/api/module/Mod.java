package me.zero.client.api.module;

import org.lwjgl.input.Keyboard;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark a class as a Module, clean place
 * to store all of the information that would
 * otherwise be passed down through a contructor.
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mod {

    /**
     * Name of Module
     *
     * @since 1.0
     */
    String name();

    /**
     * Description of Module
     *
     * @since 1.0
     */
    String description();

    /**
     * Keybind of Module, Key is {@code KEY_NONE} by default.
     *
     * @since 1.0
     */
    int bind() default Keyboard.KEY_NONE;

    /**
     * The type of the module represented as a {@code String}
     * {@code ModuleTypes} can be registered using either of
     * the following methods.
     *
     * @see me.zero.client.api.module.ModuleType#create(String)
     * @see me.zero.client.api.module.ModuleType#create(String...)
     *
     * @since 1.0
     */
    String type();
}
