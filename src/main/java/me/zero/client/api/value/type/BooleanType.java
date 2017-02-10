package me.zero.client.api.value.type;

import me.zero.client.api.util.interfaces.Toggleable;
import me.zero.client.api.value.Value;

import java.lang.reflect.Field;

/**
 * Basic type for Boolean values
 *
 * @see me.zero.client.api.value.annotation.BooleanValue
 *
 * @since 1.0
 *
 * Created by Brady on 1/23/2017.
 */
public class BooleanType extends Value<Boolean> implements Toggleable {

    public BooleanType(String name, Object object, Field field) {
        super(name, object, field);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void toggle() {
        this.setState(!this.getState());
    }

    @Override
    public void setState(boolean state) {
        this.setValue(state);

        if (this.getState()) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    @Override
    public boolean getState() {
        return super.getValue();
    }
}
