package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家手动技能排布实例表
*/
@SuppressWarnings("serial")
public class InstPlayerManualSkillLine implements Serializable
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
		位置1
	*/
	private int position1;
	public int getPosition1(){
		return position1;
	}
	public void setPosition1(int position1) {
		this.position1 = position1;
		index = 3;
		result += index + "*int*" + position1 + "#";
	}

	public void setPosition1(int position1, int bs) {
		this.position1 = position1;
	}

	/**
		位置2
	*/
	private int position2;
	public int getPosition2(){
		return position2;
	}
	public void setPosition2(int position2) {
		this.position2 = position2;
		index = 4;
		result += index + "*int*" + position2 + "#";
	}

	public void setPosition2(int position2, int bs) {
		this.position2 = position2;
	}

	/**
		位置3
	*/
	private int position3;
	public int getPosition3(){
		return position3;
	}
	public void setPosition3(int position3) {
		this.position3 = position3;
		index = 5;
		result += index + "*int*" + position3 + "#";
	}

	public void setPosition3(int position3, int bs) {
		this.position3 = position3;
	}

	/**
		位置4 当前经验值
	*/
	private int position4;
	public int getPosition4(){
		return position4;
	}
	public void setPosition4(int position4) {
		this.position4 = position4;
		index = 6;
		result += index + "*int*" + position4 + "#";
	}

	public void setPosition4(int position4, int bs) {
		this.position4 = position4;
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
		index = 7;
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
		index = 8;
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
		index = 9;
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
		index = 10;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerManualSkillLine clone(){
		InstPlayerManualSkillLine extend=new InstPlayerManualSkillLine();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setPosition1(this.position1);
		extend.setPosition2(this.position2);
		extend.setPosition3(this.position3);
		extend.setPosition4(this.position4);
		extend.setOperTime(this.operTime);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
