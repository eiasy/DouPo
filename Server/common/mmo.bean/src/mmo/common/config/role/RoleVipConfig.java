package mmo.common.config.role;

public interface RoleVipConfig {
	/** 最大VIP等级 */
	short MAX_LV          = 14;
	/** 宝箱状态-未开启 */
	byte  boxStateClosed  = 0;
	/** 宝箱状态-未领取 */
	byte  boxStateOpened  = 1;
	/** 宝箱状态-已领取 */
	byte  boxStateFetched = 2;
}
