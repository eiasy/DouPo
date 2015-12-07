package mmo.common.protocol.command.sub;

public interface SubPro_15053_gmCmds {
	/** 批量冻结角色 */
	int P_1_FREEZE_ROLE_LIST         = 1;
	/** 冻结设备 */
	int P_2_FREEZE_DEVICE            = 2;
	/** 批量强制下线 */
	int P_3_KICKOUT_LIST             = 3;
	/** 批量禁言下线 */
	int P_4_FORBID_CHAT_LIST         = 4;
	/** 更新角色数据的命令 */
	int P_5_UPDATE_ROLE_CMD          = 5;
	/** 向单个玩家发送邮件 */
	int P_6_EMAIL_SINGL              = 6;
	/** 全服发奖 */
	int P_7_EMAIL_MULTI              = 7;
	/** 发布公告 */
	int P_8_GAME_NOTICE_ADD          = 8;
	/** 公告列表 */
	int P_9_GAME_NOTICE_LIST         = 9;
	/** 更新公告 */
	int P_10_UPDATE_GAME_NOTICE      = 10;
	/** 删除公告 */
	int P_11_DELETE_GAME_NOTICE      = 11;
	/** 添加系统活动 */
	int P_12_SYSTEM_ACTIVITY_ADD     = 12;
	/** 加载系统活动列表 */
	int P_13_SYSTEM_ACTIVITY_LIST    = 13;
	/** 更新系统活动 */
	int P_14_SYSTEM_ACTIVITY_UPDATE  = 14;
	/** 删除系统活动 */
	int P_15_SYSTEM_ACTIVITY_DELETE  = 15;
	/** 请求社交信息 */
	int P_16_ROLE_RELATION           = 16;
	/** 加载收件箱 */
	int P_17_EMAIL_RECEIVE           = 17;
	/** 编辑邮件 */
	int P_18_EMAIL_EDIT              = 18;
	/** 重新类库 */
	int P_19_CLASSLOADER             = 19;
	/** 清理账号缓存内角色 */
	int P_20_CLEAR_ROLE_LIST         = 20;
	/** 重新加载账号角色到缓存 */
	int P_21_RELOAD_ROLE_LIST        = 21;
	/** 删除角色 */
	int P_22_DELETE_ROLE             = 22;
	/** 恢复角色 */
	int P_23_RESUME_ROLE             = 23;
	/** 批量发奖 */
	int P_24_AWARD_BATCH             = 24;
	/** 重新加载高级账号 */
	int P_25_ACCOUNT_HIGH            = 25;
	/** 添加跑马灯 */
	int P_26_ADD_PMD                 = 26;
	/** 更新跑马灯 */
	int P_27_UPDATE_PMD              = 27;
	/** 加载活动数据 */
	int P_28_ACTIVITY_DATA           = 28;
	/** 更新活动数据 */
	int P_29_UPDATE_ACTIVITY_DATA    = 29;
	/** 查看怪物攻城概况 */
	int P_30_LOAD_MONSTER_CITY       = 30;
	/** 更新怪物攻城 */
	int P_31_UPDATE_MONSTER_CITY     = 31;
	/** 查看世界BOSS概况 */
	int P_32_WORLD_BOSS              = 32;
	/** 更新世界BOSS时间 */
	int P_33_UPDATE_WORLD_BOSS_TIME  = 33;
	/** 更新世界BOSS最大血量 */
	int P_34_UPDATE_WORLD_BOSS_MAXHP = 34;
	/** 更新世界BOSS当前血量 */
	int P_35_UPDATE_WORLD_BOSS_CURHP = 35;
	/** 更新GM指令 */
	int P_36_GM_COMMAND              = 36;
	/** 新角色出生场景 */
	int P_37_NEW_ROLE_SCENE          = 37;
	/** 显示角色列表 */
	int P_38_SHOW_ROLE_LIST          = 38;
	/** 机器人配置 */
	int P_39_ROBOT_PARAMETER         = 39;
	/** 机器人开关 */
	int P_40_ROBOT_SWITCH            = 40;
	/** 加载配置项 */
	int P_41_LOAD_PROJECT_CONFIG     = 41;
	/** 刷新排名 */
	int P_42_REFRESH_RANK            = 42;
	/** 刷新战斗力 */
	int P_43_RESET_FIGHT             = 43;
	/** 挑战自己开关 */
	int P_44_CHALLENGE_SWITCH        = 44;
}
