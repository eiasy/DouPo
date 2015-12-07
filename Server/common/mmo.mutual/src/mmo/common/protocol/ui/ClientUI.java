package mmo.common.protocol.ui;

import java.util.HashMap;
import java.util.Map;

import mmo.tools.util.string.StringSplit;

public class ClientUI {
	public static final class Color {
		/** 附加属性 */
		public static final int    EXTEND_PROP   = 0x23f61e;
		/** 基础属性 */
		public static final int    BAS_PROP      = 0x000000;
		/** 未鉴定： **/
		public static final int    UN_CHECK      = 0xae3d0c;
		/** 需求不满足的项,物品耐久不够的时候显示红 */
		public static final int    RED           = 0xff0000;
		
		public static final int    LINK           = 0xff0000;
		/** 完成 */
		public static final int    FINISH        = 0x98e861;
		/** 未完 */
		public static final int    DISPATCHED    = 0xf94426;
		/** 可接 */
		public static final int    TASK_DISPATHC = 0xfff200;
		/** 附加属性颜色 */
		public static final int    EXPAND_PROP   = 0x2151b3;

		/** 低阶：d8d1ce 中阶：23f61e 上阶：0d69d1 顶阶：d10dcf 神阶：fe7a41 */
		private static final int[] QUALITY       = new int[] { 0xffffff, 0xcff00, 0xbaff, 0xff00f0, 0xff6600 };

		// private static final int[] QUALITY = new int[] { 0xd8d1ce, 0x23f61e, 0x0d69d1, 0xd10dcf, 0xfe7a41 };

		public static final int getQualityColor(byte quality) {
			if (quality < 0 || quality > 4) {
				quality = 0;
			}
			return QUALITY[quality];
		}
	}

	public static interface ColorId {
		/** 红色样式 */
		int RED           = 26;
		/** 地点(黄色) */
		int ADDRESS_COLOR = 19;
		/** 男角色(黄色) */
		int MAN_COLOR     = 31;
		/** 女角色(蓝色) */
		int WOMAN_COLOR   = 32;
		/** 怪物(黄色) */
		int MONSTERCOLOR  = 28;
		/** 魔修宗门(橙色) */
		int MOXIU_COLOR   = 24;
		/** 灵修宗门(淡蓝色) */
		int LINGXIU_COLOR = 27;
	}

	public static final class Animation {
		public static final String TASK                = "quest_tips.ani";
		public static final byte   DISPATCH_ACTION     = 0;
		public static final byte   FINISH_ACTION       = 1;
		public static final byte   TASK_COUNT          = 1;

		public static final String strengthenAni       = "ui_action.ani";
		public static final byte   strengthenSuc       = 2;
		public static final byte   strengthenFail      = 3;
		public static final byte   strengthenPlayCount = 1;

	}

	public static final class StringCommand {
		/** 指令开始 */
		public static final short  start             = (short) 0xc000;
		/** 指令结束 */
		public static final short  end               = (short) 0xc100;
		private final static byte  CMD_RESET_COLOR   = 2;
		public final static byte   CMD_COLOR         = 1;
		public static final String CMD_COLOR_END     = "[/color]";
		public final static byte   CMD_SPRITE_FRAME  = 23;
		public final static byte   CMD_SPRITE_ACTION = 27;
		public final static byte   CMD_STRING_LIST   = 32;
		public final static byte   CMD_ICON          = 3;
		public final static String CMD_FONT_END      = "[/fontstyle]";
		public final static String SPRITE            = "modle.ani";

