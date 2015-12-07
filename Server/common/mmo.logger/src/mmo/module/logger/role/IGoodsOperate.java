package mmo.module.logger.role;

public interface IGoodsOperate {

	/**
	 * 商店
	 */
	interface Shop {
		/** 系统商店 */
		String TYPE          = "systemShop";
		/** 购买 */
		String ITEM_AUTO_BUY = "autobuy";
		String ITEM_SHOP_BUY = "shopbuy";
		/** 卖出 */
		String ITEM_SELL     = "sell";
	}

	/**
	 * 邮件
	 */
	interface Email {
		/** 邮件 */
		String TYPE        = "email";
		/** 提取 */
		String ITEM_PICKUP = "pickup";
		/** 发送 */
		String ITEM_SEND   = "send";
	}

	/**
	 * 奖励
	 */
	interface Award {
		/** 奖励 */
		String TYPE               = "award";
		/** 任务奖励 */
		String ITEM_MISSION       = "mission";
		/** 打怪奖励 */
		String ITEM_MONSTER       = "monster";
		/** 充值奖励 */
		String ITEM_CHARGE        = "charge";
		/** 首充奖励 */
		String ITEM_CHARGEFIRST   = "chargeFirst";
		/** 每日登录奖励 */
		String ITEM_LOGIN         = "loginAward";
		/** 活动奖励 */
		String ITEM_ACTIVITY      = "activity";
		/** 副本翻牌奖励 */
		String ITEM_OPENCARD      = "openCard";
		/** 秘境抽奖 */
		String ITEM_SECRETLOTTERY = "secretLottery";
		/** 系统补偿 */
		String ITME_RECOUP        = "recoup";
	}

	/**
	 * 兑换
	 */
	interface Exchange {
		/** 兑换 */
		String TYPE         = "exchange";
		/** 入驻公会兑换 */
		String ITEM_GONGHUI = "gongHui";
		/** 神秘礼包兑换 */
		String ITEM_SECRET  = "secretBag";
	}

	/**
	 * 摆摊
	 */
	interface Storage {
		/** 摆摊 */
		String TYPE      = "storage";
		/** 购买 */
		String ITEM_BUY  = "buy";
		/** 卖出 */
		String ITEM_SELL = "sell";
	}

	/**
	 * 装备操作
	 */
	interface Equipment {
		/** 装备 */
		String TYPE             = "equipment";
		/** 装备装备 */
		String ITEM_EQUIP       = "equip";
		/** 强化 */
		String ITEM_STRENGTHEN  = "strengthen";
		/** 摘除 */
		String ITEM_REMOVESTONE = "removeStone";
		/** 镶嵌 */
		String ITEM_ADDSTONE    = "addStone";
		/** 打孔 */
		String ITEM_OPENHOLE    = "openHole";
		/** 鉴定 */
		String ITEM_IDENTIFY    = "identify";
		/** 维修 */
		String ITEM_REPAIR      = "repair";
		/** 合成 */
		String ITEM_COMPOSITE   = "composite";
	}

	/**
	 * 充值
	 */
	interface Charge {
		/** 充值 */
		String TYPE             = "charge";
		/** 玩家充值 */
		String ITEM_ROLECHARGE  = "roleCharge";
		/** 补点 */
		String ITEM_PATCHCHARGE = "patchCharge";
	}

	/**
	 * 物品
	 */
	interface Item {
		/** 物品 */
		String TYPE           = "item";
		/** 合成 */
		String ITEM_COMPOSITE = "composite";
	}

	/**
	 * 消耗
	 */
	interface Cost {
		/** 消耗 */
		String TYPE                = "cost";
		/** 进入秘境 */
		String ITEM_SECRETTRANSFER = "secretTransfer";
		/** 刷新星级 */
		String ITEM_UPDATESTAR     = "updateStar";
		/** 副本导航 */
		String ITEM_NAVIGATEGD     = "navigateGd";
		/** 通过队伍传送 */
		String ITEM_ROLETRANSFER   = "roleTransfer";
		/** 世界地图传送 */
		String ITEM_CHANGEMAP      = "changeMap";
		/** 原地复活 */
		String ITEM_LOCALRELIVE    = "localRelive";
	}
}
