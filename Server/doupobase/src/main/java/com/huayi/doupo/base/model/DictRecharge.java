package com.huayi.doupo.base.model;

import java.io.*;

/**
	充值字典表
*/
@SuppressWarnings("serial")
public class DictRecharge implements Serializable
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
		rmb金额
	*/
	private int rmb;
	public int getRmb(){
		return rmb;
	}
	public void setRmb(int rmb) {
		this.rmb = rmb;
		index = 2;
		result += index + "*int*" + rmb + "#";
	}

	public void setRmb(int rmb, int bs) {
		this.rmb = rmb;
	}

	/**
		首次送多少元宝 1-月卡
	*/
	private int firstAmt;
	public int getFirstAmt(){
		return firstAmt;
	}
	public void setFirstAmt(int firstAmt) {
		this.firstAmt = firstAmt;
		index = 3;
		result += index + "*int*" + firstAmt + "#";
	}

	public void setFirstAmt(int firstAmt, int bs) {
		this.firstAmt = firstAmt;
	}

	/**
		首次送文字描述
	*/
	private String firstAmtDes;
	public String getFirstAmtDes(){
		return firstAmtDes;
	}
	public void setFirstAmtDes(String firstAmtDes) {
		this.firstAmtDes = firstAmtDes;
		index = 4;
		result += index + "*String*" + firstAmtDes + "#";
	}

	public void setFirstAmtDes(String firstAmtDes, int bs) {
		this.firstAmtDes = firstAmtDes;
	}

	/**
		非首次送多少元宝
	*/
	private int noFirstAmt;
	public int getNoFirstAmt(){
		return noFirstAmt;
	}
	public void setNoFirstAmt(int noFirstAmt) {
		this.noFirstAmt = noFirstAmt;
		index = 5;
		result += index + "*int*" + noFirstAmt + "#";
	}

	public void setNoFirstAmt(int noFirstAmt, int bs) {
		this.noFirstAmt = noFirstAmt;
	}

	/**
		非首次送文字描述
	*/
	private String noFirstAmtDes;
	public String getNoFirstAmtDes(){
		return noFirstAmtDes;
	}
	public void setNoFirstAmtDes(String noFirstAmtDes) {
		this.noFirstAmtDes = noFirstAmtDes;
		index = 6;
		result += index + "*String*" + noFirstAmtDes + "#";
	}

	public void setNoFirstAmtDes(String noFirstAmtDes, int bs) {
		this.noFirstAmtDes = noFirstAmtDes;
	}

	/**
		
	*/
	private int uiId;
	public int getUiId(){
		return uiId;
	}
	public void setUiId(int uiId) {
		this.uiId = uiId;
		index = 7;
		result += index + "*int*" + uiId + "#";
	}

	public void setUiId(int uiId, int bs) {
		this.uiId = uiId;
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

	public DictRecharge clone(){
		DictRecharge extend=new DictRecharge();
		extend.setId(this.id);
		extend.setRmb(this.rmb);
		extend.setFirstAmt(this.firstAmt);
		extend.setFirstAmtDes(this.firstAmtDes);
		extend.setNoFirstAmt(this.noFirstAmt);
		extend.setNoFirstAmtDes(this.noFirstAmtDes);
		extend.setUiId(this.uiId);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
