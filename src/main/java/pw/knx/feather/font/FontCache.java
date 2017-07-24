package pw.knx.feather.font;

import org.lwjgl.opengl.GL11;
import pw.knx.feather.tessellate.Tessellator;
import pw.knx.feather.texture.Texture;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.text.Bidi;
import java.util.*;
import java.util.List;

/**
 * FontCache is a simple, one-class library for the rendering of all Unicode Strings using OpenType fonts.
 * It is adapted from thvortex's BetterFonts, found here: https://github.com/user/thvortex/BetterFonts
 * <p>
 * There are is one key way in which this implementation differs from thvortex's:
 * 1 - This version has been generalized for LWJGL rather than purely Minecraft.
 * As such, all color-code related information or digit optimizations are gone.
 * <p>
 * Regardless of these changes, as much of the model remains the same, plenty of documentation will
 * be copied directly from thvortex's original repository.
 * <p>
 * This aforementioned processing model is as follows: FontCache caches the glyph layout of individual strings,
 * and it also caches the pre-rendered images for individual glyphs. Once a string and its glyph images are cached,
 * the critical path in renderString() will draw the glyphs as fast as if using a bitmap font. Strings are cached
 * using weak references through a two layer string cache. Strings that are no longer in use by LWJGL will be
 * silently evicted from the cache, while the pre-rendered images of individual glyphs remains cached forever.
 * <p>
 * This class is also responsible for selecting the proper fonts to render each glyph, since Java's own "SansSerif"
 * logical font does not always select the proper physical font to use (especially on less common Linux distributions).
 * Once a pre-rendered glyph image is cached, it will remain stored in an OpenGL texture for the entire lifetime of the application.
 *
 * @author KNOXDEV, thvortex
 * @since 1/23/2017 14:40
 */
public class FontCache {

	/*
	 * Constants
	 */

	/**
	 * The width in pixels of every texture used for caching pre-rendered glyph images. Used when calculating
	 * floating point 0.0-1.0 texture coordinates. Must be a power of two for mip-mapping to work.
	 */
	private static final int TEXTURE_WIDTH = 256;

	/**
	 * The height in pixels of every texture used for caching pre-rendered glyph images. Used when calculating
	 * floating point 0.0-1.0 texture coordinates. Must be a power of two for mip-mapping to work.
	 */
	private static final int TEXTURE_HEIGHT = 256;

	/**
	 * Transparent (alpha zero) white background color for use with BufferedImage.clearRect().
	 */
	private static final Color CLEAR = new Color(255, 255, 255, 0);

	/*
	 * Glyph Graphics
	 */

	/**
	 * All font glyphs are packed inside this image and are then loaded from here into an OpenGL texture.
	 */
	private final BufferedImage glyphImage = new BufferedImage(TEXTURE_WIDTH, TEXTURE_HEIGHT,
			BufferedImage.TYPE_INT_ARGB);

	/**
	 * The Graphics2D associated with stringImage and used for string drawing to extract the individual glyph shapes.
	 */
	private final Graphics2D glyphGraphics = glyphImage.createGraphics();

	/**
	 * Needed for all text layout operations that create GlyphVectors (maps point size to pixel size).
	 */
	private final FontRenderContext fontContext = this.glyphGraphics.getFontRenderContext();

	/*
	 * String Graphics
	 */

	/**
	 * Temporary image for rendering a string to and then extracting the glyph images from.
	 */
	private BufferedImage stringImage;

	/**
	 * The Graphics2D associated with stringImage and used for string drawing to extract the individual glyph shapes.
	 */
	private Graphics2D stringGraphics;

	/**
	 * The X coordinate of the upper=left corner in glyphCacheImage where the next glyph image should be stored. Glyphs are
	 * stored left-to-right in horizontal lines, and top-to-bottom until the entire texture fills up. At that point, a new
	 * texture is allocated to keep storing additional glyphs, and the original texture remains allocated for the lifetime of
	 * the application.
	 */
	private int cacheX = 1, cacheY = 1;

	/**
	 * The height in pixels of the current line of glyphs getting written into the texture. This value determines by how much
	 * cachePosY will get incremented when the current horizontal line in the texture fills up.
	 */
	private int cacheLineHeight = 0;

