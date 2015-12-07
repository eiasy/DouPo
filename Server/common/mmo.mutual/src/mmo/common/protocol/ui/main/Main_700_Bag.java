package mmo.common.protocol.ui.main;

import java.util.HashMap;
import java.util.Map;

public class Main_700_Bag {
	/** 普通储物袋 主类别 */
	public static final short               main_700_CommonBag    = 700;
	/** 宝石袋 主类别 */
	public static final short               main_701_StoneBag     = 701;
	/** 礼包袋 主类别 */
	public static final short               main_702_GiftBag      = 702;
	/** 装备袋 主类别 */
	public static final short               main_703_EquipBag     = 703;
	/** 装备碎片袋 主类别 */
	public static final short               main_704_EquipChipBag = 704;
	/** 时装袋 主类别 */
	public static final short               main_707_FashBag      = 707;
	/** 隐藏袋（该背包袋物品无法查看） 主类别 */
	public static final short               main_799_HideBag      = 799;

	private final static Map<String, Short> bagNames              = new HashMap<String, Short>();
	private final static Map<Short, String> bagValues             = new HashMap<Short, String>();
	static {
		bagNames.put("道具袋", main_700_CommonBag);
		bagNames.put("宝石袋", main_701_StoneBag);
		bagNames.put("礼包袋", main_702_GiftBag);
		bagNames.put("装备袋", main_703_EquipBag);
		bagNames.put("装备碎片袋", main_704_EquipChipBag);
		bagNames.put("时装袋", main_707_FashBag);

		bagValues.put(main_700_CommonBag, "道具袋");
		bagValues.put(main_701_StoneBag, "宝石袋");
		bagValues.put(main_702_GiftBag, "礼包袋");
		bagValues.put(main_703_EquipBag, "装备袋");
		bagValues.put(main_704_EquipChipBag, "装备碎片袋");
		bagValues.put(main_707_FashBag, "时装袋");

	}

	public final static short getBagValue(String bagName) {
		if (bagName == null) {
			return main_799_HideBag;
		}
		Short bag = bagNames.get(bagName);
		if (bag == null) {
			return main_799_HideBag;
		}
		return bag;
	}

	public final static String getBagName(short bag) {
		String bagName = bagValues.get(bag);
		if (bagName == null) {
			return bagValues.get(bag);
		}
		return bagName;
	}
}
