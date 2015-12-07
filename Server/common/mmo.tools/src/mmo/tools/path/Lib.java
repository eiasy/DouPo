package mmo.tools.path;

/**
 * 通用工具
 * 
 * @author 段链
 * 
 */
public class Lib {
	public static Line iter = new Line();

	/**
	 * 获得点[x, y]的int形式
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static final int getPoint(int x, int y) {
		return x << 16 | y;
	}

	/**
	 * 获取点p的x分量
	 * 
	 * @param p
	 * @return
	 */
	public static final int getX(int p) {
		return (p >>> 16) & 0xFFFF;
	}

	/**
	 * 获取点p的y分量
	 * 
	 * @param p
	 * @return
	 */
	public static final int getY(int p) {
		return p & 0xFFFF;
	}

	public static final byte[] ensureCapacity(byte[] buffer, int capacity) {
		if (capacity > buffer.length) {
			buffer = increaseCapacity(buffer, capacity + (capacity >> 1) - buffer.length);
		}
		return buffer;
	}

	public static final long[] ensureCapacity(long[] buffer, int capacity) {
		if (capacity > buffer.length) {
			buffer = increaseCapacity(buffer, capacity + (capacity >> 1) - buffer.length);
		}
		return buffer;
	}

	public static final long[] increaseCapacity(long[] buffer, int capacityIncrement) {
		if (capacityIncrement > 0) {
			long[] temp = new long[buffer.length + capacityIncrement];
			System.arraycopy(buffer, 0, temp, 0, buffer.length);
			buffer = temp;
		}

		return buffer;
	}

	public static final byte[] increaseCapacity(byte[] buffer, int capacityIncrement) {
		if (capacityIncrement > 0) {
			byte[] temp = new byte[buffer.length + capacityIncrement];
			System.arraycopy(buffer, 0, temp, 0, buffer.length);
			buffer = temp;
		}

		return buffer;
	}

	public static final short[] ensureCapacity(short[] buffer, int capacity) {
		if (capacity > buffer.length) {
			buffer = increaseCapacity(buffer, capacity + (capacity >> 1) - buffer.length);
		}
		return buffer;
	}

	public static final short[] increaseCapacity(short[] buffer, int capacityIncrement) {
		if (capacityIncrement > 0) {
			short[] temp = new short[buffer.length + capacityIncrement];
			System.arraycopy(buffer, 0, temp, 0, buffer.length);
			buffer = temp;
		}

		return buffer;
	}

	public static final int[] ensureCapacity(int[] buffer, int capacity) {
		if (capacity > buffer.length) {
			buffer = increaseCapacity(buffer, capacity + (capacity >> 1) - buffer.length);
		}
		return buffer;
	}

	public static final int[] increaseCapacity(int[] buffer, int capacityIncrement) {
		if (capacityIncrement > 0) {
			int[] temp = new int[buffer.length + capacityIncrement];
			System.arraycopy(buffer, 0, temp, 0, buffer.length);
			buffer = temp;
		}

		return buffer;
	}
}
