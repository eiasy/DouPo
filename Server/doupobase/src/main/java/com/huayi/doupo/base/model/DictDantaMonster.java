package com.huayi.doupo.base.model;

import java.io.*;

/**
	丹塔层数敌人字典表
*/
@SuppressWarnings("serial")
public class DictDantaMonster implements Serializable
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
		卡牌Id
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
		卡牌等级
	*/
	private int cardLevel;
	public int getCardLevel(){
		return cardLevel;
	}
	public void setCardLevel(int cardLevel) {
		this.cardLevel = cardLevel;
		index = 3;
		result += index + "*int*" + cardLevel + "#";
	}

	public void setCardLevel(int cardLevel, int bs) {
		this.cardLevel = cardLevel;
	}

	/**
		卡牌技能一等级
	*/
	private int skillOneLevel;
	public int getSkillOneLevel(){
		return skillOneLevel;
	}
	public void setSkillOneLevel(int skillOneLevel) {
		this.skillOneLevel = skillOneLevel;
		index = 4;
		result += index + "*int*" + skillOneLevel + "#";
	}

	public void setSkillOneLevel(int skillOneLevel, int bs) {
		this.skillOneLevel = skillOneLevel;
	}

	/**
		卡牌技能二等级
	*/
	private int skillTwoLevel;
	public int getSkillTwoLevel(){
		return skillTwoLevel;
	}
	public void setSkillTwoLevel(int skillTwoLevel) {
		this.skillTwoLevel = skillTwoLevel;
		index = 5;
		result += index + "*int*" + skillTwoLevel + "#";
	}

	public void setSkillTwoLevel(int skillTwoLevel, int bs) {
		this.skillTwoLevel = skillTwoLevel;
	}

	/**
		卡牌技能三等级
	*/
	private int skillThreeLevel;
	public int getSkillThreeLevel(){
		return skillThreeLevel;
	}
	public void setSkillThreeLevel(int skillThreeLevel) {
		this.skillThreeLevel = skillThreeLevel;
		index = 6;
		result += index + "*int*" + skillThreeLevel + "#";
	}

	public void setSkillThreeLevel(int skillThreeLevel, int bs) {
		this.skillThreeLevel = skillThreeLevel;
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
		index = 7;
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
		index = 8;
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
		index = 9;
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
		index = 10;
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
		index = 11;
		result += index + "*float*" + faDefenseAdd + "#";
	}

	public void setFaDefenseAdd(float faDefenseAdd, int bs) {
		this.faDefenseAdd = faDefenseAdd;
	}

	/**
		波数（第几波敌人）
	*/
	private int waveNum;
	public int getWaveNum(){
		return waveNum;
	}
	public void setWaveNum(int waveNum) {
		this.waveNum = waveNum;
		index = 12;
		result += index + "*int*" + waveNum + "#";
	}

	public void setWaveNum(int waveNum, int bs) {
		this.waveNum = waveNum;
	}

	/**
		卡牌位置
	*/
	private int position;
	public int getPosition(){
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
		index = 13;
		result += index + "*int*" + position + "#";
	}

	public void setPosition(int position, int bs) {
		this.position = position;
	}

	/**
		是否Boss 0-不是 1-是
	*/
	private int isBoss;
	public int getIsBoss(){
		return isBoss;
	}
	public void setIsBoss(int isBoss) {
		this.isBoss = isBoss;
		index = 14;
		result += index + "*int*" + isBoss + "#";
	}

	public void setIsBoss(int isBoss, int bs) {
		this.isBoss = isBoss;
	}

	/**
		品阶Id
	*/
	private int qualityId;
	public int getQualityId(){
		return qualityId;
	}
	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
		index = 15;
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
		index = 16;
		result += index + "*int*" + starLevelId + "#";
	}

	public void setStarLevelId(int starLevelId, int bs) {
		this.starLevelId = starLevelId;
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
		index = 17;
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
		index = 18;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictDantaMonster clone(){
		DictDantaMonster extend=new DictDantaMonster();
		extend.setId(this.id);
		extend.setCardId(this.cardId);
		extend.setCardLevel(this.cardLevel);
		extend.setSkillOneLevel(this.skillOneLevel);
		extend.setSkillTwoLevel(this.skillTwoLevel);
		extend.setSkillThreeLevel(this.skillThreeLevel);
		extend.setBloodAdd(this.bloodAdd);
		extend.setWuAttackAdd(this.wuAttackAdd);
		extend.setFaAttackAdd(this.faAttackAdd);
		extend.setWuDefenseAdd(this.wuDefenseAdd);
		extend.setFaDefenseAdd(this.faDefenseAdd);
		extend.setWaveNum(this.waveNum);
		extend.setPosition(this.position);
		extend.setIsBoss(this.isBoss);
		extend.setQualityId(this.qualityId);
		extend.setStarLevelId(this.starLevelId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
