package mmo.common.protocol.game;

public interface UserProtocol {
	public final static char BELONG_SERVER     = '@';
	/** 消息窗口 */
	public final static int  PROS_8001_MESSAGE = 8001;

	public interface Client {
		/** 登陆 */
		public static final int PROC_1001_LOGIN               = 1001;
		/** 接收到的客户端攻击协议协议 */
		public static final int PROC_1003_HIT_MONSTER         = 1003;
		/** 寻找指定的NPC */
		public static final int PROC_1004_TO_NPC              = 1004;
		/** 聊天信息里的查看任务 */
		public static final int PROC_1005_CHAT_TASK           = 1005;
		/** 组队协议 */
		public static final int PROC_1006_GROUP               = 1006;
		/** 玩家走路的像素路径 */
		public static final int PROC_1007_PATH                = 1007;
		/** 攻击其他玩家 */
		public static final int PROC_1008_HIT                 = 1008;
		/** 角色申请释放技能 */
		public static final int PROC_1009_SKILL               = 1009;
		/** 怪物重置 */
		public static final int PROC_1010_RESET_MONSTER       = 1010;
		/** 心跳 */
		public static final int PROC_1012_HEART_BEAT          = 1012;
		/** 打断技能 */
		public static final int PROC_1013_STOP_SKILL          = 1013;
		/** 改变怪物坐标 */
		public static final int PROC_1014_changeMonsterXY     = 1014;
		/** 接收到的客户端换地图协议 */
		public static final int PROC_1015_CHANGE_MAP          = 1015;
		/** 与NPC交互 */
		public static final int PROC_1016_NPC_MUTUAL          = 1016;
		/** 选择NPC的一个功能或任务 */
		public static final int PROC_1017_SELECT_FUNCTION     = 1017;
		/** 接受NPC发放的一个任务 */
		public static final int PROC_1018_DO_MISSION          = 1018;
		/** 提交一个已经完成的任务 */
		public static final int PROC_1019_COMMIT_MISSION      = 1019;
		/** 加载技能树 */
		public static final int PROC_1020_SKILL_TREE          = 1020;
		/** 更新技能点数 */
		public static final int PROC_1021_UPDATE_POINT        = 1021;
		/** 组队确认 */
		public static final int PROC_1022_GROUP_FEEDBACK      = 1022;
		/** 请求任务列表 */
		public static final int PROC_1023_REQUEST_TASKLIST    = 1023;
		/** 请求任务寻路 */
		public static final int PROC_1024_TASKLIST_FINDPATH   = 1024;
		/** 请求属性列表 */
		public static final int PROC_1025_ROLE_PROPERTYS      = 1025;
		/** 请求物品面板 */
		public static final int PROC_1026_LOAD_GOODS          = 1026;
		/** 操作物品相关 */
		public static final int PROC_1027_OPERATE_GOODS       = 1027;
		/** 注册 */
		public static final int PROC_1028_REGISTER            = 1028;
		/** 构建角色 */
		public static final int PROC_1029_BUILD_ROLE          = 1029;
		/** 管理角色 */
		public static final int PROC_1030_MANAGER_ROLE        = 1030;
		/** 快速登录 */
		public static final int PROC_1031_DIRECT_LOGIN        = 1031;
		/** 快速注册 */
		public static final int PROC_1032_DIRECT_REGISTER     = 1032;
		/** 提交本地最高资源版本号 */
		public static final int PROC_1033_RESOURCE_VER        = 1033;
		/** 读取游戏编号和渠道编号 */
		public static final int PROC_1034_GAME_ID             = 1034;
		/** 安全验证 */
		public static final int PROC_1035_SECURITY_CODE       = 1035;
		/** 选择游戏服务器 */
		public static final int PROC_1036_SELECT_SERVER       = 1036;
		/** 请求资源 */
		public static final int PROC_1037_REQUEST_RESOURCE    = 1037;
		/** 进入场景 */
		public static final int PROC_1038_ENTER_SCENE         = 1038;
		/** 客户端跳转到登录界面 */
		public static final int PROC_1039_TO_MENU             = 1039;
		/** 跳转到角色列表 */
		public static final int PROC_1040_ROLE_LIST           = 1040;
		/** 聊天 */
		public static final int PROC_1041_CHAT                = 1041;
		/** 宠物释放魔法 */
		public static final int PROC_1042_PET_SKILL           = 1042;
		/** 宠物走路的像素路径 */
		public static final int PROC_1043_PATH                = 1043;
		/** 请求打开好友列表 */
		public static final int PROC_1044_FRIEND_LIST         = 1044;
		/** 请求操作好友 */
		public static final int PROC_1045_FRIEND_CMD          = 1045;
		/** 请求打开邮箱 */
		public static final int PROC_1046_EMAIL_LIST          = 1046;
		/** 请求操作邮箱 */
		public static final int PROC_1047_EMAIL_CMD           = 1047;
		/** 发送邮件 */
		public static final int PROC_1048_SEND_EMAIL          = 1048;
		/** 查看任意角色信息 **/
		public static final int PROC_1049_VIEW_DETAIL         = 1049;
		/** 副本操作 */
		public static final int PROC_1050_DUPLICATE           = 1050;
		/*** 世界地图 */
		public static final int PROC_1051_WORLD_MAP           = 1051;
		/*** 世界地图切换场景 */
		public static final int PROC_1052_WORLD_CHANGE_MAP    = 1052;
		/*** 转发协议 */
		public static final int PROC_1053_FORWARDING_PRO      = 1053;
		/** 客户端复活协议 */
		public static final int PROC_1054_RELIVE              = 1054;
		/** 穴位操作 */
		public static final int PROC_1055_IDENTITY            = 1055;
		/** 宗门相关的操作 */
		public static final int PROC_1056_SECT_OPERATE        = 1056;
		/** 宗门信息 */
		public static final int PROC_1057_SECT_INFO           = 1057;
		/** 刷新宠物 */
		public static final int PROC_1058_UPDATE_PET          = 1058;
		/** 游戏配置 */
		public static final int PROC_1059_gameConfig          = 1059;
		/** 战斗选项 */
		public static final int PROC_1060_battleOptions       = 1060;
		/** 战斗选项列表 */
		public static final int PROC_1061_battleItems         = 1061;
		/** 修改战斗选项的值 */
		public static final int PROC_1062_changeItemVale      = 1062;
		/** 游戏引导 */
		public static final int PROC_1063_lead                = 1063;
		/** 请求仓库内的物品 */
		public static final int PROC_1064_loadStoreGoods      = 1064;
		/** 设置战斗模式 */
		public static final int PROC_1065_setBattleModel      = 1065;
		/** 请求VIP信息 */
		public static final int PROC_1066_vipInfo             = 1066;
		/** 加载活动及目标 */
		public static final int PROC_1067_loadActivity        = 1067;
		/** 领取活动奖励 */
		public static final int PROC_1068_activityAward       = 1068;
		/** 领取目标奖励 */
		public static final int PROC_1069_targetAward         = 1069;
		/** 客户端版本-平台-代码 */
		public static final int PROC_1070_versions            = 1070;
		/** 活动寻路 */
		public static final int PROC_1071_activityPath        = 1071;
		/** 申请进入摆摊状态 */
		public static final int PROC_1072_TO_SHOP_STATE       = 1072;
		/** 获得随机名称 */
		public static final int PROC_1073_randomName          = 1073;
		/** 渠道对接 */
		public static final int PROC_1074_channelJoin         = 1074;
		/** 翻牌 */
		public static final int PROC_1075_openCard            = 1075;
		/** 战斗设置 */
		public static final int PROC_1076_battleSet           = 1076;
		/** 确认界面 */
		public static final int PROC_1077_confirm             = 1077;
		/** 退出副本 */
		public static final int PROC_1078_exitDuplicate       = 1078;
		/** 充值 */
		public static final int PROC_1079_charge              = 1079;
		/** 重新登录 */
		public static final int PROC_1080_relogin             = 1080;
		/** 通过角色传送 */
		public static final int PROC_1081_locationRole        = 1081;
		/** 通知GM */
		public static final int PROC_1082_noticeGm            = 1082;
		/** 器灵施法 */
		public static final int PROC_1083_qilingSkill         = 1083;
		/** 默认游戏设置 */
		public static final int PROC_1084_defaultGameset      = 1084;
		/** 公会入驻礼包 */
		public static final int PROC_1085_gongHuiBateBag      = 1085;
		/** 限量神秘礼包 */
		public static final int PROC_1086_SecretBateBag       = 1086;
		/** 每日领取境界点 */
		public static final int RPOC_1087_getRealmPoint       = 1087;
		/** 时装和翅膀操作 */
		public static final int PROC_1088_FashAndCloak        = 1088;
		/** 每日登录奖励 */
		public static final int PROC_1089_dayAward            = 1089;
		/** 加载每日登录奖励信息 */
		public static final int PROC_1090_dayAwardInfo        = 1090;
		/** 用户充值 */
		public static final int PROC_1091_roleCharge          = 1091;
		/** 系统排行榜 */
		public static final int PROC_1092_ROLE_RANK           = 1092;
		/** 首充奖励 */
		public static final int PROC_1093_CHARGEFIRST         = 1093;
		/** 离线经验 */
		public static final int PROC_1094_OffLine_Experience  = 1094;
		/** 创建宗门 */
		public static final int PROC_1095_CREATE_SECT         = 1095;
		/** 加载宗门列表-申请加入宗门 */
		public static final int PROC_1096_SECT_LIST           = 1096;
		/** 取消加入宗门 */
		public static final int PROC_1097_CANCEL_JOIN         = 1097;
		/** 检查更新 */
		public static final int PROC_1098_CHECK_VER           = 1098;
		/** 客户端用 */
		public static final int PROC_1099_CLIENT_PRO          = 1099;
		/*************************************************************/
		/** 登录 */
		public static final int PROC_1101_HTML_LOGIN          = 1101;
		/** 一键注册 */
		public static final int PROC_1102_HTML_REGISTER       = 1102;
		/** 修改密码 */
		public static final int PROC_1103_REPASSWORD          = 1103;
		/** 绑定手机 */
		public static final int PROC_1104_BIND_PHONE          = 1104;
		// /** 进入游戏 */
		// public static final int PROC_1105_ENTER_GAME = 1105;
		/** 手机验证-发送手机号 */
		public static final int PROC_1106_VALIDATE_PHONE      = 1106;
		/** 找回密码 */
		public static final int PROC_1107_GET_PASSWORD        = 1107;
		/** 不再提醒修改密码 **/
		public static final int PROC_1108_UNRESET_PWD         = 1108;
		/** 释放法宝技能 */
		public static final int PROC_1109_PLAY_FABAO_SKILL    = 1109;
		/** 客户端模拟释放技能协议（客户端用） */
		public static final int PROC_1110_CLIENT_SKILL        = 1110;
		/** 对法宝操作处理 */
		public static final int PROC_1111_TREASURE_OPE        = 1111;
		/** 打开单人副本界面 */
		public static final int C2S_1112_SINGLE_DUPLICATE     = 1112;
		/** 进入单人副本 */
		public static final int C2S_1113_ENTER_DUPLICATE      = 1113;
		/** 接受任务 */
		public static final int PROC_1114_MISSION_ACCEPT      = 1114;
		/** 任务列表 */
		public static final int PROC_1115_MISSION_LIST        = 1115;
		/** 进入指定的副本场景 */
		public static final int PROC_1116_ENTER_POINT_DUP     = 1116;
		/** 购买指定物品 */
		public static final int PROC_1117_BUY_POINT_GOODS     = 1117;
		/** 宠物操作 */
		public static final int PROC_1118_PET_OPERATE         = 1118;
		/** 其他服务器 */
		public static final int C2S_1119_OTHER_SERVER         = 1119;
		/** 副本通关 */
		public static final int PROC_1120_PASS_DUPLICATE      = 1120;
		/** 境界操作 */
		public static final int PROC_1121_REALM               = 1121;
		/** 综合协议操作 */
		public static final int PROC_1122_PILL                = 1122;
		/** 世界地图操作 */
		public static final int PROC_1123_WORLD_MAP           = 1123;
		/** 客户端用来作法宝处理的空协议 */
		public static final int PROC_1124_FABAO_OP            = 1124;
		/** 完成一次游历 */
		public static final int PROC_1125_YOU_LI              = 1125;
		/** 血包操作 */
		public static final int PROC_1126_HP_BAG              = 1126;
		/** 当前副本结束跳到另一个副本协议 */
		public static final int PROC_1127_SKIP_DUPLICATE      = 1127;
		/** 竞技场 */
		public static final int PROC_1128_OPEN_ARENA          = 1128;
		/** 提醒重连 */
		public static final int PROC_1129_RECONNECT           = 1129;
		/** 区域关卡场景操作 */
		public static final int PROC_1130_AREASCENE_OPERATE   = 1130;
		/** 检查资源版本 */
		public static final int PROC_1131_RES_CHECK_VER       = 1131;
		/** 普通场景重连 */
		public static final int PROC_1132_COMMON_RECONNECT    = 1132;
		/** 一战到底相关操作协议 */
		public static final int PROC_1133_STAND_TO_THE_END    = 1133;
		/** 世界首领相关操作协议 */
		public static final int PROC_1134_LEADER_OPERATE      = 1134;
		/** 离开任务副本相关操作协议 */
		public static final int PROC_1135_MISSION_EXIT_DUP    = 1135;
		/** 时装相关操作协议 */
		public static final int PROC_1136_FASH_OPERATE        = 1136;
		/** 检查客户端代码版本号 */
		public static final int PROC_1137_CLIENT_CODE         = 1137;
		/** 世界BOSS的伤害 */
		public static final int PROC_1138_WORLD_BOSS_HURT     = 1138;
		/** 查看秘境的关卡信息 */
		public static final int PROC_1139_SECRET_SCENE_DETAIL = 1139;
		/** 进入秘境关卡 */
		public static final int PROC_1140_ENTER_SECRET_GATE   = 1140;
		/** 请求已开启的秘境列表 */
		public static final int PROC_1141_SECRET_SCENE_LIST   = 1141;
		/** 抽奖有关的操作(如寻宝等) */
		public static final int PROC_1142_LOTTERY             = 1142;
		/** 离开秘境 */
		public static final int PROC_1143_EXIT_SECRET_SCENE   = 1143;
		/** 藏宝图 */
		public static final int PROC_1144_TREASURE_MAP        = 1144;
		/** 秘境通关奖励 */
		public static final int PROC_1145_SECRET_SCENE_AWARD  = 1145;
		/** 打开藏宝图 */
		public static final int PROC_1146_TREASURE_MAP_OPEN   = 1146;
		/** 改变物品新获取的标志状态 */
		public static final int PROC_1147_CHANGE_NEW_STATE    = 1147;
		/** 改变功能项的状态 */
		public static final int PROC_1148_CHANGE_FUN_STATE    = 1148;
		/** 领奖中心操作 */
		public static final int PROC_1149_AWARD_CENTER        = 1149;
		/** 响应服务器的网络检查 */
		public static final int PROC_1150_NET_RESPONE         = 1150;
		/** 某物品不足，消耗另一物品进行补充 */
		public static final int PROC_1151_NEED_POINT_GOODS    = 1151;
		/** 打开挑战界面 */
		public static final int PROC_1152_OPEN_CHALLENGE      = 1152;
		/** 领取登录签到奖励 */
		public static final int PROC_1153_GET_SIGN_AWARD      = 1153;
		/** VIP界面操作 */
		public static final int PROC_1154_VIP_UI_OPERATE      = 1154;
		/** 触控用户登录 */
		public static final int PROC_1155_LOGIN_CHU_KONG      = 1155;
		/** 直接进入场景 */
		public static final int PROC_1156_DIRECT_ENTER_SCENE  = 1156;
		/** 请求物品道具 */
		public static final int PROC_1157_LOAD_GOODS_DATA     = 1157;
		/** 请求订单号 */
		public static final int PROC_1158_REQUEST_ORDERFORM   = 1158;
		/** 限时宠物活动 */
		public static final int PROC_1159_LIMIT_TIME_PET      = 1159;
		/** 需要验证APPSTORE返回的数据 */
		public static final int PROC_1160_STORE_RECEIPT       = 1160;
		/** 财源广进活动 */
		public static final int PROC_1161_MORE_MONEY          = 1161;
		/** 请求区段内的服务器信息 */
		public static final int PROC_1162_ZONE_SERVERS        = 1162;
		/** 冲级排行 */
		public static final int PROC_1163_RANK_LEVEL          = 1163;
		/** 斗法排行 */
		public static final int PROC_1164_RANK_DOUFA          = 1164;
		/** 战力排行 */
		public static final int PROC_1165_RANK_FIGHT          = 1165;
		/** 消费返还 */
		public static final int PROC_1166_AMOUNT_RETURN       = 1166;
		/** 充值返还 */
		public static final int PROC_1167_RECHARGE_RETURN     = 1167;
		/** 领话费 */
		public static final int PROC_1168_GET_PHONE_MONEY     = 1168;
		/** 限时等级礼包 */
		public static final int PROC_1169_LIMIT_LEVEL_GIFT    = 1169;
		/** 7天登录奖励 */
		public static final int PROC_1170_SEVEN_DAY           = 1170;
		/** 在线奖励 */
		public static final int PROC_1171_ONLINE_TIME         = 1171;
		/** 伙伴跟随 */
		public static final int PROC_1172_PET_FOLLOW          = 1172;
		/** 首关卡赠送宠物 */
		public static final int PROC_1173_FIRST_GATE_PET      = 1173;
		/** 查看指定的伙伴阵容信息 */
		public static final int PROC_1174_CHECK_ARRAY_INFO    = 1174;
		/** 超链接 */
		public static final int PROC_1175_LINKED              = 1175;
		/** 次日留存领话费 */
		public static final int PROC_1176_NEXT_DAY            = 1176;
		/** 邀请其他人打秘境 */
		public static final int PROC_1177_INVITE_ROLE         = 1177;
		/** 返回到角色列表 */
		public static final int PROC_1178_TO_ROLE_LIST        = 1178;
		/** 查看阵容详细 */
		public static final int PROC_1179_PETS_DETAIL         = 1179;
		/** 自由挑战 */
		public static final int PROC_1180_FREE_CHALLENGE      = 1180;
		/** 挑战阵容 */
		public static final int PROC_1181_CHALLENGE_PETS      = 1181;
		/** 扩展型登录 */
		public static final int PROC_1182_LOGIN_CUSTOM        = 1182;
		/** 上传教程进度 */
		public static final int PROC_1183_TEACHPROGRESS       = 1183;
		/** YAYA语音聊天 */
		public static final int PROC_1184_YAYA_CHAT           = 1184;
		/** 腾讯充值 */
		public static final int PROC_1185_TENCENT_CHARGE      = 1185;
		/** YAYA语音聊天 */
		// public static final int PROC_1186_YAYA_CHAT = 1186;
		/** 剧情 */
		public static final int PROC_1187_PLOT_OF_A_PLAY      = 1187;
		/** 直接进入普通场景 */
		public static final int PROC_1188_SWITCH_SCENE        = 1188;
		/** 推送私人秘境 */
		public static final int PROC_1189_PUSH_SECRET_SCENE   = 1189;
		/** 抢宝 */
		public static final int PROC_1190_GRAB_GOODS          = 1190;
		/** 豪华签到 */
		public static final int PROC_1191_CHARGE_SIGN         = 1191;
	}

