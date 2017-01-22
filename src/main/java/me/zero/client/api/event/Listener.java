package me.zero.client.api.event;

import me.zero.client.api.util.Messages;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Brady on 1/21/2017.
 */
final class Listener {

    private Class<?> type;
    private Object parent;
    private Method method;
    private byte priority;

    Listener(Class<?> type, Object parent, Method method, byte priority) {
        this.type = type;
        this.parent = parent;
        this.method = method;
    }

    Class<?> getType() {
        return this.type;
    }

    Object getParent() {
        return this.parent;
    }

    Method getMethod() {
        return method;
    }

    byte getPriority() {
        return priority;
    }

    void invoke(Object event) {
        try {
            method.invoke(parent, event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            Logger.instance.logf(Level.WARNING, Messages.EVENT_UNABLE_CALL, event);
        }
    }
}
