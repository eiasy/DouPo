package mmo.module.logger.charge;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import mmo.common.bean.advertise.IdfaActive;
import mmo.common.bean.advertise.IdfaEvent;
import mmo.common.bean.bi.EventDefault;
import mmo.common.bean.role.ChargeRecord;
import mmo.common.bean.role.QQChargeRecord;
import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.system.GameSystem;
import mmo.module.logger.develop.LoggerDevelop;
import mmo.tools.log.LoggerFilter;
import mmo.tools.util.DateUtil;

import org.apache.log4j.Logger;

public class LoggerCharge {
	/** 充值记录 */
	private static final String chargeResult = "chargeresult";
	/** 订单号 */
	private static final String chargeorder = "orderform";
	/** receipt */
	private static final String receipt = "receipt";

	/** qqcharge */
	private static final String qqcharge = "qqcharge";

	/** receipterror */
	private static final String receiptError = "receipterror";

	/** idfa active */
	private static final String idfaActive = "idfaactive";

	/** idfa event */
	private static final String idfaEvent = "idfaevent";

	/** bi event */
	private static final String biEvent = "bievent";

	protected static final Logger loggerChargeResult = Logger.getLogger(chargeResult);
	protected static final Logger loggerChargeOrder = Logger.getLogger(chargeorder);
	protected static final Logger loggerChargeReceipt = Logger.getLogger(receipt);
	protected static final Logger loggerReceiptError = Logger.getLogger(receiptError);
	protected static final Logger loggerIdfaActive = Logger.getLogger(idfaActive);
	protected static final Logger loggerIdfaEvent = Logger.getLogger(idfaEvent);
	protected static final Logger loggerBIEvent = Logger.getLogger(biEvent);
	protected static final Logger loggerQQCharge = Logger.getLogger(qqcharge);

	public static final void qqcharge(QQChargeRecord cr,boolean signStatus) {
		StringBuilder sb = new StringBuilder();
		sb.append(cr.getResultCode());
		sb.append(LoggerFilter.logDivide).append(cr.getPayChannel());
		sb.append(LoggerFilter.logDivide).append(cr.getPayState());
		sb.append(LoggerFilter.logDivide).append(cr.getProvideState());
		sb.append(LoggerFilter.logDivide).append(cr.getSaveNum());
		sb.append(LoggerFilter.logDivide).append(cr.getExtendInfo());
		sb.append(LoggerFilter.logDivide).append(cr.getOrderId());
		sb.append(LoggerFilter.logDivide).append(cr.getRemoteIp());
		sb.append(LoggerFilter.logDivide).append(cr.getOpenid());
		sb.append(LoggerFilter.logDivide).append(cr.getOpenkey());
		sb.append(LoggerFilter.logDivide).append(cr.getPayToken());
		sb.append(LoggerFilter.logDivide).append(cr.getPf());
		sb.append(LoggerFilter.logDivide).append(cr.getPfkey());
		sb.append(LoggerFilter.logDivide).append(cr.getActionType());
		sb.append(LoggerFilter.logDivide).append(cr.getCdata());
		sb.append(LoggerFilter.logDivide).append(DateUtil.formatDate(new Date(cr.getAddTime())));
		sb.append(LoggerFilter.logDivide).append(DateUtil.formatDate(new Date(cr.getUpdateTime())));
		sb.append(LoggerFilter.logDivide).append(cr.getImei());
		sb.append(LoggerFilter.logDivide).append(cr.getIdfa());
		sb.append(LoggerFilter.logDivide).append(cr.getStatus());
		sb.append(LoggerFilter.logDivide).append(cr.getCheckCount());
		sb.append(LoggerFilter.logDivide).append(cr.getChargeType());
		sb.append(LoggerFilter.logDivide).append(cr.getAppId());
		sb.append(LoggerFilter.logDivide).append(cr.getHandleAppId());
		sb.append(LoggerFilter.logDivide).append(cr.getZoneId());
		sb.append(LoggerFilter.logDivide).append(signStatus);
		loggerQQCharge.info(sb.toString());
	}

