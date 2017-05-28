package me.zero.client.api.event.defaults;

import me.zero.alpine.type.Cancellable;
import me.zero.client.api.module.Module;

/**
 * Called when a Module's state is changed. If the
 * event is cancelled, the state is not changed.
 *
 * @see Module#setState(boolean)
 *
 * @author Brady
 * @since 4/5/2017 12:00 PM
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
     * @return The module having a change in state
     */
    public final Module getModule() {
        return this.module;
    }

    /**
     * @return The new state of the Module
     */
    public final boolean getNewState() {
        return this.newState;
    }
}