	/**
	 * ID of current OpenGL cache texture being used by cacheGlyphs() to store pre-rendered glyph images.
	 */
	private int texture;

	/*
	 * Fonts
	 */

	/**
	 * List of all available physical fonts on the system. Used by lookupFont() to find alternate fonts.
	 */
	private final Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

	/**
	 * A list of all fonts that have been returned so far by lookupFont(), and that will always be searched first for a usable font before
	 * searching through allFonts[]. This list will only have plain variation of a font at a dummy point size, unlike fontCache which could
	 * have multiple entries for the various styles (i.e. bold, italic, etc.) of a font. This list starts with Java's "SansSerif" logical
	 * font.
	 */
	private final List<Font> usedFonts = new ArrayList<>();

	/*
	 *  Internal Caches
	 */

	/**
	 * Every String passed to the public renderString() function is added to this WeakHashMap. As long as an application
	 * continues to hold a strong reference to the String object (i.e. from TileEntitySign and ChatLine) passed here, the
	 * weakRefCache map will continue to hold a strong reference to the Entry object that said strings all map to.
	 */
	private final Map<String, GlyphLayout> stringCache = new WeakHashMap<>();

	/**
	 * A cache of all fonts that have at least one glyph pre-rendered in a texture. Each font maps to an integer (monotonically
	 * increasing) which forms the upper 32 bits of the key into the glyphCache map. This font cache can include different styles
	 * of the same font family like bold or italic.
	 */
	private final Map<Font, Integer> fontCache = new HashMap<>();

	/**
	 * A cache of pre-rendered glyph textures mapping each glyph by its glyphcode to the position of its pre-rendered image within
	 * the cache texture. The key is a 64 bit number such that the lower 32 bits are the glyphcode and the upper 32 are the
	 * index of the font in the fontCache. This makes for a single globally unique number to identify any glyph from any font.
	 */
	private final Map<Long, Texture> glyphCache = new HashMap<>();

	/*
	 * Working Data
	 */

	/**
	 * Intermediate data array for use with textureImage.getRgb().
	 */
	private final int[] imageData = new int[TEXTURE_WIDTH * TEXTURE_HEIGHT];

	/**
	 * A big-endian direct int buffer used with glTexSubImage2D() and glTexImage2D(). Used for loading the pre-rendered glyph
	 * images from the glyphCacheImage BufferedImage into OpenGL textures. This buffer uses big-endian byte ordering to ensure
	 * that the integers holding packed RGBA colors are stored into memory in a predictable order.
	 */
	private final IntBuffer imageBuffer = ByteBuffer.allocateDirect(4 * TEXTURE_WIDTH * TEXTURE_HEIGHT)
			.order(ByteOrder.BIG_ENDIAN).asIntBuffer();

	/**
	 * A simple list of glyphs we're currently working with
	 */
	private final List<FontGlyph> glyphs = new ArrayList<>();

	FontCache() {

		/* Set background color for use with clearRect() */
		glyphGraphics.setBackground(CLEAR);

		/* The drawImage() to this buffer will copy all source pixels instead of alpha blending them into the current image */
		glyphGraphics.setComposite(AlphaComposite.Src);

		allocateTexture();
		allocateStringImage(TEXTURE_WIDTH, TEXTURE_HEIGHT);

		/* Use Java's logical font as the default initial font if user does not override it in some configuration file */
		GraphicsEnvironment.getLocalGraphicsEnvironment().preferLocaleFonts();
	}

	public Font getFont() {
		return usedFonts.get(0);
	}


	/*
	 * Caching Routines
	 */

	/**
	 * Add a string to the string cache by performing full layout on it, remembering its glyph positions, and making sure that
	 * every font glyph used by the string is pre-rendered. If this string has already been cached, then simply return its
	 * existing Entry from the cache.
	 *
	 * @param str this String will be laid out and added to the cache (or looked up, if already cached)
	 * @return the string's cache entry containing all the glyph positions
	 */
	public GlyphLayout cacheString(String str) {

		/* If this string is already in the cache, simply return the cached Entry object */
		GlyphLayout entry = stringCache.get(str);

		/* If string is not cached then layout the string */
		if (entry == null) {
			final int width = layoutBidi(str);

			entry = new GlyphLayout(glyphs.toArray(new FontGlyph[glyphs.size()]), width);
			glyphs.clear();

			/* Sorts these glyphs by associated TextureID to minimize OpenGL bind calls */
			Arrays.sort(entry.glyphs);

			/* Add to the cache for future reference */
			stringCache.put(str, entry);
		}

		return entry;
	}

