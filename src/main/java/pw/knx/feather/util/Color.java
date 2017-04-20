package pw.knx.feather.util;

/**
 * A simple objectual representation of a Color.
 * <p>
 * This particular implementation aims for total flexibility
 * and readability during usage. "Flexibility" refers to the wide
 * range of color formats accepted, such as RGB Floats, RGB Ints, HSB, and
 * hexadecimal (all with and without alpha).
 * <p>
 * This class was designed to assist in two different use cases:
 * 1 - As a convenient way to store a Color constant that could be
 * effortlessly converted into any format. (See: Builder methods)
 * 2 - As a static utility enabling quick conversion from any color
 * format into another. (See: HexFormat enum)
 * <p>
 * The conversion algorithms found in this class are
 * relied on whenever possible in the rest of Feather, but Feather
 * will never require that the user directly uses this class to
 * interface with other features.
 *
 * @author KNOXDEV
 * @since 9/8/2016 21:41
 */
public class Color implements BufferUtils {

	/**
	 * In this class, we store alpha separately and persistently,
	 * and it is not subject to being updated by conversions
	 * (with the minor exception of SOME Hex values: See this.hex())
	 */
	private float alpha = 1;

	/**
	 * Our color format for Hue, Saturation, & Brightness.
	 * This representation is identical to the format supported
	 * by java.awt.Color.
	 */
	private float hue, saturation, brightness;

	/**
	 * Our color format representing Red, Green, & Blue.
	 * Represented by standard floats from 0.0 to 1.0.
	 */
	private float red, green, blue;

	/**
	 * Our color format representing Red, Green, & Blue.
	 * Represented by standard integers from 0 to 255.
	 */
	private int redInt, greenInt, blueInt;

	/**
	 * These booleans are used to keep track of what color
	 * formats still need to be updated via conversion before
	 * they can be used. This is tracked so that any given
	 * conversion will not be performed until it is absolutely
	 * necessary.
	 */
	private boolean updateHSB, updateRGB, updateRGBInts;

	/**
	 * Restricting usage to this class requires the user to
	 * utilize our builder functions at the bottom of this class
	 */
	private Color() {
	}


	/*
	 * Scaling Methods - Allows you to scale a color value
	 */

	/**
	 * Multiply this color's HSB by given amounts
	 *
	 * @param scaleH the amount to multiply hue by
	 * @param scaleS the amount to multiply saturation by
	 * @param scaleB the amount to multiply brightness by
	 *
	 * @return This color object, freshly scaled
	 */
	public Color scaleHSB(float scaleH, float scaleS, float scaleB) {
		return scaleHue(scaleH).scaleSaturation(scaleS).scaleBrightness(scaleB);
	}

	/**
	 * Multiply this color's RGB by given amounts
	 *
	 * @param scaleR the amount to multiply red by
	 * @param scaleG the amount to multiply green by
	 * @param scaleB the amount to multiply blue by
	 *
	 * @return This color object, freshly scaled
	 */
	public Color scaleRGB(float scaleR, float scaleG, float scaleB) {
		return scaleRed(scaleR).scaleGreen(scaleG).scaleBlue(scaleB);
	}

	/**
	 * Multiply this color's hue by the given amount
	 *
	 * @param scale the amount to multiply this color
	 *
	 * @return This color object, freshly scaled
	 */
	public Color scaleHue(float scale) {
		return hue(getHue() * scale);
	}

	/**
	 * Multiply this color's saturation by the given amount
	 *
	 * @param scale the amount to multiply this color
	 *
	 * @return This color object, freshly scaled
	 */
	public Color scaleSaturation(float scale) {
		return saturation(getSaturation() * scale);
	}

	/**
	 * Multiply this color's brightness by the given amount
	 *
	 * @param scale the amount to multiply this color
	 *
	 * @return This color object, freshly scaled
	 */
	public Color scaleBrightness(float scale) {
		return brightness(getBrightness() * scale);
	}

	/**
	 * Multiply this color's red by the given amount
	 *
	 * @param scale the amount to multiply this color
	 *
	 * @return This color object, freshly scaled
	 */
	public Color scaleRed(float scale) {
		return red(getRed() * scale);
	}

	/**
	 * Multiply this color's green by the given amount
	 *
	 * @param scale the amount to multiply this color
	 *
	 * @return This color object, freshly scaled
	 */
	public Color scaleGreen(float scale) {
		return green(getGreen() * scale);
	}

