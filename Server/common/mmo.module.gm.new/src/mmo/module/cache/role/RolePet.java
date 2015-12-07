package mmo.module.cache.role;

import mmo.module.gm.bean.TreeNode;

public class RolePet extends TreeNode {

	private int    id;
	private int    realId;
	private String username;
	private short  level;
	private byte   quality;
	private byte   star;
	private byte   state;
	private int    hp;
	private int    experience;

	public RolePet(String name, TreeNode parent) {
		super(name, parent);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRealId() {
		return realId;
	}

	public void setRealId(int realId) {
		this.realId = realId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public byte getQuality() {
		return quality;
	}

	public void setQuality(byte quality) {
		this.quality = quality;
	}

	public byte getStar() {
		return star;
	}

	public void setStar(byte star) {
		this.star = star;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public String getNodeName() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-15s", "模型ID：" + id));
		sb.append(String.format("%-15s", "真实ID：" + realId));
		sb.append(String.format("%-10s", "名称：" + username));
		sb.append(String.format("%-10s", "等级：" + level));
		sb.append(String.format("%-10s", "经验：" + experience));
		sb.append(String.format("%-10s", "品阶：" + quality));
		sb.append(String.format("%-10s", "星级：" + star));
		sb.append(String.format("%-10s", "状态：" + (state == 0 ? "休息" : "出战")));
		sb.append(String.format("%-10s", "生命：" + hp));
		return sb.toString();
	}
}
