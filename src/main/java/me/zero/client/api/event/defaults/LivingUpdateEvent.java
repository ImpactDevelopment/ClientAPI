package me.zero.client.api.event.defaults;

import me.zero.event.type.EventState;

/**
 * Called before and after EntityPlayerSP#onLivingUpdate()
 *
 * @author Brady
 * @since 2/10/2017 12:00 PM
 */
public final class LivingUpdateEvent {

    /**
     * The state of this event
     */
    private final EventState state;

    public LivingUpdateEvent(EventState state) {
        this.state = state;
    }

    /**
     * @return The event state
     */
    public final EventState getState() {
        return this.state;
    }
}
