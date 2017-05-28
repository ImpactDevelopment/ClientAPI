package me.zero.client.api.gui.widget.data;

/**
 * Used to calculate the X offset (Alignment)
 * of widgets. The X value of a widget is increased
 * by the widget's width times the value returned
 * by its alignment.
 *
 * @see DefaultWidgetAlignment
 *
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public interface WidgetAlignment {

    float getValue();
}
