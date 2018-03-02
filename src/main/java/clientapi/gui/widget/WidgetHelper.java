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

package clientapi.gui.widget;

import clientapi.util.math.Vec2;

/**
 * Contains some methods to make required
 * calculations for widgets.
 *
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public final class WidgetHelper {

    private WidgetHelper() {}

    /**
     * Calculates the padding that should be applied
     * to the given widget position (as a Vec2)
     *
     * @param pos The widget position as a Vec2
     * @return The padding
     */
    public static Vec2 getPadding(Vec2 pos) {
        return new Vec2(getPadding(pos.getX()), getPadding(pos.getY()));
    }

    /**
     * Gets the padding applied from the specified screen pos.
     *
     * @param pos The screen pos (0.0/left to 1.0/right)
     * @return The calculated padding
     */
    private static float getPadding(float pos) {
        return pos == 0.0F ? 1.0F : pos == 1.0F ? -1.0F : 0.0F;
    }
}
