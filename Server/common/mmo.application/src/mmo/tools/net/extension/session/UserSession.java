package mmo.tools.net.extension.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import mmo.tools.net.NetSession;
import mmo.tools.net.extension.config.Constants;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;
import org.apache.mina.core.session.IoSession;

public class UserSession implements NetSession {
	private static final AtomicLong OBJECT_ID_GENERATOR = new AtomicLong(1);
	private static final List<Integer> platformNull = new ArrayList<Integer>();
	public static final String KEY_SESSION = "product-application";
	public static final String REMOTE_ADDRESS = "remote-address";
	private int maxPacketSize = 512 * 1024;
	/** 协议版本号 */
	protected byte proVersion;
	/** 网络回话对象 */
	protected IoSession session;
	/** 为需要拆分的大数据包生成序列号 */
	private AtomicInteger sequence = new AtomicInteger();
	/** 分配一个用户 */
	protected NetRole role;
	/** 网络断开重连次数 */
	protected int reconnectCount;
	/** 平台列表 */
	protected List<Integer> platformList = null;
	/** 远程应用的ID */
	protected int remoteAppId;
	/** 远程应用的名称 */
	protected String remoteAppName;
	/** 对象编号 */
	final protected long objectId = OBJECT_ID_GENERATOR.getAndIncrement();;

	public UserSession() {
	}

	public long getObjectId() {
		return objectId;
	}

	public void changeRole(NetRole role) {
	}

	/**
	 * 成功进入角色数量
	 * 
	 * @return 成功进入角色数量
	 */
	public int getEnterCount() {
		return 0;
	}

	public int getRemoteAppId() {
		return remoteAppId;
	}

	public void setRemoteAppId(int remoteAppId) {
		this.remoteAppId = remoteAppId;
	}

	public String getRemoteAppName() {
		return remoteAppName;
	}

	public void setRemoteAppName(String remoteAppName) {
		this.remoteAppName = remoteAppName;
	}

	/**
	 * 设置成功进入角色数量
	 * 
	 * @param 成功进入角色数量
	 */
	public void setEnterCount(int enterCount) {
	}

	public int getConnectCount() {
		return 0;
	}

	public void setConnectCount(int connectCount) {

	}

	/**
	 * 添加一个新平台
	 * 
	 * @param platform
	 */
	public final void addPlatform(int platform) {
		if (platformList == null) {
			platformList = new ArrayList<Integer>();
		}
		platformList.add(platform);
	}

	/**
	 * 获取平台列表
	 * 
	 * @return 平台列表
	 */
	public final List<Integer> getPlatformList() {
		if (platformList == null) {
			return platformNull;
		}
		return platformList;
	}

	public final void close(boolean immediately) {
		NetRole _role = role;
		if (_role != null) {
			_role.outline();
		}
		IoSession _session = session;
		if (_session != null && _session.isConnected()) {
			_session.close(immediately);
		}
	}

	@Override
	public void createRole(String name, byte sex, byte profession) {
		// TODO Auto-generated method stub

	}

	public int getMaxPacketSize() {
		return maxPacketSize;
	}

	public byte getProVersion() {
		return proVersion;
	}

	public int getReconnectCount() {
		return reconnectCount;
	}

	public NetRole getRole() {
		return role;
	}

	/**
	 * 获取套接字
	 * 
	 * @return
	 */
	public final SocketAddress getSocketAddress() {
		if (session != null) {
			return session.getRemoteAddress();
		}
		return new InetSocketAddress(0);
	}

	public void heartbeat() {
	}

	public void responseHeartbeat() {

	}

	public boolean isClosing() {
		IoSession temp = session;
		if (temp == null) {
			return true;
		}
		return temp.isClosing();
	}

	public boolean isConnected() {
		IoSession temp = session;
		if (temp == null) {
			return false;
		}
		return temp.isConnected() && !isClosing();
	}

