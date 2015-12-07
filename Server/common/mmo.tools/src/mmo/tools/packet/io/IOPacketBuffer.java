package mmo.tools.packet.io;

import java.io.IOException;
import java.io.UTFDataFormatException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

final public class IOPacketBuffer {
	// TODO
	/** 新加的 主类别，子类别 方法封装 */
	public static final class DataType {
		public static final short TYPE_BOOLEAN = (short) (1 << 11);
		public static final short TYPE_BYTE    = (short) (1 << 12);
		public static final short TYPE_INT     = (short) (1 << 14);
		public static final short TYPE_LONG    = (short) (1 << 15);
		public static final short TYPE_SHORT   = (short) (1 << 13);
		public static final short TYPE_STRING  = (short) (1 << 10);
		public static final short TYPE_LIST    = (short) (1 << 9);
		public static final short VALUE_OFFSET = (short) (1 << 8);
	}

	protected static final Logger        logger         = Logger.getLogger(IOPacketBuffer.class);
	protected static final AtomicInteger idGenerator    = new AtomicInteger(1);

	/** *****当前缓冲区信息****** */
	protected IoBuffer                   ioBuffer       = null;
	private boolean                      busy;
	/** ***********数据包拆分部分************* */
	/** 数据包中容器中的索引 */
	private int                          indexOfVector  = 0;
	/** 大数据包的序列号 */
	private int                          sequence       = 0;
	/** 标识是否为大数据包 */
	private boolean                      flag           = true;
	/** 大数据包被拆分成块的数量 */
	private int                          blockCount     = 0;
	/** 已经读取到底数据块的数量 */
	private int                          restCount      = 0;
	/** 数据包总长度 */
	private int                          totalLength    = 0;
	/** 已经读取到底数据长度 */
	private int                          currLength     = 0;
	/** 数据包的有效期 */
	private long                         validatyPeriod = 0;
	private int                          id;
	private long                         time;
	public static AtomicInteger          count          = new AtomicInteger();

	public final static IOPacketBuffer allocate(int capacity) {
		return new IOPacketBuffer(IoBuffer.allocate(capacity));
	}

	IOPacketBuffer(IoBuffer ioBuffer) {
		this.ioBuffer = ioBuffer;
	}


	public final int capacity() {
		return ioBuffer.capacity();
	}

	public final IOPacketBuffer clear() {
		ioBuffer.clear();
		return this;
	}

	public final IOPacketBuffer compact() {
		ioBuffer.compact();
		return this;
	}

	public final IOPacketBuffer duplicate() {
		return new IOPacketBuffer(ioBuffer.duplicate());
	}

	/**
	 * 结束动画指令
	 */
	public void endAnimtion() {
		putInt(-1);
	}

	/**
	 * 结束指令
	 */
	public void endCmd() {
		putShort((short) -1);
	}

	/**
	 * 结束主类别
	 */
	public void endMain() {
		putShort((short) -1);
	}

	/**
	 * 结束属性序号
	 */
	public void endProperty() {
		putShort((short) -1);
	}

	/**
	 * 结束序号
	 */
	public void endSerial() {
		putShort((short) -1);
	}

	/**
	 * 结束骨骼
	 */
	public void endSkeleton() {
		put((byte) -1);
	}

	/**
	 * 结束子类别
	 */
	public void endSub() {
		putShort((short) -1);
	}

	public final IOPacketBuffer flip() {
		ioBuffer.flip();
		return this;
	}

	public final IOPacketBuffer get(byte[] dst, int offset, int length) {
		ioBuffer.get(dst, offset, length);
		return this;
	}

	public final int getBlockCount() {
		return blockCount;
	}

	public final boolean getBoolean() {
		int ch = getByte();
		return (ch != 0);
	}

	public final byte getByte() {
		return ioBuffer.get();
	}

	public final byte getByte(int i) {
		return ioBuffer.get(i);
	}

	public final byte[] getByteArray() {
		int length = getInt();
		byte[] dest = new byte[length];
		get(dest, 0, length);
		return dest;
	}

	public final char getChar() {
		return ioBuffer.getChar();
	}

	public final char getChar(int i) {
		return ioBuffer.getChar(i);
	}

	public final int getCurrLength() {
		return currLength;
	}

	public final byte[] getData() {
		return ioBuffer.array();
	}

	public final double getDouble() {
		return Double.longBitsToDouble(getLong());
	}

	public final double getDouble(int i) {
		return Double.longBitsToDouble(getLong(i));
	}

	public final float getFloat() {
		return Float.intBitsToFloat(getInt());
	}

