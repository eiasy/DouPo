package mmo.common.protocol.manager;

public interface ManagerProtocol {
	public final static int PROC_8888_LOGIN                   = 8888;
	/** 获取地图数据 */
	public final static int PROC_1000_MEMORY_DATA             = 1000;
	/** 获取地图上的指定元素的信息 */
	public final static int PROC_1001_MEMORY_ELEMENT          = 1001;
	/** 更新内存中的怪物数据 */
	public final static int PROC_1002_UPDATE_MONSTER          = 1002;
	/** 向内存中的添加怪物 */
	public final static int PROC_1003_ADD_MONSTER             = 1003;
	/** 更新内存中的玩家的数据 */
	public final static int PROC_1004_UPDATE_ROLE             = 1004;
	/** 获取数据库表信息 */
	public final static int PROC_1005_DB_TABLE                = 1005;
	/** 获取表格数据 */
	public final static int PROC_1006_TABLE_DATA              = 1006;
	/** 向数据库中添加怪物 */
	public final static int PROC_1007_DB_ADD_MONSTER          = 1007;
	/** 更新数据库中的怪物数据 */
	public final static int PROC_1008_DB_EDITOR_MONSTER       = 1008;
	/** 删除数据库中的怪物数据 */
	public final static int PROC_1009_DELETE_MONSTER          = 1009;
	/** 向数据库中添加道具 */
	public final static int PROC_1010_ADD_GAMEITME            = 1010;
	/** 更新数据库中的道具数据 */
	public final static int PROC_1011_EDITOR_GAMEITME         = 1011;
	/** 删除怪物绑定的道具 */
	public final static int PROC_1014_DEL_MONSTER_ITEM        = 1014;
	/** 为怪物绑定一个新的道具 */
	public final static int PROC_1015_ADD_MONSTER_ITEM        = 1015;
	/** 更新怪物绑定的道具 */
	public final static int PROC_1016_UPDATE_MONSTER_ITEM     = 1016;
	/** 向数据库中添加技能 */
	public final static int PROC_1017_ADD_GAMESKILL           = 1017;
	/** 更新数据库中的技能数据 */
	public final static int PROC_1018_EDITOR_GAMESKILL        = 1018;
	/** 删除数据库中的技能数据 */
	public final static int PROC_1019_DELETE_GAMESKILL        = 1019;
	/** 获取怪物绑定的技能 */
	public final static int PROC_1020_MONSTER_SKILL           = 1020;
	/** 删除怪物绑定的技能 */
	public final static int PROC_1021_DEL_MONSTER_SKILL       = 1021;
	/** 更新怪物绑定的技能 */
	public final static int PROC_1022_UPDATE_MONSTER_SKILL    = 1022;
	/** 为怪物绑定一个新的技能 */
	public final static int PROC_1023_ADD_MONSTER_SKILL       = 1023;
	/** 删除数据库中的场景 */
	public final static int PROC_1024_DELETE_SCENE            = 1024;
	/** 向数据库中添加场景 */
	public final static int PROC_1025_ADD_SCENE               = 1025;
	/** 更新数据库中的场景数据 */
	public final static int PROC_1026_EDITOR_SCENE            = 1026;
	/** 向数据库中添加NPC */
	public final static int PROC_1027_ADD_NPC                 = 1027;
	/** 更新数据库中的怪物数据 */
	public final static int PROC_1028_EDITOR_NPC              = 1028;
	/** 删除数据库中的NPC */
	public final static int PROC_1029_DELETE_NPC              = 1029;
	/** 获取场景中的NPC */
	public final static int PROC_1030_SCENE_NPC               = 1030;
	/** 获取场景中的怪物 */
	public final static int PROC_1031_SCENE_MONSTER           = 1031;
	/** 向数据库中的场景添加怪物 */
	public final static int PROC_1032_ADD_SCENE_MONSTER       = 1032;
	/** 更新数据库中的场景怪物数据 */
	public final static int PROC_1033_EDITOR_SCENE_MONSTER    = 1033;
	/** 删除数据库中的场景怪物 */
	public final static int PROC_1034_DELETE_SCENE_MONSTER    = 1034;
	/** 浏览表格数据 */
	public final static int PROC_1035_BROWSE_TABLE_DATA       = 1035;
	/** 获取场景数据 */
	public final static int PROC_1036_GET_SCENE_DATAFILE      = 1036;
	/** 获取场景中的出口 */
	public final static int PROC_1037_SCENE_EXIT              = 1037;
	/** 向数据库中的场景添加出口 */
	public final static int PROC_1038_ADD_SCENE_EXIT          = 1038;
	/** 更新据库中的场景添加出口 */
	public final static int PROC_1039_UPDATE_SCENE_EXIT       = 1039;
	/** 删除数据库中的场景出口 */
	public final static int PROC_1040_DELETE_SCENE_EXIT       = 1040;
	/** 向数据库中添加任务 */
	public final static int PROC_1041_ADD_GAMEMISSION         = 1041;
	/** 更新数据库中任务 */
	public final static int PROC_1042_UPDATE_GAMEMISSION      = 1042;
	/** 删除数据库中的任务数据 */
	public final static int PROC_1043_DELETE_GAMEMISSION      = 1043;
	/** 心跳 */
	public final static int PROC_1044_HEART_BEAT              = 1044;
	/** 向数据库中添加物品 */
	public final static int PROC_1045_ADD_GAMEGOODS           = 1045;
	/** 更新数据库中的物品数据 */
	public final static int PROC_1046_EDITOR_GAMEGOODS        = 1046;
	/** 删除数据库中的物品数据 */
	public final static int PROC_1047_DELETE_GAMEGOODS        = 1047;
	/** 删除数据库中的物品数据 */
	public final static int PROC_1048_DELETE_GAMETRIGGETS     = 1048;
	/** 向数据库中添加触发器 */
	public final static int PROC_1049_ADD_GAMETRIGGER         = 1049;
	/** 编辑数据库中添加触发器 */
	public final static int PROC_1050_EDIT_GAMETRIGGER        = 1050;
	/** 上传技能脚本 */
	public final static int PROC_1051_UPLOAD_SKILL_SCRIPT     = 1051;
	/** 验证id */
	public final static int PROC_1052_VALIDATE_ID             = 1052;

