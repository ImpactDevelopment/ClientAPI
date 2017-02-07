package me.zero.client.api.gui.tab.elements;

import me.zero.client.api.gui.tab.impl.TabGuiMenu;
import me.zero.client.api.value.Value;

/**
 * A Tab Gui Element for Values
 *
 * @since 1.0
 *
 * Created by Brady on 2/6/2017.
 */
class TabGuiValue<T extends Value> extends TabGuiMenu {

    /**
     * Value represented by this Tab Gui Element
     */
    protected T value;

    public TabGuiValue(T value) {
        super (value.getName());
        this.value = value;
    }

    /**
     * @since 1.0
     *
     * @return Value represented by this Element
     */
    public T getValue() {
        return this.value;
    }
}