	public final float getFloat(int i) {
		return Float.intBitsToFloat(getInt(i));
	}

	protected final int getIndexOfVector() {
		return indexOfVector;
	}

	public final int getInt() {
		return ioBuffer.getInt();
	}

	public final int getInt(int i) {
		return ioBuffer.getInt(i);
	}

	public final long getLong() {
		return ioBuffer.getLong();
	}

	public final long getLong(int i) {
		return ioBuffer.getLong(i);
	}

	public final int getSequence() {
		return sequence;
	}

	public final short getShort() {
		return ioBuffer.getShort();
	}

	public final short getShort(int i) {
		return ioBuffer.getShort(i);
	}

	public static final int putString(IoBuffer buffer, String str) {
		if (str == null) {
			try {
				throw new IOException("发送到字符串为 NULL");
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		if (utflen > 65535) {
			try {
				throw new UTFDataFormatException("encoded string too long: " + utflen + " bytes");
			} catch (UTFDataFormatException e) {
			}
		}
		byte[] bytearr = new byte[(utflen * 2) + 2];

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
		buffer.put(bytearr, 0, utflen + 2);
		return utflen + 2;
	}

	public static final String getString(IoBuffer buffer) {
		try {
			int utflen = buffer.getShort();
			byte[] bytearr = null;
			char[] chararr = null;
			bytearr = new byte[utflen * 2];
			chararr = new char[utflen * 2];

			int c, char2, char3;
			int count = 0;
			int chararr_count = 0;

			buffer.get(bytearr, 0, utflen);

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
							throw new UTFDataFormatException("malformed input: partial character at end");
						char2 = (int) bytearr[count - 1];
						if ((char2 & 0xC0) != 0x80)
							throw new UTFDataFormatException("malformed input around byte " + count);
						chararr[chararr_count++] = (char) (((c & 0x1F) << 6) | (char2 & 0x3F));
						break;
					}
					case 14: {
						count += 3;
						if (count > utflen)
							throw new UTFDataFormatException("malformed input: partial character at end");
						char2 = (int) bytearr[count - 2];
						char3 = (int) bytearr[count - 1];
						if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
							throw new UTFDataFormatException("malformed input around byte " + (count - 1));
						chararr[chararr_count++] = (char) (((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
						break;
					}
					default: {
						throw new UTFDataFormatException("malformed input around byte " + count);
					}
				}
			}
			return new String(chararr, 0, chararr_count);
		} catch (Exception e) {
			return null;
		}
	}

	public final String getString() {
		return getString(ioBuffer);
	}

	public long getTime() {
		return time;
	}

	public final int getTotalLength() {
		return totalLength;
	}

	public final long getValidatyPeriod() {
		return validatyPeriod;
	}

	protected final boolean isBusy() {
		return busy;
	}

	public final boolean isFlag() {
		return flag;
	}

	public final boolean isReceivedAll() {
		return totalLength == currLength && restCount == 0;
	}

	public final int limit() {
		return ioBuffer.limit();
	}

	public final IOPacketBuffer limit(int newLimit) {
		ioBuffer.limit(newLimit);
		return this;
	}

	final int markValue() {
		return ioBuffer.markValue();
	}

	public final int position() {
		return ioBuffer.position();
	}

	public final IOPacketBuffer position(int newPosition) {
		ioBuffer.position(newPosition);
		return this;
	}

	public final IOPacketBuffer put(byte x) {
		ioBuffer.put(x);
		return this;
	}

	public final IOPacketBuffer put(byte[] src, int offset, int length) {
		ioBuffer.put(src, offset, length);
		return this;
	}

	public final IOPacketBuffer put(int i, byte x) {
		ioBuffer.put(i, x);
		return this;
	}

	public final void putBoolean(boolean v) {
		put((byte) (v ? 1 : 0));
	}

	public final IOPacketBuffer putByteArray(byte[] src) {
		putInt(src.length);
		return put(src, 0, src.length);
	}

	public final IOPacketBuffer putChar(char x) {
		ioBuffer.putChar(x);
		return this;
	}

	public final IOPacketBuffer putChar(int i, char x) {
		ioBuffer.putChar(i, x);
		return this;
	}

	public final IOPacketBuffer putDouble(double x) {
		return putLong(Double.doubleToLongBits(x));

	}

	public final IOPacketBuffer putDouble(int i, double x) {
		return putLong(i, Double.doubleToLongBits(x));

	}

	public final IOPacketBuffer putFloat(float x) {
		return putInt(Float.floatToIntBits(x));
	}

	public final IOPacketBuffer putFloat(int i, float x) {
		putInt(i, Float.floatToIntBits(x));
		return this;
	}

	public final IOPacketBuffer putInt(int x) {
		ioBuffer.putInt(x);
		return this;
	}

	public final IOPacketBuffer putInt(int i, int x) {
		ioBuffer.putInt(i, x);
		return this;
	}

	public final IOPacketBuffer putLong(int i, long x) {
		ioBuffer.putLong(i, x);
		return this;
	}

	public final IOPacketBuffer putLong(long x) {
		ioBuffer.putLong(x);
		return this;
	}

	public void putPacket(IOPacketBuffer packet) {

		put(packet.getData(), 0, packet.limit());
	}

	public final IOPacketBuffer putShort(int i, short x) {
		ioBuffer.putShort(i, x);
		return this;
	}

	public final IOPacketBuffer putShort(short x) {
		ioBuffer.putShort(x);
		return this;
	}

	public final int putString(String str) {
		return putString(ioBuffer, str);
	}

	public final int remaining() {
		return ioBuffer.limit() - ioBuffer.position();
	}

//	public final void reset() {
//		ioBuffer.reset();
//	}

	public void resetTime() {
		time = System.currentTimeMillis();
	}

	public final void setBlockCount(int blockCount) {
		try {
			if (blockCount < 1) {
				throw new Exception("数据包被拆分成的块数小于1");
			}
			if (this.blockCount > 0) {
				if (this.blockCount != blockCount) {
					throw new Exception("数据包的总长度出现差异");
				} else {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.restCount = blockCount;
		this.blockCount = blockCount;
	}

	protected final void setBusy(boolean busy) {
		this.busy = busy;
	}

	/** 指令命令头 */
	public void setCmd(int cmdid) {
		put((byte) cmdid);
	}

	public final void setCurrLength(int currLength) {
		this.currLength += currLength;
		this.restCount--;
	}

	public final void setFlag(boolean flag) {
		this.flag = flag;
	}

	protected final void setIndexOfVector(int index) {
		this.indexOfVector = index;
	}

	/** 主类别 id */
	public void setMain(int mainID) {
		putShort((short) mainID);
	}

	/** 连接ID */
	public void setNetConfirm(int netID) {
		putInt(netID);
	}

	/** 客户端 window 是否重叠 true:可重叠 false:不可重叠 */
	public void setOverlap(boolean canOverlap) {
		putBoolean(canOverlap);
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
	public void setProperty(short key, String value) {// byte
		if (value != null) {
			putShort((short) (key | DataType.TYPE_STRING));
			putString(value);
		}
	}

	/**
	 * @param key
	 * @param values
	 *            List<Byte>
	 */
	public void setPropertyList(short key, List<Byte> values) {
		if (values != null) {
			putShort((short) (key | DataType.TYPE_LIST));
			for (byte value : values) {
				put(value);
			}
			put((byte) -1);
		}
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
	public void setPropertyOffset(short key, String value) {// byte
		if (value != null) {
			putShort((short) (key | DataType.TYPE_STRING | DataType.VALUE_OFFSET));
			putString(value);
		}
	}

	/** 协议ID */
	public void setProtocol(int protocolID) {
		putInt(protocolID);
	}

	/**
	 * @param roleID
	 * @param animationName
	 */
	public void setRoleAnimation(int roleID, String animationName) {
		putInt(roleID);
		putString(animationName);
	}

	/** 脚本名字 */
	public void setScriptName(String name) {
		putString(name);
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
	public void setSearchID(short key, byte value) {// byte
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
	public void setSearchID(short key, long value) { // int
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
	public void setSearchID(short key, String value) {// byte
		setProperty(key, value);
	}

	public final void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/** 序号 ID */
	public void setSerial(int serialID) {
		// put((byte) serialID);
		putShort((short) serialID);
	}

	public void setSkeleton(int skeletonID, String skeletonName) {
		put((byte) skeletonID);
		if (skeletonID != -1 && skeletonName != null) {
			putString(skeletonName);
		}
	}

	/** 子类别id */
	public void setSub(int subID) {
		// put((byte) subID);
		putShort((short) subID);
	}

	public final void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public final void setValidatyPeriod(long validatyPeriod) {
		this.validatyPeriod = validatyPeriod;
	}

	public final synchronized IOPacketBuffer skip(int n) {
		ioBuffer.skip(n);
		return this;
	}

	public final IOPacketBuffer slice() {
		return new IOPacketBuffer(ioBuffer.slice());
	}

	protected final void toBusy() {
		busy = true;
	}

	@Override
	public String toString() {
		return "PacketBuffer [id=" + id + "]";
	}

}
