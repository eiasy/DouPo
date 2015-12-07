package mmo.common.protocol.game.operate;

public interface Operate_1134_WordBoss {
	/** 进入世界首领副本 */
	public final static byte ENTER_LEADER_DUP = 1;
	/** 角色被世界BOSS击杀 */
	public final static byte BOSS_KILL_ROLE   = 2;
	/** 打开世界首领界面 */
	public final static byte OPEN_LEADER      = 3;
	/** 打开世界首领说明 */
	public final static byte OPEN_EXPLAIN     = 4;
	/** 查看可领取的世界首领奖励 */
	public final static byte LEADER_AWARD     = 5;
	/** 退出世界首领副本 */
	public final static byte EXIT_WORD_BOSS   = 6;
	/** 查看世界首领副本排行 */
	public final static byte RANK_WORD_BOSS   = 9;

	/** 查看角色所在页 */
	public final static byte CUR_PAGE         = 7;
	/** 查看一战到底排行上一页 */
	public final static byte PRE_PAGE         = 12;
	/** 查看一战到底排行下一页 */
	public final static byte NEXT_PAGE        = 13;
	/** 查看排行榜第一页 */
	public final static byte TOP_PAGE         = 8;

	/** 打开荣誉商店 */
	public final static byte OPEN_RY_SHOP     = 20;
	/** 立即解封 */
	public final static byte SHORTLY_TIME     = 21;

	/** 怪物攻城寻路 */
	public final static byte MONSTER_ATK_FIND = 22;
}