		public final static short  STAR              = 36;
		public final static short  HALF_STAR         = 37;
		public final static short  FRAME_HOLE        = 18;
		/** 可接 */
		public final static String DIATATCH          = "ui_DIATATCH";                                                    // 60;
		/** 未完成 */
		public final static String DOING             = "ui_DOING";                                                       // 61;
		/** 已完成 */
		public final static String FINISH            = "ui_FINISH";                                                      // 62;
		/** 副本 */
		public final static String DUPLICATE         = "ui_DUPLICATE";                                                   // 63;
		/** 修理 */
		public final static String REPAIRE           = "ui_REPAIRE";
		/** 对话 */
		public final static String DIALOG            = "ui_DIALOG";
		/** 其他 */
		public final static short  OTHER             = 63;                                                               // fixed.废弃了
		/** 换行图标 */
		public final static short  FRAME_ID_68       = 68;                                                               // fixed.废弃了
		/** 新邮件 */
		// public final static short FRAME_ID_72 = 72;
		public final static String NEWMAIL           = "ui_NEWMAIL";
		/** 已读邮件 */
		// public final static short FRAME_ID_73 = 73;
		public final static String READED            = "ui_READED";
		/** 附件 */
		// public final static short FRAME_ID_74 = 74;
		public final static String ATTACHMENT        = "ui_ATTACHMENT";
		/** 绑灵 */
		public final static short  MONEY_ID_47       = 47;
		/** 灵石 */
		public static final int    lingshi           = 1;
		/** 灵石-金图标 */
		public final static String GOLD_ICON_L       = "money_3";
		/** 灵石 -银图标 */
		public final static String SILVER_ICON_L     = "money_2";
		/** 灵石-铜图标 */
		public final static String COPPER_ICON_L     = "money_1";
		/** 绑灵石-金图标 */
		public final static String GOLD_ICON_B       = "money_b3";
		/** 绑灵石 -银图标 */
		public final static String SILVER_ICON_B     = "money_b2";
		/** 绑灵石-铜图标 */
		public final static String COPPER_ICON_B     = "money_b1";
		/** 金银铜的兑换率 100 */
		public final static int    RATE              = 100;

		private final static byte  CMD_NEW_LINE      = 10;
		private final static int   SIGN_CMD          = 0xc000;
		public final static int    SIGN_END_CMD      = 0xc100;
		/** 客户端字符串换行 */
		public static String       BR                = ((char) (SIGN_CMD | CMD_NEW_LINE)) + "" + ((char) (SIGN_END_CMD));

		public final static String SPRITE_UI_FIGHT   = "ui_fight.ani";
		// public final static short FRAME_UI_FIGHT_MALE = 0;
		// public final static short FRAME_UI_FIGHT_FEMALE = 1;

		public final static char   AVATAR_NPC        = '0';
		public final static char   AVATAR_MONSTER    = '0';

		public final static String linked(String show, String cmd) {
			StringBuilder sb = new StringBuilder();
			sb.append((char) (ClientUI.StringCommand.start | ClientUI.StringCommand.CMD_COLOR)).append((char) (Color.LINK >> 16))
			        .append((char) (Color.LINK & 0xffff)).append((char) (ClientUI.StringCommand.end));

			sb.append((char)(ClientUI.StringCommand.start | StringSplit.CMD_LINK)).append((char) show.length()).append(show).append((char) cmd.length())
			        .append(cmd).append((char) (ClientUI.StringCommand.end));
			
			sb.append((char) (ClientUI.StringCommand.start | ClientUI.StringCommand.CMD_RESET_COLOR)).append((char) (ClientUI.StringCommand.end));
			return sb.toString();
		}

		public final static String getAni(short frame) {
			StringBuilder sb = new StringBuilder();
			sb.append((char) (ClientUI.StringCommand.start | ClientUI.StringCommand.CMD_SPRITE_FRAME))
			        .append((char) ClientUI.StringCommand.SPRITE.length()).append(ClientUI.StringCommand.SPRITE).append((char) frame);
			sb.append((char) (ClientUI.StringCommand.end));
			return sb.toString();
		}

		public final static String getAni(String aniFile, short frame) {
			StringBuilder sb = new StringBuilder();
			sb.append((char) (ClientUI.StringCommand.start | ClientUI.StringCommand.CMD_SPRITE_FRAME))
			        .append((char) ClientUI.StringCommand.SPRITE.length()).append(ClientUI.StringCommand.SPRITE).append((char) frame);
			sb.append((char) (ClientUI.StringCommand.end));
			return sb.toString();
		}

		public final static String getIconImg(String iconName) {
			StringBuilder sb = new StringBuilder();
			sb.append((char) (ClientUI.StringCommand.start | ClientUI.StringCommand.CMD_ICON)).append((char) iconName.length()).append(iconName);
			sb.append((char) (ClientUI.StringCommand.end));
			return sb.toString();
		}

		public final static String toColor(int color, String str) {
			StringBuilder sb = new StringBuilder();
			sb.append((char) (ClientUI.StringCommand.start | ClientUI.StringCommand.CMD_COLOR)).append((char) (color >> 16))
			        .append((char) (color & 0xffff)).append((char) (ClientUI.StringCommand.end));
			sb.append(str);
			sb.append((char) (ClientUI.StringCommand.start | ClientUI.StringCommand.CMD_RESET_COLOR)).append((char) (ClientUI.StringCommand.end));
			return sb.toString();
		}

