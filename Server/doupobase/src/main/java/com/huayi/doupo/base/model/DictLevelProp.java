package com.huayi.doupo.base.model;

import java.io.*;

/**
	战队等级属性字典
*/
@SuppressWarnings("serial")
public class DictLevelProp implements Serializable
{
	private int index;
	public String result = "";
	/**
		编号 战队等级
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
		战队经验和
	*/
	private int fleetExp;
	public int getFleetExp(){
		return fleetExp;
	}
	public void setFleetExp(int fleetExp) {
		this.fleetExp = fleetExp;
		index = 2;
		result += index + "*int*" + fleetExp + "#";
	}

	public void setFleetExp(int fleetExp, int bs) {
		this.fleetExp = fleetExp;
	}

	/**
		达到该等级获得的元宝
	*/
	private int gold;
	public int getGold(){
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
		index = 3;
		result += index + "*int*" + gold + "#";
	}

	public void setGold(int gold, int bs) {
		this.gold = gold;
	}

	/**
		达到该等级获得的金币
	*/
	private int copper;
	public int getCopper(){
		return copper;
	}
	public void setCopper(int copper) {
		this.copper = copper;
		index = 4;
		result += index + "*int*" + copper + "#";
	}

	public void setCopper(int copper, int bs) {
		this.copper = copper;
	}

	/**
		达到该等级获得的体力
	*/
	private int energy;
	public int getEnergy(){
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
		index = 5;
		result += index + "*int*" + energy + "#";
	}

	public void setEnergy(int energy, int bs) {
		this.energy = energy;
	}

	/**
		达到该等级获得的耐力
	*/
	private int vigor;
	public int getVigor(){
		return vigor;
	}
	public void setVigor(int vigor) {
		this.vigor = vigor;
		index = 6;
		result += index + "*int*" + vigor + "#";
	}

	public void setVigor(int vigor, int bs) {
		this.vigor = vigor;
	}

	/**
		达到该等级上阵卡牌数
	*/
	private int inTeamCard;
	public int getInTeamCard(){
		return inTeamCard;
	}
	public void setInTeamCard(int inTeamCard) {
		this.inTeamCard = inTeamCard;
		index = 7;
		result += index + "*int*" + inTeamCard + "#";
	}

	public void setInTeamCard(int inTeamCard, int bs) {
		this.inTeamCard = inTeamCard;
	}

	/**
		达到该等级替补卡牌数
	*/
	private int benchCard;
	public int getBenchCard(){
		return benchCard;
	}
	public void setBenchCard(int benchCard) {
		this.benchCard = benchCard;
		index = 8;
		result += index + "*int*" + benchCard + "#";
	}

	public void setBenchCard(int benchCard, int bs) {
		this.benchCard = benchCard;
	}

	/**
		达到该等级伙伴卡牌数
	*/
	private int partnerCard;
	public int getPartnerCard(){
		return partnerCard;
	}
	public void setPartnerCard(int partnerCard) {
		this.partnerCard = partnerCard;
		index = 9;
		result += index + "*int*" + partnerCard + "#";
	}

	public void setPartnerCard(int partnerCard, int bs) {
		this.partnerCard = partnerCard;
	}

	/**
		单次副本获得经验
	*/
	private int oneWarExp;
	public int getOneWarExp(){
		return oneWarExp;
	}
	public void setOneWarExp(int oneWarExp) {
		this.oneWarExp = oneWarExp;
		index = 10;
		result += index + "*int*" + oneWarExp + "#";
	}

	public void setOneWarExp(int oneWarExp, int bs) {
		this.oneWarExp = oneWarExp;
	}

	/**
		单次决斗获得经验
	*/
	private int duelFleetExp;
	public int getDuelFleetExp(){
		return duelFleetExp;
	}
	public void setDuelFleetExp(int duelFleetExp) {
		this.duelFleetExp = duelFleetExp;
		index = 11;
		result += index + "*int*" + duelFleetExp + "#";
	}

	public void setDuelFleetExp(int duelFleetExp, int bs) {
		this.duelFleetExp = duelFleetExp;
	}

	/**
		单次决斗获得银币
	*/
	private int duelFleetCopper;
	public int getDuelFleetCopper(){
		return duelFleetCopper;
	}
	public void setDuelFleetCopper(int duelFleetCopper) {
		this.duelFleetCopper = duelFleetCopper;
		index = 12;
		result += index + "*int*" + duelFleetCopper + "#";
	}

	public void setDuelFleetCopper(int duelFleetCopper, int bs) {
		this.duelFleetCopper = duelFleetCopper;
	}

	/**
		单次抢夺获得经验
	*/
	private int lootFleetExp;
	public int getLootFleetExp(){
		return lootFleetExp;
	}
	public void setLootFleetExp(int lootFleetExp) {
		this.lootFleetExp = lootFleetExp;
		index = 13;
		result += index + "*int*" + lootFleetExp + "#";
	}

	public void setLootFleetExp(int lootFleetExp, int bs) {
		this.lootFleetExp = lootFleetExp;
	}

	/**
		单次抢夺获得银币
	*/
	private int lootFleetCopper;
	public int getLootFleetCopper(){
		return lootFleetCopper;
	}
	public void setLootFleetCopper(int lootFleetCopper) {
		this.lootFleetCopper = lootFleetCopper;
		index = 14;
		result += index + "*int*" + lootFleetCopper + "#";
	}

	public void setLootFleetCopper(int lootFleetCopper, int bs) {
		this.lootFleetCopper = lootFleetCopper;
	}

	/**
		单次精英副本获得经验
	*/
	private int oneEliteWarExp;
	public int getOneEliteWarExp(){
		return oneEliteWarExp;
	}
	public void setOneEliteWarExp(int oneEliteWarExp) {
		this.oneEliteWarExp = oneEliteWarExp;
		index = 15;
		result += index + "*int*" + oneEliteWarExp + "#";
	}

	public void setOneEliteWarExp(int oneEliteWarExp, int bs) {
		this.oneEliteWarExp = oneEliteWarExp;
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
		index = 16;
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
		index = 17;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictLevelProp clone(){
		DictLevelProp extend=new DictLevelProp();
		extend.setId(this.id);
		extend.setFleetExp(this.fleetExp);
		extend.setGold(this.gold);
		extend.setCopper(this.copper);
		extend.setEnergy(this.energy);
		extend.setVigor(this.vigor);
		extend.setInTeamCard(this.inTeamCard);
		extend.setBenchCard(this.benchCard);
		extend.setPartnerCard(this.partnerCard);
		extend.setOneWarExp(this.oneWarExp);
		extend.setDuelFleetExp(this.duelFleetExp);
		extend.setDuelFleetCopper(this.duelFleetCopper);
		extend.setLootFleetExp(this.lootFleetExp);
		extend.setLootFleetCopper(this.lootFleetCopper);
		extend.setOneEliteWarExp(this.oneEliteWarExp);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