	/**
	 * 游戏引导操作项
	 * 
	 * @author fanren
	 * 
	 */
	public interface Lead {
		/** 装备 */
		short equipGoods      = 1;
		/** 使用道具 */
		short useGoods        = 2;
		/** 学习技能 */
		short leanSkill       = 3;
		/** 装备技能 */
		short equipSkill      = 4;
		/** 装备药水 */
		short equipPotion     = 5;
		/** 发放任务 */
		short missionDispatch = 6;
		/** 导航到NPC并打开交互界面 */
		short toNpc           = 7;
	}

	public interface Server {
		/** 网络连接确认协议 */
		public final static int PROS_99999_NET_CONFIRM           = 99999;
		/** 登陆游戏 */
		public static final int PROS_5001_LOGIN                  = 5001;
		/** 通知客户端开始发送场景数据 */
		public static final int PROS_5002_SCENE_PROP             = 5002;
		/** 发送地图上的出口信息 */
		public static final int PROS_5003_MAP_EXIT               = 5003;
		/** 通知客户端开始发送玩家属性 */
		public static final int PROS_5004_USER_INFO              = 5004;
		/** 组队结果 */
		public final static int PROS_5005_GROUP                  = 5005;
		/** 发送地图怪物属性 */
		public static final int PROS_5006_MAP_MONSTER            = 5006;
		/** 发送地图上的NPC数据 */
		public static final int PROS_5007_MAP_NPC                = 5007;
		/** 发送NPC功能数据数据 */
		public static final int PROS_5008_NPC_FUNCTION           = 5008;
		/** 发送任务内容 */
		public static final int PROS_5009_MISSION_CONTENT        = 5009;
		/** NPC对白 */
		public static final int PROS_5010_NPC_DIALOG             = 5010;
		/** 播放动画文件 */
		public static final int PROS_5011_ANI                    = 5011;
		/** 完成任务，询问客户端是否要提交任务 */
		public static final int PROS_5012_FINISH_MISSION         = 5012;
		/** 发送玩家携带的武器 */
		public final static int PROS_5013_WUQI                   = 5013;
		/** 英雄释放技能 */
		public final static int PROS_5014_HERO_SKILL             = 5014;
		/** 寻找指定的NPC */
		public final static int PROS_5015_TO_NPC                 = 5015;
		/** 踢出角色 */
		public final static int PROS_5016_KICKEDOUT              = 5016;
		/** 信件转发给其他玩家 */
		public final static int PROS_5017_EMAIL                  = 5017;
		/** 发送玩家拥有道具的ID */
		public final static int PROS_5018_USER_ITEM              = 5018;
		/** 打开行囊 */
		public final static int PROS_5019_OPEN_PACKAGE           = 5019;
		/** 聊天信息 */
		public final static int PROS_5020_CHAT                   = 5020;
		/** 更新任务条件 */
		public final static int PROS_5021_UPDATE_MISSION         = 5021;
		/** 有新玩家进入 */
		public final static int PROS_5028_OTHER_PLAYER           = 5028;
		/** 告诉其他玩家出地图 */
		public final static int PROS_5029_EXIT_MAPLAYER          = 5029;
		/** 告诉其他玩家我的行走路径 */
		public final static int PROS_5030_COORDINATE             = 5030;
		/** 环顾四周结果 */
		public final static int PROS_5031_AROUND                 = 5031;
		/** 加好友结果 */
		public final static int PROS_5032_ADD_FRIEND             = 5032;
		/** 找不到资源文件 */
		public final static int PROS_5033_NO_FILE                = 5033;
		// /** 穴位操作 */
		// public final static int PROS_5034_IDENTITY = 5034;
		/** 对方退出聊天 */
		public final static int PROS_5036_EXIT_CHAT              = 5036;
		/** 返回购买道具的结果 */
		public final static int PROS_5039_BUY_ITEM               = 5039;
		/** 当前时间 */
		public final static int PROS_5054_CURRENT_TIME           = 5054;
		/** 接收npc状态。 发任务。收任务 */
		public final static int PROS_5070_NPC_STATE              = 5070;
		/** 转发像素路径 */
		public final static int PROS_5077_PATH                   = 5077;
		/** 发送资源文件数据 */
		public static final int PROS_5078_FILE_DATA              = 5078;
		/** 攻击其他玩家 */
		public final static int PROS_5079_HIT                    = 5079;
		/** 复活协议 */
		public final static int PROS_5080_RELIVE                 = 5080;
		/** 发送角色的技能 */
		public final static int PROS_5081_ROLE_SKILL             = 5081;
		/** 首次启动包 */
		public final static int PROS_5082_FIRST_START            = 5082;
		/** 游戏背景音乐 */
		public final static int PROS_5083_BG_MUSIC               = 5083;
		/** 发送法宝技能 */
		public final static int PROS_5084_FABAO_SKILL            = 5084;
		/** 信息通告 */
		public final static int PROS_5085_MESSAGE                = 5085;

