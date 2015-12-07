package mmo.common.bean.extension;

import mmo.common.bean.role.Role;

import org.apache.mina.core.buffer.IoBuffer;

public class Function implements Cloneable {
	protected byte    type;               // 功能类型
	protected short   key;                // 属性键
	protected int     baseValue = 0;      // 基础属性值
	protected int     value     = 0;      // 属性值
	protected boolean percent   = false;  // 数值形式是否是百分比
	protected int     offset    = 0;      // 浮动值
	protected int     count;              // 镶嵌数量
	protected byte    state;              // 状态（0未开启，1开启，2破损）
	protected boolean forever   = false;  // 永久提升角色的一个属性值
	/** 内部索引 **/
	protected short   index;
	/** 法宝品阶 */
	protected short   treasurePropQuality; // 宝石属性中用作类别1
	/** 属性类别（0.普通 1.永久 2.五行） */
	protected short   propType;           // 宝石属性中用作类别2

	public Function clone() {
		Function o = null;
		try {
			o = (Function) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * 验证角色是否满足条件
	 * 
	 * @param role
	 *            角色
	 * @return true验证通过，false验证失败
	 */
	public boolean validate(Role role) {
		return true;
	}

	public short getIndex() {
		return index;
	}

	public void setIndex(short index) {
		this.index = index;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Function() {

	}

	public String getTag() {
		return "";
	}

	public short getKey() {
		return key;
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

	public boolean isPercent() {
		return percent;
	}

	public void setPercent(boolean percent) {
		this.percent = percent;
	}

	public boolean isForever() {
		return forever;
	}

	public void setForever(boolean forever) {
		this.forever = forever;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public void takeEffect(Role role, short goodsPoint) {

	}

	public void takeEffect(Role role, short goodsPoint, short cate) {

	}

	public void init(Role owner, byte quality, Class clazz) {

	}

	public String getDetail() {
		return "";
	}

	public void setState(byte state) {
		this.state = state;
	}

	public byte getState() {
		return state;
	}

	public int getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(int baseValue) {
		this.baseValue = baseValue;
	}

	public void packetData(IoBuffer buf) {

	}

	@Override
	public String toString() {
		return "Function [type=" + type + ", key=" + key + ", value=" + value + ", percent=" + percent + ", index=" + index + ", count=" + count
		        + ", state=" + state + ", forever=" + forever + "]";
	}

	public boolean update(Role role) {
		return false;
	}

	public short getTreasurePropQuality() {
		return treasurePropQuality;
	}

	public void setTreasurePropQuality(short treasurePropQuality) {
		this.treasurePropQuality = treasurePropQuality;
	}

	/**
	 * 属性类别（0.普通 1.永久 2.五行）
	 * 
	 * @return
	 */
	public short getPropType() {
		return propType;
	}

	/**
	 * 属性类别（0.普通 1.永久 2.五行）
	 * 
	 * @param propType
	 */
	public void setPropType(short propType) {
		this.propType = propType;
	}

	public short getGridX() {
		return 0;
	}

	public short getGridY() {
		return 0;
	}

	public String getTreasurePropName() {
		return "";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
