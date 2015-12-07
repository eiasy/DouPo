package com.huayi.doupo.base.model;

import java.io.*;

/**
	异火技能蜕变字典表
*/
@SuppressWarnings("serial")
public class DictFireSkillChange implements Serializable
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
		潜力值
	*/
	private int potential;
	public int getPotential(){
		return potential;
	}
	public void setPotential(int potential) {
		this.potential = potential;
		index = 2;
		result += index + "*int*" + potential + "#";
	}

	public void setPotential(int potential, int bs) {
		this.potential = potential;
	}

	/**
		异火技能Id
	*/
	private int fireSkillId;
	public int getFireSkillId(){
		return fireSkillId;
	}
	public void setFireSkillId(int fireSkillId) {
		this.fireSkillId = fireSkillId;
		index = 3;
		result += index + "*int*" + fireSkillId + "#";
	}

	public void setFireSkillId(int fireSkillId, int bs) {
		this.fireSkillId = fireSkillId;
	}

	/**
		概率
	*/
	private float chance;
	public float getChance(){
		return chance;
	}
	public void setChance(float chance) {
		this.chance = chance;
		index = 4;
		result += index + "*float*" + chance + "#";
	}

	public void setChance(float chance, int bs) {
		this.chance = chance;
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

	public DictFireSkillChange clone(){
		DictFireSkillChange extend=new DictFireSkillChange();
		extend.setId(this.id);
		extend.setPotential(this.potential);
		extend.setFireSkillId(this.fireSkillId);
		extend.setChance(this.chance);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
