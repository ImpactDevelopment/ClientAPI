package me.zero.client.api.event;

import me.zero.client.api.util.Messages;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Used to contain event method data
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
final class Listener {

    /**
     * Event Class
     */
    private Class<?> type;

    /**
     * Object that Method is contained within
     */
    private Object parent;

    /**
     * The method itself
     */
    private Method method;

    /**
     * Priority of Listener
     */
    private byte priority;

    Listener(Class<?> type, Object parent, Method method, byte priority) {
        this.type = type;
        this.parent = parent;
        this.method = method;
    }

    /**
     * @since 1.0
     *
     * @return Class that belongs to the Event that is being listened for
     */
    Class<?> getType() {
        return this.type;
    }

    /**
     * @since 1.0
     *
     * @return Parent, Object that contains the method
     */
    Object getParent() {
        return this.parent;
    }

    /**
     * @since 1.0
     *
     * @return Method that is waiting for an event to be passed down
     */
    Method getMethod() {
        return method;
    }

    /**
     * @since 1.0
     *
     * @return Priority of Listener
     */
    byte getPriority() {
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
    void invoke(Object event) {
        try {
            method.invoke(parent, event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            Logger.instance.logf(Level.WARNING, Messages.EVENT_UNABLE_CALL, event);
        }
    }
}
