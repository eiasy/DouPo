package mmo.module.gm.bean;

import mmo.common.config.role.RoleProfession;

public class BeanActivityRankData {
	private int     roleId;    // 角色ID
	private String  roleName;  // 角色名称
	private byte    profession; // 角色职业
	private int     position;
	private boolean awarded;

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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isAwarded() {
		return awarded;
	}

	public void setAwarded(boolean awarded) {
		this.awarded = awarded;
	}

	public String[] toArray() {
		return new String[] { getRoleId() + "", getRoleName(), RoleProfession.getProfessionName(getProfession()), position + "",
		        awarded ? "已领" : "未领", "正常" };
	}
}
