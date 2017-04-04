package me.zero.client.api.util.render;

/**
 * A collection of methods to help with color values
 *
 * @since 1.0
 *
 * Created by Brady on 4/4/2017.
 */
public class Colors {

    /**
     * Parses the RGBA values from a hex value
     *
     * @since 1.0
     *
     * @param hex The hex value
     * @return The parsed RGBA array
     */
    public static float[] getColor(int hex) {
        return new float[] {
                (hex >> 16 & 255) / 255F,
                (hex >> 8 & 255) / 255F,
                (hex & 255) / 255F,
                (hex >> 24 & 255) / 255F
        };
    }

    /**
     * Parses the hex value from RGB/RGBA values
     *
     * @since 1.0
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
     * @since 1.0
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
     * @since 1.0
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
     * @since 1.0
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
     * @since 1.0
     *
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @param a Alpha value
     * @return The parsed hex value
     */
    public static int getColor(int r, int g, int b, int a) {
        return ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8) |
                (b & 0xFF);
    }
}
