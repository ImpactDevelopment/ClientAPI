package clientapi.event;

import me.zero.alpine.EventBus;

/**
 * @author Brady
 * @since 11/8/2017 4:58 PM
 */
public class AbstractEventBus implements EventBus {

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