package clientapi.event;

import me.zero.alpine.EventBus;

/**
 * An implementation of {@code EventBus} where all inherited abstract methods
 * have been implemented with empty bodies, so that only required methods have
 * to be implemented. This is particularly useful in child event buses, where
 * not all methods may need to be implemented.
 *
 * @author Brady
 * @since 11/8/2017 4:58 PM
 */
public abstract class AbstractEventBus implements EventBus {

    @Override
    public void subscribe(Object object) {}

    @Override
    public void subscribe(Object... objects) {}

    @Override
    public void subscribe(Iterable<Object> objects) {}

    @Override
    public void unsubscribe(Object object) {}

    @Override
    public void unsubscribe(Object... objects) {}

    @Override
    public void unsubscribe(Iterable<Object> objects) {}

    @Override
    public void post(Object event) {}

    @Override
    public void attach(EventBus bus) {}

    @Override
    public void detach(EventBus bus) {}
}