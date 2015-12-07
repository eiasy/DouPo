package com.huayi.doupo.base.model;

import java.io.*;

/**
	翅膀进阶字典表
*/
@SuppressWarnings("serial")
public class DictWingAdvance implements Serializable
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
		翅膀Id
	*/
	private int wingId;
	public int getWingId(){
		return wingId;
	}
	public void setWingId(int wingId) {
		this.wingId = wingId;
		index = 2;
		result += index + "*int*" + wingId + "#";
	}

	public void setWingId(int wingId, int bs) {
		this.wingId = wingId;
	}

	/**
		星数
	*/
	private int starNum;
	public int getStarNum(){
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
		index = 3;
		result += index + "*int*" + starNum + "#";
	}

	public void setStarNum(int starNum, int bs) {
		this.starNum = starNum;
	}

	/**
		小图标Id
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 4;
		result += index + "*int*" + smallUiId + "#";
	}

	public void setSmallUiId(int smallUiId, int bs) {
		this.smallUiId = smallUiId;
	}

	/**
		大图标Id
	*/
	private int bigUiId;
	public int getBigUiId(){
		return bigUiId;
	}
	public void setBigUiId(int bigUiId) {
		this.bigUiId = bigUiId;
		index = 5;
		result += index + "*int*" + bigUiId + "#";
	}

	public void setBigUiId(int bigUiId, int bs) {
		this.bigUiId = bigUiId;
	}

	/**
		此进阶星数下的开放战斗属性列表 fightPropId;fightPropId
	*/
	private String openFightPropIdList;
	public String getOpenFightPropIdList(){
		return openFightPropIdList;
	}
	public void setOpenFightPropIdList(String openFightPropIdList) {
		this.openFightPropIdList = openFightPropIdList;
		index = 6;
		result += index + "*String*" + openFightPropIdList + "#";
	}

	public void setOpenFightPropIdList(String openFightPropIdList, int bs) {
		this.openFightPropIdList = openFightPropIdList;
	}

	/**
		进阶条件 tableTypeId_tableFieldId_value;
	*/
	private String nextStarNumConds;
	public String getNextStarNumConds(){
		return nextStarNumConds;
	}
	public void setNextStarNumConds(String nextStarNumConds) {
		this.nextStarNumConds = nextStarNumConds;
		index = 7;
		result += index + "*String*" + nextStarNumConds + "#";
	}

	public void setNextStarNumConds(String nextStarNumConds, int bs) {
		this.nextStarNumConds = nextStarNumConds;
	}

	/**
		可卖多少银币
	*/
	private int sellSilver;
	public int getSellSilver(){
		return sellSilver;
	}
	public void setSellSilver(int sellSilver) {
		this.sellSilver = sellSilver;
		index = 8;
		result += index + "*int*" + sellSilver + "#";
	}

	public void setSellSilver(int sellSilver, int bs) {
		this.sellSilver = sellSilver;
	}

	/**
		转换所需元宝数
	*/
	private int convertGold;
	public int getConvertGold(){
		return convertGold;
	}
	public void setConvertGold(int convertGold) {
		this.convertGold = convertGold;
		index = 9;
		result += index + "*int*" + convertGold + "#";
	}

	public void setConvertGold(int convertGold, int bs) {
		this.convertGold = convertGold;
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
		index = 10;
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
		index = 11;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictWingAdvance clone(){
		DictWingAdvance extend=new DictWingAdvance();
		extend.setId(this.id);
		extend.setWingId(this.wingId);
		extend.setStarNum(this.starNum);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setOpenFightPropIdList(this.openFightPropIdList);
		extend.setNextStarNumConds(this.nextStarNumConds);
		extend.setSellSilver(this.sellSilver);
		extend.setConvertGold(this.convertGold);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
