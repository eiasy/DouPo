package com.huayi.doupo.base.model;

import java.io.*;

/**
	联盟实例表
*/
@SuppressWarnings("serial")
public class InstUnion implements Serializable
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
		名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 2;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		经验
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 3;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		等级
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 4;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		联盟类型
	*/
	private int gradeTypeId;
	public int getGradeTypeId(){
		return gradeTypeId;
	}
	public void setGradeTypeId(int gradeTypeId) {
		this.gradeTypeId = gradeTypeId;
		index = 5;
		result += index + "*int*" + gradeTypeId + "#";
	}

	public void setGradeTypeId(int gradeTypeId, int bs) {
		this.gradeTypeId = gradeTypeId;
	}

	/**
		最大成员数
	*/
	private int maxMember;
	public int getMaxMember(){
		return maxMember;
	}
	public void setMaxMember(int maxMember) {
		this.maxMember = maxMember;
		index = 6;
		result += index + "*int*" + maxMember + "#";
	}

	public void setMaxMember(int maxMember, int bs) {
		this.maxMember = maxMember;
	}

	/**
		当前成员数
	*/
	private int currentMember;
	public int getCurrentMember(){
		return currentMember;
	}
	public void setCurrentMember(int currentMember) {
		this.currentMember = currentMember;
		index = 7;
		result += index + "*int*" + currentMember + "#";
	}

	public void setCurrentMember(int currentMember, int bs) {
		this.currentMember = currentMember;
	}

	/**
		团长玩家实例Id
	*/
	private int headInstPlayerId;
	public int getHeadInstPlayerId(){
		return headInstPlayerId;
	}
	public void setHeadInstPlayerId(int headInstPlayerId) {
		this.headInstPlayerId = headInstPlayerId;
		index = 8;
		result += index + "*int*" + headInstPlayerId + "#";
	}

	public void setHeadInstPlayerId(int headInstPlayerId, int bs) {
		this.headInstPlayerId = headInstPlayerId;
	}

	/**
		创建时间
	*/
	private String createTime;
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
		index = 9;
		result += index + "*String*" + createTime + "#";
	}

	public void setCreateTime(String createTime, int bs) {
		this.createTime = createTime;
	}

	/**
		联盟公告
	*/
	private String unionNotice;
	public String getUnionNotice(){
		return unionNotice;
	}
	public void setUnionNotice(String unionNotice) {
		this.unionNotice = unionNotice;
		index = 10;
		result += index + "*String*" + unionNotice + "#";
	}

	public void setUnionNotice(String unionNotice, int bs) {
		this.unionNotice = unionNotice;
	}

	/**
		联盟宣言
	*/
	private String unionManifesto;
	public String getUnionManifesto(){
		return unionManifesto;
	}
	public void setUnionManifesto(String unionManifesto) {
		this.unionManifesto = unionManifesto;
		index = 11;
		result += index + "*String*" + unionManifesto + "#";
	}

	public void setUnionManifesto(String unionManifesto, int bs) {
		this.unionManifesto = unionManifesto;
	}

	/**
		进度
	*/
	private int plan;
	public int getPlan(){
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
		index = 12;
		result += index + "*int*" + plan + "#";
	}

	public void setPlan(int plan, int bs) {
		this.plan = plan;
	}

	/**
		当日建设人数
	*/
	private int constructionNum;
	public int getConstructionNum(){
		return constructionNum;
	}
	public void setConstructionNum(int constructionNum) {
		this.constructionNum = constructionNum;
		index = 13;
		result += index + "*int*" + constructionNum + "#";
	}

	public void setConstructionNum(int constructionNum, int bs) {
		this.constructionNum = constructionNum;
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
		index = 14;
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
		index = 15;
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
		index = 16;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstUnion clone(){
		InstUnion extend=new InstUnion();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setExp(this.exp);
		extend.setLevel(this.level);
		extend.setGradeTypeId(this.gradeTypeId);
		extend.setMaxMember(this.maxMember);
		extend.setCurrentMember(this.currentMember);
		extend.setHeadInstPlayerId(this.headInstPlayerId);
		extend.setCreateTime(this.createTime);
		extend.setUnionNotice(this.unionNotice);
		extend.setUnionManifesto(this.unionManifesto);
		extend.setPlan(this.plan);
		extend.setConstructionNum(this.constructionNum);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
