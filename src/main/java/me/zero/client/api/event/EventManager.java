package me.zero.client.api.event;

import me.zero.client.api.exception.UnexpectedOutcomeException;

import java.lang.reflect.Method;
import java.util.*;

/**
 * EventManager, Used to handle all Event Flow
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public final class EventManager {

    /**
     * HashMap containing all Listeners for various Events
     */
    private static final Map<Class<?>, List<Listener>> SUBSCRIPTION_MAP = new HashMap<>();

    /**
     * Buffer used while making modifications to {@code SUBSCRIPTION_MAP}
     *
     * @see #subscribe(Object)
     * @see #unsubscribe(Object)
     */
    private static final List<Listener> eventBuffer = new ArrayList<>();

    /**
     * Discovers all valid methods from the Object
     * specified and then registers them in the
     * form of {@code Listeners}
     *
     * @see me.zero.client.api.event.Listener
     * @see #subscribe(Object, Method)
     *
     * @since 1.0
     *
     * @param object The object containing possible Event Methods
     */
    public static void subscribe(Object object) {
        for (Method method : object.getClass().getDeclaredMethods())
            if (isValidMethod(method))
                subscribe(object, method);
    }

    /**
     * Creates a listener from the specified object and method.
     * After the listener is created, it is passed to the listener
     * subscription method.
     *
     * @see #subscribe(Listener)
     *
     * @since 1.0
     *
     * @param object Parent object
     * @param method Event method
     */
    private static void subscribe(Object object, Method method) {
        Class<?> eventClass = method.getParameterTypes()[0];
        EventHandler eventHandler = method.getAnnotation(EventHandler.class);
        Listener listener = new Listener(eventClass, object, method, eventHandler.value());

        if (listener.getPriority() > EventPriority.LOWEST || listener.getPriority() < EventPriority.HIGHEST)
            throw new UnexpectedOutcomeException("Event Priority out of bounds! %s");

        if (!method.isAccessible())
            method.setAccessible(true);

        subscribe(listener);
    }

    /**
     * Subscribes a Listener to the Subscription Map
     *
     * @since 1.0
     *
     * @param listener The listener being registered
     */
    private static void subscribe(Listener listener){
        eventBuffer.clear();

        List<Listener> listeners = SUBSCRIPTION_MAP.get(listener.getType());
        if (listeners != null && listeners.size() > 0)
            eventBuffer.addAll(listeners);

        int index = 0;
        if (!eventBuffer.isEmpty()) {
            for (; index < eventBuffer.size(); index++) {
                if (listener.getPriority() < eventBuffer.get(index).getPriority()) {
                    break;
                }
            }
        }
        eventBuffer.add(index, listener);
        SUBSCRIPTION_MAP.put(listener.getType(), eventBuffer);
    }

    /**
     * Unregisters the Event Listeners from
     * the specified object
     *
     * @see #subscribe(Object)
     *
     * @since 1.0
     *
     * @param object
     */
    public static void unsubscribe(Object object) {
        for (Class<?> eventClass : SUBSCRIPTION_MAP.keySet()) {
            List<Listener> listeners = SUBSCRIPTION_MAP.get(eventClass);
            eventBuffer.clear();
            for (Listener listener : listeners) {
                if (!listener.getParent().equals(object)) {
                    eventBuffer.add(listener);
                }
            }
            SUBSCRIPTION_MAP.put(eventClass, eventBuffer);
        }
    }

    /**
     * Checks if a Method is a valid Event Handler method.
     * Done by checking the parameter count and presence
     * of the {@code EventHandler} annotation;
     *
     * @see me.zero.client.api.event.EventHandler
     *
     * @since 1.0
     *
     * @param method Method being checked
     * @return Whether or not the Method is valid
     */
    private static boolean isValidMethod(Method method) {
        return method.isAnnotationPresent(EventHandler.class) && method.getParameterTypes().length == 1;
    }

    /**
     * Posts an event to all registered {@code Listeners}.
     * Done via Reflection Method Invokation
     *
     * @see me.zero.client.api.event.Listener#invoke(Object)
     *
     * @since 1.0
     *
     * @param event Event being called
     */
    public static void post(Object event) {
        List<Listener> listeners = SUBSCRIPTION_MAP.get(event.getClass());
        if (listeners != null)
            listeners.forEach(listener -> listener.invoke(event));
    }
}
