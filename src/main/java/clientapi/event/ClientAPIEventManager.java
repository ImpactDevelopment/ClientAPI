package clientapi.event;

import me.zero.alpine.EventManager;

/**
 * Implementation of {@code EventManager} that allows the
 * user to enable/disable the post function in order to
 * allow/prevent events from being posted to their listeners.
 *
 * @author Brady
 * @since 4/21/2018 9:28 AM
 */
public final class ClientAPIEventManager extends EventManager {

    /**
     * Whether or not this EventManager is enabled. If this is set
     * to {@code false}, no events will be posted to their listeners.
     */
    private boolean enabled;

    public ClientAPIEventManager() {
        this.enabled = true;
    }

    @Override
    public final void post(Object event) {
        if (enabled) {
            super.post(event);
        }
    }

    /**
     * Sets whether or not this EventManager is enabled.
     *
     * @param enabled The new enabled state
     */
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return Whether or not this EventManager is enabled.
     */
    public final boolean isEnabled() {
        return this.enabled;
    }
}
