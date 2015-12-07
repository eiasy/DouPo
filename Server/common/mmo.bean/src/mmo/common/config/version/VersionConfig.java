package mmo.common.config.version;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.tools.log.LoggerError;

public class VersionConfig {

	public static final class Client {
		/** 各个版本对应的ICON版本 */
		private static final Map<Integer, Byte>   iconVersion  = new HashMap<Integer, Byte>();
		/** 资源版本对应的UI */
		private static final Map<Integer, String> ui_versions  = new HashMap<Integer, String>();
		/** 资源版本 */
		public static final int                   VER_RESOURCE = 3;

		/** 空白界面脚本对应的Hashcode */
		public static final String                NONE_SCRIPT  = "";

		private static final void addScript(int uiId, String script) {
			if (ui_versions.containsKey(uiId)) {
				LoggerError.error("编号：" + uiId, new Exception("【ERROR】UI编号重复使用"));
			} else {
				ui_versions.put(uiId, script);
			}
		}

		static {
			iconVersion.put(VER_RESOURCE, IconVersion.middle);

			/** 战斗界面 */
			addScript(UIIdentity.BATTLE, "s0.s");
			/** 小地图 */
			addScript(UIIdentity.SMALL_MAP, "s1.s");
			/** 九宫格 */
			addScript(UIIdentity.NINE_TILE, "s2.s");
			/** 背包界面 */
			addScript(UIIdentity.PACKAGE, "s3.s");
			/** 英雄属性 */
			addScript(UIIdentity.HERO_PROPERTIES, "s4.s");
			/** 月卡商店 */
			addScript(UIIdentity.ID_8_shop_month_card, "s8.s");
			/** 英雄属性 */
			addScript(UIIdentity.ID_9_BUY_GIFT, "s9.s");
			/** 机缘商店 */
			addScript(UIIdentity.JIYUAN_SHOP, "s10.s");
			/** 荣誉商店 */
			addScript(UIIdentity.RONGYU_SHOP, "s11.s");
			/** 技能 面板 */
			addScript(UIIdentity.SKILL, "s12.s");
			/** 登录签到 */
			addScript(UIIdentity.SIGN_DAY, "s14.s");
			/** 7天连续登录 */
			addScript(UIIdentity.SEVEN_DAY, "s15.s");
			/** 商店 */
			addScript(UIIdentity.SHOP, "s16.s");
			/** 教程 */
			addScript(UIIdentity.SYSTEM_NOTICE, "s18.s");
			/** 神秘商店 */
			addScript(UIIdentity.mysteryShop, "s20.s");
			/** 教程2 */
			addScript(UIIdentity.teacher_2, "s118.s");
			/** 技能设置 */
			addScript(UIIdentity.SKILL_SET, "s21.s");
			/** 快捷设置 */
			addScript(UIIdentity.KEY_SET, "s22.s");
			/** 中键设置 */
			addScript(UIIdentity.CENTER_SET, "s23.s");
			/** 队伍界面 */
			addScript(UIIdentity.serverTeam, "s24.s");
			/** 邮箱界面 */
			addScript(UIIdentity.EMAIL, "s25.s");
			/** 未开放页面 */
			addScript(UIIdentity.NO_OPEN, "s26.s");
			/** 系统提示 */
			addScript(UIIdentity.SYSTEM_INFO, "s315.s");
			/** 规则说明 */
			addScript(UIIdentity.SYSTEM_RULE, "s28.s");
			/** 人物选项条 */
			addScript(UIIdentity.ROLE_SELECT_ITEM, "s29.s");
			/** 任务面板 */
			addScript(UIIdentity.TASK, "s30.s");
			/** 任务介绍 */
			addScript(UIIdentity.TASK_INFO, "s31.s");
			/** 次日留存话费 */
			addScript(UIIdentity.NEXT_DAY, "s33.s");
			/** 队伍面板 */
			addScript(UIIdentity.TEAM, "s34.s");
			/** 好友界面 */
			addScript(UIIdentity.FRIEND, "s35.s");
			/** NPC选项 */
			addScript(UIIdentity.NPC_ITEM, "s36.s");
			/** NPC任务表 */
			addScript(UIIdentity.NPC_TASK, "s37.s");
			/** 对话泡泡 */
			addScript(UIIdentity.DIALOG, "s38.s");
			/** 开始界面 */
			addScript(UIIdentity.START, "s40.s");
			/** 选择服务器 */
			addScript(UIIdentity.SELECT_SERVER, "s41.s");
			/** 创建角色 */
			addScript(UIIdentity.BUILD_ROLE, "s42.s");
			/** 选择角色 */
			addScript(UIIdentity.SELECT_ROLE, "s201.s");
			/** 对方属性界面 */
			addScript(UIIdentity.TARGET_PROP, "s50.s");
			/** 随身商店 */
			addScript(UIIdentity.ROLE_SHOP, "s53.s");
			/** 引导框 */
			addScript(UIIdentity.SELECT_TIPS, "s57.s");
			/** 首次登陆时的引导 */
			addScript(UIIdentity.LOGIN_FIRST, "s58.s");
			/** 境界界面 */
			addScript(UIIdentity.REALM_UI, "s66.s");
			/** 宗门成员列表界面 */
			addScript(UIIdentity.SECT_MEMBER_LIST, "s70.s");
			/** 宗门信息界面 */
			addScript(UIIdentity.SECT_INFO, "s71.s");
			/** 府第 */
			addScript(UIIdentity.SECT_MANSION, "s83.s");
			/** 金库 */
			addScript(UIIdentity.SECT_BURSE, "s90.s");
			/** 库房 s74.s */
			addScript(UIIdentity.SECT_STORE_HOURSE, "s85.s");
			/** 兽园 s75.s */
			addScript(UIIdentity.SECT_BEAST_PARK, "s86.s");
			/** 书院 s76.s */
			addScript(UIIdentity.SECT_COLLEGE, "s88.s");
			/** 培育神兽 s79.s */
			addScript(UIIdentity.SECT_EDUCATE_BEAST, "s91.s");
			/** 播放效果动画 */
			addScript(UIIdentity.PLAY_EFFECT_ANI, "s80.s");
			addScript(UIIdentity.LEVEL_UP_EFEECT, "s82.s");
			/** 招兵买马 */
			addScript(UIIdentity.SECT_BUY_ARM, "s93.s");
			/** 军备研究 */
			addScript(UIIdentity.EXPLORE_ARM, "s94.s");
			/** 排队 */
			addScript(UIIdentity.ROLE_QUEUE, "s95.s");
			/** 宗门列表 */
			addScript(UIIdentity.SECT_LIST, "s701.s");
			addScript(UIIdentity.NO_SECT_LIST, "s700.s");
			/** 培育道具 s85.s */
			addScript(UIIdentity.SECT_PRODUCE_GOODS, "s85.s");
			/** 购买军备数量 */
			addScript(UIIdentity.BUY_ARM_COUNT, "s86.s");
			/** 创建宗门 */
			addScript(UIIdentity.CREATE_SECT, "s703.s");
			/** 个人仓库 */
			addScript(UIIdentity.serverStoreHouse, "s21.s");
			/** 发起宗战 */
			addScript(UIIdentity.SECT_SQELL, "s92.s");
			/** 提示更新客户端包 */
			addScript(UIIdentity.UPDATE_CLIENT, "s93.s");
			/** 申请宗战/申请宗门 列表 */
			addScript(UIIdentity.APPLY_BATTLE, "s96.s");
			/** 查看兽园 */
			addScript(UIIdentity.CHECK_BEASTPARK, "s89.s");
			/** 查看库房 */
			addScript(UIIdentity.CHECK_STOREHOUSE, "s91.s");
			/** 查看NPC绑定的说明 */
			addScript(UIIdentity.CHECK_EXPLAIN, "s126.s");
			/** 兑换界面 */
			addScript(UIIdentity.EXCHANGE_MAIN, "s94.s");
			/** 系统公告 */
			addScript(UIIdentity.SYSTEM_NOTIC, "s113.s");
			/** 离线经验 */
			addScript(UIIdentity.OFFLINE_EXPERIENCE, "s140.s");
			/** 充值界面 */
			addScript(UIIdentity.TO_CHARGE, "s135.s");
			/** 公告界面 */
			addScript(UIIdentity.ONLINE_NOTICE, "s143.s");
			/** 检查版本 */
			addScript(UIIdentity.CHECK_VERSION, "s93.s");

			addScript(UIIdentity.TREASURE_OPE, "s660.s");
			addScript(UIIdentity.SINGLE_DUPLICATE, "s171.s");
			addScript(UIIdentity.ROLE_CREATE, "s202.s");
			addScript(UIIdentity.SERVER_OTHER, "s203.s");
			addScript(UIIdentity.PRIVEW_AWARD, "s400.s");
			addScript(UIIdentity.DEGREE_AWARD, "s402.s");
			addScript(UIIdentity.AWARD_INFO, "s403.s");
			addScript(UIIdentity.AWARD_CENTER, "s404.s");
			addScript(UIIdentity.CHECK_ARRAY_INFO, "s408.s");

			addScript(UIIdentity.OPEN_TRAIN_503, "s503.s");
			addScript(UIIdentity.MISSION_TALK, "s520.s");
			addScript(UIIdentity.MISSION_ACCEPT, "s521.s");
			addScript(UIIdentity.OPEN_TASK, "opentask.s");
			addScript(UIIdentity.MISSION_FINISH, "s522.s");
			addScript(UIIdentity.MISSION_COMMIT, "donetask.s");
			addScript(UIIdentity.MISSION_YOULI, "s530.s");
			addScript(UIIdentity.MISSION_MIJING, "s531.s");
			addScript(UIIdentity.MISSION_XIANDAO, "s532.s");
			addScript(UIIdentity.OPEN_STONE_551, "s551.s");
			addScript(UIIdentity.FRIEND_RECOMMEND, "s584.s");
			addScript(UIIdentity.EMAIL_DETAIL_591, "s591.s");
			addScript(UIIdentity.DUP_ENTER_MISSION_600, "s600.s");
			addScript(UIIdentity.MISSION_EQUIP_GOODS_103, "s103.s");
			addScript(UIIdentity.MISSION_TEACH_100, "s100.s");

			addScript(UIIdentity.DUP_ENTER_ROOM_608, "s608.s");
			addScript(UIIdentity.DUP_PASS_609, "s609.s");
			addScript(UIIdentity.REALM_NEXT_631, "s631.s");
			addScript(UIIdentity.DU_JIE_FAIL_632, "s632.s");
			addScript(UIIdentity.STOVE_650, "s650.s");
			addScript(UIIdentity.DUP_LOTTERY_611, "s611.s");
			addScript(UIIdentity.SECRET_SCENE_AWARD_612, "s612.s");
			addScript(UIIdentity.TREASURE_MAP_613, "s613.s");
			addScript(UIIdentity.GRAB_615, "s615.s");
			addScript(UIIdentity.SECRET_QUEUE_614, "s614.s");
			addScript(UIIdentity.OPEN_STRENGTH_620, "s620.s");
			addScript(UIIdentity.OPEN_REALM_630, "s630.s");
			addScript(UIIdentity.OUT_AREA_681, "s681.s");
			addScript(UIIdentity.OUT_ONE_STAND_683, "s683.s");
			addScript(UIIdentity.OUT_LEADER_684, "s684.s");
			addScript(UIIdentity.ONE_STAND_686, "s686.s");
			addScript(UIIdentity.LEADER_687, "s687.s");
			addScript(UIIdentity.UI_690_OUT_DOU_FA, "s690.s");
			addScript(UIIdentity.SECRET_SCENE_LIST_800, "s800.s");
			addScript(UIIdentity.OPEN_ONE_STAND_804, "s804.s");
			addScript(UIIdentity.CLEAR_DUPLICATE_806, "s806.s");
			addScript(UIIdentity.SECRET_GATE_LIST_807, "s807.s");
			addScript(UIIdentity.SECRET_ATTACK_ORDER_808, "sortdmg.s");
			addScript(UIIdentity.NEW_GOODS_NOTICE_8000, "notice.s");
			addScript(UIIdentity.NEED_POINT_MONEY, "s224.s");
			addScript(UIIdentity.OPEN_PRO_VIEW_519, "s519.s");
			addScript(UIIdentity.FA_BAO_DETAIL_1043, "s662.s");
			addScript(UIIdentity.SHOP_1044, "s6.s");
			addScript(UIIdentity.CHOICE_ARRAY_1039, "s803.s");
		}

