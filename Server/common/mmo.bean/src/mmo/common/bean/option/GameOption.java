package mmo.common.bean.option;

import mmo.common.bean.role.Role;

/**
 * 游戏配置
 * 
 * @author hp
 * 
 */
public class GameOption {
	/** 键 */
	protected short key;
	/** 值 */
	protected int   value;

	public short getKey() {
		return key;
	}

	public void changeValue(boolean value) {
		if (value) {
			this.value = 1;
		} else {
			this.value = 0;
		}
	}

	public boolean getValueBoolean() {
		return this.value > 0;
	}

	public void setKey(short key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void check(Role role) {

	}

	public int getId() {
		return 0;
	}

	public void setId(int id) {
	}

	public boolean isAutoBuy() {
		return false;
	}

	public void setAutoBuy(boolean autoBuy) {
	}

	public boolean isAutoAdd() {
		return false;
	}

	public void setAutoAdd(boolean autoAdd) {
	}
}
