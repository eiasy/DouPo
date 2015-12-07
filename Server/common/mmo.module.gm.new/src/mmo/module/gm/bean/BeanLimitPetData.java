package mmo.module.gm.bean;

import java.util.Date;

import mmo.common.config.role.RoleProfession;
import mmo.tools.util.DateUtil;

public class BeanLimitPetData {
	private int    roleId;    // 角色ID
	private String roleName;  // 角色名称
	private byte   profession; // 角色职业
	private int    rank;      // 积分
	private int    freeCount; // 已经免费抽取的次数
	private int    position;  // 位置
	private int    extraCount; // 额外的元宝免费次数
	private long   freeTime;  // 免费抽取时间

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public byte getProfession() {
		return profession;
	}

	public void setProfession(byte profession) {
		this.profession = profession;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getExtraCount() {
		return extraCount;
	}

	public void setExtraCount(int extraCount) {
		this.extraCount = extraCount;
	}

	public long getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(long freeTime) {
		this.freeTime = freeTime;
	}

	public String[] toArray() {
		return new String[] { getRoleId() + "", getRoleName(), RoleProfession.getProfessionName(getProfession()), getRank() + "", getPosition() + "",
		        DateUtil.formatDate(new Date(getFreeTime())), getFreeCount() + "", getExtraCount() + "", "正常" };
	}
}
