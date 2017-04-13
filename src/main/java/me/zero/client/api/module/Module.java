package me.zero.client.api.module;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.ModuleStateEvent;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.manage.Node;
import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.util.keybind.Keybind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public abstract class Module extends Node implements IModule {

    /**
     * The type/category of the module
     */
    private Class<?> type;

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
    private List<ModuleMode> modes;

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
                    .findFirst().orElse(Category.Default.class);
        } else {
            throw new UnexpectedOutcomeException("Modules must have a Mod annotation!");
        }

        if (ClientUtils.containsNull(name, description, type))
            throw new NullPointerException("One or more Mod members were null!");
    }

    /**
     * Sets the modes of this module
     *
     * @since 1.0
     *
     * @param modes Modes for this mod
     */
    protected final void setModes(ModuleMode... modes) {
        if (modes.length == 0) return;

        this.modes = new ArrayList<>();
        this.modes.addAll(Arrays.asList(modes));
        this.setMode(this.modes.get(0));
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
     * Sets the module's mode to the specified mode.
     * Null will be returned if the mode is unable to
     * be set.
     *
     * @since 1.0
     *
     * @param mode Mode being set
     * @return The new mode
     */
    public ModuleMode setMode(ModuleMode mode) {
        if (mode == null || mode.getParent() != this)
            return null;

        if (!this.hasModes())
            return null;

        if (this.mode != null)
            this.mode.setState(false);

        (this.mode = mode).setState(true);

        return this.mode;
    }

    /**
     * Sets the module's mode to the mode in the
     * specified index. An IndexOutOfBoundsException
     * will be thrown if the index is less than 0 or
     * exceeds the maximum index of the mode array.
     *
     * @since 1.0
     *
     * @param index Index of the mode
     * @return The new mode
     */
    public ModuleMode setMode(int index) {
        return this.setMode(this.modes.get(index));
    }

    /**
     * Sets the module's mode from the mode's name.
     * Null will be returned if there isn't a mode
     * with the specified name
     *
     * @since 1.0
     *
     * @param name The mode name
     * @return The new mode
     */
    public ModuleMode setMode(String name) {
        return this.setMode(this.modes.stream().filter(mode -> mode.getName().equalsIgnoreCase(name)).findFirst().orElse(null));
    }

    /**
     * Returns the list of modes that this module has,
     * null will be returned if this module doesn't have
     * any modes.
     *
     * @since 1.0
     *
     * @return List of modes
     */
    public final List<ModuleMode> getModes() {
        if (!hasModes())
            return null;

        return new ArrayList<>(this.modes);
    }

    /**
     * Switches the mode to the mode following
     * the current mode in the list of modes.
     *
     * @since 1.0
     *
     * @return The new mode
     */
    public final ModuleMode nextMode() {
        if (!hasModes())
            return null;

        int index = this.modes.indexOf(this.getMode());
        if (++index >= this.modes.size() - 1)
            index = 0;

        return setMode(index);
    }

    /**
     * Switched the mode to the mode preceding
     * the current mode in the list of modes.
     * @since 1.0
     *
     * @return The new mode
     */
    public final ModuleMode lastMode() {
        if (!hasModes())
            return null;

        int index = this.modes.indexOf(this.getMode());
        if (--index < 0)
            index = this.modes.size() - 1;

        return setMode(index);
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

        ModuleStateEvent event = new ModuleStateEvent(this, state);
        EventManager.post(event);
        if (event.isCancelled())
            return;

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
    public final Class<?> getType() {
        return this.type;
    }

    @Override
    public final Keybind getBind() {
        return this.bind;
    }
}
