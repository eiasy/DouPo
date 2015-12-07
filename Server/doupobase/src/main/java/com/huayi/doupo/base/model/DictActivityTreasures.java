package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class DictActivityTreasures implements Serializable
{
	private int index;
	public String result = "";
	/**
		id
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
		排序，记录第几次抽元宝
	*/
	private int rank;
	public int getRank(){
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
		index = 2;
		result += index + "*int*" + rank + "#";
	}

	public void setRank(int rank, int bs) {
		this.rank = rank;
	}

	/**
		消耗元宝数
	*/
	private int cost;
	public int getCost(){
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
		index = 3;
		result += index + "*int*" + cost + "#";
	}

	public void setCost(int cost, int bs) {
		this.cost = cost;
	}

	/**
		获得元宝数
	*/
	private int obtain;
	public int getObtain(){
		return obtain;
	}
	public void setObtain(int obtain) {
		this.obtain = obtain;
		index = 4;
		result += index + "*int*" + obtain + "#";
	}

	public void setObtain(int obtain, int bs) {
		this.obtain = obtain;
	}

	/**
		权重
	*/
	private int weight;
	public int getWeight(){
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
		index = 5;
		result += index + "*int*" + weight + "#";
	}

	public void setWeight(int weight, int bs) {
		this.weight = weight;
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

	public DictActivityTreasures clone(){
		DictActivityTreasures extend=new DictActivityTreasures();
		extend.setId(this.id);
		extend.setRank(this.rank);
		extend.setCost(this.cost);
		extend.setObtain(this.obtain);
		extend.setWeight(this.weight);
		extend.setVersion(this.version);
		return extend;
	}
}