		/**
		 * 通过客户端和UI编号获取UI脚本
		 * 
		 * @param clientVersion
		 *            客户端版本
		 * @param uiId
		 *            UI编号
		 * @return UI脚本
		 */
		public static final String getUIScript(int clientVersion, int uiId) {
			String uiScript = null;
			switch (clientVersion) {
				case VER_RESOURCE: {
					uiScript = ui_versions.get(uiId);
					break;
				}
				default: {
					uiScript = ui_versions.get(uiId);
				}
			}
			if (uiScript == null) {
				return NONE_SCRIPT;
			}
			return uiScript;
		}

		/**
		 * 通过客户端版本获取ICON版本
		 * 
		 * @param clientVersion
		 *            客户端版本
		 * @return ICON版本
		 */
		public static final byte getIconVerserion(int clientVersion) {
			Byte iconVer = iconVersion.get(clientVersion);
			if (iconVer == null) {
				return IconVersion.small;
			}
			return iconVer;
		}
	}

	public static class IconVersion {
		public static final byte               small  = 1;
		public static final byte               middle = 2;
		public static final byte               big    = 3;
		private static final Map<Byte, String> names  = new HashMap<Byte, String>();
		static {
			names.put(small, "小图标");
			names.put(middle, "中图标");
			names.put(big, "大图标");
		}