		/** 发送系统信息 */
		public final static int PROS_5525_SYSTEM_INFO            = 5525;
		/** 加载副本场景 */
		public final static int PROS_5526_ENTER_SINGLE_DUPLICATE = 5526;
		/** 修改角色坐标（冲锋，撞击） */
		public final static int PROS_5527_CHANGE_POSITION        = 5527;
		/** 更新临时物理层 */
		public final static int PROS_5528_UPDATE_TEMP_PHY        = 5528;
		/** 进入竞技场协议 */
		public final static int PROS_5529_ENTER_ARENA            = 5529;
		/** 进入一战到底协议 */
		public final static int PROS_5530_ENTER_ONESTAND         = 5530;
		/** 进入世界首领副本协议 */
		public final static int PROS_5531_ENTER_LEADER           = 5531;
		/** 进入秘境协议 */
		public final static int PROS_5532_ENTER_SECRET_SCENE     = 5532;
		/** 进入秘境的BOSS关 */
		public final static int PROS_5533_SECRET_SCENE_BOSS      = 5533;

		/** 通知客户端可以开始游戏 */
		public static final int PROS_6000_START_GAME             = 6000;
		/** 更新怪物数据 */
		public static final int PROS_6001_UPDATE_MONSTER         = 6001;
		/** 测试网络延迟 */
		public static final int PROS_6002_NET_DELAY              = 6002;
		/** 准备释放技能 */
		public static final int PROS_6003_PRE_SKILL              = 6003;
		/** 打断技能 */
		public static final int PROS_6004_STOP_SKILL             = 6004;
		/** 刷新角色状态，该协议发送到数据均为变化量 */
		public static final int PROS_6005_UPDATE_ROLE            = 6005;
		/** 更新角色拥有的技能信息 */
		public static final int PROS_6006_ROLE_SKILL             = 6006;
		/** 动画创建与销毁 */
		public static final int PROS_6007_ANIMATION              = 6007;
		/** BUF创建与销毁 */
		public static final int PROS_6008_SKILL_BUF              = 6008;
		/** 开始战场 */
		public static final int PROS_6009_START_BATTLE           = 6009;
		/** 发送角色技能树信息 */
		public static final int PROS_6010_SKILL_TREE             = 6010;
		/** 刷新战场信息 */
		public static final int PROS_6011_UPDATE_BATTLE          = 6011;
		/** 脱离战场 */
		public static final int PROS_6012_EXIT_BATTLE            = 6012;
		/** 打开UI */
		public final static int PROS_6500_OPEN_UI                = 6500;
		/** 打开窗口(不叠加) */
		public final static int PROS_6501_OPEN_WINDOW            = 6501;
		/** 返回任务路径 */
		public static final int PROS_6502_TASKPATH               = 6502;
		/** 关闭窗口 */
		public static final int PROS_6503_CLOSE_WINDOW           = 6503;
		/** 通知人物换装 */
		public static final int PROS_6505_REPLACEMENT_SKELETON   = 6505;
		/** 发送网关的相关数据 */
		public final static int PROS_6506_GATEWAY                = 6506;
		/** 资源版本列表 */
		public final static int PROS_6507_RESOURCE_VER           = 6507;
		/** 网络连接确认协议 */
		public final static int PROS_6508_NET_CONFIRM            = 6508;
		/** 打开窗口(叠加) */
		public final static int PROS_6509_OPEN_WINDOW_OVERLAPP   = 6509;
		/** 玩家宠物出征 */
		public static final int PROS_6510_PET_BATTLE             = 6510;
		/** 玩家宠物休息/宠物当角色使用 */
		public static final int PROS_6511_PET_RESET              = 6511;
		/** 宠物路径 */
		public static final int PROS_6512_PET_PATH               = 6512;
		/** 收回宠物 */
		public static final int PROS_6513_DISPOSE_PET            = 6513;
		/** 世界地图信息 */
		public static final int PROS_6514_WORLD_MAP              = 6514;
		/** 天气 */
		public static final int PROS_6515_WEATHER                = 6515;
		/** 改变动作 */
		public static final int PROS_6516_CHANGE_ACTION          = 6516;
		/** 获得物品信息 */
		public static final int PROS_6517_addGoods               = 6517;
		/** 公共冷却 */
		public static final int PROS_6518_commonCool             = 6518;
		/** 完成任务 */
		public static final int PROS_6519_finishMission          = 6519;
		/** 通知采集 */
		public static final int PROS_6520_toPluck                = 6520;
		/** 反馈随机生成的名称 */
		public static final int PROS_6521_randomName             = 6521;
		/** 加载游戏设置 */
		public static final int PROS_6522_loadGameSet            = 6522;
		/** 放弃任务自动停止寻路 */
		public static final int PROS_6523_stopDispatch           = 6523;
		/** 账号操作 */
		public static final int PROS_6524_USER_OPERATE           = 6524;
		/** 通知客户端开始冷却技能 */
		public static final int PROS_6525_SKILL_COOL             = 6525;
		/** 检查客户端是否需要更新资源 */
		public static final int PROS_6526_RES_INFO               = 6526;
		/** 数据分析协议 */
		public static final int PROS_6527_DATA_INTERFACE         = 6527;
		/** 拼包协议 */
		public static final int PROS_6528_MULTI_PACKET           = 6528;
		/** 播放场景脚本 */
		public static final int PROS_6529_SCENE_SCRIPT           = 6529;
		/** 消息窗口 */
		public final static int PROS_8001_MESSAGE                = 8001;
		/** opcode */
		public final static int PROS_8002_OPCODE                 = 8002;
		/** 操作码 */
		public final static int PROS_8003_RESULT                 = 8003;
		/** 返回连接ID */
		public final static int PROS_8004_CONNECT_ID             = 8004;
		/** 关闭窗口 */
		public final static int PROS_8005_CLOSEWINDOW            = 8005;
		/** 返回百度多酷订单号 */
		public static final int PROC_8006_BDDK_ORDERID           = 8006;
		/** 宜付通支付返回的参数 */
		public static final int PROC_8007_EFT                    = 8007;
		/** 跳转地址 */
		public static final int PROC_8008_REDIRECT               = 8008;
		/** 通知客户端重新登录 */
		public static final int PROS_8009_RELOGIIN               = 8009;
		/** 检查网络连接 */
		public static final int PROS_8010_NET_PING               = 8010;
		/** 数据统计 */
		public static final int PROS_8011_BI_DATA                = 8011;
		/** 返回请求的订单号 */
		public static final int PROS_8012_ORDERFORM              = 8012;
	}

