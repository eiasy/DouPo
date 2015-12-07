package com.huayi.doupo.base.model;

import java.io.*;

/**
	异火字典表
*/
@SuppressWarnings("serial")
public class DictFire implements Serializable
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
		特效文件名 分号隔开
	*/
	private String plists;
	public String getPlists(){
		return plists;
	}
	public void setPlists(String plists) {
		this.plists = plists;
		index = 3;
		result += index + "*String*" + plists + "#";
	}

	public void setPlists(String plists, int bs) {
		this.plists = plists;
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
		index = 4;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		潜力
	*/
	private int potential;
	public int getPotential(){
		return potential;
	}
	public void setPotential(int potential) {
		this.potential = potential;
		index = 5;
		result += index + "*int*" + potential + "#";
	}

	public void setPotential(int potential, int bs) {
		this.potential = potential;
	}

	/**
		类型 1-异火 2-兽火
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 6;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		初始异火技能Id
	*/
	private int fireSkillId;
	public int getFireSkillId(){
		return fireSkillId;
	}
	public void setFireSkillId(int fireSkillId) {
		this.fireSkillId = fireSkillId;
		index = 7;
		result += index + "*int*" + fireSkillId + "#";
	}

	public void setFireSkillId(int fireSkillId, int bs) {
		this.fireSkillId = fireSkillId;
	}

	/**
		被动技能个数
	*/
	private int bySkills;
	public int getBySkills(){
		return bySkills;
	}
	public void setBySkills(int bySkills) {
		this.bySkills = bySkills;
		index = 8;
		result += index + "*int*" + bySkills + "#";
	}

	public void setBySkills(int bySkills, int bs) {
		this.bySkills = bySkills;
	}

	/**
		被动技能条件 15_25_35
	*/
	private String bySkillConds;
	public String getBySkillConds(){
		return bySkillConds;
	}
	public void setBySkillConds(String bySkillConds) {
		this.bySkillConds = bySkillConds;
		index = 9;
		result += index + "*String*" + bySkillConds + "#";
	}

	public void setBySkillConds(String bySkillConds, int bs) {
		this.bySkillConds = bySkillConds;
	}

	/**
		异火技能加成系数
	*/
	private int fireSkillAdd;
	public int getFireSkillAdd(){
		return fireSkillAdd;
	}
	public void setFireSkillAdd(int fireSkillAdd) {
		this.fireSkillAdd = fireSkillAdd;
		index = 10;
		result += index + "*int*" + fireSkillAdd + "#";
	}

	public void setFireSkillAdd(int fireSkillAdd, int bs) {
		this.fireSkillAdd = fireSkillAdd;
	}

	/**
		出售铜钱价格
	*/
	private int sellCopper;
	public int getSellCopper(){
		return sellCopper;
	}
	public void setSellCopper(int sellCopper) {
		this.sellCopper = sellCopper;
		index = 11;
		result += index + "*int*" + sellCopper + "#";
	}

	public void setSellCopper(int sellCopper, int bs) {
		this.sellCopper = sellCopper;
	}

	/**
		异火技能品质Id
	*/
	private int fireSkillQualityId;
	public int getFireSkillQualityId(){
		return fireSkillQualityId;
	}
	public void setFireSkillQualityId(int fireSkillQualityId) {
		this.fireSkillQualityId = fireSkillQualityId;
		index = 12;
		result += index + "*int*" + fireSkillQualityId + "#";
	}

	public void setFireSkillQualityId(int fireSkillQualityId, int bs) {
		this.fireSkillQualityId = fireSkillQualityId;
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
		index = 13;
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
		index = 14;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictFire clone(){
		DictFire extend=new DictFire();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setPlists(this.plists);
		extend.setExp(this.exp);
		extend.setPotential(this.potential);
		extend.setType(this.type);
		extend.setFireSkillId(this.fireSkillId);
		extend.setBySkills(this.bySkills);
		extend.setBySkillConds(this.bySkillConds);
		extend.setFireSkillAdd(this.fireSkillAdd);
		extend.setSellCopper(this.sellCopper);
		extend.setFireSkillQualityId(this.fireSkillQualityId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