	/**
	 * Multiply this color's blue by the given amount
	 *
	 * @param scale the amount to multiply this color
	 *
	 * @return This color object, freshly scaled
	 */
	public Color scaleBlue(float scale) {
		return blue(getBlue() * scale);
	}

	/**
	 * Multiply this color's alpha by the given amount
	 *
	 * @param scale the amount to multiply this color
	 *
	 * @return This color object, freshly scaled
	 */
	public Color scaleAlpha(float scale) {
		return alpha(getAlpha() * scale);
	}


	/*
	 * Translation Methods - Adds or subtracts from a color value
	 * by the passed offset(s)
	 */

	/**
	 * Translates this color's HSB by the passed offsets
	 *
	 * @param offsetH the amount to add to this color's Hue
	 * @param offsetS the amount to add to this color's Saturation
	 * @param offsetB the amount to add to this color's Brightness
	 */
	public Color translateHSB(float offsetH, float offsetS, float offsetB) {
		return translateHue(offsetH).translateSaturation(offsetS).translateBrightness(offsetB);
	}

	/**
	 * Translates this color's RGB by the passed offsets
	 *
	 * @param offsetR the amount to add to this color's Red
	 * @param offsetG the amount to add to this color's Green
	 * @param offsetB the amount to add to this color's Blue
	 */
	public Color translateRGB(float offsetR, float offsetG, float offsetB) {
		return translateRed(offsetR).translateGreen(offsetG).translateBlue(offsetB);
	}

	/**
	 * Translates this color's RGB Integers by the passed offsets
	 *
	 * @param offsetR the integer to add to this color's Red
	 * @param offsetG the integer to add to this color's Green
	 * @param offsetB the integer to add to this color's Blue
	 */
	public Color translateRGB(int offsetR, int offsetG, int offsetB) {
		return translateRed(offsetR).translateGreen(offsetG).translateBlue(offsetB);
	}

	/**
	 * Translates this color's hue by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateHue(float offset) {
		return hue(getHue() + offset);
	}

	/**
	 * Translates this color's saturation by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateSaturation(float offset) {
		return saturation(getSaturation() + offset);
	}

	/**
	 * Translates this color's brightness by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateBrightness(float offset) {
		return brightness(getBrightness() + offset);
	}

	/**
	 * Translates this color's red by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateRed(float offset) {
		return red(getRed() + offset);
	}

	/**
	 * Translates this color's green by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateGreen(float offset) {
		return green(getGreen() + offset);
	}

	/**
	 * Translates this color's blue by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateBlue(float offset) {
		return blue(getBlue() + offset);
	}

	/**
	 * Translates this color's red integer by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateRed(int offset) {
		return red(getRedInt() + offset);
	}

	/**
	 * Translates this color's green integer by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateGreen(int offset) {
		return green(getGreenInt() + offset);
	}

	/**
	 * Translates this color's blue integer by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateBlue(int offset) {
		return blue(getBlueInt() + offset);
	}

	/**
	 * Translates this color's alpha by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateAlpha(float offset) {
		return alpha(getAlpha() + offset);
	}

	/**
	 * Translates this color's alpha integer by the passed offset
	 *
	 * @param offset the amount to add to this color
	 *
	 * @return This color object, freshly translated
	 */
	public Color translateAlpha(int offset) {
		return alpha(getAlphaInt() + offset);
	}


	/*
	 * Group Setters - Methods for setting color formats in bulk.
	 * Mostly just calls the Individual Setters below
	 */

	/**
	 * Sets this Color object's Hue, Saturation, & Brightness
	 * as floats from 0.0 to 1.0
	 *
	 * @param hue        The Color's hue
	 * @param saturation The Color's saturation
	 * @param brightness The Color's brightness
	 *
	 * @return This color object, freshly set
	 */
	public Color hsb(float hue, float saturation, float brightness) {
		return hue(hue).saturation(saturation).brightness(brightness);
	}

	/**
	 * Sets this Color object's Red, Green, & Blue
	 * as floats from 0.0 to 1.0
	 *
	 * @param red   The Color's red value
	 * @param green The Color's green value
	 * @param blue  The Color's blue value
	 *
	 * @return This color object, freshly set
	 */
	public Color rgb(float red, float green, float blue) {
		return red(red).green(green).blue(blue);
	}

	/**
	 * Sets this Color object's Red, Green, & Blue
	 * as integers from 0 to 255
	 *
	 * @param red   The Color's red value
	 * @param green The Color's green value
	 * @param blue  The Color's blue value
	 *
	 * @return This color object, freshly set
	 */
	public Color rgb(int red, int green, int blue) {
		return red(red).green(green).blue(blue);
	}

