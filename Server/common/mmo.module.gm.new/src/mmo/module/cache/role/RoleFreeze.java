package mmo.module.cache.role;

public class RoleFreeze {
	private int roleId;
	private int time;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "RoleFreeze [roleId=" + roleId + ", time=" + time + "]";
	}
}
