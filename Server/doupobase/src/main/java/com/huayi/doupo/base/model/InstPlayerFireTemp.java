package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家异火技能蜕变临时实例表
*/
@SuppressWarnings("serial")
public class InstPlayerFireTemp implements Serializable
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
		异火实例Id
	*/
	private int instPlayerFireId;
	public int getInstPlayerFireId(){
		return instPlayerFireId;
	}
	public void setInstPlayerFireId(int instPlayerFireId) {
		this.instPlayerFireId = instPlayerFireId;
		index = 2;
		result += index + "*int*" + instPlayerFireId + "#";
	}

	public void setInstPlayerFireId(int instPlayerFireId, int bs) {
		this.instPlayerFireId = instPlayerFireId;
	}

	/**
		位置
	*/
	private int position;
	public int getPosition(){
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
		index = 3;
		result += index + "*int*" + position + "#";
	}

	public void setPosition(int position, int bs) {
		this.position = position;
	}

	/**
		异火技能字典Id
	*/
	private int fireSkillId;
	public int getFireSkillId(){
		return fireSkillId;
	}
	public void setFireSkillId(int fireSkillId) {
		this.fireSkillId = fireSkillId;
		index = 4;
		result += index + "*int*" + fireSkillId + "#";
	}

	public void setFireSkillId(int fireSkillId, int bs) {
		this.fireSkillId = fireSkillId;
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
		index = 5;
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
		index = 6;
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
		index = 7;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerFireTemp clone(){
		InstPlayerFireTemp extend=new InstPlayerFireTemp();
		extend.setId(this.id);
		extend.setInstPlayerFireId(this.instPlayerFireId);
		extend.setPosition(this.position);
		extend.setFireSkillId(this.fireSkillId);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
