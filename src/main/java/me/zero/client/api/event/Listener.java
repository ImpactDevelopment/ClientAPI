package me.zero.client.api.event;

import me.zero.client.api.event.type.EventPriority;
import net.jodah.typetools.TypeResolver;

import java.util.Arrays;
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
    private final Class<T> target;

    /**
     * The hook for this Listener
     */
    private final EventHook<T> hook;

    /**
     * Event filters
     */
    private final Predicate<T>[] filters;

    /**
     * Priority of Listener
     */
    private final byte priority;

    @SafeVarargs
    public Listener(EventHook<T> hook, Predicate<T>... filters) {
        this(hook, EventPriority.DEFAULT, filters);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public Listener(EventHook<T> hook, byte priority, Predicate<T>... filters) {
        this.hook = hook;
        this.priority = priority;
        this.target = (Class<T>) TypeResolver.resolveRawArgument(EventHook.class, hook.getClass());
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
    final byte getPriority() {
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
        if (Arrays.stream(filters).filter(filter -> !filter.test(event)).count() > 0)
            return;

        this.hook.invoke(event);
    }
}
