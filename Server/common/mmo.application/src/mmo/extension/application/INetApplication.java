package mmo.extension.application;

import java.nio.charset.Charset;
import java.util.List;

import mmo.tools.net.extension.logic.ILogicHandler;
import mmo.tools.net.extension.session.NetRole;
import mmo.tools.net.extension.session.UserSession;
import mmo.tools.thread.heartbeat.LogicHeartbeat;

import org.apache.mina.core.buffer.IoBuffer;

public interface INetApplication {

	/**
	 * 初始化
	 */
	public void init();

	/**
	 * 启动应用
	 */
	public void start();

	/**
	 * 获取逻辑处理对象
	 * 
	 * @return
	 */
	public ILogicHandler getLogicHandler();

	/**
	 * 创建会话角色
	 * 
	 * @return
	 */
	public NetRole generatorRole();

	/**
	 * 创建会话对象
	 * 
	 * @return
	 */
	public UserSession generatorNetSession();

	/**
	 * 获取心跳类对象
	 * 
	 * @return
	 */
	public LogicHeartbeat getHeartbeat();

	/**
	 * 获取应用名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 设置应用名称
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * 获取应用面向的应用平台
	 * 
	 * @return
	 */
	public List<Integer> getPlatforms();

	/**
	 * 建立网络连接
	 * 
	 * @param session
	 *            网络回话
	 */
	public void connected(UserSession session);

	/**
	 * 获得已经建立连接的数量
	 * 
	 * @return 已经建立连接的数量
	 */
	public int getConnectCount();

	/**
	 * 建立一个新的连接
	 * 
	 * @param session
	 *            网络会话
	 */
	public void createConnect(UserSession session);

	/**
	 * 断开一个连接
	 * 
	 * @param 网络会话
	 */
	public void closeConnect(UserSession session);

	// /**
	// * 网络用户管理器
	// *
	// * @return 网络用户管理器
	// */
	// public NetRoleManager getNetRoleManager();

	/**
	 * 把数据包推送给网关
	 * 
	 * @param data
	 */
	public void pushData2Gateway(IoBuffer data);

	/**
	 * 获取编码集
	 * 
	 * @return 编码集
	 */
	public Charset getCharset();

	/**
	 * 根据回话ID活动角色对象
	 * 
	 * @param sessionId
	 *            会话ID
	 * @return 角色对象
	 */
	public NetRole getNetRole(String sessionId);

	/**
	 * 切换sessionId绑定的角色对象
	 * 
	 * @param role角色对象
	 */
	public void switchNetRole(NetRole role);

	/**
	 * 用户池删除用户
	 */
	public void removeNetRole(String key);

	/**
	 * 获取所有在线玩家
	 * 
	 * @return
	 */
	public NetRole[] getAllNetRole();
}
