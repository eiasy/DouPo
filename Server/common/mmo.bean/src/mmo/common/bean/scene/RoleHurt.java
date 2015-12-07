package mmo.common.bean.scene;

public class RoleHurt {
	private int roleId;
	private int hurt;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getHurt() {
		return hurt;
	}

	public void setHurt(int hurt) {
		this.hurt = hurt;
	}

	public RoleHurt() {
	}

	public void addHurt(int hurt) {
		this.hurt += hurt;
	}

}