	public interface Result {
		/** 操作成功 */
		public final static int SUCCESS = 0;
		/** 重复 */
		public final static int REPEAT  = 1;
		/** 非法 */
		public final static int LAWLESS = 2;
	}

	public interface MusicCate {
		/** 音效 */
		byte SPECIAL_SOUND = 0;
		/** 背景音乐 */
		byte BACKGROUND    = 1;
	}

	/**
	 * 组队相关的操作标识
	 * 
	 * @author 李天喜
	 * 
	 */
	interface TeamPro {
		/** 申请创建队伍 */
		public final static byte APPLY_TEAM     = 0;
		/** 发出邀请 */
		public final static byte INVITE_ROLE    = 1;
		/** 同意加入队伍！ */
		public final static byte JOIN_TEAM      = 2;
		/** 拒绝加入队伍 */
		public final static byte REFUSE_JOIN    = 3;
		/** 退出队伍 */
		public final static byte OUT_TEAM       = 4;
		/** 解散队伍 */
		public final static byte UNLAY_TEAM     = 5;
		/** 队员信息 */
		public final static byte MEMBER_INFO    = 6;
		/** 踢出队伍 */
		public final static byte KICKOUT_TEAM   = 7;
		/** 转让队长 */
		public final static byte CHANGE_CAPTAIN = 8;
	}

