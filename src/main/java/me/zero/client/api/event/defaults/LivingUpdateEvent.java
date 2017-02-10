package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.EventState;

/**
 * Called before and after EntityPlayerSP#onLivingUpdate()
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
public final class LivingUpdateEvent {

    private final EventState state;

    public LivingUpdateEvent(EventState state) {
        this.state = state;
    }

    public EventState getState() {
        return this.state;
    }
}
