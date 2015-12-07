package mmo.common.message;

public interface MessageActivity {
	String SIGN_AWARD_FAIL = "未满足领取条件！";

	String REASON_YAO_QIAN = "摇钱树奖励";
	String REASON_XIAN_DAN = "仙丹树奖励";
	String SIGN_AWARD      = "奖励已经领取！";
	String LEVEL_FAIL      = "等级不满足，不能领取奖励！";
	String LEVEL_AWARD     = "该等级的奖励已经领取！";
	String LEVEL_NO_AWARD  = "该等级没有奖励！";

	String DA_ZUO_TIME     = "当前时间不能进行打坐修炼！";
	String DA_ZUO_AWARDED  = "你已经完成该时间段内的打坐修炼！";
	String DA_ZUO_AWARD    = "奖励已经发放，请注意查收！";
	String DA_ZUO_WAIT     = "请稍后再尝试打坐修炼！";

	String XIAN_LU_WAIT    = "请稍后再尝试领取仙露！";
	String XIAN_LU_GETTED  = "活动还未开启，请耐心等待！";
	String CUI_HUA_TO_MAX  = "催化次数已经用完了！";
	String XIAN_LU_LESS    = "你的仙露数量不能满足该次催化！";
	String TREE_UPGRADE    = "升级成功！";
	String CLICK_SUCCESS   = "完成一次催化！";
	String QI_FU_TO_MAX    = "祈福次数已经用完了！";
}
