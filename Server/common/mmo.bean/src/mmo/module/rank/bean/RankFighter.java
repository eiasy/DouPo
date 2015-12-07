package mmo.module.rank.bean;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.common.bean.role.RoleMini;
import mmo.module.cache.role.CacheRole;

public class RankFighter extends RankData {
	public RankFighter() {
	}

	public RankFighter(CacheRole role) {
		super(role);

		RoleMini rm = RoleManager.getRoleMini(role.getRoleId());
		if (rm != null) {
			if (rm.getTotalFight() > 0) {
				this.rankValue = rm.getTotalFight();
			}
		} else {
			if (role.getTotalFight() > 0) {
				this.rankValue = role.getTotalFight();
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("[RankFighter]");
		sbf.append(addSpaceForNum(roleName, 8)).append("  ");
		sbf.append(addSpaceForNum("" + level, 2)).append("  ");
		sbf.append(addSpaceForNum("" + getRankValue(), 6)).append("  ");
		sbf.append(addSpaceForNum("" + realm, 1)).append("  ");
		return sbf.toString();
	}

	public void reset() {
		super.reset();
		RoleMini rm = RoleManager.getRoleMini(getRoleId());
		if (rm != null) {
			if (rm.getTotalFight() > 0) {
				this.rankValue = rm.getTotalFight();
			}
		}
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getTotalFight();
	}
}
