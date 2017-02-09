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
final class Listener implements IListener {

    /**
     * Event Class
     */
    private Class<?> target;

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

    Listener(Class<?> target, Object parent, Method method, byte priority) {
        this.target = target;
        this.parent = parent;
        this.method = method;
        this.priority = priority;
    }

    /**
     * @since 1.0
     *
     * @return Class that belongs to the Event that is being listened for
     */
    @Override
    public Class<?> getTarget() {
        return this.target;
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
    @Override
    public byte getPriority() {
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
    public void invoke(Object event) {
        try {
            method.invoke(parent, event);
        } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
            Logger.instance.logf(Level.WARNING, Messages.EVENT_UNABLE_CALL, event);
        }
    }
}
