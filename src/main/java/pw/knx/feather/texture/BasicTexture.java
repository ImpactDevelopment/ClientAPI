package pw.knx.feather.texture;

import org.lwjgl.opengl.GL11;
import pw.knx.feather.tessellate.base.Tessellator;
import pw.knx.feather.texture.base.Texture;

/**
 * A simple implementation of our OpenGL Texture interface
 * The setters in this class return the Texture object for easy method chaining
 *
 * @author KNOXDEV
 * @since 8/9/2016 01:10
 */
public class BasicTexture implements Texture {

	/**
	 * The OpenGL Texture ID
	 */
	private int texID;

	/**
	 * The various size and position properties OpenGL requires to render textures
	 */
	private float u, v, u1, v1, width, height;

	/**
	 * Binds the texture to be rendered
	 *
	 * @return The original Texture object
	 */
	@Override
	public Texture bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
		return this;
	}

	/**
	 * Draws the texture using standard Tessellator proceedure.
	 *
	 * @param tess The Tessellator Object you wish to use to render this texture
	 * @param mode The OpenGL mode ID you wish to use to render this texture
	 * @param x    The x coordinate in pixels you wish to render the texture on the screen
	 * @param y    The y coordinate in pixels you wish to render the texture on the screen
	 * @return The original Texture object
	 */
	@Override
	public Texture draw(Tessellator tess, int mode, float x, float y) {
		tess.texture(u1, v).vertex(x + width, y, 0).texture(u, v).vertex(x, y, 0);
		tess.texture(u, v1).vertex(x, y + height, 0).texture(u1, v1).vertex(x + width, y + height, 0);
		tess.draw(mode);
		return this;
	}

	/**
	 * @return the OpenGL Texture ID
	 */
	@Override
	public int getID() {
		return this.texID;
	}

	/**
	 * @return the x coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 */
	@Override
	public float getU() {
		return this.u;
	}

	/**
	 * @return the y coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 */
	@Override
	public float getV() {
		return this.v;
	}

	/**
	 * @return the x coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 */
	@Override
	public float getU1() {
		return this.u1;
	}

	/**
	 * @return the y coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 */
	@Override
	public float getV1() {
		return this.v1;
	}

	/**
	 * @return the width of the texture rendering in pixels
	 */
	@Override
	public float getWidth() {
		return this.width;
	}

	/**
	 * @return the height of the texture rendering in pixels
	 */
	@Override
	public float getHeight() {
		return this.height;
	}

	/**
	 * @param id The OpenGL Texture object ID
	 * @return The original Texture object
	 */
	@Override
	public Texture setID(int id) {
		this.texID = id;
		return this;
	}

	/**
	 * @param u The x coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 * @return The original Texture object
	 */
	@Override
	public Texture setU(float u) {
		this.u = u;
		return this;
	}

	/**
	 * @param v The y coordinate of the top-left texture point, on a float scale from 0 to 1.0
	 * @return The original Texture object
	 */
	@Override
	public Texture setV(float v) {
		this.v = v;
		return this;
	}

	/**
	 * @param u1 The x coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 * @return The original Texture object
	 */
	@Override
	public Texture setU1(float u1) {
		this.u1 = u1;
		return this;
	}

	/**
	 * @param v1 The y coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
	 * @return The original Texture object
	 */
	@Override
	public Texture setV1(float v1) {
		this.v1 = v1;
		return this;
	}

	/**
	 * @param width The width of the rendered texture in pixels
	 * @return The original Texture object
	 */
	@Override
	public Texture setWidth(float width) {
		this.width = width;
		return this;
	}

	/**
	 * @param height The height of the rendered texture in pixels
	 * @return The original Texture object
	 */
	@Override
	public Texture setHeight(float height) {
		this.height = height;
		return this;
	}
}