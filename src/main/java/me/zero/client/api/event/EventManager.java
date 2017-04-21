package me.zero.client.api.event;

import me.zero.client.api.event.type.EventPriority;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * EventManager, Used to handle all Event Flow
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public final class EventManager {

    /**
     * Prevents invokation
     */
    private EventManager() {}

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
     * Discovers all valid listeners from the Object
     * specified and then registers them in the
     * form of {@code Listeners}
     *
     * @see me.zero.client.api.event.Listener
     * @see #subscribe(Object, Field)
     *
     * @since 1.0
     *
     * @param object The object containing possible Event Listeners
     */
    public static void subscribe(Object object) {
        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(EventManager::isValidField)
                .forEach(field -> subscribe(object, field));
    }

    /**
     * Registers an array of Objects
     *
     * @see me.zero.client.api.event.Listener
     * @see #subscribe(Object)
     *
     * @since 1.0
     *
     * @param objects The array of objects
     */
    public static void subscribe(Object... objects) {
        Arrays.stream(objects).forEach(EventManager::subscribe);
    }

    /**
     * Registers a list of Objects
     *
     * @see me.zero.client.api.event.Listener
     * @see #subscribe(Object)
     *
     * @since 1.0
     *
     * @param objects The list of objects
     */
    public static void subscribe(List<Object> objects) {
        objects.forEach(EventManager::subscribe);
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
     * @param field Listener field
     */
    private static void subscribe(Object object, Field field) {
        Listener listener = (Listener) ReflectionUtils.getField(object, field);

        if (listener.getPriority() > EventPriority.LOWEST || listener.getPriority() < EventPriority.HIGHEST)
            throw new UnexpectedOutcomeException("Event Priority out of bounds! %s");

        subscribe(listener);
    }

    /**
     * Subscribes a Listener to the Subscription Map
     *
     * @since 1.0
     *
     * @param listener The listener being registered
     */
    private static void subscribe(Listener listener) {
        eventBuffer.clear();

        List<Listener> listeners = SUBSCRIPTION_MAP.get(listener.getTarget());
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
        SUBSCRIPTION_MAP.put(listener.getTarget(), new ArrayList<>(eventBuffer));
    }

    /**
     * Unregisters the Event Listeners from
     * the specified object
     *
     * @see #subscribe(Object)
     *
     * @since 1.0
     *
     * @param object The object being unsubscribed
     */
    public static void unsubscribe(Object object) {
        List<Listener> objectListeners = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(EventManager::isValidField)
                .map(field -> (Listener) ReflectionUtils.getField(object, field)).collect(Collectors.toList());

        SUBSCRIPTION_MAP.keySet().forEach(eventClass -> SUBSCRIPTION_MAP.put(eventClass,
                SUBSCRIPTION_MAP.get(eventClass).stream()
                        .filter(listener -> !objectListeners.contains(listener))
                        .collect(Collectors.toList())
        ));
    }

    /**
     * Unregisters an array of Objects
     *
     * @see me.zero.client.api.event.Listener
     * @see #unsubscribe(Object)
     *
     * @since 1.0
     *
     * @param objects The array of objects
     */
    public static void unsubscribe(Object... objects) {
        Arrays.stream(objects).forEach(EventManager::unsubscribe);
    }

    /**
     * Unregisters a list of Objects
     *
     * @see me.zero.client.api.event.Listener
     * @see #unsubscribe(Object)
     *
     * @since 1.0
     *
     * @param objects The list of objects
     */
    public static void unsubscribe(List<Object> objects) {
        objects.forEach(EventManager::unsubscribe);
    }

    /**
     * Checks if a Field is a valid Event Handler field.
     * Done by checking the field type and presence
     * of the {@code EventHandler} annotation;
     *
     * @see me.zero.client.api.event.EventHandler
     *
     * @since 1.0
     *
     * @param field Field being checked
     * @return Whether or not the Field is valid
     */
    private static boolean isValidField(Field field) {
        return field.isAnnotationPresent(EventHandler.class) && field.getType() == Listener.class;
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
    @SuppressWarnings("unchecked")
    public static void post(Object event) {
        List<Listener> listeners = SUBSCRIPTION_MAP.get(event.getClass());
        if (listeners != null)
            listeners.forEach(listener -> listener.invoke(event));
    }
}
