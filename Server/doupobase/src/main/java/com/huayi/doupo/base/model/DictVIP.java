package com.huayi.doupo.base.model;

import java.io.*;

/**
	VIP字典表
*/
@SuppressWarnings("serial")
public class DictVIP implements Serializable
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
		名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 2;
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
		index = 3;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		vip开放等级-用于没做充值以前,玩家升到多少级给vip
	*/
	private int openLevel;
	public int getOpenLevel(){
		return openLevel;
	}
	public void setOpenLevel(int openLevel) {
		this.openLevel = openLevel;
		index = 4;
		result += index + "*int*" + openLevel + "#";
	}

	public void setOpenLevel(int openLevel, int bs) {
		this.openLevel = openLevel;
	}

	/**
		购买道具及其数量 thingId_num;
	*/
	private String buyItemsNum;
	public String getBuyItemsNum(){
		return buyItemsNum;
	}
	public void setBuyItemsNum(String buyItemsNum) {
		this.buyItemsNum = buyItemsNum;
		index = 5;
		result += index + "*String*" + buyItemsNum + "#";
	}

	public void setBuyItemsNum(String buyItemsNum, int bs) {
		this.buyItemsNum = buyItemsNum;
	}

	/**
		白银招募加成 qualityId_add; add为整数值
	*/
	private String silverRecruitAdd;
	public String getSilverRecruitAdd(){
		return silverRecruitAdd;
	}
	public void setSilverRecruitAdd(String silverRecruitAdd) {
		this.silverRecruitAdd = silverRecruitAdd;
		index = 6;
		result += index + "*String*" + silverRecruitAdd + "#";
	}

	public void setSilverRecruitAdd(String silverRecruitAdd, int bs) {
		this.silverRecruitAdd = silverRecruitAdd;
	}

	/**
		黄金招募加成 qualityId_add;
	*/
	private String goldRecruitAdd;
	public String getGoldRecruitAdd(){
		return goldRecruitAdd;
	}
	public void setGoldRecruitAdd(String goldRecruitAdd) {
		this.goldRecruitAdd = goldRecruitAdd;
		index = 7;
		result += index + "*String*" + goldRecruitAdd + "#";
	}

	public void setGoldRecruitAdd(String goldRecruitAdd, int bs) {
		this.goldRecruitAdd = goldRecruitAdd;
	}

	/**
		钻石招募加成 qualityId_add;
	*/
	private String diamondRecruitAdd;
	public String getDiamondRecruitAdd(){
		return diamondRecruitAdd;
	}
	public void setDiamondRecruitAdd(String diamondRecruitAdd) {
		this.diamondRecruitAdd = diamondRecruitAdd;
		index = 8;
		result += index + "*String*" + diamondRecruitAdd + "#";
	}

	public void setDiamondRecruitAdd(String diamondRecruitAdd, int bs) {
		this.diamondRecruitAdd = diamondRecruitAdd;
	}

	/**
		黑角域重置次数
	*/
	private int hJYReset;
	public int getHJYReset(){
		return hJYReset;
	}
	public void setHJYReset(int hJYReset) {
		this.hJYReset = hJYReset;
		index = 9;
		result += index + "*int*" + hJYReset + "#";
	}

	public void setHJYReset(int hJYReset, int bs) {
		this.hJYReset = hJYReset;
	}

	/**
		每日可购买竞技场挑战的次数
	*/
	private int buyArenaNum;
	public int getBuyArenaNum(){
		return buyArenaNum;
	}
	public void setBuyArenaNum(int buyArenaNum) {
		this.buyArenaNum = buyArenaNum;
		index = 10;
		result += index + "*int*" + buyArenaNum + "#";
	}

	public void setBuyArenaNum(int buyArenaNum, int bs) {
		this.buyArenaNum = buyArenaNum;
	}

	/**
		银币材料活动副本购买次数 神龙宝藏
	*/
	private int silverActivityChapterBuyTimes;
	public int getSilverActivityChapterBuyTimes(){
		return silverActivityChapterBuyTimes;
	}
	public void setSilverActivityChapterBuyTimes(int silverActivityChapterBuyTimes) {
		this.silverActivityChapterBuyTimes = silverActivityChapterBuyTimes;
		index = 11;
		result += index + "*int*" + silverActivityChapterBuyTimes + "#";
	}

	public void setSilverActivityChapterBuyTimes(int silverActivityChapterBuyTimes, int bs) {
		this.silverActivityChapterBuyTimes = silverActivityChapterBuyTimes;
	}

	/**
		经验材料活动副本购买次数 阳火古坛
	*/
	private int expActivityChapterBuyTimes;
	public int getExpActivityChapterBuyTimes(){
		return expActivityChapterBuyTimes;
	}
	public void setExpActivityChapterBuyTimes(int expActivityChapterBuyTimes) {
		this.expActivityChapterBuyTimes = expActivityChapterBuyTimes;
		index = 12;
		result += index + "*int*" + expActivityChapterBuyTimes + "#";
	}

	public void setExpActivityChapterBuyTimes(int expActivityChapterBuyTimes, int bs) {
		this.expActivityChapterBuyTimes = expActivityChapterBuyTimes;
	}

	/**
		丹药材料活动副本购买次数
	*/
	private int pillActivityChapterBuyTimes;
	public int getPillActivityChapterBuyTimes(){
		return pillActivityChapterBuyTimes;
	}
	public void setPillActivityChapterBuyTimes(int pillActivityChapterBuyTimes) {
		this.pillActivityChapterBuyTimes = pillActivityChapterBuyTimes;
		index = 13;
		result += index + "*int*" + pillActivityChapterBuyTimes + "#";
	}

	public void setPillActivityChapterBuyTimes(int pillActivityChapterBuyTimes, int bs) {
		this.pillActivityChapterBuyTimes = pillActivityChapterBuyTimes;
	}

	/**
		魂魄材料活动副本购买次数
	*/
	private int soulActivityChapterBuyTimes;
	public int getSoulActivityChapterBuyTimes(){
		return soulActivityChapterBuyTimes;
	}
	public void setSoulActivityChapterBuyTimes(int soulActivityChapterBuyTimes) {
		this.soulActivityChapterBuyTimes = soulActivityChapterBuyTimes;
		index = 14;
		result += index + "*int*" + soulActivityChapterBuyTimes + "#";
	}

	public void setSoulActivityChapterBuyTimes(int soulActivityChapterBuyTimes, int bs) {
		this.soulActivityChapterBuyTimes = soulActivityChapterBuyTimes;
	}

	/**
		境界丹材料活动副本购买次数  天山血池
	*/
	private int talentActivityChapterBuyTimes;
	public int getTalentActivityChapterBuyTimes(){
		return talentActivityChapterBuyTimes;
	}
	public void setTalentActivityChapterBuyTimes(int talentActivityChapterBuyTimes) {
		this.talentActivityChapterBuyTimes = talentActivityChapterBuyTimes;
		index = 15;
		result += index + "*int*" + talentActivityChapterBuyTimes + "#";
	}

	public void setTalentActivityChapterBuyTimes(int talentActivityChapterBuyTimes, int bs) {
		this.talentActivityChapterBuyTimes = talentActivityChapterBuyTimes;
	}

	/**
		精英副本购买次数
	*/
	private int eliteChapterBuyTimes;
	public int getEliteChapterBuyTimes(){
		return eliteChapterBuyTimes;
	}
	public void setEliteChapterBuyTimes(int eliteChapterBuyTimes) {
		this.eliteChapterBuyTimes = eliteChapterBuyTimes;
		index = 16;
		result += index + "*int*" + eliteChapterBuyTimes + "#";
	}

	public void setEliteChapterBuyTimes(int eliteChapterBuyTimes, int bs) {
		this.eliteChapterBuyTimes = eliteChapterBuyTimes;
	}

	/**
		搜索紫炎次数
	*/
	private int pagodaSearchNum;
	public int getPagodaSearchNum(){
		return pagodaSearchNum;
	}
	public void setPagodaSearchNum(int pagodaSearchNum) {
		this.pagodaSearchNum = pagodaSearchNum;
		index = 17;
		result += index + "*int*" + pagodaSearchNum + "#";
	}

	public void setPagodaSearchNum(int pagodaSearchNum, int bs) {
		this.pagodaSearchNum = pagodaSearchNum;
	}

	/**
		重置爬塔次数
	*/
	private int pagodaResetNum;
	public int getPagodaResetNum(){
		return pagodaResetNum;
	}
	public void setPagodaResetNum(int pagodaResetNum) {
		this.pagodaResetNum = pagodaResetNum;
		index = 18;
		result += index + "*int*" + pagodaResetNum + "#";
	}

	public void setPagodaResetNum(int pagodaResetNum, int bs) {
		this.pagodaResetNum = pagodaResetNum;
	}

	/**
		神羽强化副本次数
	*/
	private int wingChapterNum;
	public int getWingChapterNum(){
		return wingChapterNum;
	}
	public void setWingChapterNum(int wingChapterNum) {
		this.wingChapterNum = wingChapterNum;
		index = 19;
		result += index + "*int*" + wingChapterNum + "#";
	}

	public void setWingChapterNum(int wingChapterNum, int bs) {
		this.wingChapterNum = wingChapterNum;
	}

	/**
		魔王副本次数
	*/
	private int fiendChapterNum;
	public int getFiendChapterNum(){
		return fiendChapterNum;
	}
	public void setFiendChapterNum(int fiendChapterNum) {
		this.fiendChapterNum = fiendChapterNum;
		index = 20;
		result += index + "*int*" + fiendChapterNum + "#";
	}

	public void setFiendChapterNum(int fiendChapterNum, int bs) {
		this.fiendChapterNum = fiendChapterNum;
	}

	/**
		是否可以连续战斗 0-不可以 1-可以
	*/
	private int isContinuFight;
	public int getIsContinuFight(){
		return isContinuFight;
	}
	public void setIsContinuFight(int isContinuFight) {
		this.isContinuFight = isContinuFight;
		index = 21;
		result += index + "*int*" + isContinuFight + "#";
	}

	public void setIsContinuFight(int isContinuFight, int bs) {
		this.isContinuFight = isContinuFight;
	}

	/**
		是否可以重置普通副本 0-不可以 1-可以
	*/
	private int isResetGenerBarrier;
	public int getIsResetGenerBarrier(){
		return isResetGenerBarrier;
	}
	public void setIsResetGenerBarrier(int isResetGenerBarrier) {
		this.isResetGenerBarrier = isResetGenerBarrier;
		index = 22;
		result += index + "*int*" + isResetGenerBarrier + "#";
	}

	public void setIsResetGenerBarrier(int isResetGenerBarrier, int bs) {
		this.isResetGenerBarrier = isResetGenerBarrier;
	}

	/**
		是否达到成为米特尔白金贵宾的vip条件 0-不到 1-达到
	*/
	private int isUpSilverVip;
	public int getIsUpSilverVip(){
		return isUpSilverVip;
	}
	public void setIsUpSilverVip(int isUpSilverVip) {
		this.isUpSilverVip = isUpSilverVip;
		index = 23;
		result += index + "*int*" + isUpSilverVip + "#";
	}

	public void setIsUpSilverVip(int isUpSilverVip, int bs) {
		this.isUpSilverVip = isUpSilverVip;
	}

	/**
		vip礼包图片Id
	*/
	private int giftBugUiId;
	public int getGiftBugUiId(){
		return giftBugUiId;
	}
	public void setGiftBugUiId(int giftBugUiId) {
		this.giftBugUiId = giftBugUiId;
		index = 24;
		result += index + "*int*" + giftBugUiId + "#";
	}

	public void setGiftBugUiId(int giftBugUiId, int bs) {
		this.giftBugUiId = giftBugUiId;
	}

	/**
		vip礼包物品 tableTypeId_tableFieldId_value;
	*/
	private String giftBagThings;
	public String getGiftBagThings(){
		return giftBagThings;
	}
	public void setGiftBagThings(String giftBagThings) {
		this.giftBagThings = giftBagThings;
		index = 25;
		result += index + "*String*" + giftBagThings + "#";
	}

	public void setGiftBagThings(String giftBagThings, int bs) {
		this.giftBagThings = giftBagThings;
	}

	/**
		达到vip限制-充多少rmb能到此vip等级
	*/
	private int limit;
	public int getLimit(){
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
		index = 26;
		result += index + "*int*" + limit + "#";
	}

	public void setLimit(int limit, int bs) {
		this.limit = limit;
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
		index = 27;
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
		index = 28;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		神秘商店vip刷新次数（元宝）
0—为没限制，可以一直刷新
	*/
	private int hjyFreshCount;
	public int getHjyFreshCount(){
		return hjyFreshCount;
	}
	public void setHjyFreshCount(int hjyFreshCount) {
		this.hjyFreshCount = hjyFreshCount;
		index = 29;
		result += index + "*int*" + hjyFreshCount + "#";
	}

	public void setHjyFreshCount(int hjyFreshCount, int bs) {
		this.hjyFreshCount = hjyFreshCount;
	}

	/**
		普通副本重置次数
	*/
	private int chapterResetCount;
	public int getChapterResetCount(){
		return chapterResetCount;
	}
	public void setChapterResetCount(int chapterResetCount) {
		this.chapterResetCount = chapterResetCount;
		index = 30;
		result += index + "*int*" + chapterResetCount + "#";
	}

	public void setChapterResetCount(int chapterResetCount, int bs) {
		this.chapterResetCount = chapterResetCount;
	}

	public String getResult(){
		return result;
	}

	public DictVIP clone(){
		DictVIP extend=new DictVIP();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setLevel(this.level);
		extend.setOpenLevel(this.openLevel);
		extend.setBuyItemsNum(this.buyItemsNum);
		extend.setSilverRecruitAdd(this.silverRecruitAdd);
		extend.setGoldRecruitAdd(this.goldRecruitAdd);
		extend.setDiamondRecruitAdd(this.diamondRecruitAdd);
		extend.setHJYReset(this.hJYReset);
		extend.setBuyArenaNum(this.buyArenaNum);
		extend.setSilverActivityChapterBuyTimes(this.silverActivityChapterBuyTimes);
		extend.setExpActivityChapterBuyTimes(this.expActivityChapterBuyTimes);
		extend.setPillActivityChapterBuyTimes(this.pillActivityChapterBuyTimes);
		extend.setSoulActivityChapterBuyTimes(this.soulActivityChapterBuyTimes);
		extend.setTalentActivityChapterBuyTimes(this.talentActivityChapterBuyTimes);
		extend.setEliteChapterBuyTimes(this.eliteChapterBuyTimes);
		extend.setPagodaSearchNum(this.pagodaSearchNum);
		extend.setPagodaResetNum(this.pagodaResetNum);
		extend.setWingChapterNum(this.wingChapterNum);
		extend.setFiendChapterNum(this.fiendChapterNum);
		extend.setIsContinuFight(this.isContinuFight);
		extend.setIsResetGenerBarrier(this.isResetGenerBarrier);
		extend.setIsUpSilverVip(this.isUpSilverVip);
		extend.setGiftBugUiId(this.giftBugUiId);
		extend.setGiftBagThings(this.giftBagThings);
		extend.setLimit(this.limit);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		extend.setHjyFreshCount(this.hjyFreshCount);
		extend.setChapterResetCount(this.chapterResetCount);
		return extend;
	}
}
