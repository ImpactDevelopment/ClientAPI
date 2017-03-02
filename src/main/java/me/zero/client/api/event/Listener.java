package me.zero.client.api.event;

import me.zero.client.api.event.type.EventPriority;
import net.jodah.typetools.TypeResolver;

import java.util.function.Predicate;

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
     * Event filters
     */
    private Predicate<T>[] filters;

    /**
     * Priority of Listener
     */
    private byte priority;

    public Listener(EventHook<T> hook, Predicate<T>... filters) {
        this(hook, EventPriority.DEFAULT, filters);
    }

    @SuppressWarnings("unchecked")
    public Listener(EventHook<T> hook, byte priority, Predicate<T>... filters) {
        this.hook = hook;
        this.priority = priority;
        this.target = (Class<T>) TypeResolver.resolveRawArguments(EventHook.class, hook.getClass())[0];
        this.filters = filters;
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
    @SuppressWarnings("unchecked")
    public final void invoke(T event) {
        for (Predicate filter : filters)
            if (!filter.test(event))
                return;

        this.hook.invoke(event);
    }
}
