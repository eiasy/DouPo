package mmo.common.bean.cdkey;

public interface CDkeyConfig {

	public interface state {
		/** 激活码有效 */
		static int effective = 0;
	}

	public interface cate {
		/** 公会入驻类型激活码 */
		static byte gongHuiKey = 1;
		/** 神秘礼包激活码 */
		static byte secretKey  = 2;
	}

	public interface fun {
		/** 内测灵石奖励领取 */
		static byte bateMoney   = 1;
		/** 公会入驻礼包兑换 */
		static byte bateGongHui = 2;
		/** 神秘礼包兑换 */
		static byte bateSecret  = 3;
		/** 公测 */
		static byte publicTest  = 4;

	}

	public interface title {
		/** 内测灵石奖励 */
		static String bateMoneyTitle      = "百元灵石免费领取";
		/** 公会入驻礼包 */
		static String bateGonghuiTtitle   = "公会入驻礼包兑换";
		/** 神秘礼包兑换 */
		static String bateSecretTile      = "限量神秘礼包兑换";
		/** 公测奖励 */
		static String publicTestTitle     = "领取活动兑换码奖励";

	}
}
