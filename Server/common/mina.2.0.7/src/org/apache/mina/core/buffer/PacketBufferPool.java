package org.apache.mina.core.buffer;

import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;
import java.util.List;

public abstract class PacketBufferPool {
	public static int pkgSize = 1024 * 10;

	// public static int pkgCount = 100;
	// private static ConcurrentLinkedQueue<IoBuffer> packetBufferQueue = null;
	// private static boolean init = false;
	// private static AtomicInteger freeCount = new AtomicInteger();

	public static final void initPool(IoBufferAllocator ioBufferAllocator, int bigPkgSize, int bigPkgCount, int pkgSize, int pkgCount) {
		PacketBufferPool.pkgSize = 5120;
		IoBuffer.setAllocator(ioBufferAllocator);
	}

	public final static void freePacketBuffer(IoBuffer ioBuffer) {
		// if (!init) {
		// initPool();
		// }
		// if (ioBuffer != null && ioBuffer.capacity() == pkgSize) {
		// ioBuffer.clear();
		// if (!ioBuffer.isDerived()) {
		// ioBuffer.shrink();
		// }
		// packetBufferQueue.offer(ioBuffer);
		// }
	}

	public final static IoBuffer getPacketBuffer() {
		// if (!init) {
		// initPool();
		// }
		return getIoBuffer();
	}

	private final static IoBuffer getIoBuffer() {
		IoBuffer ioBuffer = null;
		// while ((ioBuffer = packetBufferQueue.poll()) != null) {
		// if (ioBuffer.position() == 0) {
		// break;
		// }
		// }
		if (ioBuffer == null) {
			ioBuffer = IoBuffer.allocate(pkgSize);
			ioBuffer.setAutoExpand(true);
		}
		return ioBuffer;
	}

	public static final IoBuffer getPacketBuffer(int size) {
		// if (!init) {
		// initPool();
		// }
		IoBuffer ioBuffer = IoBuffer.allocate(size);
		ioBuffer.setAutoExpand(true);
		return ioBuffer;
	}

	// public static void addIoBuffer(IoBuffer ioBuffer) {
	// if (ioBuffer != null) {
	// ioBuffer.clear();
	// if (!ioBuffer.isDerived()) {
	// ioBuffer.shrink();
	// }
	// packetBufferQueue.offer(ioBuffer);
	// }
	// }
	/**
	 * Declares this buffer and all its derived buffers are not used anymore so that it can be reused by some
	 * {@link IoBufferAllocator} implementations. It is not mandatory to call this method, but you might want to invoke
	 * this method for maximum performance.
	 */
	public abstract void free();

	/**
	 * Returns the underlying NIO buffer instance.
	 */
	public abstract ByteBuffer buf();

	/**
	 * @see ByteBuffer#capacity()
	 */
	public abstract int capacity();

	/**
	 * @see java.nio.Buffer#position()
	 */
	public abstract int position();

	/**
	 * @see java.nio.Buffer#position(int)
	 */
	public abstract IoBuffer position(int newPosition);

	/**
	 * @see java.nio.Buffer#limit()
	 */
	public abstract int limit();

	/**
	 * @see java.nio.Buffer#limit(int)
	 */
	public abstract IoBuffer limit(int newLimit);

	/**
	 * @see java.nio.Buffer#mark()
	 */
	public abstract IoBuffer mark();

	/**
	 * @see java.nio.Buffer#flip()
	 */
	public abstract IoBuffer flip();

	/**
	 * @see java.nio.Buffer#hasRemaining()
	 */
	public abstract boolean hasRemaining();

	/**
	 * @see ByteBuffer#array()
	 */
	public abstract byte[] array();

	/**
	 * @see ByteBuffer#get()
	 */
	public abstract byte get();

	/**
	 * @see ByteBuffer#put(byte)
	 */
	public abstract IoBuffer put(byte b);