	/**
	 * Find the first font in the system able to render a given character. The function always tries searching first
	 * in the fontCache (based on the request style). Failing that, it searches the usedFonts list followed by the allFonts[] array.
	 *
	 * @param ch the character to check against the font
	 * @return an OpenType font capable of displaying this character
	 */
	private Font cacheFont(char ch) {
		/* Try using an already known base font; the first font in usedFonts list is the one set with setFont() */
		for (Font font : usedFonts) {
			 /* Only use the font if it can layout the character */
			if (font.canDisplay(ch))
				return font;
		}

		for (Font font : allFonts) {
			/* Only use the font if it can layout the character */
			if (font.canDisplay(ch)) {
				/* Don't forget to enforce our current style and size on this new font */
				font = font.deriveFont(getFont().getStyle(), getFont().getSize());

				/* If found, add this font to the usedFonts list so it can be looked up faster next time */
				usedFonts.add(font);
				return font;
			}
		}

		/* If no supported fonts found, use the default one (first in usedFonts) so it can draw its unknown character glyphs */
		return getFont();
	}

	/**
	 * Given an OpenType font and a string, make sure that every glyph used by that string is pre-rendered into an OpenGL texture and cached
	 * in the glyphCache map for later retrieval
	 *
	 * @param text          the string from which to cache glyph images
	 * @param start         the offset at which to start the layout
	 * @param limit         the limit (offset + length) which to stop performing the layout
	 * @param directionFlag either Font.LAYOUT_RIGHT_TO_LEFT or Font.LAYOUT_LEFT_TO_RIGHT
	 * @param font          the font to use to layout a GlyphVector
	 * @return width of the glyphs cached
	 */
	private int cacheGlyphs(char[] text, int start, int limit, int directionFlag, Font font) {
		/* Create new GlyphVector so glyphs can be moved around (kerning workaround; see below) without affecting caller */
		final GlyphVector vector = layoutVector(font, text, start, limit, directionFlag);

		/*
		 * Kerning can make it impossible to cleanly separate adjacent glyphs. To work around this,
         * each glyph is manually advanced by 2 pixels to the right of its neighbor before rendering
         * the entire string. The getGlyphPixelBounds() later on will return the new adjusted bounds
         * for the glyph. This is done even if glyphs do not have to be drawn so the spacing is predictable.
         */
		for (int i = 0; i < vector.getNumGlyphs(); i++) {
			final Point2D pos = vector.getGlyphPosition(i);
			pos.setLocation(pos.getX() + 2 * i, pos.getY());
			vector.setGlyphPosition(i, pos);
		}

		/* Pixel aligned bounding box for the entire vector; only set if the vector has to be drawn to cache a glyph image */
		Rectangle vectorBounds = null;
		Rectangle dirty = null;         /* Total area within texture that needs to be updated with glTexSubImage2D() */

		/* This forms the upper 32 bits of the fontCache key to make every font/glyph code point unique */
		final long fontKey = (long) fontCache.get(font) << 32;

		/* Length of the GlyphVector */
		final int numGlyphs = vector.getNumGlyphs();

		for (int index = 0; index < numGlyphs; index++) {

			final int glyphCode = vector.getGlyphCode(index);
			Texture tex = glyphCache.get(fontKey | glyphCode);

			/* If this glyph code is already in glyphCache, then there is no reason to pre-render it again */
			if (tex == null) {

				/*
			 	* The only way to get glyph shapes with font hinting is to draw the entire glyph vector into a
             	* temporary BufferedImage, and then bit blit the individual glyphs based on their bounding boxes
             	* returned by the glyph vector. Although it is possible to call font.createGlyphVector() with an
             	* array of glyphcodes (and therefore render only a few glyphs at a time), this produces corrupted
             	* Davengari glyphs under Windows 7. This will draw the string at most one time.
             	*/
				if (vectorBounds == null)
					vectorBounds = cacheVector(vector);

				/*
			 	* Get the glyph's pixel-aligned bounding box. The JavaDoc claims that the "The outline returned
             	* by this method is positioned around the origin of each individual glyph." However, the actual
             	* bounds are all relative to the start of the entire GlyphVector, which is actually more useful
             	* for extracting the glyph's image from the rendered string.
             	*/
				final Rectangle rect = vector.getGlyphPixelBounds(index, null, -vectorBounds.x, -vectorBounds.y);

				/* If the current line in cache image is full, then advance to the next line */
				if (cacheX + rect.width + 1 > TEXTURE_WIDTH) {
					cacheX = 1;
					cacheY += cacheLineHeight + 1;
					cacheLineHeight = 0;
				}

				/*
             	* If the entire image is full, update the current OpenGL texture with everything changed so far in the image
             	* (i.e. the dirty rectangle), allocate a new cache texture, and then continue storing glyph images to the
             	* upper-left corner of the new texture.
             	*/
				if (cacheY + rect.height + 1 > TEXTURE_HEIGHT) {
					updateTexture(dirty);
					dirty = null;

					/* Note that allocateTexture() will leave the GL texture already bound */
					allocateTexture();
					cacheY = cacheX = 1;
					cacheLineHeight = 0;
				}

				/* The tallest glyph on this line determines the total vertical advance in the texture */
				cacheLineHeight = Math.max(rect.height, cacheLineHeight);

				/*
             	* Blit the individual glyph from it's position in the temporary string buffer to its (cachePosX,
             	* cachePosY) position in the texture. NOTE: We don't have to erase the area in the texture image
             	* first because the composite method in the Graphics object is always set to AlphaComposite.Src.
             	*/
				glyphGraphics.drawImage(stringImage, cacheX, cacheY, cacheX + rect.width, cacheY + rect.height, rect.x,
						rect.y, rect.x + rect.width, rect.y + rect.height, null);

				/*
             	* Store this glyph's position in texture and its origin offset. Note that "rect" will not be modified after
             	* this point, and getGlyphPixelBounds() always returns a new Rectangle.
             	*/
				rect.setLocation(cacheX, cacheY);

				/*
             	* Create new cache entry to record both the texture used by the glyph and its position within that texture.
             	* Texture coordinates are normalized to 0.0-1.0 by dividing with TEXTURE_WIDTH and TEXTURE_HEIGHT.
             	*/
				tex = Texture.from(rect.x, rect.y, rect.width, rect.height, TEXTURE_WIDTH).setID(texture);

				/*
             	 * The lower 32 bits of the glyphCache key are the glyph codepoint. The upper 64 bits are the font number
            	 * stored in the fontCache. This creates a unique numerical id for every font/glyph combination.
            	 */
				glyphCache.put(fontKey | glyphCode, tex);

				/*
             	* Track the overall modified region in the texture by performing a union of this glyph's texture position
             	* with the update region created so far. Reusing "rect" here makes it easier to extend the dirty rectangle
             	* region than using the add(x, y) method to extend by a single point. Also note that creating the first
             	* dirty rectangle here avoids having to deal with the special rules for empty/non-existent rectangles.
             	*/
				if (dirty == null)
					dirty = new Rectangle(cacheX, cacheY, rect.width, rect.height);
				else
					dirty.add(rect);

				/* Advance cachePosX so the next glyph can be stored immediately to the right of this one */
				cacheX += rect.width + 1;
			}

			final Point point = vector.getGlyphPixelBounds(index, null, 0, 0).getLocation();
			glyphs.add(new FontGlyph(tex, point.x - 2 * index, point.y));
		}

		/* Update OpenGL texture if any part of the glyphCacheImage has changed */
		updateTexture(dirty);

		/* return total string width from the second vector */
		return (int) vector.getGlyphPosition(numGlyphs).getX() - 2 * numGlyphs;
	}