		public static final String[] getNames() {
			Collection<String> coll = names.values();
			String[] arr = new String[coll.size()];
			coll.toArray(arr);
			return arr;
		}

		public static final Set<Byte> getKeys() {
			return names.keySet();
		}

		public static final String getName(byte key) {
			return names.get(key);
		}
	}

	public static final class UIIdentity {
		/** 战斗界面 */
		public static final int BATTLE                  = 0;
		/** 小地图 */
		public static final int SMALL_MAP               = 1;
		/** 九宫格 */
		public static final int NINE_TILE               = 2;
		/** 背包界面 */
		public static final int PACKAGE                 = 3;
		/** 英雄属性 */
		public static final int HERO_PROPERTIES         = 4;
		/** 月卡商店 */
		public static final int ID_8_shop_month_card    = 8;
		/** 购买礼包 */
		public static final int ID_9_BUY_GIFT           = 9;
		/** 机缘商店脚本 */
		public static final int JIYUAN_SHOP             = 10;
		/** 荣誉商店脚本 */
		public static final int RONGYU_SHOP             = 11;
		/** 技能 面板 */
		public static final int SKILL                   = 12;
		/** 登录签到 */
		public static final int SIGN_DAY                = 14;
		/** 7天连续登录 */
		public static final int SEVEN_DAY               = 15;
		/** 商店 */
		public static final int SHOP                    = 16;
		/** 系统公告 */
		public static final int SYSTEM_NOTICE           = 18;
		/** 神秘商店 */
		public static final int mysteryShop             = 20;
		/** 教程2 */
		public static final int teacher_2               = 118;
		/** 技能设置 */
		public static final int SKILL_SET               = 21;
		/** 快捷设置 */
		public static final int KEY_SET                 = 22;
		/** 中键设置 */
		public static final int CENTER_SET              = 23;
		/** 队伍界面 */
		public static final int serverTeam              = 24;
		/** 邮箱界面 */
		public static final int EMAIL                   = 25;
		/** 未开放页面 */
		public static final int NO_OPEN                 = 26;
		/** 规则说明 */
		public static final int SYSTEM_RULE             = 28;
		/** 系统提示 */
		public static final int SYSTEM_INFO             = 315;
		/** 人物选项条 */
		public static final int ROLE_SELECT_ITEM        = 29;
		/** 任务面板 */
		public static final int TASK                    = 30;
		/** 任务介绍 */
		public static final int TASK_INFO               = 31;
		/** 次日留存话费活动 */
		public static final int NEXT_DAY                = 33;
		/** 队伍面板 */
		public static final int TEAM                    = 34;
		/** 好友界面 */
		public static final int FRIEND                  = 35;
		/** NPC选项 */
		public static final int NPC_ITEM                = 36;
		/** NPC任务表 */
		public static final int NPC_TASK                = 37;
		/** 对话泡泡 */
		public static final int DIALOG                  = 38;
		/** 开始界面 */
		public static final int START                   = 40;
		/** 选择服务器 */
		public static final int SELECT_SERVER           = 41;
		/** 创建角色 */
		public static final int BUILD_ROLE              = 42;
		/** 选择角色 */
		public static final int SELECT_ROLE             = 43;
		/** 对方属性界面 */
		public static final int TARGET_PROP             = 50;
		/** 随身商店 */
		public static final int ROLE_SHOP               = 53;
		/** 引导框 */
		public static final int SELECT_TIPS             = 57;
		/** 首次登陆时的引导 */
		public static final int LOGIN_FIRST             = 58;
		/** 境界界面 */
		public static final int REALM_UI                = 66;
		/** 宗门成员列表 */
		public static final int SECT_MEMBER_LIST        = 70;
		/** 宗门信息界面 */
		public static final int SECT_INFO               = 71;
		/** 宗门府第 */
		public static final int SECT_MANSION            = 73;
		/** 库房 s74.s */
		public static final int SECT_STORE_HOURSE       = 74;
		/** 兽园 s75.s */
		public static final int SECT_BEAST_PARK         = 75;
		/** 书院 s76.s */
		public static final int SECT_COLLEGE            = 76;
		/** 兵部 s77.s */
		public static final int SECT_WAR_MINISTRY       = 77;
		/** 宗门金库 */
		public static final int SECT_BURSE              = 78;
		/** 培育神兽 s79.s */
		public static final int SECT_EDUCATE_BEAST      = 79;
		/** 播放效果动画 */
		public static final int PLAY_EFFECT_ANI         = 80;
		/** 招兵买马 */
		public static final int SECT_BUY_ARM            = 81;
		/** 军备研究 */
		public static final int EXPLORE_ARM             = 94;
		/** 升级特效 */
		public static final int LEVEL_UP_EFEECT         = 82;
		/** 培育道具 s85.s */
		public static final int SECT_PRODUCE_GOODS      = 85;
		/** 购买军备数量 */
		public static final int BUY_ARM_COUNT           = 86;
		/** 创建宗门 */
		public static final int CREATE_SECT             = 703;
		/** 个人仓库 */
		public static final int serverStoreHouse        = 89;
		/** 宗门阵法 */
		public static final int SECT_SQELL              = 92;
		/** 提示更新客户端包 */
		public static final int UPDATE_CLIENT           = 93;
		/** 排队 */
		public static final int ROLE_QUEUE              = 95;
		/** 申请宗战 */
		public static final int APPLY_BATTLE            = 96;
		/** 查看兽园 */
		public static final int CHECK_BEASTPARK         = 97;
		/** 查看库房 */
		public static final int CHECK_STOREHOUSE        = 98;
		/** 查看NPC绑定的说明 */
		public static final int CHECK_EXPLAIN           = 126;
		/** 兑换界面 */
		public static final int EXCHANGE_MAIN           = 99;
		/** 系统公告 */
		public static final int SYSTEM_NOTIC            = 108;
		/** 离线经验 */
		public static final int OFFLINE_EXPERIENCE      = 109;
		/** 充值界面 */
		public static final int TO_CHARGE               = 110;
		/** 公告界面 */
		public static final int ONLINE_NOTICE           = 111;
		/** 检查版本 */
		public static final int CHECK_VERSION           = 112;

