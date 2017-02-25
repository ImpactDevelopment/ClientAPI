package me.zero.client.api.module;

import com.google.common.collect.Sets;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.manage.Node;
import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.keybind.Keybind;

import java.util.Arrays;
import java.util.Set;

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

    /**
     * List of Modes
     */
    private Set<ModuleMode> modes;

    /**
     * The Current Mode
     */
    private ModuleMode mode;

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

    /**
     * Sets the modes of this module
     *
     * @since 1.0
     *
     * @param modes Modes for this mod
     */
    protected final void setModes(ModuleMode... modes) {
        this.modes = Sets.newLinkedHashSet();
        Arrays.stream(modes).forEach(this.modes::add);
    }

    /**
     * Returns whether or not the module has modes
     *
     * @since 1.0
     *
     * @return True if this module has modes, false if not
     */
    public final boolean hasModes() {
        return this.modes != null;
    }

    /**
     * Sets the module's mode to the specified mode
     *
     * @since 1.0
     *
     * @param mode Mode being set
     */
    private void setMode(ModuleMode mode) {
        if (!this.hasModes())
            return;

        if (this.mode != null)
            this.mode.setState(false);

        (this.mode = mode).setState(true);
    }

    /**
     * Returns this Module's mode, if it has modes
     *
     * @since 1.0
     *
     * @return The current mode
     */
    public final ModuleMode getMode() {
        return this.mode;
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

        if (hasModes() && mode != null)
            mode.setState(state);
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