	/**
	 * Sets this Color via a 32-bit ARGB integer.
	 * <p>
	 * This function will change your current Alpha
	 * value if and ONLY if those bits
	 * do not equal zero.
	 *
	 * @param hex color as a 32-bit integer
	 *
	 * @return This color object, freshly set
	 */
	public Color hex(int hex) {
		return hex(hex, HexFormat.ARGB);
	}

	/**
	 * Sets this Color via a 32-bit integer in the
	 * format of your choice.
	 * <p>
	 * This function will change your current Alpha
	 * value if and ONLY if those bits
	 * do not equal zero.
	 *
	 * @param hex    color as a 32-bit integer
	 * @param format the format of the integer color
	 *
	 * @return This color object, freshly set
	 */
	public Color hex(int hex, HexFormat format) {
		int alpha = format.getAlphaInt(hex);
		if (alpha != 0) {
			alpha(alpha);
		}
		return red(format.getRedInt(hex)).green(format.getGreenInt(hex)).blue(format.getBlueInt(hex));
	}


	/*
	 * Individual Setters - sets each individual color format value
 	 */

	/**
	 * Sets the Color's Hue as a float
	 *
	 * @param hue The Hue between 0.0 and 1.0
	 *
	 * @return This color object, freshly set
	 */
	public Color hue(float hue) {
		onSetHSB();
		this.hue = clamp(hue, 0, 1);
		return this;
	}

	/**
	 * Sets the Color's Saturation as a float
	 *
	 * @param saturation The Saturation between 0.0 and 1.0
	 *
	 * @return This color object, freshly set
	 */
	public Color saturation(float saturation) {
		onSetHSB();
		this.saturation = clamp(saturation, 0, 1);
		return this;
	}

	/**
	 * Sets the Color's Brightness as a float
	 *
	 * @param brightness The Brightness between 0.0 and 1.0
	 *
	 * @return This color object, freshly set
	 */
	public Color brightness(float brightness) {
		onSetHSB();
		this.brightness = clamp(brightness, 0, 1);
		return this;
	}

	/**
	 * Sets the Color's Red as a float
	 *
	 * @param red The Red between 0.0 and 1.0
	 *
	 * @return This color object, freshly set
	 */
	public Color red(float red) {
		onSetRGB();
		this.red = clamp(red, 0, 1);
		return this;
	}

	/**
	 * Sets the Color's Green as a float
	 *
	 * @param green The Green between 0.0 and 1.0
	 *
	 * @return This color object, freshly set
	 */
	public Color green(float green) {
		onSetRGB();
		this.green = clamp(green, 0, 1);
		return this;
	}

	/**
	 * Sets the Color's Blue as a float
	 *
	 * @param blue The Blue between 0.0 and 1.0
	 *
	 * @return This color object, freshly set
	 */
	public Color blue(float blue) {
		onSetRGB();
		this.blue = clamp(blue, 0, 1);
		return this;
	}

	/**
	 * Sets the Color's Red as an integer
	 *
	 * @param red The Red between 0 and 255
	 *
	 * @return This color object, freshly set
	 */
	public Color red(int red) {
		onSetRGBInts();
		this.redInt = clamp(red, 0, 255);
		return this;
	}

	/**
	 * Sets the Color's Green as an integer
	 *
	 * @param green The Green between 0 and 255
	 *
	 * @return This color object, freshly set
	 */
	public Color green(int green) {
		onSetRGBInts();
		this.greenInt = clamp(green, 0, 255);
		return this;
	}

	/**
	 * Sets the Color's Blue as an integer
	 *
	 * @param blue The Blue between 0 and 255
	 *
	 * @return This color object, freshly set
	 */
	public Color blue(int blue) {
		onSetRGBInts();
		this.blueInt = clamp(blue, 0, 255);
		return this;
	}

	/**
	 * Sets the Color's Alpha as a float
	 *
	 * @param alpha The Alpha between 0.0 and 1.0
	 *
	 * @return This color object, freshly set
	 */
	public Color alpha(float alpha) {
		this.alpha = clamp(alpha, 0, 1);
		return this;
	}

	/**
	 * Sets the Color's Alpha as an integer
	 *
	 * @param alpha The Alpha between 0 and 255
	 *
	 * @return This color object, freshly set
	 */
	public Color alpha(int alpha) {
		return alpha((float) alpha / 255F);
	}


