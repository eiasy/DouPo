package com.huayi.doupo.base.model;

import java.io.*;

/**
	装备与套装映射字典表
*/
@SuppressWarnings("serial")
public class DictEquipSuitRefer implements Serializable
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
		装备Id
	*/
	private int equipId;
	public int getEquipId(){
		return equipId;
	}
	public void setEquipId(int equipId) {
		this.equipId = equipId;
		index = 2;
		result += index + "*int*" + equipId + "#";
	}

	public void setEquipId(int equipId, int bs) {
		this.equipId = equipId;
	}

	/**
		套装套装Id
	*/
	private int equipSuitId;
	public int getEquipSuitId(){
		return equipSuitId;
	}
	public void setEquipSuitId(int equipSuitId) {
		this.equipSuitId = equipSuitId;
		index = 3;
		result += index + "*int*" + equipSuitId + "#";
	}

	public void setEquipSuitId(int equipSuitId, int bs) {
		this.equipSuitId = equipSuitId;
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
		index = 4;
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
		index = 5;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictEquipSuitRefer clone(){
		DictEquipSuitRefer extend=new DictEquipSuitRefer();
		extend.setId(this.id);
		extend.setEquipId(this.equipId);
		extend.setEquipSuitId(this.equipSuitId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
