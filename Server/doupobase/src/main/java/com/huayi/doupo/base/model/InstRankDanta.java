package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class InstRankDanta implements Serializable
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
		玩家ID
	*/
	private int playerId;
	public int getPlayerId(){
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
		index = 2;
		result += index + "*int*" + playerId + "#";
	}

	public void setPlayerId(int playerId, int bs) {
		this.playerId = playerId;
	}

	/**
		
	*/
	private String playerName;
	public String getPlayerName(){
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		index = 3;
		result += index + "*String*" + playerName + "#";
	}

	public void setPlayerName(String playerName, int bs) {
		this.playerName = playerName;
	}

	/**
		等级
	*/
	private int playerLevel;
	public int getPlayerLevel(){
		return playerLevel;
	}
	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
		index = 4;
		result += index + "*int*" + playerLevel + "#";
	}

	public void setPlayerLevel(int playerLevel, int bs) {
		this.playerLevel = playerLevel;
	}

	/**
		名次
	*/
	private int rankIndex;
	public int getRankIndex(){
		return rankIndex;
	}
	public void setRankIndex(int rankIndex) {
		this.rankIndex = rankIndex;
		index = 5;
		result += index + "*int*" + rankIndex + "#";
	}

	public void setRankIndex(int rankIndex, int bs) {
		this.rankIndex = rankIndex;
	}

	/**
		最大图层
	*/
	private int maxLayer;
	public int getMaxLayer(){
		return maxLayer;
	}
	public void setMaxLayer(int maxLayer) {
		this.maxLayer = maxLayer;
		index = 6;
		result += index + "*int*" + maxLayer + "#";
	}

	public void setMaxLayer(int maxLayer, int bs) {
		this.maxLayer = maxLayer;
	}

	/**
		添加时间
	*/
	private String addTime;
	public String getAddTime(){
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
		index = 7;
		result += index + "*String*" + addTime + "#";
	}

	public void setAddTime(String addTime, int bs) {
		this.addTime = addTime;
	}

	/**
		更新时间
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 8;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	/**
		启动次数
	*/
	private int startCount;
	public int getStartCount(){
		return startCount;
	}
	public void setStartCount(int startCount) {
		this.startCount = startCount;
		index = 9;
		result += index + "*int*" + startCount + "#";
	}

	public void setStartCount(int startCount, int bs) {
		this.startCount = startCount;
	}

	/**
		通关次数
	*/
	private int finishCount;
	public int getFinishCount(){
		return finishCount;
	}
	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
		index = 10;
		result += index + "*int*" + finishCount + "#";
	}

	public void setFinishCount(int finishCount, int bs) {
		this.finishCount = finishCount;
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
		index = 11;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		获得的勋章数量
	*/
	private int medalCount;
	public int getMedalCount(){
		return medalCount;
	}
	public void setMedalCount(int medalCount) {
		this.medalCount = medalCount;
		index = 12;
		result += index + "*int*" + medalCount + "#";
	}

	public void setMedalCount(int medalCount, int bs) {
		this.medalCount = medalCount;
	}

	/**
		联盟ID
	*/
	private int instUnionId;
	public int getInstUnionId(){
		return instUnionId;
	}
	public void setInstUnionId(int instUnionId) {
		this.instUnionId = instUnionId;
		index = 13;
		result += index + "*int*" + instUnionId + "#";
	}

	public void setInstUnionId(int instUnionId, int bs) {
		this.instUnionId = instUnionId;
	}

	/**
		卡片头像ID
	*/
	private int headerCardId;
	public int getHeaderCardId(){
		return headerCardId;
	}
	public void setHeaderCardId(int headerCardId) {
		this.headerCardId = headerCardId;
		index = 14;
		result += index + "*int*" + headerCardId + "#";
	}

	public void setHeaderCardId(int headerCardId, int bs) {
		this.headerCardId = headerCardId;
	}

	public String getResult(){
		return result;
	}

	public InstRankDanta clone(){
		InstRankDanta extend=new InstRankDanta();
		extend.setId(this.id);
		extend.setPlayerId(this.playerId);
		extend.setPlayerName(this.playerName);
		extend.setPlayerLevel(this.playerLevel);
		extend.setRankIndex(this.rankIndex);
		extend.setMaxLayer(this.maxLayer);
		extend.setAddTime(this.addTime);
		extend.setUpdateTime(this.updateTime);
		extend.setStartCount(this.startCount);
		extend.setFinishCount(this.finishCount);
		extend.setVersion(this.version);
		extend.setMedalCount(this.medalCount);
		extend.setInstUnionId(this.instUnionId);
		extend.setHeaderCardId(this.headerCardId);
		return extend;
	}
}
