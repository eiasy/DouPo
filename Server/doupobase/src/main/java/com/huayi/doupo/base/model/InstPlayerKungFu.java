package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家功法实例表
*/
@SuppressWarnings("serial")
public class InstPlayerKungFu implements Serializable
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
		功法Id
	*/
	private int kungFuId;
	public int getKungFuId(){
		return kungFuId;
	}
	public void setKungFuId(int kungFuId) {
		this.kungFuId = kungFuId;
		index = 3;
		result += index + "*int*" + kungFuId + "#";
	}

	public void setKungFuId(int kungFuId, int bs) {
		this.kungFuId = kungFuId;
	}

	/**
		当前经验值
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 4;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		功法重数加成字典Id
	*/
	private int kungFuTierAddId;
	public int getKungFuTierAddId(){
		return kungFuTierAddId;
	}
	public void setKungFuTierAddId(int kungFuTierAddId) {
		this.kungFuTierAddId = kungFuTierAddId;
		index = 5;
		result += index + "*int*" + kungFuTierAddId + "#";
	}

	public void setKungFuTierAddId(int kungFuTierAddId, int bs) {
		this.kungFuTierAddId = kungFuTierAddId;
	}

	/**
		当前要开启的经脉节点字典表Id
	*/
	private int acupointNodeId;
	public int getAcupointNodeId(){
		return acupointNodeId;
	}
	public void setAcupointNodeId(int acupointNodeId) {
		this.acupointNodeId = acupointNodeId;
		index = 6;
		result += index + "*int*" + acupointNodeId + "#";
	}

	public void setAcupointNodeId(int acupointNodeId, int bs) {
		this.acupointNodeId = acupointNodeId;
	}

	/**
		当前重数下的所有经脉节点字典Id
	*/
	private String acupointNodes;
	public String getAcupointNodes(){
		return acupointNodes;
	}
	public void setAcupointNodes(String acupointNodes) {
		this.acupointNodes = acupointNodes;
		index = 7;
		result += index + "*String*" + acupointNodes + "#";
	}

	public void setAcupointNodes(String acupointNodes, int bs) {
		this.acupointNodes = acupointNodes;
	}

	/**
		属性1
	*/
	private String fightPropOne;
	public String getFightPropOne(){
		return fightPropOne;
	}
	public void setFightPropOne(String fightPropOne) {
		this.fightPropOne = fightPropOne;
		index = 8;
		result += index + "*String*" + fightPropOne + "#";
	}

	public void setFightPropOne(String fightPropOne, int bs) {
		this.fightPropOne = fightPropOne;
	}

	/**
		属性2
	*/
	private String fightPropTwo;
	public String getFightPropTwo(){
		return fightPropTwo;
	}
	public void setFightPropTwo(String fightPropTwo) {
		this.fightPropTwo = fightPropTwo;
		index = 9;
		result += index + "*String*" + fightPropTwo + "#";
	}

	public void setFightPropTwo(String fightPropTwo, int bs) {
		this.fightPropTwo = fightPropTwo;
	}

	/**
		属性3
	*/
	private String fightPropThree;
	public String getFightPropThree(){
		return fightPropThree;
	}
	public void setFightPropThree(String fightPropThree) {
		this.fightPropThree = fightPropThree;
		index = 10;
		result += index + "*String*" + fightPropThree + "#";
	}

	public void setFightPropThree(String fightPropThree, int bs) {
		this.fightPropThree = fightPropThree;
	}

	/**
		装备的卡牌实例Id  0-未使用
	*/
	private int instPlayerCardId;
	public int getInstPlayerCardId(){
		return instPlayerCardId;
	}
	public void setInstPlayerCardId(int instPlayerCardId) {
		this.instPlayerCardId = instPlayerCardId;
		index = 11;
		result += index + "*int*" + instPlayerCardId + "#";
	}

	public void setInstPlayerCardId(int instPlayerCardId, int bs) {
		this.instPlayerCardId = instPlayerCardId;
	}

	/**
		属性4
	*/
	private String fightPropFour;
	public String getFightPropFour(){
		return fightPropFour;
	}
	public void setFightPropFour(String fightPropFour) {
		this.fightPropFour = fightPropFour;
		index = 12;
		result += index + "*String*" + fightPropFour + "#";
	}

	public void setFightPropFour(String fightPropFour, int bs) {
		this.fightPropFour = fightPropFour;
	}

	/**
		属性5
	*/
	private String fightPropFive;
	public String getFightPropFive(){
		return fightPropFive;
	}
	public void setFightPropFive(String fightPropFive) {
		this.fightPropFive = fightPropFive;
		index = 13;
		result += index + "*String*" + fightPropFive + "#";
	}

	public void setFightPropFive(String fightPropFive, int bs) {
		this.fightPropFive = fightPropFive;
	}

	/**
		属性6
	*/
	private String fightPropSix;
	public String getFightPropSix(){
		return fightPropSix;
	}
	public void setFightPropSix(String fightPropSix) {
		this.fightPropSix = fightPropSix;
		index = 14;
		result += index + "*String*" + fightPropSix + "#";
	}

	public void setFightPropSix(String fightPropSix, int bs) {
		this.fightPropSix = fightPropSix;
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
		index = 15;
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
		index = 16;
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
		index = 17;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerKungFu clone(){
		InstPlayerKungFu extend=new InstPlayerKungFu();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setKungFuId(this.kungFuId);
		extend.setExp(this.exp);
		extend.setKungFuTierAddId(this.kungFuTierAddId);
		extend.setAcupointNodeId(this.acupointNodeId);
		extend.setAcupointNodes(this.acupointNodes);
		extend.setFightPropOne(this.fightPropOne);
		extend.setFightPropTwo(this.fightPropTwo);
		extend.setFightPropThree(this.fightPropThree);
		extend.setInstPlayerCardId(this.instPlayerCardId);
		extend.setFightPropFour(this.fightPropFour);
		extend.setFightPropFive(this.fightPropFive);
		extend.setFightPropSix(this.fightPropSix);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
