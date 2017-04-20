package pw.knx.feather.util;

import pw.knx.feather.texture.BasicTexture;
import pw.knx.feather.texture.base.Texture;

/**
 * A utility class currently only used to generate Basic textures.
 * <p>
 * This particular interface-with-inner-class structure is
 * implemented as a new form of Singleton model well-suited
 * for classes holding utility methods, as it allows a
 * developer to simply implement the interface to access the
 * methods within via the Singleton object, while also making
 * it easily apparent which classes require a given range of
 * utilities by it's *implements* clause.
 *
 * @author KNOXDEV
 * @since 8/9/2016 00:53
 */
public interface TextureUtils {
	/**
	 * The static Singleton Object containing TextureUtils
	 */
	TextureUtil tex = new TextureUtil();

	/**
	 * The Singleton inner-class which contains the utility methods
	 */
	class TextureUtil {

		/**
		 * This line exists solely to prevent additional instantiation
		 */
		private TextureUtil() {
		}

		/**
		 * @return a freshly instantiated BasicTexture object
		 */
		public Texture createTexture() {
			return new BasicTexture();
		}

		/**
		 * @param u      The x coordinate of the top-left texture point, on a float scale from 0 to 1.0
		 * @param v      The y coordinate of the top-left texture point, on a float scale from 0 to 1.0
		 * @param u1     The x coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
		 * @param v1     The y coordinate of the bottom-right texture point, on a float scale from 0 to 1.0
		 * @param width  The width of the texture rendering in pixels
		 * @param height The height of the texture rendering in pixels
		 * @return a freshly instantiated BasicTexture object with the parameters provided
		 */
		public Texture createTexture(float u, float v, float u1, float v1, float width, float height) {
			final Texture texture = this.createTexture();
			texture.setU(u);
			texture.setV(v);
			texture.setU1(u1);
			texture.setV1(v1);
			texture.setWidth(width);
			texture.setHeight(height);
			return texture;
		}

		/**
		 * Like above, creates a new BasicTexture object with the parameters provided.
		 * The difference is that it allows you to pass pixel-based U&V rather than 0-1 floats.
		 * In exchange for automatically calculating U, V, U1, and V1, the method requires the texture
		 * resource's original dimensions. (64, 256, etc.)
		 *
		 * @param u          Starting x coordinate of section of the origin resource to be rendered in pixels.
		 * @param v          Starting y coordinate of section of the origin resource to be rendered in pixels.
		 * @param width      The width of section of the origin resource to be rendered in pixels.
		 * @param height     The height of section of the origin resource to be rendered in pixels.
		 * @param dimensions The origin texture resource's original dimensions. (64, 256, etc.)
		 * @return a freshly instantiated BasicTexture object with the parameters provided
		 */
		public Texture createTexture(float u, float v, float width, float height, float dimensions) {
			return this.createTexture(u / dimensions, v / dimensions, (u + width) / dimensions, (v + height) / dimensions, width, height);
		}

		/**
		 * Like above, creates a new BasicTexture object with the parameters provided.
		 * The difference is that it allows you to pass pixel-based U&V rather than 0-1 floats.
		 * In exchange for automatically calculating U, V, U1, and V1, the method requires the texture
		 * resource's original dimensions. (64, 256, etc.)
		 *
		 * @param id         The OpenGL Texture ID you wish to create a Texture for.
		 * @param u          Starting x coordinate of section of the origin resource to be rendered in pixels.
		 * @param v          Starting y coordinate of section of the origin resource to be rendered in pixels.
		 * @param width      The width of section of the origin resource to be rendered in pixels.
		 * @param height     The height of section of the origin resource to be rendered in pixels.
		 * @param dimensions The origin texture resource's original dimensions. (64, 256, etc.)
		 * @return a freshly instantiated BasicTexture object with the parameters provided
		 */
		public Texture createTexture(int id, float u, float v, float width, float height, float dimensions) {
			final Texture texture = this.createTexture(u, v, width, height, dimensions);
			texture.setID(id);
			return texture;
		}
	}
}