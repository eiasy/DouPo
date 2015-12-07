package com.huayi.doupo.base.model;

import java.io.*;

/**
	塔层字典表
*/
@SuppressWarnings("serial")
public class DictPagodaStorey implements Serializable
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
		塔阵字典Id
	*/
	private int pagodaFormationId;
	public int getPagodaFormationId(){
		return pagodaFormationId;
	}
	public void setPagodaFormationId(int pagodaFormationId) {
		this.pagodaFormationId = pagodaFormationId;
		index = 3;
		result += index + "*int*" + pagodaFormationId + "#";
	}

	public void setPagodaFormationId(int pagodaFormationId, int bs) {
		this.pagodaFormationId = pagodaFormationId;
	}

	/**
		奖励金币
	*/
	private int copper;
	public int getCopper(){
		return copper;
	}
	public void setCopper(int copper) {
		this.copper = copper;
		index = 4;
		result += index + "*int*" + copper + "#";
	}

	public void setCopper(int copper, int bs) {
		this.copper = copper;
	}

	/**
		奖励火能 (由之前的修为改为火能)
	*/
	private int culture;
	public int getCulture(){
		return culture;
	}
	public void setCulture(int culture) {
		this.culture = culture;
		index = 5;
		result += index + "*int*" + culture + "#";
	}

	public void setCulture(int culture, int bs) {
		this.culture = culture;
	}

	/**
		胜利途径
	*/
	private int victoryMeans;
	public int getVictoryMeans(){
		return victoryMeans;
	}
	public void setVictoryMeans(int victoryMeans) {
		this.victoryMeans = victoryMeans;
		index = 6;
		result += index + "*int*" + victoryMeans + "#";
	}

	public void setVictoryMeans(int victoryMeans, int bs) {
		this.victoryMeans = victoryMeans;
	}

	/**
		胜利途径值
	*/
	private int victoryValue;
	public int getVictoryValue(){
		return victoryValue;
	}
	public void setVictoryValue(int victoryValue) {
		this.victoryValue = victoryValue;
		index = 7;
		result += index + "*int*" + victoryValue + "#";
	}

	public void setVictoryValue(int victoryValue, int bs) {
		this.victoryValue = victoryValue;
	}

	/**
		塔层掉落物品最小个数
	*/
	private int thingMin;
	public int getThingMin(){
		return thingMin;
	}
	public void setThingMin(int thingMin) {
		this.thingMin = thingMin;
		index = 8;
		result += index + "*int*" + thingMin + "#";
	}

	public void setThingMin(int thingMin, int bs) {
		this.thingMin = thingMin;
	}

	/**
		塔层掉落物品最大个数
	*/
	private int thingMax;
	public int getThingMax(){
		return thingMax;
	}
	public void setThingMax(int thingMax) {
		this.thingMax = thingMax;
		index = 9;
		result += index + "*int*" + thingMax + "#";
	}

	public void setThingMax(int thingMax, int bs) {
		this.thingMax = thingMax;
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

	public DictPagodaStorey clone(){
		DictPagodaStorey extend=new DictPagodaStorey();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setPagodaFormationId(this.pagodaFormationId);
		extend.setCopper(this.copper);
		extend.setCulture(this.culture);
		extend.setVictoryMeans(this.victoryMeans);
		extend.setVictoryValue(this.victoryValue);
		extend.setThingMin(this.thingMin);
		extend.setThingMax(this.thingMax);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
