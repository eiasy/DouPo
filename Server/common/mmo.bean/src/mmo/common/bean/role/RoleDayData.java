package mmo.common.bean.role;

import java.io.DataInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mmo.common.bean.goods.AGoods;
import mmo.tools.util.DateUtil;

import org.apache.mina.core.buffer.IoBuffer;

public class RoleDayData {
	/** 角色每天可清除穴位冷却时间的次数 */
	private int                                 clearIdentityCount      = 0;
	/** 角色是否领取了培养丹 */
	private boolean                             isGetPeiYangDan         = false;
	/** 角色死亡次数刷新间隔时间 */
	public final static int                     refreshTime             = 4 * 60 * 1000;
	/** 角色死亡响应复活事件时间 */
	public final static int                     eventTime               = 1 * 60 * 1000;
	/** 惩罚时间间隔 */
	private static long                         PUNISH                  = 5 * 60 * 1000;

	/** 日期：年-月-日 */
	private String                              dayString;
	/** 当天死亡次数 */
	private int                                 deathCount;
	/** 当天原地复活次数 */
	private int                                 localRelive;
	/** 最后一次死亡时间 */
	private long                                deathTime;
	/** 惩罚时间 临界值，大于此时间不惩罚 */
	private long                                punishTime              = 0;
	/** 当天重复做任务的数量 */
	private Map<Integer, Integer>               missionCount            = new HashMap<Integer, Integer>();
	/** 当天使用物品的数量 */
	private Map<Integer, Integer>               goodsUseCount           = new HashMap<Integer, Integer>();
	/** <商店类型，当天购买商店物品的数量> */
	private Map<Integer, Map<Integer, Integer>> goodsBuyCount           = new HashMap<Integer, Map<Integer, Integer>>();
	/** 特殊活动物品当天使用物品的次数(13-6-24暂时没用上) */
	private Map<String, Integer>                activityGoodsCount      = new HashMap<String, Integer>();
	/** 【公测临时使用】当天是否发送过VIP卡的MAIL */
	private boolean                             isVipCardSendTORole     = false;
	/** 每日领取境界点次数 */
	private int                                 realmPointCount;
	/** 进入副本英雄模式的次数<key=副本gateStr.hashCode，value=次数> */
	private Map<Integer, Byte>                  enterDupCounts          = new HashMap<Integer, Byte>();
	/** 记录每日赠送亲密物品的好友ID */
	private HashMap<Integer, Short>             intimacyLevelData       = new HashMap<Integer, Short>();
	/** 进入攻城副本的次数<序号ID， 次数> */
	private Map<Integer, Byte>                  enterAtkCityDup         = new HashMap<Integer, Byte>();
	/** 角色已领取经验的离线时间 */
	private double                              offlineTime             = 0;
	/** 可领取离线经验的时间 */
	private double                              totalTime               = 0;
	/** 可领取离线经验的时间 在进入游戏后使用此值 */
	private double                              offlineTimebackup       = 0;
	/** 领取离线经验 */
	private int                                 getOfflineExpCount      = 0;
	/** 当天是否领取宗门活动奖励 */
	private boolean                             awardMark               = false;
	/** 当天是否领取内测250金灵石奖励 */
	private boolean                             bateMoneyMark           = false;
	/** 领取斗法奖励领取次数 */
	private byte                                areaAwardCount          = 0;
	/** 一战到底奖励领取次数 */
	private byte                                oneStandAwardCount      = 0;
	/** 世界首领奖励领取次数 */
	private byte                                leaderAwardCount        = 0;
	/** 角色每天领取免费扫荡符的次数 */
	private byte                                sweepDuplicateCount     = 0;
	/** 四种抽牌每天已免费抽取的次数 */
	private int[]                               pickOutCardFreeCount    = new int[4];
	/** 角色每天购买精力值的次数 */
	private byte                                buyJingLiCount          = 0;
	/** 角色每天购买灵石的次数 */
	private byte                                buyLingShiCount         = 0;
	/** 每天重置英雄副本的次数 <gateId,count> */
	private Map<Integer, Byte>                  resetHeroDupCounts      = new HashMap<Integer, Byte>();
	/** 每天购买体力的次数 */
	private byte                                buyTiLiCount            = 0;
	/** 每天购买斗法的次数 */
	private byte                                buyDouFaCount           = 0;