	public static final void chargeResult(ChargeRecord cr, int oldChargeTotal, int newChargeTotal, String handsel) {
		StringBuilder sb = new StringBuilder();
		sb.append(cr.getId());
		sb.append(LoggerFilter.logDivide).append(cr.getFriendId());
		sb.append(LoggerFilter.logDivide).append(cr.getOrderId());
		sb.append(LoggerFilter.logDivide).append(cr.getGameId());
		sb.append(LoggerFilter.logDivide).append(cr.getServerId());
		sb.append(LoggerFilter.logDivide).append(cr.getChannelId());
		sb.append(LoggerFilter.logDivide).append(cr.getAccountId());
		sb.append(LoggerFilter.logDivide).append(cr.getRoleId());
		sb.append(LoggerFilter.logDivide).append(cr.getRolename());
		sb.append(LoggerFilter.logDivide).append(cr.getMoney());
		sb.append(LoggerFilter.logDivide).append(cr.getGetmoney());
		sb.append(LoggerFilter.logDivide).append(cr.getCtype());
		sb.append(LoggerFilter.logDivide).append(cr.getNote());
		sb.append(LoggerFilter.logDivide).append(cr.getOrderform());
		sb.append(LoggerFilter.logDivide).append(cr.getProxy());
		sb.append(LoggerFilter.logDivide).append(cr.getProxyChannel());
		sb.append(LoggerFilter.logDivide).append(DateUtil.formatDate(cr.getProxyTime()));
		sb.append(LoggerFilter.logDivide).append(cr.getUserid());
		sb.append(LoggerFilter.logDivide).append(cr.getChannelSub());
		sb.append(LoggerFilter.logDivide).append(cr.getRoleLevel());
		sb.append(LoggerFilter.logDivide).append(cr.getGoodsId());
		sb.append(LoggerFilter.logDivide).append(cr.getGoodsName());
		sb.append(LoggerFilter.logDivide).append(cr.getGoodsCount());
		sb.append(LoggerFilter.logDivide).append(oldChargeTotal);
		sb.append(LoggerFilter.logDivide).append(newChargeTotal);
		sb.append(LoggerFilter.logDivide).append(handsel);
		sb.append(LoggerFilter.logDivide).append(cr.getDeviceOS());
		sb.append(LoggerFilter.logDivide).append(cr.getDeviceImei());
		sb.append(LoggerFilter.logDivide).append(cr.getIdfa());
		loggerChargeResult.info(sb.toString());
	}

