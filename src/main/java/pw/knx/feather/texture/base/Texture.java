package pw.knx.feather.texture.base;

import pw.knx.feather.tessellate.base.Tessellator;
import pw.knx.feather.util.TextureUtils;

/**
 * A simple OpenGL Texture interface
 * The voids in this interface return the Texture object for easy method chaining
 *
 * @author KNOXDEV
 * @since 8/8/2016 23:53
 */
public interface Texture extends TextureUtils {

	/**
	 * @return the OpenGL Texture ID
	 */
	int getID();

	/**
	 * @return the x coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 */
	float getU();

	/**
	 * @return the y coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 */
	float getV();

	/**
	 * @return the x coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 */
	float getU1();

	/**
	 * @return the y coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 */
	float getV1();

	/**
	 * @return the width of the texture rendering in pixels
	 */
	float getWidth();

	/**
	 * @return the height of the texture rendering in pixels
	 */
	float getHeight();

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
}