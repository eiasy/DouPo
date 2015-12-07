package mmo.tools.net;

import java.net.SocketAddress;

import mmo.tools.net.extension.session.NetRole;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public interface NetSession {

	/**
	 * 修改角色
	 * 
	 * @param role
	 *            切换网络回话绑定的角色
	 */
	public void changeRole(NetRole role);

	/**
	 * 获取回话绑定的角色
	 * 
	 * @return 回话绑定的角色
	 */
	public NetRole getRole();

	/**
	 * 在心跳时推送数据
	 */
	public void pushData();

	/**
	 * 返回到游戏主菜单
	 */
	public void toMenu();

	/**
	 * 返回到选择角色界面
	 */
	public void toSelectRole();

	/**
	 * 选择角色进入游戏
	 */
	public void selectRole();

	/**
	 * 断开网络连接
	 * 
	 * @param immediately
	 *            是否立即断开连接
	 */
	public void close(boolean immediately);

	/**
	 * 创建角色
	 * 
	 * @param name
	 *            角色名称
	 * @param sex
	 *            性别
	 * @param profession
	 *            职业
	 */
	public void createRole(String name, byte sex, byte profession);

	/**
	 * 获取单包最大值
	 * 
	 * @return 单包上限
	 */
	public int getMaxPacketSize();

	/**
	 * 获取网络地址
	 * 
	 * @return
	 */
	public SocketAddress getSocketAddress();

	/**
	 * 心跳
	 */
	public void heartbeat();

	/**
	 * 心跳回应
	 */
	public void responseHeartbeat();

	/**
	 * 判断是否正在关闭网络
	 * 
	 * @return true网络正在被关闭
	 */
	public boolean isClosing();

	/**
	 * 判断是否已经建立网络连接
	 * 
	 * @return true已经建立连接
	 */
	public boolean isConnected();

	/**
	 * 登陆结果
	 * 
	 * @param role
	 *            玩家
	 * @param result
	 *            true登陆成功，false登陆失败
	 * @param message
	 *            结果描述
	 * @param data
	 *            数据传递
	 */
	public void login(NetRole role, boolean result, String message, Object data);

	/**
	 * 角色管理
	 * 
	 * @param flag
	 *            操作
	 * @param roleId
	 *            角色编号
	 */
	public void managerRole(byte flag, int roleId);

	/**
	 * 用户下线
	 */
	public void outline();

	/**
	 * 注册
	 * 
	 * @param role
	 *            网络用户
	 * @param result
	 *            true注册成功
	 * @param message
	 *            消息反馈
	 */
	public void register(NetRole role, boolean result, String message);

	/**
	 * 释放回话ID
	 * 
	 * @param sessionId
	 *            回话ID
	 */
	public void releaseSession(String sessionId);

	/**
	 * 选择服务器
	 */
	public void selectServer();

	/**
	 * 推送服务器列表
	 */
	public void serverList();

	/**
	 * 发送数据包，如果数据包超出数据包最大值限制则会被拆分出多个小包，然后小包按先后顺序发出
	 * 
	 * @param packet
	 *            把数据包发送到网络
	 */
	public void sendData(IoBuffer packet);

	/**
	 * 发送数据包，如果数据包超出数据包最大值限制则会被拆分出多个小包，然后小包按先后顺序发出
	 * 
	 * @param packet
	 *            把数据包发送到网络
	 */
	public void sendData(byte[] packet);
	
	/**
	 * 把数据包发送到指定的回话
	 * 
	 * @param sessionId
	 *            网络回话ID
	 * @param packet
	 *            待发送的数据包
	 */
	public void sendData(String sessionId, IoBuffer packet);

	/**
	 * 完整发送数据包
	 * 
	 * @param packet
	 *            数据包
	 */
	public void sendFullData(IoBuffer packet);

	/**
	 * 回话被创建
	 */
	public void sessionCreated();

	/**
	 * 设置封包的最大容量
	 * 
	 * @param maxPacketSize
	 *            数据包最大值
	 */
	public void setMaxPacketSize(int maxPacketSize);

	/**
	 * 设置网络回话对象
	 * 
	 * @param ioSession
	 */
	public void setUserSession(IoSession ioSession);

	/**
	 * 拆包发送
	 * 
	 * @param data
	 */
	public void splitPacket(byte[] data);

	/**
	 * 未知的数据包
	 * 
	 * @param packet
	 */
	public void unheardPacket(IoBuffer packet);

	/**
	 * 验证验证码
	 */
	public void validateSecurityCode(int connectId);

	/**
	 * 验证兑换码
	 * 
	 * @param role
	 *            角色
	 * @param eventId
	 *            事件编号
	 * @param exchangeCode
	 *            兑换码
	 */
	public void validateExchange(NetRole role, int eventId, String exchangeCode);

	/**
	 * 修改密码
	 * 
	 * @param netRole
	 *            角色
	 * @param eventId
	 *            时间编号
	 * @param oldPwd
	 *            旧密码
	 * @param newPwd
	 *            新密码
	 */
	public void alterPassword(NetRole netRole, int eventId, String oldPwd, String newPwd);

	/**
	 * 验证手机
	 * 
	 * @param netRole
	 *            角色
	 * @param eventId
	 *            时间编号
	 * @param oldPwd
	 *            旧密码
	 * @param binded
	 *            是否已经绑定
	 */
	public void validatePhone(NetRole netRole, int eventId, String phone, boolean binded);

	/**
	 * 绑定手机
	 * 
	 * @param netRole
	 *            角色
	 * @param eventId
	 *            事件编号
	 * @param phone
	 *            手机号
	 * @param pwd
	 *            密码
	 * @param validatecode
	 *            验证码
	 */
	public void bindPhone(NetRole netRole, int eventId, String phone, String pwd, String validatecode);

	/**
	 * 验证验证码的合法性
	 * 
	 * @param eventId
	 *            事件编号
	 * @param securityCode
	 *            验证码
	 */
	public void validateSecurityCode(int eventId, String securityCode);

	/**
	 * 反向注册验证码
	 */
	public void reverseSecurityCode();

	/**
	 * 释放网络占用的资源
	 */
	public void releaseNet();

}