		public final static String fontStyle(int styleID) {
			StringBuilder str = new StringBuilder();
			str.append("[fontstyle:").append(styleID).append("]");
			return str.toString();
		}

		public final static String offset(int offset) {
			StringBuilder str = new StringBuilder();
			str.append("[offset:").append(offset).append("]");
			return str.toString();
		}

		/**
		 * 获取ani的动作
		 * 
		 * @param aniFile
		 *            ani文件
		 * @param action
		 *            动作编号
		 * @return 转换后的字符串，不需要二次转换
		 */
		public final static String getAniAction(String aniFile, int action) {
			StringBuilder sb = new StringBuilder();
			sb.append((char) (start | CMD_SPRITE_ACTION)).append((char) aniFile.length()).append(aniFile).append((char) action);
			sb.append((char) (end));
			return sb.toString();
		}

		public final static String getAvatar(String key) {
			int hashcode = key.hashCode();
			StringBuilder sb = new StringBuilder();
			sb.append((char) (start | CMD_STRING_LIST)).append((char) (hashcode >> 16)).append((char) (hashcode & 0x0000ffff));
			sb.append((char) (end));
			return sb.toString();
		}

		public final static String getAvatarNpc() {
			StringBuilder sb = new StringBuilder();
			sb.append((char) (start | CMD_SPRITE_ACTION)).append((char) 0).append((char) 0);
			sb.append((char) (end));
			return sb.toString();
		}

		public final static String getAvatarMonster() {
			StringBuilder sb = new StringBuilder();
			sb.append((char) (start | CMD_SPRITE_ACTION)).append((char) 0).append((char) 0);
			sb.append((char) (end));
			return sb.toString();
		}

		public final static String newLine() {
			return StringSplit.newLine;
		}

		public static String getStringInfo(String key) {
			int hashcode = key.hashCode();
			StringBuilder sb = new StringBuilder();
			sb.append((char) (start | CMD_STRING_LIST)).append((char) (hashcode >> 16)).append((char) (hashcode & 0x0000ffff));
			sb.append((char) (end));
			return sb.toString();
		}

		public static String getMoneyInfo(int type, int count) {
			// if (count == 0) {
			// return null;
			// }
			StringBuilder sb = new StringBuilder();
			sb.append(count);
			// int gold = count / (RATE * RATE);
			// int silver = count % (RATE * RATE) / RATE;
			// int copper = count % (RATE * RATE) % RATE;
			// if (type == lingshi) {
			// if (gold == 0 && silver == 0) {
			// sb.append(copper).append(getStringInfo(COPPER_ICON_L));
			// } else if (gold == 0 && copper == 0) {
			// sb.append(silver).append(getStringInfo(SILVER_ICON_L));
			// } else if (silver == 0 && copper == 0) {
			// sb.append(gold).append(getStringInfo(GOLD_ICON_L));
			// } else if (copper == 0) {
			// sb.append(gold).append(getStringInfo(GOLD_ICON_L)).append(silver).append(getStringInfo(SILVER_ICON_L));
			// } else if (gold == 0) {
			// sb.append(silver).append(getStringInfo(SILVER_ICON_L)).append(copper).append(getStringInfo(COPPER_ICON_L));
			// } else {
			// sb.append(gold).append(getStringInfo(GOLD_ICON_L)).append(silver).append(getStringInfo(SILVER_ICON_L)).append(copper)
			// .append(getStringInfo(COPPER_ICON_L));
			// }
			// } else {
			// if (gold == 0 && silver == 0) {
			// sb.append(copper).append(getStringInfo(COPPER_ICON_B));
			// } else if (gold == 0 && copper == 0) {
			// sb.append(silver).append(getStringInfo(SILVER_ICON_B));
			// } else if (silver == 0 && copper == 0) {
			// sb.append(gold).append(getStringInfo(GOLD_ICON_B));
			// } else if (copper == 0) {
			// sb.append(gold).append(getStringInfo(GOLD_ICON_B)).append(silver).append(getStringInfo(SILVER_ICON_B));
			// } else if (gold == 0) {
			// sb.append(silver).append(getStringInfo(SILVER_ICON_B)).append(copper).append(getStringInfo(COPPER_ICON_B));
			// } else {
			// sb.append(gold).append(getStringInfo(GOLD_ICON_B)).append(silver).append(getStringInfo(SILVER_ICON_B)).append(copper)
			// .append(getStringInfo(COPPER_ICON_B));
			// }
			// }
			return sb.toString();
		}
	}

