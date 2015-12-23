package com.huayi.doupo.base.model;

import java.io.*;

/**
	买团购箱子给字规则字典表
*/
@SuppressWarnings("serial")
public class DictActivityGroupGiveZiRule implements Serializable
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
		开始购买个数
	*/
	private int startBuyNum;
	public int getStartBuyNum(){
		return startBuyNum;
	}
	public void setStartBuyNum(int startBuyNum) {
		this.startBuyNum = startBuyNum;
		index = 2;
		result += index + "*int*" + startBuyNum + "#";
	}

	public void setStartBuyNum(int startBuyNum, int bs) {
		this.startBuyNum = startBuyNum;
	}

	/**
		结束购买个数
	*/
	private int endBuyNum;
	public int getEndBuyNum(){
		return endBuyNum;
	}
	public void setEndBuyNum(int endBuyNum) {
		this.endBuyNum = endBuyNum;
		index = 3;
		result += index + "*int*" + endBuyNum + "#";
	}

	public void setEndBuyNum(int endBuyNum, int bs) {
		this.endBuyNum = endBuyNum;
	}

	/**
		必送的字   Dict_Gener_Box的Id
	*/
	private int generBoxId;
	public int getGenerBoxId(){
		return generBoxId;
	}
	public void setGenerBoxId(int generBoxId) {
		this.generBoxId = generBoxId;
		index = 4;
		result += index + "*int*" + generBoxId + "#";
	}

	public void setGenerBoxId(int generBoxId, int bs) {
		this.generBoxId = generBoxId;
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

	public DictActivityGroupGiveZiRule clone(){
		DictActivityGroupGiveZiRule extend=new DictActivityGroupGiveZiRule();
		extend.setId(this.id);
		extend.setStartBuyNum(this.startBuyNum);
		extend.setEndBuyNum(this.endBuyNum);
		extend.setGenerBoxId(this.generBoxId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
