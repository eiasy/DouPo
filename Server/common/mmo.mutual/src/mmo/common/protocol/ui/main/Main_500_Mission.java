package mmo.common.protocol.ui.main;

public interface Main_500_Mission {
	/** 正在接取的任务 主类别 */
	short main_500_missionAccepting = 500;
	/** 任务奖励 主类别 */
	short main_520_missionAward     = 520;
	/** 任务条件 主类别 */
	short main_521_missionFinish    = 521;
	/** 任务教程 主类别 */
	short main_522_missionTeach     = 522;
	/** 每日任务主类别 */
	short main_523_missionEveryDay  = 523;
	/** 活跃度任务主类别 */
	short main_524_missionLiveness  = 524;
	/** 小助手 主类别 */
	short main_526_signPics         = 526;
	/** 开启新区域 */
	short main_527_openNewArea      = 527;

	// ----------------------主类别对应的子类别-----------------------------
	interface Sub_500 {
		byte sub_0_NPC_TALK        = 0;
		byte sub_1_NPC_TALK_OPTION = 1;
	}

	interface Main_501_Sub {

	}

	interface Main_520_Sub {

	}

	interface Main_521_Sub {

	}

	interface Main_522_Sub {

	}

	interface Main_523_Sub {

	}

	interface Main_524_Sub {

	}

	interface Sub_526 {
		/** 我要变强 */
		byte sub_0_strength = 0;
		/** 我要伙伴 */
		byte sub_1_pet      = 1;
		/** 我要赚钱 */
		byte sub_2_money    = 2;
		/** 我要宝石 */
		byte sub_3_stone    = 3;
		/** 我要升级 */
		byte sub_4_level    = 4;
		/** 我要精力 */
		byte sub_5_jingli   = 5;
		/** 我要体力 */
		byte sub_6_tili     = 6;
		/** 我要机缘 */
		byte sub_7_jiyuan   = 7;
	}
}
