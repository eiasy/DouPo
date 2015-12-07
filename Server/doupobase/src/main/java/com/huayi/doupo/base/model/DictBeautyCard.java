package com.huayi.doupo.base.model;

import java.io.*;

/**
	美人字典表
*/
@SuppressWarnings("serial")
public class DictBeautyCard implements Serializable
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
		卡牌Id
	*/
	private int cardId;
	public int getCardId(){
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
		index = 2;
		result += index + "*int*" + cardId + "#";
	}

	public void setCardId(int cardId, int bs) {
		this.cardId = cardId;
	}

	/**
		解锁条件
	*/
	private int unblock;
	public int getUnblock(){
		return unblock;
	}
	public void setUnblock(int unblock) {
		this.unblock = unblock;
		index = 3;
		result += index + "*int*" + unblock + "#";
	}

	public void setUnblock(int unblock, int bs) {
		this.unblock = unblock;
	}

	/**
		开启条件 tableTypeId_tableFieldId;
	*/
	private String conditions;
	public String getConditions(){
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
		index = 4;
		result += index + "*String*" + conditions + "#";
	}

	public void setConditions(String conditions, int bs) {
		this.conditions = conditions;
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
		index = 5;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		
	*/
	private String sound;
	public String getSound(){
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
		index = 6;
		result += index + "*String*" + sound + "#";
	}

	public void setSound(String sound, int bs) {
		this.sound = sound;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 7;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictBeautyCard clone(){
		DictBeautyCard extend=new DictBeautyCard();
		extend.setId(this.id);
		extend.setCardId(this.cardId);
		extend.setUnblock(this.unblock);
		extend.setConditions(this.conditions);
		extend.setDescription(this.description);
		extend.setSound(this.sound);
		extend.setVersion(this.version);
		return extend;
	}
}
