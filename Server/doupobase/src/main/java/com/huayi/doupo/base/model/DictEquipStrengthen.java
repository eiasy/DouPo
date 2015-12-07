package com.huayi.doupo.base.model;

import java.io.*;

/**
	装备强化字典表
*/
@SuppressWarnings("serial")
public class DictEquipStrengthen implements Serializable
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
		白品质强化消耗的银币
	*/
	private int whiteCopper;
	public int getWhiteCopper(){
		return whiteCopper;
	}
	public void setWhiteCopper(int whiteCopper) {
		this.whiteCopper = whiteCopper;
		index = 2;
		result += index + "*int*" + whiteCopper + "#";
	}

	public void setWhiteCopper(int whiteCopper, int bs) {
		this.whiteCopper = whiteCopper;
	}

	/**
		绿品质强化消耗的银币
	*/
	private int greenCopper;
	public int getGreenCopper(){
		return greenCopper;
	}
	public void setGreenCopper(int greenCopper) {
		this.greenCopper = greenCopper;
		index = 3;
		result += index + "*int*" + greenCopper + "#";
	}

	public void setGreenCopper(int greenCopper, int bs) {
		this.greenCopper = greenCopper;
	}

	/**
		蓝品质强化消耗的银币
	*/
	private int blueCopper;
	public int getBlueCopper(){
		return blueCopper;
	}
	public void setBlueCopper(int blueCopper) {
		this.blueCopper = blueCopper;
		index = 4;
		result += index + "*int*" + blueCopper + "#";
	}

	public void setBlueCopper(int blueCopper, int bs) {
		this.blueCopper = blueCopper;
	}

	/**
		紫品质强化消耗的银币
	*/
	private int purpleCopper;
	public int getPurpleCopper(){
		return purpleCopper;
	}
	public void setPurpleCopper(int purpleCopper) {
		this.purpleCopper = purpleCopper;
		index = 5;
		result += index + "*int*" + purpleCopper + "#";
	}

	public void setPurpleCopper(int purpleCopper, int bs) {
		this.purpleCopper = purpleCopper;
	}

	/**
		金品质强化消耗的银币
	*/
	private int goldenCopper;
	public int getGoldenCopper(){
		return goldenCopper;
	}
	public void setGoldenCopper(int goldenCopper) {
		this.goldenCopper = goldenCopper;
		index = 6;
		result += index + "*int*" + goldenCopper + "#";
	}

	public void setGoldenCopper(int goldenCopper, int bs) {
		this.goldenCopper = goldenCopper;
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
		index = 7;
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
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictEquipStrengthen clone(){
		DictEquipStrengthen extend=new DictEquipStrengthen();
		extend.setId(this.id);
		extend.setWhiteCopper(this.whiteCopper);
		extend.setGreenCopper(this.greenCopper);
		extend.setBlueCopper(this.blueCopper);
		extend.setPurpleCopper(this.purpleCopper);
		extend.setGoldenCopper(this.goldenCopper);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