		/** 请求单人副本 */
		public static final int SINGLE_DUPLICATE        = 171;
		public static final int MISSION_TALK            = 172;
		public static final int MISSION_ACCEPT          = 173;
		public static final int MISSION_FINISH          = 174;
		public static final int MISSION_COMMIT          = 176;
		public static final int OPEN_TASK               = 175;
		/** 创建角色 */
		public static final int ROLE_CREATE             = 202;
		/** 其他服务器 */
		public static final int SERVER_OTHER            = 203;
		/** 精力体力等不足时打开界面 */
		public static final int NEED_POINT_MONEY        = 224;
		/** 预览奖励 */
		public static final int PRIVEW_AWARD            = 400;
		/** 副本第一次通关奖励 */
		public static final int DEGREE_AWARD            = 402;
		/** 物品奖励信息界面 */
		public static final int AWARD_INFO              = 403;
		/** 领奖中心 */
		public static final int AWARD_CENTER            = 404;
		/** 查看阵容 */
		public static final int CHECK_ARRAY_INFO        = 408;
		/** 打开培养界面 */
		public static final int OPEN_TRAIN_503          = 503;
		/** 打开灵根和渡劫属性预览 */
		public static final int OPEN_PRO_VIEW_519       = 519;
		public static final int MISSION_XIANDAO         = 532;
		public static final int MISSION_MIJING          = 531;
		public static final int MISSION_YOULI           = 530;
		/** 打开宝石界面 */
		public static final int OPEN_STONE_551          = 551;
		/** 好友推荐 */
		public static final int FRIEND_RECOMMEND        = 584;
		/** 万灵榜 */
		public static final int TREASURE_OPE            = 660;
		/** 教学 */
		public static final int MISSION_TEACH_100       = 100;
		/** 装备物品提示 */
		public static final int MISSION_EQUIP_GOODS_103 = 103;
		/** 邮件内容 */
		public static final int EMAIL_DETAIL_591        = 591;
		/** 加入副本任务 */
		public static final int DUP_ENTER_MISSION_600   = 600;
		/** 加入多人副本房间 */
		public static final int DUP_ENTER_ROOM_608      = 608;
		/** 副本通关界面 */
		public static final int DUP_PASS_609            = 609;
		/** 进行抽奖环节 */
		public static final int DUP_LOTTERY_611         = 611;
		/** 秘境奖励 */
		public static final int SECRET_SCENE_AWARD_612  = 612;
		/** 藏宝图 */
		public static final int TREASURE_MAP_613        = 613;
		/** 藏宝图抢夺钥匙 */
		public static final int GRAB_615                = 615;
		/** 多人秘境排队 */
		public static final int SECRET_QUEUE_614        = 614;
		/** 打开强化界面 */
		public static final int OPEN_STRENGTH_620       = 620;
		/** 打开境界界面 */
		public static final int OPEN_REALM_630          = 630;
		/** 境界渡劫界面 */
		public static final int REALM_NEXT_631          = 631;
		/** 渡劫失败 */
		public static final int DU_JIE_FAIL_632         = 632;
		/** 炼丹炉 */
		public static final int STOVE_650               = 650;
		/** 斗法出来后调 */
		public static final int OUT_AREA_681            = 681;
		/** 一战到底出来后调 */
		public static final int OUT_ONE_STAND_683       = 683;
		/** 世界首领出来后调 */
		public static final int OUT_LEADER_684          = 684;

