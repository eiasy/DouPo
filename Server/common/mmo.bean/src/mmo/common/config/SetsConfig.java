package mmo.common.config;

/**
 * 游戏设置用到的键
 * 
 * @author fanren
 * 
 */
public class SetsConfig {
	/**********************************************/
	/******************* 战斗栏快捷键 ***************/
	/**********************************************/
	/******* 战斗栏快捷键 ******/
	public static final int   SHORTCUT_KEY_0   = 0;
	public static final int   SHORTCUT_KEY_1   = 1;
	public static final int   SHORTCUT_KEY_2   = 2;
	public static final int   SHORTCUT_KEY_3   = 3;
	public static final int   SHORTCUT_KEY_4   = 4;
	public static final int   SHORTCUT_KEY_5   = 5;
	public static final int   SHORTCUT_KEY_6   = 6;
	public static final int   SHORTCUT_KEY_7   = 7;
	public static final int   SHORTCUT_KEY_8   = 8;
	public static final int   SHORTCUT_KEY_9   = 9;

	/** 快捷键-血瓶 */
	public static final int   SHORTCUT_HP_101  = 101;
	/** 快捷键-蓝瓶 */
	public static final int   SHORTCUT_MP_102  = 102;

	/*******************************************************************/
	/***************************** 游戏设置项 ****************************/
	/*******************************************************************/
	/** 自动接受组队 */
	public static final short autoTeam_0       = 0;
	/** 屏蔽聊天信息 */
	public static final short hideChat_1       = 1;
	/** 屏蔽玩家名称 */
	public static final short hideRoleName_2   = 2;
	/** 屏蔽小地图 */
	public static final short hideLittleMap_3  = 3;
	/** 屏蔽角色形象 */
	public static final short hideRoleFigure_4 = 4;
	/** 设置显示人数 */
	public static final short showRoleCount_5  = 5;
	/** 屏蔽地图资源 */
	public static final short hideMapRes_6     = 6;
//	/** 战斗设置-血瓶 */
//	public static final short battleHp_7       = 7;
//	/** 战斗设置-气血百分比 */
//	public static final short battleHpScale_8  = 8;
//	/** 战斗设置-蓝瓶 */
//	public static final short battleMp_9       = 9;
//	/** 战斗设置-灵力百分比 */
//	public static final short battleMpScale_10 = 10;
//	/** 自动使用灵石购买 */
//	public static final short autoLingshi_11   = 11;
//	/** 屏蔽其他玩家，除队友外，看不到其他角色（0可以看到，>0看不到） */
//	public static final short HIDE_OTHER_12    = 12;

	/*******************************************************************/
	/***************************** 判断是否设置 1表示已经设置 ****************************/
	/*******************************************************************/
	/** 同意自动组队 */
	public static final short autoTeam         = 1;
	/** 同意屏蔽聊天信息 */
	public static final short hideChat         = 1;

}
