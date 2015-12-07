package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家塔实例表
*/
@SuppressWarnings("serial")
public class InstPlayerPagoda implements Serializable
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
		塔层字典Id
	*/
	private int pagodaStoreyId;
	public int getPagodaStoreyId(){
		return pagodaStoreyId;
	}
	public void setPagodaStoreyId(int pagodaStoreyId) {
		this.pagodaStoreyId = pagodaStoreyId;
		index = 3;
		result += index + "*int*" + pagodaStoreyId + "#";
	}

	public void setPagodaStoreyId(int pagodaStoreyId, int bs) {
		this.pagodaStoreyId = pagodaStoreyId;
	}

	/**
		塔层挑战数量  0-未过  1,2,3表示次数
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
		重置次数
	*/
	private int resetNum;
	public int getResetNum(){
		return resetNum;
	}
	public void setResetNum(int resetNum) {
		this.resetNum = resetNum;
		index = 5;
		result += index + "*int*" + resetNum + "#";
	}

	public void setResetNum(int resetNum, int bs) {
		this.resetNum = resetNum;
	}

	/**
		搜寻次数
	*/
	private int searchNum;
	public int getSearchNum(){
		return searchNum;
	}
	public void setSearchNum(int searchNum) {
		this.searchNum = searchNum;
		index = 6;
		result += index + "*int*" + searchNum + "#";
	}

	public void setSearchNum(int searchNum, int bs) {
		this.searchNum = searchNum;
	}

	/**
		最高挑战到的塔层字典Id
	*/
	private int maxPagodaStoreyId;
	public int getMaxPagodaStoreyId(){
		return maxPagodaStoreyId;
	}
	public void setMaxPagodaStoreyId(int maxPagodaStoreyId) {
		this.maxPagodaStoreyId = maxPagodaStoreyId;
		index = 7;
		result += index + "*int*" + maxPagodaStoreyId + "#";
	}

	public void setMaxPagodaStoreyId(int maxPagodaStoreyId, int bs) {
		this.maxPagodaStoreyId = maxPagodaStoreyId;
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
		index = 8;
		result += index + "*String*" + operTime + "#";
	}

	public void setOperTime(String operTime, int bs) {
		this.operTime = operTime;
	}

	/**
		领奖Id 分号隔开
	*/
	private String pagodaStores;
	public String getPagodaStores(){
		return pagodaStores;
	}
	public void setPagodaStores(String pagodaStores) {
		this.pagodaStores = pagodaStores;
		index = 9;
		result += index + "*String*" + pagodaStores + "#";
	}

	public void setPagodaStores(String pagodaStores, int bs) {
		this.pagodaStores = pagodaStores;
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
		index = 10;
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
		index = 11;
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
		index = 12;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerPagoda clone(){
		InstPlayerPagoda extend=new InstPlayerPagoda();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setPagodaStoreyId(this.pagodaStoreyId);
		extend.setNum(this.num);
		extend.setResetNum(this.resetNum);
		extend.setSearchNum(this.searchNum);
		extend.setMaxPagodaStoreyId(this.maxPagodaStoreyId);
		extend.setOperTime(this.operTime);
		extend.setPagodaStores(this.pagodaStores);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
