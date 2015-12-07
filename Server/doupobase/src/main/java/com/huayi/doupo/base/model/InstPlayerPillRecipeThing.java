package com.huayi.doupo.base.model;

import java.io.*;

/**
	丹药药方碎片字典表
*/
@SuppressWarnings("serial")
public class InstPlayerPillRecipeThing implements Serializable
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
		丹药药方材料字典Id
	*/
	private int pillRecipeThingId;
	public int getPillRecipeThingId(){
		return pillRecipeThingId;
	}
	public void setPillRecipeThingId(int pillRecipeThingId) {
		this.pillRecipeThingId = pillRecipeThingId;
		index = 3;
		result += index + "*int*" + pillRecipeThingId + "#";
	}

	public void setPillRecipeThingId(int pillRecipeThingId, int bs) {
		this.pillRecipeThingId = pillRecipeThingId;
	}

	/**
		数量
	*/
	private int num;
	public int getNum(){
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		index = 4;
		result += index + "*int*" + num + "#";
	}

	public void setNum(int num, int bs) {
		this.num = num;
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
		版本号a
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

	public InstPlayerPillRecipeThing clone(){
		InstPlayerPillRecipeThing extend=new InstPlayerPillRecipeThing();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setPillRecipeThingId(this.pillRecipeThingId);
		extend.setNum(this.num);
		extend.setOperTime(this.operTime);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