	public static final void idfaActive(IdfaActive idfa) {
		StringBuilder sb = new StringBuilder();
		sb.append(idfa.getId());
		sb.append(LoggerFilter.logDivide).append(idfa.getAppId());
		sb.append(LoggerFilter.logDivide).append(idfa.getChannelTag());
		sb.append(LoggerFilter.logDivide).append(idfa.getIdfa());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceMac());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceImei());
		sb.append(LoggerFilter.logDivide).append(idfa.getIp());
		sb.append(LoggerFilter.logDivide).append(idfa.getDesc());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceUdid());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceSerial());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceUA());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceOS());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceOsVersion());
		sb.append(LoggerFilter.logDivide).append(idfa.getChannelSub());
		sb.append(LoggerFilter.logDivide).append(idfa.getMedia());
		sb.append(LoggerFilter.logDivide).append(idfa.getCallback());
		loggerIdfaActive.info(sb.toString());
	}

	public static final void idfaEvent(IdfaEvent idfa) {
		StringBuilder sb = new StringBuilder();
		sb.append(idfa.getAppId());
		sb.append(LoggerFilter.logDivide).append(idfa.getEventType());
		sb.append(LoggerFilter.logDivide).append(idfa.getEventTag());
		sb.append(LoggerFilter.logDivide).append(idfa.getChannelTag());
		sb.append(LoggerFilter.logDivide).append(idfa.getIdfa());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceMac());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceImei());
		sb.append(LoggerFilter.logDivide).append(idfa.getIp());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceUdid());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceSerial());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceUA());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceOS());
		sb.append(LoggerFilter.logDivide).append(idfa.getDeviceOsVersion());
		sb.append(LoggerFilter.logDivide).append(idfa.getPhoneCode());
		sb.append(LoggerFilter.logDivide).append(idfa.getDesc());
		sb.append(LoggerFilter.logDivide).append(idfa.getChannelSub());
		sb.append(LoggerFilter.logDivide).append(idfa.getMedia());
		sb.append(LoggerFilter.logDivide).append(idfa.getCallback());
		Map<String, String> map = idfa.getCustoms();
		Set<String> keys = map.keySet();
		int index = 0;
		for (String k : keys) {
			index++;
			sb.append(LoggerFilter.logDivide).append(k);
			sb.append(LoggerFilter.logDivide).append(map.get(k));
		}
		for (; index < 5; index++) {
			sb.append(LoggerFilter.logDivide);
			sb.append(LoggerFilter.logDivide);
		}
		loggerIdfaEvent.info(sb.toString());
	}

	public static final void biEvent(EventDefault event) {
		StringBuilder sb = new StringBuilder();
		sb.append(event.getEventSource());
		sb.append(LoggerFilter.logDivide).append(event.getEventTag());
		sb.append(LoggerFilter.logDivide).append(event.getAppTag());
		sb.append(LoggerFilter.logDivide).append(event.getPlatform());
		sb.append(LoggerFilter.logDivide).append(event.getServerTag());
		sb.append(LoggerFilter.logDivide).append(event.getChannelTag());
		sb.append(LoggerFilter.logDivide).append(event.getChannelSub());
		sb.append(LoggerFilter.logDivide).append(event.getAccountId());
		sb.append(LoggerFilter.logDivide).append(event.getUserId());
		sb.append(LoggerFilter.logDivide).append(event.getRoleId());
		sb.append(LoggerFilter.logDivide).append(event.getRoleName());
		sb.append(LoggerFilter.logDivide).append(event.getRoleLevel());
		sb.append(LoggerFilter.logDivide).append(event.getVipLevel());
		sb.append(LoggerFilter.logDivide).append(event.getValue1string());
		sb.append(LoggerFilter.logDivide).append(event.getValue2string());
		sb.append(LoggerFilter.logDivide).append(event.getValue3string());
		sb.append(LoggerFilter.logDivide).append(event.getValue4string());
		sb.append(LoggerFilter.logDivide).append(event.getValue5string());
		sb.append(LoggerFilter.logDivide).append(event.getValue6string());
		sb.append(LoggerFilter.logDivide).append(event.getValue7string());
		sb.append(LoggerFilter.logDivide).append(event.getValue8string());
		sb.append(LoggerFilter.logDivide).append(event.getKey1int());
		sb.append(LoggerFilter.logDivide).append(event.getValue1int());
		sb.append(LoggerFilter.logDivide).append(event.getKey2int());
		sb.append(LoggerFilter.logDivide).append(event.getValue2int());
		sb.append(LoggerFilter.logDivide).append(event.getKey3int());
		sb.append(LoggerFilter.logDivide).append(event.getValue3int());
		sb.append(LoggerFilter.logDivide).append(event.getKey1long());
		sb.append(LoggerFilter.logDivide).append(event.getValue1long());
		sb.append(LoggerFilter.logDivide).append(event.getKey1double());
		sb.append(LoggerFilter.logDivide).append(event.getValue1double());

		loggerBIEvent.info(sb.toString());
	}

	public static final void receiptError(String ip, String receipt, String channelId, String channelSub, String userId, int accountId, int roleId, String roleName, int level, int gameId, int serverId, int goodsId, String goodsName, int goodsCount, int price, String imei, String serial, String mac, String os, String idfa, String proid) {
		StringBuilder sb = new StringBuilder();
		sb.append(ip);
		sb.append(LoggerFilter.logDivide).append(receipt);
		sb.append(LoggerFilter.logDivide).append(channelId);
		sb.append(LoggerFilter.logDivide).append(channelSub);
		sb.append(LoggerFilter.logDivide).append(userId);
		sb.append(LoggerFilter.logDivide).append(accountId);
		sb.append(LoggerFilter.logDivide).append(roleId);
		sb.append(LoggerFilter.logDivide).append(roleName);
		sb.append(LoggerFilter.logDivide).append(level);
		sb.append(LoggerFilter.logDivide).append(gameId);
		sb.append(LoggerFilter.logDivide).append(serverId);
		sb.append(LoggerFilter.logDivide).append(goodsId);
		sb.append(LoggerFilter.logDivide).append(goodsName);
		sb.append(LoggerFilter.logDivide).append(goodsCount);
		sb.append(LoggerFilter.logDivide).append(price);
		sb.append(LoggerFilter.logDivide).append(imei);
		sb.append(LoggerFilter.logDivide).append(serial);
		sb.append(LoggerFilter.logDivide).append(mac);
		sb.append(LoggerFilter.logDivide).append(os);
		sb.append(LoggerFilter.logDivide).append(idfa);
		sb.append(LoggerFilter.logDivide).append(proid);
		loggerReceiptError.info(sb.toString());
	}

	public static final void chargeReceipt(RoleStoreReceipt receipt) {
		StringBuilder sb = new StringBuilder();
		sb.append(receipt.getId());
		sb.append(LoggerFilter.logDivide).append(receipt.getReceipt());
		sb.append(LoggerFilter.logDivide).append(receipt.getChannelId());
		sb.append(LoggerFilter.logDivide).append(receipt.getChannelSub());
		sb.append(LoggerFilter.logDivide).append(receipt.getUserId());
		sb.append(LoggerFilter.logDivide).append(receipt.getAccountId());
		sb.append(LoggerFilter.logDivide).append(receipt.getRoleId());
		sb.append(LoggerFilter.logDivide).append(receipt.getRoleName());
		sb.append(LoggerFilter.logDivide).append(receipt.getLevel());
		sb.append(LoggerFilter.logDivide).append(receipt.getGameId());
		sb.append(LoggerFilter.logDivide).append(receipt.getServerId());
		sb.append(LoggerFilter.logDivide).append(receipt.getGoodsId());
		sb.append(LoggerFilter.logDivide).append(receipt.getGoodsName());
		sb.append(LoggerFilter.logDivide).append(receipt.getCount());
		sb.append(LoggerFilter.logDivide).append(receipt.getPrice());
		sb.append(LoggerFilter.logDivide).append(receipt.getDeviceImei());
		sb.append(LoggerFilter.logDivide).append(receipt.getDeviceSerial());
		sb.append(LoggerFilter.logDivide).append(receipt.getDeviceMac());
		sb.append(LoggerFilter.logDivide).append(receipt.getDeviceOS());
		sb.append(LoggerFilter.logDivide).append(receipt.getIdfa());
		sb.append(LoggerFilter.logDivide).append(receipt.getProid());
		loggerChargeReceipt.info(sb.toString());
	}

	public static final void chargeOrder(String result, String info, String orderid) {
		StringBuilder sb = new StringBuilder();
		sb.append(result);
		sb.append(LoggerFilter.logDivide).append(info);
		sb.append(LoggerFilter.logDivide).append(orderid);
		loggerChargeOrder.info(sb.toString());
	}

	static long nextLogTime = stringToMillisecond();

	public static long stringToMillisecond() {
		DateFormat _dateformat = new SimpleDateFormat("yyyy-MM-dd HH");
		String dateString = _dateformat.format(new Date(System.currentTimeMillis()));
		String dateStartStr = dateString + ":10:00";

		Date dateStart = DateUtil.stringToDate(dateStartStr);
		if (dateStart == null) {
			return Long.MAX_VALUE;
		}
		return dateStart.getTime();
	}

	public static final void nullLog() {
		long currTime = System.currentTimeMillis();
		if (currTime > nextLogTime) {
			nextLogTime = nextLogTime + GameSystem.ONE_HOUR;
			loggerChargeResult.info(LoggerDevelop.NULL_LOG);
		}
	}
}
