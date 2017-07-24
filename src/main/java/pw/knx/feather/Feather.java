package pw.knx.feather;

import pw.knx.feather.font.FontCache;
import pw.knx.feather.font.FontGlyph;
import pw.knx.feather.font.GlyphLayout;
import pw.knx.feather.tessellate.Tessellator;
import pw.knx.feather.texture.Texture;

import java.awt.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * The central class of the Feather library.
 *
 * Basically functions as a convenient state manager for OpenGL,
 * wrapping the most commonly used functions in intuitive, intelligent,
 * chainable methods. In this way, Feather reduces your required knowledge of
 * obscure OpenGL constants and compacts otherwise painfully verbose state management code.
 *
 * Probably going to use the enum singleton pattern for simplicity, thread safety and global access.
 * This should not pose an inconvenience since LWJGL only allows once thread to interact
 * with OpenGL anyways.
 *
 * WIP
 *
 * @author KNOXDEV
 * @since 6/7/2017 6:54 PM
 */
public enum Feather {
	feather;

	/**
	 * The simple Feather Tessellator we've designated to render our glyphs.
	 */
	private final Tessellator tess = Tessellator.createExpanding(4 * 4, 1, 2);

	private final Map<Font, FontCache> fonts = new HashMap<>();
	private FontCache currentFont;


	/*
	 * State Management - Most of these return Feather so they can be chained.
	 */

	/**
	 * Binds an existing texture object to the working texture2D buffer
	 *
	 * @param id The OpenGL ID of the texture object to be bound
	 * @return the Feather manager, for additional chaining
	 */
	public Feather bindTexture(int id) {
		glBindTexture(GL_TEXTURE_2D, id);
		return this;
	}

	/**
	 * Binds an existing buffer object to the working array buffer
	 *
	 * @param id The OpenGL ID of the buffer object to be bound
	 * @return the Feather manager, for additional chaining
	 */
	public Feather bindBuffer(int id) {
		glBindBuffer(GL_ARRAY_BUFFER, id);
		return this;
	}


	/*
	 * Allocators - Methods that wrap Java's unfortunate Buffer API
	 */

	/**
	 * Allocates a ByteBuffer in the platform's native byte order
	 *
	 * @param capacity the size of the Buffer to be allocated in bytes
	 * @return the allocated ByteBuffer
	 */
	public synchronized ByteBuffer allocateBuffer(int capacity) {
		return ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder());
	}


	/*
	 * Basic Rendering Routines
	 */

	public Feather setFont(Font font) {
		// holy shit streams
		currentFont = fonts.computeIfAbsent(font, FontCache::from);
		return this;
	}

	public Feather drawString(String str, float x, float y) {
		if(currentFont == null)
			throw new RuntimeException("You must first set the Font to draw");

		/* Make sure the entire string is cached before rendering and return its glyph representation */
		final GlyphLayout entry = currentFont.cacheString(str);

		/* Track which texture is currently bound to minimize the number of glBindTexture() and Tessellator.draw() calls needed */
		int boundTex = 0;

		/* Cycle through the Glyphs to be rendered */
		for (FontGlyph glyph : entry.glyphs) {
			final Texture texture = glyph.texture;

			/*
			* Make sure the OpenGL texture storing this glyph's image is bound (if not already bound). All pending glyphs in the
			* Tessellator's vertex array must be drawn before switching textures, otherwise they would erroneously use the new
			* texture as well.
			*/
			if (boundTex != texture.id()) {
				if (boundTex != 0)
					tess.draw(GL_QUADS);
				boundTex = texture.bind().id();
			}
			final float x1 = x + glyph.x;
			final float x2 = x1 + texture.width();
			final float y1 = y + glyph.y;
			final float y2 = y1 + texture.height();
			tess.setTexture(texture.u(), texture.v()).addVertex(x1, y1, 0);
			tess.setTexture(texture.u(), texture.v1()).addVertex(x1, y2, 0);
			tess.setTexture(texture.u1(), texture.v1()).addVertex(x2, y2, 0);
			tess.setTexture(texture.u1(), texture.v()).addVertex(x2, y1, 0);
		}

		/* Draw any remaining glyphs in the Tessellator's vertex array (there should be at least one glyph pending) */
		tess.draw(GL_QUADS);

		return this;
	}
}
