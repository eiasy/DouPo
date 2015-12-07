package com.huayi.doupo.base.model;

import java.io.*;

/**
	美人经验字典表
*/
@SuppressWarnings("serial")
public class DictBeautyCardExp implements Serializable
{
	private int index;
	public String result = "";
	/**
		编号 等级
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
		极低值
	*/
	private int veryLow;
	public int getVeryLow(){
		return veryLow;
	}
	public void setVeryLow(int veryLow) {
		this.veryLow = veryLow;
		index = 3;
		result += index + "*int*" + veryLow + "#";
	}

	public void setVeryLow(int veryLow, int bs) {
		this.veryLow = veryLow;
	}

	/**
		极低初始概率
	*/
	private float veryLowPr;
	public float getVeryLowPr(){
		return veryLowPr;
	}
	public void setVeryLowPr(float veryLowPr) {
		this.veryLowPr = veryLowPr;
		index = 4;
		result += index + "*float*" + veryLowPr + "#";
	}

	public void setVeryLowPr(float veryLowPr, int bs) {
		this.veryLowPr = veryLowPr;
	}

	/**
		极低概率加成
	*/
	private float veryLowPrAdd;
	public float getVeryLowPrAdd(){
		return veryLowPrAdd;
	}
	public void setVeryLowPrAdd(float veryLowPrAdd) {
		this.veryLowPrAdd = veryLowPrAdd;
		index = 5;
		result += index + "*float*" + veryLowPrAdd + "#";
	}

	public void setVeryLowPrAdd(float veryLowPrAdd, int bs) {
		this.veryLowPrAdd = veryLowPrAdd;
	}

	/**
		低值
	*/
	private float low;
	public float getLow(){
		return low;
	}
	public void setLow(float low) {
		this.low = low;
		index = 6;
		result += index + "*float*" + low + "#";
	}

	public void setLow(float low, int bs) {
		this.low = low;
	}

	/**
		低初始概率
	*/
	private float lowPr;
	public float getLowPr(){
		return lowPr;
	}
	public void setLowPr(float lowPr) {
		this.lowPr = lowPr;
		index = 7;
		result += index + "*float*" + lowPr + "#";
	}

	public void setLowPr(float lowPr, int bs) {
		this.lowPr = lowPr;
	}

	/**
		低概率加成
	*/
	private float lowPrAdd;
	public float getLowPrAdd(){
		return lowPrAdd;
	}
	public void setLowPrAdd(float lowPrAdd) {
		this.lowPrAdd = lowPrAdd;
		index = 8;
		result += index + "*float*" + lowPrAdd + "#";
	}

	public void setLowPrAdd(float lowPrAdd, int bs) {
		this.lowPrAdd = lowPrAdd;
	}

	/**
		中值
	*/
	private float centre;
	public float getCentre(){
		return centre;
	}
	public void setCentre(float centre) {
		this.centre = centre;
		index = 9;
		result += index + "*float*" + centre + "#";
	}

	public void setCentre(float centre, int bs) {
		this.centre = centre;
	}

	/**
		中初始概率
	*/
	private float centrePr;
	public float getCentrePr(){
		return centrePr;
	}
	public void setCentrePr(float centrePr) {
		this.centrePr = centrePr;
		index = 10;
		result += index + "*float*" + centrePr + "#";
	}

	public void setCentrePr(float centrePr, int bs) {
		this.centrePr = centrePr;
	}

	/**
		中概率加成
	*/
	private float centrePrAdd;
	public float getCentrePrAdd(){
		return centrePrAdd;
	}
	public void setCentrePrAdd(float centrePrAdd) {
		this.centrePrAdd = centrePrAdd;
		index = 11;
		result += index + "*float*" + centrePrAdd + "#";
	}

	public void setCentrePrAdd(float centrePrAdd, int bs) {
		this.centrePrAdd = centrePrAdd;
	}

	/**
		高值
	*/
	private float tall;
	public float getTall(){
		return tall;
	}
	public void setTall(float tall) {
		this.tall = tall;
		index = 12;
		result += index + "*float*" + tall + "#";
	}

	public void setTall(float tall, int bs) {
		this.tall = tall;
	}

	/**
		高初始概率
	*/
	private float tallPr;
	public float getTallPr(){
		return tallPr;
	}
	public void setTallPr(float tallPr) {
		this.tallPr = tallPr;
		index = 13;
		result += index + "*float*" + tallPr + "#";
	}

	public void setTallPr(float tallPr, int bs) {
		this.tallPr = tallPr;
	}

	/**
		高概率加成
	*/
	private float tallPrAdd;
	public float getTallPrAdd(){
		return tallPrAdd;
	}
	public void setTallPrAdd(float tallPrAdd) {
		this.tallPrAdd = tallPrAdd;
		index = 14;
		result += index + "*float*" + tallPrAdd + "#";
	}

	public void setTallPrAdd(float tallPrAdd, int bs) {
		this.tallPrAdd = tallPrAdd;
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
		index = 15;
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
		index = 16;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictBeautyCardExp clone(){
		DictBeautyCardExp extend=new DictBeautyCardExp();
		extend.setId(this.id);
		extend.setExp(this.exp);
		extend.setVeryLow(this.veryLow);
		extend.setVeryLowPr(this.veryLowPr);
		extend.setVeryLowPrAdd(this.veryLowPrAdd);
		extend.setLow(this.low);
		extend.setLowPr(this.lowPr);
		extend.setLowPrAdd(this.lowPrAdd);
		extend.setCentre(this.centre);
		extend.setCentrePr(this.centrePr);
		extend.setCentrePrAdd(this.centrePrAdd);
		extend.setTall(this.tall);
		extend.setTallPr(this.tallPr);
		extend.setTallPrAdd(this.tallPrAdd);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
