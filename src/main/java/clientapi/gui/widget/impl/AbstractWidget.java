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

package clientapi.gui.widget.impl;

import clientapi.gui.widget.Widget;
import clientapi.gui.widget.data.WidgetAlignment;
import clientapi.gui.widget.data.WidgetPos;

/**
 * Implementation of Widget
 *
 * @see Widget
 *
 * @author Brady
 * @since 5/28/2017
 */
public abstract class AbstractWidget implements Widget {

    /**
     * Position of this widget
     */
    private WidgetPos pos;

    /**
     * Alignment of this widget
     */
    private WidgetAlignment alignment;

    /**
     * The width of this widget. This should be
     * set after the widget is finished rendering.
     */
    private float width;

    /**
     * The height of this widget. This should be
     * set after the widget is finished rendering.
     */
    private float height;

    /**
     * The visibility of this widget
     */
    private boolean visible = true;

    @Override
    public Widget setPos(WidgetPos pos) {
        this.pos = pos;
        return this;
    }

    @Override
    public Widget setAlignment(WidgetAlignment alignment) {
        this.alignment = alignment;
        return this;
    }

    @Override
    public Widget setWidth(float width) {
        this.width = width;
        return this;
    }

    @Override
    public Widget setHeight(float height) {
        this.height = height;
        return this;
    }

    @Override
    public Widget setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    @Override
    public WidgetPos getPos() {
        return this.pos;
    }

    @Override
    public WidgetAlignment getAlignment() {
        return this.alignment;
    }

    @Override
    public float getWidth() {
        return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }
}
