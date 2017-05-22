package me.zero.client.api.module;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.ModuleStateEvent;
import me.zero.client.api.exception.ActionNotSupportedException;
import me.zero.client.api.manage.Node;
import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.util.keybind.Keybind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.zero.client.api.util.keybind.Keybind.Action.*;

/**
 * The base for all modules. Contains the data
 * for values, properties, type, keybind, name
 * and description.
 *
 * @see Category
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public abstract class Module extends Node implements IModule {

    /**
     * The type/category of the module
     */
    private final Class<?> type;

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
        Class<? extends Module> c = this.getClass();
        if (c.isAnnotationPresent(Mod.class)) {
            Mod data = c.getAnnotation(Mod.class);

            this.name = data.name();
            this.description = data.description();

            this.bind = new Keybind(Keybind.Type.TOGGLE, data.bind(), type -> {
                if (type == CLICK)
                    Module.this.toggle();
            });
        }

        this.type = Arrays.stream(c.getInterfaces())
                .filter(clazz -> clazz.isAnnotationPresent(Category.class))
                .findFirst().orElse(Category.Default.class);

        if (ClientUtils.containsNull(name, description, type))
            throw new NullPointerException("One or more Mod members were null!");
    }

    /**
     * Sets the modes of this module
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
     * @param mode Mode being set
     * @return The new mode
     */
    public final ModuleMode setMode(ModuleMode mode) {
        checkModes();

        if (mode == null || mode.getParent() != this)
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
     * @param index Index of the mode
     * @return The new mode
     */
    public final ModuleMode setMode(int index) {
        checkModes();
        return this.setMode(this.modes.get(index));
    }

    /**
     * Sets the module's mode from the mode's name.
     * Null will be returned if there isn't a mode
     * with the specified name
     *
     * @param name The mode name
     * @return The new mode
     */
    public final ModuleMode setMode(String name) {
        checkModes();
        return this.setMode(getMode(name));
    }

    /**
     * Gets a mode that belongs to this
     * module from the mode's name
     *
     * @return Mode from name
     */
    public final ModuleMode getMode(String name) {
        checkModes();
        return this.modes.stream().filter(mode -> mode.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    /**
     * Returns the list of modes that this module has,
     * null will be returned if this module doesn't have
     * any modes.
     *
     * @return List of modes
     */
    public final List<ModuleMode> getModes() {
        checkModes();
        return new ArrayList<>(this.modes);
    }

    /**
     * Switches the mode to the mode following
     * the current mode in the list of modes.
     *
     * @return The new mode
     */
    public final ModuleMode nextMode() {
        checkModes();

        int index = this.modes.indexOf(this.getMode());
        if (++index > this.modes.size() - 1)
            index = 0;

        return setMode(index);
    }

    /**
     * Switched the mode to the mode preceding
     * the current mode in the list of modes.
     * @return The new mode
     */
    public final ModuleMode lastMode() {
        checkModes();

        int index = this.modes.indexOf(this.getMode());
        if (--index < 0)
            index = this.modes.size() - 1;

        return setMode(index);
    }

    /**
     * Returns this Module's mode, if it has modes
     *
     * @return The current mode
     */
    public final ModuleMode getMode() {
        checkModes();
        return this.mode;
    }

    /**
     * Called when mode related actions are carried out,
     * throws an {@code ActionNotSupportedException} if
     * modes aren't supported by this module.
     */
    private void checkModes() {
        if (!hasModes())
            throw new ActionNotSupportedException("Cannot use mode required actions when modes aren't supported");
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
            EventManager.unsubscribe(this);
            onDisable();
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
