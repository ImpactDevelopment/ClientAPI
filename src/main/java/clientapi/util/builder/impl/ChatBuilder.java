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

package clientapi.util.builder.impl;

import clientapi.util.builder.Builder;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * Used to build {@code ITextComponent}. Better alternative
 * to appending the control codes to text strings.
 *
 * @author Brady
 * @since 9/7/2017 7:20 PM
 */
public final class ChatBuilder implements Builder<ITextComponent> {

    /**
     * Component being appended upon.
     */
    private final ITextComponent component = new TextComponentString("");

    public final ChatBuilder append(ITextComponent component) {
        this.component.appendSibling(component);
        return this;
    }

    public final ChatBuilder append(String text) {
        this.component.appendSibling(new TextComponentString(text));
        return this;
    }

    /**
     * Appends a string with the specified color(s)
     *
     * @param text The string being appended
     * @param colors Color formatting
     * @return This builder
     */
    public final ChatBuilder append(String text, TextFormatting... colors) {
        this.append(text, null, null, colors);
        return this;
    }

    /**
     * Appends a string with the specified color(s)
     * and an event triggered on text hover.
     *
     * @param text The string being appended
     * @param hoverEvent Hover event to be used
     * @param colors Color formatting
     * @return This builder
     */
    public final ChatBuilder append(String text, HoverEvent hoverEvent, TextFormatting... colors) {
        this.append(text, null, hoverEvent, colors);
        return this;
    }

    /**
     * Appends a string with the specified color(s)
     * and an event triggered on text click.
     *
     * @param text The string being appended
     * @param clickEvent Click event to be used
     * @param colors Color formatting
     * @return This builder
     */
    public final ChatBuilder append(String text, ClickEvent clickEvent, TextFormatting... colors) {
        this.append(text, clickEvent, null, colors);
        return this;
    }

    /**
     * Appends a string with the specified color(s) and an event
     * triggered on text click and on text hover. Only one actual
     * color should be specified, any other {@code TextFormatting}
     * type should be styling. (Bold, Italic, Underline, Strikethrough,
     * and Obfuscated)
     *
     * @param text The string being appended
     * @param clickEvent Click event to be used
     * @param hoverEvent Hover event to be used
     * @param colors Color formatting
     * @return This builder
     */
    public final ChatBuilder append(String text, @Nullable ClickEvent clickEvent, @Nullable HoverEvent hoverEvent, TextFormatting... colors) {
        TextComponentString component = new TextComponentString(text);
        Style style = component.getStyle();
        Arrays.stream(colors).forEach(color -> {
            switch (color) {
                case BOLD: {
                    style.setBold(true);
                    break;
                }
                case ITALIC: {
                    style.setItalic(true);
                    break;
                }
                case UNDERLINE: {
                    style.setUnderlined(true);
                    break;
                }
                case STRIKETHROUGH: {
                    style.setStrikethrough(true);
                    break;
                }
                case OBFUSCATED: {
                    style.setObfuscated(true);
                    break;
                }
                default: {
                    style.setColor(color);
                    break;
                }
            }
        });
        // noinspection ConstantConditions
        style.setClickEvent(clickEvent).setHoverEvent(hoverEvent);
        this.component.appendSibling(component);
        return this;
    }

    /**
     * Returns the current text component. Can be modified
     * after the {@code build} method has been called.
     *
     * @return The created text component
     */
    @Override
    public ITextComponent build() {
        return this.component;
    }
}
