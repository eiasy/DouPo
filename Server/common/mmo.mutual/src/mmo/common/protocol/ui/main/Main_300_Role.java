package mmo.common.protocol.ui.main;

public interface Main_300_Role {
	/** 角色数据 主类别 */
	short main_300_Role         = 300;
	/** 角色培养 主类别 */
	short main_303_Train        = 303;
	/** 角色称号 主类别 */
	short main_304_TrainTitle   = 304;

	/** 详细技能 主类别 */
	short main_306_DetailSkill  = 306;
	/** 详细天赋 主类别 */
	short main_307_DetailGenius = 307;
	/** 详细缘分 主类别 */
	short main_308_DetailLuck   = 308;
	/** 角色强化 主类别 */
	short main_310_RoleStrength = 310;
	/** 仙府 */
	short main_311_RoleMansion  = 311;
	/** 跟随宠物 主类别 */
	short main_312_FollowPet    = 312;

	interface Sub_311 {
		/** 倒计时 */
		short sub_2_TIMER = 2;
	}

}