	/** 单轮最大刷新次数 */
	private int                                 maxCountMysteryShop     = 2;
	/** 当前一轮剩余刷新次数 */
	private int                                 remainCountMysteryShop  = 2;
	/** 当天累计刷新次数 */
	private int                                 refreshCountMysteryShop = 0;
	/** 当天发现多人秘境的次数 */
	private int                                 mulitySecretCount;
	/** 当天累计充值金额(分) */
	private int                                 chargeCount;
	/** 记录每天打坐恢复的精力值 */
	private Map<Integer, Integer>               daZuoReplayDatas        = new HashMap<Integer, Integer>();

	public RoleDayData() {
		dayString = DateUtil.getCurrentDate();
	}

	public int getDeathCount() {
		return deathCount;
	}

	public int getLocalRelive() {
		long currTime = System.currentTimeMillis();
		int count = 0;
		if (punishTime == 0 || currTime < punishTime) {
			count = localRelive + 1;
		} else {
			count = localRelive - 1;
		}
		return count <= 0 ? 1 : count;
	}

	private void reset() {
		isGetPeiYangDan = false;
		awardMark = false;
		bateMoneyMark = false;
		clearIdentityCount = 0;
		deathCount = 0;
		localRelive = 0;
		punishTime = 0;
		missionCount.clear();
		goodsUseCount.clear();
		goodsBuyCountRelease();
		activityGoodsCount.clear();

		enterDupCounts.clear();
		intimacyLevelData.clear();
		isVipCardSendTORole = false;
		realmPointCount = 0;
		offlineTime = 0;
		totalTime = 0;
		getOfflineExpCount = 0;
		areaAwardCount = 0;
		oneStandAwardCount = 0;
		leaderAwardCount = 0;
		sweepDuplicateCount = 0;
		enterAtkCityDup.clear();
		buyJingLiCount = 0;
		initPickOutCardFreeCount();
		resetHeroDupCounts.clear();

		maxCountMysteryShop = 2;
		remainCountMysteryShop = 2;
		refreshCountMysteryShop = 0;
		buyLingShiCount = 0;
		mulitySecretCount = 0;
		buyTiLiCount = 0;
		buyDouFaCount = 0;
		chargeCount = 0;

		daZuoReplayDatas.clear();
	}

	private void initPickOutCardFreeCount() {
		for (int i = 0; i < pickOutCardFreeCount.length; i++) {
			pickOutCardFreeCount[i] = 0;
		}
	}

	private void goodsBuyCountRelease() {
		if (goodsBuyCount != null) {
			Collection<Map<Integer, Integer>> values = goodsBuyCount.values();
			for (Map<Integer, Integer> value : values) {
				value.clear();
			}
			goodsBuyCount.clear();
		}
	}

	public void toDeath() {
		long currTIme = System.currentTimeMillis();
		if (deathTime > 0) {
			deathCount = (int) (deathCount - (currTIme - deathTime) / refreshTime);
			if (deathCount < 1) {
				deathCount = 1;
			} else {
				deathCount++;
			}
		} else {
			deathCount++;
		}
		deathTime = currTIme;
	}

	public void localRelive() {
		long currTime = System.currentTimeMillis();
		if (punishTime == 0 || currTime < punishTime) {
			localRelive++;
		}
		punishTime = currTime + PUNISH;
	}

	public boolean isRelive() {
		return System.currentTimeMillis() - deathTime > eventTime;
	}

	public boolean validate() {
		String dayCurr = DateUtil.getCurrentDate();
		if (!dayCurr.equals(dayString)) {
			this.dayString = dayCurr;
			reset();
			return false;
		}
		return true;
	}

	public void commitMission(int missionId) {
		Integer count = missionCount.get(missionId);
		if (count == null) {
			count = 1;
		} else {
			count += 1;
		}
		missionCount.put(missionId, count);
	}

	public void initMissionCount(int missionId, int count) {
		if (missionCount == null) {
			missionCount = new HashMap<Integer, Integer>();
		}
		missionCount.put(missionId, count);
	}

	public int getMissionCount(int missionId) {
		Integer count = missionCount.get(missionId);
		if (count == null) {
			return 0;
		}
		return count;
	}

	public void goodsBuyCount(int shopType, int goodsId, int count) {
		if (count < 1 || shopType < 1) {
			return;
		}
		Map<Integer, Integer> values = goodsBuyCount.get(shopType);
		if (values == null) {
			values = new HashMap<Integer, Integer>();
			goodsBuyCount.put(shopType, values);
		}
		Integer buy = values.get(goodsId);
		if (buy != null) {
			count += buy;
		}
		values.put(goodsId, count);
	}

	public int getGoodsBuyCount(int shopType, int goodsId) {
		Map<Integer, Integer> values = goodsBuyCount.get(shopType);
		if (values == null) {
			return 0;
		}
		Integer count = values.get(goodsId);
		if (count == null) {
			return 0;
		}
		return count;
	}

