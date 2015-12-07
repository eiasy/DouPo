package mmo.common.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.config.DuplicateConfig;
import mmo.common.config.SpeedConfig;
import mmo.common.config.StringLib;
import mmo.common.xls.AParseSheet;
import mmo.common.xls.AParseXLS;
import mmo.module.rank.RankManager;
import mmo.tools.util.DateUtil;
import mmo.tools.util.StringUtil;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class GameParameter {
	/** 进入引导场景 */
	protected static boolean          toTeachScene                    = false;
	/** 切换到角色列表界面 */
	protected static boolean          toRoleList                      = true;
	/** 是否开启给BI定时推送在线数据 */
	protected static boolean          openSendDataToBi                = false;
	/** 是否开启GM命令 */
	protected static boolean          openGMcommand                   = false;
	/** 是否开启激活码按钮 */
	protected static boolean          openNumberButton                = false;
	/** 是否开启VIP按钮 */
	protected static boolean          openVipButton                   = false;
	/** 是否开启账号按钮 */
	protected static boolean          openAccountButton               = false;
	/** 陌生人存在最大值 */
	protected static int              tempRelationMax                 = 10;
	/** 境界点的最大值 */
	protected static int              realPointMax                    = 100000;
	/** 角色最高等级 */
	protected static short            roleMaxLevel                    = 60;
	/** 技能召唤的宝宝最高等级 */
	protected static short            skillPetMaxLevel                = 7;
	/** 系统产出的宝宝的最高等级 */
	protected static short            gamePetMaxLevel                 = 5;
	/** 排行榜刷新间隔(分钟) */
	protected static int              rankListRefresh                 = 5 * 1000 * 60;
	/** 记录在线人数时间间隔 */
	protected static int              logRoleCountOffset              = 1000 * 60 * 15;
	/** 保存角色数据的时间间隔 */
	protected static int              resaveOffset                    = 1000 * 60 * 5;
	/** HP回复间隔 */
	protected static int              addHpOffset                     = 1000 * 10;
	/** 重连有效时间 */
	protected static int              reconnctTime                    = 1000 * 60 * 5;
	/** 精力值回复间隔 */
	protected static int              addJingLi                       = 1000 * 60 * 6;
	/** 体力恢复间隔 */
	protected static int              addTiLi                         = 1000 * 60 * 15;
	/** 技能点回复间隔 */
	protected static int              addSkillPoint                   = 1000 * 60 * 10;
	/** 每次恢复体力的数量 */
	protected static int              addTiLiEveryTime                = 1;
	/** 每次恢复精力值 */
	protected static int              addJingLiEveryTime              = 1;
	/** 每次回复技能点值 */
	protected static int              addSkillPointEveryTime          = 1;
	/** 在线角色平均等级时间间隔 */
	protected static int              averageLeveOffset               = 1000 * 60 * 30;
	/** 任务星级对应的读条时间 */
	protected static int              starTime                        = 2 * 1000;
	/** 宗门宗战失败后的保护时间(分钟) */
	protected static int              protectedTime                   = 5;
	/** 宠物复活倒计时(秒) */
	protected static int              petReliveTime                   = 180;
	/** 世界BOSS次数回复间隔时间(秒) */
	protected static int              worldBossReplayTime             = 2 * 60 * 1000;
	/** 竞技场初始积分 */
	protected static int              baseIntegral                    = 1000;
	/** 世界地图传送消耗的传送卷轴 */
	protected static int              transferReel                    = 11008;
	/** 传送价格 */
	protected static int              transferPrice                   = 100;
	/** 世界聊天的时间间隔 */
	protected static int              worldChatOffset                 = 1000 * 20;
	/** 世界聊天的可及时发送最大条数 */
	protected static int              worldChatCount                  = 5;
	/** 玩家之间进行交易的税率：百分比的分子值 */
	protected static byte             rate                            = 15;
	/** 秘境场景 */
	protected static int              sceneMijing                     = 1043;
	/** 与NPC对话时的最大距离 */
	protected static int              npcMaxDistance                  = 60 * 60;
	/** 物品合成时需要的材料数量 */
	protected static int              materialCount                   = 5;
	/** 角色接受任务数量上限 */
	protected static int              taskUpperLimit                  = 10;
	/** 服务器剩余神阶法宝数量 */
	protected static int              shenjieFabaoCount               = 0;
	/** 经验倍数 */
	protected static int              expMultiple                     = 2;
	/** 判断物品归属的伤害值比率 */
	protected static int              belongPercent                   = 20;
	/** 怪物切换攻击目标所需要的最高仇恨值比率（%） */
	protected static int              changeHitTarget                 = 120;
	/** 今日杀怪上限值 */
	protected static int              todayKillMonsterMax             = 5000;
	/** 明日杀怪剩余上限值 */
	protected static int              yesterdayResidueKill            = 3000;
	/** 疲劳期的恢复刷新时间(分钟/天) */
	protected static int              tiredRefreshTime                = 480;
	/** 英雄难度的副本次数重置刷新时间(分钟/天) */
	protected static int              heroRefreshTime                 = 480;
	/** 切换到屠杀模式的最低等级 */
	protected static int              killModelLevel                  = 30;
	/** 低于30级切换到屠杀模式时的提示信息 */
	protected static String           killModelInfo                   = "提升到30级才能切换战斗模式！";
	/** 重置装备属性时消耗的物品 的ID */
	protected static int              resetGoodsId                    = 11015;
	/** 重置装备属性时每次消耗的物品的数量 */
	protected static int              resetGoodsCount                 = 1;
	/** 可被分解的装备最低等级 */
	protected static short            canResolveLevel                 = 15;
	/** 当日宗战排名在该值之内的可以领取奖励 */
	protected static int              sectTopNumber                   = 3;
	/** 是否支持机器人 */
	protected static boolean          isRebot;
	/** gm账号下限值 */
	protected static int              gmAccountSub                    = 101;
	/** gm账号上限值 */
	protected static int              gmAccountSup                    = 200;
	/** 协战好友没有宠物时的默认宠物形象 */
	protected static int              petDefault                      = 2001;
	/** 3-5星抽牌的冷却时间(毫秒) */
	protected static long             pickOutCoolStar_3_5;
	/** 4-5星抽牌的冷却时间 （毫秒） */
	protected static long             pickOutCoolStar_4_5;
	/** 协战时间 */
	protected static int              helpWarTime;
	/** 每秒恢复MP值 */
	protected static int              replayMp                        = 1;
	/** 世界首领开放时间(毫秒数) */
	protected static long             openWordBoss                    = 0;
	public static long                lastOpenWordBoss                = 0;
	public static String              openWordBossStr                 = null;
	public static String              worldBossOpenTimeStr            = null;
	/** 竞技场持续时间(秒) */
	protected static int              arenaMaxTime                    = 300;
	/** 世界首领关闭世界(毫秒数) */
	protected static long             closeWordBoss                   = 0;
	public static long                lastCloseWordBoss               = 0;
	/** 世界首领奖励持续时间(毫秒) */
	protected static long             worldBossAwardEffectiveTime     = DateUtil.ONE_DAY_M;
	/** 斗法奖励持续时间(毫秒) */
	protected static long             douFaAwardEffectiveTime         = DateUtil.ONE_DAY_M;
	/** 秘境奖励持续时间(毫秒) */
	protected static long             miJingAwardEffectiveTime        = DateUtil.ONE_DAY_M;
	/** 一战到底奖励持续时间(毫秒) */
	protected static long             oneStandAwardEffectiveTime      = DateUtil.ONE_DAY_M;
	/** 斗法奖品发放时间 */
	protected static long             douFaAwardTimeMin               = DateUtil.getCurrentDateSec() + DateUtil.ONE_DAY_M;
	/** 一战到底奖品发放时间 */
	protected static long             oneStandAwardTimeMin            = DateUtil.getCurrentDateSec() + DateUtil.ONE_DAY_M;
	/** 普通关卡，普通难度掉率增加百分比 */
	protected static int              commonLevelPercent              = 5;
	/** 普通关卡，困难难度掉率增加百分比 */
	protected static int              diffcultLevelPercent            = 10;
	/** 斗法数据下一次重置的时间 */
	protected static long             douFaResetDataTime              = 0;
	/** 斗法数据重置的时间间隔 */
	protected static long             douFaResetDataOffset            = 0;
	/** 活跃度任务重置时间 */
	protected static long             livenessMissionResetTime        = 0;
	/** 斗法消耗体力值 */
	protected static int              douFaCostTiLi                   = 2;
	/** 一战到底数据下一次重置的时间 */
	protected static long             oneStandResetDataTime           = 0;
	/** 一战到底数据重置的时间间隔 */
	protected static long             oneStandResetDataOffset         = 0;
	/** 一战到底开启等级 */
	protected static short            openOneStandLevel               = 25;
	/** 世界BOSS解封需要元宝数 */
	protected static int              worldBossShortlyMoney           = 10;
	/** 怪物攻城开始时间 */
	protected static long             monsterAtkCityStartTime         = 0;
	protected static String           monsterAtkOpenTimeStr           = null;
	/** 怪物攻城开始公告发布时间 */
	protected static long             monsterAtkCityStartNoticeTime   = 0;
	/** 怪物攻城结束时间 */
	protected static long             monsterAtkCityEndTime           = 0;
	/** 怪物攻城结束公告发布时间 */
	protected static long             monsterAtkCityEndNoticeTime     = 0;
	/** 怪物攻城小怪NPC刷新间隔时间 */
	protected static long             monsterAtkCityNpcOffsetTime     = 0;
	/** 怪物攻城小怪NPC刷新时间 */
	protected static long             monsterAtkCityNpcUpdateTime     = 0;
	/** 怪物攻城第一波BOSS刷出时间,活动开启后offset分钟出现 */
	protected static long             monsterAtkCityBossUpdateOffset  = 0;
	protected static long             monsterAtkCityBossUpdateTime    = 0;
	protected static long             monsterAtkCityBossUpdateOffset2 = 0;
	protected static long             monsterAtkCityBossUpdateTime2   = 0;
	/** 怪物攻城第一波BOSS消失时间，出现后offset分钟消失 */
	protected static long             monsterAtkCityBossHideOffset    = 0;
	protected static long             monsterAtkCityBossHideTime      = 0;
	protected static long             monsterAtkCityBossHideTime2     = 0;
	/** 免费抽宠物卡每天最大次数 */
	protected static int              pickPetCardFreeMaxCount         = 5;
	/** 重置斗法时间消耗元宝数量 */
	protected static int              resetDoufaTimeYuanBao           = 50;
	/** 记录对战记录最大条数 */
	protected static int              challengeLogCount               = 10;
	/** 排行榜内显示条数 */
	protected static int              rankShowCount                   = 50;
	protected static long[]           mysteryShopRefreshTime          = new long[0];
	protected static long[]           douFaShopRefreshTime            = new long[0];
	protected static long[]           oneStandShopRefreshTime         = new long[0];
	/** 自动弹出签到奖励界面最低等级 */
	protected static short            openSignAwardUiLevel            = 10;
	/** 斗法商店刷新消耗 */
	protected static int              updateDouFaCost                 = 100;
	/** 一战到底商店刷新消耗 */
	protected static int              updateOneStandCost              = 100;
	/** 第一个关卡赠送伙伴1模型ID */
	protected static int              firstModelId                    = 72;
	/** 第一个关卡赠送伙伴2模型ID */
	protected static int              secendModelId                   = 55;
	/** 寻路过程中不让移动的任务ID */
	protected static List<Integer>    notMoveFindPathMission          = new ArrayList<Integer>();
	/** 重置技能加点消耗元宝数量 */
	protected static int              resetSillDataCost               = 50;
	/**********************************************************************************************/
	/** 仓库容量 */
	private static Map<Short, Short>  storeCapacity                   = new HashMap<Short, Short>();
	/** 仓库序号 */
	private static Map<Short, String> storeIndex                      = new HashMap<Short, String>();
	/** 仓库-VIP */
	private static Map<Short, Short>  storeVipLV                      = new HashMap<Short, Short>();
	/** 仓库名称 */
	private static Map<Short, String> storeName                       = new HashMap<Short, String>();
	/***********************************************************************************************/
	/** 斗法属性倍率 */
	protected static float            DF_HP                           = 1.0F;
	protected static float            DF_ATK                          = 1.0F;
	protected static float            DF_DEF                          = 1.0F;
	/** 一战到底属性倍率 */
	protected static float            YZDD_HP                         = 1.0F;
	protected static float            YZDD_ATK                        = 1.0F;
	protected static float            YZDD_DEF                        = 1.0F;
	/********************************************** 推送秘境的冷却时间 **********************************************/
	protected static int              pushSecretScene2WorldCool       = 20 * 1000;
	protected static int              pushSecretScene2FriendCool      = 10 * 1000;
	/********************************************** 机器人配置 *****************************************************/
	protected static boolean          robotSwitch                     = false;
	protected static int              robotMaxCount                   = 100;
	protected static int              robotCreateOffsetSub            = 5000;
	protected static int              robotCreateOffsetSup            = 20000;
	protected static long             robotStartTime                  = 0;
	protected static long             robotStopTime                   = 0;
	/** 夺宝成功的几率 */
	protected static int              grabRate                        = 20;
	protected static int              assessParameter                 = 10;
	protected static long             grabCoolTime                    = 18 * 60 * 60 * 1000;
	/** 是否可以挑战自己 */
	protected static boolean          challengeSelf                   = true;
	/** 斗法冷却时间 */
	protected static int              challengeCooltime               = 0;
	/** 藏宝图开启等级 */
	protected static int              treasureMapLevel                = 13;

	public static int getChallengeCooltime() {
		return challengeCooltime;
	}

	public static void setChallengeCooltime(int challengeCooltime) {
		GameParameter.challengeCooltime = challengeCooltime;
	}

	public static int getTreasureMapLevel() {
		return treasureMapLevel;
	}

	public static void setTreasureMapLevel(int treasureMapLevel) {
		GameParameter.treasureMapLevel = treasureMapLevel;
	}

	public static boolean isChallengeSelf() {
		return challengeSelf;
	}

	public static void setChallengeSelf(boolean challengeSelf) {
		GameParameter.challengeSelf = challengeSelf;
	}

	public static int getAssessParameter() {
		return assessParameter;
	}

	public static void setAssessParameter(int assessParameter) {
		GameParameter.assessParameter = assessParameter;
	}

	public static void setGrabRate(int grabRate) {
		GameParameter.grabRate = grabRate;
	}

	public static int getGrabRate() {
		return grabRate;
	}

	public static boolean isToRoleList() {
		return toRoleList;
	}

	public static void setToRoleList(boolean toRoleList) {
		GameParameter.toRoleList = toRoleList;
	}

	public static boolean isToTeachScene() {
		return toTeachScene;
	}

	public static void setToTeachScene(boolean toTeachScene) {
		GameParameter.toTeachScene = toTeachScene;
	}

	public static int getAddHpOffset() {
		return addHpOffset;
	}

	public static int getAddJingLi() {
		return addJingLi;
	}

	public static int getAddTiLi() {
		return addTiLi;
	}

	public static int getAddSkillPoint() {
		return addSkillPoint;
	}

	public static int getAverageLeveOffset() {
		return averageLeveOffset;
	}

	public static int getStarTime() {
		return starTime;
	}

	public static int getBaseIntegral() {
		return baseIntegral;
	}

	public static int getResaveOffset() {
		return resaveOffset;
	}

	public final static int getWorldChatCount() {
		return worldChatCount;
	}

	public static int getRankShowCount() {
		return rankShowCount;
	}

	public static void setRankShowCount(int rankShowCount) {
		if (rankShowCount > 0) {
			GameParameter.rankShowCount = rankShowCount;
		}
	}

	/**
	 * 根据仓库编号获取索尼
	 * 
	 * @param storeId
	 * @return
	 */
	public final static String getStoreIndex(short storeId) {
		String index = storeIndex.get(storeId);
		if (index == null) {
			return "-";
		}
		return index;
	}

	public static int getChallengeLogCount() {
		return challengeLogCount;
	}

	public static void setChallengeLogCount(int challengeLogCount) {
		GameParameter.challengeLogCount = challengeLogCount;
	}

	public static final int getWorldChatOffset() {
		return worldChatOffset;
	}

	/**
	 * 根据仓库编号获取仓库名称
	 * 
	 * @return
	 */
	public final static String getStoreName(short storeId) {
		String name = storeName.get(storeId);
		if ("".equals(name)) {
			return "-";
		}
		return name;
	}

	/**
	 * 根据仓库vip级别获取仓库类别名称
	 * 
	 * @return
	 */
	public final static String getStoreNameR(int storeLev) {
		if (storeLev > 0) {
			return StringLib.storeVip;
		} else {
			return StringLib.storeSelf;
		}
	}

	public static int getMaterialCount() {
		return materialCount;
	}

	public final static int getSceneMijing() {
		return sceneMijing;
	}

	public final static int getTransferPrice() {
		return transferPrice;
	}

	public final static int getNpcMaxDistance() {
		return npcMaxDistance;
	}

	public final static int getBelongPercent() {
		return belongPercent;
	}

	public final static int getChangeHitTarget() {
		return changeHitTarget;
	}

	public final static int getResetGoodsId() {
		return resetGoodsId;
	}

	public final static int getResetGoodsCount() {
		return resetGoodsCount;
	}

	/**
	 * 获取开启仓库时的VIP等级
	 * 
	 * @param storeId
	 * @return
	 */
	public final static short getStoreVipLv(short storeId) {
		Short lv = storeVipLV.get(storeId);
		if (lv == null) {
			return 0;
		}
		return lv;
	}

	/**
	 * 根据仓库编号获取仓库的容量
	 * 
	 * @param storeId
	 * @return
	 */
	public final static short getStoreCapacity(short storeId) {
		Short cap = storeCapacity.get(storeId);
		if (cap == null) {
			return 0;
		}
		return cap;
	}

	public final static int getLogRoleCountOffset() {
		return logRoleCountOffset;
	}

	public final static int getTempRelationMax() {
		return tempRelationMax;
	}

	public final static int getRealPointMax() {
		return realPointMax;
	}

	// public final static short getRoleMaxLevel() {
	// return roleMaxLevel;
	// }

	public final static short getSkillPetMaxLevel() {
		return skillPetMaxLevel;
	}

	public final static short getGamePetMaxLevel() {
		return gamePetMaxLevel;
	}

	public final static int getTransferReel() {
		return transferReel;
	}

	public final static byte getRate() {
		return rate;
	}

	public final static int getShenjieFabaoCount() {
		return shenjieFabaoCount;
	}

	public final static void initParameter(final String filePath) {
		AParseXLS parameterXLS = new ParseParameterXls(filePath);
		parameterXLS.execute();
	}

	public final static int getTaskUpperLimit() {
		return taskUpperLimit;
	}

	public final static boolean isRebot() {
		return isRebot;
	}

	public final static int getGmAccountSub() {
		return gmAccountSub;
	}

	public final static int getGmAccountSup() {
		return gmAccountSup;
	}

	public final static int getExpMultiple() {
		return expMultiple;
	}

	public final static int getRankListRefresh() {
		return rankListRefresh;
	}

	public final static int getTodayKillMonsterMax() {
		return todayKillMonsterMax;
	}

	public final static int getYesterdayResidueKill() {
		return yesterdayResidueKill;
	}

	public final static int getTiredRefreshTime() {
		return tiredRefreshTime;
	}

	public final static int getHeroRefreshTime() {
		return heroRefreshTime;
	}

	public final static int getKillModelLevel() {
		return killModelLevel;
	}

	public final static String getKillModelInfo() {
		return killModelInfo;
	}

	public final static int getProtectedTime() {
		return protectedTime;
	}

	public final static boolean isOpenGMcommand() {
		return openGMcommand;
	}

	public final static void setOpenGMcommand(boolean status) {
		openGMcommand = status;
	}

	public final static boolean isOpenSendDataToBi() {
		return openSendDataToBi;
	}

	public final static short getCanResolveLevel() {
		return canResolveLevel;
	}

	public final static int getSectTopNumber() {
		return sectTopNumber;
	}

	public final static int getPetReliveTime() {
		return petReliveTime;
	}

	public final static int getReconnctTime() {
		return reconnctTime;
	}

	public final static int getWorldBossReplayTime() {
		return worldBossReplayTime;
	}

	public final static int getAddJingLiEveryTime() {
		return GameParameter.addJingLiEveryTime;
	}

	public final static int getAddSkillPointEveryTime() {
		return GameParameter.addSkillPointEveryTime;
	}

	public final static int getAddTiLiEveryTime() {
		return GameParameter.addTiLiEveryTime;
	}

	public static int getPetDefault() {
		return petDefault;
	}

	public static int getHelpWarTime() {
		return helpWarTime;
	}

	public static int getArenaMaxTime() {
		return arenaMaxTime;
	}

	/**
	 * 3-5星抽牌的冷却时间(毫秒)
	 * 
	 * @return
	 */
	public static long getPickOutCoolStar_2_5() {
		return pickOutCoolStar_3_5;
	}

	/**
	 * 4-5星抽牌的冷却时间(毫秒)
	 * 
	 * @return
	 */
	public static long getPickOutCoolStar_3_5() {
		return pickOutCoolStar_4_5;
	}

	public static int getReplayMp() {
		return replayMp;
	}

	/**
	 * 是否为GM账号
	 * 
	 * @param accountId
	 *            账号ID
	 * @return true:GM账号，false:用户
	 */
	public final static boolean isGmAccount(int accountId) {
		return accountId >= getGmAccountSub() && accountId <= getGmAccountSup();
	}

	public static short getRoleMaxLevel() {
		return roleMaxLevel;
	}

	public static long getWorldBossAwardEffectiveTime() {
		return worldBossAwardEffectiveTime;
	}

	public static long getDouFaAwardEffectiveTime() {
		return douFaAwardEffectiveTime;
	}

	public static long getMiJingAwardEffectiveTime() {
		return miJingAwardEffectiveTime;
	}

	public static long getOneStandAwardEffectiveTime() {
		return oneStandAwardEffectiveTime;
	}

	public static Map<Short, Short> getStoreCapacity() {
		return storeCapacity;
	}

	public static Map<Short, String> getStoreIndex() {
		return storeIndex;
	}

	public static Map<Short, Short> getStoreVipLV() {
		return storeVipLV;
	}

	public static Map<Short, String> getStoreName() {
		return storeName;
	}

	public static boolean isDouFaAwardTimeMin(long currTime) {
		if (douFaAwardTimeMin < currTime) {
			douFaAwardTimeMin = currTime + DateUtil.ONE_DAY_M;
			return true;
		}
		return false;
	}

	public static void setDouFaAwardTime(long time) {
		douFaAwardTimeMin = time;
	}

	public static boolean isOneStandAwardTimeMin(long currTime) {
		if (oneStandAwardTimeMin < currTime) {
			oneStandAwardTimeMin = currTime + DateUtil.ONE_DAY_M;
			return true;
		}
		return false;
	}

	public static int getDupPercentByDiffcultLevel(int level) {
		switch (level) {
			case DuplicateConfig.DupLevel.normal:
				return commonLevelPercent;
			case DuplicateConfig.DupLevel.hard:
				return diffcultLevelPercent;
			default:
				return 0;
		}
	}

	public static boolean isDouFaResetTime(long currTime) {
		if (GameParameter.douFaResetDataTime <= currTime) {
			GameParameter.douFaResetDataTime = currTime + GameParameter.douFaResetDataOffset;
			return true;
		}
		return false;
	}

	public static long getDouFaOffsetResetTime() {
		return GameParameter.douFaResetDataOffset;
	}

	public static boolean isOpenWordBoss(long currTime) {
		if (GameParameter.openWordBoss <= currTime) {
			GameParameter.lastOpenWordBoss = currTime;
			GameParameter.openWordBoss = currTime + DateUtil.ONE_DAY_M;
			return true;
		}
		return false;
	}

	public static boolean isCloseWordBoss(long currTime) {
		if (GameParameter.closeWordBoss <= currTime) {
			GameParameter.lastCloseWordBoss = currTime;
			GameParameter.closeWordBoss = currTime + DateUtil.ONE_DAY_M;
			return true;
		}
		return false;
	}

	public static void setNextCloseWordBoss() {
		GameParameter.closeWordBoss += DateUtil.ONE_DAY_M;
	}

	public static long getOpenWordBoss() {
		return GameParameter.openWordBoss;
	}

	public static long getCloseWordBoss() {
		return GameParameter.closeWordBoss;
	}

	public static boolean isLivenessMissionReset(long currTime) {
		if (GameParameter.livenessMissionResetTime <= currTime) {
			GameParameter.livenessMissionResetTime = currTime + DateUtil.ONE_DAY_M;
			return true;
		}
		return false;
	}

	public static int getDouFaCostTiLi() {
		return GameParameter.douFaCostTiLi;
	}

	public static long getOneStandNextResetTime() {
		return GameParameter.oneStandResetDataTime;
	}

	public static boolean isOneStandResetTime(long currTime) {
		if (GameParameter.oneStandResetDataTime <= currTime) {
			GameParameter.oneStandResetDataTime = currTime + GameParameter.oneStandResetDataOffset;
			return true;
		}
		return false;
	}

	public static short getOpenOneStandLevel() {
		return GameParameter.openOneStandLevel;
	}

	public static int getWorldBossShortlyMoney() {
		return GameParameter.worldBossShortlyMoney;
	}

	public static String getOpenWorldBossTimeStr() {
		return GameParameter.openWordBossStr;
	}

	public static boolean isMonsterAtkCityStartNoticeTime(long currTime) {
		if (monsterAtkCityStartNoticeTime < currTime) {
			monsterAtkCityStartNoticeTime = currTime + DateUtil.ONE_DAY_M;
			return true;
		}
		return false;
	}

	public static boolean isMonsterAtkCityStartTime(long currTime) {
		if (monsterAtkCityStartTime < currTime) {
			monsterAtkCityStartTime = currTime + DateUtil.ONE_DAY_M;
			return true;
		}
		return false;
	}

	public static long getMonsterAtkCityStartTime() {
		return monsterAtkCityStartTime;
	}

	public static void setMonsterAtkCityStartTime(long monsterAtkCityStartTime) {
		GameParameter.monsterAtkCityStartTime = monsterAtkCityStartTime;
	}

	public static boolean isMonsterAtkCityEndNoticeTime(long currTime) {
		if (monsterAtkCityEndNoticeTime < currTime) {
			monsterAtkCityEndNoticeTime = currTime + DateUtil.ONE_DAY_M;
			return true;
		}
		return false;
	}

	public static boolean isMonsterAtkCityEndTime(long currTime) {
		if (monsterAtkCityEndTime < currTime) {
			monsterAtkCityNpcUpdateTime = monsterAtkCityStartTime + monsterAtkCityNpcOffsetTime;
			monsterAtkCityEndTime += DateUtil.ONE_DAY_M;
			return true;
		}
		return false;
	}

	public static boolean isMonsterAtkCityNpcUpdate(long currTime) {
		if (monsterAtkCityNpcUpdateTime < currTime) {
			monsterAtkCityNpcUpdateTime = currTime + monsterAtkCityNpcOffsetTime;
			return true;
		}
		return false;
	}

	public static boolean isMonsterAtkCityBossUpdate(long currTime) {
		if (monsterAtkCityBossUpdateTime < currTime) {
			monsterAtkCityBossHideTime = currTime + monsterAtkCityBossHideOffset;
			monsterAtkCityBossUpdateTime = monsterAtkCityStartTime + monsterAtkCityBossUpdateOffset;
			return true;
		}
		return false;
	}

	public static boolean isMonsterAtkCityBossHide(long currTime) {
		if (monsterAtkCityBossHideTime < currTime) {
			monsterAtkCityBossHideTime = monsterAtkCityBossUpdateTime + monsterAtkCityBossHideOffset;
			return true;
		}
		return false;
	}

	public static boolean isMonsterAtkCityBoss2Update(long currTime) {
		if (monsterAtkCityBossUpdateTime2 < currTime) {
			monsterAtkCityBossHideTime2 = currTime + monsterAtkCityBossHideOffset;
			monsterAtkCityBossUpdateTime2 = monsterAtkCityStartTime + monsterAtkCityBossUpdateOffset2;
			return true;
		}
		return false;
	}

	public static boolean isMonsterAtkCityBoss2Hide(long currTime) {
		if (monsterAtkCityBossHideTime2 < currTime) {
			monsterAtkCityBossHideTime2 = monsterAtkCityBossUpdateTime2 + monsterAtkCityBossHideOffset;
			return true;
		}
		return false;
	}

	public static int getPickFreeMaxCount() {
		return GameParameter.pickPetCardFreeMaxCount;
	}

	public static int getResetDoufaTimeYuanBao() {
		return GameParameter.resetDoufaTimeYuanBao;
	}

	public static void setResetDoufaTimeYuanBao(int resetDoufaTimeYuanBao) {
		GameParameter.resetDoufaTimeYuanBao = resetDoufaTimeYuanBao;
	}

	public final static long nextTimeMysteryShopRefresh() {
		long time = System.currentTimeMillis();

		for (int ti = 0; ti < mysteryShopRefreshTime.length; ti++) {
			if (mysteryShopRefreshTime[ti] < time) {
				mysteryShopRefreshTime[ti] += GameSystem.ONEDAYMS;
			}
		}
		time = Long.MAX_VALUE;
		for (int ti = 0; ti < mysteryShopRefreshTime.length; ti++) {
			if (mysteryShopRefreshTime[ti] < time) {
				time = mysteryShopRefreshTime[ti];
			}
		}
		return time;
	}

	public final static long nextTimeDouFaShopRefresh() {
		long time = System.currentTimeMillis();

		for (int ti = 0; ti < douFaShopRefreshTime.length; ti++) {
			if (douFaShopRefreshTime[ti] < time) {
				douFaShopRefreshTime[ti] += GameSystem.ONEDAYMS;
			}
		}
		time = Long.MAX_VALUE;
		for (int ti = 0; ti < douFaShopRefreshTime.length; ti++) {
			if (douFaShopRefreshTime[ti] < time) {
				time = douFaShopRefreshTime[ti];
			}
		}
		return time;
	}

	public final static long nextTimeOneStandShopRefresh() {
		long time = System.currentTimeMillis();

		for (int ti = 0; ti < oneStandShopRefreshTime.length; ti++) {
			if (oneStandShopRefreshTime[ti] < time) {
				oneStandShopRefreshTime[ti] += GameSystem.ONEDAYMS;
			}
		}
		time = Long.MAX_VALUE;
		for (int ti = 0; ti < oneStandShopRefreshTime.length; ti++) {
			if (oneStandShopRefreshTime[ti] < time) {
				time = oneStandShopRefreshTime[ti];
			}
		}
		return time;
	}

	public static float getDouFaHpMulti() {
		if (GameParameter.DF_HP < 1) {
			GameParameter.DF_HP = 1.0F;
		}
		return GameParameter.DF_HP;
	}

	public static float getDouFaAtkMulti() {
		if (GameParameter.DF_ATK < 1) {
			GameParameter.DF_ATK = 1.0F;
		}
		return GameParameter.DF_ATK;
	}

	public static float getDouFaDefMulti() {
		if (GameParameter.DF_DEF < 1) {
			GameParameter.DF_DEF = 1.0F;
		}
		return GameParameter.DF_DEF;
	}

	public static float getYzddHpMulti() {
		if (GameParameter.YZDD_HP < 1) {
			GameParameter.YZDD_HP = 1.0F;
		}
		return GameParameter.YZDD_HP;
	}

	public static float getYzddAtkMulti() {
		if (GameParameter.YZDD_ATK < 1) {
			GameParameter.YZDD_ATK = 1.0F;
		}
		return GameParameter.YZDD_ATK;
	}

	public static float getYzddDefMulti() {
		if (GameParameter.YZDD_DEF < 1) {
			GameParameter.YZDD_DEF = 1.0F;
		}
		return GameParameter.YZDD_DEF;
	}

	public static String getMonsterAtkOpenTimeStr() {
		return GameParameter.monsterAtkOpenTimeStr;
	}

	public static String getWorldBossOpenTimeStr() {
		return GameParameter.worldBossOpenTimeStr;
	}

	public static short getOpenSignAwardUiLevel() {
		return GameParameter.openSignAwardUiLevel;
	}

	public static int getUpdateDouFaCost() {
		return GameParameter.updateDouFaCost;
	}

	public static int getUpdateOneStandCost() {
		return GameParameter.updateOneStandCost;
	}

	public static boolean isNotMoveFindPathMission(int missionId) {
		return GameParameter.notMoveFindPathMission.indexOf(missionId) > -1;
	}

	public static int getFirstModelId() {
		return GameParameter.firstModelId;
	}

	public static int getSecendModelId() {
		return GameParameter.secendModelId;
	}

	public static String getMonsterAtkCityInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("开启时间：").append(DateUtil.formatDate(new Date(monsterAtkCityStartTime))).append("\r\n");
		sb.append("结束时间：").append(DateUtil.formatDate(new Date(monsterAtkCityEndTime))).append("\r\n");
		sb.append("怪物攻城小怪NPC刷新：").append(DateUtil.formatDate(new Date(monsterAtkCityNpcUpdateTime))).append("\r\n");
		sb.append("怪物攻城第一波BOSS怪刷出：").append(DateUtil.formatDate(new Date(monsterAtkCityBossUpdateTime))).append("\r\n");
		sb.append("怪物攻城第一波BOSS怪消失：").append(DateUtil.formatDate(new Date(monsterAtkCityBossHideTime))).append("\r\n");
		sb.append("怪物攻城第二波BOSS怪刷出：").append(DateUtil.formatDate(new Date(monsterAtkCityBossUpdateTime2))).append("\r\n");
		sb.append("怪物攻城第二波BOSS怪消失：").append(DateUtil.formatDate(new Date(monsterAtkCityBossHideTime2))).append("\r\n");
		sb.append("怪物攻城小怪NPC刷新时间（毫秒）：").append(monsterAtkCityNpcOffsetTime).append("\r\n");
		sb.append("怪物攻城开始多少时间(毫秒)后，出现第一波BOSS怪物：").append(monsterAtkCityBossUpdateOffset).append("\r\n");
		sb.append("怪物攻城BOSS出现后，持续多长时间(毫秒)：").append(monsterAtkCityBossHideOffset).append("\r\n");
		sb.append("怪物攻城开始多少时间(毫秒)后，出现第二波BOSS怪物：").append(monsterAtkCityBossUpdateOffset2).append("\r\n");
		return sb.toString();
	}

	public static String updateMonsterCity(String startTime, String stopTime, int monsterOffset, int boss_1_late, int boss_2_late, int bossContinue) {
		long currTime = System.currentTimeMillis();
		// if (currTime + 15 * 60 * 1000 > monsterAtkCityStartTime) {
		// return "参数修改失败，怪物攻城活动即将开始！";
		// } else {
		// if (monsterAtkCityStartTime > monsterAtkCityEndTime && currTime < monsterAtkCityEndTime) {
		// return "参数修改失败，怪物攻城活动正在进行中！";
		// }
		// }
		/*************************************************************************************************************************/
		GameParameter.monsterAtkCityStartTime = stringToMillisecond(startTime);
		/*************************************************************************************************************************/
		GameParameter.monsterAtkCityEndTime = stringToMillisecond(stopTime);
		if (currTime > GameParameter.monsterAtkCityEndTime) {
			GameParameter.monsterAtkCityStartTime += DateUtil.ONE_DAY_M;
			GameParameter.monsterAtkCityEndTime += DateUtil.ONE_DAY_M;
		}

		// 开始公告在开始前5分钟发布
		GameParameter.monsterAtkCityStartNoticeTime = GameParameter.monsterAtkCityStartTime - 5 * DateUtil.ONE_MINUTE_M;
		GameParameter.monsterAtkOpenTimeStr = "每日" + DateUtil.getPointHour(GameParameter.monsterAtkCityStartTime) + "-";

		// 结束公告在结束前5分钟发布
		GameParameter.monsterAtkCityEndNoticeTime = GameParameter.monsterAtkCityEndTime - 5 * DateUtil.ONE_MINUTE_M;
		GameParameter.monsterAtkOpenTimeStr = GameParameter.monsterAtkOpenTimeStr + DateUtil.getPointHour(GameParameter.monsterAtkCityEndTime)
		        + "点开启";
		/*************************************************************************************************************************/
		GameParameter.monsterAtkCityNpcOffsetTime = monsterOffset * DateUtil.ONE_MINUTE_M;
		GameParameter.monsterAtkCityNpcUpdateTime = GameParameter.monsterAtkCityStartTime + GameParameter.monsterAtkCityNpcOffsetTime;
		/*************************************************************************************************************************/
		// 活动开始后X分钟刷BOSS怪物
		GameParameter.monsterAtkCityBossUpdateOffset = boss_1_late * DateUtil.ONE_MINUTE_M;
		GameParameter.monsterAtkCityBossUpdateTime = GameParameter.monsterAtkCityStartTime + GameParameter.monsterAtkCityBossUpdateOffset;
		/*************************************************************************************************************************/
		GameParameter.monsterAtkCityBossHideOffset = bossContinue * DateUtil.ONE_MINUTE_M;
		GameParameter.monsterAtkCityBossHideTime = GameParameter.monsterAtkCityBossUpdateTime + GameParameter.monsterAtkCityBossHideOffset;
		/*************************************************************************************************************************/
		GameParameter.monsterAtkCityBossUpdateOffset2 = boss_2_late * DateUtil.ONE_MINUTE_M;
		GameParameter.monsterAtkCityBossUpdateTime2 = GameParameter.monsterAtkCityStartTime + GameParameter.monsterAtkCityBossUpdateOffset2;
		GameParameter.monsterAtkCityBossHideTime2 = GameParameter.monsterAtkCityBossUpdateTime2 + GameParameter.monsterAtkCityBossHideOffset;
		return "怪物攻城参数已经修改成功!";
	}

	public static long stringToMillisecond(String timeStr) {
		String dateString = DateUtil.getCurrentDate();
		String dateStartStr = dateString + " " + timeStr.trim();
		if (dateStartStr.length() == 16) {
			dateStartStr += ":00";
		}

		Date dateStart = DateUtil.stringToDate(dateStartStr);
		if (dateStart == null) {
			dateStart = DateUtil.stringToDate(DateUtil.getCurrentDate() + "20:00:00");
		}
		return dateStart.getTime();
	}

	public static String updateWorldBossTime(String startTime, String stopTime) {
		long currTime = System.currentTimeMillis();
		// if (currTime + 15 * 60 * 1000 > openWordBoss) {
		// return "参数修改失败，世界首领活动即将开始！";
		// } else {
		// if (openWordBoss > closeWordBoss && currTime < closeWordBoss) {
		// return "参数修改失败，世界首领活动正在进行中！";
		// }
		// }
		/*************************************************************************************************************************/
		GameParameter.openWordBossStr = startTime;
		GameParameter.lastOpenWordBoss = GameParameter.stringToMillisecond(GameParameter.openWordBossStr);
		GameParameter.openWordBoss = GameParameter.lastOpenWordBoss;
		/*************************************************************************************************************************/
		GameParameter.lastCloseWordBoss = GameParameter.stringToMillisecond(stopTime);
		if (currTime > GameParameter.lastCloseWordBoss) {
			GameParameter.openWordBoss = GameParameter.lastOpenWordBoss + DateUtil.ONE_DAY_M;
			GameParameter.closeWordBoss = GameParameter.lastCloseWordBoss + DateUtil.ONE_DAY_M;
		} else {
			GameParameter.closeWordBoss = GameParameter.lastCloseWordBoss;
		}
		GameParameter.worldBossOpenTimeStr = "每日" + DateUtil.getPointHour(GameParameter.openWordBoss) + "-";
		GameParameter.worldBossOpenTimeStr = GameParameter.worldBossOpenTimeStr + DateUtil.getPointHour(GameParameter.closeWordBoss) + "点开启";
		return "世界首领参数已经修改成功!";
	}

	public static void initRobotParameters() {
		if (GameParameter.robotStopTime < System.currentTimeMillis()) {
			GameParameter.robotStartTime += GameSystem.ONEDAYMS;
			GameParameter.robotStopTime += GameSystem.ONEDAYMS;
		}
	}

	public static boolean isRobotSwitch() {
		return robotSwitch;
	}

	public static void setRobotSwitch(boolean robotSwitch) {
		GameParameter.robotSwitch = robotSwitch;
	}

	public static int getRobotMaxCount() {
		return robotMaxCount;
	}

	public static void setRobotMaxCount(int robotMaxCount) {
		GameParameter.robotMaxCount = robotMaxCount;
	}

	public static int getRobotCreateOffsetSub() {
		return robotCreateOffsetSub;
	}

	public static void setRobotCreateOffsetSub(int robotCreateOffsetSub) {
		GameParameter.robotCreateOffsetSub = robotCreateOffsetSub;
	}

	public static int getRobotCreateOffsetSup() {
		return robotCreateOffsetSup;
	}

	public static void setRobotCreateOffsetSup(int robotCreateOffsetSup) {
		GameParameter.robotCreateOffsetSup = robotCreateOffsetSup;
	}

	public static long getRobotStartTime() {
		return robotStartTime;
	}

	public static void setRobotStartTime(long robotStartTime) {
		GameParameter.robotStartTime = robotStartTime;
	}

	public static long getRobotStopTime() {
		return robotStopTime;
	}

	public static void setRobotStopTime(long robotStopTime) {
		GameParameter.robotStopTime = robotStopTime;
	}

	public static void updateRobot(int maxCount, String startTime, String stopTime, String create) {
		GameParameter.robotMaxCount = maxCount;
		GameParameter.robotStartTime = GameParameter.stringToMillisecond(startTime);
		GameParameter.robotStopTime = GameParameter.stringToMillisecond(stopTime);
		int[] array = StringUtil.string2IntArray(create);
		if (array.length == 2) {
			GameParameter.robotCreateOffsetSub = array[0] * 1000;
			GameParameter.robotCreateOffsetSup = array[1] * 1000;
		} else {
			GameParameter.robotCreateOffsetSub = 5000;
			GameParameter.robotCreateOffsetSup = 2000;
		}
		GameParameter.initRobotParameters();
	}

	public static boolean isOpenNumberButton() {
		return openNumberButton;
	}

	public static boolean isOpenVipButton() {
		return openVipButton;
	}

	public static boolean isOpenAccountButton() {
		return openAccountButton;
	}

	public static int getResetSkillDataCost() {
		return resetSillDataCost;
	}

	public static int getPushSecretScene2WorldCool() {
		return pushSecretScene2WorldCool;
	}

	public static int getPushSecretScene2FriendCool() {
		return pushSecretScene2FriendCool;
	}

	public static long getGrabCoolTime() {
		return grabCoolTime;
	}

	public static void setGrabCoolTime(long grabCoolTime) {
		GameParameter.grabCoolTime = grabCoolTime;
	}
}

