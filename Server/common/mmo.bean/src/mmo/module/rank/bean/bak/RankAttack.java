package mmo.module.rank.bean.bak;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.module.cache.role.CacheRole;
import mmo.module.rank.bean.RankData;

public class RankAttack extends RankData {
	protected RankAttack() {
	}

	public RankAttack(CacheRole role) {
		super(role);
		this.rankValue = role.getSumAttack();
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("[RankAttack]");
		sbf.append(addSpaceForNum(roleName, 8)).append("  ");
		sbf.append(addSpaceForNum("" + level, 2)).append("  ");
		sbf.append(addSpaceForNum("" + getRankValue(), 6)).append("  ");
		sbf.append(addSpaceForNum("" + realm, 1)).append("  ");
		return sbf.toString();
	}

	public void reset() {
		super.reset();
		Role roleOnLine = RoleManager.getOLUserRole(roleId);
		if (roleOnLine != null) {
			setRankValue(roleOnLine.getSumAttack());// 最大攻击力
		}
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getSumAttack();
	}
}