	/**
	 * @see ByteBuffer#get(int)
	 */
	public abstract byte get(int index);

	/**
	 * @see ByteBuffer#put(int, byte)
	 */
	public abstract IoBuffer put(int index, byte b);

	/**
	 * @see ByteBuffer#get(byte[], int, int)
	 */
	public abstract IoBuffer get(byte[] dst, int offset, int length);

	/**
	 * @see ByteBuffer#get(byte[])
	 */
	public abstract IoBuffer get(byte[] dst);

	/**
	 * Writes the content of the specified <tt>src</tt> into this buffer.
	 */
	public abstract IoBuffer put(ByteBuffer src);

	/**
	 * Writes the content of the specified <tt>src</tt> into this buffer.
	 */
	public abstract IoBuffer put(IoBuffer src);

	/**
	 * @see ByteBuffer#put(byte[], int, int)
	 */
	public abstract IoBuffer put(byte[] src, int offset, int length);

	/**
	 * @see ByteBuffer#put(byte[])
	 */
	public abstract IoBuffer put(byte[] src);

	/**
	 * @see ByteBuffer#getChar()
	 */
	public abstract char getChar();

	/**
	 * @see ByteBuffer#putChar(char)
	 */
	public abstract IoBuffer putChar(char value);

	/**
	 * @see ByteBuffer#getChar(int)
	 */
	public abstract char getChar(int index);

	/**
	 * @see ByteBuffer#putChar(int, char)
	 */
	public abstract IoBuffer putChar(int index, char value);

	/**
	 * @see ByteBuffer#getShort()
	 */
	public abstract short getShort();

	/**
	 * @see ByteBuffer#putShort(short)
	 */
	public abstract IoBuffer putShort(short value);

	/**
	 * @see ByteBuffer#getShort()
	 */
	public abstract short getShort(int index);

	/**
	 * @see ByteBuffer#putShort(int, short)
	 */
	public abstract IoBuffer putShort(int index, short value);

	/**
	 * @see ByteBuffer#getInt()
	 */
	public abstract int getInt();

	/**
	 * @see ByteBuffer#putInt(int)
	 */
	public abstract IoBuffer putInt(int value);

	/**
	 * @see ByteBuffer#getInt(int)
	 */
	public abstract int getInt(int index);

	/**
	 * @see ByteBuffer#putInt(int, int)
	 */
	public abstract IoBuffer putInt(int index, int value);

	/**
	 * @see ByteBuffer#getLong()
	 */
	public abstract long getLong();

	/**
	 * @see ByteBuffer#putLong(int, long)
	 */
	public abstract IoBuffer putLong(long value);

	/**
	 * @see ByteBuffer#getLong(int)
	 */
	public abstract long getLong(int index);

	/**
	 * @see ByteBuffer#putLong(int, long)
	 */
	public abstract IoBuffer putLong(int index, long value);

	/**
	 * @see ByteBuffer#getFloat()
	 */
	public abstract float getFloat();

	/**
	 * @see ByteBuffer#putFloat(float)
	 */
	public abstract IoBuffer putFloat(float value);

	/**
	 * @see ByteBuffer#getFloat(int)
	 */
	public abstract float getFloat(int index);

	/**
	 * @see ByteBuffer#putFloat(int, float)
	 */
	public abstract IoBuffer putFloat(int index, float value);

	/**
	 * @see ByteBuffer#getDouble()
	 */
	public abstract double getDouble();

	/**
	 * @see ByteBuffer#putDouble(double)
	 */
	public abstract IoBuffer putDouble(double value);

	/**
	 * @see ByteBuffer#getDouble(int)
	 */
	public abstract double getDouble(int index);

	/**
	 * @see ByteBuffer#putDouble(int, double)
	 */
	public abstract IoBuffer putDouble(int index, double value);

	/**
	 * Returns hexdump of this buffer. The data and pointer are not changed as a result of this method call.
	 * 
	 * @return hexidecimal representation of this buffer
	 */
	public abstract String getHexDump();

