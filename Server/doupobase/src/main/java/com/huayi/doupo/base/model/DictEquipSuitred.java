package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class DictEquipSuitred implements Serializable
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
		套装名字
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
		装备套装列表
	*/
	private String suitEquipIdList;
	public String getSuitEquipIdList(){
		return suitEquipIdList;
	}
	public void setSuitEquipIdList(String suitEquipIdList) {
		this.suitEquipIdList = suitEquipIdList;
		index = 3;
		result += index + "*String*" + suitEquipIdList + "#";
	}

	public void setSuitEquipIdList(String suitEquipIdList, int bs) {
		this.suitEquipIdList = suitEquipIdList;
	}

	/**
		红色0星套装属性 fightPropId_value;(大于1代表数值，小于1代表百分比)
	*/
	private String suit0StarProp;
	public String getSuit0StarProp(){
		return suit0StarProp;
	}
	public void setSuit0StarProp(String suit0StarProp) {
		this.suit0StarProp = suit0StarProp;
		index = 4;
		result += index + "*String*" + suit0StarProp + "#";
	}

	public void setSuit0StarProp(String suit0StarProp, int bs) {
		this.suit0StarProp = suit0StarProp;
	}

	/**
		1星套装属性 fightPropId_value;(大于1代表数值，小于1代表百分比)
	*/
	private String suit1StarProp;
	public String getSuit1StarProp(){
		return suit1StarProp;
	}
	public void setSuit1StarProp(String suit1StarProp) {
		this.suit1StarProp = suit1StarProp;
		index = 5;
		result += index + "*String*" + suit1StarProp + "#";
	}

	public void setSuit1StarProp(String suit1StarProp, int bs) {
		this.suit1StarProp = suit1StarProp;
	}

	/**
		2星套装属性 fightPropId_value;(大于1代表数值，小于1代表百分比)
	*/
	private String suit2StarProp;
	public String getSuit2StarProp(){
		return suit2StarProp;
	}
	public void setSuit2StarProp(String suit2StarProp) {
		this.suit2StarProp = suit2StarProp;
		index = 6;
		result += index + "*String*" + suit2StarProp + "#";
	}

	public void setSuit2StarProp(String suit2StarProp, int bs) {
		this.suit2StarProp = suit2StarProp;
	}

	/**
		3星套装属性 fightPropId_value;(大于1代表数值，小于1代表百分比)
	*/
	private String suit3StarProp;
	public String getSuit3StarProp(){
		return suit3StarProp;
	}
	public void setSuit3StarProp(String suit3StarProp) {
		this.suit3StarProp = suit3StarProp;
		index = 7;
		result += index + "*String*" + suit3StarProp + "#";
	}

	public void setSuit3StarProp(String suit3StarProp, int bs) {
		this.suit3StarProp = suit3StarProp;
	}

	/**
		4星套装属性 fightPropId_value;(大于1代表数值，小于1代表百分比)
	*/
	private String suit4StarProp;
	public String getSuit4StarProp(){
		return suit4StarProp;
	}
	public void setSuit4StarProp(String suit4StarProp) {
		this.suit4StarProp = suit4StarProp;
		index = 8;
		result += index + "*String*" + suit4StarProp + "#";
	}

	public void setSuit4StarProp(String suit4StarProp, int bs) {
		this.suit4StarProp = suit4StarProp;
	}

	/**
		5星套装属性 fightPropId_value;(大于1代表数值，小于1代表百分比)
	*/
	private String suit5StarProp;
	public String getSuit5StarProp(){
		return suit5StarProp;
	}
	public void setSuit5StarProp(String suit5StarProp) {
		this.suit5StarProp = suit5StarProp;
		index = 9;
		result += index + "*String*" + suit5StarProp + "#";
	}

	public void setSuit5StarProp(String suit5StarProp, int bs) {
		this.suit5StarProp = suit5StarProp;
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
		index = 10;
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
		index = 11;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictEquipSuitred clone(){
		DictEquipSuitred extend=new DictEquipSuitred();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSuitEquipIdList(this.suitEquipIdList);
		extend.setSuit0StarProp(this.suit0StarProp);
		extend.setSuit1StarProp(this.suit1StarProp);
		extend.setSuit2StarProp(this.suit2StarProp);
		extend.setSuit3StarProp(this.suit3StarProp);
		extend.setSuit4StarProp(this.suit4StarProp);
		extend.setSuit5StarProp(this.suit5StarProp);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