	public static final byte SELF_PROPERTY = 0;

	/** npc对话 */
	public static final class NpcDialog {
		public static final byte ID = 5;

		/** 操作码 */
		public static final class Code {

			/** 交互命令头 */
			public static final byte        cmd                 = 0;
			/** 攻击 */
			public static final byte        cmdAttack           = 1;
			/** 打开副本界面 */
			public static final byte        openDuplicate       = 2;
			/** 多人秘境排队 */
			public static final byte        cmdSecretSceneQueue = 4;
			public static Map<Byte, String> notes               = new HashMap<Byte, String>();
			// { "下一步", "可接任务", "完成", "返回", "接受", "寻路" };
			/** 下一步 */
			public static final byte        next                = 0;
			/** npc发放任务 */
			public static final byte        dispatch            = 1;
			/** 已经发放的任务 */
			public static final byte        dispatched          = 2;
			/** 返回 */
			public static final byte        goback              = 3;
			/** 接受任务 */
			public static final byte        dominssion          = 4;
			/** 导航 */
			public static final byte        missionNavigate     = 5;
			/** 提交任务 */
			public static final byte        commit              = 6;
			/** 7.显示奖励 */
			public static final byte        showAward           = 7;
			/** 8.查看物品 */
			public static final byte        goods               = 8;
			/** 9.发送操作码 */
			public static final byte        sendCode            = 9;
			/** 10.完成任务奖励 */
			public static final byte        getAward            = 10;
			/** 11.npc交互 */
			public static final byte        mutual              = 11;
			/** 查看npc销售的物品 */
			public static final byte        npcSellGoods        = 13;
			/** 玩家出售物品 */
			public static final byte        roleSellGoods       = 14;
			/** 放弃任务 */
			public static final byte        cancelMission       = 15;
			/** 维修装备 */
			public static final byte        repairEquip         = 16;
			/** 宗门操作 */
			public static final byte        sectOperate         = 17;
			/** 仓库操作 */
			public static final byte        storeOperate        = 18;
			/** 通过导航与NPC交互 */
			public static final byte        navigateMutual      = 19;
			// /** 秘境传送 */
			// public static final byte secretArea = 20;
			/** 抽奖NPC */
			public static final byte        lotteryer           = 21;
			/** 绑定的说明 */
			public static final byte        explain             = 22;
			/** 内测奖励使者 */
			public static final byte        bateTest            = 23;
			/** 魔塔试炼使者 */
			public static final byte        mota                = 24;
			/** 快速提交任务 */
			public static final byte        commitDirect        = 25;
			/** 单一任务 */
			public static final byte        commitSingle        = 26;
			/** 活跃任务引导 */
			public static final byte        livenessGuide       = 27;

			static {
				init();
			}

			static void init() {
				if (notes == null) {
					notes = new HashMap<Byte, String>();
				}
				notes.put(next, "下一步");
				notes.put(dispatch, "可接任务");
				notes.put(dispatched, "完成");
				notes.put(goback, "返回");
				notes.put(dominssion, "接受");
				notes.put(missionNavigate, "寻路");
				notes.put(commit, "提交");
				notes.put(showAward, "奖励");
				notes.put(sendCode, "发送操作码");
				notes.put(getAward, "获得奖励");
				notes.put(mutual, "交互");
				notes.put(npcSellGoods, "商店");
				notes.put(roleSellGoods, "出售");
				notes.put(cancelMission, "丢弃任务");
				notes.put(repairEquip, "维修");
				notes.put(sectOperate, "创建宗门");
			}

			public static final String getCodeName(byte code) {
				String name = notes.get(code);
				if (name == null) {
					return code + ":null";
				}
				return name;
			}
		}

