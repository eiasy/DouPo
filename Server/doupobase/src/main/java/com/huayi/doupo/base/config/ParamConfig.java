package com.huayi.doupo.base.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import com.huayi.doupo.base.config.sdk.SdkConfig;
import com.huayi.doupo.base.model.player.DelayRechargePlayer;
import com.huayi.doupo.base.model.socket.WorldBossPlayer;


/**
 * 参数配置类
 * @author mp
 * @date 2014-7-30 下午5:05:20
 */
public class ParamConfig {
	
	/**
	 * 是否使用活动服更新活动数据 0-不使用 1-使用
	 */
	public static int isUserActivity = 1;
	
	/**
	 * 最大在线人数
	 */
	public static int maxOnlineNum = 0;
	
	/**
	 * 最大在线人数时间点
	 */
	public static String maxOnlineTime = "";
	
	/**
	 * 最小在线人数
	 */
	public static int minOnlineNum = 1000;
	
	/**
	 * 最小在线人数时间点
	 */
	public static String minOnlineTime = "";
	
	/**
	 * Boss初始血量   已废弃
	 */
	public static int initWorldBossBlood = 5000;
	
	/**
	 * Boss血量
	 */
	public static int worldBossBlood = 0;
	
	/**
	 * Boss初始血量
	 */
	public static int worldBossInitBlood = 0;
	
	/**
	 * Boss的状态 0-倒计时 1-进行时
	 */
	public static int worldBossstate = 0;
	
	/**
	 * 最大打Boss的人数
	 */
	public static int maxWorldBossNum = 0;
	
	/**
	 * 清除退出游戏几个小时的人
	 */
	public static int clearHourNum = 2;
	
	/**
	 * 可允许的玩家数量， 默认为4000人
	 */
	public static int canAllowPlayerNum = 7000;

	/**
	 * 打Boss时，可允许的在线玩家数量， 默认为300人
	 */
	public static int canAllowHitBossPlayerNum = 300; 
	
	/**
	 * 可注册状态， 1-可以注册  0-不可注册，默认为1
	 */
	public static int canRegeditState = 1;
	
	/**
	 * 充值通道Id, 长连接，不受心跳影响
	 */
	public static String rechargeChannelId = "";
	
	/**
	 * Gm通道Id, 长连接，不受心跳影响
	 */
	public static String gmChannelId = "";
	
	/**
	 * 开服时间-开服2小时后开始统计最小在线人数 
	 */
	public static long startServerTime = 0;
	
	/**
	 * 是否使用缓存 - 默认使用
	 */
	public static boolean isUseCache = true;
	
	/**
	 * 关服状态-默认false
	 */
	public static boolean closeServer = false;
	
	/**
	 * 竞技场对象所(注：零长度的byte数组对象创建起来将比任何对象都经济――查看编译后的字节码：生成零长度的byte[]对象只需3条操作码，而Object lock= new Object()则需要7行操作码。)
	 */
	public static byte[] arenaLock = new byte[0]; 
	
	/**
	 * 用于竞技场初始获取名次同步
	 */
	public static byte[] arenaRankLock = new byte[0]; 
	
	/**
	 * 用于联盟同意同步
	 */
	public static byte[] agreeApply = new byte[0];
	
	/**
	 * 用于联盟弹劾同步
	 */
	public static byte[] agreeAccuse = new byte[0];

	/**
	 * 用于资源矿同步
	 */
	public static byte[] mineLock = new byte[0];
	
	/**
	 * 第一次洗澡开始时间
	 */
	public static String firstWashStartTime = "10:00:00";
	
	/**
	 * 第一次下澡结束时间
	 */
	public static String firstWashEndTime = "14:00:00";
	
	/**
	 * 第二次洗澡开始时间
	 */
	public static String lastWashStartTime = "17:00:00";
	
	/**
	 * 第二次下澡结束时间
	 */
	public static String lastWashEndTime = "21:00:00";
	
	/**
	 * 名字第一部分
	 */
	public static List<String> nameFirstList = null;

	/**
	 * 名字第二部分
	 */
	public static List<String> nameSecondList = null;
	
	/**
	 * 名字第三部分
	 */
	public static List<String> nameThridList = null;
	
	/**
	 * 充值延迟队列
	 */
	public static BlockingQueue<DelayRechargePlayer> blockingQueue = new LinkedBlockingQueue<DelayRechargePlayer>();

	/**
	 * 跨天玩家信息 
	 */
	public static BlockingQueue<String> acrossDayQueue = new LinkedBlockingQueue<String>();
	
