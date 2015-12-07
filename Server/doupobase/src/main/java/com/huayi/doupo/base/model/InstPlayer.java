package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家实例表
*/
@SuppressWarnings("serial")
public class InstPlayer implements Serializable
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
		账号Id
	*/
	private String openId;
	public String getOpenId(){
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
		index = 2;
		result += index + "*String*" + openId + "#";
	}

	public void setOpenId(String openId, int bs) {
		this.openId = openId;
	}

	/**
		名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 3;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		等级
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 4;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		元宝
	*/
	private int gold;
	public int getGold(){
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
		index = 5;
		result += index + "*int*" + gold + "#";
	}

	public void setGold(int gold, int bs) {
		this.gold = gold;
	}

	/**
		铜钱
	*/
	private String copper;
	public String getCopper(){
		return copper;
	}
	public void setCopper(String copper) {
		this.copper = copper;
		index = 6;
		result += index + "*String*" + copper + "#";
	}

	public void setCopper(String copper, int bs) {
		this.copper = copper;
	}

	/**
		经验
	*/
	private int exp;
	public int getExp(){
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
		index = 7;
		result += index + "*int*" + exp + "#";
	}

	public void setExp(int exp, int bs) {
		this.exp = exp;
	}

	/**
		体力
	*/
	private int energy;
	public int getEnergy(){
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
		index = 8;
		result += index + "*int*" + energy + "#";
	}

	public void setEnergy(int energy, int bs) {
		this.energy = energy;
	}

	/**
		最大体力
	*/
	private int maxEnergy;
	public int getMaxEnergy(){
		return maxEnergy;
	}
	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
		index = 9;
		result += index + "*int*" + maxEnergy + "#";
	}

	public void setMaxEnergy(int maxEnergy, int bs) {
		this.maxEnergy = maxEnergy;
	}

	/**
		耐力
	*/
	private int vigor;
	public int getVigor(){
		return vigor;
	}
	public void setVigor(int vigor) {
		this.vigor = vigor;
		index = 10;
		result += index + "*int*" + vigor + "#";
	}

	public void setVigor(int vigor, int bs) {
		this.vigor = vigor;
	}

	/**
		最大耐力
	*/
	private int maxVigor;
	public int getMaxVigor(){
		return maxVigor;
	}
	public void setMaxVigor(int maxVigor) {
		this.maxVigor = maxVigor;
		index = 11;
		result += index + "*int*" + maxVigor + "#";
	}

	public void setMaxVigor(int maxVigor, int bs) {
		this.maxVigor = maxVigor;
	}

	/**
		可上阵卡牌数
	*/
	private int cardNum;
	public int getCardNum(){
		return cardNum;
	}
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
		index = 12;
		result += index + "*int*" + cardNum + "#";
	}

	public void setCardNum(int cardNum, int bs) {
		this.cardNum = cardNum;
	}

	/**
		最大上阵卡牌数
	*/
	private int maxCardNum;
	public int getMaxCardNum(){
		return maxCardNum;
	}
	public void setMaxCardNum(int maxCardNum) {
		this.maxCardNum = maxCardNum;
		index = 13;
		result += index + "*int*" + maxCardNum + "#";
	}

	public void setMaxCardNum(int maxCardNum, int bs) {
		this.maxCardNum = maxCardNum;
	}

	/**
		引导步骤 内容格式为：level_step&level_step;
	*/
	private String guidStep;
	public String getGuidStep(){
		return guidStep;
	}
	public void setGuidStep(String guidStep) {
		this.guidStep = guidStep;
		index = 14;
		result += index + "*String*" + guidStep + "#";
	}

	public void setGuidStep(String guidStep, int bs) {
		this.guidStep = guidStep;
	}

	/**
		章节Id
	*/
	private int chapterId;
	public int getChapterId(){
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
		index = 15;
		result += index + "*int*" + chapterId + "#";
	}

	public void setChapterId(int chapterId, int bs) {
		this.chapterId = chapterId;
	}

	/**
		关卡Id
	*/
	private int barrierId;
	public int getBarrierId(){
		return barrierId;
	}
	public void setBarrierId(int barrierId) {
		this.barrierId = barrierId;
		index = 16;
		result += index + "*int*" + barrierId + "#";
	}

	public void setBarrierId(int barrierId, int bs) {
		this.barrierId = barrierId;
	}

	/**
		异火抓取规则Id
	*/
	private int fireGainRuleId;
	public int getFireGainRuleId(){
		return fireGainRuleId;
	}
	public void setFireGainRuleId(int fireGainRuleId) {
		this.fireGainRuleId = fireGainRuleId;
		index = 17;
		result += index + "*int*" + fireGainRuleId + "#";
	}

	public void setFireGainRuleId(int fireGainRuleId, int bs) {
		this.fireGainRuleId = fireGainRuleId;
	}

	/**
		玩家异火实例Id  0-未装备
	*/
	private int instPlayerFireId;
	public int getInstPlayerFireId(){
		return instPlayerFireId;
	}
	public void setInstPlayerFireId(int instPlayerFireId) {
		this.instPlayerFireId = instPlayerFireId;
		index = 18;
		result += index + "*int*" + instPlayerFireId + "#";
	}

	public void setInstPlayerFireId(int instPlayerFireId, int bs) {
		this.instPlayerFireId = instPlayerFireId;
	}

	/**
		vip等级
	*/
	private int vipLevel;
	public int getVipLevel(){
		return vipLevel;
	}
	public void setVipLevel(int vipLevel) {
		this.vipLevel = vipLevel;
		index = 19;
		result += index + "*int*" + vipLevel + "#";
	}

	public void setVipLevel(int vipLevel, int bs) {
		this.vipLevel = vipLevel;
	}

	/**
		元气（该字段目前没用）
	*/
	private int vigour;
	public int getVigour(){
		return vigour;
	}
	public void setVigour(int vigour) {
		this.vigour = vigour;
		index = 20;
		result += index + "*int*" + vigour + "#";
	}

	public void setVigour(int vigour, int bs) {
		this.vigour = vigour;
	}

	/**
		火能（由之前的修为改为火能）
	*/
	private int culture;
	public int getCulture(){
		return culture;
	}
	public void setCulture(int culture) {
		this.culture = culture;
		index = 21;
		result += index + "*int*" + culture + "#";
	}

	public void setCulture(int culture, int bs) {
		this.culture = culture;
	}

	/**
		两个含义：1-首次破满/使用时间 2-上一次体力恢复时间
	*/
	private String lastEnergyRecoverTime;
	public String getLastEnergyRecoverTime(){
		return lastEnergyRecoverTime;
	}
	public void setLastEnergyRecoverTime(String lastEnergyRecoverTime) {
		this.lastEnergyRecoverTime = lastEnergyRecoverTime;
		index = 22;
		result += index + "*String*" + lastEnergyRecoverTime + "#";
	}

	public void setLastEnergyRecoverTime(String lastEnergyRecoverTime, int bs) {
		this.lastEnergyRecoverTime = lastEnergyRecoverTime;
	}

	/**
		两个含义：1-首次破满/使用时间 2-上一次耐力恢复时间
	*/
	private String lastVigorRecoverTime;
	public String getLastVigorRecoverTime(){
		return lastVigorRecoverTime;
	}
	public void setLastVigorRecoverTime(String lastVigorRecoverTime) {
		this.lastVigorRecoverTime = lastVigorRecoverTime;
		index = 23;
		result += index + "*String*" + lastVigorRecoverTime + "#";
	}

	public void setLastVigorRecoverTime(String lastVigorRecoverTime, int bs) {
		this.lastVigorRecoverTime = lastVigorRecoverTime;
	}

	/**
		副本战斗胜利次数（用于控制拍卖行开启）
	*/
	private int barrierNum;
	public int getBarrierNum(){
		return barrierNum;
	}
	public void setBarrierNum(int barrierNum) {
		this.barrierNum = barrierNum;
		index = 24;
		result += index + "*int*" + barrierNum + "#";
	}

	public void setBarrierNum(int barrierNum, int bs) {
		this.barrierNum = barrierNum;
	}

	/**
		当日购买体力的次数
	*/
	private int buyEnergyNum;
	public int getBuyEnergyNum(){
		return buyEnergyNum;
	}
	public void setBuyEnergyNum(int buyEnergyNum) {
		this.buyEnergyNum = buyEnergyNum;
		index = 25;
		result += index + "*int*" + buyEnergyNum + "#";
	}

	public void setBuyEnergyNum(int buyEnergyNum, int bs) {
		this.buyEnergyNum = buyEnergyNum;
	}

	/**
		当日购买耐力的次数
	*/
	private int buyVigorNum;
	public int getBuyVigorNum(){
		return buyVigorNum;
	}
	public void setBuyVigorNum(int buyVigorNum) {
		this.buyVigorNum = buyVigorNum;
		index = 26;
		result += index + "*int*" + buyVigorNum + "#";
	}

	public void setBuyVigorNum(int buyVigorNum, int bs) {
		this.buyVigorNum = buyVigorNum;
	}

	/**
		最后一次购买体力的时间
	*/
	private String lastBuyEnergyTime;
	public String getLastBuyEnergyTime(){
		return lastBuyEnergyTime;
	}
	public void setLastBuyEnergyTime(String lastBuyEnergyTime) {
		this.lastBuyEnergyTime = lastBuyEnergyTime;
		index = 27;
		result += index + "*String*" + lastBuyEnergyTime + "#";
	}

	public void setLastBuyEnergyTime(String lastBuyEnergyTime, int bs) {
		this.lastBuyEnergyTime = lastBuyEnergyTime;
	}

	/**
		最后一次购买耐力的时间
	*/
	private String lastBuyVigorTime;
	public String getLastBuyVigorTime(){
		return lastBuyVigorTime;
	}
	public void setLastBuyVigorTime(String lastBuyVigorTime) {
		this.lastBuyVigorTime = lastBuyVigorTime;
		index = 28;
		result += index + "*String*" + lastBuyVigorTime + "#";
	}

	public void setLastBuyVigorTime(String lastBuyVigorTime, int bs) {
		this.lastBuyVigorTime = lastBuyVigorTime;
	}

	/**
		关卡引导步骤 内容格式为：barrierId#step&barrierId#step;barrierId#step;
	*/
	private String guipedBarrier;
	public String getGuipedBarrier(){
		return guipedBarrier;
	}
	public void setGuipedBarrier(String guipedBarrier) {
		this.guipedBarrier = guipedBarrier;
		index = 29;
		result += index + "*String*" + guipedBarrier + "#";
	}

	public void setGuipedBarrier(String guipedBarrier, int bs) {
		this.guipedBarrier = guipedBarrier;
	}

	/**
		洗澡恢复体力时间
	*/
	private String washTime;
	public String getWashTime(){
		return washTime;
	}
	public void setWashTime(String washTime) {
		this.washTime = washTime;
		index = 30;
		result += index + "*String*" + washTime + "#";
	}

	public void setWashTime(String washTime, int bs) {
		this.washTime = washTime;
	}

	/**
		每日任务时间
	*/
	private String dailyTashTime;
	public String getDailyTashTime(){
		return dailyTashTime;
	}
	public void setDailyTashTime(String dailyTashTime) {
		this.dailyTashTime = dailyTashTime;
		index = 31;
		result += index + "*String*" + dailyTashTime + "#";
	}

	public void setDailyTashTime(String dailyTashTime, int bs) {
		this.dailyTashTime = dailyTashTime;
	}

	/**
		头像卡牌Id
	*/
	private int headerCardId;
	public int getHeaderCardId(){
		return headerCardId;
	}
	public void setHeaderCardId(int headerCardId) {
		this.headerCardId = headerCardId;
		index = 32;
		result += index + "*int*" + headerCardId + "#";
	}

	public void setHeaderCardId(int headerCardId, int bs) {
		this.headerCardId = headerCardId;
	}

	/**
		用于存储已领取的vipId礼包
	*/
	private String vipIds;
	public String getVipIds(){
		return vipIds;
	}
	public void setVipIds(String vipIds) {
		this.vipIds = vipIds;
		index = 33;
		result += index + "*String*" + vipIds + "#";
	}

	public void setVipIds(String vipIds, int bs) {
		this.vipIds = vipIds;
	}

	/**
		拉黑 1-拉黑
	*/
	private int pullBlack;
	public int getPullBlack(){
		return pullBlack;
	}
	public void setPullBlack(int pullBlack) {
		this.pullBlack = pullBlack;
		index = 34;
		result += index + "*int*" + pullBlack + "#";
	}

	public void setPullBlack(int pullBlack, int bs) {
		this.pullBlack = pullBlack;
	}

	/**
		是否已领首充礼包 0-未领取 1-已领取
	*/
	private int isGetFirstRechargeGift;
	public int getIsGetFirstRechargeGift(){
		return isGetFirstRechargeGift;
	}
	public void setIsGetFirstRechargeGift(int isGetFirstRechargeGift) {
		this.isGetFirstRechargeGift = isGetFirstRechargeGift;
		index = 35;
		result += index + "*int*" + isGetFirstRechargeGift + "#";
	}

	public void setIsGetFirstRechargeGift(int isGetFirstRechargeGift, int bs) {
		this.isGetFirstRechargeGift = isGetFirstRechargeGift;
	}

	/**
		美人缠绵时间
	*/
	private String beautyCardTime;
	public String getBeautyCardTime(){
		return beautyCardTime;
	}
	public void setBeautyCardTime(String beautyCardTime) {
		this.beautyCardTime = beautyCardTime;
		index = 36;
		result += index + "*String*" + beautyCardTime + "#";
	}

	public void setBeautyCardTime(String beautyCardTime, int bs) {
		this.beautyCardTime = beautyCardTime;
	}

	/**
		
	*/
	private int serverId;
	public int getServerId(){
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
		index = 37;
		result += index + "*int*" + serverId + "#";
	}

	public void setServerId(int serverId, int bs) {
		this.serverId = serverId;
	}

	/**
		
	*/
	private String channel;
	public String getChannel(){
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
		index = 38;
		result += index + "*String*" + channel + "#";
	}

	public void setChannel(String channel, int bs) {
		this.channel = channel;
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
		index = 39;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		添加时间
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 40;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
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
		index = 41;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayer clone(){
		InstPlayer extend=new InstPlayer();
		extend.setId(this.id);
		extend.setOpenId(this.openId);
		extend.setName(this.name);
		extend.setLevel(this.level);
		extend.setGold(this.gold);
		extend.setCopper(this.copper);
		extend.setExp(this.exp);
		extend.setEnergy(this.energy);
		extend.setMaxEnergy(this.maxEnergy);
		extend.setVigor(this.vigor);
		extend.setMaxVigor(this.maxVigor);
		extend.setCardNum(this.cardNum);
		extend.setMaxCardNum(this.maxCardNum);
		extend.setGuidStep(this.guidStep);
		extend.setChapterId(this.chapterId);
		extend.setBarrierId(this.barrierId);
		extend.setFireGainRuleId(this.fireGainRuleId);
		extend.setInstPlayerFireId(this.instPlayerFireId);
		extend.setVipLevel(this.vipLevel);
		extend.setVigour(this.vigour);
		extend.setCulture(this.culture);
		extend.setLastEnergyRecoverTime(this.lastEnergyRecoverTime);
		extend.setLastVigorRecoverTime(this.lastVigorRecoverTime);
		extend.setBarrierNum(this.barrierNum);
		extend.setBuyEnergyNum(this.buyEnergyNum);
		extend.setBuyVigorNum(this.buyVigorNum);
		extend.setLastBuyEnergyTime(this.lastBuyEnergyTime);
		extend.setLastBuyVigorTime(this.lastBuyVigorTime);
		extend.setGuipedBarrier(this.guipedBarrier);
		extend.setWashTime(this.washTime);
		extend.setDailyTashTime(this.dailyTashTime);
		extend.setHeaderCardId(this.headerCardId);
		extend.setVipIds(this.vipIds);
		extend.setPullBlack(this.pullBlack);
		extend.setIsGetFirstRechargeGift(this.isGetFirstRechargeGift);
		extend.setBeautyCardTime(this.beautyCardTime);
		extend.setServerId(this.serverId);
		extend.setChannel(this.channel);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
