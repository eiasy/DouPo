package mmo.common.bean.option;


/**
 * 战斗栏快捷键设置
 * 
 * @author fanren
 * 
 */
public class GameShortcut {

	public static final byte CATE_SKILL = 1;
	public static final byte CATE_GOODS = 2;
	/** 按键 */
	private int              key;
	/** 类型:1为技能，2为物品 */
	private byte             cate;
	/** 编号 */
	private int              value;

	public GameShortcut() {

	}

	public GameShortcut(int key, byte cate, int value) {
		this.key = key;
		this.cate = cate;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public byte getCate() {
		return cate;
	}

	public void setCate(byte cate) {
		this.cate = cate;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
