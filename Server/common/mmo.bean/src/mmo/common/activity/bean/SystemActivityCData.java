package mmo.common.activity.bean;

public interface SystemActivityCData {
	String CDATA            = "cdata";
	String HADSEL           = "handsel";
	String DOUBLE           = "double";
	String PERCENT          = "percent";
	String DAY_COUNT        = "dayCount";
	String TOTAL_COUNT      = "totalCount";
	String FREE             = "free";
	String VIP_SUB          = "vipSub";
	String VIP_SUP          = "vipSup";
	String SHOP_GOODS       = "shopGoods";
	String YUAN_BAO         = "yuanbao";
	String START_HOUR       = "startHour";
	String STOP_HOUR        = "stopHour";
	String YUAN_BAO_AMOUNTS = "amounts";
	String COUNT            = "count";
	String COST_YUAN_BAO    = "costYuanbao";
	String PROFESSION       = "profession";
	String ORDER_LIMIT      = "orderLimit";
	String buyerCount       = "buyerCount";

	String GOODS_ID         = "goodsId";
	String GOODS_NAME       = "goodsName";
	String WEIGHT           = "weight";
	String COUNT_SUB        = "countSub";
	String COUNT_SUP        = "countSup";
	String AWARD            = "award";
	String PRICE_GOODS      = "priceGoods";
	String ONCE_BUY         = "onceBuy";
	String PRICE_NAME       = "priceName";
	String PRICE            = "price";
	String PRICE_NOW        = "priceNow";
	String DAY_LIMIT        = "dayLimit";
	String TOTAL_LIMIT      = "totalLimit";
	String PERIOD           = "period";
	String AMOUNT           = "amount";
	String EXCHANGE         = "exchange";
	String RESET            = "reset";
	String TITLE            = "title";

	interface PickPet {
		/** 冷却时间 */
		String COOL_TIME   = "coolTime";
		/** 免费抽获得的积分 */
		String FREE_INT    = "freeInt";
		/** 元宝抽获得的积分 */
		String YUANBAO_INT = "yuanbaoInt";
		/** 达到该积分获得一次元宝抽 */
		String TARGET_INT  = "targetInt";
		/** 达到该积分，活动结束可以领取奖励 */
		String AWARD_INT   = "awardInt";
		/** 奖励说明 */
		String DESC        = "desc";

		/** 积分奖励 */
		String INT_AWARD   = "intAward";
		/** 名次范围获得的奖励 */
		String ORDERS      = "orders";

	}
}
