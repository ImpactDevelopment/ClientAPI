/*
 * Copyright 2017 ImpactDevelopment
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
import clientapi.util.interfaces.Helper;
import clientapi.util.math.Vec2;

/**
 * Default implementations of {@code WidgetPos}
 *
 * @see WidgetPos`
 *
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public enum DefaultWidgetPos implements WidgetPos, Helper {

    LEFT_TOP(0.0F, 0.0F, 0.0F),
    LEFT_MIDDLE(0.0F, 0.5F, -0.5F),
    LEFT_BOTTOM(0.0F, 1.0F, -1.0F),

    CENTER_TOP(0.5F, 0.0F, 0.0F),
    CENTER_MIDDLE(0.5F, 0.5F, -0.5F),
    CENTER_BOTTOM(0.5F, 1.0F, -1.0F),

    RIGHT_TOP(1.0F, 0.0F, 0.0F),
    RIGHT_MIDDLE(1.0F, 0.5F, -0.5F),
    RIGHT_BOTTOM(1.0F, 1.0F, -1.0F);

    private final Vec2 pos;
    private final Vec2 padding;
    private final float offset;

    DefaultWidgetPos(float x, float y, float offset) {
        this.pos = new Vec2(x, y);
        this.padding = WidgetHelper.getPadding(pos);
        this.offset = offset;
    }

    @Override
    public final Vec2 getPos() {
        return this.pos;
    }

    @Override
    public final Vec2 getPadding() {
        return this.padding;
    }

    @Override
    public final float getOffset() {
        return this.offset;
    }
}
