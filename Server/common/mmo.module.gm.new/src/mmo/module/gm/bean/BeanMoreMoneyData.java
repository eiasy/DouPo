package mmo.module.gm.bean;

import mmo.common.config.role.RoleProfession;

public class BeanMoreMoneyData {
	private int    roleId;    // 角色ID
	private String roleName;  // 角色名称
	private byte   profession; // 角色职业
	private int    costMoney;

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

	public int getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(int costMoney) {
		this.costMoney = costMoney;
	}

	public String[] toArray() {
		return new String[] { getRoleId() + "", getRoleName(), RoleProfession.getProfessionName(getProfession()), costMoney + "" };
	}
}
