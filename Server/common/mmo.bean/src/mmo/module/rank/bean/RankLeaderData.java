package mmo.module.rank.bean;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.module.cache.role.CacheRole;

/**
 * 世界首领积分排行
 * 
 * @author 肖琼
 * 
 */
public class RankLeaderData extends RankData {
	public RankLeaderData() {

	}

	public RankLeaderData(CacheRole role) {
		super(role);
		this.rankValue = role.getLeaderIntegral();
	}

	public void reset() {
		super.reset();
		Role roleOnLine = RoleManager.getOLUserRole(roleId);
		if (roleOnLine != null) {
			setRankValue(roleOnLine.getLeaderIntegral());// 获取世界首领积分
		}
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getLeaderIntegral();
	}
}
