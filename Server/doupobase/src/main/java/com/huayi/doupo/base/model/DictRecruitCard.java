package com.huayi.doupo.base.model;

import java.io.*;

/**
	招募卡牌库字典表
*/
@SuppressWarnings("serial")
public class DictRecruitCard implements Serializable
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
		招募类型Id
	*/
	private int recruitTypeId;
	public int getRecruitTypeId(){
		return recruitTypeId;
	}
	public void setRecruitTypeId(int recruitTypeId) {
		this.recruitTypeId = recruitTypeId;
		index = 2;
		result += index + "*int*" + recruitTypeId + "#";
	}

	public void setRecruitTypeId(int recruitTypeId, int bs) {
		this.recruitTypeId = recruitTypeId;
	}

	/**
		卡牌Id
	*/
	private int cardId;
	public int getCardId(){
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
		index = 3;
		result += index + "*int*" + cardId + "#";
	}

	public void setCardId(int cardId, int bs) {
		this.cardId = cardId;
	}

	/**
		品质Id
	*/
	private int qualityId;
	public int getQualityId(){
		return qualityId;
	}
	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
		index = 4;
		result += index + "*int*" + qualityId + "#";
	}

	public void setQualityId(int qualityId, int bs) {
		this.qualityId = qualityId;
	}

	/**
		招募权重 可以控制卡牌投放
	*/
	private int weight;
	public int getWeight(){
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
		index = 5;
		result += index + "*int*" + weight + "#";
	}

	public void setWeight(int weight, int bs) {
		this.weight = weight;
	}

	/**
		描述
	*/
	private String description;
	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		index = 6;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		
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

	public String getResult(){
		return result;
	}

	public DictRecruitCard clone(){
		DictRecruitCard extend=new DictRecruitCard();
		extend.setId(this.id);
		extend.setRecruitTypeId(this.recruitTypeId);
		extend.setCardId(this.cardId);
		extend.setQualityId(this.qualityId);
		extend.setWeight(this.weight);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
