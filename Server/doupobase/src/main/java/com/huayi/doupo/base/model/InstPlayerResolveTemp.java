package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家分解临时表
*/
@SuppressWarnings("serial")
public class InstPlayerResolveTemp implements Serializable
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
		玩家实例Id
	*/
	private int instPlayerId;
	public int getInstPlayerId(){
		return instPlayerId;
	}
	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
		index = 2;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
	}

	/**
		分解结果 tableTypeId_tableFieldId_value;
	*/
	private String resolveResult;
	public String getResolveResult(){
		return resolveResult;
	}
	public void setResolveResult(String resolveResult) {
		this.resolveResult = resolveResult;
		index = 3;
		result += index + "*String*" + resolveResult + "#";
	}

	public void setResolveResult(String resolveResult, int bs) {
		this.resolveResult = resolveResult;
	}

	/**
		分解消耗的铜币数
	*/
	private int consumCopper;
	public int getConsumCopper(){
		return consumCopper;
	}
	public void setConsumCopper(int consumCopper) {
		this.consumCopper = consumCopper;
		index = 4;
		result += index + "*int*" + consumCopper + "#";
	}

	public void setConsumCopper(int consumCopper, int bs) {
		this.consumCopper = consumCopper;
	}

	/**
		
	*/
	private int resolveType;
	public int getResolveType(){
		return resolveType;
	}
	public void setResolveType(int resolveType) {
		this.resolveType = resolveType;
		index = 5;
		result += index + "*int*" + resolveType + "#";
	}

	public void setResolveType(int resolveType, int bs) {
		this.resolveType = resolveType;
	}

	/**
		
	*/
	private String resolveList;
	public String getResolveList(){
		return resolveList;
	}
	public void setResolveList(String resolveList) {
		this.resolveList = resolveList;
		index = 6;
		result += index + "*String*" + resolveList + "#";
	}

	public void setResolveList(String resolveList, int bs) {
		this.resolveList = resolveList;
	}

	/**
		
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 7;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
	}

	/**
		
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 8;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	/**
		
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

	public String getResult(){
		return result;
	}

	public InstPlayerResolveTemp clone(){
		InstPlayerResolveTemp extend=new InstPlayerResolveTemp();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setResolveResult(this.resolveResult);
		extend.setConsumCopper(this.consumCopper);
		extend.setResolveType(this.resolveType);
		extend.setResolveList(this.resolveList);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		extend.setVersion(this.version);
		return extend;
	}
}
