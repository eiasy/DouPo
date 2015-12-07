package com.huayi.doupo.base.model;

import java.io.*;

/**
	丹塔的层
*/
@SuppressWarnings("serial")
public class DictDantaLayer implements Serializable
{
	private int index;
	public String result = "";
	/**
		层ID
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
		最大替补人数
	*/
	private int maxSubstituteCount;
	public int getMaxSubstituteCount(){
		return maxSubstituteCount;
	}
	public void setMaxSubstituteCount(int maxSubstituteCount) {
		this.maxSubstituteCount = maxSubstituteCount;
		index = 2;
		result += index + "*int*" + maxSubstituteCount + "#";
	}

	public void setMaxSubstituteCount(int maxSubstituteCount, int bs) {
		this.maxSubstituteCount = maxSubstituteCount;
	}

	/**
		最大首发人数
	*/
	private int maxFirstCount;
	public int getMaxFirstCount(){
		return maxFirstCount;
	}
	public void setMaxFirstCount(int maxFirstCount) {
		this.maxFirstCount = maxFirstCount;
		index = 3;
		result += index + "*int*" + maxFirstCount + "#";
	}

	public void setMaxFirstCount(int maxFirstCount, int bs) {
		this.maxFirstCount = maxFirstCount;
	}

	/**
		本层奖励
	*/
	private String layerAwards;
	public String getLayerAwards(){
		return layerAwards;
	}
	public void setLayerAwards(String layerAwards) {
		this.layerAwards = layerAwards;
		index = 4;
		result += index + "*String*" + layerAwards + "#";
	}

	public void setLayerAwards(String layerAwards, int bs) {
		this.layerAwards = layerAwards;
	}

	/**
		怪物ID库（id1,id2）
	*/
	private String monsters;
	public String getMonsters(){
		return monsters;
	}
	public void setMonsters(String monsters) {
		this.monsters = monsters;
		index = 5;
		result += index + "*String*" + monsters + "#";
	}

	public void setMonsters(String monsters, int bs) {
		this.monsters = monsters;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictDantaLayer clone(){
		DictDantaLayer extend=new DictDantaLayer();
		extend.setId(this.id);
		extend.setMaxSubstituteCount(this.maxSubstituteCount);
		extend.setMaxFirstCount(this.maxFirstCount);
		extend.setLayerAwards(this.layerAwards);
		extend.setMonsters(this.monsters);
		extend.setVersion(this.version);
		return extend;
	}
}