	private Rectangle cacheVector(GlyphVector vector) {
		/*
         * Compute the exact area that the rendered string will take up in the image buffer. Note that
         * the string will actually be drawn at a positive (x,y) offset from (0,0) to leave enough room
         * for the ascent above the baseline and to correct for a few glyphs that appear to have negative
         * horizontal bearing (e.g. U+0423 Cyrillic uppercase letter U on Windows 7).
         */
		final Rectangle vectorBounds = vector.getPixelBounds(fontContext, 0, 0);

		/* Enlage the stringImage if it is too small to store the entire rendered string */
		if (vectorBounds.width > stringImage.getWidth() || vectorBounds.height > stringImage.getHeight())
			allocateStringImage(Math.max(vectorBounds.width, stringImage.getWidth()),
					Math.max(vectorBounds.height, stringImage.getHeight()));

		/* Erase the upper-left corner where the string will get drawn*/
		stringGraphics.clearRect(0, 0, vectorBounds.width, vectorBounds.height);

		/* Draw string with opaque white color and baseline adjustment so the upper-left corner of the image is at (0,0) */
		stringGraphics.drawGlyphVector(vector, -vectorBounds.x, -vectorBounds.y);

		return vectorBounds;
	}


	/*
	 * Layout Routines
	 */

	private int layoutBidi(String str) {
		final char[] text = str.toCharArray();

		/* Avoid performing full bidirectional analysis if text has no "strong" right-to-left characters */
		if (!Bidi.requiresBidi(text, 0, text.length))
			return layoutFont(text, 0, text.length, Font.LAYOUT_LEFT_TO_RIGHT);

		final Bidi bidi = new Bidi(text, 0, null, 0, text.length, Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);

		/* If text is entirely right-to-left, then just lay it out */
		if (bidi.isRightToLeft())
			return layoutFont(text, 0, text.length, Font.LAYOUT_RIGHT_TO_LEFT);

		/* Otherwise text has a mixture of LTR and RLT, and it requires full bidirectional analysis */
		final int runCount = bidi.getRunCount();
		final byte[] levels = new byte[runCount];
		final Integer[] ranges = new Integer[runCount];

		for (int i = 0; i < runCount; i++) {
			levels[i] = (byte) bidi.getRunLevel(i);
			ranges[i] = i;
		}
		Bidi.reorderVisually(levels, 0, ranges, 0, runCount);

		int width = 0;

		for (int i = 0; i < runCount; i++) {
			final int index = ranges[i];
			width += layoutFont(text, bidi.getRunStart(index), bidi.getRunLimit(index), (bidi.getRunLevel(index) & 1));
		}
		return width;
	}

