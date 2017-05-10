package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.EventState;

/**
 * Called before and after EntityPlayerSP#onLivingUpdate()
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/10/2017 12:00PM
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
     * @since 1.0
     *
     * @return The event state
     */
    public final EventState getState() {
        return this.state;
    }
}
