package mmo.common.protocol.game.operate;

/**
 * 聊天相关的频道设置
 * 
 * @author 李天喜
 * 
 */
public interface ChatChannel {
	/** * 战斗 -等级-经验-境界-PK-技能升级 */
	public final static byte CHANNEL_FIGHT        = 1;
	/** * 公告 */
	public final static byte CHANNEL_NOTICE       = 2;
	/** * 世界 */
	public final static byte CHANNEL_WORLD        = 3;
	/** * 区域 */
	public final static byte CHANNEL_ZONE         = 4;
	/** * 门派 */
	public final static byte CHANNEL_MARTIAL      = 5;
	/** * 门派信息 */
	public final static byte CHANNEL_MARTIAL_INFO = 6;
	/** * 队伍 */
	public final static byte CHANNEL_TEAM         = 7;
	/** * 私聊 */
	public final static byte CHANNEL_PRIVATE      = 8;
	/** 获得物品-任务-怪物-购买 */
	public final static byte CHANNEL_GOODS        = 9;
	/** 消息频道-商店 */
	public final static byte CHANNEL_MSG_SHOP     = 10;
	/** 消息频道-任务 */
	public final static byte CHANNEL_MSG_MISSION  = 11;
	/** 消息频道-活动 */
	public final static byte CHANNEL_MSG_ACTIVITY = 12;
	/** 消息频道-系统 */
	public final static byte CHANNEL_MSG_SYSTEM   = 13;
	/** * 喇叭 */
	public final static byte CHANNEL_HORN         = 14;

	/** * 喇叭引导 */
	public final static byte CHANNEL_HORN_GUIDE   = 15;
	/** 秘境频道 */
	public final static byte CHANNEL_SECRET_SCENE = 16;
}