	/**
	 * SDK配置类
	 */
	public static SdkConfig sdkConfig = null;
	
	/**
	 * netty worker工作线程数-cpu倍数
	 */
	public static int cpuMom = 0;
	
	/**
	 * 联盟缓存表 key=unionId, value=unionName
	 */
	public static ConcurrentHashMap<Integer, String> unionMap = new ConcurrentHashMap<Integer, String>();
	
	/**
	 * 玩家联盟关系表 key=instPlayerId, value=unionId
	 */
	public static ConcurrentHashMap<Integer, Integer> unionPlayer = new ConcurrentHashMap<Integer, Integer>();
	
	/**
	 * 跨天给登录服发送请求的url地址
	 */
	public static String serverAcrossDayUrl = "";
	
	/**
	 * 聊天cd时间 - 秒
	 */
	public static int chatCd = 5;
	
	/**
	 * 所有在删档测试和收费删档来过的玩家的openid
	 */
	public static HashSet<String> openIds = new HashSet<String>();
	
	/**
	 * 充值返利map [openId, 总共充了多少人民币]
	 */
	public static HashMap<String, Integer> rechargeMap = new HashMap<String, Integer>();
	
	/**
	 * 充值返利规则 [天数, 返百分之多少] 每日返还比例-一共返还7天
	 */
	public static Map<Integer, Float> rechargeRetRuleMap = new HashMap<Integer, Float> ();
	
	/**
	 * 是否打开非验证模式, 默认为0-否  1-是
	 */
	public static int isOpenNoEnv = 0;
	
	/**
	 * 购买开服基金总人数
	 */
	public static int sumBuyFundNum = 0;
	
	/**
	 * 存储玩家名字
	 */
	public static ConcurrentHashMap<Integer, String> playerNameMap = new ConcurrentHashMap<Integer, String>();
	
	/**
	 * 限时英雄招募积分-所有玩家活动期内的,过期clear
	 */
	public static ConcurrentHashMap<Integer, Integer> recruitJifenMap = new ConcurrentHashMap<Integer, Integer>();
	
	/**
	 * 限时英雄招募积分奖励  InstPlayerId   <Day , 积分>
	 */
	public static ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>> recruitJifenRewardMap = new ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>>();
	
	/**
	 * 限时英雄招募排行奖励  InstPlayerId <Day , 排行>
	 */
	public static ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>> recruitRankRewardMap = new ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>>();
	
	/**
	 * 最强英雄积分-所有玩家活动期内的,活动过期clear
	 */
	public static ConcurrentHashMap<Integer, Integer> strogerHeroJifenMap = new ConcurrentHashMap<Integer, Integer>();
	
	/**
	 * 最强英雄排名奖励  InstPlayerId   <Day , 排行> 只给前20名发
	 */
	public static ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>> strogerRankRewardMap = new ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>>();
	
	/**
	 * 最强英雄每天12点执行发奖任务调度器
	 */
	public static ScheduledExecutorService exec12 = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * 最强英雄每天21点执行发奖任务调度器
	 */
	public static ScheduledExecutorService exec21 = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * 最强英雄每天23点执行发奖任务调度器
	 */
	public static ScheduledExecutorService exec23 = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * 每小时检测一下玩家缓存对象,将2[gm工具可调配]个小时之前且不在线的玩家去掉
	 */
	public static ScheduledExecutorService exec1Hour = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * 开服时间
	 */
	public static String openServerTime = "";
	
	/**
	 * 是否开启向客服工具推送数据-默认开启--后修改为关闭了
	 */
	public static int isOpenPushDataCenter = 0;

	/**
	 *能允许的最大注册人数-默认99万
	 */
	public static int canAllowMaxRegeditNum = 990000;
	
	/**
	 * 玩家抢夺随机验证码map
	 */
	public static ConcurrentHashMap<Integer, String> playerLootMap = new ConcurrentHashMap<Integer, String>();

	/**
	 * 世界boss排名缓存
	 */
	public static List<WorldBossPlayer> worldBossRankList = new ArrayList<WorldBossPlayer>();
	
	/**
	 * 巅峰强者每个阶段的第一名名称,用分号分开
	 */
	public static String strogherHeroNumOnes = "";
	
	/**
	 * 获取SDK配置文件中的值
	 * @author mp
	 * @date 2015-6-30 下午3:08:43
	 * @param channel
	 * @param key
	 * @return
	 * @Description
	 */
	public static String getSdkValue (String channel, String key) {
		if (sdkConfig != null) {
			return ParamConfig.sdkConfig.getSdkMap().get(channel).get(key);
		}
		return null;
	}
	
}
