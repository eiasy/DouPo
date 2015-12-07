package com.huayi.doupo.base.model;

import java.io.*;

/**
	卡牌经验成长字典表
*/
@SuppressWarnings("serial")
public class DictCardExp implements Serializable
{
	private int index;
	public String result = "";
	/**
		等级
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
		经验值
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 2;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		轮回返还银币
	*/
	private int restoreCopper;
	public int getRestoreCopper(){
		return restoreCopper;
	}
	public void setRestoreCopper(int restoreCopper) {
		this.restoreCopper = restoreCopper;
		index = 3;
		result += index + "*int*" + restoreCopper + "#";
	}

	public void setRestoreCopper(int restoreCopper, int bs) {
		this.restoreCopper = restoreCopper;
	}

	/**
		属性公式值
	*/
	private int propFormula;
	public int getPropFormula(){
		return propFormula;
	}
	public void setPropFormula(int propFormula) {
		this.propFormula = propFormula;
		index = 4;
		result += index + "*int*" + propFormula + "#";
	}

	public void setPropFormula(int propFormula, int bs) {
		this.propFormula = propFormula;
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
		index = 5;
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
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictCardExp clone(){
		DictCardExp extend=new DictCardExp();
		extend.setId(this.id);
		extend.setExp(this.exp);
		extend.setRestoreCopper(this.restoreCopper);
		extend.setPropFormula(this.propFormula);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
