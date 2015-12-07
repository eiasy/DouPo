package com.huayi.doupo.base.model;

import java.io.*;

/**
	联盟动态实例表
*/
@SuppressWarnings("serial")
public class InstUnionDynamic implements Serializable
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
		联盟实例Id
	*/
	private int instUnionId;
	public int getInstUnionId(){
		return instUnionId;
	}
	public void setInstUnionId(int instUnionId) {
		this.instUnionId = instUnionId;
		index = 2;
		result += index + "*int*" + instUnionId + "#";
	}

	public void setInstUnionId(int instUnionId, int bs) {
		this.instUnionId = instUnionId;
	}

	/**
		成员玩家实例Id
	*/
	private int instPlayerId;
	public int getInstPlayerId(){
		return instPlayerId;
	}
	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
		index = 3;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
	}

	/**
		联盟建设字典Id
	*/
	private int unionBuild;
	public int getUnionBuild(){
		return unionBuild;
	}
	public void setUnionBuild(int unionBuild) {
		this.unionBuild = unionBuild;
		index = 4;
		result += index + "*int*" + unionBuild + "#";
	}

	public void setUnionBuild(int unionBuild, int bs) {
		this.unionBuild = unionBuild;
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

	public InstUnionDynamic clone(){
		InstUnionDynamic extend=new InstUnionDynamic();
		extend.setId(this.id);
		extend.setInstUnionId(this.instUnionId);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setUnionBuild(this.unionBuild);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
