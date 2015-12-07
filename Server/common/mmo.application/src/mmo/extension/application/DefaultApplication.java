package mmo.extension.application;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import mmo.tools.net.extension.logic.ILogicHandler;
import mmo.tools.net.extension.session.NetRole;
import mmo.tools.net.extension.session.UserSession;
import mmo.tools.thread.heartbeat.LogicHeartbeat;

import org.apache.mina.core.buffer.IoBuffer;

public class DefaultApplication implements INetApplication {
	protected Charset                          charset  = Charset.forName("UTF-8");
	protected String                           name     = "application";
	/** 在线用户容器(一个UserSession对象对应一个用户) */
	private ConcurrentHashMap<String, NetRole> netRoles = new ConcurrentHashMap<String, NetRole>();

	/**
	 * 用户池删除用户
	 */
	public void removeNetRole(String key) {
		if (key == null) {
			return;
		}
		netRoles.remove(key);
	}

	public int getNetRoleCount() {
		return netRoles.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public ILogicHandler getLogicHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NetRole generatorRole() {
		return new NetRole();
	}

	@Override
	public UserSession generatorNetSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicHeartbeat getHeartbeat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public List<Integer> getPlatforms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connected(UserSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getConnectCount() {
		return getNetRoleCount();
	}

	@Override
	public void createConnect(UserSession session) {
		NetRole role = session.getRole();
		if (role == null || netRoles.get(role.getSessionId()) == role) {
			return;
		}
		netRoles.put(role.getSessionId(), role);
	}

	@Override
	public void closeConnect(UserSession session) {
		if (session.getRole() != null) {
			removeNetRole(session.getRole().getSessionId());
		}
	}

	@Override
	public void pushData2Gateway(IoBuffer data) {
		// TODO Auto-generated method stub

	}

	@Override
	public Charset getCharset() {
		return charset;
	}

	public NetRole getNetRole(String sessionId) {
		return netRoles.get(sessionId);
	}

	/**
	 * 获取所有在线玩家
	 * 
	 * @return
	 */
	public NetRole[] getAllNetRole() {
		try {
			Collection<NetRole> users = netRoles.values();
			NetRole[] allUser = new NetRole[netRoles.size()];
			users.toArray(allUser);
			return allUser;
		} catch (Exception e) {
			Collection<NetRole> users = netRoles.values();
			NetRole[] allUser = new NetRole[netRoles.size()];
			users.toArray(allUser);
			return allUser;
		}
	}

	/**
	 * 切换sessionId绑定的角色对象
	 * 
	 * @param role角色对象
	 */
	public void switchNetRole(NetRole role) {
		netRoles.put(role.getSessionId(), role);
	}
}
