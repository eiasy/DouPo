package mmo.module.cache.role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mmo.tools.util.DateUtil;

/**
 * 缓存账号下面的角色数据
 * 
 * @author 李天喜
 * 
 */
final public class CacheAccountRole {
	/** 一小时内没有 */
	private final static long             OVERTIME_OFFSET = 1000 * 60 * 60 * 8;
	/** 过期时间 */
	private long                          overtime;
	/****************************************************/
	private Map<Integer, List<CacheRole>> serverRoles     = new HashMap<Integer, List<CacheRole>>();
	private Map<Integer, CacheRole>       roleMap         = new ConcurrentHashMap<Integer, CacheRole>();
	/** 账号ID */
	private int                           accountId;
	/** 对象时间被重置过，需要重新排队 */
	private boolean                       reorder;
	/** 是否被强制清理 */
	private boolean                       cleared;

	public CacheAccountRole(int accountId) {
		this.accountId = accountId;
		this.overtime = System.currentTimeMillis() + OVERTIME_OFFSET;
	}

	public int getAccountId() {
		return accountId;
	}

	/**
	 * 判断缓存对象是否已经过期
	 * 
	 * @param currTime
	 *            当前时间
	 * @return true已经过期，需要被回收掉，同时被回收还有同一个账号下的其他缓存角色
	 */
	public final boolean isOvertime(long currTime) {
		return currTime > overtime;
	}

	/**
	 * 重置过期时间
	 */
	public void resetOvertime() {
		this.overtime = System.currentTimeMillis() + OVERTIME_OFFSET;
		this.reorder = true;
	}

	/**
	 * 删除一个角色ID
	 * 
	 * @param roleId
	 *            被删除的角色ID
	 * @return true删除成功，false删除失败
	 */
	public boolean deleteRole(int belongServer, int roleId) {
		List<CacheRole> roles = serverRoles.get(belongServer);
		roleMap.remove(roleId);
		if (roles != null) {
			int crSize = roles.size();
			for (int cri = 0; cri < crSize; cri++) {
				if (roles.get(cri).getRoleId() == roleId) {
					roles.remove(cri);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 返回账号下的角色ID列表
	 * 
	 * @return 角色ID列表
	 */
	public final List<CacheRole> getRoles(int belongServer) {
		List<CacheRole> roles = serverRoles.get(belongServer);
		if (roles == null) {
			roles = new ArrayList<CacheRole>();
			serverRoles.put(belongServer, roles);
		}
		return roles;
	}

	protected void clearRoles() {
		this.serverRoles.clear();
		this.roleMap.clear();
	}

	public Collection<CacheRole> getAllRoles() {
		return roleMap.values();
	}

	/**
	 * 是否还可以创建新角色
	 * 
	 * @return true可以创建
	 */
	public final boolean isFull(int belongServer) {
		List<CacheRole> roles = serverRoles.get(belongServer);
		if (roles == null) {
			return false;
		}
		return roles.size() > 2;
	}

	/**
	 * 加入一个新角色
	 * 
	 * @param roleId
	 */
	public final int addRole(CacheRole cacheRole) {
		List<CacheRole> roles = serverRoles.get(cacheRole.getBelongServer());
		if (roles == null) {
			roles = new ArrayList<CacheRole>();
			serverRoles.put(cacheRole.getBelongServer(), roles);
		}
		roleMap.put(cacheRole.getRoleId(), cacheRole);
		int size = roles.size();
		if (size > 0) {
			for (int ri = 0; ri < size; ri++) {
				if (roles.get(ri).getRoleId() == cacheRole.getRoleId()) {
					return roles.size();
				}
			}
		}
		roles.add(cacheRole);
		return roles.size();
	}

	/**
	 * 获取缓存角色
	 * 
	 * @param roleId
	 *            角色编号
	 * @return 缓存角色
	 */
	public CacheRole getCacheRole(int roleId) {
		return roleMap.get(roleId);
	}

	public boolean isReorder() {
		return reorder;
	}

	public void setReorder(boolean reorder) {
		this.reorder = reorder;
	}

	public boolean isCleared() {
		return cleared;
	}

	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}

	@Override
	public String toString() {
		return "CacheAccountRole [overtime=" + DateUtil.formatDatetime(new Date(overtime)) + ", accountId=" + accountId + ", reorder=" + reorder
		        + ", serverRoles = " + serverRoles + "]";
	}
}
