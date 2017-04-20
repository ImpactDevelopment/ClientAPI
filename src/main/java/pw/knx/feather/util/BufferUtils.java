package pw.knx.feather.util;

import org.lwjgl.opengl.GL15;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * A utility class currently used to generate, bind, and manage buffers.
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
 * @since 8/9/2016 01:34
 */
public interface BufferUtils {

	/**
	 * The static Singleton Object containing Buffer Utilities
	 */
	BufferUtil buff = new BufferUtil();

	/**
	 * The Singleton inner-class which contains the utility methods
	 */
	class BufferUtil {

		/**
		 * This line exists solely to prevent additional instantiation
		 */
		private BufferUtil() {
		}

		/**
		 * A ByteOrder object containing the native order of the platform
		 */
		private final ByteOrder nativeOrder = ByteOrder.nativeOrder();

		/**
		 * @param capacity The size of the Buffer to be allocated in Bytes
		 * @return The ByteBuffer object allocated to the specified capacity
		 */
		public synchronized ByteBuffer createDirectBuffer(int capacity) {
			return ByteBuffer.allocateDirect(capacity).order(nativeOrder);
		}

		/**
		 * @param id The OpenGL ID of the buffer object to be bound
		 */
		public void bindBuffer(int id) {
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
		}

		/**
		 * @return true if the native order of the platform is Big Endian (Most significant first)
		 */
		public boolean isBigEndian() {
			return nativeOrder == ByteOrder.BIG_ENDIAN;
		}
	}
}