	/** 删除活动 */
	public final static int PROC_1053_ACTIVITY_DELETE         = 1053;
	/** 添加新的活动 */
	public final static int PROC_1054_ACTIVITY_ADD            = 1054;
	/** 更新活动 */
	public final static int PROC_1055_ACTIVITY_UPDATE         = 1055;

	/** 删除目标 */
	public final static int PROC_1056_TARGET_DELETE           = 1056;
	/** 更新目标 */
	public final static int PROC_1057_TARGET_UPDATE           = 1057;
	/** 添加新的目标 */
	public final static int PROC_1058_TARGET_ADD              = 1058;
	/****************************************************/
	/****************************************************/
	/****************************************************/
	/****************************************************/
	/****************************************************/
	/****************************************************/

	/** 联网确认 */
	public final static int PROS_8888_NET_CONFIRM             = 8888;
	/** 登录 */
	public final static int PROS_8889_LOGIN                   = 8889;
	/** 发送地图信息 */
	public final static int PROS_4000_MAP_DATA                = 4000;
	/** 地图上元素信息 */
	public final static int PROS_4001_MAP_ELEMENT             = 4001;
	/** 数据库表信息 */
	public final static int PROS_4002_DB_TABLE                = 4002;
	/** 表格数据 */
	public final static int PROS_4003_TABLE_DATA              = 4003;
	/** 获取怪物绑定的道具 */
	public final static int PROS_4004_MONSTER_ITEM            = 4004;
	/** 获取怪物绑定的技能 */
	public final static int PROS_4005_MONSTER_SKILL           = 4005;
	/** 返回场景上的NPC */
	public final static int PROS_4006_SCENE_NPC               = 4006;
	/** 返回场景上的怪物 */
	public final static int PROS_4007_SCENE_MONSTER           = 4007;
	/** 浏览表格数据 */
	public final static int PROS_4008_BROWSE_TABLE_DATA       = 4008;
	/** 删除怪物绑定的道具 */
	public final static int PROS_4009_DEL_MONSTER_ITEM        = 4009;
	/** 向数据库中添加怪物 */
	public final static int PROS_4010_DB_ADD_MONSTER          = 4010;
	/** 更新数据库中的怪物数据 */
	public final static int PROS_4011_DB_EDITOR_MONSTER       = 4011;
	/** 删除数据库中的怪物数据 */
	public final static int PROS_4012_DB_DELETE_MONSTER       = 4012;
	/** 向数据库中添加道具 */
	public final static int PROS_4013_DB_ADD_GAMEITME         = 4013;
	/** 更新数据库中的道具数据 */
	public final static int PROS_4014_DB_EDITOR_GAMEITME      = 4014;
	/** 删除数据库中的道具数据 */
	public final static int PROS_4015_DB_DELETE_GAMEITME      = 4015;
	/** 更新数据库中的技能数据 */
	public final static int PROS_4016_DB_EDITOR_GAMESKILL     = 4016;
	/** 向数据库中添加技能 */
	public final static int PROS_4017_DB_ADD_GAMESKILL        = 4017;
	/** 删除数据库中的技能数据 */
	public final static int PROS_4018_DB_DELETE_GAMESKILL     = 4018;
	/** 删除怪物绑定的技能 */
	public final static int PROS_4019_DB_DEL_MONSTER_SKILL    = 4019;
	/** 更新怪物绑定的技能 */
	public final static int PROS_4020_DB_UPDATE_MONSTER_SKILL = 4020;
	/** 为怪物绑定一个新的技能 */
	public final static int PROS_4021_ADD_MONSTER_SKILL       = 4021;
	/** 为怪物绑定一个新的道具 */
	public final static int PROS_4022_ADD_MONSTER_ITEM        = 4022;
	/** 更新怪物绑定的道具 */
	public final static int PROS_4023_DB_UPDATE_MONSTER_ITEM  = 4023;
	/** 删除数据库中的NPC */
	public final static int PROS_4024_DB_DELETE_NPC           = 4024;
	/** 更新数据库中的怪物数据 */
	public final static int PROS_4025_DB_EDITOR_NPC           = 4025;
	/** 向数据库中添加NPC */
	public final static int PROS_4026_DB_ADD_NPC              = 4026;
	/** 删除数据库中的场景 */
	public final static int PROS_4027_DELETE_SCENE            = 4027;
	/** 更新数据库中的场景数据 */
	public final static int PROS_4028_DB_EDITOR_SCENE         = 4028;
	/** 向数据库中添加场景 */
	public final static int PROS_4029_DB_ADD_Scene            = 4029;
	/** 删除数据库中的场景怪物 */
	public final static int PROS_4030_DB_DELETE_Scene_MONSTER = 4030;
	/** 更新数据库中的场景怪物数据 */
	public final static int PROS_4031_DB_EDITOR_SCENE_MONSTER = 4031;
	/** 向数据库中的场景添加怪物 */
	public final static int PROS_4032_DB_ADD_SCENE_MONSTER    = 4032;
	/** 发送场景配置文件数据 */
	public final static int PROS_4033_SCENE_DATAFILE          = 4033;
	/** 返回场景上的出口 */
	public final static int PROS_4034_SCENE_EXIT              = 4034;
	/** 向数据库中的场景添加出口 */
	public final static int PROS_4035_DB_ADD_SCENE_EXIT       = 4035;
	/** 向数据库中的场景添加出口 */
	public final static int PROS_4036_DB_UPDATE_SCENE_EXIT    = 4036;
	/** 删除数据库中的出口 */
	public final static int PROS_4037_DB_DELETE_EXIT          = 4037;
	/** 更新内存怪物 */
	public final static int PROS_4038_MEMORY_MONSTER          = 4038;
	/** 添加内存怪物 */
	public final static int PROS_4039_ADD_MEMORY_MONSTER      = 4039;
	/** 更新角色数据 */
	public final static int PROS_4040_UPDATE_ROLE             = 4040;
	/** 添加任务 */
	public final static int PROS_4041_ADD_GAMEMISSION         = 4041;
	/** 更新任务 */
	public final static int PROS_4042_UPDATE_GAMEMISSION      = 4042;
	/** 删除数据库中的任务 */
	public final static int PROS_4043_DELETE_GAMEMISSION      = 4043;
	/** 添加物品 */
	public final static int PROS_4044_ADD_GAMEGOODS           = 4044;
	/** 更新物品 */
	public final static int PROS_4045_EDITOR_GAMEGOODS        = 4045;
	/** 删除数据库中的物品 */
	public final static int PROS_4046_DELETE_GAMEGOODS        = 4046;
	/** 删除数据库中的触发器 */
	public final static int PROS_4047_DELETE_GAMETRIGGERS     = 4047;
	/** 添加触发器 */
	public final static int PROS_4048_ADD_GAMETRIGGER         = 4048;
	/** 更新触发器 */
	public final static int PROS_4049_EDITOR_GAMETRIGGER      = 4049;
	/** 心跳 */
	public final static int PROS_4050_HEART_BEAT              = 4050;
	/** 上传技能脚本 */
	public final static int PROS_4051_UPLOAD_SKILL_SCRIPT     = 4051;
	/** 反馈客户端的请求 */
	public final static int PROS_4052_RESPONSE                = 4052;
	/** 添加活动 */
	public final static int PROS_4053_activityAdd             = 4053;
	/** 更新活动 */
	public final static int PROS_4054_activityUpdate          = 4054;
	/** 删除活动 */
	public final static int PROS_4055_activityDelete          = 4055;
	/** 删除目标 */
	public final static int PROS_4056_targetDelete            = 4056;
	/** 更新目标 */
	public final static int PROS_4057_targetUpdate            = 4057;
	/** 添加目标 */
	public final static int PROS_4058_targetAdd               = 4058;

	public static final class MsgLevel {
		public final static byte INFO  = 1;
		public final static byte WARN  = 2;
		public final static byte ERROR = 3;
	}
}
