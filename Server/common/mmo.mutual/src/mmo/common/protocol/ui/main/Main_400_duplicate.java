package mmo.common.protocol.ui.main;

public interface Main_400_duplicate {
	/** 副本 */
	short main_400_duplicate          = 400;
	/** 副本 */
	short main_401_duplicate          = 401;
	/** 关卡信息 */
	short main_402_GATE_INFO          = 402;
	/** 副本区域关卡星奖励信息 */
	short main_406_GATESTAR_AWARD     = 406;
	/** 第一次通关额外奖励 */
	short main_407_DEGREE_AWARD       = 407;
	/** 副本区域的信息 */
	short main_408_ARENA_INFO         = 408;
	/** 秘境关卡列表 */
	short main_414_SECRET_GATE_LIST   = 414;
	/** 秘境列表 */
	short main_415_SECRET_SCENE_LIST  = 415;

	/** 寻宝 主类别 */
	short main_416_TREASURE_HUNT      = 416;
	/** 秘境奖励 */
	short main_417_SECRET_SCENE_AWARD = 417;
	/** 藏宝图信息 */
	short main_418_TREASURE_MAP_INFO  = 418;
	/** 藏宝图钥匙 */
	short main_419_TREASURE_MAP_KEY   = 419;
	/** 藏宝图可能获得的奖励 */
	short main_420_TREASURE_MAP_AWARD = 420;

	interface Sub_402 {
		/** 关卡列表 */
		short sub_0_GATE_LIST = 0;
	}

	interface Sub_414 {
		/** 秘境信息 */
		short sub_0_SECRET_SCENE_INFO  = 0;
		/** 秘境内关卡信息 */
		short sub_1_SECRET_GATE_INFO   = 1;
		/** 秘境内关卡信息 */
		short sub_2_SECRET_GATE_ATTACK = 2;
		/** 已经进入秘境的角色 */
		short sub_4_ENTER_SECRET       = 4;
		/** 关卡钥匙状态 */
		short sub_5_KEY_STATE          = 5;

	}

	interface Sub_407 {
		/** 奖励信息 */
		short sub_0_AWARD_INFO  = 0;
		/** 奖励的标题 */
		short sub_1_AWARD_TITLE = 1;

	}
}