	interface ChatMission {
		/** 查看 */
		public static final byte LOOK_TASK = 1;
	}

	/** 穴位操作项 */
	interface IdentityOptions {
		/** 升级 */
		byte upgrade = 1;
	}

	/**
	 * 信件操作
	 * 
	 * @author hp
	 * 
	 */
	interface EMailPro {
		/** 查看 */
		public final static byte LOOK              = 0;
		/** 发送 */
		public final static byte SEND              = 1;
		/** 删除 */
		public final static byte DELETE            = 2;
		/** 删除只读邮件 */
		public final static byte DELETE_READ_ONLY  = 3;
		/** 删除所有 */
		public final static byte DELETE_ALL        = 4;
		/** 取消交易邮件 */
		public final static byte CANCEL            = 5;
		/** 提取 */
		public final static byte EXTRACT           = 6;
		/** 拒绝交易邮件 */
		public final static byte REFUSE            = 7;
		/** 查看邮件物品 */
		public final static byte ATTACHMENT_DETAIL = 8;
		/** 提取 部 */
		public final static byte EXTRACT_ALL       = 9;
	}

	/**
	 * 副本操作
	 * */
	interface DuplicatePro {
		/** 打开多人副本 */
		public final static byte OPEN_DUP       = 1;
		/** 上一页 */
		public final static byte PRE_PAGE       = 2;
		/** 下一页 */
		public final static byte NEXT_PAGE      = 3;
		/** 刷新 */
		public final static byte UPDATE_PAGE    = 4;
		/** 加入副本 */
		public final static byte ENTER_ROOM     = 5;
		/** 创建房间 */
		public final static byte CREATE_ROOM    = 6;
		/** 退出房间 */
		public final static byte EXIT_ROOM      = 7;
		/** 关闭房间位置 */
		public final static byte CLOSE_POSITION = 8;
		/** 开始副本 */
		public final static byte START_DUP      = 9;
		/** 房间队员点击准备 */
		public final static byte CLICK_READY    = 10;
		// /** 创建副本 */
		// public final static byte CREATE = 0;
		// /** 浏览副本队伍 */
		// public final static byte VIEW = 1;
		// /** 加入副本队伍 */
		// public final static byte JOIN = 2;
		// /** 上一页 */
		// public final static byte PRE_PAGE = 3;
		// /** 下一页 */
		// public final static byte NEXT_PAGE = 4;
		// /** 进入副本 */
		// public final static byte ENTER = 5;
		// /** 拒绝副本 */
		// public final static byte REFUSE = 6;
		// /** 同意绝副本 */
		// public final static byte AGREE_JOIN = 7;
	}

