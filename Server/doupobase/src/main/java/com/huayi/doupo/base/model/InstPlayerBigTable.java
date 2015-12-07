package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家BigTable实例表
*/
@SuppressWarnings("serial")
public class InstPlayerBigTable implements Serializable
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
		玩家Id
	*/
	private int instPlayerId;
	public int getInstPlayerId(){
		return instPlayerId;
	}
	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
		index = 2;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
	}

	/**
		玩家属性
	*/
	private String properties;
	public String getProperties(){
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
		index = 3;
		result += index + "*String*" + properties + "#";
	}

	public void setProperties(String properties, int bs) {
		this.properties = properties;
	}

	/**
		属性相关值1
	*/
	private String value1;
	public String getValue1(){
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
		index = 4;
		result += index + "*String*" + value1 + "#";
	}

	public void setValue1(String value1, int bs) {
		this.value1 = value1;
	}

	/**
		属性相关值2
	*/
	private String value2;
	public String getValue2(){
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
		index = 5;
		result += index + "*String*" + value2 + "#";
	}

	public void setValue2(String value2, int bs) {
		this.value2 = value2;
	}

	/**
		属性相关值3
	*/
	private String value3;
	public String getValue3(){
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
		index = 6;
		result += index + "*String*" + value3 + "#";
	}

	public void setValue3(String value3, int bs) {
		this.value3 = value3;
	}

	/**
		属性相关值4
	*/
	private String value4;
	public String getValue4(){
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
		index = 7;
		result += index + "*String*" + value4 + "#";
	}

	public void setValue4(String value4, int bs) {
		this.value4 = value4;
	}

	/**
		属性相关值5
	*/
	private String value5;
	public String getValue5(){
		return value5;
	}
	public void setValue5(String value5) {
		this.value5 = value5;
		index = 8;
		result += index + "*String*" + value5 + "#";
	}

	public void setValue5(String value5, int bs) {
		this.value5 = value5;
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

	/**
		
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 10;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
	}

	/**
		
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 11;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerBigTable clone(){
		InstPlayerBigTable extend=new InstPlayerBigTable();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setProperties(this.properties);
		extend.setValue1(this.value1);
		extend.setValue2(this.value2);
		extend.setValue3(this.value3);
		extend.setValue4(this.value4);
		extend.setValue5(this.value5);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
