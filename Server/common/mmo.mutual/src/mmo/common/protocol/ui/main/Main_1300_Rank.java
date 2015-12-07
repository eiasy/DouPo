package mmo.common.protocol.ui.main;

import mmo.common.protocol.game.UserProtocol;

public class Main_1300_Rank {
	/** 竞技场排行主类别 */
	public static final short main_1301_arena                = 1301;
	/** 基本信息主类别 */
	public static final short main_1302_BASE_INFO            = 1302;
	/** 一战到底主类别 */
	public static final short main_1303_OneStand             = 1303;
	/** 世界首领主类别 */
	public static final short main_1304_Leader               = 1304;

	/** 斗法的对手 */
	public static final short main_1307_CHALLENGE_ENEMY      = 1307;
	/** 斗法的对手阵容 */
	public static final short main_1308_CHALLENGE_ENEMY_PETS = 1308;
	/** 挑战奖品 */
	public static final short main_1309_CHALLENGE_AWARDS     = 1309;
	/** 世界首领奖品 */
	public static final short main_1311_WORLD_BOSS_AWARDS    = 1311;

	/** 新版本一战到底主类别 */
	public static final short main_1305_newOneStand          = 1305;
	/** 新版本一战到底奖励信息主类别 */
	public static final short main_1306_oneStandAward        = 1306;

	/** 攻城界面时间显示<每日xx-xx点开启> */
	public static final short main_1310_monsterAtkTime       = 1310;

	public static interface Sub_1302 {
		/** 子类别0 */
		short sub_0 = 0;
	}

	/**
	 * 通过RANK类型获取主类别
	 * 
	 * @param type
	 * @return
	 */
	public static short getMainByRankType(byte type) {
		switch (type) {
			case UserProtocol.RankType.ARENA_SORT:
				return main_1301_arena;
			case UserProtocol.RankType.ONE_STAND_SORT:
				return main_1303_OneStand;
			case UserProtocol.RankType.LEADER_SORT:
				return main_1304_Leader;
			default:
				return 0;
		}
	}

}
