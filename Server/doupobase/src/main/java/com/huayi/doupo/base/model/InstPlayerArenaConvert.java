package com.huayi.doupo.base.model;

import java.io.*;

/**
	竞技场兑换实例表
*/
@SuppressWarnings("serial")
public class InstPlayerArenaConvert implements Serializable
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
		竞技场兑换字典Id
	*/
	private int arenaConvertId;
	public int getArenaConvertId(){
		return arenaConvertId;
	}
	public void setArenaConvertId(int arenaConvertId) {
		this.arenaConvertId = arenaConvertId;
		index = 3;
		result += index + "*int*" + arenaConvertId + "#";
	}

	public void setArenaConvertId(int arenaConvertId, int bs) {
		this.arenaConvertId = arenaConvertId;
	}

	/**
		兑换次数
	*/
	private int convertNum;
	public int getConvertNum(){
		return convertNum;
	}
	public void setConvertNum(int convertNum) {
		this.convertNum = convertNum;
		index = 4;
		result += index + "*int*" + convertNum + "#";
	}

	public void setConvertNum(int convertNum, int bs) {
		this.convertNum = convertNum;
	}

	/**
		操作时间
	*/
	private String operTime;
	public String getOperTime(){
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
		index = 5;
		result += index + "*String*" + operTime + "#";
	}

	public void setOperTime(String operTime, int bs) {
		this.operTime = operTime;
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

	/**
		添加时间
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
		更新时间
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

	public String getResult(){
		return result;
	}

	public InstPlayerArenaConvert clone(){
		InstPlayerArenaConvert extend=new InstPlayerArenaConvert();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setArenaConvertId(this.arenaConvertId);
		extend.setConvertNum(this.convertNum);
		extend.setOperTime(this.operTime);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
