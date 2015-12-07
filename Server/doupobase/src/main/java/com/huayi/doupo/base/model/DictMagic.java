package com.huayi.doupo.base.model;

import java.io.*;

/**
	法宝功法字典表
*/
@SuppressWarnings("serial")
public class DictMagic implements Serializable
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
		小图标Id
	*/
	private int smallUiId;
	public int getSmallUiId(){
		return smallUiId;
	}
	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
		index = 2;
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
		index = 3;
		result += index + "*int*" + bigUiId + "#";
	}

	public void setBigUiId(int bigUiId, int bs) {
		this.bigUiId = bigUiId;
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
		index = 4;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		类型 1-法宝 2-功法
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 5;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		品质Id
	*/
	private int magicQualityId;
	public int getMagicQualityId(){
		return magicQualityId;
	}
	public void setMagicQualityId(int magicQualityId) {
		this.magicQualityId = magicQualityId;
		index = 6;
		result += index + "*int*" + magicQualityId + "#";
	}

	public void setMagicQualityId(int magicQualityId, int bs) {
		this.magicQualityId = magicQualityId;
	}

	/**
		品级
	*/
	private int grade;
	public int getGrade(){
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
		index = 7;
		result += index + "*int*" + grade + "#";
	}

	public void setGrade(int grade, int bs) {
		this.grade = grade;
	}

	/**
		法宝功法等级经验字典Id
	*/
	private int magicLevelId;
	public int getMagicLevelId(){
		return magicLevelId;
	}
	public void setMagicLevelId(int magicLevelId) {
		this.magicLevelId = magicLevelId;
		index = 8;
		result += index + "*int*" + magicLevelId + "#";
	}

	public void setMagicLevelId(int magicLevelId, int bs) {
		this.magicLevelId = magicLevelId;
	}

	/**
		出售价格
	*/
	private int sellCopper;
	public int getSellCopper(){
		return sellCopper;
	}
	public void setSellCopper(int sellCopper) {
		this.sellCopper = sellCopper;
		index = 9;
		result += index + "*int*" + sellCopper + "#";
	}

	public void setSellCopper(int sellCopper, int bs) {
		this.sellCopper = sellCopper;
	}

	/**
		初始经验
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 10;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		属性1 最前面1代表百分比 2代表数值;第二个值 代表Dict_FightProp 表ID;第三个值 代表初始属性;第四个值 代表升级增加属性  3代表是经验
	*/
	private String value1;
	public String getValue1(){
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
		index = 11;
		result += index + "*String*" + value1 + "#";
	}

	public void setValue1(String value1, int bs) {
		this.value1 = value1;
	}

	/**
		属性2 最前面1代表百分比 2代表数值;第二个值 代表Dict_FightProp 表ID;第三个值 代表初始属性;第四个值 代表升级增加属性
	*/
	private String value2;
	public String getValue2(){
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
		index = 12;
		result += index + "*String*" + value2 + "#";
	}

	public void setValue2(String value2, int bs) {
		this.value2 = value2;
	}

	/**
		属性3 最前面1代表百分比 2代表数值;第二个值 代表Dict_FightProp 表ID;第三个值 代表初始属性;第四个值 代表升级增加属性
	*/
	private String value3;
	public String getValue3(){
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
		index = 13;
		result += index + "*String*" + value3 + "#";
	}

	public void setValue3(String value3, int bs) {
		this.value3 = value3;
	}

	/**
		属性4 10级解锁属性  fightPropId_fightPropValue(百分比)
	*/
	private String value4;
	public String getValue4(){
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
		index = 14;
		result += index + "*String*" + value4 + "#";
	}

	public void setValue4(String value4, int bs) {
		this.value4 = value4;
	}

	/**
		属性5 20级解锁属性  fightPropId_fightPropValue(百分比)
	*/
	private String value5;
	public String getValue5(){
		return value5;
	}
	public void setValue5(String value5) {
		this.value5 = value5;
		index = 15;
		result += index + "*String*" + value5 + "#";
	}

	public void setValue5(String value5, int bs) {
		this.value5 = value5;
	}

	/**
		40级解锁属性  fightPropId_fightPropValue(百分比)
	*/
	private String value6;
	public String getValue6(){
		return value6;
	}
	public void setValue6(String value6) {
		this.value6 = value6;
		index = 16;
		result += index + "*String*" + value6 + "#";
	}

	public void setValue6(String value6, int bs) {
		this.value6 = value6;
	}

	/**
		切割坐标分号隔开
	*/
	private String chops;
	public String getChops(){
		return chops;
	}
	public void setChops(String chops) {
		this.chops = chops;
		index = 17;
		result += index + "*String*" + chops + "#";
	}

	public void setChops(String chops, int bs) {
		this.chops = chops;
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

	public DictMagic clone(){
		DictMagic extend=new DictMagic();
		extend.setId(this.id);
		extend.setSmallUiId(this.smallUiId);
		extend.setBigUiId(this.bigUiId);
		extend.setName(this.name);
		extend.setType(this.type);
		extend.setMagicQualityId(this.magicQualityId);
		extend.setGrade(this.grade);
		extend.setMagicLevelId(this.magicLevelId);
		extend.setSellCopper(this.sellCopper);
		extend.setExp(this.exp);
		extend.setValue1(this.value1);
		extend.setValue2(this.value2);
		extend.setValue3(this.value3);
		extend.setValue4(this.value4);
		extend.setValue5(this.value5);
		extend.setValue6(this.value6);
		extend.setChops(this.chops);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
