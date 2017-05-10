package me.zero.client.api.value.type;

import me.zero.client.api.util.interfaces.Toggleable;
import me.zero.client.api.value.Value;

import java.lang.reflect.Field;

/**
 * Basic type for Boolean values
 *
 * @see me.zero.client.api.value.annotation.BooleanValue
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
public final class BooleanType extends Value<Boolean> implements Toggleable {

    public BooleanType(String name, String id, String description, Object object, Field field) {
        super(name, id, description, object, field);
    }

    @Override
    public final void onEnable() {}

    @Override
    public final void onDisable() {}

    @Override
    public final void setState(boolean state) {
        this.setValue(state);

        if (this.getState()) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    @Override
    public final boolean getState() {
        return super.getValue();
    }
}
