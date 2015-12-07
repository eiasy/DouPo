package mmo.common.bean.vip;

import mmo.tools.util.string.StringSplit;

/**
 * VIP等级消耗及特权描述
 * 
 * @author fanren
 * 
 */
public class VipLevel {
	/** 序号 */
	private short  index;
	/** 等级 */
	private short  level;
	/** 累积充值的人民币数量 */
	private int    money;
	/** 宝箱编号 */
	private int    box;
	/** 特权描述 */
	private String note;
	/**与NPC绑定后的物品ID*/
	private int    bindId;

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public short getIndex() {
		return index;
	}

	public void setIndex(short index) {
		this.index = index;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getBox() {
		return box;
	}

	public void setBox(int box) {
		this.box = box;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = StringSplit.transformString(note);
	}

	public int getBindId() {
    	return bindId;
    }

	public void setBindId(int bindId) {
    	this.bindId = bindId;
    }

	@Override
	public String toString() {
		return "VipLevel [level=" + level + ", money=" + money + ", box=" + box + ", note=" + note + "]";
	}
}
