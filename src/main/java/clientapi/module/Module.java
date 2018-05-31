/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.module;

import clientapi.ClientAPI;
import clientapi.event.defaults.internal.ModuleStateEvent;
import clientapi.module.exception.ModuleInitException;
import clientapi.util.ClientAPIUtils;
import clientapi.util.Tag;
import clientapi.util.interfaces.Describable;
import clientapi.util.interfaces.Nameable;
import clientapi.util.interfaces.Taggable;
import clientapi.util.io.Keybind;
import clientapi.value.holder.ValueHolder;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The base for all modules. Contains the data
 * for values, properties, type, keybind, name
 * and description.
 *
 * @see IModule
 * @see Category
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public abstract class Module extends ValueHolder implements IModule, Nameable, Describable, Taggable {

    /**
     * Reference to self-class
     */
    private final Class<? extends Module> self = this.getClass();

    /**
     * Name of the node
     */
    protected String name;

    /**
     * Description of the node
     */
    protected String description;

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

    /**
     * List of Tags
     */
    private final List<Tag> tags = new ArrayList<>();

    public Module() {
        if (!self.isAnnotationPresent(Mod.class))
            throw new ModuleInitException("@Mod annotation must be present if required parameters aren't passed through constructor");

        Mod data = self.getAnnotation(Mod.class);
        setup(data.name(), data.description(), data.bind());
    }

    public Module(String name, String description) {
        this(name, description, Keyboard.KEY_NONE);
    }

    public Module(String name, String description, int bind) {
        setup(name, description, bind);
    }

    private void setup(String name, String description, int bind) {
        this.name = name;
        this.description = description;

        this.bind = new Keybind(Keybind.Type.TOGGLE, bind, type -> {
            if (type == Keybind.Action.CLICK) Module.this.toggle();
        });

        this.type = Arrays.stream(self.getInterfaces())
                .filter(clazz -> clazz.isAnnotationPresent(Category.class))
                .findFirst().orElse(Category.Default.class);

        if (ClientAPIUtils.containsNull(name, description, type))
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

        if (mode == this.mode)
            return mode;

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
     * throws an {@code UnsupportedOperationException} if
     * modes aren't supported by this module.
     */
    private void checkModes() {
        if (!hasModes())
            throw new UnsupportedOperationException("Cannot use mode required actions when modes aren't supported");
    }

    @Override
    public final void setState(boolean state) {
        if (state == this.state)
            return;

        ModuleStateEvent event = new ModuleStateEvent(this, state);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            return;

        if (this.state = state) {
            onEnable();
            ClientAPI.EVENT_BUS.subscribe(this);
        } else {
            ClientAPI.EVENT_BUS.unsubscribe(this);
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

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final void addTag(Tag tag) {
        if (!getTag(tag).isPresent())
            tags.add(tag);
    }

    @Override
    public final void removeTag(String id) {
        getTag(id).ifPresent(tags::remove);
    }

    @Override
    public final boolean hasTag(String id) {
        return getTag(id).isPresent();
    }

    @Override
    public final Optional<Tag> getTag(String id) {
        return this.tags.stream().filter(tag -> tag.getID().equals(id)).findFirst();
    }

    @Override
    public final List<Tag> getTags() {
        return this.tags;
    }
}
