package mmo.common.bean.role.confirm;

import mmo.common.bean.role.Role;

public abstract class AConfirmEvent {
	public static final byte OPTION_AGREE  = 1;
	public static final byte OPTION_CANCEL = 2;
	protected int            id;

	/**
	 * 事件编号
	 * 
	 * @return 事件编号
	 */
	public int getId() {
		return id;
	}

	/**
	 * 执行体
	 */
	public final void run(Role role, byte option) {
		switch (option) {
			case OPTION_AGREE: {
				agree(role);
				break;
			}
			case OPTION_CANCEL: {
				refuse(role);
				break;
			}
			default: {

			}
		}
		clear();

	}

	/**
	 * 执行体
	 */
	public final void run(Role role, boolean result) {
		if (result) {
			agree(role);
		} else {
			refuse(role);
		}
		clear();
	}

	public final void run(int roleId, boolean result) {
		if (result) {
			agree(roleId);
		} else {
			refuse(roleId);
		}
		clear();
	}

	public void run(int roleId, byte result) {
		switch (result) {
			case OPTION_AGREE: {
				agree(roleId);
				break;
			}
			case OPTION_CANCEL: {
				refuse(roleId);
				break;
			}
			default: {

			}
		}
		clear();
		clear();
	}

	public void refuse(Role role) {

	}

	public void agree(Role role) {

	}

	public void refuse(int roleId) {

	}

	public void agree(int roleId) {

	}

	public void clear() {

	}

	public void setId(int id) {
		this.id = id;
	}
}
