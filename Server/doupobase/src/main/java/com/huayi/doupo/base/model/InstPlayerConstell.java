package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家命宫实例表
*/
@SuppressWarnings("serial")
public class InstPlayerConstell implements Serializable
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
		卡牌实例Id
	*/
	private int instCardId;
	public int getInstCardId(){
		return instCardId;
	}
	public void setInstCardId(int instCardId) {
		this.instCardId = instCardId;
		index = 3;
		result += index + "*int*" + instCardId + "#";
	}

	public void setInstCardId(int instCardId, int bs) {
		this.instCardId = instCardId;
	}

	/**
		命宫字典Id
	*/
	private int constellId;
	public int getConstellId(){
		return constellId;
	}
	public void setConstellId(int constellId) {
		this.constellId = constellId;
		index = 4;
		result += index + "*int*" + constellId + "#";
	}

	public void setConstellId(int constellId, int bs) {
		this.constellId = constellId;
	}

	/**
		命宫丹药状态 0-未服用 1-服用
	*/
	private String pills;
	public String getPills(){
		return pills;
	}
	public void setPills(String pills) {
		this.pills = pills;
		index = 5;
		result += index + "*String*" + pills + "#";
	}

	public void setPills(String pills, int bs) {
		this.pills = pills;
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
		index = 6;
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
		index = 7;
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
		index = 8;
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
		index = 9;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerConstell clone(){
		InstPlayerConstell extend=new InstPlayerConstell();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setInstCardId(this.instCardId);
		extend.setConstellId(this.constellId);
		extend.setPills(this.pills);
		extend.setOperTime(this.operTime);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
