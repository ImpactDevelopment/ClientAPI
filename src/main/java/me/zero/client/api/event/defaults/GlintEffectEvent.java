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
public class GlintEffectEvent extends Cancellable {

    private GlintTarget target;

    public GlintEffectEvent(GlintTarget target) {
        this.target = target;
    }

    public GlintTarget getTarget() {
        return this.target;
    }

    public enum GlintTarget {
        ARMOR, ITEM
    }
}
