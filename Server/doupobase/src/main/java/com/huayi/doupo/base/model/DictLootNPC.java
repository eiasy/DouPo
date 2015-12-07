package com.huayi.doupo.base.model;

import java.io.*;

/**
	抢夺NPC字典表
*/
@SuppressWarnings("serial")
public class DictLootNPC implements Serializable
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
		NPC战队名
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
		卡牌 卡牌Id多个分号隔开
	*/
	private String cards;
	public String getCards(){
		return cards;
	}
	public void setCards(String cards) {
		this.cards = cards;
		index = 3;
		result += index + "*String*" + cards + "#";
	}

	public void setCards(String cards, int bs) {
		this.cards = cards;
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
		index = 4;
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
		index = 5;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictLootNPC clone(){
		DictLootNPC extend=new DictLootNPC();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setCards(this.cards);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
