package me.zero.client.api.event;

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

    private static final Map<Class<?>, List<Listener>> SUBSCRIPTION_MAP = new HashMap<>();
    private static final List<Listener> eventBuffer = new ArrayList<>();

    public static void subscribe(Object object) {
        for (Method method : object.getClass().getDeclaredMethods())
            if (isValidMethod(method))
                subscribe(object, method);
    }

    private static void subscribe(Object object, Method method) {
        Class<?> eventClass = method.getParameterTypes()[0];
        EventHandler eventHandler = method.getAnnotation(EventHandler.class);
        Listener listener = new Listener(eventClass, object, method, eventHandler.value());

        if (!method.isAccessible())
            method.setAccessible(true);

        subscribe(listener);
    }

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

    private static boolean isValidMethod(Method method) {
        return method.isAnnotationPresent(EventHandler.class) && method.getParameterTypes().length == 1;
    }

    public static void post(Object event) {
        List<Listener> listeners = SUBSCRIPTION_MAP.get(event.getClass());
        if (listeners != null)
            listeners.forEach(listener -> listener.invoke(event));
    }
}
