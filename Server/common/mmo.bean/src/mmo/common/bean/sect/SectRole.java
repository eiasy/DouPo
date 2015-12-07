package mmo.common.bean.sect;

/**
 * 宗门成员
 * 
 * @author 李天喜
 * 
 */
public class SectRole {
	/** 宗门职位 */
	private byte   sectPost;
	/** 角色ID */
	private int    roleId;
	/** 角色名称 */
	private String name;
	/** 角色职业 */
	private byte  profession;
	/** 角色性别 */
	private byte   sex;
	/** 贡献点 */
	private int    contribute;
	/** 日期-申请-加入 */
	private long   atime;

	public SectRole() {

	}

	public byte getSectPost() {
		return sectPost;
	}

	public void setSectPost(byte sectPost) {
		this.sectPost = sectPost;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getAtime() {
		return atime;
	}

	public int getContribute() {
		return contribute;
	}

	public void setContribute(int contribute) {
		this.contribute = contribute;
	}

	public void setAtime(long atime) {
		this.atime = atime;
	}

	@Override
	public String toString() {
		return "SectRole [sectPost=" + sectPost + ", roleId=" + roleId + ", name=" + name + ", profession=" + profession + ", sex=" + sex
		        + ", contribute=" + contribute + ", atime=" + atime + "]";
	}
}
