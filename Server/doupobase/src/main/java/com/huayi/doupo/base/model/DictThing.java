package com.huayi.doupo.base.model;

import java.io.*;

/**
	物品字典表
*/
@SuppressWarnings("serial")
public class DictThing implements Serializable
{
	private int index;
	public String result = "";
	/**
		编号
	*/
	private int id;
	public int getId(){
		return id;
	}
	public void setId(int id) {
		this.id = id;
		index = 1;
		result += index + "*int*" + id + "#";
	}

	public void setId(int id, int bs) {
		this.id = id;
	}

	/**
		名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 2;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		等级
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 3;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		小图标Id
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 4;
		result += index + "*int*" + smallUiId + "#";
	}

	public void setSmallUiId(int smallUiId, int bs) {
		this.smallUiId = smallUiId;
	}

	/**
		大图标Id
	*/
	private int bigUiId;
	public int getBigUiId(){
		return bigUiId;
	}
	public void setBigUiId(int bigUiId) {
		this.bigUiId = bigUiId;
		index = 5;
		result += index + "*int*" + bigUiId + "#";
	}

	public void setBigUiId(int bigUiId, int bs) {
		this.bigUiId = bigUiId;
	}

	/**
		
	*/
	private int oldBuyGold;
	public int getOldBuyGold(){
		return oldBuyGold;
	}
	public void setOldBuyGold(int oldBuyGold) {
		this.oldBuyGold = oldBuyGold;
		index = 6;
		result += index + "*int*" + oldBuyGold + "#";
	}

	public void setOldBuyGold(int oldBuyGold, int bs) {
		this.oldBuyGold = oldBuyGold;
	}

	/**
		元宝购买价格
	*/
	private int buyGold;
	public int getBuyGold(){
		return buyGold;
	}
	public void setBuyGold(int buyGold) {
		this.buyGold = buyGold;
		index = 7;
		result += index + "*int*" + buyGold + "#";
	}

	public void setBuyGold(int buyGold, int bs) {
		this.buyGold = buyGold;
	}

	/**
		铜钱购买价格
	*/
	private int buyCopper;
	public int getBuyCopper(){
		return buyCopper;
	}
	public void setBuyCopper(int buyCopper) {
		this.buyCopper = buyCopper;
		index = 8;
		result += index + "*int*" + buyCopper + "#";
	}

	public void setBuyCopper(int buyCopper, int bs) {
		this.buyCopper = buyCopper;
	}

	/**
		铜钱出售价格
	*/
	private int sellCopper;
	public int getSellCopper(){
		return sellCopper;
	}
	public void setSellCopper(int sellCopper) {
		this.sellCopper = sellCopper;
		index = 9;
		result += index + "*int*" + sellCopper + "#";
	}

	public void setSellCopper(int sellCopper, int bs) {
		this.sellCopper = sellCopper;
	}

	/**
		物品类型Id  vip礼包时要填vipGift
	*/
	private int thingTypeId;
	public int getThingTypeId(){
		return thingTypeId;
	}
	public void setThingTypeId(int thingTypeId) {
		this.thingTypeId = thingTypeId;
		index = 10;
		result += index + "*int*" + thingTypeId + "#";
	}

	public void setThingTypeId(int thingTypeId, int bs) {
		this.thingTypeId = thingTypeId;
	}

	/**
		背包类型-vip礼包时要填道具
	*/
	private int bagTypeId;
	public int getBagTypeId(){
		return bagTypeId;
	}
	public void setBagTypeId(int bagTypeId) {
		this.bagTypeId = bagTypeId;
		index = 11;
		result += index + "*int*" + bagTypeId + "#";
	}

	public void setBagTypeId(int bagTypeId, int bs) {
		this.bagTypeId = bagTypeId;
	}

	/**
		战斗属性Id
	*/
	private int fightPropId;
	public int getFightPropId(){
		return fightPropId;
	}
	public void setFightPropId(int fightPropId) {
		this.fightPropId = fightPropId;
		index = 12;
		result += index + "*int*" + fightPropId + "#";
	}

	public void setFightPropId(int fightPropId, int bs) {
		this.fightPropId = fightPropId;
	}

	/**
		战斗属性值
	*/
	private int fightPropValue;
	public int getFightPropValue(){
		return fightPropValue;
	}
	public void setFightPropValue(int fightPropValue) {
		this.fightPropValue = fightPropValue;
		index = 13;
		result += index + "*int*" + fightPropValue + "#";
	}

	public void setFightPropValue(int fightPropValue, int bs) {
		this.fightPropValue = fightPropValue;
	}

	/**
		数量
	*/
	private int value;
	public int getValue(){
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		index = 14;
		result += index + "*int*" + value + "#";
	}

	public void setValue(int value, int bs) {
		this.value = value;
	}

	/**
		装备字典Id
	*/
	private int equipmentId;
	public int getEquipmentId(){
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
		index = 15;
		result += index + "*int*" + equipmentId + "#";
	}

