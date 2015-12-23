package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class DictUnionWarBattlefield implements Serializable
{
	private int index;
	public String result = "";
	/**
		id
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
		战场积分
	*/
	private int score;
	public int getScore(){
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		index = 2;
		result += index + "*int*" + score + "#";
	}

	public void setScore(int score, int bs) {
		this.score = score;
	}

	/**
		生存积分
	*/
	private int aliveScore;
	public int getAliveScore(){
		return aliveScore;
	}
	public void setAliveScore(int aliveScore) {
		this.aliveScore = aliveScore;
		index = 3;
		result += index + "*int*" + aliveScore + "#";
	}

	public void setAliveScore(int aliveScore, int bs) {
		this.aliveScore = aliveScore;
	}

	/**
		杀人积分
	*/
	private int killScore;
	public int getKillScore(){
		return killScore;
	}
	public void setKillScore(int killScore) {
		this.killScore = killScore;
		index = 4;
		result += index + "*int*" + killScore + "#";
	}

	public void setKillScore(int killScore, int bs) {
		this.killScore = killScore;
	}

	/**
		非道具奖励
	*/
	private String reward;
	public String getReward(){
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
		index = 5;
		result += index + "*String*" + reward + "#";
	}

	public void setReward(String reward, int bs) {
		this.reward = reward;
	}

	/**
		奖励
	*/
	private String description;
	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		index = 6;
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
		index = 7;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictUnionWarBattlefield clone(){
		DictUnionWarBattlefield extend=new DictUnionWarBattlefield();
		extend.setId(this.id);
		extend.setScore(this.score);
		extend.setAliveScore(this.aliveScore);
		extend.setKillScore(this.killScore);
		extend.setReward(this.reward);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