		/** 操作项指令 */
		public static final class Command {
			/** 交互指令头 */
			public static final String cmd_npc             = "0#";
			/** 攻击指令 */
			public static final String cmdAttack           = "1#";
			/** 打开副本 */
			public static final String openDuplicate       = "2#";
			/** 游历 */
			public static final String cmdYouli            = "3#";
			/** 多人秘境排队 */
			public static final String cmdSecretSceneQueue = "4#";
			/** 下一步 */
			public static final String next                = "0@";
			/** npc发放任务 */
			public static final String dispatch            = "1@";
			/** 已经发放的任务 */
			public static final String distatched          = "2@";
			/** 返回 */
			public static final String goback              = "3@";
			/** 接受任务 */
			public static final String domission           = "4@";
			/** 导航 */
			public static final String missionNavigate     = "5@";
			/** 提交任务 */
			public static final String commit              = "6@";
			/** 7.显示奖励 */
			public static final String showAward           = "7@";
			/** 8.查看物品 */
			public static final String goods               = "8@";
			/** 9.发送操作码 */
			public static final String sendCode            = "9@";
			/** 10.完成任务奖励 */
			public static final String getAward            = "10@";
			/** 11.npc交互 */
			public static final String mutual              = "11@";
			/** 发放副本 */
			public static final String duplicate           = "12@";
			/** 查看npc销售的物品 */
			public static final String npcSellGoods        = "13@";
			/** 玩家出售物品 */
			public static final String roleSellGoods       = "14@";
			/** 放弃任务 */
			public static final String cancelMission       = "15@";
			/** 维修装备 */
			public static final String repairEquip         = "16@";
			/** 宗门操作 */
			public static final String sectOperate         = "17@";
			/** 仓库操作 */
			public static final String storeOperate        = "18@";
			/** 通过导航与NPC交互 */
			public static final String navigateMutual      = "19@";
			/** 秘境传送师 */
			public static final String openSecretArea      = "20@";
			/** 抽奖NPC */
			public static final String lotteryer           = "21@";
			/** NPC绑定的功能说明 */
			public static final String explain             = "22@";
			/** 内测奖励使者 */
			public static final String bateTest            = "23@";
			/** 魔塔试炼使者 */
			public static final String mota                = "24@";
			/** 快速完成任务 */
			public static final String commitDirect        = "25@";
			/************************************************/
			public static final String ID                  = "$";
		}
	}

	/**
	 * 主类别：人物
	 */
	public static final class ROLE {
		public static final short ID                   = 300;
		/** 角色培养 */
		public static final short MAIN_TRAIN_303       = 303;
		/** 角色称号 */
		public static final short MAIN_TRAIN_TITLE_304 = 304;

		/** 自己 */
		public static final class SELF {
			public static final byte ID = 0;
		}

		/** 区服务器角色列表 */
		public static final class LIST {
			public static final byte ID = 3;
		}

		/** 焦点角色 */
		public static final class FOCUS {
			public static final byte ID = 1;
		}

		/** 查看对方 */
		public static final class TARGET {
			public static final byte ID = 2;
		}
	}

	/**
	 * 主类别：物品
	 */
	public static final class GOODS {
		/** 物品模块主类别 */
		public static final short ID                   = 200;
		/** 查看制作装备主类别 */
		public static final short MAKE_ID              = 201;
		/** 装备镶嵌界面主类别 */
		public static final short INLAY_ID             = 203;

		/** 制作玉佩的材料 主类别 */
		public static final short MATERAL_ID_210       = 210;
		/** 制作头盔的材料 主类别 */
		public static final short MATERAL_ID_211       = 211;
		/** 制作戒指的材料 主类别 */
		public static final short MATERAL_ID_212       = 212;
		/** 制作衣服的材料 主类别 */
		public static final short MATERAL_ID_213       = 213;
		/** 制作鞋子的材料 主类别 */
		public static final short MATERAL_ID_214       = 214;
		/** 制作武器的材料 主类别 */
		public static final short MATERAL_ID_215       = 215;

		/** 装备区子类别 */
		public static final byte  SUB_EQUIP_ZONNE_10   = 10;
		/** 时装区子类别 */
		public static final byte  SUB_EQUIP_FASHION_11 = 11;

		/** 合成物品 */
		public static final byte  subCompositeGoods    = 43;
		/** 合成材料 */
		public static final byte  subCompositeMaterial = 44;
		/** 币种 */
		public static final byte  subMoneyCate         = 60;

		/** 摆摊 **/
		public static final class SelfShop {
			public static final byte ID = 45;
		}

		/** 当前聊天信息物品表 */
		public static final class Chat {
			public static final byte ID = 30;
		}
	}

