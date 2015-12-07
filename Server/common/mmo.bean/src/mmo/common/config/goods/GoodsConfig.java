package mmo.common.config.goods;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mmo.common.config.BeanConfig;
import mmo.common.config.StringLib;
import mmo.common.ids.GoodsIds;
import mmo.tools.util.MathUtil;

public class GoodsConfig extends BeanConfig {
	/** 新获得的物品 */
	public static final byte NEW_GOODS = 1;

	public final static class GridMaxSize {
		/** 储物袋区最大格子数 */
		public static final byte COMMONBAG_MAX_SIZE     = 127;
		/** 储物袋区初始开启格子数 */
		public static final byte COMMONBAG_BASE_SIZE    = 125;
		/** 宝石袋区最大格子数 */
		public static final byte STONEBAG_MAX_SIZE      = 100;
		/** 宝石袋区初始开启格子数 */
		public static final byte STONEBAG_BASE_SIZE     = 50;
		/** 装备区最大格子数 */
		public static final byte EQUIPBAG_MAX_SIZE      = 100;
		/** 装备区初始开启格子数 */
		public static final byte EQUIPBAG_BASE_SIZE     = 50;
		/** 装备碎片区最大格子数 */
		public static final byte EQUIPCHIPBAG_MAX_SIZE  = 100;
		/** 装备碎片区初始开启格子数 */
		public static final byte EQUIPCHIPBAG_BASE_SIZE = 50;
		/** 宠物背包区最大格子数 */
		public static final byte PETBAG_MAX_SIZE        = 100;
		/** 宠物背包区初始开启格子数 */
		public static final byte PETBAG_BASE_SIZE       = 50;
		/** 宠物碎片背包区最大格子数 */
		public static final byte PETBAGCHIP_MAX_SIZE    = 100;
		/** 宠物碎片背包区初始开启格子数 */
		public static final byte PETBAGCHIP_BASE_SIZE   = 50;
		/** 时装区最大格子数 */
		public static final byte FASHBAG_MAX_SIZE       = 100;
		/** 时装区开启格子数 */
		public static final byte FASHBAG_BASE_SIZE      = 50;
		/** 礼包区最大格子数 */
		public static final byte GIFT_MAX_SIZE          = 100;
		/** 礼包区开启格子数 */
		public static final byte GIFT_BASE_SIZE         = 50;
		/** 隐藏区最大格子数 */
		public static final byte HIDE_MAX_SIZE          = 120;
		/** 隐藏区开启格子数 */
		public static final byte HIDE_BASE_SIZE         = 120;
	}

	/** 物品来源 */
	public static class Source {
		/** 商城 */
		public static final byte shop    = 0;
		/** 游戏掉落F */
		public static final byte game    = 1;
		/** VIP宝箱开出的物品 */
		public static final byte VIP_BOX = 2;
		/** GM通过邮件发放的物品 */
		public static final byte GM      = 3;
	}

	public static class CustomState {
		/** 可出售 */
		public static final byte CAN_SELL     = 0;
		/** 不可出售 */
		public static final byte CAN_NOT_SELL = 1;
	}

	/**
	 * 物品功能项
	 */
	public static class FunctionType {
		/** 装备基础值 */
		public static final byte              BASE              = 1;
		/** 装备附加值 */
		public static final byte              EXPAND            = 2;
		/** 凹槽 */
		public static final byte              HOLE              = 3;
		/** 装备特殊属性 */
		public static final byte              SPECIAL           = 4;
		/** 技能书选项，用于绑定可以学习到底技能及学习限制条件 */
		public static final byte              FUNCTION_STUDY    = 5;
		/** 符咒类物品，使用后可以激活一个buf，持续性的提升玩家的部分属性 */
		public static final byte              FUNCTION_TOBUF    = 6;
		/** 符咒类物品，该选项帮等了一个可以激活的技能，该技能用于发出攻击，或创建一个有状态的区域 */
		public static final byte              FUNCTION_TOSKILL  = 7;
		/** 符咒类物品，该选项绑定了传送到目标场景及坐标 */
		public static final byte              FUNCTION_TRANSFER = 8;
		/** 使用物品时的坐标 */
		public static final byte              LOCATION_GOODS    = 9;

		/** 法宝 */
		public static final byte              TREASURE_GOODS    = 10;
		/** 法宝技能 */
		public static final byte              TREASURE_SKILLS   = 11;
		/** 商场物品购买后处理 */
		public static final byte              ITEM_BUY_AFTER_DO = 12;
		/** 物品奖励 */
		public static final byte              ITEM_AWAED_GOODS  = 13;
		/** 永久属性 */
		public static final byte              FOREVER           = 14;

