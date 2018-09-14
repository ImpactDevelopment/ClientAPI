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

import clientapi.gui.widget.data.WidgetPos;
import clientapi.util.math.Vec2;
import clientapi.util.render.RenderUtils;
import clientapi.util.render.gl.DisplayList;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_COMPILE;

/**
 * Used to manage and render widgets
 *
 * @author Brady
 * @since 5/28/2017
 */
public final class WidgetHandler {

    /**
     * List of all the widgets loaded into the handler
     */
    private final List<Widget> widgets = new ArrayList<>();

    /**
     * {@link WidgetHandler#widgets} sorted by category
     */
    private final Map<WidgetPos, List<Widget>> widgetMap = new HashMap<>();

    /**
     * The Open GL instruction list used when rendering
     */
    private final DisplayList list = new DisplayList(1);

    private float padding, spacing, position;

    /**
     * When {@code true}, a white outline will be rendered around
     * all widget areas,
     */
    private boolean outlines;

    public WidgetHandler() {
        list.gen();
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

            GlStateManager.pushMatrix();
            Vec2 screenPos = pos.getScreenPos(sr);
            GlStateManager.translate(screenPos.getX(), screenPos.getY(), 0.0F);

            // Reset current position
            position = 0.0F;

            // We write to a list so that the height can
            // be updated and the required vertical adjustment
            // can be made before we actually render the widgets
            list.start(GL_COMPILE);
            widgets.forEach(widget -> {
                float mP = (widget.getAlignment().getValue() + 0.5F != 0.0F) ? 1.0F : 0.0F;
                float xP = pos.getPadding().getX() * padding * mP;
                float yP = pos.getPadding().getY() * padding;

                GlStateManager.pushMatrix();
                GlStateManager.translate(widget.getAlignment().getValue() * widget.getWidth() + xP, yP, 0.0F);
                if (outlines)
                    RenderUtils.rectangleBordered(0, 0, widget.getWidth(), widget.getHeight(), 0xFFFFFFFF, 0x00000000);

                widget.render(font, sr);
                GlStateManager.popMatrix();

                GlStateManager.translate(0.0F, widget.getHeight() + spacing, 0.0F);
                position += widget.getHeight();
            });
            list.stop();

            position += spacing * (widgets.size() - 1);
            GlStateManager.translate(0.0F, position * pos.getOffset(), 0.0F);

            // Render all of the widgets
            list.call();

            GlStateManager.popMatrix();
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