	/*
	 * Cache Invalidators - These methods keep track of the constant,
	 * "one-step-behind" color formats and ensure that at least one
	 * color format is up-to-date at any given time.
	 */

	private Color onSetHSB() {
		updateHSB();
		this.updateRGB = true;
		this.updateRGBInts = true;
		return this;
	}

	private Color onSetRGB() {
		updateRGB();
		this.updateHSB = true;
		this.updateRGBInts = true;
		return this;
	}

	private Color onSetRGBInts() {
		updateRGBInts();
		this.updateHSB = true;
		this.updateRGB = true;
		return this;
	}


	/*
	 * Individual Getters - returns actual values of this Color in a
	 * variety of formats. Each call checks to make sure the requested
	 * Color format is available, and if not, converts it
	 */

	/**
	 * @return The Hue component of this Color between 0.0 and 1.0
	 */
	public float getHue() {
		updateHSB();
		return this.hue;
	}

	/**
	 * @return The Saturation component of this Color between 0.0 and 1.0
	 */
	public float getSaturation() {
		updateHSB();
		return this.saturation;
	}

	/**
	 * @return The Brightness component of this Color between 0.0 and 1.0
	 */
	public float getBrightness() {
		updateHSB();
		return this.brightness;
	}

	/**
	 * @return The Red component of this Color between 0.0 and 1.0
	 */
	public float getRed() {
		updateRGB();
		return this.red;
	}

	/**
	 * @return The Green component of this Color between 0.0 and 1.0
	 */
	public float getGreen() {
		updateRGB();
		return this.green;
	}

	/**
	 * @return The Blue component of this Color between 0.0 and 1.0
	 */
	public float getBlue() {
		updateRGB();
		return this.blue;
	}

	/**
	 * @return The Red component of this Color between 0 and 255
	 */
	public int getRedInt() {
		updateRGBInts();
		return this.redInt;
	}

	/**
	 * @return The Green component of this Color between 0 and 255
	 */
	public int getGreenInt() {
		updateRGBInts();
		return this.greenInt;
	}

	/**
	 * @return The Blue component of this Color between 0 and 255
	 */
	public int getBlueInt() {
		updateRGBInts();
		return this.blueInt;
	}

	/**
	 * @return The Alpha component of this Color between 0.0 and 1.0
	 */
	public float getAlpha() {
		return this.alpha;
	}

	/**
	 * @return The Alpha component of this Color between 0 and 255
	 */
	public int getAlphaInt() {
		return (int) (getAlpha() * 255F);
	}

	/**
	 * @return This Color as a 32-bit integer, with alpha
	 */
	public int getHex() {
		return getHex(HexFormat.ARGB);
	}

	/**
	 * @param format The format of hex you would like this color in
	 *
	 * @return This Color as a 32-bit integer, with alpha
	 */
	public int getHex(HexFormat format) {
		return format.getHex(getRedInt(), getGreenInt(), getBlueInt(), getAlphaInt());
	}


	/*
	 * Cache Updaters - These functions handle the actual conversions between values.
	 * These methods are not called when the color is set, but rather when a given
	 * color format is requested (ie, lazy values to decrease overhead)
	 */

	private Color updateHSB() {
		if (this.updateHSB) {
			final float[] hsb = new float[3];
			java.awt.Color.RGBtoHSB(getRedInt(), getGreenInt(), getBlueInt(), hsb);
			this.hue = hsb[0];
			this.saturation = hsb[1];
			this.brightness = hsb[2];

			this.updateHSB = false;
		}
		return this;
	}

	private Color updateRGB() {
		if (this.updateRGB) {
			this.red = getRedInt() / 255F;
			this.green = getGreenInt() / 255F;
			this.blue = getBlueInt() / 255F;
			this.updateRGB = false;
		}
		return this;
	}

	private Color updateRGBInts() {
		if (this.updateRGBInts) {
			if (updateRGB) { // rgb is dirty too, so we have to use the less-efficient HSB method
				int hex = java.awt.Color.HSBtoRGB(getHue(), getSaturation(), getBrightness());
				this.redInt = HexFormat.ARGB.getRedInt(hex);
				this.greenInt = HexFormat.ARGB.getGreenInt(hex);
				this.blueInt = HexFormat.ARGB.getBlueInt(hex);
			} else {
				this.redInt = (int) (getRed() * 255);
				this.greenInt = (int) (getGreen() * 255);
				this.blueInt = (int) (getBlue() * 255);
			}
		}
		return this;
	}


	/*
	 * Misc. Functions:
	 */

