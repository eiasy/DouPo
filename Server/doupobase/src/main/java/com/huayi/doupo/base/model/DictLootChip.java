package com.huayi.doupo.base.model;

import java.io.*;

/**
	碎片抢夺字典表
*/
@SuppressWarnings("serial")
public class DictLootChip implements Serializable
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
		类型 1-技能  2-功法 3-法宝
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 2;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		品质Id 跟随类型走
	*/
	private int qualityId;
	public int getQualityId(){
		return qualityId;
	}
	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
		index = 3;
		result += index + "*int*" + qualityId + "#";
	}

	public void setQualityId(int qualityId, int bs) {
		this.qualityId = qualityId;
	}

	/**
		抢夺玩家等级高概率
	*/
	private float lootPlayer;
	public float getLootPlayer(){
		return lootPlayer;
	}
	public void setLootPlayer(float lootPlayer) {
		this.lootPlayer = lootPlayer;
		index = 4;
		result += index + "*float*" + lootPlayer + "#";
	}

	public void setLootPlayer(float lootPlayer, int bs) {
		this.lootPlayer = lootPlayer;
	}

	/**
		抢夺玩家等级高概率描述 1-高
	*/
	private int lootPlayerDS;
	public int getLootPlayerDS(){
		return lootPlayerDS;
	}
	public void setLootPlayerDS(int lootPlayerDS) {
		this.lootPlayerDS = lootPlayerDS;
		index = 5;
		result += index + "*int*" + lootPlayerDS + "#";
	}

	public void setLootPlayerDS(int lootPlayerDS, int bs) {
		this.lootPlayerDS = lootPlayerDS;
	}

	/**
		抢夺玩家等级低概率
	*/
	private float lootPlayer2;
	public float getLootPlayer2(){
		return lootPlayer2;
	}
	public void setLootPlayer2(float lootPlayer2) {
		this.lootPlayer2 = lootPlayer2;
		index = 6;
		result += index + "*float*" + lootPlayer2 + "#";
	}

	public void setLootPlayer2(float lootPlayer2, int bs) {
		this.lootPlayer2 = lootPlayer2;
	}

	/**
		抢夺玩家等级低概率描述 2-较高
	*/
	private int lootPlayerDS2;
	public int getLootPlayerDS2(){
		return lootPlayerDS2;
	}
	public void setLootPlayerDS2(int lootPlayerDS2) {
		this.lootPlayerDS2 = lootPlayerDS2;
		index = 7;
		result += index + "*int*" + lootPlayerDS2 + "#";
	}

	public void setLootPlayerDS2(int lootPlayerDS2, int bs) {
		this.lootPlayerDS2 = lootPlayerDS2;
	}

	/**
		抢夺NPC概率
	*/
	private float lootNPC;
	public float getLootNPC(){
		return lootNPC;
	}
	public void setLootNPC(float lootNPC) {
		this.lootNPC = lootNPC;
		index = 8;
		result += index + "*float*" + lootNPC + "#";
	}

	public void setLootNPC(float lootNPC, int bs) {
		this.lootNPC = lootNPC;
	}

	/**
		抢夺NPC描述 3-一般 4-低 5-极低
	*/
	private int lootNPCDS;
	public int getLootNPCDS(){
		return lootNPCDS;
	}
	public void setLootNPCDS(int lootNPCDS) {
		this.lootNPCDS = lootNPCDS;
		index = 9;
		result += index + "*int*" + lootNPCDS + "#";
	}

	public void setLootNPCDS(int lootNPCDS, int bs) {
		this.lootNPCDS = lootNPCDS;
	}

	/**
		抢夺加成
	*/
	private float lootAdd;
	public float getLootAdd(){
		return lootAdd;
	}
	public void setLootAdd(float lootAdd) {
		this.lootAdd = lootAdd;
		index = 10;
		result += index + "*float*" + lootAdd + "#";
	}

	public void setLootAdd(float lootAdd, int bs) {
		this.lootAdd = lootAdd;
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
		index = 11;
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
		index = 12;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictLootChip clone(){
		DictLootChip extend=new DictLootChip();
		extend.setId(this.id);
		extend.setType(this.type);
		extend.setQualityId(this.qualityId);
		extend.setLootPlayer(this.lootPlayer);
		extend.setLootPlayerDS(this.lootPlayerDS);
		extend.setLootPlayer2(this.lootPlayer2);
		extend.setLootPlayerDS2(this.lootPlayerDS2);
		extend.setLootNPC(this.lootNPC);
		extend.setLootNPCDS(this.lootNPCDS);
		extend.setLootAdd(this.lootAdd);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
