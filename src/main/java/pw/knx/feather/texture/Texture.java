package pw.knx.feather.texture;

import pw.knx.feather.tessellate.Tessellator;

/**
 * A simple OpenGL Texture interface
 * The voids in this interface return the Texture object for easy method chaining
 * <p>
 * In addition, this interface contains construction methods that can be called intuitively:
 * Texture.from();
 * ...rather than needing to know to use 'new ***Texture()'
 *
 * @author KNOXDEV
 * @since 8/8/2016 23:53
 */
public interface Texture {

	/**
	 * @return the OpenGL Texture ID
	 */
	int id();

	/**
	 * @return the x coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 */
	float u();

	/**
	 * @return the y coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 */
	float v();

	/**
	 * @return the x coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 */
	float u1();

	/**
	 * @return the y coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 */
	float v1();

	/**
	 * @return the width of the texture rendering in pixels
	 */
	float width();

	/**
	 * @return the height of the texture rendering in pixels
	 */
	float height();

	/**
	 * @param id The OpenGL Texture object ID
	 * @return The original Texture object
	 */
	Texture setID(int id);

	/**
	 * @param u The x coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 * @return The original Texture object
	 */
	Texture setU(float u);

	/**
	 * @param v The y coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 * @return The original Texture object
	 */
	Texture setV(float v);

	/**
	 * @param u1 The x coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 * @return The original Texture object
	 */
	Texture setU1(float u1);

	/**
	 * @param v1 The y coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 * @return The original Texture object
	 */
	Texture setV1(float v1);

	/**
	 * @param width The width of the rendered texture in pixels
	 * @return The original Texture object
	 */
	Texture setWidth(float width);

	/**
	 * @param height The height of the rendered texture in pixels
	 * @return The original Texture object
	 */
	Texture setHeight(float height);

	/**
	 * Binds the texture to be rendered
	 *
	 * @return The original Texture object
	 */
	Texture bind();

	/**
	 * @param tess The Tessellator Object you wish to use to render this texture
	 * @param mode The OpenGL mode ID you wish to use to render this texture
	 * @param x    The x coordinate in pixels you wish to render the texture on the screen
	 * @param y    The y coordinate in pixels you wish to render the texture on the screen
	 * @return The original Texture object
	 */
	Texture draw(Tessellator tess, int mode, float x, float y);


	/* #####################
	 *  Static Constructors
	 * ##################### */

	/**
	 * @param u      The x coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 * @param v      The y coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 * @param u1     The x coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 * @param v1     The y coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 * @param width  The width of the texture rendering in pixels
	 * @param height The height of the texture rendering in pixels
	 * @return a freshly instantiated BasicTexture object with the parameters provided
	 */
	static Texture from(float u, float v, float u1, float v1, float width, float height) {
		return new BasicTexture().setU(u).setV(v).setU1(u1).setV1(v1).setWidth(width).setHeight(height);
	}

	/**
	 * Like above, creates a new BasicTexture object with the parameters provided.
	 * The difference is that it allows you to pass pixel-based U&V rather than 0-1 floats.
	 * In exchange for automatically calculating U, V, U1, and V1, the method requires the texture
	 * resource's original dimensions. (64, 256, etc.)
	 *
	 * @param u          Starting x coordinate of section of the origin resource to be rendered in pixels.
	 * @param v          Starting y coordinate of section of the origin resource to be rendered in pixels.
	 * @param width      The width of section of the origin resource to be rendered in pixels.
	 * @param height     The height of section of the origin resource to be rendered in pixels.
	 * @param dimensions The origin texture resource's original dimensions. (64, 256, etc.)
	 * @return a freshly instantiated BasicTexture object with the parameters provided
	 */
	static Texture from(float u, float v, float width, float height, int dimensions) {
		return from(u / dimensions, v / dimensions, (u + width) / dimensions, (v + height) / dimensions, width, height);
	}
}