	/**
	 * Private shorthand for quick clamping of color input. Normally,
	 * OpenGL will do this automatically with glColor, but because
	 * this class may do conversions to other color formats later,
	 * we have to do it now to prevent overflow/underflow from
	 * corrupting the converted values.
	 *
	 * @param toClamp The value to clamp
	 * @param low     The lower bound of our clamp operation
	 * @param high    The upper bound of our clamp operation
	 *
	 * @return the clamped float
	 */
	private float clamp(float toClamp, float low, float high) {
		return Math.max(Math.min(toClamp, high), low);
	}

	/**
	 * Private shorthand for quick clamping of color input. Normally,
	 * OpenGL will do this automatically with glColor, but because
	 * this class may do conversions to other color formats later,
	 * we have to do it now to prevent overflow/underflow from
	 * corrupting the converted values.
	 *
	 * @param toClamp The value to clamp
	 * @param low     The lower bound of our clamp operation
	 * @param high    The upper bound of our clamp operation
	 *
	 * @return the clamped float
	 */
	private int clamp(int toClamp, int low, int high) {
		return Math.max(Math.min(toClamp, high), low);
	}

	/**
	 * @return a deep copy of this color that can be mutated separately
	 */
	public Color copy() {
		return Color.fromRGB(getRedInt(), getGreenInt(), getBlueInt());
	}


	/*
	 * Builder functions - These ensure that the given color is initialized
	 * with SOMETHING before you start calling getters
	 */

	/**
	 * Creates a Color object with an initial Hue, Saturation, and Brightness.
	 * This Color's Alpha will default to 1F (fully opaque)
	 *
	 * @param hue        The Hue component between 0.0 and 1.0
	 * @param saturation The Saturation component between 0.0 and 1.0
	 * @param brightness The Brightness component between 0.0 and 1.0
	 *
	 * @return The newly created Color object
	 */
	public static Color fromHSB(float hue, float saturation, float brightness) {
		return new Color().hsb(hue, saturation, brightness);
	}

	/**
	 * Creates a Color object with an initial Red, Green, & Blue
	 * This Color's Alpha will default to 1F (fully opaque)
	 *
	 * @param red   The Red component between 0.0 and 1.0
	 * @param green The Green component between 0.0 and 1.0
	 * @param blue  The Blue component between 0.0 and 1.0
	 *
	 * @return The newly created Color object
	 */
	public static Color fromRGB(float red, float green, float blue) {
		return new Color().rgb(red, green, blue);
	}

	/**
	 * Creates a Color object with an initial Red, Green, & Blue
	 * This Color's Alpha will default to 255 (fully opaque)
	 *
	 * @param red   The Red component between 0 and 255
	 * @param green The Green component between 0 and 255
	 * @param blue  The Blue component between 0 and 255
	 *
	 * @return The newly created Color object
	 */
	public static Color fromRGB(int red, int green, int blue) {
		return new Color().rgb(red, green, blue);
	}

	/**
	 * Creates a Color object initialized with a hex value.
	 * Alpha will default to 1 (fully opaque) IF AND ONLY IF
	 * hex bits 24-32 are not set
	 *
	 * @param color 32-bit integer in the format 'ARGB'
	 *
	 * @return The newly created Color object
	 */
	public static Color fromHex(int color) {
		return new Color().hex(color);
	}

	/**
	 * Hex Format Enum
	 */
	public enum HexFormat {
		ARGB(1, 2, 3, 0), // Conventional/Java Format
		BGRA(0, 3, 2, 1), // Conventional/Java Format, Reversed
		RGBA(0, 1, 2, 3), // OpenGL Preferred
		ABGR(3, 2, 1, 0); // OpenGL Preferred, Reversed

		final int rx, gx, bx, ax;

		HexFormat(int rx, int gx, int bx, int ax) {
			this.rx = 24 - (rx << 3);
			this.gx = 24 - (gx << 3);
			this.bx = 24 - (bx << 3);
			this.ax = 24 - (ax << 3);
		}

		public int getHex(int r, int g, int b, int a) {
			return (r << rx) | (g << gx) | (b << bx) | (a << ax);
		}

		public int getRedInt(int hex) {
			return hex >>> rx & 0xFF;
		}

		public int getGreenInt(int hex) {
			return hex >>> gx & 0xFF;
		}

		public int getBlueInt(int hex) {
			return hex >>> bx & 0xFF;
		}

		public int getAlphaInt(int hex) {
			return hex >>> ax & 0xFF;
		}
	}
}
