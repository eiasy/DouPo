package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class InstPlayerMineAward implements Serializable
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
		玩家ID
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
		0. 领取过矿主奖励 1.领取过协助奖励
	*/
	private int awardType;
	public int getAwardType(){
		return awardType;
	}
	public void setAwardType(int awardType) {
		this.awardType = awardType;
		index = 3;
		result += index + "*int*" + awardType + "#";
	}

	public void setAwardType(int awardType, int bs) {
		this.awardType = awardType;
	}

	/**
		数据版本
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 4;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerMineAward clone(){
		InstPlayerMineAward extend=new InstPlayerMineAward();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setAwardType(this.awardType);
		extend.setVersion(this.version);
		return extend;
	}
}
