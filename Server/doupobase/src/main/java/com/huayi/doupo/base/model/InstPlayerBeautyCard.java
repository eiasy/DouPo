package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家美人实例表
*/
@SuppressWarnings("serial")
public class InstPlayerBeautyCard implements Serializable
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
		美人Id
	*/
	private int beautyCardId;
	public int getBeautyCardId(){
		return beautyCardId;
	}
	public void setBeautyCardId(int beautyCardId) {
		this.beautyCardId = beautyCardId;
		index = 3;
		result += index + "*int*" + beautyCardId + "#";
	}

	public void setBeautyCardId(int beautyCardId, int bs) {
		this.beautyCardId = beautyCardId;
	}

	/**
		美人经验Id
	*/
	private int beautyCardExpId;
	public int getBeautyCardExpId(){
		return beautyCardExpId;
	}
	public void setBeautyCardExpId(int beautyCardExpId) {
		this.beautyCardExpId = beautyCardExpId;
		index = 4;
		result += index + "*int*" + beautyCardExpId + "#";
	}

	public void setBeautyCardExpId(int beautyCardExpId, int bs) {
		this.beautyCardExpId = beautyCardExpId;
	}

	/**
		经验值
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 5;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		概率
	*/
	private int pr;
	public int getPr(){
		return pr;
	}
	public void setPr(int pr) {
		this.pr = pr;
		index = 6;
		result += index + "*int*" + pr + "#";
	}

	public void setPr(int pr, int bs) {
		this.pr = pr;
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

	public InstPlayerBeautyCard clone(){
		InstPlayerBeautyCard extend=new InstPlayerBeautyCard();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setBeautyCardId(this.beautyCardId);
		extend.setBeautyCardExpId(this.beautyCardExpId);
		extend.setExp(this.exp);
		extend.setPr(this.pr);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