	private int layoutFont(char[] text, int start, int limit, int directionFlag) {
		int width = 0;

		/* Break the string up into segments, where each segment can be displayed using a single font */
		while (start < limit) {
			final Font font = cacheFont(text[start]);
			int next = font.canDisplayUpTo(text, start, limit);

			/*
			 * canDisplayUpTo() returns start if the starting character is not supported at all. In that case, draw just the
			 * one unsupported character (which will use the font's "missing glyph code"), then retry the lookup again at the
			 * next character after that.
			 */
			if (next == start)
				next++;
			else if (next == -1) /* canDisplayUpTo returns -1 if the entire string range is supported by this font */
				next = limit;

			width += cacheGlyphs(text, start, next, directionFlag, font);
			start = next;
		}
		return width;
	}

	/**
	 * Given a single OpenType font, perform full text layout and create a new GlyphVector for a string.
	 *
	 * @param font        the Font used to layout a GlyphVector for the string
	 * @param text        the string to layout
	 * @param start       the offset into text at which to start the layout
	 * @param limit       the (offset + length) at which to stop performing the layout
	 * @param layoutFlags either Font.LAYOUT_RIGHT_TO_LEFT or Font.LAYOUT_LEFT_TO_RIGHT
	 * @return the newly created GlyphVector
	 */
	private GlyphVector layoutVector(Font font, char text[], int start, int limit, int layoutFlags) {
        /* Ensure this font is already in fontCache so it can be referenced by cacheGlyphs() later on */
		if (!fontCache.containsKey(font))
			fontCache.put(font, fontCache.size());
		return font.layoutGlyphVector(fontContext, text, start, limit, layoutFlags);
	}


