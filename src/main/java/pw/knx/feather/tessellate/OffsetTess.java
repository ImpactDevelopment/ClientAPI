package pw.knx.feather.tessellate;

import org.lwjgl.opengl.GL11;
import pw.knx.feather.tessellate.base.Tessellator;

/**
 * An easily translatable implementation of the Tessellator interface.
 * <p>
 * This class is an unusual implementation as it does not strictly handle tessellating itself,
 * rather, this class is designed to wrap around an existing Tessellator, so developers can be
 * flexible about what kind of Tessellator they want to translate. Most methods in this class
 * will simply be passed through to the original Tessellator and leaves most of its
 * behavior intact. The only difference is a series of methods that allow for simple
 * offsetting of the current shape, which is handy for rendering the same shape in multiple
 * places without binding completely new data. If the shape is totally consistent throughout
 * Runtime, consider using the VBO class instead.
 *
 * @author KNOXDEV
 * @since 8/9/2016 03:55
 */
public class OffsetTess implements Tessellator {

	/**
	 * The original Tessellator object that most methods will pass to.
	 */
	private final Tessellator tess;

	/**
	 * Floats representing the X, Y, and Z distance that this Tessellator is currently offset by.
	 */
	public float offsetX, offsetY, offsetZ;

	/**
	 * Constructs an Offset Tessellator. See Class Documentation for more information.
	 * Please note that by default, this Tessellator will offset a Basic Tessellator.
	 * Use the alternate constructor if you wish to change this behavior.
	 *
	 * @param capacity The total initial capacity, in whole vertices.
	 */
	public OffsetTess(int capacity) {
		this(new BasicTess(capacity));
	}

	/**
	 * Constructs an Offset Tessellator. See Class Documentation for more information.
	 *
	 * @param tess The Tessellator object you wish to wrap and offset
	 */
	public OffsetTess(Tessellator tess) {
		this.tess = tess;
	}

	/**
	 * Please see implemented Tessellator documentation for specific information.
	 *
	 * @param color The color to associate the upcoming vertex data with
	 * @return The original Tessellator Object
	 */
	@Override
	public Tessellator color(int color) {
		tess.color(color);
		return this;
	}

	/**
	 * Set the texture coordinates to associate the upcoming vertex data with.
	 * <p>
	 * Please see implemented Tessellator documentation for specific information.
	 *
	 * @param u The x starting coordinate
	 * @param v The y starting coordinate
	 * @return The original Tessellator Object
	 */
	@Override
	public Tessellator texture(float u, float v) {
		tess.texture(u, v);
		return this;
	}

	/**
	 * Enters a vertex of the shape to be rendered.
	 * All data fed to the Tessellator revolves around the vertex data,
	 * as it is the only information absolutely necessary to render a shape.
	 * <p>
	 * Please see implemented Tessellator documentation for specific information.
	 *
	 * @param x The x coordinate of this vertex
	 * @param y The y coordinate of this vertex
	 * @param z The z coordinate of this vertex
	 * @return The original Tessellator Object
	 */
	@Override
	public Tessellator vertex(float x, float y, float z) {
		tess.vertex(x, y, z);
		return this;
	}

	/**
	 * The first stage of rendering.
	 * Binds (finalizes) the current rendering data stored in the buffer for drawing.
	 * This must be executed before you perform a rendering pass.
	 * <p>
	 * Please see implemented Tessellator documentation for specific information.
	 *
	 * @return The original Tessellator Object
	 */
	@Override
	public Tessellator bind() {
		tess.bind();
		return this;
	}

	/**
	 * The second stage of rendering.
	 * Performs a rendering pass with the data bound to the buffer.
	 * If the data is not bound first, this method will fail.
	 * Otherwise, you can render the data for as many passes as you please.
	 * <p>
	 * Please see implemented Tessellator documentation for specific information.
	 *
	 * @param mode The OpenGL mode to render the data with
	 * @return The original Tessellator Object
	 */
	@Override
	public Tessellator pass(int mode) {
		GL11.glTranslatef(offsetX, offsetY, offsetZ);
		final Tessellator sup = tess.pass(mode);
		GL11.glTranslatef(-offsetX, -offsetY, offsetZ);
		return sup;
	}

	/**
	 * The third and final stage of rendering.
	 * Clears up the buffer and resets the Tessellator so it can
	 * be used again with new data. Passes can no longer be made
	 * after this method is executed.
	 * <p>
	 * Please see implemented Tessellator documentation for specific information.
	 *
	 * @return The original Tessellator Object
	 */
	@Override
	public Tessellator reset() {
		tess.reset();
		return this;
	}

	@Override
	public Tessellator unbind() {
		tess.unbind();
		return this;
	}

	/**
	 * Performs all three rendering stages in one method.
	 * This method cannot be run more than once without entering new data.
	 * <p>
	 * Please see implemented Tessellator documentation for specific information.
	 *
	 * @param mode The OpenGL mode to render the data with
	 * @return The original Tessellator Object
	 */
	@Override
	public Tessellator draw(int mode) {
		tess.draw(mode);
		return this;
	}
}