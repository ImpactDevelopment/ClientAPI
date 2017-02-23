package me.zero.client.api.module;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.manage.Node;
import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.keybind.Keybind;

import java.util.Arrays;

import static me.zero.client.api.util.keybind.Keybind.Action.*;

/**
 * The base for all cheats
 *
 * @see me.zero.client.api.module.Category
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public abstract class Module extends Node<Module> implements IModule, Helper {

    /**
     * The type/category of the module
     */
    private String type;

    /**
     * The Keybind of this Module
     */
    private Keybind bind;

    /**
     * The state of the module, indicated whether it is on or off
     */
    private boolean state;

    public Module() {
        if (this.getClass().isAnnotationPresent(Mod.class)) {
            Mod data = this.getClass().getAnnotation(Mod.class);

            this.name = data.name();
            this.description = data.description();

            this.bind = new Keybind(data.bind(), type -> {
                if (type == CLICK)
                    Module.this.toggle();
            });

            this.type = Arrays.stream(this.getClass().getInterfaces())
                    .filter(c -> c.isAnnotationPresent(Category.class))
                    .findFirst().orElse(Category.Default.class)
                    .getAnnotation(Category.class).name();
        } else {
            throw new UnexpectedOutcomeException("Modules must have a Mod annotation!");
        }

        if (ClientUtils.containsNull(name, description, type))
            throw new UnexpectedOutcomeException("One or more Mod members were null!");
    }

    @Override
    public final void toggle() {
        this.setState(!this.getState());
    }

    @Override
    public final void setState(boolean state) {
        if (state == this.state) return;

        if (this.state = state) {
            onEnable();
            EventManager.subscribe(this);
        } else {
            onDisable();
            EventManager.unsubscribe(this);
        }
    }

    @Override
    public final boolean getState() {
        return this.state;
    }

    @Override
    public final String getType() {
        return this.type;
    }

    @Override
    public final Keybind getBind() {
        return this.bind;
    }
}
