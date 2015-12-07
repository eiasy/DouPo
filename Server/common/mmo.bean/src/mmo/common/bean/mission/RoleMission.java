package mmo.common.bean.mission;

import java.util.HashMap;
import java.util.Map;

import mmo.common.system.GameSystem;

/**
 * 角色完成任务的记录
 * 
 * @author 李天喜
 * 
 */
public class RoleMission {
	private int                   roleId;
	private int                   dayOfYear = 0;
	private Map<Integer, Integer> counter   = new HashMap<Integer, Integer>();

	protected RoleMission() {
		this.dayOfYear = GameSystem.getInstance().getDayOfYear();
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * 提交一个日常任务
	 * 
	 * @param missionId
	 *            任务编号
	 * @return 已经完成的次数
	 */
	public final int commitMission(int missionId) {
		Integer count = counter.get(missionId);
		if (count == null) {
			count = 1;
		} else {
			count += 1;
		}
		counter.put(missionId, count);
		return count;
	}

	public final int getCount(int mission) {
		Integer count = counter.get(mission);
		if (count == null) {
			return 0;
		}
		return count;
	}

	public void validate() {
		if (this.dayOfYear != GameSystem.getInstance().getDayOfYear()) {
			clearLogs();
			this.dayOfYear = GameSystem.getInstance().getDayOfYear();
		}
	}

	public void clearLogs() {
		counter.clear();
	}
}