class ParseParameterXls extends AParseXLS {
	/** 文件SHEET名 */
	private static final String GAME_PARAMETERS = "游戏参数";
	private static final String PROPERTY_MULTI  = "属性倍率";

	public ParseParameterXls(String sourceFile) {
		super(sourceFile);
	}

	@Override
	protected boolean parse() {
		if (hwb != null) {
			/** 怪物兵种 */
			SheetParameter aiSheet = new SheetParameter(getSourceFile(), hwb.getSheet(GAME_PARAMETERS));
			aiSheet.execute();
			/** 属性倍率 */
			SheetPropertyMulti propertyMulti = new SheetPropertyMulti(getSourceFile(), hwb.getSheet(PROPERTY_MULTI));
			propertyMulti.execute();
		}
		return true;
	}

}

class SheetPropertyMulti extends AParseSheet {
	// 模块 HP 攻击 防御
	protected final static String COL_MODEL = "模块";
	protected final static String COL_HP    = "HP";
	protected final static String COL_ATK   = "攻击";
	protected final static String COL_DEF   = "防御";

	public SheetPropertyMulti(String sourceFile, HSSFSheet sheet) {
		super(sourceFile, sheet);
	}

	@Override
	public boolean parse() {
		String value = null;
		HSSFRow row = null;
		int rowCount = getLastRowNum();
		for (int ri = 1; ri <= rowCount; ri++) {
			row = getRow(ri);
			if (row != null) {
				value = getStringValue(row, COL_MODEL);
				if (value != null) {
					if (value.startsWith("斗法")) {
						GameParameter.DF_HP = getFloatValueRelax(row, COL_HP);
						GameParameter.DF_ATK = getFloatValueRelax(row, COL_ATK);
						GameParameter.DF_DEF = getFloatValueRelax(row, COL_DEF);
					} else if (value.startsWith("一战")) {
						GameParameter.YZDD_HP = getFloatValueRelax(row, COL_HP);
						GameParameter.YZDD_ATK = getFloatValueRelax(row, COL_ATK);
						GameParameter.YZDD_DEF = getFloatValueRelax(row, COL_DEF);
					} else {
						// TODO 其他的模块

					}
				}
			}
		}
		return true;
	}

}