		/** 一战到底通关脚本 */
		public static final int ONE_STAND_686           = 686;
		/** 世界首领通关脚本 */
		public static final int LEADER_687              = 687;
		/** 离开斗法 */
		public static final int UI_690_OUT_DOU_FA       = 690;
		/***/
		public static final int NO_SECT_LIST            = 700;
		/** 宗门列表 */
		public static final int SECT_LIST               = 701;
		/** 秘境列表 */
		public static final int SECRET_SCENE_LIST_800   = 800;
		/** 一战到底脚本 */
		public static final int OPEN_ONE_STAND_804      = 804;
		/** 扫荡副本 */
		public static final int CLEAR_DUPLICATE_806     = 806;
		/** 秘境关卡列表 */
		public static final int SECRET_GATE_LIST_807    = 807;
		/** 秘境伤害排序 */
		public static final int SECRET_ATTACK_ORDER_808 = 808;
		/** 选择阵容 */
		public static final int CHOICE_ARRAY_1039       = 1039;
		/** 查看法宝信息 */
		public static final int FA_BAO_DETAIL_1043      = 1043;
		/** 商城 */
		public static final int SHOP_1044               = 1044;
		/** 获得新物品 */
		public static final int NEW_GOODS_NOTICE_8000   = 8000;
	}
}
