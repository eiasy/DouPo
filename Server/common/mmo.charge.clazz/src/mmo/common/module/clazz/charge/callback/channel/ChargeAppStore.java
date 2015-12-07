package mmo.common.module.clazz.charge.callback.channel;

import java.sql.Timestamp;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeState;
import mmo.common.config.StringLib;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.service.OrderformService;
import mmo.common.module.service.charge.service.ServiceCharge;
import mmo.common.module.service.charge.thread.IChargeDatabase;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.log.LoggerError;

public class ChargeAppStore implements IChargeDatabase {
	private final String PROXY = "AppStore";
	private final String CHANNEL = "AppStore";
	private ChargeOrderform orderform;
	private String channelOrder;
	private String chargeType;

	public ChargeAppStore() {
	}

	public void setOrderform(ChargeOrderform orderform, String channelOrder) {
		this.orderform = orderform;
		this.channelOrder = channelOrder;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	/**
	 * 向客户端应答结果
	 * 
	 * @param response
	 * @param content
	 */
	public final HttpResponseMessage sendToClient(String content) {
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain;charset=utf-8");
		response.appendBody(content);
		return response;
	}

	@Override
	public void run() {
		if (orderform == null) {
			return;
		}
		if (OrderformService.getInstance().validateOrderform(orderform.getOrderform())) {
			return;
		}

		OrderformManager.getInstance().hadleAppStoreOrderform(orderform);

		ChargeRecord cr = new ChargeRecord();
		cr.setOrderId(orderform.getOrderform());
		cr.setGameId(orderform.getGameId());
		cr.setServerId(orderform.getServerId());
		cr.setChannelId(orderform.getChannelId());
		cr.setAccountId(orderform.getAccountId());
		cr.setRoleId(orderform.getRoleId());
		cr.setRolename(orderform.getRoleName());
		cr.setMoney(orderform.getItemPrice() * orderform.getItemCount());
		cr.setCtype(ChargeType.TYPE_ROLE_CHARGE_SUCC);
		cr.setState(ChargeState.GAME_SERVER_UNADD);
		cr.setNote(chargeType);
		cr.setAtime(new Timestamp(System.currentTimeMillis()));
		cr.setOrderform(channelOrder);
		cr.setProxy(PROXY);
		cr.setProxyChannel(CHANNEL);
		cr.setProxyTime(new Timestamp(System.currentTimeMillis()));
		cr.setUserid(orderform.getUserId());
		cr.setChannelSub(orderform.getChannelSub());
		cr.setRoleLevel(orderform.getRoleLevel());
		cr.setGoodsId(orderform.getItemId());
		cr.setGoodsName(orderform.getItemName());
		cr.setGoodsCount(orderform.getItemCount());
		cr.setDeviceOS(orderform.getDeviceOS());
		cr.setDeviceImei(orderform.getDeviceImei());
		cr.setPrice(orderform.getItemPrice());
		cr.setDeviceSerial(orderform.getDeviceSerial());
		cr.setDeviceMac(orderform.getDeviceMac());
		cr.setIdfa(orderform.getIdfa());
		try {
			if (ServiceCharge.getInstance().addChargeRecord(cr)) {
				ThreadManager.newCharge(cr);
				OrderformManager.getInstance().addChargeRecord(cr);
			}
		} catch (Exception e) {
			LoggerError.error("存储充值记录报错", e);
		}
		LoggerCharge.chargeResult(cr, 0, 0, ";");
	}
}