	public Map<Integer, Integer> getMissionCount() {
		return missionCount;
	}

	public void useGoods(AGoods goods, int useCount) {
		/**
		 * @alter
		 * @name：xiaoqiong
		 * @date：13-6-26
		 * @note：使用次数增加从1修改为useCount
		 */
		int goodsId = goods.getGoodsId();
		Integer count = goodsUseCount.get(goodsId);
		Integer cou = activityGoodsCount.get(goods.getMainName());
		// modify by renqiang 2013-04-02,下面四种商品的使用次数根据所有品阶的使用的情况计算
		if (goods.getMainName().startsWith("精力丹") || goods.getMainName().startsWith("蕴灵丹") || goods.getMainName().startsWith("历练书")
		        || goods.getMainName().startsWith("霓裳草")) {
			if (cou == null) {
				cou = useCount;
			} else {
				cou += useCount;
			}
			activityGoodsCount.put(goods.getMainName(), cou);
		} else {
			if (count == null) {
				count = useCount;
			} else {
				count += useCount;
			}
			goodsUseCount.put(goodsId, count);
		}

	}

	public int getGoodsUseCount(AGoods goods) {
		if (goods.getMainName().startsWith("精力丹") || goods.getMainName().startsWith("蕴灵丹") || goods.getMainName().startsWith("历练书")
		        || goods.getMainName().startsWith("霓裳草")) {
			Integer cou = activityGoodsCount.get(goods.getMainName());
			if (cou == null) {
				return 0;
			}
			return cou;
		} else {
			Integer count = goodsUseCount.get(goods.getGoodsId());
			if (count == null) {
				return 0;
			}
			return count;
		}
	}

	public Map<String, Integer> getActivityGoodsCount() {
		return activityGoodsCount;
	}

	public void initActivityGoodsCount(String mainName, int count) {
		if (activityGoodsCount == null) {
			activityGoodsCount = new HashMap<String, Integer>();
		}
		activityGoodsCount.put(mainName, count);
	}

	public Map<Integer, Integer> getGoodsCount() {
		return goodsUseCount;
	}

	public void initGoodsCount(int goodsId, int count) {
		if (goodsUseCount == null) {
			goodsUseCount = new HashMap<Integer, Integer>();
		}
		goodsUseCount.put(goodsId, count);
	}

	public Map<Integer, Map<Integer, Integer>> getGoodsBuyCount() {
		return goodsBuyCount;
	}

	public void initGoodsBuyCount(int shopType, int goodsId, int count) {
		if (goodsBuyCount == null) {
			goodsBuyCount = new HashMap<Integer, Map<Integer, Integer>>();
		}
		Map<Integer, Integer> buyCount = goodsBuyCount.get(shopType);
		if (buyCount == null) {
			buyCount = new HashMap<Integer, Integer>();
			goodsBuyCount.put(shopType, buyCount);
		}
		buyCount.put(goodsId, count);
	}

	/**
	 * 通过副本ID获取已经进入的次数
	 * 
	 * @param duplicateId
	 *            副本ID
	 */
	public byte getEnterDupCount(int gateId) {
		Byte count = enterDupCounts.get(gateId);
		if (count == null) {
			count = 0;
		}
		if (count < 0) {
			enterDupCounts.remove(gateId);
			count = 0;
		}
		return count;
	}

	/**
	 * 角色进入对应的副本，则进入的次数加1
	 * 
	 * @param duplicateId
	 *            副本ID
	 */
	public byte addEnterDupCount(int gateId) {
		return addEnterDupCount(gateId, 1);
	}

	/**
	 * 角色进入对应的副本，则进入的次数加1
	 * 
	 * @param duplicateId
	 *            副本ID
	 */
	public byte addEnterDupCount(int gateId, int addCount) {
		if (enterDupCounts == null) {
			enterDupCounts = new HashMap<Integer, Byte>();
		}

		Byte count = enterDupCounts.get(gateId);
		if (count == null) {
			count = (byte) addCount;
			enterDupCounts.put(gateId, count);
		} else {
			count = (byte) (count + addCount);
			enterDupCounts.put(gateId, count);
		}
		return count;
	}

	/**
	 * 增加一条进入副本的次数记录
	 * 
	 * @param duplicateId
	 * @param count
	 */
	public void addEnterDupCount(int gateId, byte count) {
		if (enterDupCounts == null) {
			enterDupCounts = new HashMap<Integer, Byte>();
		}
		enterDupCounts.put(gateId, count);
	}

