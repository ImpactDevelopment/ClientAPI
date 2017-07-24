package pw.knx.feather.shapes;

/**
 * Representation of a geometric Rectangle.
 * Four vectors, x position, y position, width, and height.
 * <p>
 * We decided to create our own shape structures because LWJGL's and the JDK's
 * did not suit the conventions of the Feather library.
 * <p>
 * In standard Feather convention, the setters in this class return itself
 * to allow for efficient and readable method chaining.
 *
 * @author KNOXDEV
 * @since 5/25/2017 5:01 AM
 */
public class Rectangle {
	// we can keep these private because the methods we have totally expose them
	private int x, y, width, height;


	/*
	 * Getters - For retrieving internal values, some literal, some logical (such as x1, etc.)
	 */

	/**
	 * @return this shape's x-position
	 */
	public int x() {
		return x;
	}

	/**
	 * @return this shape's y-position
	 */
	public int y() {
		return y;
	}

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

	/**
	 * @return the calculated x-position of this shape's opposite vertex
	 */
	public int x1() {
		return x + width;
	}

	/**
	 * @return the calculated y-position of this shape's opposite vertex
	 */
	public int y1() {
		return y + height;
	}


	/*
	 * Setters - For setting internal values, some literal, some logical (such as x1, etc.)
	 */

	/**
	 * Set this shape's x-position
	 *
	 * @param x the x-position to set
	 * @return this shape, freshly set
	 */
	public Rectangle setX(int x) {
		this.x = x;
		return this;
	}

	/**
	 * Set this shape's y-position
	 *
	 * @param y the y-position to set
	 * @return this shape, freshly set
	 */
	public Rectangle setY(int y) {
		this.y = y;
		return this;
	}

	/**
	 * Set the x-position of this shape's opposite vertex
	 *
	 * @param x1 the x-position to set
	 * @return this shape, freshly set
	 */
	public Rectangle setX1(int x1) {
		return setWidth(x1 - x);
	}

	/**
	 * Set the y-position of this shape's opposite vertex
	 *
	 * @param y1 the y-position to set
	 * @return this shape, freshly set
	 */
	public Rectangle setY1(int y1) {
		return setHeight(y1 - y);
	}

	/**
	 * Set this shape's width
	 *
	 * @param w the width to set
	 * @return this shape, freshly set
	 */
	public Rectangle setWidth(int w) {
		width = w;
		return this;
	}

	/**
	 * Set this shape's height
	 *
	 * @param h the height to set
	 * @return this shape, freshly set
	 */
	public Rectangle setHeight(int h) {
		height = h;
		return this;
	}

	/**
	 * Set the x-position, y-position, width, and height of this shape
	 *
	 * @param x the x-position value to use
	 * @param y the xy-position value to use
	 * @param w the width value to use
	 * @param h the height value to use
	 * @return this shape, freshly set
	 */
	public Rectangle setTo(int x, int y, int w, int h) {
		return setX(x).setY(y).setWidth(w).setHeight(h);
	}

	/**
	 * Set the x-position, y-position, width, and height of this shape to match the Rectangle provided
	 *
	 * @param rect the Rectangle area to be set to
	 * @return this shape, freshly set
	 */
	public Rectangle setTo(Rectangle rect) {
		return setTo(rect.x, rect.y, rect.width, rect.height);
	}


	/*
	 * Modifiers - Mostly just short hands for common adjustment operations
	 */

	/**
	 * Offset this shape by the x and y provided
	 *
	 * @param dx  the change in x-position
	 * @param dy the change in y-position
	 * @return this shape, freshly offset
	 */
	public Rectangle offset(int dx, int dy) {
		return offset(dx, dy, 0, 0);
	}

	/**
	 * Offset this shape's x-position, y-position, width, and height by the deltas provided
	 *
	 * @param dx  the change in x-position
	 * @param dy the change in y-position
	 * @param dWidth the change in width
	 * @param dHeight the change in height
	 * @return this shape, freshly offset
	 */
	public Rectangle offset(int dx, int dy, int dWidth, int dHeight) {
		return setTo(x + dx, y + dy, width + dWidth, height + dHeight);
	}

	/**
	 * Expand this shape by the x and y provided
	 *
	 * @param dx  the horizontal change
	 * @param dy the vertical change
	 * @return this shape, freshly expanded
	 */
	public Rectangle expand(int dx, int dy) {
		return offset(-dx, -dy, 2*dx, 2*dy);
	}

	/**
	 * Contract this shape by the x and y provided
	 *
	 * @param dx  the horizontal change
	 * @param dy the vertical change
	 * @return this shape, freshly contracted
	 */
	public Rectangle contract(int dx, int dy) {
		return offset(dx, dy, -2*dx, -2*dy);
	}


	/*
	 * Misc. Functions:
	 */

	/**
	 * Checks if the provided point is inside the bounds of this shape, inclusive
	 *
	 * @param i the point's horizontal position
	 * @param j the point's vertical position
	 * @return true, if the point lies on the plane described by this shape
	 */
	public boolean contains(int i, int j) {
		return i >= x && j >= y && i <= x1() && j <= y1();
	}

	/**
	 * @return an exact, dereferenced copy of this Rectangle with the same values
	 */
	public Rectangle copy() {
		return new Rectangle().setTo(this);
	}
}
