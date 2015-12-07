package mmo.tools.net.extension.config;

public class Config {
	protected static final String BIG_PKG_SIZE         = "bigPacketBufferSize";
	protected static final String BIG_PKG_COUNT        = "bigPacketBufferCount";
	protected static final String PKG_SIZE             = "packetBufferSize";
	protected static final String PKG_COUNT            = "packetBufferCount";

	/** 大数据包缓存对象大小 */
	protected int                 bigPacketBufferSize  = 1024 * 100;
	/** 大数据包缓存对象初始化数量 */
	protected int                 bigPacketBufferCount = 20;
	/** 数据包缓存对象大小 */
	protected int                 packetBufferSize     = 1024 * 10;
	/** 数据包缓存对象初始化数量 */
	protected int                 packetBufferCount    = 50;

	public int getBigPacketBufferSize() {
		return bigPacketBufferSize;
	}

	public void setBigPacketBufferSize(int bigPacketBufferSize) {
		this.bigPacketBufferSize = bigPacketBufferSize;
	}

	public int getBigPacketBufferCount() {
		return bigPacketBufferCount;
	}

	public void setBigPacketBufferCount(int bigPacketBufferCount) {
		this.bigPacketBufferCount = bigPacketBufferCount;
	}

	public int getPacketBufferSize() {
		return packetBufferSize;
	}

	public void setPacketBufferSize(int packetBufferSize) {
		this.packetBufferSize = packetBufferSize;
	}

	public int getPacketBufferCount() {
		return packetBufferCount;
	}

	public void setPacketBufferCount(int packetBufferCount) {
		this.packetBufferCount = packetBufferCount;
	}
}
