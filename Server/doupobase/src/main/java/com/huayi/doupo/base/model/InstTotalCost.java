package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class InstTotalCost implements Serializable
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
	private String startPoint;
	public String getStartPoint(){
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
		index = 3;
		result += index + "*String*" + startPoint + "#";
	}

	public void setStartPoint(String startPoint, int bs) {
		this.startPoint = startPoint;
	}

	/**
		
	*/
	private String stopPoint;
	public String getStopPoint(){
		return stopPoint;
	}
	public void setStopPoint(String stopPoint) {
		this.stopPoint = stopPoint;
		index = 4;
		result += index + "*String*" + stopPoint + "#";
	}

	public void setStopPoint(String stopPoint, int bs) {
		this.stopPoint = stopPoint;
	}

	/**
		
	*/
	private int costCount;
	public int getCostCount(){
		return costCount;
	}
	public void setCostCount(int costCount) {
		this.costCount = costCount;
		index = 5;
		result += index + "*int*" + costCount + "#";
	}

	public void setCostCount(int costCount, int bs) {
		this.costCount = costCount;
	}

	/**
		
	*/
	private String costReset;
	public String getCostReset(){
		return costReset;
	}
	public void setCostReset(String costReset) {
		this.costReset = costReset;
		index = 6;
		result += index + "*String*" + costReset + "#";
	}

	public void setCostReset(String costReset, int bs) {
		this.costReset = costReset;
	}

	/**
		
	*/
	private String amountState;
	public String getAmountState(){
		return amountState;
	}
	public void setAmountState(String amountState) {
		this.amountState = amountState;
		index = 7;
		result += index + "*String*" + amountState + "#";
	}

	public void setAmountState(String amountState, int bs) {
		this.amountState = amountState;
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

	public InstTotalCost clone(){
		InstTotalCost extend=new InstTotalCost();
		extend.setId(this.id);
		extend.setPlayerId(this.playerId);
		extend.setStartPoint(this.startPoint);
		extend.setStopPoint(this.stopPoint);
		extend.setCostCount(this.costCount);
		extend.setCostReset(this.costReset);
		extend.setAmountState(this.amountState);
		extend.setVersion(this.version);
		return extend;
	}
}
