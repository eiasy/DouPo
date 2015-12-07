package mmo.module.cache.role;

import mmo.module.gm.bean.TreeNode;

public class RoleMission extends TreeNode {

	private int    missionId;
	private String title;
	private short  category;
	private String categoryName;
	private String timeAccept;
	private short  state;

	public RoleMission(String name, TreeNode parent) {
		super(name, parent);
	}

	public int getMissionId() {
		return missionId;
	}

	public void setMissionId(int missionId) {
		this.missionId = missionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public short getCategory() {
		return category;
	}

	public void setCategory(short category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTimeAccept() {
		return timeAccept;
	}

	public void setTimeAccept(String timeAccept) {
		this.timeAccept = timeAccept;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	@Override
	public String getNodeName() {
		return "ID:" + missionId + ", 标题：" + title + ", 类型：" + categoryName + ", 接任务时间：" + timeAccept + ", 状态：" + (state == 4 ? "未完成" : "完成");
	}
}
