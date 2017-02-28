package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;

/**
 * Called when the enchanted effect of a
 * layer or an item is being rendered.
 *
 * @since 1.0
 *
 * Created by Brady on 2/19/2017.
 */
public final class GlintEffectEvent extends Cancellable {

    /**
     * The object getting a glint effect applied to it
     */
    private GlintTarget target;

    public GlintEffectEvent(GlintTarget target) {
        this.target = target;
    }

    /**
     * @since 1.0
     *
     * @return The glint object
     */
    public GlintTarget getTarget() {
        return this.target;
    }

    /**
     * Glint Object
     */
    public enum GlintTarget {
        ARMOR, ITEM
    }
}
