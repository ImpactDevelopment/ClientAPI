package me.zero.client.api.gui.widget.data;

/**
 * Default implementations of {@code WidgetAlignment}
 *
 * @see WidgetAlignment
 *
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public enum DefaultWidgetAlignment implements WidgetAlignment {

    LEFT(-1.0F),
    CENTERED(-0.5F),
    RIGHT(0.0F);

    private float value;

    DefaultWidgetAlignment(float value) {
        this.value = value;
    }

    @Override
    public final float getValue() {
        return this.value;
    }
}
