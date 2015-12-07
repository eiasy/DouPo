package mmo.common.bean.role;

public class ChallengeLog {
	private int     roleId;
	private int     otherRoleId;
	private boolean challenge;
	private boolean success;
	private int     rankChange;
	private long    timeChallenge;

	public ChallengeLog() {
		super();
	}

	public ChallengeLog(int roleId, int otherRoleId, boolean challenge, boolean success, int rankChange, long timeChallenge) {
		super();
		this.roleId = roleId;
		this.otherRoleId = otherRoleId;
		this.challenge = challenge;
		this.success = success;
		this.rankChange = rankChange;
		this.timeChallenge = timeChallenge;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getOtherRoleId() {
		return otherRoleId;
	}

	public void setOtherRoleId(int otherRoleId) {
		this.otherRoleId = otherRoleId;
	}

	public boolean isChallenge() {
		return challenge;
	}

	public void setChallenge(boolean challenge) {
		this.challenge = challenge;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getRankChange() {
		return rankChange;
	}

	public void setRankChange(int rankChange) {
		this.rankChange = rankChange;
	}

	public long getTimeChallenge() {
		return timeChallenge;
	}

	public void setTimeChallenge(long timeChallenge) {
		this.timeChallenge = timeChallenge;
	}

	public String packetInfo() {
		StringBuilder sb = new StringBuilder();
		if (challenge) {
			sb.append("你对【");
			RoleMini rm = RoleManager.getRoleMini(otherRoleId);
			if (rm != null) {
				sb.append(rm.getRoleName());
			}
			sb.append("】发起了挑战");
		} else {
			sb.append("【");
			RoleMini rm = RoleManager.getRoleMini(otherRoleId);
			if (rm != null) {
				sb.append(rm.getRoleName());
			}
			sb.append("】对你发起了挑战");
		}
		if (success) {
			sb.append("【获胜了】,");
		} else {
			sb.append("【失败了】,");
		}
		if (rankChange == Integer.MAX_VALUE) {
			sb.append("你进入斗法排行榜");
		} else {
			if (rankChange > 0) {
				sb.append("你的排名提升").append(rankChange).append("位");
			} else if (rankChange == 0) {
				sb.append("你的排名不变");
			} else {
				sb.append("你的排名下降").append(-rankChange).append("位");
			}
		}
		return sb.toString();
	}

}
