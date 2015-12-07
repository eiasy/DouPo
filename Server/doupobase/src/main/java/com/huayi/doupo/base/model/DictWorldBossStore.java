package com.huayi.doupo.base.model;

import java.io.*;

/**
	世界boss商店字典表
*/
@SuppressWarnings("serial")
public class DictWorldBossStore implements Serializable
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
		商品
	*/
	private String things;
	public String getThings(){
		return things;
	}
	public void setThings(String things) {
		this.things = things;
		index = 3;
		result += index + "*String*" + things + "#";
	}

	public void setThings(String things, int bs) {
		this.things = things;
	}

	/**
		Boss积分
	*/
	private int needbossIntegral;
	public int getNeedbossIntegral(){
		return needbossIntegral;
	}
	public void setNeedbossIntegral(int needbossIntegral) {
		this.needbossIntegral = needbossIntegral;
		index = 4;
		result += index + "*int*" + needbossIntegral + "#";
	}

	public void setNeedbossIntegral(int needbossIntegral, int bs) {
		this.needbossIntegral = needbossIntegral;
	}

	/**
		排序
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 5;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
	}

	/**
		类型 1-魂魄 2-装备碎片 3-材料
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 6;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
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
		index = 7;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictWorldBossStore clone(){
		DictWorldBossStore extend=new DictWorldBossStore();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setThings(this.things);
		extend.setNeedbossIntegral(this.needbossIntegral);
		extend.setRank(this.rank);
		extend.setType(this.type);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