		public static final Map<Byte, String> FUNCTIONS         = new HashMap<Byte, String>();
		static {
			FUNCTIONS.put(FUNCTION_STUDY, "技能书");
			FUNCTIONS.put(FUNCTION_TOBUF, "激活BUF");
			FUNCTIONS.put(FUNCTION_TOSKILL, "激活技能");
			FUNCTIONS.put(FUNCTION_TRANSFER, "传送卷轴");
		}

		public static final String[] getFunctionNames() {
			Collection<String> values = FUNCTIONS.values();
			String[] names = new String[values.size()];
			values.toArray(names);
			return names;
		}

		public static final Set<Byte> getFunction() {
			return FUNCTIONS.keySet();
		}

		public static final byte getFunction(String name) {
			if (name == null) {
				return 0;
			}
			Set<Byte> keys = FUNCTIONS.keySet();
			for (byte key : keys) {
				if (name.equals(FUNCTIONS.get(key))) {
					return key;
				}
			}
			return 0;
		}

		public static final String getFunctionName(byte key) {
			return FUNCTIONS.get(key);
		}
	}

	/**
	 * 物品品质
	 * 
	 * @author 李天喜
	 * 
	 */
	public static class Quality {
		/** 随机生成品介 */
		public static final byte               random        = -1;
		/** 下阶 - 白色 */
		public static final byte               QUALITY_LOW   = 0;
		/** 中阶 - 绿色 */
		public static final byte               QUALITY_MID   = 1;
		/** 上阶 - 蓝色 */
		public static final byte               QUALITY_TOP   = 2;
		/** 顶阶 - 紫色 */
		public static final byte               QUALITY_GREAT = 3;
		/** 神阶 - 橙色 */
		public static final byte               QUALITY_SHEN  = 4;
		private static final Map<Byte, String> QUALITYS      = new HashMap<Byte, String>();

		static {
			QUALITYS.put(random, "随机");
			QUALITYS.put(QUALITY_LOW, "下阶");
			QUALITYS.put(QUALITY_MID, "中阶");
			QUALITYS.put(QUALITY_TOP, "上阶");
			QUALITYS.put(QUALITY_GREAT, "顶阶");
			QUALITYS.put(QUALITY_SHEN, "神阶");
		}

		/**
		 * 随机物品的品介
		 * 
		 * @return 品质编号
		 */
		public static final byte getQuality() {
			return (byte) MathUtil.getRandom(0, 4);
		}

		public static final String[] getQualityNames() {
			Collection<String> values = QUALITYS.values();
			String[] names = new String[values.size()];
			values.toArray(names);
			return names;
		}

		public static final byte getQualityValue(String name) {
			if (name == null) {
				return 0;
			}
			Set<Byte> keys = QUALITYS.keySet();
			for (Byte key : keys) {
				if (name.equals(QUALITYS.get(key))) {
					return key;
				}
			}
			return 0;
		}

		public static final String getQualityName(byte key) {
			String name = QUALITYS.get(key);
			if (name == null) {
				name = key + ":NULL";
			}
			return name;
		}
	}

	public static class Grid {
		/** 玉佩 */
		public static final short JADE         = 0;
		/** 头盔 */
		public static final short ARMET        = 1;
		/** 戒指 */
		public static final short RING         = 2;
		/** 衣服 */
		public static final short CLOTHES      = 3;
		/** 鞋子 */
		public static final short SHEOES       = 4;
		/** 武器 */
		public static final short WEAPON       = 5;
		/** 法宝 */
		public static final short TRUMP        = 3;

		/** 时装衣服 */
		public static final short FASH_CLOTHES = 10;
		/** 时装武器 */
		public static final short FASH_WEAPON  = 11;
		/** 时装翅膀 */
		public static final short FASH_CLOAK   = 12;
		/** 时装坐骑 */
		public static final short FASH_MOUNT   = 13;
	}

