package mmo.common.message;

public interface ErrorCode {
	String loginPwdErr           = "登录失败，账号和密码错误！[1001]";
	String relive                = "角色生命状态异常！[1002]";

	String missionRepeat         = "操作失败，重复接受任务！[2001]";
	String missionNo             = "操作失败，任务不存在！[2002]";

	String shopNoGoods           = "上架失败，道具不存在！[2051]";
	String shopPrice             = "上架失败，价格不能小于1！[2052]";
	String shopCount             = "上架失败，数量必须大于1！[2053]";
	String shopCountMore         = "上架失败，背包数量不足！[2054]";
	String shopNoGrid            = "上架失败，摊位不足！[2055]";
	String shopFail              = "上架失败！[2056]";
	String shopOutGoods          = "下架失败，货摊上没有该物品！[2057]";
	String shopOutFull           = "下架失败，背包栏已满！[2058]";
	String shopRolePriceGroup    = "该物品没有价格，不能购买！[2059]";
	String shopRolePriceItem     = "购买失败，物品价格出错！[2060]";
	
	String shopStateErr          = "切换摆摊状态指令有误！[2064]";
	String shopSellOut         	 = "该物品已经售空！";
	String shopNpcNo             = "商人不存在！[2065]";
	String shopNpcNoGoods        = "购买物品出错！[2066]";
	String shopBinded            = "上架失败，物品已绑定！[2067]";

	String autoShopNoGoods       = "购买失败，系统正在补充物品！[3001]";

	String sectNotJoin           = "操作失败，未加入宗门！[5001]";
	String sectNoPower           = "操作失败，权限不够！[5002]";
	String sectOffline           = "招收失败，对方已离线！[5003]";
	String sectNoExist           = "加入失败，宗门不存在或已经被解散！[5004]";
	String sectKeyNull           = "加入失败，宗门不存在或已经被解散！[5005]";
	String sectJoined            = "招收失败，对方已经加入宗门！[5006]";
	String sectSelf              = "操作失败，不能挑战自己！[5007]";
	String sectBattleErro        = "操作失败，未知宗战状态！[5008]";

	String tradeBuyLingshi       = "购买失败，灵石不足！[6001]";
	String tradePriceLingshi     = "操作失败，价格有误！[6002]";

	String goodsDeleteNull       = "移除失败，找不到道具！[6501]";
	String goodsDeleteMore       = "移除失败，背包数量不足！[6502]";
	String goodsUseNo            = "使用道具失败，物品不存在！[6503]";
	String goodsUseHpFull        = "使用道具失败，气血已满！[6504]";

	// String goodsUseCool = "使用道具失败，冷却未结束！[6508]";
	String goodsEquipNo          = "装备失败，物品不存在！[6509]";
	String goodsEquipProfession  = "装备失败，职业不符！[6510]";
	String goodsEquipLevel       = "装备失败，等级未达到要求！[6511]";

	String stengthenNotFound     = "强化失败，背包中没有该道具！[6701]";
	String stengthenBag          = "强化失败，只能强化背包内的道具！[6702]";
	String stengthenFull         = "强化失败，道具已经满级！[6703]";
	String stengthenNotFondStone = "强化失败，找不到精炼石！[6704]";
	String stengthenNoItem       = "强化失败，选择的消耗品有误！[6705]";
	String stengthenBindLingshi  = "强化失败，绑灵不够用！[6706]";
	String stengthenLingshi      = "强化失败，灵石不够用！[6707]";
	String stengthenMoreStone    = "强化失败，精炼石不够用！[6708]";
	String noStone               = "请放入精炼石";

	String storeNotExist         = "打开仓库失败，仓库不存在！[7001]";
	String storeEnterGoods       = "存入仓库失败，物品不存在！[7002]";
	String storeEnterNoStore     = "存入仓库失败，仓库不存在！[7003]";
	String storeEnterClosed      = "存入仓库失败，仓库未开启！[7004]";
	String storeEnterFull        = "存入仓库失败，仓库已满！[7005]";
	String storeOutGoods         = "提取失败，仓库里没有该该物品！[7006]";
	String storeOutFull          = "提取失败，背包栏已满！[7007]";

	String addStoneNoGoods       = "镶嵌失败，物品不存在！[7501]";

	String addStoneIndex         = "镶嵌失败，请选择凹槽！[7502]";

	String addStoneNoIndex       = "镶嵌失败，选择的凹槽有误！[7503]";

	String addStoneOpen          = "镶嵌失败，凹槽未开启！[7504]";

	String addStoneNoStone       = "镶嵌失败，未找到宝石！[7505]";

	String addStoneErrStone      = "镶嵌失败，请选择宝石！[7506]";

	String addStoneCount         = "镶嵌失败，宝石数量不足！[7507]";

	String pathActivityNo        = "寻路失败，路径不可达！[8001]";
	String pathActivityTo        = "寻路失败，没有目标！[8002]";
	String pathActivityTime      = "寻路失败，活动不存在或活动已经过期！[8003]";
	String pathMissionNo         = "寻路失败，路径不可达！[8004]";

	String awardActivityReceived = "领取奖励失败，每天只能领取一次活动奖励！[8202]";
	String awardActivityNo       = "领取奖励失败，活动目标不可达，劳驾你把该问题提交给GM，谢谢你的支持！[8203]";
	String awardTargetNo         = "领取奖励失败，目标不可达，劳驾你把该问题提交给GM，谢谢你的支持！[8204]";

	String skillUpgradeNo        = "升级失败，找不到目标对象！[8401]";

	String skillUpgradeMiji      = "升级失败，秘籍与技能不匹配！[8402]";

	String duplicateNotExist     = "打开副本界面失败，副本不存在！[8501]";

	String duplicateJoin         = "同意加入副本异常！[8502]";
	String ERR_SECT_JOIN         = "加入宗门失败！[8503]";
}
