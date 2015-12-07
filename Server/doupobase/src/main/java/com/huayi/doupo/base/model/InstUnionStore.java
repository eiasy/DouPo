package com.huayi.doupo.base.model;

import java.io.*;

/**
	联盟商店实例表
*/
@SuppressWarnings("serial")
public class InstUnionStore implements Serializable
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
		道具购买记录(用于每天可购买) id_num;
	*/
	private String unionStoreOnes;
	public String getUnionStoreOnes(){
		return unionStoreOnes;
	}
	public void setUnionStoreOnes(String unionStoreOnes) {
		this.unionStoreOnes = unionStoreOnes;
		index = 4;
		result += index + "*String*" + unionStoreOnes + "#";
	}

	public void setUnionStoreOnes(String unionStoreOnes, int bs) {
		this.unionStoreOnes = unionStoreOnes;
	}

	/**
		限时购买记录
	*/
	private String unionStoreTwos;
	public String getUnionStoreTwos(){
		return unionStoreTwos;
	}
	public void setUnionStoreTwos(String unionStoreTwos) {
		this.unionStoreTwos = unionStoreTwos;
		index = 5;
		result += index + "*String*" + unionStoreTwos + "#";
	}

	public void setUnionStoreTwos(String unionStoreTwos, int bs) {
		this.unionStoreTwos = unionStoreTwos;
	}

	/**
		奖励领取记录
	*/
	private String unionStoreThrees;
	public String getUnionStoreThrees(){
		return unionStoreThrees;
	}
	public void setUnionStoreThrees(String unionStoreThrees) {
		this.unionStoreThrees = unionStoreThrees;
		index = 6;
		result += index + "*String*" + unionStoreThrees + "#";
	}

	public void setUnionStoreThrees(String unionStoreThrees, int bs) {
		this.unionStoreThrees = unionStoreThrees;
	}

	/**
		永久购买记录（目前用于道具一次性购买)id_num;
	*/
	private String unionStores;
	public String getUnionStores(){
		return unionStores;
	}
	public void setUnionStores(String unionStores) {
		this.unionStores = unionStores;
		index = 7;
		result += index + "*String*" + unionStores + "#";
	}

	public void setUnionStores(String unionStores, int bs) {
		this.unionStores = unionStores;
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

	public InstUnionStore clone(){
		InstUnionStore extend=new InstUnionStore();
		extend.setId(this.id);
		extend.setInstUnionId(this.instUnionId);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setUnionStoreOnes(this.unionStoreOnes);
		extend.setUnionStoreTwos(this.unionStoreTwos);
		extend.setUnionStoreThrees(this.unionStoreThrees);
		extend.setUnionStores(this.unionStores);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