	/** 获取所有副本的进入次数 */
	public Map<Integer, Byte> getEnterDupCount() {
		return enterDupCounts;
	}

	public boolean isVipCardSendTORole() {
		return isVipCardSendTORole;
	}

	public void setVipCardSendTORole(boolean isVipCardSendTORole) {
		this.isVipCardSendTORole = isVipCardSendTORole;
	}

	public int getRealmPointCount() {
		return realmPointCount;
	}

	public void refreshRealmPointCount() {
		realmPointCount++;
	}

	//
	// public Map<Integer, TempAward> getAwardMap() {
	// return awardMap;
	// }
	//
	// public void setAwardMap(Map<Integer, TempAward> awardMap) {
	// this.awardMap = awardMap;
	// }

	public double getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(double offlineTime) {
		this.offlineTime = offlineTime;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	public int getGetOfflineExpCount() {
		return getOfflineExpCount;
	}

	public void setGetOfflineExpCount(int getOfflineExpCount) {
		this.getOfflineExpCount = getOfflineExpCount;
	}

	public double getOfflineTimebackup() {
		return offlineTimebackup;
	}

	public void setOfflineTimebackup(double totalTimebackup) {
		this.offlineTimebackup = totalTimebackup;
	}

	public boolean getBateMoneyMark() {
		return bateMoneyMark;
	}

	public void setBateMoneyMark(boolean bateMoneyMark) {
		this.bateMoneyMark = bateMoneyMark;
	}

	public boolean getAwardMark() {
		return awardMark;
	}

	public void setAwardMark(boolean awardMark) {
		this.awardMark = awardMark;
	}

	public String getDayString() {
		return dayString;
	}

	public void setDayString(String dayString) {
		this.dayString = dayString;
	}

	public boolean isGetPeiYangDan() {
		return isGetPeiYangDan;
	}

	public void setGetPeiYangDan(boolean isGetPeiYangDan) {
		this.isGetPeiYangDan = isGetPeiYangDan;
	}

	public final void packetData(IoBuffer buf) {

	}

	public final void loadData(DataInputStream dis) {
	}

	public int getClearIdentityCount() {
		return clearIdentityCount;
	}

	public void setClearIdentityCount(int clearIdentityCount) {
		this.clearIdentityCount = clearIdentityCount;
	}

	public void addIntimacyLevelData(int friendRoleId, short count) {
		intimacyLevelData.put(friendRoleId, count);
	}

	/**
	 * @param roleId
	 * @param friendId
	 * @return true:已送物品 false:未送
	 */
	public boolean isSendedFriendIntimacyItem(int friendRoleId) {
		validate();
		return this.intimacyLevelData.get(friendRoleId) != null;
	}

	public HashMap<Integer, Short> getIntimacyLevelData() {
		validate();
		return intimacyLevelData;
	}

	public byte getAreaAwardCount() {
		return areaAwardCount;
	}

	public void setAreaAwardCount(byte areaAwardCount) {
		this.areaAwardCount = areaAwardCount;
	}

	public byte getOneStandAwardCount() {
		return oneStandAwardCount;
	}

	public void setOneStandAwardCount(byte oneStandAwardCount) {
		this.oneStandAwardCount = oneStandAwardCount;
	}

	public byte getLeaderAwardCount() {
		return leaderAwardCount;
	}

	public void setLeaderAwardCount(byte leaderAwardCount) {
		this.leaderAwardCount = leaderAwardCount;
	}

	public byte getSweepDuplicateCount() {
		return sweepDuplicateCount;
	}

	public void setSweepDuplicateCount(byte sweepDuplicateCount) {
		// 1表示已放入领取中心，2表示已领取
		this.sweepDuplicateCount = sweepDuplicateCount;
	}

	public Map<Integer, Byte> getEnterAtkCityCount() {
		return enterAtkCityDup;
	}

	public void addEnterAtkCityCount(int index, byte count) {
		if (enterAtkCityDup == null) {
			enterAtkCityDup = new HashMap<Integer, Byte>();
		}
		enterAtkCityDup.put(index, count);
	}

	public byte getEnterAtkCityCount(int index) {
		Byte count = enterAtkCityDup.get(index);
		if (count == null) {
			count = 0;
		}
		return count;
	}

	public void addPickOutCardFreeCount(int index, int count) {
		if (pickOutCardFreeCount == null) {
			pickOutCardFreeCount = new int[4];
		}
		if (index < 0) {
			index = 0;
		}
		if (index >= pickOutCardFreeCount.length) {
			index = pickOutCardFreeCount.length - 1;
		}
		if (count < 0) {
			count = 0;
		}
		pickOutCardFreeCount[index] = count;
	}

	public int getPickOutCardFreeCount(int index) {
		if (index < 0) {
			index = 0;
		}
		if (index >= pickOutCardFreeCount.length) {
			index = pickOutCardFreeCount.length - 1;
		}
		return pickOutCardFreeCount[index];
	}

	public int[] getPickOutCardFreeCount() {
		return pickOutCardFreeCount;
	}

	public byte getBuyJingLiCount() {
		return buyJingLiCount;
	}

	public void setBuyJingLiCount(byte buyJingLiCount) {
		this.buyJingLiCount = buyJingLiCount;
	}

	public byte getBuyLingShiCount() {
		return buyLingShiCount;
	}

	public void setBuyLingShiCount(byte buyJingLiCount) {
		this.buyLingShiCount = buyJingLiCount;
	}

	public byte getBuyTiLiCount() {
		return buyTiLiCount;
	}

	public void setBuyTiLiCount(byte buyTiLiCount) {
		this.buyTiLiCount = buyTiLiCount;
	}

	public byte getBuyDouFaCount() {
		return buyDouFaCount;
	}

	public void setBuyDouFaCount(byte buyDouFaCount) {
		this.buyDouFaCount = buyDouFaCount;
	}

	public int getChargeCount() {
		return chargeCount;
	}

	public void setChargeCount(int chargeCount) {
		this.chargeCount = chargeCount;
	}

	public void addChargeCount(int chargeCount) {
		this.chargeCount += chargeCount;
	}

	/**
	 * 通过关卡ID获取已经重置的次数
	 * 
	 * @param gateId
	 *            副本ID
	 */
	public byte getResetDupHeroCount(int gateId) {
		Byte count = resetHeroDupCounts.get(gateId);
		if (count == null) {
			count = 0;
		}
		if (count < 0) {
			resetHeroDupCounts.remove(gateId);
			count = 0;
		}
		return count;
	}

	/**
	 * 增加一条重置副本的次数记录
	 * 
	 * @param gateId
	 * @param count
	 */
	public void addResetDupHeroCount(int gateId, byte count) {
		if (resetHeroDupCounts == null) {
			resetHeroDupCounts = new HashMap<Integer, Byte>();
		}
		resetHeroDupCounts.put(gateId, count);
	}

	/** 获取所有副本的重置次数 */
	public Map<Integer, Byte> getResetDupHeroCount() {
		return resetHeroDupCounts;
	}

	public int getRefreshMysteryShopCost() {
		return 1;
	}

	public void refreshMysteryShop() {
		this.refreshCountMysteryShop++;
		this.remainCountMysteryShop--;
		if (remainCountMysteryShop < 1) {
			this.maxCountMysteryShop += 2;
			this.remainCountMysteryShop = maxCountMysteryShop;
		}
	}

	public int getMaxCountMysteryShop() {
		return maxCountMysteryShop;
	}

	public void setMaxCountMysteryShop(int maxCountMysteryShop) {
		this.maxCountMysteryShop = maxCountMysteryShop;
	}

	public int getRemainCountMysteryShop() {
		return remainCountMysteryShop;
	}

	public void setRemainCountMysteryShop(int remainCountMysteryShop) {
		this.remainCountMysteryShop = remainCountMysteryShop;
	}

	public int getRefreshCountMysteryShop() {
		return refreshCountMysteryShop;
	}

	public void setRefreshCountMysteryShop(int refreshCountMysteryShop) {
		this.refreshCountMysteryShop = refreshCountMysteryShop;
	}

	public int getMulitySecretCount() {
		return mulitySecretCount;
	}

	public void setMulitySecretCount(int mulitySecretCount) {
		this.mulitySecretCount = mulitySecretCount;
	}

	public void findMultiSecret() {
		mulitySecretCount++;
	}

	public void setDaZuoReplayData(int index, int count) {
		this.daZuoReplayDatas.put(index, count);
	}

	public void addDaZuoReplayData(int index, int count) {
		Integer value = this.daZuoReplayDatas.get(index);
		if (value == null) {
			value = count;
		} else {
			value += count;
		}
		this.daZuoReplayDatas.put(index, value);
	}

	public int getDaZuoReplayData(int index) {
		Integer value = this.daZuoReplayDatas.get(index);
		if (value == null) {
			return 0;
		}
		return value;
	}

	public Map<Integer, Integer> getDaZuoReplayDatas() {
		return this.daZuoReplayDatas;
	}
}