	/** 副本 */
	public static final class Duplicate {
		/** 关卡进度 */
		public static final short MAIN_GATE_400  = 400;
		/** 英雄副本查看掉落主类别 */
		public static final short HERO_MAIN_403  = 403;
		/** 多人副本查看掉落主类别 */
		public static final short MULTI_MAIN_404 = 404;
		/** 副本组队平台 */
		public static final short MULTI_WAIT_405 = 405;

		/** 当前副本子类别 */
		public static final short SUB_CUR_0      = 0;
		/** 普通模式子类别 */
		public static final short SUB_COMMON_1   = 1;
		/** 英雄模式子类别 */
		public static final short SUB_HERO_2     = 2;
		/** 多人副本子类别 */
		public static final short SUB_GROUP_3    = 3;
		/** 通关奖励子类别 */
		public static final short SUB_AWARD_4    = 4;
		/** 通关抽奖子类别 */
		public static final short SUB_LOTTERY_5  = 5;

		public static final byte  ID             = 30;

		public static final class CopyList {
			public static final byte ID = 0;
		}

		public static final class TeamList {
			public static final byte ID = 1;
		}

		public static final class Doing {
			public static final byte ID = 2;
		}

		public static final class Selected {
			public static final byte ID = 3;
		}
	}

	/**
	 * UI指令
	 * 
	 * @author hp
	 * 
	 */
	public static interface UI {
		/** 清空对象列表 */
		public static final byte CLEAR_OBJECT_LIST         = 2;
		/** 清空对象 */
		public static final byte CLEAR_OBJECT              = 3;
		/** 删除对象列表 */
		public static final byte DELETE_OBJECT_LIST        = 5;
		/** 删除对象，已知序号 */
		public static final byte DELETE_OBJECT_BY_INDEX    = 6;
		/** 删除对象，知道属性 */
		public static final byte DELETE_OBJECT_BY_PROPERTY = 7;
		/** 添加对象属性 */
		public static final byte ADD_OBJECT_PROPERTY       = 9;
		/** 发送属性列表 */
		public static final byte ADD_PROPERTY_LIST         = 10;
		/** 添加一组属性 */
		public static final byte ADD_PROPERTY_GROUP        = 11;
		/** 添加对象 */
		public static final byte ADD_OBJECT                = 12;
		/** 移动对象,主类别不变 */
		public static final byte MOVE_OBJECT_14            = 14;
		/** 移动列表中的对象，已知对象的序号，插入新的位置 */
		public static final byte MOVE_OBJECT_16            = 16;
		/** 修改对象属性，已知对象的某一个属性 */
		public static final byte UPDATE_OBJECT             = 19;
		/** 骨骼动画 */
		public static final byte SKELETION_ANIMATION       = 20;
		/** 长度 */
		public static final byte ITEM_LENGTH               = 21;
		/** 清空主类别下的数据 */
		public static final byte CLEAR_MAIN_CATE           = 22;
		/** 拷贝主子类别到 另一个主子类别下，往后追加 */
		public static final byte COPY_OBJECT               = 23;
		/** 根据序号引用一个对象的数据 */
		public static final byte REFERENCE_OBJECT_24       = 24;
		/** 向列表中插入对象，已知对象的序号，插入新的位置 */
		public static final byte MOVE_OBJECT_25            = 25;
		/** 根据一个已知属性引用一个对象的数据 */
		public static final byte REFERENCE_OBJECT_26       = 26;
		/** 根据一个已知属性，删除对象并删除其所有引用 */
		public static final byte DELETE_ALL_BY_PROP_27     = 27;
	}

	public static class CmdMission {
		/** 更新 */
		public static final byte update = 1;
		/** 删除 */
		public static final byte delete = 2;
		/** 添加 */
		public static final byte add    = 3;
	}

	public static class BagUiZone {
		/** 装备区 */
		public static final short EQUIPAGE   = 0;
		/** 本命法宝区 */
		public static final short LIFE       = 10;
		/** 时装区 */
		public static final short FASH       = 1;
		/** 秘籍区 */
		public static final short DRUG       = 12;
		/** 储物袋区 */
		public static final short BAGZONE    = 13;

		// ///////////////////////////////////////////////////////////
		/** 仓库信息 */
		public static final short storeInfo  = 70;
		/** 个人仓库 */
		public static final short store10    = 70;
		/** 个人仓库 */
		// ///////////////////////////////////////////////////////////
		/** 店铺商品区 */
		public static final short SHOP_GOODS = 45;
	}
}
