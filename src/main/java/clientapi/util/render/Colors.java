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

package clientapi.util.render;

import clientapi.util.interfaces.Helper;
import clientapi.util.math.MathUtils;

import java.awt.*;

/**
 * A collection of methods to help with color values
 *
 * @author Brady
 * @since 4/4/2017 12:00 PM
 */
public final class Colors implements Helper {

    private Colors() {}

    /**
     * Parses the RGBA values from a hex value
     *
     * @param hex The hex value
     * @return The parsed RGBA array
     */
    public static float[] getColor(int hex) {
        return new float[] {
                (hex >> 16 & 0xFF) / 255F,
                (hex >> 8 & 0xFF) / 255F,
                (hex & 0xFF) / 255F,
                (hex >> 24 & 0xFF) / 255F
        };
    }

    /**
     * Gets the hex value from a color code
     *
     * @param cc The color code
     * @return Hex corresponding to color code
     */
    public static int getColor(char cc) {
        return 0xFF000000 | mc.fontRenderer.getColorCode(cc);
    }

    /**
     * Blends two colors with the specified blend amount (percentage)
     *
     * @param c1 First color
     * @param c2 Second color
     * @param perc Amount of the first color that remains.
     *             1.0 = 100% first color
     *             0.5 = equally blended
     *             0.0 = 100% second color
     * @return
     */
    public static int blend(int c1, int c2, float perc) {
        float inverse = 1.0F - perc;
        float[] color1 = getColor(c1);
        float[] color2 = getColor(c2);
        return Colors.getColor(
                color1[0] * perc + color2[0] * inverse,
                color1[1] * perc + color2[1] * inverse,
                color1[2] * perc + color2[2] * inverse,
                color1[3] * perc + color2[3] * inverse
        );
    }

    /**
     * Parses the hex value from RGB/RGBA values
     *
     * @param color The RGB/RGBA array
     * @return The parsed hex value
     */
    public static int getColor(float[] color) {
        if (color.length >= 3)
            return getColor(color[0], color[1], color[2], color.length > 3 ? color[3] : 1.0F);

        return -1;
    }

    /**
     * Parses the hex value from RGB
     *
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @return The parsed hex value
     */
    public static int getColor(float r, float g, float b) {
        return getColor(r, g, b, 1.0F);
    }

    /**
     * Parses the hex value from RGB
     *
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @return The parsed hex value
     */
    public static int getColor(int r, int g, int b) {
        return getColor(r, g, b, 255);
    }

    /**
     * Parses the hex value from RGBA
     *
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @param a Alpha value
     * @return The parsed hex value
     */
    public static int getColor(float r, float g, float b, float a) {
        return getColor((int) (r * 255F), (int) (g * 255F), (int) (b * 255F), (int) (a * 255F));
    }

    /**
     * Parses the hex value from RGBA
     *
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @param a Alpha value
     * @return The parsed hex value
     */
    public static int getColor(int r, int g, int b, int a) {
        r = clamp(r);
        g = clamp(g);
        b = clamp(b);
        a = clamp(a);

        return ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8) |
                (b & 0xFF);
    }

    /**
     * Returns the current rainbow hex
     *
     * @return Rainbow hex
     */
    public static int rainbow() {
        return rainbow(0);
    }

    /**
     * Returns the current rainbow hex with custom
     * saturation and brightness.
     *
     * @param saturation The color saturation applied (0.0 -> 1.0)
     * @param brightness The color brightness applied (0.0 -> 1.0)
     * @return Rainbow hex
     */
    public static int rainbow(float saturation, float brightness) {
        return rainbow(saturation, brightness, 0);
    }

    /**
     * Returns the current rainbow hex. An extra
     * parameter, offset is used to offset the
     * System current millisecond time
     *
     * @param offset The offset added onto the current millisecond time
     * @return Rainbow hex
     */
    public static int rainbow(int offset) {
        return rainbow(1.0F, 1.0F, offset);
    }

    /**
     * Returns the current rainbow hex with custom
     * saturation and brightness. An extra parameter,
     * offset is used to offset the System current
     * millisecond time
     *
     * @param saturation The color saturation applied (0.0 -> 1.0)
     * @param brightness The color brightness applied (0.0 -> 1.0)
     * @param offset The offset added onto the current millisecond time
     * @return Rainbow hex
     */
    public static int rainbow(float saturation, float brightness, int offset) {
        float hue = ((System.currentTimeMillis() + offset) % 1000) / 1000F;
        return Color.getHSBColor(hue, saturation, brightness).getRGB();
    }

    /**
     * Returns a random color, that has an
     * RGB color range of 0 to 255
     *
     * @return A random color
     */
    public static int random() {
        return random(0, 255);
    }

    /**
     * Returns a random color that has the specified
     * RGB color range, as a float (0.0 to 1.0)
     *
     * @param min Minimum R/G/B
     * @param max Maximum R/G/B
     * @return A random color
     */
    public static int random(float min, float max) {
        return random((int) (min * 255F), (int) (max) * 255F);
    }

    /**
     * Returns a random color that has the specified
     * RGB color range, as an integer (0 to 255)
     *
     * @param min Minimum R/G/B
     * @param max Maximum R/G/B
     * @return A random color
     */
    public static int random(int min, int max) {
        min = clamp(min);
        max = clamp(max);
        return Colors.getColor(MathUtils.random(min, max), MathUtils.random(min, max), MathUtils.random(min, max));
    }

    /**
     * Clamps a float color channel value to be within
     * the 0.0 to 1.0 bounds.
     *
     * @param val Color value
     * @return Clamped value
     */
    public static float clamp(float val) {
        return MathUtils.clamp(val, 0.0F, 1.0F);
    }

    /**
     * Clamps a integer color channel value to be within
     * the 0 to 255 bounds.
     *
     * @param val Color value
     * @return Clamped value
     */
    public static int clamp(int val) {
        return MathUtils.clamp(val, 0, 255);
    }
}
