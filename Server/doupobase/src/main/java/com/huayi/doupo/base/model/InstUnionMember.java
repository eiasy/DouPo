package com.huayi.doupo.base.model;

import java.io.*;

/**
	联盟成员实例表
*/
@SuppressWarnings("serial")
public class InstUnionMember implements Serializable
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
		工会实例Id
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
		工会职位字典Id
	*/
	private int unionGradeId;
	public int getUnionGradeId(){
		return unionGradeId;
	}
	public void setUnionGradeId(int unionGradeId) {
		this.unionGradeId = unionGradeId;
		index = 4;
		result += index + "*int*" + unionGradeId + "#";
	}

	public void setUnionGradeId(int unionGradeId, int bs) {
		this.unionGradeId = unionGradeId;
	}

	/**
		总贡献
	*/
	private int sumOffer;
	public int getSumOffer(){
		return sumOffer;
	}
	public void setSumOffer(int sumOffer) {
		this.sumOffer = sumOffer;
		index = 5;
		result += index + "*int*" + sumOffer + "#";
	}

	public void setSumOffer(int sumOffer, int bs) {
		this.sumOffer = sumOffer;
	}

	/**
		当天贡献
	*/
	private int currentOffer;
	public int getCurrentOffer(){
		return currentOffer;
	}
	public void setCurrentOffer(int currentOffer) {
		this.currentOffer = currentOffer;
		index = 6;
		result += index + "*int*" + currentOffer + "#";
	}

	public void setCurrentOffer(int currentOffer, int bs) {
		this.currentOffer = currentOffer;
	}

	/**
		联盟建设字典Id
	*/
	private int unionBuildId;
	public int getUnionBuildId(){
		return unionBuildId;
	}
	public void setUnionBuildId(int unionBuildId) {
		this.unionBuildId = unionBuildId;
		index = 7;
		result += index + "*int*" + unionBuildId + "#";
	}

	public void setUnionBuildId(int unionBuildId, int bs) {
		this.unionBuildId = unionBuildId;
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

	public InstUnionMember clone(){
		InstUnionMember extend=new InstUnionMember();
		extend.setId(this.id);
		extend.setInstUnionId(this.instUnionId);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setUnionGradeId(this.unionGradeId);
		extend.setSumOffer(this.sumOffer);
		extend.setCurrentOffer(this.currentOffer);
		extend.setUnionBuildId(this.unionBuildId);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
