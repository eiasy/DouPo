package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class InstLuckRank implements Serializable
{
	private int index;
	public String result = "";
	/**
		
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
		
	*/
	private int playerId;
	public int getPlayerId(){
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
		index = 2;
		result += index + "*int*" + playerId + "#";
	}

	public void setPlayerId(int playerId, int bs) {
		this.playerId = playerId;
	}

	/**
		
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 3;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		积分
	*/
	private int rankValue;
	public int getRankValue(){
		return rankValue;
	}
	public void setRankValue(int rankValue) {
		this.rankValue = rankValue;
		index = 4;
		result += index + "*int*" + rankValue + "#";
	}

	public void setRankValue(int rankValue, int bs) {
		this.rankValue = rankValue;
	}

	/**
		名次
	*/
	private int orderIndex;
	public int getOrderIndex(){
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
		index = 5;
		result += index + "*int*" + orderIndex + "#";
	}

	public void setOrderIndex(int orderIndex, int bs) {
		this.orderIndex = orderIndex;
	}

	/**
		时间
	*/
	private String countReset;
	public String getCountReset(){
		return countReset;
	}
	public void setCountReset(String countReset) {
		this.countReset = countReset;
		index = 6;
		result += index + "*String*" + countReset + "#";
	}

	public void setCountReset(String countReset, int bs) {
		this.countReset = countReset;
	}

	/**
		免费启动剩余次数
	*/
	private int startRemain;
	public int getStartRemain(){
		return startRemain;
	}
	public void setStartRemain(int startRemain) {
		this.startRemain = startRemain;
		index = 7;
		result += index + "*int*" + startRemain + "#";
	}

	public void setStartRemain(int startRemain, int bs) {
		this.startRemain = startRemain;
	}

	/**
		免费刷新剩余次数
	*/
	private int refreshRemain;
	public int getRefreshRemain(){
		return refreshRemain;
	}
	public void setRefreshRemain(int refreshRemain) {
		this.refreshRemain = refreshRemain;
		index = 8;
		result += index + "*int*" + refreshRemain + "#";
	}

	public void setRefreshRemain(int refreshRemain, int bs) {
		this.refreshRemain = refreshRemain;
	}

	/**
		转盘内的物品
	*/
	private String items;
	public String getItems(){
		return items;
	}
	public void setItems(String items) {
		this.items = items;
		index = 9;
		result += index + "*String*" + items + "#";
	}

	public void setItems(String items, int bs) {
		this.items = items;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 10;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		付费启动次数
	*/
	private int payStartCount;
	public int getPayStartCount(){
		return payStartCount;
	}
	public void setPayStartCount(int payStartCount) {
		this.payStartCount = payStartCount;
		index = 11;
		result += index + "*int*" + payStartCount + "#";
	}

	public void setPayStartCount(int payStartCount, int bs) {
		this.payStartCount = payStartCount;
	}

	/**
		付费刷新次数
	*/
	private int payRefreshCount;
	public int getPayRefreshCount(){
		return payRefreshCount;
	}
	public void setPayRefreshCount(int payRefreshCount) {
		this.payRefreshCount = payRefreshCount;
		index = 12;
		result += index + "*int*" + payRefreshCount + "#";
	}

	public void setPayRefreshCount(int payRefreshCount, int bs) {
		this.payRefreshCount = payRefreshCount;
	}

	/**
		获得限定物品次数
	*/
	private int getLimitCount;
	public int getGetLimitCount(){
		return getLimitCount;
	}
	public void setGetLimitCount(int getLimitCount) {
		this.getLimitCount = getLimitCount;
		index = 13;
		result += index + "*int*" + getLimitCount + "#";
	}

	public void setGetLimitCount(int getLimitCount, int bs) {
		this.getLimitCount = getLimitCount;
	}

	/**
		最后一次获得限定物品时的积分
	*/
	private int lastLimitValue;
	public int getLastLimitValue(){
		return lastLimitValue;
	}
	public void setLastLimitValue(int lastLimitValue) {
		this.lastLimitValue = lastLimitValue;
		index = 14;
		result += index + "*int*" + lastLimitValue + "#";
	}

	public void setLastLimitValue(int lastLimitValue, int bs) {
		this.lastLimitValue = lastLimitValue;
	}

	public String getResult(){
		return result;
	}

	public InstLuckRank clone(){
		InstLuckRank extend=new InstLuckRank();
		extend.setId(this.id);
		extend.setPlayerId(this.playerId);
		extend.setName(this.name);
		extend.setRankValue(this.rankValue);
		extend.setOrderIndex(this.orderIndex);
		extend.setCountReset(this.countReset);
		extend.setStartRemain(this.startRemain);
		extend.setRefreshRemain(this.refreshRemain);
		extend.setItems(this.items);
		extend.setVersion(this.version);
		extend.setPayStartCount(this.payStartCount);
		extend.setPayRefreshCount(this.payRefreshCount);
		extend.setGetLimitCount(this.getLimitCount);
		extend.setLastLimitValue(this.lastLimitValue);
		return extend;
	}
}
