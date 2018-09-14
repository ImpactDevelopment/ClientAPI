/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.gui.widget.data;

import clientapi.gui.widget.WidgetHelper;
import clientapi.util.math.Vec2;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Stores the data that is used to
 * calculate the position that widgets
 * are rendered at.
 *
 * @see DefaultWidgetPos
 *
 * @author Brady
 * @since 5/28/2017
 */
public interface WidgetPos {

    /**
     * Returns the position of this widget. Both
     * the X and Y value of the Vec2 should range
     * from 0.0 to 1.0 to represent the decimal amount
     * of the scaled resolution that this widget is
     * placed at. That means that 0.0 would be the
     * leftmost part of your screen and 1.0 would be
     * the rightmost part of your screen.
     *
     * @return The position of this widget
     */
    Vec2 getPos();

    /**
     * Takes this widget's pos and translates it to
     * the scaled position on the screen. This is done
     * by multiplying the Vec2 returned by {@link WidgetPos#getPos}
     * by the scaled resolution X and Y values.
     *
     * @param sr The scaled resolution used to calculate the screen pos
     * @return The screen pos
     */
    default Vec2 getScreenPos(ScaledResolution sr) {
        float screenX = getPos().getX() * sr.getScaledWidth();
        float screenY = getPos().getY() * sr.getScaledHeight();
        return new Vec2(screenX, screenY);
    }

    /**
     * Returns the amount of padding that is applied
     * to the X and Y values of this widget. This should
     * be based on the position of the widget, because
     * widgets bordering the side of the screen should
     * be the only ones affected. The padding is represented
     * by the decimal amount of the handler padding that
     * is added onto a widget's position when it's rendered.
     * This means that anything bordering the left side of
     * the screen will have a padding X value of 1.0, and
     * anything bordering the right side of the screen will
     * have a padding X value of -1.0.
     *
     * @see WidgetHelper#getPadding(Vec2)
     *
     * @return The padding of this widget.
     */
    Vec2 getPadding();

    /**
     * Returns the vertical offset that is multiplied
     * by the collection of Widgets with the same position.
     * Widgets at the top of the screen would have an offset of
     * 0.0, widgets in the middle of the screen would have an
     * offset of -0.5, and widgets at the bottom of the screen
     * would have an offset of -1.0.
     *
     * @return The vertical offset
     */
    float getOffset();
}
