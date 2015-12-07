package com.huayi.doupo.base.model;

import java.io.*;

/**
	卡牌字典表
*/
@SuppressWarnings("serial")
public class DictCard implements Serializable
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
		小头像Id
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 3;
		result += index + "*int*" + smallUiId + "#";
	}

	public void setSmallUiId(int smallUiId, int bs) {
		this.smallUiId = smallUiId;
	}

	/**
		大头像Id
	*/
	private int bigUiId;
	public int getBigUiId(){
		return bigUiId;
	}
	public void setBigUiId(int bigUiId) {
		this.bigUiId = bigUiId;
		index = 4;
		result += index + "*int*" + bigUiId + "#";
	}

	public void setBigUiId(int bigUiId, int bs) {
		this.bigUiId = bigUiId;
	}

	/**
		资质
	*/
	private int nickname;
	public int getNickname(){
		return nickname;
	}
	public void setNickname(int nickname) {
		this.nickname = nickname;
		index = 5;
		result += index + "*int*" + nickname + "#";
	}

	public void setNickname(int nickname, int bs) {
		this.nickname = nickname;
	}

	/**
		性别 0-女   1-男
	*/
	private int sex;
	public int getSex(){
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
		index = 6;
		result += index + "*int*" + sex + "#";
	}

	public void setSex(int sex, int bs) {
		this.sex = sex;
	}

	/**
		卡牌类型Id
	*/
	private int cardTypeId;
	public int getCardTypeId(){
		return cardTypeId;
	}
	public void setCardTypeId(int cardTypeId) {
		this.cardTypeId = cardTypeId;
		index = 7;
		result += index + "*int*" + cardTypeId + "#";
	}

	public void setCardTypeId(int cardTypeId, int bs) {
		this.cardTypeId = cardTypeId;
	}

	/**
		战斗类型Id
	*/
	private int fightTypeId;
	public int getFightTypeId(){
		return fightTypeId;
	}
	public void setFightTypeId(int fightTypeId) {
		this.fightTypeId = fightTypeId;
		index = 8;
		result += index + "*int*" + fightTypeId + "#";
	}

	public void setFightTypeId(int fightTypeId, int bs) {
		this.fightTypeId = fightTypeId;
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
		index = 9;
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
		index = 10;
		result += index + "*int*" + starLevelId + "#";
	}

	public void setStarLevelId(int starLevelId, int bs) {
		this.starLevelId = starLevelId;
	}

	/**
		具体称号Id
	*/
	private int titleDetailId;
	public int getTitleDetailId(){
		return titleDetailId;
	}
	public void setTitleDetailId(int titleDetailId) {
		this.titleDetailId = titleDetailId;
		index = 11;
		result += index + "*int*" + titleDetailId + "#";
	}

	public void setTitleDetailId(int titleDetailId, int bs) {
		this.titleDetailId = titleDetailId;
	}

	/**
		系数Id
	*/
	private int coefficientId;
	public int getCoefficientId(){
		return coefficientId;
	}
	public void setCoefficientId(int coefficientId) {
		this.coefficientId = coefficientId;
		index = 12;
		result += index + "*int*" + coefficientId + "#";
	}

	public void setCoefficientId(int coefficientId, int bs) {
		this.coefficientId = coefficientId;
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
		index = 13;
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
		index = 14;
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
		index = 15;
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
		index = 16;
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
		index = 17;
		result += index + "*int*" + faDefense + "#";
	}

	public void setFaDefense(int faDefense, int bs) {
		this.faDefense = faDefense;
	}

	/**
		闪避
	*/
	private int dodge;
	public int getDodge(){
		return dodge;
	}
	public void setDodge(int dodge) {
		this.dodge = dodge;
		index = 18;
		result += index + "*int*" + dodge + "#";
	}

	public void setDodge(int dodge, int bs) {
		this.dodge = dodge;
	}

	/**
		暴击
	*/
	private int crit;
	public int getCrit(){
		return crit;
	}
	public void setCrit(int crit) {
		this.crit = crit;
		index = 19;
		result += index + "*int*" + crit + "#";
	}

	public void setCrit(int crit, int bs) {
		this.crit = crit;
	}

	/**
		命中
	*/
	private int hit;
	public int getHit(){
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
		index = 20;
		result += index + "*int*" + hit + "#";
	}

	public void setHit(int hit, int bs) {
		this.hit = hit;
	}

	/**
		韧性
	*/
	private int flex;
	public int getFlex(){
		return flex;
	}
	public void setFlex(int flex) {
		this.flex = flex;
		index = 21;
		result += index + "*int*" + flex + "#";
	}

	public void setFlex(int flex, int bs) {
		this.flex = flex;
	}

	/**
		血量+
	*/
	private int bloodAdd;
	public int getBloodAdd(){
		return bloodAdd;
	}
	public void setBloodAdd(int bloodAdd) {
		this.bloodAdd = bloodAdd;
		index = 22;
		result += index + "*int*" + bloodAdd + "#";
	}

	public void setBloodAdd(int bloodAdd, int bs) {
		this.bloodAdd = bloodAdd;
	}

	/**
		物攻+
	*/
	private int wuAttackAdd;
	public int getWuAttackAdd(){
		return wuAttackAdd;
	}
	public void setWuAttackAdd(int wuAttackAdd) {
		this.wuAttackAdd = wuAttackAdd;
		index = 23;
		result += index + "*int*" + wuAttackAdd + "#";
	}

	public void setWuAttackAdd(int wuAttackAdd, int bs) {
		this.wuAttackAdd = wuAttackAdd;
	}

	/**
		法攻+
	*/
	private int faAttackAdd;
	public int getFaAttackAdd(){
		return faAttackAdd;
	}
	public void setFaAttackAdd(int faAttackAdd) {
		this.faAttackAdd = faAttackAdd;
		index = 24;
		result += index + "*int*" + faAttackAdd + "#";
	}

	public void setFaAttackAdd(int faAttackAdd, int bs) {
		this.faAttackAdd = faAttackAdd;
	}

	/**
		物防+
	*/
	private int wuDefenseAdd;
	public int getWuDefenseAdd(){
		return wuDefenseAdd;
	}
	public void setWuDefenseAdd(int wuDefenseAdd) {
		this.wuDefenseAdd = wuDefenseAdd;
		index = 25;
		result += index + "*int*" + wuDefenseAdd + "#";
	}

	public void setWuDefenseAdd(int wuDefenseAdd, int bs) {
		this.wuDefenseAdd = wuDefenseAdd;
	}

	/**
		法防+
	*/
	private int faDefenseAdd;
	public int getFaDefenseAdd(){
		return faDefenseAdd;
	}
	public void setFaDefenseAdd(int faDefenseAdd) {
		this.faDefenseAdd = faDefenseAdd;
		index = 26;
		result += index + "*int*" + faDefenseAdd + "#";
	}

	public void setFaDefenseAdd(int faDefenseAdd, int bs) {
		this.faDefenseAdd = faDefenseAdd;
	}

	/**
		闪避+
	*/
	private int dodgeAdd;
	public int getDodgeAdd(){
		return dodgeAdd;
	}
	public void setDodgeAdd(int dodgeAdd) {
		this.dodgeAdd = dodgeAdd;
		index = 27;
		result += index + "*int*" + dodgeAdd + "#";
	}

	public void setDodgeAdd(int dodgeAdd, int bs) {
		this.dodgeAdd = dodgeAdd;
	}

	/**
		暴击+
	*/
	private int critAdd;
	public int getCritAdd(){
		return critAdd;
	}
	public void setCritAdd(int critAdd) {
		this.critAdd = critAdd;
		index = 28;
		result += index + "*int*" + critAdd + "#";
	}

	public void setCritAdd(int critAdd, int bs) {
		this.critAdd = critAdd;
	}

	/**
		命中+
	*/
	private int hitAdd;
	public int getHitAdd(){
		return hitAdd;
	}
	public void setHitAdd(int hitAdd) {
		this.hitAdd = hitAdd;
		index = 29;
		result += index + "*int*" + hitAdd + "#";
	}

	public void setHitAdd(int hitAdd, int bs) {
		this.hitAdd = hitAdd;
	}

	/**
		韧性+
	*/
	private int flexAdd;
	public int getFlexAdd(){
		return flexAdd;
	}
	public void setFlexAdd(int flexAdd) {
		this.flexAdd = flexAdd;
		index = 30;
		result += index + "*int*" + flexAdd + "#";
	}

	public void setFlexAdd(int flexAdd, int bs) {
		this.flexAdd = flexAdd;
	}

	/**
		技能1
	*/
	private int skillOne;
	public int getSkillOne(){
		return skillOne;
	}
	public void setSkillOne(int skillOne) {
		this.skillOne = skillOne;
		index = 31;
		result += index + "*int*" + skillOne + "#";
	}

	public void setSkillOne(int skillOne, int bs) {
		this.skillOne = skillOne;
	}

	/**
		技能2
	*/
	private int skillTwo;
	public int getSkillTwo(){
		return skillTwo;
	}
	public void setSkillTwo(int skillTwo) {
		this.skillTwo = skillTwo;
		index = 32;
		result += index + "*int*" + skillTwo + "#";
	}

	public void setSkillTwo(int skillTwo, int bs) {
		this.skillTwo = skillTwo;
	}

	/**
		技能3
	*/
	private int skillThree;
	public int getSkillThree(){
		return skillThree;
	}
	public void setSkillThree(int skillThree) {
		this.skillThree = skillThree;
		index = 33;
		result += index + "*int*" + skillThree + "#";
	}

	public void setSkillThree(int skillThree, int bs) {
		this.skillThree = skillThree;
	}

	/**
		潜力点
	*/
	private int potential;
	public int getPotential(){
		return potential;
	}
	public void setPotential(int potential) {
		this.potential = potential;
		index = 34;
		result += index + "*int*" + potential + "#";
	}

	public void setPotential(int potential, int bs) {
		this.potential = potential;
	}

	/**
		命宫字典Id用分号隔开
	*/
	private String constells;
	public String getConstells(){
		return constells;
	}
	public void setConstells(String constells) {
		this.constells = constells;
		index = 35;
		result += index + "*String*" + constells + "#";
	}

	public void setConstells(String constells, int bs) {
		this.constells = constells;
	}

	/**
		放技能是否显示技能名称 0-不显示 1-显示
	*/
	private int isSkillName;
	public int getIsSkillName(){
		return isSkillName;
	}
	public void setIsSkillName(int isSkillName) {
		this.isSkillName = isSkillName;
		index = 36;
		result += index + "*int*" + isSkillName + "#";
	}

	public void setIsSkillName(int isSkillName, int bs) {
		this.isSkillName = isSkillName;
	}

	/**
		卡牌动态图资源
	*/
	private String animationFiles;
	public String getAnimationFiles(){
		return animationFiles;
	}
	public void setAnimationFiles(String animationFiles) {
		this.animationFiles = animationFiles;
		index = 37;
		result += index + "*String*" + animationFiles + "#";
	}

	public void setAnimationFiles(String animationFiles, int bs) {
		this.animationFiles = animationFiles;
	}

	/**
		阵营
	*/
	private String camp;
	public String getCamp(){
		return camp;
	}
	public void setCamp(String camp) {
		this.camp = camp;
		index = 38;
		result += index + "*String*" + camp + "#";
	}

	public void setCamp(String camp, int bs) {
		this.camp = camp;
	}

	/**
		用于卡牌自身
	*/
	private String dubOne;
	public String getDubOne(){
		return dubOne;
	}
	public void setDubOne(String dubOne) {
		this.dubOne = dubOne;
		index = 39;
		result += index + "*String*" + dubOne + "#";
	}

	public void setDubOne(String dubOne, int bs) {
		this.dubOne = dubOne;
	}

	/**
		用于战斗中
	*/
	private String dubTwo;
	public String getDubTwo(){
		return dubTwo;
	}
	public void setDubTwo(String dubTwo) {
		this.dubTwo = dubTwo;
		index = 40;
		result += index + "*String*" + dubTwo + "#";
	}

	public void setDubTwo(String dubTwo, int bs) {
		this.dubTwo = dubTwo;
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
		index = 41;
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
		index = 42;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictCard clone(){
		DictCard extend=new DictCard();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setNickname(this.nickname);
		extend.setSex(this.sex);
		extend.setCardTypeId(this.cardTypeId);
		extend.setFightTypeId(this.fightTypeId);
		extend.setQualityId(this.qualityId);
		extend.setStarLevelId(this.starLevelId);
		extend.setTitleDetailId(this.titleDetailId);
		extend.setCoefficientId(this.coefficientId);
		extend.setBlood(this.blood);
		extend.setWuAttack(this.wuAttack);
		extend.setFaAttack(this.faAttack);
		extend.setWuDefense(this.wuDefense);
		extend.setFaDefense(this.faDefense);
		extend.setDodge(this.dodge);
		extend.setCrit(this.crit);
		extend.setHit(this.hit);
		extend.setFlex(this.flex);
		extend.setBloodAdd(this.bloodAdd);
		extend.setWuAttackAdd(this.wuAttackAdd);
		extend.setFaAttackAdd(this.faAttackAdd);
		extend.setWuDefenseAdd(this.wuDefenseAdd);
		extend.setFaDefenseAdd(this.faDefenseAdd);
		extend.setDodgeAdd(this.dodgeAdd);
		extend.setCritAdd(this.critAdd);
		extend.setHitAdd(this.hitAdd);
		extend.setFlexAdd(this.flexAdd);
		extend.setSkillOne(this.skillOne);
		extend.setSkillTwo(this.skillTwo);
		extend.setSkillThree(this.skillThree);
		extend.setPotential(this.potential);
		extend.setConstells(this.constells);
		extend.setIsSkillName(this.isSkillName);
		extend.setAnimationFiles(this.animationFiles);
		extend.setCamp(this.camp);
		extend.setDubOne(this.dubOne);
		extend.setDubTwo(this.dubTwo);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
