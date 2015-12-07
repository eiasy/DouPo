package com.huayi.doupo.base.model;

import java.io.*;

/**
	进阶字典表 条件组合tableTypeId_tableFieldId_tableValue
*/
@SuppressWarnings("serial")
public class DictAdvance implements Serializable
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
		
	*/
	private int cardId;
	public int getCardId(){
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
		index = 2;
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
		index = 3;
		result += index + "*int*" + qualityId + "#";
	}

	public void setQualityId(int qualityId, int bs) {
		this.qualityId = qualityId;
	}

	/**
		星级Id
	*/
	private int starLevelId;
	public int getStarLevelId(){
		return starLevelId;
	}
	public void setStarLevelId(int starLevelId) {
		this.starLevelId = starLevelId;
		index = 4;
		result += index + "*int*" + starLevelId + "#";
	}

	public void setStarLevelId(int starLevelId, int bs) {
		this.starLevelId = starLevelId;
	}

	/**
		下一级品质Id
	*/
	private int nextQualityId;
	public int getNextQualityId(){
		return nextQualityId;
	}
	public void setNextQualityId(int nextQualityId) {
		this.nextQualityId = nextQualityId;
		index = 5;
		result += index + "*int*" + nextQualityId + "#";
	}

	public void setNextQualityId(int nextQualityId, int bs) {
		this.nextQualityId = nextQualityId;
	}

	/**
		下一级星级Id
	*/
	private int nextStarLevelId;
	public int getNextStarLevelId(){
		return nextStarLevelId;
	}
	public void setNextStarLevelId(int nextStarLevelId) {
		this.nextStarLevelId = nextStarLevelId;
		index = 6;
		result += index + "*int*" + nextStarLevelId + "#";
	}

	public void setNextStarLevelId(int nextStarLevelId, int bs) {
		this.nextStarLevelId = nextStarLevelId;
	}

	/**
		条件（多条分号隔开）
	*/
	private String conds;
	public String getConds(){
		return conds;
	}
	public void setConds(String conds) {
		this.conds = conds;
		index = 7;
		result += index + "*String*" + conds + "#";
	}

	public void setConds(String conds, int bs) {
		this.conds = conds;
	}

	/**
		血量
	*/
	private int blood;
	public int getBlood(){
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
		index = 8;
		result += index + "*int*" + blood + "#";
	}

	public void setBlood(int blood, int bs) {
		this.blood = blood;
	}

	/**
		物攻
	*/
	private int wuAttack;
	public int getWuAttack(){
		return wuAttack;
	}
	public void setWuAttack(int wuAttack) {
		this.wuAttack = wuAttack;
		index = 9;
		result += index + "*int*" + wuAttack + "#";
	}

	public void setWuAttack(int wuAttack, int bs) {
		this.wuAttack = wuAttack;
	}

	/**
		法攻
	*/
	private int faAttack;
	public int getFaAttack(){
		return faAttack;
	}
	public void setFaAttack(int faAttack) {
		this.faAttack = faAttack;
		index = 10;
		result += index + "*int*" + faAttack + "#";
	}

	public void setFaAttack(int faAttack, int bs) {
		this.faAttack = faAttack;
	}

	/**
		物防
	*/
	private int wuDefense;
	public int getWuDefense(){
		return wuDefense;
	}
	public void setWuDefense(int wuDefense) {
		this.wuDefense = wuDefense;
		index = 11;
		result += index + "*int*" + wuDefense + "#";
	}

	public void setWuDefense(int wuDefense, int bs) {
		this.wuDefense = wuDefense;
	}

	/**
		法防
	*/
	private int faDefense;
	public int getFaDefense(){
		return faDefense;
	}
	public void setFaDefense(int faDefense) {
		this.faDefense = faDefense;
		index = 12;
		result += index + "*int*" + faDefense + "#";
	}

	public void setFaDefense(int faDefense, int bs) {
		this.faDefense = faDefense;
	}

	/**
		血量+
	*/
	private float bloodAdd;
	public float getBloodAdd(){
		return bloodAdd;
	}
	public void setBloodAdd(float bloodAdd) {
		this.bloodAdd = bloodAdd;
		index = 13;
		result += index + "*float*" + bloodAdd + "#";
	}

	public void setBloodAdd(float bloodAdd, int bs) {
		this.bloodAdd = bloodAdd;
	}

	/**
		物攻+
	*/
	private float wuAttackAdd;
	public float getWuAttackAdd(){
		return wuAttackAdd;
	}
	public void setWuAttackAdd(float wuAttackAdd) {
		this.wuAttackAdd = wuAttackAdd;
		index = 14;
		result += index + "*float*" + wuAttackAdd + "#";
	}

	public void setWuAttackAdd(float wuAttackAdd, int bs) {
		this.wuAttackAdd = wuAttackAdd;
	}

	/**
		法攻+
	*/
	private float faAttackAdd;
	public float getFaAttackAdd(){
		return faAttackAdd;
	}
	public void setFaAttackAdd(float faAttackAdd) {
		this.faAttackAdd = faAttackAdd;
		index = 15;
		result += index + "*float*" + faAttackAdd + "#";
	}

	public void setFaAttackAdd(float faAttackAdd, int bs) {
		this.faAttackAdd = faAttackAdd;
	}

	/**
		物防+
	*/
	private float wuDefenseAdd;
	public float getWuDefenseAdd(){
		return wuDefenseAdd;
	}
	public void setWuDefenseAdd(float wuDefenseAdd) {
		this.wuDefenseAdd = wuDefenseAdd;
		index = 16;
		result += index + "*float*" + wuDefenseAdd + "#";
	}

	public void setWuDefenseAdd(float wuDefenseAdd, int bs) {
		this.wuDefenseAdd = wuDefenseAdd;
	}

	/**
		法防+
	*/
	private float faDefenseAdd;
	public float getFaDefenseAdd(){
		return faDefenseAdd;
	}
	public void setFaDefenseAdd(float faDefenseAdd) {
		this.faDefenseAdd = faDefenseAdd;
		index = 17;
		result += index + "*float*" + faDefenseAdd + "#";
	}

	public void setFaDefenseAdd(float faDefenseAdd, int bs) {
		this.faDefenseAdd = faDefenseAdd;
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
		index = 18;
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
		index = 19;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictAdvance clone(){
		DictAdvance extend=new DictAdvance();
		extend.setId(this.id);
		extend.setCardId(this.cardId);
		extend.setQualityId(this.qualityId);
		extend.setStarLevelId(this.starLevelId);
		extend.setNextQualityId(this.nextQualityId);
		extend.setNextStarLevelId(this.nextStarLevelId);
		extend.setConds(this.conds);
		extend.setBlood(this.blood);
		extend.setWuAttack(this.wuAttack);
		extend.setFaAttack(this.faAttack);
		extend.setWuDefense(this.wuDefense);
		extend.setFaDefense(this.faDefense);
		extend.setBloodAdd(this.bloodAdd);
		extend.setWuAttackAdd(this.wuAttackAdd);
		extend.setFaAttackAdd(this.faAttackAdd);
		extend.setWuDefenseAdd(this.wuDefenseAdd);
		extend.setFaDefenseAdd(this.faDefenseAdd);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
