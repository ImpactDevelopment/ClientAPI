package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
import me.zero.client.api.module.Module;

/**
 * Called when a Module's state is changed
 *
 * @since 1.0
 *
 * Created by Brady on 4/5/2017.
 */
public final class ModuleStateEvent extends Cancellable {

    /**
     * Module having a change in state
     */
    private final Module module;

    /**
     * New state of the module
     */
    private final boolean newState;

    public ModuleStateEvent(Module module, boolean newState) {
        this.module = module;
        this.newState = newState;
    }

    /**
     * @since 1.0
     *
     * @return The module having a change in state
     */
    public Module getModule() {
        return this.module;
    }

    /**
     * @since 1.0
     *
     * @return The new state of the Module
     */
    public boolean getNewState() {
        return this.newState;
    }
}