	/*
	 * OpenGL Routines
	 */

	/**
	 * Update a portion of the current glyph cache texture using the contents of the glyphImage with glTexSubImage2D().
	 *
	 * @param dirty The rectangular region in glyphImage that has changed and needs to be copied into the texture
	 */
	private void updateTexture(Rectangle dirty) {
		if (dirty != null) {
			updateBuffer(dirty.x, dirty.y, dirty.width, dirty.height);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
			GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, dirty.x, dirty.y, dirty.width, dirty.height, GL11.GL_RGBA,
					GL11.GL_UNSIGNED_BYTE, imageBuffer);
		}
	}

	/**
	 * Allocate a new OpenGL texture for caching pre-rendered glyph images. The new texture is initialized to fully transparent
	 * white so the individual glyphs images within can have a transparent border between them. The new texture remains bound
	 * after returning from the function.
	 */
	private void allocateTexture() {
		/* Initialize the background to all white but fully transparent. */
		glyphGraphics.clearRect(0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);

		/* Allocate new OpenGL texure */
		texture = GL11.glGenTextures();

		/* Load imageBuffer with pixel data ready for transfer to OpenGL texture */
		updateBuffer(0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);

		/*
         * Initialize texture with the now cleared BufferedImage. Using a texture with GL_ALPHA8 internal format may result in
         * faster rendering since the GPU has to only fetch 1 byte per texel instead of 4 with a regular RGBA texture.
         */
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_ALPHA8, TEXTURE_WIDTH, TEXTURE_HEIGHT, 0, GL11.GL_RGBA,
				GL11.GL_UNSIGNED_BYTE, imageBuffer);

		/* Explicitly disable mipmap support because updateTexture() will only update the base level 0 */
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	}

	/**
	 * Allocte and initialize a new BufferedImage and Graphics2D context for rendering strings into. May need to be called
	 * at runtime to re-allocate a bigger BufferedImage if cacheGlyphs() is called with a very long string.
	 */
	private void allocateStringImage(int width, int height) {
		stringImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		stringGraphics = stringImage.createGraphics();
		setRenderingHints();

		/* Set background color for use with clearRect() */
		stringGraphics.setBackground(CLEAR);

		/*
         * Full white (1.0, 1.0, 1.0, 1.0) can be modulated by vertex color to produce a full gamut of text colors, although with
         * a GL_ALPHA8 texture, only the alpha component of the color will actually get loaded into the texture.
         */
		stringGraphics.setPaint(Color.WHITE);
	}

	/**
	 * Copy pixel data from a region in glyphCacheImage into imageBuffer and prepare it for use with glText(Sub)Image2D(). This
	 * function takes care of converting the ARGB format used with BufferedImage into the RGBA format used by OpenGL.
	 *
	 * @param x      the horizontal coordinate of the region's upper-left corner
	 * @param y      the vertical coordinate of the region's upper-left corner
	 * @param width  the width of the pixel region that will be copied into the buffer
	 * @param height the height of the pixel region that will be copied into the buffer
	 */
	private void updateBuffer(int x, int y, int width, int height) {
		glyphImage.getRGB(x, y, width, height, imageData, 0, width);

		/* Swizzle each color integer from Java's ARGB format to OpenGL's RGBA */
		for (int i = 0; i < width * height; i++) {
			int color = imageData[i];
			imageData[i] = (color << 8) | (color >>> 24);
		}

		imageBuffer.clear();
		imageBuffer.put(imageData);
		imageBuffer.flip();
	}

	/**
	 * Set rendering hints on stringGraphics object. Uses current antiAliasEnabled settings and is therefore called both from
	 * allocateStringImage() when expanding the size of the BufferedImage and from setDefaultFont() when changing current
	 * configuration.
	 */
	private void setRenderingHints() {
		stringGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		stringGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		stringGraphics
				.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
	}


	/*
	 * Static Construction Methods
	 */

	/**
	 * Creates a FontCache of the requested Font object
	 * @param font The Font to cache
	 * @return the Cache of the font passed
	 */
	public static FontCache from(Font font) {
		FontCache cache = new FontCache();
		cache.usedFonts.add(font);
		return cache;
	}
}
