package mmo.module.bi;

import mmo.common.bean.role.Role;
import mmo.common.bean.role.RoleManager;
import mmo.common.bean.role.RoleMini;
import mmo.common.bi.BIManager;
import mmo.common.config.ClientCustom;
import mmo.common.protocol.game.UserProtocol;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.net.extension.session.NetRole;

import org.apache.mina.core.buffer.IoBuffer;

public class BIInterface {
	public static interface EventNames {
		String costLingshi     = "灵石消耗";

		String buyLingshi      = "灵石购买";

		String outLingshi      = "灵石产出";
		String pickCommon      = "普通召唤";
		String pickCommonFree  = "普通免费";
		String pickCommonTen   = "普通十次";
		String pickHigh        = "高级召唤";
		String pickHighTen     = "高级十次";
		String timeCommonFree  = "限时普通免费";
		String timeCommon      = "限时普通召唤";
		String timeYuanbao     = "限时元宝召唤";
		String timeYuanbaoFree = "限时元宝免费";
	}

	private final static byte   createAccount    = 1;
	private final static byte   accountLogin     = 2;
	private final static byte   setGameServer    = 5;
	private final static byte   setLevel         = 6;
	private final static byte   chargeRequest    = 10;
	private final static byte   chargeSuccess    = 11;

	private final static byte   duplicateBegin   = 13;
	private final static byte   duplicateSuccess = 14;
	private final static byte   duplicateFail    = 15;

	private final static byte   onPurchase       = 16;
	private final static byte   onReward         = 18;
	private final static byte   onEvent2         = 25;

	/** 物品名称 */
	private final static String KEY_ITEM         = "item";
	/** 购买或使用数量 */
	private final static String KEY_ITEM_NUMBER  = "itemNumber";
	/** 物品的单价 */
	private final static String KEY_LINGSHI      = "USE_LING_SHI";
	/** 获赠原因 */
	private final static String KEY_REASON       = "reason";