	/****************************************************************************************************/
	/****************************************************************************************************/
	/****************************************************************************************************/
	/****************************************************************************************************/
	/****************************************************************************************************/
	/** 新加的 主类别，子类别 方法封装 */
	public static final class DataType {
		public static final short TYPE_BOOLEAN = (short) (0x0000);
		public static final short TYPE_BYTE    = (short) (0x1000);
		public static final short TYPE_SHORT   = (short) (0x2000);
		public static final short TYPE_INT     = (short) (0x3000);
		public static final short TYPE_LONG    = (short) (0x4000);
		public static final short TYPE_STRING  = (short) (0x5000);
		public static final short TYPE_LIST    = (short) (0x6000);
		public static final short TYPE_FLOAT   = (short) (0x9000);
		public static final short VALUE_OFFSET = (short) (0x8000);
	}

	/** 协议ID */
	public void setProtocol(int protocolID) {
		putInt(0);
		putInt(protocolID);
	}

	/** 协议ID */
	public void setProtocol2(short protocolID) {
		putShort((short) 0);
		putShort(protocolID);
	}

	/** 连接ID */
	public void setNetConfirm(int netID) {
		putInt(netID);
	}

	/** 脚本名字 */
	public void setScriptName(String name) {
		putString(name);
	}

	/** 脚本名字的Hashcode值 */
	public void setScriptName(int nameHashcode) {
		putInt(nameHashcode);
	}

	/** 客户端 window 是否重叠 true:可重叠 false:不可重叠 */
	public void setOverlap(boolean canOverlap) {
		putBoolean(canOverlap);
	}

	/** 指令命令头 */
	public void setCmd(byte cmdid) {
		put(cmdid);
	}

	/** 主类别 id */
	public void setMain(short mainID) {
		// if(mainID==503){
		// try{
		// int a =1/0;
		// }catch (Exception e) {
		// System.out.println("value == " + mainID);
		// e.printStackTrace();
		// }
		// }
		// put((byte) mainID);
		putShort(mainID);
	}

	/** 子类别id */
	public void setSub(int subID) {
		// put((byte) subID);
		putInt(subID);
	}