	public static final class PropKey {
		/** 开放状态 */
		public static final byte OPEN_STATE     = 104;
		/** 套装ID */
		public static final byte PERTAIN_ID     = 105;
		/** 法宝格子开启顺序 */
		public static final byte GRID_STACK     = 106;
		/** 绑灵价格 */
		public static final byte moneyId        = 107;
		// /** 元宝价格 */
		// public static final byte moneyCount = 108;
		/** 绑定状态 */
		public static final byte BINDSTATE      = 110;
		/** 与角色绑定的ID */
		public static final byte BIND_ID        = 111;
		/** 物品编号 */
		public static final byte GOODS_ID       = 112;
		/** 存放的分区编号 */
		public static final byte ZONE_ID        = 113;
		/** 存放的格子编号 */
		public static final byte GRID_ID        = 114;
		/** 数量 */
		public static final byte COUNT          = 115;
		/** 耐久度 */
		public static final byte DURABILITY     = 116;
		/** 强化 */
		public static final byte STRENGTHEN     = 117;
		/** 品质 */
		public static final byte PINZHI         = 118;
		/** 最大耐久度 */
		public static final byte MAX_DURABILITY = 120;
		/** 品介 **/
		public static final byte QUALITY        = 121;
		/** 有效期 */
		public static final byte EFFECT_TIME    = 122;
		/** 法宝灵气值 */
		public static final byte FABAO_LINGQI   = 123;
		/** 可被制作的装备ID */
		public static final byte MAKE_ID        = 124;
		/** 物品状态 */
		public static final byte STATE          = 125;
		/** 物品穿戴角色的ID */
		public static final byte EQUIP_ROLE_ID  = 126;

	}

	public static final class State {
		/** 未开启 */
		public static final byte CLOSE = 0;
		/** 开启 */
		public static final byte OPEN  = 1;
		/** 破损 */
		public static final byte WORN  = 2;

	}

	public static class Operate {
		/** 不可使用 */
		public static short ESTOP  = 0;
		/** 可使用 */
		public static short EFFECT = (short) (1 << 11);
		/** 可装备 */
		public static short EQUIP  = (short) (1 << 12);
		/** 强化 */
		public static short STRONG = (short) (1 << 10);
		/** 鉴定 */
		public static short CHECK  = (short) (1 << 9);
		/** 打孔 */
		public static short HOLE   = (short) (1 << 8);
	}

	public static class SkillOpenStatus {
		/** 不可使用 */
		public static byte CLOSE    = 0;
		/** 开启中 */
		public static byte PRE_OPEN = 1;
		/** 可使用 */
		public static byte OPEN     = 2;
	}

	public static class Hole {
		/** 初始开孔数 */
		public static final byte OPEN_COUNT  = 0;
		/** 未开孔 */
		public static final byte CLOSE       = 1;
		/** 无孔 */
		public static final byte NONE        = 0;
		/** 已开孔 */
		public static final byte OPEN        = 2;

		/** 开第1孔消耗打孔器的数量 */
		public static final int  OPEN_COST_1 = 1;
		/** 开第2孔消耗打孔器的数量 */
		public static final int  OPEN_COST_2 = 4;
		/** 开第3孔消耗打孔器的数量 */
		public static final int  OPEN_COST_3 = 8;
		/** 开第4孔消耗打孔器的数量 */
		public static final int  OPEN_COST_4 = 16;
		/** 每个孔改变颜色消耗元宝 */
		public static final int  COLOR_COST  = 50;

		public static byte getMaxHoleCount(byte star) {
			if (star == 2) {
				return 1;
			} else if (star == 3) {
				return 2;
			} else if (star == 4) {
				return 3;
			} else if (star == 5) {
				return 4;
			}
			return 0;
		}

		public static int getOpenCost(int index) {
			if (index == 0) {
				return OPEN_COST_1;
			} else if (index == 1) {
				return OPEN_COST_2;
			} else if (index == 2) {
				return OPEN_COST_3;
			} else if (index == 3) {
				return OPEN_COST_4;
			}
			return Integer.MAX_VALUE;
		}
	}

	public static class PertainState {
		/** 套装属性激活 */
		public static final byte ACTIVATE   = 1;
		/** 套装属性未激活 */
		public static final byte UNACTIVATE = 0;
	}

	public static class AwardUiButtonState {
		/** 可领取 */
		public static final byte CAN_GET = 0;
		/** 不可领取 */
		public static final byte UN_GET  = 1;

		public static final String getButtonName(byte state) {
			if (state == CAN_GET) {
				return StringLib.cdkey.bateMoney;
			} else {
				return StringLib.Button.exit;
			}
		}
	}

	/** 是否是要发指定公告的物品 */
	public static boolean isSpecialNotice(int goodsId) {
		switch (goodsId) {
			case GoodsIds.JING_LI_WAN_900009:
			case GoodsIds.JYD_SHJ_900051:
			case GoodsIds.JYD_DJ_900052:
			case GoodsIds.JYD_SJ_900053:
			case GoodsIds.JNS_SHJ_900056:
			case GoodsIds.JNS_DJ_900057:
			case GoodsIds.JNS_SJ_900058: {
				return true;
			}
		}
		return false;
	}
}
