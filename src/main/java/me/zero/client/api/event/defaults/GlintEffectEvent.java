package me.zero.client.api.event.defaults;

import me.zero.event.type.Cancellable;

/**
 * Called when the enchanted effect of a
 * layer or an item is being rendered.
 *
 * @author Brady
 * @since 2/19/2017 12:00 PM
 */
public final class GlintEffectEvent extends Cancellable {

    /**
     * The object getting a glint effect applied to it
     */
    private final GlintTarget target;

    public GlintEffectEvent(GlintTarget target) {
        this.target = target;
    }

    /**
     * @return The glint object
     */
    public final GlintTarget getTarget() {
        return this.target;
    }

    /**
     * Glint Object
     */
    public enum GlintTarget {
        ARMOR, ITEM
    }
}
