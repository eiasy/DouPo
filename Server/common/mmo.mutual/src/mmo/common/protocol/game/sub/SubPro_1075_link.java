package mmo.common.protocol.game.sub;

public interface SubPro_1075_link {
	char SPLIT_CMD                = '#';
	/** 参与秘境 */
	int  SUB_1_SECRET_SCENE       = 1;
	/** 参与打坐 */
	int  SUB_2_DA_ZUO             = 2;
	/** WORLD BOSS */
	int  SUB_3_WORLD_BOSS         = 3;
	/** 打开商店 */
	int  SUB_4_SHOP               = 4;

	/** 打开VIP特权， openwindsow(s6,2,0) */
	int  SUB_5_SHOP_VIP           = 5;
	/** 打开今日特惠， openwindsow(s6,2,1) */
	int  SUB_6_SHOP_LOW           = 6;
	/** 打开特权礼包， openwindsow(s6,2,3) */
	int  SUB_7_SHOP_SPECIAL       = 7;
	/** 打开时装， openwindsow(s6,2,4) */
	int  SUB_8_SHOP_CLOTHES       = 8;
	/** 直接进行斗法 */
	int  SUB_9_DIRECT_DOUFA       = 9;

	/** 进入其他人发现的秘境 */
	int  SUB_10_SINGL_SECRET      = 10;
	/** 私战 */
	int  SUB_11_PRIVATE_CHALLENGE = 11;
}
