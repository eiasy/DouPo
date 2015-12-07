package mmo.common.protocol.ui.main;

public interface Main_900_Pet {
	/** 宠物基本信息 */
	short main_900_petBaseInfo     = 900;
	/** 宠物技能信息 */
	short main_901_petSkillInfo    = 901;
	/** 宠物渡劫信息 */
	short main_902_petElevateInfo  = 902;
	/** 宠物穴位信息 */
	short main_903_petAcupointInfo = 903;

	interface Sub_903 {
		/** 穴位信息（引用） */
		short acupoint_0_reference = 0;
		/** 穴位用到的材料 */
		short acupoint_1_material  = 1;
	}
}