class SheetParameter extends AParseSheet {
	protected final static String COL_ID    = "参数ID";
	protected final static String COL_VALUE = "参数值";

	public SheetParameter(String sourceFile, HSSFSheet sheet) {
		super(sourceFile, sheet);
	}

	@Override
	public boolean parse() {

		HSSFRow row = null;
		int rowCount = getLastRowNum();
		for (int ri = 1; ri <= rowCount; ri++) {
			row = getRow(ri);
			if (row != null) {
				int parameterId = 0;
				try {
					parameterId = getIntValue(row, COL_ID);
					switch (parameterId) {
						case 1: {
							GameParameter.openGMcommand = "true".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 2: {
							GameParameter.tempRelationMax = getIntValue(row, COL_VALUE);
							break;
						}
						case 3: {
							GameParameter.realPointMax = getIntValue(row, COL_VALUE);
							break;
						}
						case 4: {
							GameParameter.roleMaxLevel = getShortValue(row, COL_VALUE);
							break;
						}
						case 5: {
							GameParameter.skillPetMaxLevel = getShortValue(row, COL_VALUE);
							break;
						}
						case 6: {
							GameParameter.gamePetMaxLevel = getShortValue(row, COL_VALUE);
							break;
						}
						case 7: {
							GameParameter.rankListRefresh = getIntValue(row, COL_VALUE) * 1000 * 60;
							RankManager.getInstance().resetRankOffset();
							break;
						}
						case 8: {
							GameParameter.logRoleCountOffset = getIntValue(row, COL_VALUE) * 1000 * 60;
							break;
						}
						case 9: {
							GameParameter.resaveOffset = getIntValue(row, COL_VALUE) * 1000 * 60;
							break;
						}
						case 10: {
							GameParameter.addHpOffset = getIntValue(row, COL_VALUE) * 1000;
							break;
						}
						case 11: {
							GameParameter.reconnctTime = getIntValue(row, COL_VALUE) * 1000 * 60;
							break;
						}
						case 12: {
							GameParameter.addJingLi = getIntValue(row, COL_VALUE) * 1000 * 60;
							break;
						}
						case 13: {
							GameParameter.averageLeveOffset = getIntValue(row, COL_VALUE) * 1000 * 60;
							break;
						}
						case 14: {
							GameParameter.starTime = getIntValue(row, COL_VALUE) * 1000 * 60;
							break;
						}
						case 15: {
							GameParameter.protectedTime = getIntValue(row, COL_VALUE) * 1000 * 60;
							break;
						}
						case 16: {
							GameParameter.petReliveTime = getIntValue(row, COL_VALUE) * 1000;
							break;
						}
						case 17: {
							GameParameter.worldBossReplayTime = getIntValue(row, COL_VALUE) * 1000;
							break;
						}
						case 18: {
							GameParameter.baseIntegral = getIntValue(row, COL_VALUE);
							break;
						}
						case 19: {
							GameParameter.transferReel = getIntValue(row, COL_VALUE);
							break;
						}
						case 20: {
							GameParameter.transferPrice = getIntValue(row, COL_VALUE);
							break;
						}
						case 21: {
							GameParameter.worldChatOffset = getIntValue(row, COL_VALUE) * 1000;
							break;
						}
						case 22: {
							GameParameter.worldChatCount = getIntValue(row, COL_VALUE);
							break;
						}
						case 23: {
							GameParameter.rate = (byte) getShortValue(row, COL_VALUE);
							break;
						}
						case 24: {
							GameParameter.sceneMijing = getIntValue(row, COL_VALUE);
							break;
						}
						case 25: {
							int distance = getIntValue(row, COL_VALUE);
							GameParameter.npcMaxDistance = distance * distance;
							break;
						}
						case 26: {
							GameParameter.materialCount = getIntValue(row, COL_VALUE);
							break;
						}
						case 27: {
							GameParameter.taskUpperLimit = getIntValue(row, COL_VALUE);
							break;
						}
						case 28: {
							GameParameter.shenjieFabaoCount = getIntValue(row, COL_VALUE);
							break;
						}
						case 29: {
							GameParameter.expMultiple = getIntValue(row, COL_VALUE);
							break;
						}
						case 30: {
							GameParameter.belongPercent = getIntValue(row, COL_VALUE);
							break;
						}
						case 31: {
							GameParameter.changeHitTarget = getIntValue(row, COL_VALUE);
							break;
						}
						case 32: {
							GameParameter.todayKillMonsterMax = getIntValue(row, COL_VALUE);
							break;
						}
						case 33: {
							GameParameter.yesterdayResidueKill = getIntValue(row, COL_VALUE);
							break;
						}
						case 34: {
							GameParameter.tiredRefreshTime = getIntValue(row, COL_VALUE) * 60;
							break;
						}
						case 35: {
							GameParameter.heroRefreshTime = getIntValue(row, COL_VALUE) * 60;
							break;
						}
						case 36: {
							GameParameter.killModelLevel = getIntValue(row, COL_VALUE);
							break;
						}
						case 37: {
							GameParameter.killModelInfo = getStringValue(row, COL_VALUE);
							break;
						}
						case 38: {
							GameParameter.resetGoodsId = getIntValue(row, COL_VALUE);
							break;
						}
						case 39: {
							GameParameter.resetGoodsCount = getIntValue(row, COL_VALUE);
							break;
						}
						case 40: {
							GameParameter.canResolveLevel = getShortValue(row, COL_VALUE);
							break;
						}
						case 41: {
							GameParameter.sectTopNumber = getIntValue(row, COL_VALUE);
							break;
						}
						case 42: {
							GameParameter.isRebot = "true".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 43: {
							int[] array = StringUtil.string2IntArray(getStringValue(row, COL_VALUE), '~');
							if (array.length == 2) {
								GameParameter.gmAccountSub = array[0];
								GameParameter.gmAccountSup = array[1];
							}
							break;
						}
						case 44: {
							GameParameter.addJingLiEveryTime = getIntValue(row, COL_VALUE);
							if (GameParameter.addJingLiEveryTime < 1) {
								GameParameter.addJingLiEveryTime = 1;
							}
							break;
						}
						case 45: {
							GameParameter.petDefault = getIntValue(row, COL_VALUE);
							break;
						}
						case 46: {
							GameParameter.helpWarTime = getIntValue(row, COL_VALUE);
							break;
						}

						case 47: {
							GameParameter.pickOutCoolStar_3_5 = getIntValue(row, COL_VALUE) * 60 * 1000;
							break;
						}
						case 48: {
							GameParameter.pickOutCoolStar_4_5 = getIntValue(row, COL_VALUE) * 60 * 1000;
							break;
						}
						case 49: {
							GameParameter.replayMp = getIntValue(row, COL_VALUE);
							break;
						}
						case 50: {
							GameParameter.openWordBossStr = getStringValue(row, COL_VALUE);
							GameParameter.lastOpenWordBoss = GameParameter.stringToMillisecond(GameParameter.openWordBossStr);
							GameParameter.openWordBoss = GameParameter.lastOpenWordBoss;
							GameParameter.worldBossOpenTimeStr = "每日" + DateUtil.getPointHour(GameParameter.openWordBoss) + "-";
							break;
						}
						case 51: {
							GameParameter.addTiLi = getIntValue(row, COL_VALUE) * 1000 * 60;
							break;
						}
						case 52: {
							GameParameter.addTiLiEveryTime = getIntValue(row, COL_VALUE);
							break;
						}
						case 53: {
							GameParameter.arenaMaxTime = getIntValue(row, COL_VALUE);
							break;
						}
						case 54: {
							GameParameter.lastCloseWordBoss = GameParameter.stringToMillisecond(getStringValue(row, COL_VALUE));
							long currTime = System.currentTimeMillis();
							if (currTime > GameParameter.lastCloseWordBoss) {
								GameParameter.openWordBoss = GameParameter.lastOpenWordBoss + DateUtil.ONE_DAY_M;
								GameParameter.closeWordBoss = GameParameter.lastCloseWordBoss + DateUtil.ONE_DAY_M;
							} else {
								GameParameter.closeWordBoss = GameParameter.lastCloseWordBoss;
							}
							GameParameter.worldBossOpenTimeStr = GameParameter.worldBossOpenTimeStr
							        + DateUtil.getPointHour(GameParameter.closeWordBoss) + "点开启";
							break;
						}
						case 55: {
							GameParameter.worldBossAwardEffectiveTime = getIntValueRelax(row, COL_VALUE) * 60 * 60 * 1000;
							break;
						}
						case 56: {
							GameParameter.douFaAwardEffectiveTime = getIntValueRelax(row, COL_VALUE) * 60 * 60 * 1000;
							break;
						}
						case 57: {
							GameParameter.miJingAwardEffectiveTime = getIntValueRelax(row, COL_VALUE) * 60 * 60 * 1000;
							break;
						}
						case 58: {
							GameParameter.oneStandAwardEffectiveTime = getIntValueRelax(row, COL_VALUE) * 60 * 60 * 1000;
							break;
						}
						case 59: {
							long currTime = System.currentTimeMillis();
							GameParameter.douFaAwardTimeMin = GameParameter.stringToMillisecond(getStringValue(row, COL_VALUE));
							if (currTime > GameParameter.douFaAwardTimeMin) {
								GameParameter.douFaAwardTimeMin += DateUtil.ONE_DAY_M;
							}
							break;
						}
						case 60: {
							long currTime = System.currentTimeMillis();
							GameParameter.oneStandAwardTimeMin = GameParameter.stringToMillisecond(getStringValue(row, COL_VALUE));
							if (currTime > GameParameter.oneStandAwardTimeMin) {
								GameParameter.oneStandAwardTimeMin += DateUtil.ONE_DAY_M;
							}
							break;
						}
						case 61: {
							GameParameter.commonLevelPercent = getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 62: {
							GameParameter.diffcultLevelPercent = getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 63: {
							GameParameter.douFaResetDataTime = DateUtil.stringToDec(getStringValue(row, COL_VALUE), DateUtil.DATE_FORMAT);
							break;
						}
						case 64: {
							GameParameter.douFaResetDataOffset = getIntValueRelax(row, COL_VALUE) * DateUtil.ONE_DAY_M;
							GameParameter.douFaResetDataOffset = GameParameter.douFaResetDataOffset <= 0 ? DateUtil.ONE_DAY_M * 7
							        : GameParameter.douFaResetDataOffset;
							long offset = System.currentTimeMillis() - GameParameter.douFaResetDataTime;
							if (offset > 0) {
								int value = (int) (offset / GameParameter.douFaResetDataOffset);
								long mode = offset % GameParameter.douFaResetDataOffset;
								if (mode == 0) {
									GameParameter.douFaResetDataTime += (value * GameParameter.douFaResetDataOffset);
								} else {
									GameParameter.douFaResetDataTime += ((value + 1) * GameParameter.douFaResetDataOffset);
								}
							}
							break;
						}
						case 65: {
							GameParameter.oneStandResetDataTime = DateUtil.stringToDec(getStringValue(row, COL_VALUE), DateUtil.DATE_FORMAT);
							break;
						}
						case 66: {
							GameParameter.oneStandResetDataOffset = getIntValueRelax(row, COL_VALUE) * DateUtil.ONE_DAY_M;
							GameParameter.oneStandResetDataOffset = GameParameter.oneStandResetDataOffset <= 0 ? DateUtil.ONE_DAY_M
							        : GameParameter.oneStandResetDataOffset;
							long offset = System.currentTimeMillis() - GameParameter.oneStandResetDataTime;
							if (offset > 0) {
								int value = (int) (offset / GameParameter.oneStandResetDataOffset);
								long mode = offset % GameParameter.oneStandResetDataOffset;
								if (mode == 0) {
									GameParameter.oneStandResetDataTime += (value * GameParameter.oneStandResetDataOffset);
								} else {
									GameParameter.oneStandResetDataTime += ((value + 1) * GameParameter.oneStandResetDataOffset);
								}
							}
							break;
						}
						case 67: {
							long currTime = System.currentTimeMillis();
							GameParameter.livenessMissionResetTime = GameParameter.stringToMillisecond(getStringValue(row, COL_VALUE));
							if (currTime > GameParameter.livenessMissionResetTime) {
								GameParameter.livenessMissionResetTime += DateUtil.ONE_DAY_M;
							}
							break;
						}
						case 68: {
							GameParameter.douFaCostTiLi = getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 69: {
							GameParameter.openOneStandLevel = (short) getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 70: {
							GameParameter.worldBossShortlyMoney = getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 71: {
							GameParameter.monsterAtkCityStartTime = GameParameter.stringToMillisecond(getStringValue(row, COL_VALUE));
							break;
						}
						case 72: {
							GameParameter.monsterAtkCityEndTime = GameParameter.stringToMillisecond(getStringValue(row, COL_VALUE));
							long currTime = System.currentTimeMillis();
							if (currTime > GameParameter.monsterAtkCityEndTime) {
								GameParameter.monsterAtkCityStartTime += DateUtil.ONE_DAY_M;
								GameParameter.monsterAtkCityEndTime += DateUtil.ONE_DAY_M;
							}

							// 开始公告在开始前5分钟发布
							GameParameter.monsterAtkCityStartNoticeTime = GameParameter.monsterAtkCityStartTime - 5 * DateUtil.ONE_MINUTE_M;
							GameParameter.monsterAtkOpenTimeStr = "每日" + DateUtil.getPointHour(GameParameter.monsterAtkCityStartTime) + "-";

							// 结束公告在结束前5分钟发布
							GameParameter.monsterAtkCityEndNoticeTime = GameParameter.monsterAtkCityEndTime - 5 * DateUtil.ONE_MINUTE_M;
							GameParameter.monsterAtkOpenTimeStr = GameParameter.monsterAtkOpenTimeStr
							        + DateUtil.getPointHour(GameParameter.monsterAtkCityEndTime) + "点开启";
							break;
						}
						case 73: {
							GameParameter.monsterAtkCityNpcOffsetTime = getIntValueRelax(row, COL_VALUE) * DateUtil.ONE_MINUTE_M;
							GameParameter.monsterAtkCityNpcUpdateTime = GameParameter.monsterAtkCityStartTime
							        + GameParameter.monsterAtkCityNpcOffsetTime;
							break;
						}
						case 74: {
							// 活动开始后X分钟刷BOSS怪物
							GameParameter.monsterAtkCityBossUpdateOffset = getIntValueRelax(row, COL_VALUE) * DateUtil.ONE_MINUTE_M;
							GameParameter.monsterAtkCityBossUpdateTime = GameParameter.monsterAtkCityStartTime
							        + GameParameter.monsterAtkCityBossUpdateOffset;
							break;
						}
						case 75: {
							GameParameter.monsterAtkCityBossHideOffset = getIntValueRelax(row, COL_VALUE) * DateUtil.ONE_MINUTE_M;
							GameParameter.monsterAtkCityBossHideTime = GameParameter.monsterAtkCityBossUpdateTime
							        + GameParameter.monsterAtkCityBossHideOffset;
							break;
						}
						case 76: {
							GameParameter.monsterAtkCityBossUpdateOffset2 = getIntValueRelax(row, COL_VALUE) * DateUtil.ONE_MINUTE_M;
							GameParameter.monsterAtkCityBossUpdateTime2 = GameParameter.monsterAtkCityStartTime
							        + GameParameter.monsterAtkCityBossUpdateOffset2;
							GameParameter.monsterAtkCityBossHideTime2 = GameParameter.monsterAtkCityBossUpdateTime2
							        + GameParameter.monsterAtkCityBossHideOffset;
							break;
						}
						case 77: {
							// 技能点每次回复值
							GameParameter.addSkillPointEveryTime = getIntValue(row, COL_VALUE);
							if (GameParameter.addSkillPointEveryTime < 1) {
								GameParameter.addSkillPointEveryTime = 1;
							}
							break;
						}
						case 78: {
							// 技能回复间隔
							GameParameter.addSkillPoint = getIntValue(row, COL_VALUE) * 1000 * 60;
							break;
						}
						case 79: {
							// 重置斗法时间消耗的元宝数量
							GameParameter.resetDoufaTimeYuanBao = getIntValue(row, COL_VALUE);
							break;
						}
						case 80: {
							// 记录对战记录最大条数
							GameParameter.challengeLogCount = getIntValue(row, COL_VALUE);
							break;
						}
						case 81: {
							// 记录对战记录最大条数
							GameParameter.setRankShowCount(getIntValue(row, COL_VALUE));
							break;
						}
						case 82: {
							String[] array = StringUtil.splitString(getStringValue(row, COL_VALUE), ',');
							List<Long> times = new ArrayList<Long>();

							String dateString = DateUtil.getCurrentDate();
							for (int ti = 0; ti < array.length; ti++) {
								String dateStartStr = dateString + " " + array[ti].trim();
								if (dateStartStr.length() == 16) {
									dateStartStr += ":00";
								}

								if (dateStartStr.length() != 19) {
									continue;
								}

								Date dateStart = DateUtil.stringToDate(dateStartStr);
								if (dateStart == null) {
									continue;
								}

								long timeStart = dateStart.getTime();
								if (timeStart < System.currentTimeMillis()) {
									timeStart += GameSystem.ONEDAYMS;
								}
								times.add(timeStart);
							}
							if (times.size() < 1) {
								String dateStartStr = dateString + " 18:00:00";
								Date dateStart = DateUtil.stringToDate(dateStartStr);
								long timeStart = dateStart.getTime();
								if (timeStart < System.currentTimeMillis()) {
									timeStart += GameSystem.ONEDAYMS;
								}
								times.add(timeStart);
							}
							long[] timeArray = new long[times.size()];
							for (int ti = 0; ti < timeArray.length; ti++) {
								timeArray[ti] = times.get(ti);
							}
							GameParameter.mysteryShopRefreshTime = timeArray;
							break;
						}
						case 83: {
							GameParameter.openSendDataToBi = "true".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 84: {
							GameParameter.openSignAwardUiLevel = (short) getIntValue(row, COL_VALUE);
							break;
						}
						case 85: {
							String[] array = StringUtil.splitString(getStringValue(row, COL_VALUE), ',');
							List<Long> times = new ArrayList<Long>();

							String dateString = DateUtil.getCurrentDate();
							for (int ti = 0; ti < array.length; ti++) {
								String dateStartStr = dateString + " " + array[ti].trim();
								if (dateStartStr.length() == 16) {
									dateStartStr += ":00";
								}

								if (dateStartStr.length() != 19) {
									continue;
								}

								Date dateStart = DateUtil.stringToDate(dateStartStr);
								if (dateStart == null) {
									continue;
								}

								long timeStart = dateStart.getTime();
								if (timeStart < System.currentTimeMillis()) {
									timeStart += GameSystem.ONEDAYMS;
								}
								times.add(timeStart);
							}
							if (times.size() < 1) {
								String dateStartStr = dateString + " 18:00:00";
								Date dateStart = DateUtil.stringToDate(dateStartStr);
								long timeStart = dateStart.getTime();
								if (timeStart < System.currentTimeMillis()) {
									timeStart += GameSystem.ONEDAYMS;
								}
								times.add(timeStart);
							}
							long[] timeArray = new long[times.size()];
							for (int ti = 0; ti < timeArray.length; ti++) {
								timeArray[ti] = times.get(ti);
							}
							GameParameter.douFaShopRefreshTime = timeArray;
							break;
						}
						case 86: {
							String[] array = StringUtil.splitString(getStringValue(row, COL_VALUE), ',');
							List<Long> times = new ArrayList<Long>();

							String dateString = DateUtil.getCurrentDate();
							for (int ti = 0; ti < array.length; ti++) {
								String dateStartStr = dateString + " " + array[ti].trim();
								if (dateStartStr.length() == 16) {
									dateStartStr += ":00";
								}

								if (dateStartStr.length() != 19) {
									continue;
								}

								Date dateStart = DateUtil.stringToDate(dateStartStr);
								if (dateStart == null) {
									continue;
								}

								long timeStart = dateStart.getTime();
								if (timeStart < System.currentTimeMillis()) {
									timeStart += GameSystem.ONEDAYMS;
								}
								times.add(timeStart);
							}
							if (times.size() < 1) {
								String dateStartStr = dateString + " 18:00:00";
								Date dateStart = DateUtil.stringToDate(dateStartStr);
								long timeStart = dateStart.getTime();
								if (timeStart < System.currentTimeMillis()) {
									timeStart += GameSystem.ONEDAYMS;
								}
								times.add(timeStart);
							}
							long[] timeArray = new long[times.size()];
							for (int ti = 0; ti < timeArray.length; ti++) {
								timeArray[ti] = times.get(ti);
							}
							GameParameter.oneStandShopRefreshTime = timeArray;
							break;
						}
						case 87: {
							GameParameter.updateDouFaCost = getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 88: {
							GameParameter.updateOneStandCost = getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 89: {
							String[] values = StringUtil.splitString(getStringValue(row, COL_VALUE), ',');
							if (values != null && values.length > 0) {
								for (int i = 0; i < values.length; i++) {
									try {
										GameParameter.notMoveFindPathMission.add(Integer.parseInt(values[i]));
									} catch (Exception e) {
										continue;
									}
								}
							}
							break;
						}
						case 90: {
							SpeedConfig.ROLE_MOVE_SPEED = getShortValueRelax(row, COL_VALUE);
							break;
						}
						case 91: {
							SpeedConfig.MONSTER_MOVE_SPEED = getShortValueRelax(row, COL_VALUE);
							break;
						}
						case 92: {
							SpeedConfig.BASE_SPEED = getShortValueRelax(row, COL_VALUE);
							break;
						}
						case 93: {
							GameParameter.firstModelId = getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 94: {
							GameParameter.secendModelId = getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 95: {
							GameParameter.toRoleList = "TRUE".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 96: {
							GameParameter.toTeachScene = "TRUE".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 97: {
							GameParameter.robotSwitch = "TRUE".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 98: {
							GameParameter.robotMaxCount = Integer.parseInt(getStringValue(row, COL_VALUE));
							break;
						}
						case 99: {
							GameParameter.robotStartTime = GameParameter.stringToMillisecond(getStringValue(row, COL_VALUE));
							break;
						}
						case 100: {
							GameParameter.robotStopTime = GameParameter.stringToMillisecond(getStringValue(row, COL_VALUE));
							break;
						}
						case 101: {
							int[] array = StringUtil.string2IntArray(getStringValue(row, COL_VALUE), ',');
							if (array.length == 2) {
								GameParameter.robotCreateOffsetSub = array[0] * 1000;
								GameParameter.robotCreateOffsetSup = array[1] * 1000;
							} else {
								GameParameter.robotCreateOffsetSub = 5000;
								GameParameter.robotCreateOffsetSup = 2000;
							}

							GameParameter.initRobotParameters();
							break;
						}
						case 102: {
							GameParameter.openNumberButton = "true".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 103: {
							GameParameter.openVipButton = "true".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 104: {
							GameParameter.openAccountButton = "true".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 105: {
							GameParameter.resetSillDataCost = Integer.parseInt(getStringValue(row, COL_VALUE));
							break;
						}
						case 106: {
							GameParameter.pushSecretScene2WorldCool = Integer.parseInt(getStringValue(row, COL_VALUE)) * 1000;
							break;
						}
						case 107: {
							GameParameter.pushSecretScene2FriendCool = Integer.parseInt(getStringValue(row, COL_VALUE)) * 1000;
							break;
						}
						case 108: {
							GameParameter.grabRate = getIntValueRelax(row, COL_VALUE);
							break;
						}
						case 109: {
							int value = getIntValueRelax(row, COL_VALUE);
							if (value < 1) {
								value = 60;
							}
							GameParameter.grabCoolTime = value * 60 * 1000;
							break;
						}
						case 110: {
							GameParameter.challengeSelf = "true".equalsIgnoreCase(getStringValue(row, COL_VALUE));
							break;
						}
						case 111: {
							int value = getIntValueRelax(row, COL_VALUE);
							if (value < 0) {
								value = 0;
							}
							GameParameter.challengeCooltime = value * 60 * 1000;
							break;
						}
						case 112: {
							int value = getIntValueRelax(row, COL_VALUE);
							if (value < 1) {
								value = 1;
							}
							GameParameter.treasureMapLevel = value;
							break;
						}
					}
				} catch (Exception e) {
					error(ri, COL_ID, getStringValue(row, COL_ID), e);
				}
			}
		}
		return true;
	}

}