	public final static void chargeRequest(Role role, String orderId, String iapId, double currencyAmount, String currencyType,
	        double virtualCurrencyAmount, String paymentType) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(chargeRequest);
		buf.putString(orderId);
		buf.putString(iapId);
		buf.putDouble(currencyAmount);
		buf.putString(currencyType);
		buf.putDouble(virtualCurrencyAmount);
		buf.putString(paymentType);
		role.sendData(buf);
	}

	public final static void chargeSuccess(Role role, String orderId) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(chargeSuccess);
		buf.putString(orderId);
		role.sendData(buf);
	}

	public final static void accountCreated(NetRole role, int accountId) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(createAccount);
		buf.putString(accountId + "");
		role.sendData(buf);
	}

	public final static void accountLogin(NetRole role) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(accountLogin);
		role.sendData(buf);
	}

	public final static void duplicateBegin(NetRole role, int gateId, String petInfo) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(duplicateBegin);
		buf.putString(gateId + "");
		role.sendData(buf);
		RoleMini rm = RoleManager.getRoleMini(role.getId());
		short level = 0;
		if (rm != null) {
			level = rm.getMansionLevel();
		}
		BIManager.getInstance().roleDuplicateBegin(ApplicationConfig.getInstance().getProductName(), ApplicationConfig.getInstance().getAppName(),
		        role.getAccountId() + "", role.getChannelId() + "", role.getChannelSub(), gateId + "", role.getId(), level, petInfo);
	}

	public final static void duplicateSuccess(NetRole role, int gateId) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(duplicateSuccess);
		buf.putString(gateId + "");
		role.sendData(buf);
		RoleMini rm = RoleManager.getRoleMini(role.getId());
		short level = 0;
		if (rm != null) {
			level = rm.getMansionLevel();
		}
		BIManager.getInstance().roleDuplicatePass(ApplicationConfig.getInstance().getProductName(), ApplicationConfig.getInstance().getAppName(),
		        role.getAccountId() + "", role.getChannelId() + "", role.getChannelSub(), gateId + "", role.getId(), level, "pass");
	}

	public final static void duplicateFail(NetRole role, int gateId, String reason) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(duplicateFail);
		buf.putString(gateId + "");
		buf.putString(reason);
		role.sendData(buf);
		RoleMini rm = RoleManager.getRoleMini(role.getId());
		short level = 0;
		if (rm != null) {
			level = rm.getMansionLevel();
		}
		BIManager.getInstance().roleDuplicateFail(ApplicationConfig.getInstance().getProductName(), ApplicationConfig.getInstance().getAppName(),
		        role.getAccountId() + "", role.getChannelId() + "", role.getChannelSub(), gateId + "", role.getId(), level, reason);
	}

	public final static void switchServer(NetRole role, int serverId) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(setGameServer);
		buf.putString(serverId + "");
		role.sendData(buf);
		BIManager.getInstance().switchServer(ApplicationConfig.getInstance().getProductName(), ApplicationConfig.getInstance().getAppName(),
		        role.getChannelId() + "", role.getDeviceOS(), role.getDeviceSerial());
	}

	public final static void levelUpgrade(NetRole role, int level) {
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(setLevel);
		buf.putInt(level);
		role.sendData(buf);
		BIManager.getInstance().roleUpgrade(ApplicationConfig.getInstance().getProductName(), ApplicationConfig.getInstance().getAppName(),
		        role.getAccountId() + "", role.getChannelId() + "", role.getChannelSub(), role.getId(), level, "");
	}

	public final static void cc_purchase(Role role, String item, int count, int price) {
		if (role.getClientModel() == ClientCustom.ClientModel.V_2_ROBOT || role.getId() < 1) {
			return;
		}
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(onPurchase);
		buf.putString(item);
		buf.putInt(count);
		buf.putDouble(Math.abs(price));
		role.sendData(buf);
		BIManager.getInstance()
		        .roleBuyItem(ApplicationConfig.getInstance().getProductName(), ApplicationConfig.getInstance().getAppName(),
		                role.getAccountId() + "", role.getChannelId() + "", role.getChannelSub(), role.getId(), role.getLevel(), item, count, "元宝",
		                price, "");
	}

	public final static void cc_use(Role role, String item, int count, String reason) {
		if (role.getClientModel() == ClientCustom.ClientModel.V_2_ROBOT || role.getId() < 1) {
			return;
		}
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(onEvent2);
		buf.putString(KEY_LINGSHI);
		buf.putShort((short) 3);

		buf.putString(KEY_ITEM);
		buf.putBoolean(false);
		buf.putString(item);

		buf.putString(KEY_ITEM_NUMBER);
		buf.putBoolean(true);
		buf.putInt(Math.abs(count));

		buf.putString(KEY_REASON);
		buf.putBoolean(false);
		buf.putString(reason);

		buf.putLong(0);
		buf.putBoolean(true);
		role.sendData(buf);

		BIManager.getInstance().roleCostItem(ApplicationConfig.getInstance().getProductName(), ApplicationConfig.getInstance().getAppName(),
		        role.getAccountId() + "", role.getChannelId() + "", role.getChannelSub(), role.getId(), role.getLevel(), item, count, reason);
	}

	public final static void cc_reward(Role role, String item, int count, String reason) {
		if (role.getClientModel() == ClientCustom.ClientModel.V_2_ROBOT || role.getId() < 1) {
			return;
		}
		IoBuffer buf = IoBuffer.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8011_BI_DATA);
		buf.put(onReward);
		buf.putDouble(count);
		buf.putString(reason);
		buf.putString(item);
		role.sendData(buf);
		BIManager.getInstance().roleGetItem(ApplicationConfig.getInstance().getProductName(), ApplicationConfig.getInstance().getAppName(),
		        role.getAccountId() + "", role.getChannelId() + "", role.getChannelSub(), role.getId(), role.getLevel(), item, count, reason);
	}
}