	/** 副本操作数据值定义 */
	interface DuplicateValues {
		/** 英雄模式 */
		byte heroMode   = 1;
		/** 普通模式 */
		byte commonMode = 0;
	}

	interface Opcode {
		/** 错误 */
		public final static byte ERROR   = 0;
		/** 失败 */
		public final static byte FAIL    = 1;
		/** 成功 */
		public final static byte SUCCESS = 2;
		/** 警告 */
		public final static byte WARNING = 3;
	}

	/**
	 * 背包相关的操作标志
	 */
	interface SkillPro {
		/** 详细 */
		public final static byte DETAIL  = 1;
		/** 升级 */
		public final static byte LEVELUP = 2;
		/** 激活 */
		public final static byte ACTIVE  = 3;
		/** 装备 */
		public final static byte EQUIP   = 4;
		/** 卸载 */
		public final static byte UNLOAD  = 5;
	}

	interface Message {
		/** 普通信息 */
		public final static byte info    = 0;
		/** 警告 */
		public final static byte warn    = 1;
		/** 错误 */
		public final static byte error   = 2;
		/** 获得奖励 */
		public final static byte award   = 3;
		/** 离线 */
		public final static byte outline = 4;
	}

	interface Tips {
		/** 无提示 */
		public final static byte NONE  = 0;
		/** 显示一段时间后消失 */
		public final static byte flash = 1;
		/** 停留等到按键操作后消失 */
		public final static byte wait  = 2;

	}

