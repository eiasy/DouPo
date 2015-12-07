package mmo.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mmo.tools.log.LoggerError;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class MoneyConfig {
	/** 灵石 */
	public static final int              LING_SHI_1000      = 1000;
	/** 元宝 */
	public static final int              YUAN_BAO_1001      = 1001;
	/** 仙露 */
	public static final int              XIAN_LU_1002       = 1002;
	/** 精力值 */
	public static final int              JING_LI_1003       = 1003;
	/** 境界点 */
	public static final int              JING_JIE_1004      = 1004;
	/** 荣誉 */
	public static final int              RONG_YU_1005       = 1005;
	/** 杀戮点 */
	public static final int              SHA_LU_1006        = 1006;
	/** 经验值 */
	public static final int              JING_YAN_1007      = 1007;
	/** 阅历 */
	public static final int              YUE_LI_1008        = 1008;
	/** 贡献 */
	public static final int              GONG_XIAN_1009     = 1009;
	/** 极品灵石 */
	public static final int              JI_PING_LING_1010  = 1010;
	/** 机缘 */
	public static final int              JI_YUAN_1011       = 1011;
	/** 修为点 */
	public static final int              XIU_WEI_1012       = 1012;
	/** 建设度 */
	public static final int              JIAN_SHE_1013      = 1013;
	/** 战绩 */
	public static final int              ZHAN_JI_1014       = 1014;
	/** 技能点 */
	// public static final int JI_NENG_DIAN_1015 = 1015;
	/** 亲密度 */
	public static final int              QIN_MI_DU_1016     = 1016;
	/** 培养丹 */
	public static final int              PEI_YANG_DAN_1017  = 1017;
	/** 星 */
	public static final int              STAR_1018          = 1018;
	/** 英雄牌 */
	public static final int              HERO_CARD_1019     = 1019;
	/** 召唤牌 */
	public static final int              CALL_CARD_1020     = 1020;
	/** 体力 */
	public static final int              TI_LI_1021         = 1021;
	/** 寻宝牌 */
	public static final int              FIND_TREASURE_1022 = 1022;
	/** 仙府经验 */
	public static final int              MANSION_EXP        = 1023;
	/** 人民币 */
	public static final int              CNY                = 1024;
	/** 幸运 */
	public static final int              LUCK               = 1025;

	private static int[]                 moneyArray         = null;
	private static Map<Integer, Short>   moneyKey           = new HashMap<Integer, Short>();
	private static Map<Integer, Short>   moneyMaxKey        = new HashMap<Integer, Short>();
	private static Map<Integer, Integer> moneyMaxCount      = new HashMap<Integer, Integer>();
	private static Map<Integer, String>  missionAward       = new HashMap<Integer, String>();
	private static Map<Integer, String>  moneyNames         = new HashMap<Integer, String>();

	public static final short getMoneyKey(int money) {
		Short key = moneyKey.get(money);
		if (key == null) {
			LoggerError.error("不存在该货币 :" + money);
			return -1;
		}
		return key;
	}

	public static final short getMoneyMaxKey(int money) {
		Short key = moneyMaxKey.get(money);
		if (key == null) {
			LoggerError.error("不存在该货币 :" + money);
			return -1;
		}
		return key;
	}

	public static final int getMoneyMaxCount(int money) {
		Integer count = moneyMaxCount.get(money);
		return count;
	}

	public static final boolean isMoney(int goodsId) {
		return moneyKey.containsKey(goodsId);
	}

	public static final short getKeyLingshi() {
		return moneyKey.get(LING_SHI_1000);
	}

	/**
	 * 获取币种
	 * 
	 * @return
	 */
	public static final int[] getMoneyArray() {
		return moneyArray;
	}

	public static String[] getMissionAwards() {
		String[] arr = new String[missionAward.size()];
		missionAward.values().toArray(arr);
		return arr;
	}

	public static int getIdByName(String name) {
		Set<Integer> keys = moneyNames.keySet();
		for (int key : keys) {
			if (moneyNames.get(key).equals(name)) {
				return key;
			}
		}
		return 0;
	}

	public static final String getName(int id) {
		return moneyNames.get(id);
	}

	public static final void init(String file) {
		parseXml(file);
	}

	protected static final String TAG_MONEYS    = "monyes";
	protected static final String TAG_MONEY     = "money";
	protected static final String ATT_ID        = "id";
	protected static final String ATT_KEY       = "key";
	protected static final String ATT_MAX_KEY   = "maxkey";
	protected static final String ATT_AWARD     = "award";
	protected static final String ATT_NAME      = "name";
	protected static final String ATT_MAX_COUNT = "maxcount";

	private static void parseXml(String file) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = null;
		int id = 0;
		short key = 0;
		short maxKey = 0;
		int maxCount = 0;
		String name = null;
		boolean award = false;
		Element notices = doc.getRootElement();
		List<Element> eleXingList = notices.getChildren(TAG_MONEY);
		if (eleXingList != null) {
			for (Element ele : eleXingList) {
				id = 0;
				key = 0;
				maxKey = 0;
				maxCount = 0;
				name = null;
				award = false;
				text = ele.getAttributeValue(ATT_ID);
				if (text != null && text.trim().length() > 0) {
					id = Integer.parseInt(text.trim());
					text = ele.getAttributeValue(ATT_KEY);
					if (text != null && text.trim().length() > 0) {
						key = Short.parseShort(text.trim());

						text = ele.getAttributeValue(ATT_MAX_KEY);
						if (text != null && text.trim().length() > 0) {
							maxKey = Short.parseShort(text.trim());
						}

						text = ele.getAttributeValue(ATT_MAX_COUNT);
						if (text != null && text.trim().length() > 0) {
							maxCount = Integer.parseInt(text.trim());
						}

						text = ele.getAttributeValue(ATT_NAME);
						if (text != null && text.trim().length() > 0) {
							name = text.trim();
							text = ele.getAttributeValue(ATT_AWARD);
							if (text != null && text.trim().length() > 0) {
								award = "true".equals(text.trim());
							}
							moneyKey.put(id, key);
							moneyMaxKey.put(id, maxKey);
							moneyMaxCount.put(id, maxCount);
							if (award) {
								missionAward.put(id, name);
							}
							moneyNames.put(id, name);
						}
					}
				}
			}
		}
		Set<Integer> keys = moneyKey.keySet();
		moneyArray = new int[keys.size()];
		{

		}
		int index = 0;
		for (int gk : keys) {
			moneyArray[index++] = gk;
		}
	}

	/** 货币的上限类型 */
	public class MoneyMaxCate {
		/** 上限值受到等级限制 */
		public static final short LIMIT_LEVEL = -1;
		/** 无上限类型 */
		public static final short NO_MAX_CATE = 1;
		// /** 上限200 */
		// public static final short MAX_200 = 2;
		// /** 上限400 */
		// public static final short MAX_400 = 3;
		/** 上限10 */
		public static final short MAX_10      = 4;
	}

	// public static final int getMoneySpecialMaxById(int money) {
	// if (money == JING_LI_1003 || money == TI_LI_1021) {
	// return 400;
	// }
	// return getMoneyMaxCount(money);
	// }
}
