package mmo.common.protocol.game.operate;

import java.util.HashMap;
import java.util.Map;

/**
 * 宗门相关的操作
 * 
 * @author 李天喜
 * 
 */
public class SectOption {
	/** 取消 */
	public final static byte               cancel            = 0;
	/** 创建宗门 */
	public static final byte               createSect        = 1;
	/** 开启领地 */
	public static final byte               openManor         = 2;
	/** 宗门列表 */
	public static final byte               sectList          = 3;
	/** 进入宗门 */
	public static final byte               enterSect         = 5;
	/** 进入敌宗 */
	public static final byte               enterEnemySect    = 6;
	/** 领取神兽 */
	public static final byte               getBeast          = 7;
	/** 对话-升级金库 */
	public static final byte               openBurse         = 8;
	/** 功能-升级金库 */
	public static final byte               burseLevel        = 9;
	/** 成员列表 */
	public static final byte               memberList        = 10;
	/** 升级兽园 */
	public static final byte               openBeastPark     = 11;
	/** 升级府第 */
	public static final byte               openMansion       = 12;
	/** 升级书院 */
	public static final byte               openCollege       = 13;
	/** 升级兵部 */
	public static final byte               openWarMinistry   = 14;
	/** 升级库房 */
	public static final byte               openStoreHouse    = 15;
	/** 升级府第 */
	public static final byte               mansionLevel      = 16;
	/** 升级书院 */
	public static final byte               collegeLevel      = 17;
	/** 升级库房 */
	public static final byte               storeHouseLevel   = 18;
	/** 升级兵部 */
	public static final byte               warMinistryLevel  = 19;
	/** 升级兽园 */
	public static final byte               beastParkLevel    = 20;
	/** 打开增强兵力界面 */
	public static final byte               openStrengthenArm = 21;
	/** 打开招兵买马界面 */
	public static final byte               openBuyArm        = 22;
	/** 购买兵力 */
	public static final byte               buyArm            = 23;
	/** 打开军备研究界面 */
	public static final byte               openExploreEquip  = 24;
	/** 打开军备升级界面 */
	public static final byte               openEquipLevel    = 25;
	/** 军备升级 */
	public static final byte               openUpgradeEquip  = 26;
	/** 购买兵力及数量 */
	public static final byte               buyArmCount       = 27;
	/** 宗门信息 */
	public static final byte               sectInfo          = 28;
	/** 加入宗门 */
	public static final byte               sectJoin          = 29;
	/** 打开退出宗门 */
	public static final byte               openSectQuit      = 30;
	/** 道具信息 */
	public static final byte               sectGoodsInfo     = 31;
	/** 补充宗门道具 */
	public static final byte               sectAddGoods      = 32;
	/** 兑换宗门道具 */
	public static final byte               sectExchangeGoods = 33;
	/** 打开宗门升级 */
	public static final byte               openSectUpgreade  = 34;
	/** 宗门升级 */
	public static final byte               sectUpgrade       = 35;
	/** 退出宗门 */
	public static final byte               sectQuit          = 36;
	/** 发起宗战 */
	public static final byte               sectBattle        = 37;
	/** 册封(转让宗主) */
	public static final byte               sectPosition      = 38;
	/** 踢出宗门 */
	public static final byte               sectKickOut       = 39;
	/** 修改宗旨 */
	public static final byte               sectPurpose       = 40;
	/** 处理发起宗战 */
	public static final byte               sectBattleHandler = 41;
	// /** 应战 */
	// public static final byte sectAcceptBattle = 42;
	// /** 拒绝 */
	// public static final byte sectRefuseBattle = 43;
	/** 提高经验 */
	public static final byte               sectAddExp        = 44;
	/** 提升开心度--停用 */
	public static final byte               sectAddHapp       = 45;
	/** 升级阵法 */
	public static final byte               sectSpellUpgrade  = 46;
	/** 查看领地 */
	public static final byte               sectLookManor     = 47;
	/** 解散 */
	public static final byte               sectDismiss       = 48;
	/** 招收成员 */
	public static final byte               sectInviteRole    = 49;
	/** 请求信息 */
	public static final byte               sectRequestList   = 50;
	/** 发起宗战 */
	public static final byte               sectRequestWar    = 51;
	/** 确认加入 */
	public static final byte               sectConfirmJoin   = 52;
	/** 宗战列表 */
	public static final byte               sectBattleList    = 53;
	/** 宗战奖励 */
	public static final byte               battleAward       = 54;
	/** 查看宗战当前页 */
	public static final byte               battleView        = 55;
	/** 查看宗战上一页 */
	public static final byte               battleBefor       = 56;
	/** 查看宗战下一页 */
	public static final byte               battleNext        = 57;
	/** 退出宗战地图 */
	public static final byte               quitBattle        = 58;
	/** 进入宗门圣地 */
	public static final byte               manorEnter        = 59;
	/** 查看兽园 */
	public static final byte               checkBeastPark    = 61;
	/** 查看库房 */
	public static final byte               checkStoreHouse   = 62;
	/** 同意加入宗门 */
	public static final byte               agreeRequestJion  = 63;
	/** 拒绝加入宗门 */
	public static final byte               refuseRequestJion = 64;
	/** 宗门签到 */
	public static final byte               sectSign          = 65;
	/** 任命副宗主 */
	public static final byte               appointViceHost   = 66;
	/** 解除副宗主职务 */
	public static final byte               relievedViceHost  = 67;
	/** 宗门贡献 */
	public static final byte               contribution      = 68;
	/** 查看宗门当前页 */
	public static final byte               sectView          = 69;
	/** 查看宗门上一页 */
	public static final byte               sectPrevious      = 70;
	/** 查看宗门下一页 */
	public static final byte               sectNext          = 71;

	private final static Map<Byte, String> optNames          = new HashMap<Byte, String>();
	static {
		optNames.put(createSect, "创建宗门");
		optNames.put(openManor, "开启领地");
		optNames.put(sectList, "宗门列表");
		optNames.put(enterSect, "进入本宗");
		optNames.put(enterEnemySect, "进入敌宗");
		optNames.put(getBeast, "领取神兽");
		optNames.put(openBurse, "升级金库");
		// optNames.put(burseLevel, "升级金库");
		optNames.put(memberList, "成员列表");
		optNames.put(openBeastPark, "升级兽园");
		optNames.put(openMansion, "升级府第");
		optNames.put(openCollege, "升级书院");
		optNames.put(openWarMinistry, "升级兵部");
		optNames.put(openStoreHouse, "升级库房");
		// optNames.put(mansionLevel, "升级府第");
		// optNames.put(collegeLevel, "升级书院");
		// optNames.put(storeHouseLevel, "升级库房");
		// optNames.put(warMinistryLevel, "升级兵部");
		// optNames.put(beastParkLevel, "升级兽园");
		optNames.put(openSectQuit, "退出宗门");
		// optNames.put(openSectUpgreade, "升级宗门");
		optNames.put(sectBattleList, "申请宗战");
		optNames.put(battleAward, "宗战奖励");
		optNames.put(manorEnter, "进入宗门圣地");
		optNames.put(checkBeastPark, "查看兽园");
		optNames.put(checkStoreHouse, "查看库房");
	}

	public static final String getOptName(byte opt) {
		return optNames.get(opt);
	}
}