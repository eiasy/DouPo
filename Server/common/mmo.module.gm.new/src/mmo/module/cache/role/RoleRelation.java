package mmo.module.cache.role;

public class RoleRelation {
	private int    roleId;
	private String roleName;
	private byte   profession;
	private byte   sex;
	private short  level;
	private byte   state;
	private byte   type;
	private long   time;

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

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RoleRelation [roleId=" + roleId + ", roleName=" + roleName + ", profession=" + profession + ", sex=" + sex + ", level=" + level
		        + ", state=" + state + ", time=" + time + "]";
	}
}
