package me.zero.client.integration;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.util.interfaces.Loadable;

/**
 * Base for Integration Objects
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
public class Integration implements Loadable {

    @Override
    public final void load() {
        EventManager.subscribe(this);
    }
}
