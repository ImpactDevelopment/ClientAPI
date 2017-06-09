package me.zero.client.api.event;

import me.zero.alpine.EventManager;

/**
 * Gives more control to EventManager
 *
 * @author Brady
 * @since 6/9/2017 2:53 PM
 */
public final class CAPIEventManager extends EventManager {

    private boolean enabled = true;

    @Override
    public final void post(Object event) {
        if (!enabled)
            super.post(event);
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public final boolean isEnabled() {
        return this.enabled;
    }
}
