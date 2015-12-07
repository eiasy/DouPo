package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家竞技场NPC实例表
*/
@SuppressWarnings("serial")
public class InstPlayerArenaNPC implements Serializable
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
		NPC名字
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
		卡牌列表
	*/
	private String cards;
	public String getCards(){
		return cards;
	}
	public void setCards(String cards) {
		this.cards = cards;
		index = 4;
		result += index + "*String*" + cards + "#";
	}

	public void setCards(String cards, int bs) {
		this.cards = cards;
	}

	/**
		卡牌阵型列表
	*/
	private String formations;
	public String getFormations(){
		return formations;
	}
	public void setFormations(String formations) {
		this.formations = formations;
		index = 5;
		result += index + "*String*" + formations + "#";
	}

	public void setFormations(String formations, int bs) {
		this.formations = formations;
	}

	/**
		卡牌阵容列表
	*/
	private String lineups;
	public String getLineups(){
		return lineups;
	}
	public void setLineups(String lineups) {
		this.lineups = lineups;
		index = 6;
		result += index + "*String*" + lineups + "#";
	}

	public void setLineups(String lineups, int bs) {
		this.lineups = lineups;
	}

	/**
		装备列表
	*/
	private String equips;
	public String getEquips(){
		return equips;
	}
	public void setEquips(String equips) {
		this.equips = equips;
		index = 7;
		result += index + "*String*" + equips + "#";
	}

	public void setEquips(String equips, int bs) {
		this.equips = equips;
	}

	/**
		功法法宝列表
	*/
	private String magics;
	public String getMagics(){
		return magics;
	}
	public void setMagics(String magics) {
		this.magics = magics;
		index = 8;
		result += index + "*String*" + magics + "#";
	}

	public void setMagics(String magics, int bs) {
		this.magics = magics;
	}

	/**
		版本号
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 9;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		添加时间
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 10;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
	}

	/**
		更新时间
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 11;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerArenaNPC clone(){
		InstPlayerArenaNPC extend=new InstPlayerArenaNPC();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setLevel(this.level);
		extend.setCards(this.cards);
		extend.setFormations(this.formations);
		extend.setLineups(this.lineups);
		extend.setEquips(this.equips);
		extend.setMagics(this.magics);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
