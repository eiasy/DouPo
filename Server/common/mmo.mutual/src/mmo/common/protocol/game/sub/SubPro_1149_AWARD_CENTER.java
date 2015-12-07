package mmo.common.protocol.game.sub;

public interface SubPro_1149_AWARD_CENTER {
	/** 领取全部 */
	byte GET_ALL_AWARDS           = 1;
	/** 领取某一项 */
	byte GET_POINT_AWARD          = 2;
	/** 打开奖励中心 */
	byte OPEN_AWARD_CENTER        = 3;
	/** 更新有效期 */
	byte UPDATE_EFFECTIVE_TIME    = 4;
	/** 购买等级礼包 */
	byte OPT_5_BUY_LEVEL_GIFT     = 5;
	/**关闭领奖中心*/
	byte OPT_6_CLOSE_AWARD_CENTER = 6;
}
