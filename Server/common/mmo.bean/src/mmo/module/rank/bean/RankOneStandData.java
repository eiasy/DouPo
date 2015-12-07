package mmo.module.rank.bean;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.module.cache.role.CacheRole;

/**
 * 一战到底积分排行
 * 
 * @author 肖琼
 * 
 */
public class RankOneStandData extends RankData {
	public RankOneStandData() {

	}

	public RankOneStandData(CacheRole role) {
		super(role);
		this.rankValue = role.getOneStandIntegral();
	}

	public void reset() {
		super.reset();
		Role roleOnLine = RoleManager.getOLUserRole(roleId);
		if (roleOnLine != null) {
			setRankValue(roleOnLine.getOneStandIntegral());// 获取一战到底的积分
		}
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getOneStandIntegral();
	}
}
