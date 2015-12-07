package mmo.common.config.buttons;

public class FunButton {
	/** 按钮ID-int值的指定位 */
	private int    id;
	/** 开启标识 */
	private int    flag;
	/** 开启时需要达到的条件 */
	private int    value;
	/** 功能描述 */
	private String note;

	public FunButton() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
