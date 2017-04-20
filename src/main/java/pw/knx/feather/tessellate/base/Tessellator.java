package pw.knx.feather.tessellate.base;

import pw.knx.feather.util.Color;

/**
 * A standard abstract interface for an OpenGL Tessellator.
 * This abstraction supports purely vertices, texture, and color.
 * The voids in this interface return the Tessellator object for easy method chaining
 *
 * @author KNOXDEV
 * @since 8/9/2016 01:46
 */
public interface Tessellator {

	/**
	 * @param color The color to associate the upcoming vertex data with
	 *              NOTE: Must be in ABGR format
	 * @return The original Tessellator Object
	 */
	Tessellator color(int color);

	/**
	 * @param color The color to associate the upcoming vertex data with
	 * @return The original Tessellator Object
	 */
	default Tessellator color(Color color) {
		return color(color.getHex(Color.HexFormat.ABGR));
	}

	/**
	 * @param red   The red component of the color to bind
	 * @param green The green component of the color to bind
	 * @param blue  The blue component of the color to bind
	 * @param alpha The alpha component of the color to bind
	 * @return The original Tessellator Object
	 */
	default Tessellator color(int red, int green, int blue, int alpha) {
		return color(Color.HexFormat.ABGR.getHex(red, green, blue, alpha));
	}

	/**
	 * @param red   The red component of the color to bind
	 * @param green The green component of the color to bind
	 * @param blue  The blue component of the color to bind
	 * @param alpha The alpha component of the color to bind
	 * @return The original Tessellator Object
	 */
	default Tessellator color(float red, float green, float blue, float alpha) {
		return color((int) (red * 255), (int) (green * 255), (int) (blue * 255), (int) (alpha * 255));
	}

	/**
	 * Set the texture coordinates to associate the upcoming vertex data with.
	 *
	 * @param u The x starting coordinate
	 * @param v The y starting coordinate
	 * @return The original Tessellator Object
	 */
	Tessellator texture(float u, float v);

	/**
	 * Enters a vertex of the shape to be rendered.
	 * All data fed to the Tessellator revolves around the vertex data,
	 * as it is the only information absolutely necessary to render a shape.
	 *
	 * @param x The x coordinate of this vertex
	 * @param y The y coordinate of this vertex
	 * @param z The z coordinate of this vertex
	 * @return The original Tessellator Object
	 */
	Tessellator vertex(float x, float y, float z);

	/**
	 * The first stage of rendering.
	 * <p>
	 * Binds (finalizes) the current rendering data stored in the buffer for drawing.
	 * This must be executed before you perform a rendering pass.
	 *
	 * @return The original Tessellator Object
	 */
	Tessellator bind();

	/**
	 * The second stage of rendering.
	 * <p>
	 * Performs a rendering pass with the data bound to the buffer.
	 * If the data is not bound first, this method will fail.
	 * Otherwise, you can render the data for as many passes as you please.
	 *
	 * @param mode The OpenGL mode to render the data with
	 * @return The original Tessellator Object
	 */
	Tessellator pass(int mode);

	/**
	 * The third and final stage of rendering.
	 * <p>
	 * Clears up the buffer and resets the Tessellator so it can
	 * be used again with new data. Passes can no longer be made
	 * after this method is executed.
	 *
	 * @return The original Tessellator Object
	 */
	Tessellator reset();

	/**
	 * Resets the buffer without clearing the data.
	 * This will allow you to stop using the Tessellator for a bit
	 * while still preserving its current contents. This means you
	 * can unbind this Tessellator, continue to add vertices, and
	 * reuse it later with the old data + the new information.
	 *
	 * @return The original Tessellator Object
	 */
	Tessellator unbind();

	/**
	 * Performs all three rendering stages in one method.
	 * This method cannot be run more than once without entering new data.
	 *
	 * @param mode The OpenGL mode to render the data with
	 * @return The original Tessellator Object
	 */
	default Tessellator draw(int mode) {
		return this.bind().pass(mode).reset();
	}
}