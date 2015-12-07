package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class DictMineType implements Serializable
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
		矿主的类型 0.铁矿 1.铜矿 2.银矿 3.金矿
	*/
	private int mineType;
	public int getMineType(){
		return mineType;
	}
	public void setMineType(int mineType) {
		this.mineType = mineType;
		index = 2;
		result += index + "*int*" + mineType + "#";
	}

	public void setMineType(int mineType, int bs) {
		this.mineType = mineType;
	}

	/**
		矿的名称
	*/
	private String mineName;
	public String getMineName(){
		return mineName;
	}
	public void setMineName(String mineName) {
		this.mineName = mineName;
		index = 3;
		result += index + "*String*" + mineName + "#";
	}

	public void setMineName(String mineName, int bs) {
		this.mineName = mineName;
	}

	/**
		矿主消耗类型 0.耐力  1.元宝
	*/
	private int masterCsType;
	public int getMasterCsType(){
		return masterCsType;
	}
	public void setMasterCsType(int masterCsType) {
		this.masterCsType = masterCsType;
		index = 4;
		result += index + "*int*" + masterCsType + "#";
	}

	public void setMasterCsType(int masterCsType, int bs) {
		this.masterCsType = masterCsType;
	}

	/**
		矿主消耗数量
	*/
	private int masterCsValue;
	public int getMasterCsValue(){
		return masterCsValue;
	}
	public void setMasterCsValue(int masterCsValue) {
		this.masterCsValue = masterCsValue;
		index = 5;
		result += index + "*int*" + masterCsValue + "#";
	}

	public void setMasterCsValue(int masterCsValue, int bs) {
		this.masterCsValue = masterCsValue;
	}

	/**
		协助消耗类型 0.耐力 1.元宝
	*/
	private int slaveCsType;
	public int getSlaveCsType(){
		return slaveCsType;
	}
	public void setSlaveCsType(int slaveCsType) {
		this.slaveCsType = slaveCsType;
		index = 6;
		result += index + "*int*" + slaveCsType + "#";
	}

	public void setSlaveCsType(int slaveCsType, int bs) {
		this.slaveCsType = slaveCsType;
	}

	/**
		协助消耗数量
	*/
	private int slaveCsValue;
	public int getSlaveCsValue(){
		return slaveCsValue;
	}
	public void setSlaveCsValue(int slaveCsValue) {
		this.slaveCsValue = slaveCsValue;
		index = 7;
		result += index + "*int*" + slaveCsValue + "#";
	}

	public void setSlaveCsValue(int slaveCsValue, int bs) {
		this.slaveCsValue = slaveCsValue;
	}

	/**
		收益周期
	*/
	private int cycleTime;
	public int getCycleTime(){
		return cycleTime;
	}
	public void setCycleTime(int cycleTime) {
		this.cycleTime = cycleTime;
		index = 8;
		result += index + "*int*" + cycleTime + "#";
	}

	public void setCycleTime(int cycleTime, int bs) {
		this.cycleTime = cycleTime;
	}

	/**
		矿主收益银币数量
	*/
	private int masterCopperCount;
	public int getMasterCopperCount(){
		return masterCopperCount;
	}
	public void setMasterCopperCount(int masterCopperCount) {
		this.masterCopperCount = masterCopperCount;
		index = 9;
		result += index + "*int*" + masterCopperCount + "#";
	}

	public void setMasterCopperCount(int masterCopperCount, int bs) {
		this.masterCopperCount = masterCopperCount;
	}

	/**
		矿主收益进阶石数量
	*/
	private int masterThing83Count;
	public int getMasterThing83Count(){
		return masterThing83Count;
	}
	public void setMasterThing83Count(int masterThing83Count) {
		this.masterThing83Count = masterThing83Count;
		index = 10;
		result += index + "*int*" + masterThing83Count + "#";
	}

	public void setMasterThing83Count(int masterThing83Count, int bs) {
		this.masterThing83Count = masterThing83Count;
	}

	/**
		协助者收益银币数量
	*/
	private int slaveCopperCount;
	public int getSlaveCopperCount(){
		return slaveCopperCount;
	}
	public void setSlaveCopperCount(int slaveCopperCount) {
		this.slaveCopperCount = slaveCopperCount;
		index = 11;
		result += index + "*int*" + slaveCopperCount + "#";
	}

	public void setSlaveCopperCount(int slaveCopperCount, int bs) {
		this.slaveCopperCount = slaveCopperCount;
	}

	/**
		协助者收益进阶石数量
	*/
	private int slaveThing83Count;
	public int getSlaveThing83Count(){
		return slaveThing83Count;
	}
	public void setSlaveThing83Count(int slaveThing83Count) {
		this.slaveThing83Count = slaveThing83Count;
		index = 12;
		result += index + "*int*" + slaveThing83Count + "#";
	}

	public void setSlaveThing83Count(int slaveThing83Count, int bs) {
		this.slaveThing83Count = slaveThing83Count;
	}

	/**
		产出倍数
	*/
	private float multiple;
	public float getMultiple(){
		return multiple;
	}
	public void setMultiple(float multiple) {
		this.multiple = multiple;
		index = 13;
		result += index + "*float*" + multiple + "#";
	}

	public void setMultiple(float multiple, int bs) {
		this.multiple = multiple;
	}

	/**
		数据版本
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

	public DictMineType clone(){
		DictMineType extend=new DictMineType();
		extend.setId(this.id);
		extend.setMineType(this.mineType);
		extend.setMineName(this.mineName);
		extend.setMasterCsType(this.masterCsType);
		extend.setMasterCsValue(this.masterCsValue);
		extend.setSlaveCsType(this.slaveCsType);
		extend.setSlaveCsValue(this.slaveCsValue);
		extend.setCycleTime(this.cycleTime);
		extend.setMasterCopperCount(this.masterCopperCount);
		extend.setMasterThing83Count(this.masterThing83Count);
		extend.setSlaveCopperCount(this.slaveCopperCount);
		extend.setSlaveThing83Count(this.slaveThing83Count);
		extend.setMultiple(this.multiple);
		extend.setVersion(this.version);
		return extend;
	}
}
