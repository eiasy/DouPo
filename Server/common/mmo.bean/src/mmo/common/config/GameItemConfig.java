package mmo.common.config;


public class GameItemConfig extends BeanConfig {

	// ///////////////////////////////////////////////////////
	// //////////////////// 装备类道具 ////////////////////////
	// ///////////////////////////////////////////////////////
	/** 道具类别：刀 */
	public static final short    REAMER          = 1;
	/** 道具类别：棍 */
	public static final short    STICK           = 2;
	/** 道具类别：弓 */
	public static final short    BOW             = 3;
	/** 道具类别：剑 */
	public static final short    SWORD           = 4;
	/** 道具类别：枪，矛 */
	public static final short    SPEAR           = 5;
	/** 道具类别：帽子 */
	public static final short    HAT             = 6;
	/** 道具类别：项链 */
	public static final short    NECKLACE        = 7;
	/** 道具类别：戒指 */
	public static final short    FINGER_RING     = 8;
	/** 道具类别：腰带 */
	public static final short    SASH            = 9;
	/** 道具类别：鞋子 */
	public static final short    SHOES           = 10;
	/** 道具类别：斧子 */
	public static final short    AXE             = 11;
	/** 道具类别：鱼竿 */
	public static final short    FISHING_ROD     = 12;
	/** 道具类别：铁锹 */
	public static final short    SHOVEL          = 13;
	/** 道具类别：衣服 */
	public static final short    CLOTHING        = 14;
	// ////////////////////////////////////////////////////////
	// ///////////////////// 消耗类道具 ////////////////////////
	// ////////////////////////////////////////////////////////
	/** 道具类别：魔法瓶 */
	public static final short    MAGIC           = 101;
	/** 道具类别：生命药水 */
	public static final short    TONIC           = 102;
	/** 道具类别：礼花 */
	public static final short    FLOWER          = 103;
	/** 道具类别：宠物粮 */
	public static final short    PET_FOOD        = 104;
	/** 道具类别：点卡 */
	public static final short    TIME_CARD       = 105;
	/** 道具类别：箭 */
	public static final short    ARROW           = 106;
	/** 道具类别：打开传送门 */
	public static final short    OPEN_DOOR       = 107;
	/** 道具类别：单向传送 */
	public static final short    SINGLE_TRANSFER = 108;
	/** 道具类别：回城卷 */
	public static final short    GO_CITY         = 109;
	/** 道具类别：复活点 */
	public static final short    RELIVE_POINT    = 110;

	/** 道具名称 */
	public static final String[] itemCateName    = { "NULL", "刀", "棍", "弓", "剑", "矛", "帽子", "项链", "戒指", "腰带", "鞋子", "斧子", "鱼竿", "铁锹", "衣服", "魔法瓶",
	        "生命药水", "礼花", "宠物粮", "点卡", "箭", "打开传送门", "单向传送", "回城卷", "复活点" };
	/** 道具类别 */
	public static final short[]  itemCate        = { -1, REAMER, STICK, BOW, BOW, SPEAR, HAT, NECKLACE, FINGER_RING, SASH, SHOES, AXE, FISHING_ROD,
	        SHOVEL, CLOTHING, MAGIC, TONIC, FLOWER, PET_FOOD, TIME_CARD, ARROW, OPEN_DOOR, SINGLE_TRANSFER, GO_CITY, RELIVE_POINT };

	public final static String getCateName(Short cate) {
		int length = itemCate.length;
		for (int i = 0; i < length; i++) {
			if (itemCate[i] == cate) {
				return itemCateName[i];
			}
		}

		return cate + ":NULL";
	}

	public final static short getCate(String cateName) {
		if (cateName == null) {
			return -1;
		}
		int count = itemCateName.length;

		for (int i = 0; i < count; i++) {
			if (cateName.equals(itemCateName[i])) {
				return itemCate[i];
			}
		}
		return -1;
	}

	public final static int getCateIndex(int cate) {
		int length = itemCate.length;
		for (int i = 0; i < length; i++) {
			if (itemCate[i] == cate) {
				return i;
			}
		}
		return 0;
	}
}
