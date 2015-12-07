package mmo.common.config;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	/** 系统根目录 */
	public final static String                ROOT              = System.getProperty("user.dir");
	/** 每页显示50条记录 */
	public final static int                   PAGE_COUNT        = 50;
	/*******************************************************/
//	public final static int                   TILE_WIDTH        = 32;
//	public final static int                   TILE_HEIGHT       = 32;
	/** 树上节点存放类别的键 */
	public final static String                TREE_CATE         = "treeCate";
	/** 树的根节点 */
	public final static int                   TREE_ROOT         = 1;
	/** 游戏世界的分区节点 */
	public final static int                   TREE_ZONE         = 2;
	/** 游戏世界的场景节点 */
	public final static int                   TREE_MAP          = 3;
	/** 信息列表节点 */
	public final static int                   TREE_OUTLINE      = 4;
	/** 信息列表节点 */
	public final static int                   TREE_RESOURCE     = 5;
	/** 数据节点 */
	public final static int                   TREE_ELEMENT      = 6;
	/** 树中节点用该键来保存分区数值 */
	public final static String                ZONE              = "zone";
	/** 树中节点用该键来保存场景编号 */
	public final static String                MAP               = "map";
	/** 树中节点用该键来保存元素编号 */
	public final static String                ID                = "id";
	/** 游戏中具体的场景 */
	public final static String                EDITOR_SOURCE     = "editorSource";

	/******************************************************/

	/** 选项卡用来保存类别的键 */
	public final static String                TAB_CATE          = "tabCate";
	public final static int                   TAB_NULL          = 51;
	public final static int                   TAB_MEMORY        = 52;
	public final static int                   TAB_RESOURCE      = 53;
	public final static int                   TAB_SCENE         = 54;
	public final static int                   TAB_MONSTER       = 55;
	public final static int                   TAB_NPC           = 56;
	public final static int                   TAB_ROLE          = 57;
	public final static int                   TAB_OUTLINE       = 58;
	public final static int                   DB_TABLE_LIST     = 59;

	/*********************************************************/
	/** 游戏中的性别 */
	public final static String[]              SEX               = { "男", "女" };

	/** 数据库信息 **/
	public final static int                   DB_SCENE_LIST     = 101;
	public final static int                   DB_MONSTER_LIST   = 102;
	public final static int                   DB_NPC_LIST       = 103;
	public final static int                   DB_SKILL_LIST     = 104;
	public final static int                   DB_ROLE_LIST      = 106;
	public final static int                   DB_SCENE          = 107;                                                                         // 场景画面
	public final static int                   DB_SCENE_INFO     = 108;                                                                         // 场景数据
	public final static int                   DB_MISSION_LIST   = 109;
	public final static int                   DB_GOODS_LIST     = 110;
	public final static int                   DB_TRIGGER_LIST   = 111;
	public final static int                   DB_DUPLICATE_LIST = 112;
	public final static int                   DB_ACTIVITY_LIST  = 113;
	public final static int                   DB_TARGET_LIST    = 114;
	public final static int                   DB_NOTICE_LIST    = 115;
	public static final String[]              DB_TABLE_NAME     = { "角色", "场景", "怪物", "NPC", "技能", "任务", "物品",  "副本", "活动", "目标", "公告" };
	public final static int[]                 TABLE_ID          = { DB_ROLE_LIST, DB_SCENE_LIST, DB_MONSTER_LIST, DB_NPC_LIST, DB_SKILL_LIST,
	        DB_MISSION_LIST, DB_GOODS_LIST, DB_DUPLICATE_LIST, DB_ACTIVITY_LIST, DB_TARGET_LIST, DB_NOTICE_LIST };
	private final static Map<Integer, String> TITLE             = new HashMap<Integer, String>();

	static {
		TITLE.put(TAB_MONSTER, "怪物数据");
		TITLE.put(TAB_NPC, "NPC");
		TITLE.put(TAB_MEMORY, "内存数据");
		TITLE.put(TAB_ROLE, "在线玩家");
		TITLE.put(TAB_RESOURCE, "资源管理");
		TITLE.put(TAB_SCENE, "场景画面");
		TITLE.put(DB_TABLE_LIST, "数据表列表");
		TITLE.put(DB_SCENE_LIST, "场景列表");
		TITLE.put(DB_MONSTER_LIST, "怪物列表");
		TITLE.put(DB_NPC_LIST, "NPC列表");
		TITLE.put(DB_SKILL_LIST, "技能信息");
		TITLE.put(DB_SCENE, "场景画面");
		TITLE.put(DB_SCENE_INFO, "场景数据");
		TITLE.put(DB_MISSION_LIST, "任务列表");
		TITLE.put(DB_GOODS_LIST, "物品列表");
//		TITLE.put(DB_TRIGGER_LIST, "触发器列表");
		TITLE.put(DB_DUPLICATE_LIST, "副本列表");
		TITLE.put(DB_ACTIVITY_LIST, "活动列表");
		TITLE.put(DB_TARGET_LIST, "目标列表");
		TITLE.put(DB_NOTICE_LIST, "公告列表");
	}

	public final static String getTitle(int key) {
		String value = TITLE.get(key);
		if (value == null) {
			value = "NULL";
		}
		return value;
	}
}
