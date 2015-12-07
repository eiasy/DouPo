package mmo.module.rank.bean;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.common.bean.role.RoleMini;
import mmo.module.cache.role.CacheRole;

public class RankData extends RoleMini {
	/** 参与排行的数值 */
	protected int     rankValue;
	/** 宗门ID */
	private long      sectId;
	/** 升级时记录日期（毫秒） */
	private long      levelupFristTime;
	/** 上一次排名 */
	private int       preRankPosition  = -1;
	/** 当前排名 */
	private int       currRankPosition = -1;
	/** 全民挑战 */
	protected boolean allChallenge;

	public boolean isAllChallenge() {
		return allChallenge;
	}

	public void setAllChallenge(boolean allChallenge) {
		this.allChallenge = allChallenge;
	}

	public RankData(CacheRole role) {
		setRoleId(role.getRoleId()); // 角色ID
		setRoleName(role.getUsername()); // 角色名称
		setSectId(role.getSectId()); // 宗门ID
		setProfession(role.getProfession()); // 角色职业
		setLevelupFirstTime(role.getLevelupFirstTime());// 等级升级时间
		setAccountId(role.getAccountId());// 账号id
		setLevel(role.getLevel());
		setSex(role.getSex());
	}

	public RankData() {

	}

	public long getSectId() {
		return sectId;
	}

	public void setSectId(long sectId) {
		this.sectId = sectId;
	}

	public String addSpaceForNum(String str, int strLength) {
		int strLen = str.getBytes().length;
		if (strLen < strLength) {
			StringBuffer sb = new StringBuffer();
			for (; strLen < strLength; strLen++) {
				sb.append(" ");// 左补""
			}
			sb.append(str);
			str = sb.toString();
		}
		return str;
	}

	public String toString() {
		return "RankData [rankValue=" + rankValue + ", preRankPosition=" + preRankPosition + ", currRankPosition=" + currRankPosition + ", roleId="
		        + roleId + ", roleName=" + roleName + "]";
	}

	public long getLevelupFristTime() {
		return levelupFristTime;
	}

	public void setLevelupFirstTime(long levelupFristTime) {
		this.levelupFristTime = levelupFristTime;
	}

	public void reset() {
		Role roleOnLine = RoleManager.getOLUserRole(roleId);
		if (roleOnLine != null) {
			setSectId(roleOnLine.getSectId()); // 宗门ID
			setLevel(roleOnLine.getLevel());// 等级
			setRealm(roleOnLine.getRealm());// 境界值
			setLevelupFirstTime(roleOnLine.getLevelupFirstTime());// 等级升级时间
		}
	}

	public void resetFull() {
		reset();
		RoleMini roleOnLine = RoleManager.getRoleMini(roleId);
		if (roleOnLine != null) {
			setLevel(roleOnLine.getLevel());// 等级
			setRealm(roleOnLine.getRealm());// 境界值
			setRoleName(roleOnLine.getRoleName()); // 角色名称
			setProfession(roleOnLine.getProfession()); // 角色职业
			setAccountId(roleOnLine.getAccountId());// 账号id
		}
	}

	public void resetPosition(int newRankPosition) {
		this.preRankPosition = currRankPosition;
		this.currRankPosition = newRankPosition;
		if (preRankPosition < 0) {
			preRankPosition = currRankPosition;
		}
		reset();
	}

	public int getRankValue() {
		return rankValue;
	}

	public void setRankValue(int rankValue) {
		this.rankValue = rankValue;
	}

	public String getRankContent() {
		return "" + rankValue;
	}

	public int getCompareResult(RankData rankData) {
		return this.rankValue - rankData.getRankValue();
	}

	public int getCompareResult(Role role) {
		return 0;
	}

	public void setLevelupFristTime(long levelupFristTime) {
		this.levelupFristTime = levelupFristTime;
	}

	public final boolean isPositionChange() {
		return this.preRankPosition != currRankPosition;
	}

	public int getPreRankPosition() {
		return preRankPosition;
	}

	public void setPreRankPosition(int preRankPosition) {
		this.preRankPosition = preRankPosition;
	}

	public int getCurrRankPosition() {
		return currRankPosition;
	}

	public void setCurrRankPosition(int currRankPosition) {
		this.currRankPosition = currRankPosition;
	}

	public boolean isChallenged() {
		return false;
	}

	public void setChallenged(boolean challenged) {
	}
}