	interface EveryDayMission {
		/** 接受每日任务 */
		public final byte ACCEPT = 1;
		/** 放弃每日任务 */
		public final byte GIVEUP = 2;
		/** 刷新每日任务 */
		public final byte UPDATE = 3;
		/** 提交每日任务 */
		public final byte COMMIT = 4;
	}

	/**
	 * 排行榜
	 */
	public static interface RankType {
		/** 取得排行列表名称 */
		public final static byte RANK_NAME_LIST = 0;
		/** 取得排行榜 */
		public final static byte GET_RANK_LIST  = 1;

		/** 10:等级排行 */
		public final static byte LEVEL_SORT     = 10;
		/** 元宝排行 */
		public final static byte RANK_YUAN_BAO  = 11;
		/** 灵石排行 */
		public final static byte RANK_LING_SHI  = 12;
		/** 18:战斗力排行 */
		public final static byte FIGHTER_SORT   = 18;
		/** 竞技场排行 */
		public final static byte ARENA_SORT     = 19;
		/** 一战到底的积分排行 */
		public final static byte ONE_STAND_SORT = 20;
		/** 世界首领积分 */
		public final static byte LEADER_SORT    = 21;

		/** 数据存储-开启的秘境 */
		public final static byte SECRET_SCENE   = 101;
		/** 数据存储-领奖中心 */
		public final static byte AWARD_CENTER   = 103;
	}

