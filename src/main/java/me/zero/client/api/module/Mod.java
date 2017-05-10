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
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mod {

    /**
     * Name of Module
     */
    String name();

    /**
     * Description of Module
     */
    String description();

    /**
     * Keybind of Module, Key is {@code KEY_NONE} by default.
     */
    int bind() default Keyboard.KEY_NONE;
}
