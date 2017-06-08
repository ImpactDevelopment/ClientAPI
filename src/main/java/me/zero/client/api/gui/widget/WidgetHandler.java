/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.gui.widget;

import me.zero.client.api.gui.widget.data.WidgetPos;
import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.render.RenderUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import java.util.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Used to manage and render widgets
 *
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public final class WidgetHandler {

    /**
     * List of all the widgets loaded into the handler
     */
    private final List<Widget> widgets = new ArrayList<>();

    /**
     * {@code WidgetHandler#widgets} sorted by category
     */
    private final Map<WidgetPos, List<Widget>> widgetMap = new HashMap<>();

    /**
     * The Open GL instruction list used when rendering
     */
    private final int list;

    private float padding, spacing, position;

    /**
     * When {@code true}, a white outline will be rendered around
     * all widget areas,
     */
    private boolean outlines;

    public WidgetHandler() {
        list = glGenLists(1);
    }

    /**
     * Renders all of the widgets on a screen with the specified
     * scaled resolution using the specified font renderer.
     *
     * @param font The font that is passed to the widgets
     * @param sr The scaled resolution used to calculate widget pos
     */
    public final void draw(FontRenderer font, ScaledResolution sr) {
        widgetMap.forEach((pos, widgets) -> widgets.clear());
        widgets.forEach(widget -> {
            List<Widget> list = widgetMap.get(widget.getPos());
            if (list == null)
                list = new ArrayList<>();

            if (!widget.isVisible())
                return;

            list.add(widget);
            widgetMap.put(widget.getPos(), list);
        });

        RenderUtils.setupRender(true);

        widgetMap.forEach((pos, widgets) -> {
            if (widgets.isEmpty())
                return;

            glPushMatrix();
            Vec2 screenPos = pos.getScreenPos(sr);
            glTranslatef(screenPos.getX(), screenPos.getY(), 0.0F);

            // Reset current position
            position = 0.0F;

            // We write to a list so that the height can
            // be updated and the required vertical adjustment
            // can be made before we actually render the widgets
            glNewList(list, GL_COMPILE);
            widgets.forEach(widget -> {
                float mP = (widget.getAlignment().getValue() + 0.5F != 0.0F) ? 1.0F : 0.0F;
                float xP = pos.getPadding().getX() * padding * mP;
                float yP = pos.getPadding().getY() * padding;

                glPushMatrix();
                glTranslatef(widget.getAlignment().getValue() * widget.getWidth() + xP, yP, 0.0F);
                if (outlines)
                    RenderUtils.rectangleBordered(0, 0, widget.getWidth(), widget.getHeight(), 0xFFFFFFFF, 0x00000000);

                widget.render(font, sr);
                glPopMatrix();

                glTranslatef(0.0F, widget.getHeight() + spacing, 0.0F);
                position += widget.getHeight();
            });
            glEndList();

            position += spacing * (widgets.size() - 1);
            glTranslatef(0.0F, position * pos.getOffset(), 0.0F);

            // Render all of the widgets
            glCallList(list);

            glPopMatrix();
        });

        RenderUtils.setupRender(false);
    }

    /**
     * Adds a widget, assuming that the
     * specified widget isn't already
     * registered with this handler.
     *
     * @param widget The widget to remove
     */
    public final void add(Widget widget) {
        if (!widgets.contains(widget))
            widgets.add(widget);
    }

    /**
     * Removes a widget, assuming that the
     * specified widget is already registered
     * with this handler.
     *
     * @param widget The widget to remove
     */
    public final void remove(Widget widget) {
        if (widgets.contains(widget))
            widgets.remove(widget);
    }

    /**
     * Sets the outline flag
     *
     * @param outlines New outline flag value
     */
    public final void setOutlines(boolean outlines) {
        this.outlines = outlines;
    }

    /**
     * Sets the border padding amount
     *
     * @param padding New border padding amount
     */
    public final void setPadding(float padding) {
        this.padding = padding;
    }

    /**
     * Sets the vertical widget spacing
     *
     * @param spacing New vertical widget spacing
     */
    public final void setSpacing(float spacing) {
        this.spacing = spacing;
    }
}
