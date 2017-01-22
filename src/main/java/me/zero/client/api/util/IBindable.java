package me.zero.client.api.util;

import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brady on 1/21/2017.
 */
public interface IBindable {

    Map<IBindable, Integer> bindingMap = new HashMap<>();

    default void setBind(int bind) {
        bindingMap.put(this, bind);
    }

    default int getBind() {
        bindingMap.putIfAbsent(this, Keyboard.KEY_NONE);
        return bindingMap.get(this);
    }
}