	/** 序号 ID */
	public void setSerial(int serialID) {
		// put((byte) serialID);
		putInt(serialID);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setProperty(short key, long value) { // int
		putShort((short) (key | DataType.TYPE_LONG));
		putLong(value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setProperty(short key, int value) { // int
		putShort((short) (key | DataType.TYPE_INT));
		putInt(value);

	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setProperty(short key, float value) { // float
		putShort((short) (key | DataType.TYPE_INT));
		putFloat(value);

	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setProperty(short key, short value) { // short
		putShort((short) (key | DataType.TYPE_SHORT));
		putShort(value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setProperty(short key, byte value) {// byte
		putShort((short) (key | DataType.TYPE_BYTE));
		put(value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setProperty(short key, boolean value) { // int
		putShort((short) (key | DataType.TYPE_BOOLEAN));
		putBoolean(value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setProperty(short key, String value) {// byte
		if (value != null) {
			putShort((short) (key | DataType.TYPE_STRING));
			putString(value);
		}
	}

	/**
	 * 获取键标识的数据类型
	 * 
	 * @param key
	 *            原始键
	 * @return 数据类型
	 */
	public short getDataType(short key) {
		return (short) (key & 0x7000);
	}

	/**
	 * 获取真实的键
	 * 
	 * @param key
	 *            原始键
	 * @return 真实键
	 */
	public short getRealKey(short key) {
		return (short) (key & 0x0fff);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setSearchID(short key, long value) { // int
		setProperty(key, value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setSearchID(short key, int value) { // int
		setProperty(key, value);

	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setSearchID(short key, short value) { // short
		setProperty(key, value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setSearchID(short key, byte value) {// byte
		setProperty(key, value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setSearchID(short key, boolean value) { // int
		setProperty(key, value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 覆盖
	 */
	public void setSearchID(short key, String value) {// byte
		setProperty(key, value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 变化
	 */
	public void setPropertyOffset(short key, long value) { // int
		putShort((short) (key | DataType.TYPE_LONG | DataType.VALUE_OFFSET));
		putLong(value);

	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 变化
	 */
	public void setPropertyOffset(short key, float value) { // int
		putShort((short) (key | DataType.TYPE_INT | DataType.VALUE_OFFSET));
		putFloat(value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 变化
	 */
	public void setPropertyOffset(short key, int value) { // int
		putShort((short) (key | DataType.TYPE_INT | DataType.VALUE_OFFSET));
		putInt(value);

	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 变化
	 */
	public void setPropertyOffset(short key, short value) { // short
		putShort((short) (key | DataType.TYPE_SHORT | DataType.VALUE_OFFSET));
		putShort(value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 变化
	 */
	public void setPropertyOffset(short key, byte value) {// byte
		putShort((short) (key | DataType.TYPE_BYTE | DataType.VALUE_OFFSET));
		put(value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 变化
	 */
	public void setPropertyOffset(short key, boolean value) {// byte
		putShort((short) (key | DataType.TYPE_BOOLEAN | DataType.VALUE_OFFSET));
		putBoolean(value);
	}

	/**
	 * @param key
	 *            属性序号
	 * @param value
	 *            属性值 变化
	 */
	public void setPropertyOffset(short key, String value) {// byte
		if (value != null) {
			putShort((short) (key | DataType.TYPE_STRING | DataType.VALUE_OFFSET));
			putString(value);
		}
	}

	/**
	 * @param key
	 * @param values
	 *            List<Byte>
	 */
	public void setPropertyList(short key, List<Short> values) {
		if (values != null) {
			putShort((short) (key | DataType.TYPE_LIST));
			for (short value : values) {
				putShort(value);
			}
			putShort((short) -1);
		}
	}

	/**
	 * 结束主类别
	 */
	public void endMain() {
		putShort((short) -1);
	}

	/**
	 * 结束子类别
	 */
	public void endSub() {
		putInt(-1);
	}

	/**
	 * 结束序号
	 */
	public void endSerial() {
		putInt(-1);
	}

	/**
	 * 结束属性序号
	 */
	public void endProperty() {
		putShort((short) -1);
	}

	/**
	 * 结束指令
	 */
	public void endCmd() {
		put((byte) -1);
	}

	public final int getConnectId() {
		return getInt(limit() - 4);
	}

	/**
	 * @param roleID
	 * @param animationName
	 */
	public void setRoleAnimation(int roleID, String animationName) {
		putInt(roleID);
		putString(animationName);
	}

	/**
	 * 结束骨骼
	 */
	public void endSkeleton() {
		put((byte) -1);
	}

	/**
	 * 结束动画指令
	 */
	public void endAnimtion() {
		putInt(-1);
	}

	private byte[] writeByteArr = null;
	private byte[] getByteArr   = new byte[80];
	private char[] chararr      = new char[80];

	public final int putString(String str) {
		if (str == null) {
			str = "";
		}
		int strlen = str.length();
		int utflen = 0;
		int c, count = 0;

		for (int i = 0; i < strlen; i++) {
			c = str.charAt(i);
			if ((c >= 0x0001) && (c <= 0x007F)) {
				utflen++;
			} else if (c > 0x07FF) {
				utflen += 3;
			} else {
				utflen += 2;
			}
		}
		if (utflen > 65535)
			try {
				throw new UTFDataFormatException("encoded string too long: " + utflen + " bytes");
			} catch (UTFDataFormatException e) {
				e.printStackTrace();
			}

		if (this.writeByteArr == null || (this.writeByteArr.length < (utflen + 2)))
			this.writeByteArr = new byte[(utflen * 2) + 2];
		byte[] bytearr = this.writeByteArr;

		bytearr[count++] = (byte) ((utflen >>> 8) & 0xFF);
		bytearr[count++] = (byte) ((utflen >>> 0) & 0xFF);

		int i = 0;
		for (i = 0; i < strlen; i++) {
			c = str.charAt(i);
			if (!((c >= 0x0001) && (c <= 0x007F)))
				break;
			bytearr[count++] = (byte) c;
		}

		for (; i < strlen; i++) {
			c = str.charAt(i);
			if ((c >= 0x0001) && (c <= 0x007F)) {
				bytearr[count++] = (byte) c;

			} else if (c > 0x07FF) {
				bytearr[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
				bytearr[count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
				bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
			} else {
				bytearr[count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
				bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
			}
		}
		put(bytearr, 0, utflen + 2);
		return utflen + 2;
	}

	/**
	 * 读取一个Boolean
	 * 
	 * @return
	 */
	public final boolean getBoolean() {
		int ch = get();
		return (ch != 0);
	}

	public final String getString() {
		try {
			int utflen = getShort();
			byte[] bytearr = null;
			char[] chararr = null;
			if (this.getByteArr.length < utflen) {
				this.getByteArr = new byte[utflen * 2];
				this.chararr = new char[utflen * 2];
			}
			chararr = this.chararr;
			bytearr = this.getByteArr;

			int c, char2, char3;
			int count = 0;
			int chararr_count = 0;

			get(bytearr, 0, utflen);

			while (count < utflen) {
				c = (int) bytearr[count] & 0xff;
				if (c > 127)
					break;
				count++;
				chararr[chararr_count++] = (char) c;
			}

			while (count < utflen) {
				c = (int) bytearr[count] & 0xff;
				switch (c >> 4) {
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7: {
						count++;
						chararr[chararr_count++] = (char) c;
						break;
					}
					case 12: {

					}
					case 13: {
						count += 2;
						if (count > utflen)
							throw new UTFDataFormatException("malformed inwrite: partial character at end");
						char2 = (int) bytearr[count - 1];
						if ((char2 & 0xC0) != 0x80)
							throw new UTFDataFormatException("malformed inwrite around byte " + count);
						chararr[chararr_count++] = (char) (((c & 0x1F) << 6) | (char2 & 0x3F));
						break;
					}
					case 14: {
						count += 3;
						if (count > utflen)
							throw new UTFDataFormatException("malformed inwrite: partial character at end");
						char2 = (int) bytearr[count - 2];
						char3 = (int) bytearr[count - 1];
						if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
							throw new UTFDataFormatException("malformed inwrite around byte " + (count - 1));
						chararr[chararr_count++] = (char) (((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
						break;
					}
					default: {
						throw new UTFDataFormatException("malformed inwrite around byte " + count);
					}
				}
			}
			return new String(chararr, 0, chararr_count);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public final void putBoolean(boolean v) {
		put((byte) (v ? 1 : 0));
	}

	public final IoBuffer putByteArray(byte[] src) {
		putInt(src.length);
		return put(src, 0, src.length);
	}

	public final IoBuffer putByteArray(byte[] src, int offset, int length) {
		putInt(length);
		return put(src, offset, length);
	}

	public void putPacket(IoBuffer packet) {
		if (packet == null) {
			return;
		}
		put(packet.array(), 0, packet.limit());
	}

	/**
	 * 首先从当前位置开始读取一个int作为数组的长度，然后读取之后的数据并封装到byte数组内
	 * 
	 * @return
	 */
	public final byte[] getByteArray() {
		int length = getInt();
		byte[] dest = new byte[length];
		get(dest, 0, length);
		return dest;
	}

	public IoBuffer duplicateBuffer() {
		IoBuffer buffer = getPacketBuffer();
		buffer.put(array(), 0, position());
		return buffer;
	}
}