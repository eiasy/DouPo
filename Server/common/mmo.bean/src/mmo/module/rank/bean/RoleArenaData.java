package mmo.module.rank.bean;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.common.bean.role.RoleMini;
import mmo.module.cache.role.CacheRole;
import mmo.tools.util.StringUtil;

public class RoleArenaData extends RankData {
	private boolean challenged;

	public RoleArenaData() {

	}

	public RoleArenaData(CacheRole role) {
		super(role);
		RoleMini rm = RoleManager.getRoleMini(role.getRoleId());
		if (rm != null) {
			int sumFight = rm.getFightPower();
			String petInfo = rm.getPetInfo();
			String[] array1 = StringUtil.splitString(petInfo, ';');
			if (array1.length > 0) {
				int length = array1.length;
				for (int pi = 0; pi < length; pi++) {
					String[] array2 = StringUtil.splitString(array1[pi], ',');
					if (array2.length > 7) {
						sumFight += Integer.parseInt(array2[7]);
					}
				}
			}

			this.rankValue = sumFight;
		}
	}

	public void reset() {
		super.reset();
		RoleMini rm = RoleManager.getRoleMini(getRoleId());
		if (rm != null) {
			int sumFight = rm.getFightPower();
			String petInfo = rm.getPetInfo();
			String[] array1 = StringUtil.splitString(petInfo, ';');
			if (array1.length > 0) {
				int length = array1.length;
				for (int pi = 0; pi < length; pi++) {
					String[] array2 = StringUtil.splitString(array1[pi], ',');
					if (array2.length > 7) {
						sumFight += Integer.parseInt(array2[7]);
					}
				}
			}

			this.rankValue = sumFight;
		}
	}

	public int getCompareResult(Role role) {
		return this.rankValue - role.getFighting();
	}

	public boolean isChallenged() {
		return challenged;
	}

	public void setChallenged(boolean challenged) {
		this.challenged = challenged;
	}
}