	@Override
	public void login(NetRole role, boolean result, String message, Object data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void managerRole(byte flag, int roleId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void outline() {
		// TODO Auto-generated method stub
	}

	@Override
	public void register(NetRole role, boolean result, String message) {
		// TODO Auto-generated method stub

	}

	public void releaseNet() {
		session = null;
		role = null;
	}

	public void releaseSession(String sessionId) {
	}

	@Override
	public void selectServer() {
		// TODO Auto-generated method stub

	}

	/**
	 * 推送服务器列表
	 */
	@Override
	public void serverList() {

	}

	/**
	 * 发送数据包，如果数据包超出数据包最大值限制则会被拆分出多个小包，然后小包按先后顺序发出
	 * 
	 * @param packet
	 *            把数据包发送到网络
	 */
	public void sendData(byte[] packet) {
		if (packet == null) {
			return;
		}
		IoBuffer data = IoBuffer.getPacketBuffer();
		data.putInt(packet.length);
		data.put(packet, 0, packet.length);
	}

	/**
	 * 把一个包发到网络上去,支持大于缓冲区的包
	 * 
	 * @param data
	 *            ByteBuffer
	 * @throws IOException
	 */
	public void sendData(IoBuffer data) {
		IoSession temp = session;
		if (temp == null || temp.isClosing() || data == null) {
			PacketBufferPool.freePacketBuffer(data);
			return;
		}
		data.flip();
		data.putInt(0, data.limit() - 4);
		int limit = data.limit();
		if (limit > getMaxPacketSize()) {
			int length = data.limit() - 4;
			byte[] bytes = new byte[length];
			data.getInt();// 预留的长度
			data.get(bytes, 0, length);
			splitPacket(temp, bytes);
			PacketBufferPool.freePacketBuffer(data);
		} else {
			temp.write(data);
		}
	}

	@Override
	public void sendData(String sessionId, IoBuffer packet) {
		sendData(packet);
	}

	/**
	 * 把一个包完整的发到网络上去,支持大于缓冲区的包
	 * 
	 * @param data
	 *            ByteBuffer
	 * @throws IOException
	 */
	public void sendFullData(IoBuffer data) {
		IoSession temp = session;
		if (temp == null || temp.isClosing() || data == null) {
			PacketBufferPool.freePacketBuffer(data);
			return;
		}
		data.flip();
		data.putInt(0, data.limit() - 4);
		temp.write(data);
	}

	public void sessionCreated() {

	}

	public void setMaxPacketSize(int maxPacketSize) {
		this.maxPacketSize = maxPacketSize;
	}

	public void setProVersion(byte version) {
		this.proVersion = version;
	}

	public void setReconnectCount(int reconnectCount) {
		this.reconnectCount = reconnectCount;
	}

	public void setRole(NetRole role) {
		this.role = role;
	}

	public void setUserSession(IoSession session) {
		this.session = session;
		if (this.session != null) {
			String addIp = "";
			SocketAddress sa = session.getRemoteAddress();
			if (sa != null) {
				addIp = sa.toString();
			}
			role.setRemoteAddress(addIp);
		}
	}

	public void splitPacket(byte[] data) {
		splitPacket(session, data);
	}

	protected void splitPacket(IoSession session, byte[] data) {
		int length = data.length;
		int seq = sequence.getAndIncrement();
		int sub = 0;
		int sup = 0;
		int blockCount = (length % getMaxPacketSize() == 0) ? length
				/ getMaxPacketSize() : length / getMaxPacketSize() + 1;
		for (int i = 0; i < blockCount; i++) {
			IoBuffer packet = PacketBufferPool.getPacketBuffer();
			packet.setProtocol(Constants.PACKET_BLOCK);
			packet.putInt(seq);// 完整包的标识
			packet.putInt(length);// 总长度
			packet.putInt(blockCount);// 子包数
			sup = sub + getMaxPacketSize();
			if (sup > length) {
				sup = length;
			}
			packet.putInt(sub);// 起点
			packet.putInt(0);
			packet.putInt(sup - sub);// 数据长度
			packet.put(data, sub, sup - sub);
			packet.flip();
			packet.putInt(0, packet.limit() - 4);
			session.write(packet);
			sub = sup;
		}
	}

	@Override
	public String toString() {
		return "UserSession [session=" + session + "]";
	}

	@Override
	public void unheardPacket(IoBuffer packet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateSecurityCode(int connectId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void toMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void toSelectRole() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectRole() {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateExchange(NetRole role, int eventId, String exchangeCode) {
		// TODO Auto-generated method stub

	}

	public void validateExchangeResult(String sessionId, int accoutId,
			int roleId, int eventId, byte result, int giftId) {

	}

	public void reponseConfirm(int roleId, int eventId, boolean result) {

	}

	public void reponseConfirm(int roleId, int eventId, byte result) {

	}

	@Override
	public void pushData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterPassword(NetRole netRole, int eventId, String oldPwd,
			String newPwd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validatePhone(NetRole netRole, int eventId, String phone,
			boolean binded) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bindPhone(NetRole netRole, int eventId, String phone,
			String pwd, String vcode) {
		// TODO Auto-generated method stub

	}

	public void register() {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateSecurityCode(int eventId, String securityCode) {
		// TODO Auto-generated method stub

	}

	/**
	 * 反向注册验证码
	 */
	public void reverseSecurityCode() {

	}

	public String getAddress4User(String remoteIp) {
		return "";
	}

	public String getLocalAddress4User() {
		return "";
	}

	public String getWwwAddress4User() {
		return "";
	}

	public void registerSuccess() {

	}
}