	public void setEquipmentId(int equipmentId, int bs) {
		this.equipmentId = equipmentId;
	}

	/**
		产出副本
	*/
	private String outBarrier;
	public String getOutBarrier(){
		return outBarrier;
	}
	public void setOutBarrier(String outBarrier) {
		this.outBarrier = outBarrier;
		index = 16;
		result += index + "*String*" + outBarrier + "#";
	}

	public void setOutBarrier(String outBarrier, int bs) {
		this.outBarrier = outBarrier;
	}

	/**
		是否可以购买 0-不可以 1-可以
	*/
	private int isCanBuy;
	public int getIsCanBuy(){
		return isCanBuy;
	}
	public void setIsCanBuy(int isCanBuy) {
		this.isCanBuy = isCanBuy;
		index = 17;
		result += index + "*int*" + isCanBuy + "#";
	}

	public void setIsCanBuy(int isCanBuy, int bs) {
		this.isCanBuy = isCanBuy;
	}

	/**
		是否可以使用 0-不可以 1-可以
	*/
	private int isUse;
	public int getIsUse(){
		return isUse;
	}
	public void setIsUse(int isUse) {
		this.isUse = isUse;
		index = 18;
		result += index + "*int*" + isUse + "#";
	}

	public void setIsUse(int isUse, int bs) {
		this.isUse = isUse;
	}

	/**
		商店购买随次数而增加的金币数
	*/
	private int storeBuyGoldGrow;
	public int getStoreBuyGoldGrow(){
		return storeBuyGoldGrow;
	}
	public void setStoreBuyGoldGrow(int storeBuyGoldGrow) {
		this.storeBuyGoldGrow = storeBuyGoldGrow;
		index = 19;
		result += index + "*int*" + storeBuyGoldGrow + "#";
	}

	public void setStoreBuyGoldGrow(int storeBuyGoldGrow, int bs) {
		this.storeBuyGoldGrow = storeBuyGoldGrow;
	}

	/**
		魔核转换所需金币数
	*/
	private int coreConvCopper;
	public int getCoreConvCopper(){
		return coreConvCopper;
	}
	public void setCoreConvCopper(int coreConvCopper) {
		this.coreConvCopper = coreConvCopper;
		index = 20;
		result += index + "*int*" + coreConvCopper + "#";
	}

	public void setCoreConvCopper(int coreConvCopper, int bs) {
		this.coreConvCopper = coreConvCopper;
	}

	/**
		排序
	*/
	private int indexOrder;
	public int getIndexOrder(){
		return indexOrder;
	}
	public void setIndexOrder(int indexOrder) {
		this.indexOrder = indexOrder;
		index = 21;
		result += index + "*int*" + indexOrder + "#";
	}

	public void setIndexOrder(int indexOrder, int bs) {
		this.indexOrder = indexOrder;
	}

	/**
		如vip礼包 里面有哪些东西 tableType_tableField_value;
	*/
	private String childThings;
	public String getChildThings(){
		return childThings;
	}
	public void setChildThings(String childThings) {
		this.childThings = childThings;
		index = 22;
		result += index + "*String*" + childThings + "#";
	}

	public void setChildThings(String childThings, int bs) {
		this.childThings = childThings;
	}

	/**
		静态字段
	*/
	private String sname;
	public String getSname(){
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
		index = 23;
		result += index + "*String*" + sname + "#";
	}

	public void setSname(String sname, int bs) {
		this.sname = sname;
	}

	/**
		背景色
	*/
	private int bkGround;
	public int getBkGround(){
		return bkGround;
	}
	public void setBkGround(int bkGround) {
		this.bkGround = bkGround;
		index = 24;
		result += index + "*int*" + bkGround + "#";
	}

	public void setBkGround(int bkGround, int bs) {
		this.bkGround = bkGround;
	}

	/**
		描述
	*/
	private String description;
	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		index = 25;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 26;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictThing clone(){
		DictThing extend=new DictThing();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setLevel(this.level);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setOldBuyGold(this.oldBuyGold);
		extend.setBuyGold(this.buyGold);
		extend.setBuyCopper(this.buyCopper);
		extend.setSellCopper(this.sellCopper);
		extend.setThingTypeId(this.thingTypeId);
		extend.setBagTypeId(this.bagTypeId);
		extend.setFightPropId(this.fightPropId);
		extend.setFightPropValue(this.fightPropValue);
		extend.setValue(this.value);
		extend.setEquipmentId(this.equipmentId);
		extend.setOutBarrier(this.outBarrier);
		extend.setIsCanBuy(this.isCanBuy);
		extend.setIsUse(this.isUse);
		extend.setStoreBuyGoldGrow(this.storeBuyGoldGrow);
		extend.setCoreConvCopper(this.coreConvCopper);
		extend.setIndexOrder(this.indexOrder);
		extend.setChildThings(this.childThings);
		extend.setSname(this.sname);
		extend.setBkGround(this.bkGround);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
