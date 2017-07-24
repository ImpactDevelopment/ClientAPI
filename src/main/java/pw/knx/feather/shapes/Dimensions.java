package pw.knx.feather.shapes;

/**
 * The simplest 2-Dimensional shape, basically a paired width-height vector.
 * <p>
 * We decided to create our own shape structures because LWJGL's and the JDK's
 * did not suit the conventions of the Feather library.
 * <p>
 * In standard Feather convention, the setters in this class return itself
 * to allow for efficient and readable method chaining.
 *
 * @author KNOXDEV
 * @since 5/25/2017 4:58 AM
 */
public class Dimensions {
	// we can keep these private because the methods we have totally expose them
	private int width, height;


	/*
	 * Getters - For retrieving internal values
	 */

	/**
	 * @return this shape's width
	 */
	public int width() {
		return width;
	}

	/**
	 * @return this shape's height
	 */
	public int height() {
		return height;
	}


	/*
	 * Setters - For setting internal values
	 */

	/**
	 * Set this shape's width
	 *
	 * @param w the width to set
	 * @return this shape, freshly set
	 */
	public Dimensions setWidth(int w) {
		width = w;
		return this;
	}

	/**
	 * Set this shape's height
	 *
	 * @param h the height to set
	 * @return this shape, freshly set
	 */
	public Dimensions setHeight(int h) {
		height = h;
		return this;
	}

	/**
	 * Set the width and height of this shape
	 *
	 * @param width  the width value to use
	 * @param height the height value to use
	 * @return this shape, freshly set
	 */
	public Dimensions setTo(int width, int height) {
		return setWidth(width).setHeight(height);
	}

	/**
	 * Set the width and height of this shape to match the Dimension provided
	 *
	 * @param size the Dimensions to be set to
	 * @return this shape, freshly set
	 */
	public Dimensions setTo(Dimensions size) {
		return setTo(size.width, size.height);
	}


	/*
	 * Modifiers - For now, just an offset shorthand method
	 */

	/**
	 * Offset this shape by the width and height provided
	 *
	 * @param dWidth  the change in width
	 * @param dHeight the change in height
	 * @return this shape, freshly offset
	 */
	public Dimensions offset(int dWidth, int dHeight) {
		return setTo(width + dWidth, height + dHeight);
	}
}
