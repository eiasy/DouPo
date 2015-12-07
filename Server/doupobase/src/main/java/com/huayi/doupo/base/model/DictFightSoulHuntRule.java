package com.huayi.doupo.base.model;

import java.io.*;

/**
	斗魂猎取规则字典表
*/
@SuppressWarnings("serial")
public class DictFightSoulHuntRule implements Serializable
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
		图片Id
	*/
	private int uiId;
	public int getUiId(){
		return uiId;
	}
	public void setUiId(int uiId) {
		this.uiId = uiId;
		index = 2;
		result += index + "*int*" + uiId + "#";
	}

	public void setUiId(int uiId, int bs) {
		this.uiId = uiId;
	}

	/**
		所需银币
	*/
	private int needSilver;
	public int getNeedSilver(){
		return needSilver;
	}
	public void setNeedSilver(int needSilver) {
		this.needSilver = needSilver;
		index = 3;
		result += index + "*int*" + needSilver + "#";
	}

	public void setNeedSilver(int needSilver, int bs) {
		this.needSilver = needSilver;
	}

	/**
		所需金币 没有点亮按钮时,填0
	*/
	private int needGold;
	public int getNeedGold(){
		return needGold;
	}
	public void setNeedGold(int needGold) {
		this.needGold = needGold;
		index = 4;
		result += index + "*int*" + needGold + "#";
	}

	public void setNeedGold(int needGold, int bs) {
		this.needGold = needGold;
	}

	/**
		激活猎取Id的概率
	*/
	private int activeHuntRuleIdPer;
	public int getActiveHuntRuleIdPer(){
		return activeHuntRuleIdPer;
	}
	public void setActiveHuntRuleIdPer(int activeHuntRuleIdPer) {
		this.activeHuntRuleIdPer = activeHuntRuleIdPer;
		index = 5;
		result += index + "*int*" + activeHuntRuleIdPer + "#";
	}

	public void setActiveHuntRuleIdPer(int activeHuntRuleIdPer, int bs) {
		this.activeHuntRuleIdPer = activeHuntRuleIdPer;
	}

	/**
		激活的猎取Id
	*/
	private int activeHuntRuleId;
	public int getActiveHuntRuleId(){
		return activeHuntRuleId;
	}
	public void setActiveHuntRuleId(int activeHuntRuleId) {
		this.activeHuntRuleId = activeHuntRuleId;
		index = 6;
		result += index + "*int*" + activeHuntRuleId + "#";
	}

	public void setActiveHuntRuleId(int activeHuntRuleId, int bs) {
		this.activeHuntRuleId = activeHuntRuleId;
	}

	/**
		得到各猎魂品质的概率
	*/
	private String fightSoulQualityPers;
	public String getFightSoulQualityPers(){
		return fightSoulQualityPers;
	}
	public void setFightSoulQualityPers(String fightSoulQualityPers) {
		this.fightSoulQualityPers = fightSoulQualityPers;
		index = 7;
		result += index + "*String*" + fightSoulQualityPers + "#";
	}

	public void setFightSoulQualityPers(String fightSoulQualityPers, int bs) {
		this.fightSoulQualityPers = fightSoulQualityPers;
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
		index = 8;
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
		index = 9;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictFightSoulHuntRule clone(){
		DictFightSoulHuntRule extend=new DictFightSoulHuntRule();
		extend.setId(this.id);
		extend.setUiId(this.uiId);
		extend.setNeedSilver(this.needSilver);
		extend.setNeedGold(this.needGold);
		extend.setActiveHuntRuleIdPer(this.activeHuntRuleIdPer);
		extend.setActiveHuntRuleId(this.activeHuntRuleId);
		extend.setFightSoulQualityPers(this.fightSoulQualityPers);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
