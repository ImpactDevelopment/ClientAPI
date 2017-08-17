/*
 * Copyright 2017 ZeroMemes
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

package clientapi.gui;

import net.minecraft.client.gui.FontRenderer;

/**
 * Gives a class the ability to be rendered.
 * Rendering is not forced to be implemented.
 *
 * @author Brady
 * @since 2/6/2017 12:00 PM
 */
public interface IRenderer {

    /**
     * Renders this object
     *
     * @param x The X position of where this render object will start
     * @param y The Y position of where this render object will start
     * @param font The FontRenderer being used in drawing
     */
    default void render(float x, float y, FontRenderer font) {}
}
