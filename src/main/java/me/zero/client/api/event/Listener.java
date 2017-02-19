package me.zero.client.api.event;

import me.zero.client.api.event.defaults.Render2DEvent;
import me.zero.client.api.event.type.EventPriority;
import net.jodah.typetools.TypeResolver;
import net.minecraft.client.renderer.GlStateManager;

/**
 * Used to contain event method data
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public final class Listener<T> implements EventHook<T> {

    /**
     * Class representation of the Event being
     * listened for.
     */
    private Class<T> target;

    /**
     * The hook for this Listener
     */
    private EventHook<T> hook;

    /**
     * Priority of Listener
     */
    private byte priority;

    public Listener(EventHook<T> hook) {
        this(hook, EventPriority.DEFAULT);
    }

    @SuppressWarnings("unchecked")
    public Listener(EventHook<T> hook, byte priority) {
        this.hook = hook;
        this.priority = priority;
        this.target = (Class<T>) TypeResolver.resolveRawArguments(EventHook.class, hook.getClass())[0];
    }

    /**
     * @since 1.0
     *
     * @return The class of T
     */
    public final Class<T> getTarget() {
        return this.target;
    }

    /**
     * @since 1.0
     *
     * @return Priority of Listener
     */
    public final byte getPriority() {
        return priority;
    }

    /**
     * Invokes the method that corresponds
     * with this Listener.
     *
     * @since 1.0
     *
     * @param event Event being called
     */
    @Override
    public final void invoke(T event) {
        this.hook.invoke(event);
    }
}