	/**
	 * 万灵榜
	 */
	public static interface TreasureList {
		/** 装备法宝 */
		public final static byte TREASURE_EQUIP            = 0;
		/** 卸下法宝 */
		public final static byte TREASURE_UNEQUIP          = 1;
		/** 开启法宝 */
		public final static byte TREASURE_OPEN             = 2;
		/** 查看法宝技能 */
		public final static byte CHECK_FABAO_SKILL         = 3;
		/** 开启法宝格子属性 */
		public final static byte TREASURE_GRID_PROP_OPEN   = 4;
		/** 退回法宝格子属性 */
		public final static byte TREASURE_GRID_PROP_BACK   = 5;
		/** 打开万灵绑界面 */
		public final static byte TREASURE_WanLingBang      = 6;

		/** 领悟法宝技能 */
		public final static byte TREASURE_OPEN_SKILL       = 7;
		/** 立即领悟法宝技能 */
		public final static byte TREASURE_QUICK_OPEN_SKILL = 8;
	}

	/**
	 * 任务列表
	 */
	public static interface TaskList {
		/** 任务列表 主线 */
		public final static byte TASK_OPE_LIST       = 0;

		/** 任务快速传送 */
		public final static byte TASK_QUICK_LOCATION = 1;

		/** 任务寻路 */
		public final static byte TASK_NAVIGATE       = 2;

		/** 任务删除 */
		public final static byte TASK_CLOSE          = 3;

		/** 快速完成 */
		public final static byte QUITK_FINISH        = 5;

		/** 导航 */
		public final static byte MISSION_NAVIGATE    = 6;

		/** 进入秘境 */
		public final static byte MISSION_MIJING      = 7;

	}

	/**
	 * 境界
	 */
	public static interface RealmType {
		/** 境界界面 */
		public final static byte REALM_INFO      = 1;

		/** 境界打通穴位 */
		public final static byte REALM_CAVE_OPEN = 2;

	}

	interface PKG_Example_6528 {
		// IoBuffer dup = IoBuffer.getPacketBuffer();
		// dup.setProtocol(UserProtocol.Server.PROS_6528_MULTI_PACKET);
		// IoBuffer pkg_1 = buf.duplicateBuffer();
		// pkg_1.flip();
		// pkg_1.putInt(0, pkg_1.limit() - 4);
		// dup.put(pkg_1.array(), 0, pkg_1.limit());
		// dup.put(pkg_1.array(), 0, pkg_1.limit());
		// dup.put(pkg_1.array(), 0, pkg_1.limit());
		// dup.put(pkg_1.array(), 0, pkg_1.limit());
		// role.sendData(dup);
	}
}
