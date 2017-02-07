package me.zero.client.api.gui.tab.elements;

import me.zero.client.api.value.type.NumberType;
import org.lwjgl.input.Keyboard;

/**
 * Tab Gui Element for Number Values
 *
 * @see me.zero.client.api.value.annotation.NumberValue
 * @see me.zero.client.api.value.type.NumberType
 *
 * @since 1.0
 *
 * Created by Brady on 2/6/2017.
 */
public final class TabGuiValueNumber extends TabGuiValue<NumberType<?>> {

    /**
     * The Text Element that is used to display the current value
     */
    private TabGuiText textValue;

    public TabGuiValueNumber(NumberType<?> value) {
        super(value);

        elements.add(new TabGuiText(value.getMaximum().toString()));
        elements.add(textValue = new TabGuiText(""));
        elements.add(new TabGuiText(value.getMinimum().toString()));

        updateText();
    }

    @Override
    public void keyPress(int key) {
        boolean ctrl = Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
        int multiplier = ctrl ? 10 : 1;
        switch (key) {
            case Keyboard.KEY_UP :
                value.increment(multiplier);
                break;
            case Keyboard.KEY_DOWN :
                value.decrement(multiplier);
                break;
            default :
                super.keyPress(key);
                return;
        }
        updateText();
    }

    /**
     * Updates the value text element
     */
    private void updateText() {
        textValue.setText(String.format("%.2f", value.getValue().floatValue()));
    }
}
