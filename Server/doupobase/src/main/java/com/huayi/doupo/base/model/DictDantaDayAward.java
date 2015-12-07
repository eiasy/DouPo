package com.huayi.doupo.base.model;

import java.io.*;

/**
	丹塔每日奖励
*/
@SuppressWarnings("serial")
public class DictDantaDayAward implements Serializable
{
	private int index;
	public String result = "";
	/**
		
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
		名次下限
	*/
	private int rankSub;
	public int getRankSub(){
		return rankSub;
	}
	public void setRankSub(int rankSub) {
		this.rankSub = rankSub;
		index = 2;
		result += index + "*int*" + rankSub + "#";
	}

	public void setRankSub(int rankSub, int bs) {
		this.rankSub = rankSub;
	}

	/**
		名次上限
	*/
	private int rankSup;
	public int getRankSup(){
		return rankSup;
	}
	public void setRankSup(int rankSup) {
		this.rankSup = rankSup;
		index = 3;
		result += index + "*int*" + rankSup + "#";
	}

	public void setRankSup(int rankSup, int bs) {
		this.rankSup = rankSup;
	}

	/**
		奖励（type_id_count;）
	*/
	private String awards;
	public String getAwards(){
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
		index = 4;
		result += index + "*String*" + awards + "#";
	}

	public void setAwards(String awards, int bs) {
		this.awards = awards;
	}

	/**
		版本号
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

	public DictDantaDayAward clone(){
		DictDantaDayAward extend=new DictDantaDayAward();
		extend.setId(this.id);
		extend.setRankSub(this.rankSub);
		extend.setRankSup(this.rankSup);
		extend.setAwards(this.awards);
		extend.setVersion(this.version);
		return extend;
	}
}
