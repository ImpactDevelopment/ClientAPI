package pw.knx.feather.structures;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static pw.knx.feather.Feather.feather;

/**
 * A simple OpenGL VertexBufferObject implementation for rendering shapes that will stay
 * consistent throughout Runtime and be used often. A key feature of this particular
 * implementation is the ability to switch between VBOs and VAOs smoothly for compliance
 * with older builds of OpenGL. Please also note that this class is only compatible with
 * vertices and not factors like textures or colors.
 * <p>
 * I seem to recall Halalaboos creating something like this,
 * so credit is given accordingly.
 * <p>
 * The voids in this interface return the VBO object for easy method chaining.
 *
 * @author KNOXDEV, Halalaboos
 * @since 8/9/2016 05:58
 */
public class VBO {

	/**
	 * The VBO's buffer ID, for binding this object to.
	 */
	private final int id;

	/**
	 * Quite literally, this object's dimensions.
	 * No seriously, if you want to render 1-dimensional objects feel free. That's weird though.
	 */
	protected final int dimensions;

	/**
	 * The size of the buffer object compiled. Used for VAO mode.
	 */
	private int size = 0;

	/**
	 * Constructs a VertexBufferObject for use with OpenGL.
	 * Auto-generates a buffer ID if one is not provided.
	 */
	public VBO() {
		this(GL15.glGenBuffers());
	}

	/**
	 * Constructs a VertexBufferObject for use with OpenGL.
	 *
	 * @param id a pre-generated OpenGL buffer ID to use
	 */
	public VBO(int id) {
		this(3, id);
	}

	/**
	 * Constructs a VertexBufferObject for use with OpenGL.
	 *
	 * @param dimensions number of GL dimensions to render the VBO in.
	 * @param id         a pre-generated OpenGL buffer ID to use
	 */
	public VBO(int dimensions, int id) {
		this.id = id;
		this.dimensions = dimensions;
	}

	/**
	 * Compiles a series of floats to a Vertex Buffer which is then bound to OpenGL for rendering.
	 *
	 * @param points a series of float representing vertices
	 * @return The original VBO
	 */
	public VBO compile(float... points) {
		if (points != null && points.length > 0) {
			final FloatBuffer buffer = feather.allocateBuffer(points.length * 4).asFloatBuffer();
			buffer.put(points).flip();
			return this.compile(buffer);
		}
		return this;
	}

	/**
	 * Compiles a FloatBuffer and binds it to OpenGL for rendering.
	 *
	 * @param buffer a FloatBuffer populated with VBO vertices
	 * @return The original VBO
	 */
	public VBO compile(FloatBuffer buffer) {
		this.size = buffer.capacity();
		feather.bindBuffer(this.id);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		feather.bindBuffer(0);
		return this;
	}

	/**
	 * Binds OpenGL to our VBO for rendering.
	 *
	 * @return The original VBO
	 */
	public VBO bind() {
		feather.bindBuffer(this.id);
		GL11.glVertexPointer(this.dimensions, GL11.GL_FLOAT, 0, 0L);
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		return this;
	}

	/**
	 * Draws the shape with VertexArrayObjects.
	 *
	 * @param mode The OpenGL mode to render with.
	 * @return The original VBO
	 */
	public VBO draw(int mode) {
		GL11.glDrawArrays(mode, 0, this.size);
		return this;
	}

	/**
	 * Draws the shape with VertexBufferObjects. Because we're using VBOs, we can also accept unusual vertex orders.
	 *
	 * @param mode  The OpenGL mode to render with
	 * @param order A ByteBuffer representing the order to draw the vertices in
	 * @return The original VBO
	 */
	public VBO draw(int mode, ByteBuffer order) {
		GL11.glDrawElements(mode, order);
		return this;
	}
}
