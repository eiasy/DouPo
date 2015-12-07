package mmo.common.bean.monster;

import mmo.tools.util.string.StringSplit;

public class MonsterModel {
	private int    realId       = 0;   // 怪物真实ID
	private String name         = null; // 名称
	private String animation    = null; // 动画文件
	private String icon         = null; // 图标
	private int    attackOffset = 0;   // 攻击间隔
	private byte   star         = 0;   // 星级
	private int    zoomValue    = 0;   // 缩放比例
	private int    baseHp       = 0;   // 初始生命
	private int    baseAtk      = 0;   // 初始攻击
	private int    baseDef      = 0;   // 初始防御
	private float  growHp       = 0;   // 生命成长
	private float  growAtk      = 0;   // 攻击成长
	private float  growDef      = 0;   // 防御成长
	private String dialogScript = null; // 对话脚本
	private int    petModelId   = 0;   // 对应的宠物模型ID
	/** 宠物类型 */
	private byte   petType      = 0;

	public byte getPetType() {
		return petType;
	}

	public void setPetType(byte petType) {
		this.petType = petType;
	}

	public int getRealId() {
		return realId;
	}

	public void setRealId(int realId) {
		this.realId = realId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnimation() {
		return animation;
	}

	public void setAnimation(String animation) {
		this.animation = animation;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = StringSplit.transformString(icon);
	}

	public int getAttackOffset() {
		return attackOffset;
	}

	public void setAttackOffset(int attackOffset) {
		this.attackOffset = attackOffset;
	}

	public byte getStar() {
		return star;
	}

	public void setStar(byte star) {
		this.star = star;
	}

	public int getZoomValue() {
		return zoomValue;
	}

	public void setZoomValue(int zoomValue) {
		this.zoomValue = zoomValue;
	}

	public int getBaseHp() {
		return baseHp;
	}

	public void setBaseHp(int baseHp) {
		this.baseHp = baseHp;
	}

	public int getBaseAtk() {
		return baseAtk;
	}

	public void setBaseAtk(int baseAtk) {
		this.baseAtk = baseAtk;
	}

	public int getBaseDef() {
		return baseDef;
	}

	public void setBaseDef(int baseDef) {
		this.baseDef = baseDef;
	}

	public float getGrowHp() {
		return growHp;
	}

	public void setGrowHp(float growHp) {
		this.growHp = growHp;
	}

	public float getGrowAtk() {
		return growAtk;
	}

	public void setGrowAtk(float growAtk) {
		this.growAtk = growAtk;
	}

	public float getGrowDef() {
		return growDef;
	}

	public void setGrowDef(float growDef) {
		this.growDef = growDef;
	}

	public String getDialogScript() {
		return dialogScript;
	}

	public void setDialogScript(String dialogScript) {
		this.dialogScript = dialogScript;
	}

	public int getPetModelId() {
		return petModelId;
	}

	public void setPetModelId(int petModelId) {
		this.petModelId = petModelId;
	}

}
