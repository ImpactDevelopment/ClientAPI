package pw.knx.feather.tessellate;

import pw.knx.feather.tessellate.base.Tessellator;

/**
 * An automatically resizing implementation of the Tessellator interface.
 * <p>
 * This class directly extends the Basic Tessellator, and leaves most of its
 * behavior intact. The only difference is that this Tessellator grows in capacity
 * automatically should you continue to add vertices past its initial size.
 *
 * @author KNOXDEV
 * @since 8/9/2016 03:00
 */
public class GrowingTess extends BasicTess {

	/**
	 * The target ratio, between 0 and 1.0, that this Tessellator must hit before it grows.
	 */
	final float ratio;

	/**
	 * The factor of which this Tessellator will grow when it hits the ratio.
	 */
	final float factor;

	/**
	 * Constructs a Growing Tessellator. See Class Documentation for more information.
	 * By default, a GrowingTess's capacity will double every time it hits its maximum capacity.
	 * Use the alternate constructor if you wish to change this behavior.
	 *
	 * @param initial The total initial capacity, in whole vertices.
	 */
	public GrowingTess(int initial) {
		this(initial, 1, 2);
	}

	/**
	 * Constructs a Growing Tessellator. See Class Documentation for more information.
	 *
	 * @param initial The total initial capacity, in whole vertices.
	 * @param ratio   The target ratio, between 0 and 1.0, that this Tessellator must hit before it grows.
	 * @param factor  The factor of which this Tessellator will grow when it hits the ratio.
	 */
	public GrowingTess(int initial, float ratio, float factor) {
		super(initial);
		this.ratio = ratio;
		this.factor = factor;
	}

	/**
	 * Enters a vertex of the shape to be rendered.
	 * All data fed to the Tessellator revolves around the vertex data,
	 * as it is the only information absolutely necessary to render a shape.
	 * <p>
	 * If the Tessellator's capacity ratio is reached, it will increase in size before
	 * adding the vertices to the buffer.
	 *
	 * @param x The x coordinate of this vertex
	 * @param y The y coordinate of this vertex
	 * @param z The z coordinate of this vertex
	 * @return The original Tessellator Object
	 */
	@Override
	public Tessellator vertex(float x, float y, float z) {
		int capacity = raw.length;
		if (index * 6 >= capacity * ratio) {                         // if we've hit our capacity limit
			capacity *= factor;                                 // raise our limit by the amount specified
			final int[] newBuffer = new int[capacity];          // allocate the new data
			System.arraycopy(raw, 0, newBuffer, 0, raw.length); // transfer the data from the old array to the new array
			raw = newBuffer;                                    // replace the array
			buffer = buff.createDirectBuffer(capacity * 4);       // allocate a new corresponding ByteBuffer
			iBuffer = buffer.asIntBuffer();
			fBuffer = buffer.asFloatBuffer();
		}
		return super.vertex(x, y, z);
	}
}