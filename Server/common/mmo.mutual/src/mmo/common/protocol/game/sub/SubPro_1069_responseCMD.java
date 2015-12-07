package mmo.common.protocol.game.sub;

public interface SubPro_1069_responseCMD {
	/** 查看活跃度积分30分奖励物品信息 */
	byte OPEN_LIVENESS30_1     = 1;
	/** 查看活跃度积分60分奖励物品信息 */
	byte OPEN_LIVENESS60_2     = 2;
	/** 查看活跃度积分100分奖励物品信息 */
	byte OPEN_LIVENESS100_3    = 3;
	/** 领取活跃度积分奖励 */
	byte GET_LIVENESS_AWARD_4  = 4;
	/** 领取斗法奖励 */
	byte GET_DOUFA_AWARD_5     = 5;
	/** 领取世界首领奖励 */
	byte GET_LEADER_BOSS_6     = 6;
	/** 领取一战到底奖励 */
	byte GET_ONE_STAND_7       = 7;
	/** 领取关卡星奖励 */
	byte GET_GATE_STAR_AWARD_8 = 8;
	/** 一战到底每层通关奖励 */
	byte GET_ONE_STAND_AWARD_9 = 9;
	/** 打开秘境 */
	byte OPT_10_SECRET_SCENE   = 10;
}
