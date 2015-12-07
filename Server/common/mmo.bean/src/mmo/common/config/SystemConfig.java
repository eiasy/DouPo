package mmo.common.config;

public class SystemConfig {
	/** 系统ID */
	public static final int    EMAIL_SYSTEM_ID               = 0;
	/** 无偿赠与 */
	public static final byte   EMAIL_HANDSEL                 = 0;                                      // 无偿赠与
	/** 收费取信 */
	public static final byte   EMAIL_CHARGE                  = 1;                                      // 收费取信
	public static final String EMAIL_SYSTEM_NAME             = "系统";
	public static final String EMAIL_CS_NAME                 = "客服";
	public static final String EMAIL_CHARGE_TITLE            = "充值结果";
	public static final String EMAIL_SYSTEM                  = "系统邮件";
	public static final String EMAIL_SYSTEM_TITLE            = "交易邮件";
	public static final String EMAIL_CANCEL_CONTENTS         = "<您取消了交易，请即时回收附件>";
	public static final String EMAIL_REFUSE_CONTENTS         = "<对方拒收邮件，请即时回收附件>";
	public static final String EMAIL_PACKUP_CONTENTS         = "<系统发送：交易成功，请即时回收灵石，本次交易税率15%>";
	public static final String EMAIL_AWARD_TITLE             = "天赐神丹";
	public static final String EMAIL_AWARD_CONTENTS          = "<系统：恭喜你修炼有所小成，这颗虎力仙丹在使用后有神奇的效果，少侠收下吧>";
	public static final String EMAIL_DUPLICATE_TITLE         = "副本翻牌奖励";
	public static final String EMAIL_GUMO                    = "封印古魔奖励";
	public static final String EMAIL_BAG_TITLE               = "礼包兑换";
	public static final String EMAIL_BAG_CONTENTS            = "<系统发送：兑换物品，请即时领取>";
	public static final String EMAIL_DUPLICATE_CONTENTS      = "<系统发送：副本翻牌奖励，请即时领取>";
	public static final String EMAIL_GUMO_CONTENTS           = "<系统发送：封印古魔奖励，请即时领取>";
	public static final String EMAIL_LOGINAWARD_TITLE        = " 每日登录奖励";
	public static final String EMAIL_LOGINAWARD_CONTENTS     = "<系统发送：每日登录奖励，请即时领取>";
	public static final String EMALL_CHARGEFIRST_TITLE       = "首次充值奖励";
	public static final String EMAIL_CHARGEFIRST_CONTENTS    = "<系统发送：首次充值奖励，请即时领取>";
	public static final String EMAIL_CHARGEFIRST_BINDLINGSHI = "<系统发送：首次充值{0}元，奖励{1}绑灵,奖励已发送,请在背包中查看>";
	public static final String EMALL_CHARGEAWARD_TITLE       = "充值奖励";
	public static final String EMALL_CHARGEAWARD_CONTENTS    = "<系统发送：充值{0}元，奖励{1}灵石,奖励已发送,请在背包中查看>";

	public static final String EMAIL_SECRET_TITLE            = "秘境奖励";
	public static final String EMAIL_FIND_SECRET_TITLE       = "秘境发现奖";
	public static final String EMAIL_MAX_SECRET_TITLE        = "秘境最高伤害奖";
	public static final String EMAIL_LAST_SECRET_TITLE       = "秘境最后一刀奖";
	public static final String EMAIL_JOIN_SECRET_TITLE       = "秘境参与奖";

	public static final String EMALL_LIMITPET_JF_CONTENTS    = "<系统发送:限时宠物活动结束，获得积分奖励，请即时领取>";
	public static final String EMALL_LIMITPET_RK_CONTENTS    = "<系统发送:限时宠物活动结束，获得排名奖励，请即时领取>";
	public static final String EMALL_VIP_LEVEL_CONTENTS      = "<系统发送:VIP等级提升，获得VIP等级提升奖励，请即时领取>";
	public static final String EMALL_SECRET_CONTENTS         = "<系统发送:获得秘境奖励，请即时领取>";
	public static final String EMALL_DOUFA_CONTENTS          = "<系统发送:获得斗法排名奖励，请即时领取>";
	public static final String EMALL_WORLD_BOSS_CONTENTS     = "<系统发送:获得世界首领奖励，请即时领取>";
	public static final String EMALL_CHARGE_FIRST_CONTENTS   = "<系统发送:首充大礼包奖励，请即时领取>";
}
