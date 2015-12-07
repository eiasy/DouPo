package mmo.common.bean.extension;

import mmo.common.bean.role.Role;

public class SkillSet {
	/** 编号绑定 */
	public static final byte     FLAG_SKILL      = 0;
	/** 等级或学习设定 */
	public static final byte     FLAG_LEVEL      = 1;

	/** 达到条件后由系统负责开启（即玩家可见，学习后方可使用） */
	public static final byte     OPEN_SYSTEM     = 0;
	/** 角色主动去开启（开启方式由物品、任务奖励等等） */
	public static final byte     OPEN_INITIATIVE = 1;
	public static final String[] openNotes       = new String[] { "被动--系统", "主动" };

	/** 标识 */
	private byte                 flag;
	/** 编号 */
	private int                  id;
	/** 技能等级 */
	private short                level;
	/** 开启等级 */
	private short                lv;
	/** 学习消耗 */
	private int                  money;
	/** 开启条件 */
	private byte                 open;
	/** 设置描述 */
	public String                note;
	/** 根据等级限制排序后的索引 */
	private byte                 levelIndex;

	public SkillSet() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getLevelIndex() {
		return levelIndex;
	}

	public void setLevelIndex(byte levelIndex) {
		this.levelIndex = levelIndex;
	}

	public byte getOpen() {
		return open;
	}

	public void setOpen(byte open) {
		this.open = open;
	}

	public String getOpenNote() {
		if (open > openNotes.length || open < 0) {
			return "NULL";
		}
		return openNotes[open];
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = (byte) flag;
	}

	public short getLv() {
		return lv;
	}

	public void setLv(short lv) {
		this.lv = lv;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public String getTag() {
		StringBuilder sb = new StringBuilder();
		sb.append("<set");
		if (flag > 0) {
			sb.append(" flag=\"").append(flag).append("\"");
		}
		if (id > 0) {
			sb.append(" id=\"").append(id).append("\"");
		}
		if (level > 0) {
			sb.append(" level=\"").append(level).append("\"");
		}
		if (lv > 0) {
			sb.append(" lv=\"").append(lv).append("\"");
		}
		if (money > 0) {
			sb.append(" money=\"").append(money).append("\"");
		}
		if (open > 0) {
			sb.append(" open=\"").append(open).append("\"");
		}
		sb.append("/>");
		return sb.toString();
	}

	public boolean validateActive(Role role) {
		if (open == OPEN_SYSTEM) {
			return role.getLevel() >= lv;
		}
		return false;
	}

	public boolean validateLevel(Role role) {
		return role.getLevel() >= lv;
	}

	public String getNote() {
		if (note == null) {
			return "";
		}
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "SkillSet [flag=" + flag + ", id=" + id + ", level=" + level + ", lv=" + lv + ", money=" + money + "]";
	}
}
