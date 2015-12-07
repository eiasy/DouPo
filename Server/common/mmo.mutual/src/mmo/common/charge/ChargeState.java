package mmo.common.charge;

public interface ChargeState {
	/** 游戏服还未做记录 */
	byte GAME_SERVER_UNADD = 1;
	/** 游戏服已做记录 */
	byte GAME_SERVER_ADDED = 2;
	/** 腾讯未发货 */
	byte UNPROVITE_TENCENT = 3;
}
