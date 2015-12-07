package mmo.common.module.clazz.charge.callback;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.bean.role.ChargeRecord;
import mmo.common.charge.ChargeState;
import mmo.common.config.charge.ChargeType;
import mmo.common.module.clazz.charge.callback.run.PushBIEventRun;
import mmo.common.module.service.charge.OrderformManager;
import mmo.common.module.service.charge.bean.ChargeOrderform;
import mmo.common.module.service.charge.service.OrderformService;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.common.module.service.charge.thread.run.AddChargeRun;
import mmo.extension.application.ApplicationConfig;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;

public abstract class AChargeContextHandle extends AContextHandle {
	protected final static int RESULT_1_ORDER_OK = 1;
	protected final static int RESULT_2_ORDER_NOT_EXIST = 2;
	protected final static int RESULT_3_USER_NOT_MATCH = 3;
	protected final static int RESULT_4_ORDER_MATCH = 4;
	protected final static int RESULT_5_AMOUNT = 5;

	/**
	 * 验证渠道传过来的参数
	 * 
	 * @param request
	 * @return NULL代表验证通过，否则验证失败，把该消息返回给渠道
	 */
	abstract protected HttpResponseMessage checkParameters(HttpRequestMessage request);

	/**
	 * 
	 * @param orderCustom
	 *            自有服务器生成的订单
	 * @param cents
	 *            充值金额（分）
	 * @param chargeType
	 *            充值类型（1：成功，2：GM补点，3：充值失败）
	 * @param note
	 *            充值描述（例如：玩家充值，GM补点）
	 * @param channelOrder
	 *            渠道生成的订单
	 * @param proxy
	 *            代理渠道
	 * @param proxyChannel
	 *            真实渠道
	 * @param proxyTime
	 *            渠道记录时间(毫秒值)
	 * @param userId
	 *            用户ID
	 * @param goodsId
	 *            道具编号
	 * @param goodsName
	 *            道具名称
	 * @param goodsCount
	 *            道具数量
	 * @return
	 */
	protected int handleOrder(String orderCustom, int cents, byte chargeType, String note, String channelOrder, String proxy, String proxyChannel, long proxyTime, String userId) {
		ChargeOrderform orderform = OrderformManager.getInstance().getOrderform(orderCustom);
		if (orderform == null) {
			return RESULT_2_ORDER_NOT_EXIST;
		}

		if (orderform.isHadled() && OrderformService.getInstance().validateOrderform(orderform.getOrderform())) {
			return RESULT_1_ORDER_OK;
		}
		if (userId != null && !orderform.getUserId().startsWith(userId)) {
			return RESULT_3_USER_NOT_MATCH;
		}
		if (channelOrder == null || channelOrder.equals("") || channelOrder.equalsIgnoreCase("null")) {
			channelOrder = orderCustom;
		}

		OrderformManager.getInstance().hadleOrderform(orderform);

		ChargeRecord cr = new ChargeRecord();
		cr.setOrderId(orderform.getOrderform());
		cr.setGameId(orderform.getGameId());
		cr.setServerId(orderform.getServerId());
		cr.setChannelId(orderform.getChannelId());
		cr.setAccountId(orderform.getAccountId());
		cr.setRoleId(orderform.getRoleId());
		cr.setRolename(orderform.getRoleName());
		cr.setMoney(cents);
		if (cents < orderform.getItemPrice()) {
			cr.setCtype(ChargeType.TYPE_4_ERROR);
		} else {
			cr.setCtype(chargeType);
		}

		cr.setState(ChargeState.GAME_SERVER_UNADD);
		cr.setNote(note);
		cr.setAtime(new Timestamp(System.currentTimeMillis()));
		cr.setOrderform(channelOrder);
		cr.setProxy(proxy);
		cr.setProxyChannel(proxyChannel);
		cr.setProxyTime(new Timestamp(proxyTime));
		cr.setUserid(orderform.getUserId());
		cr.setChannelSub(orderform.getChannelSub());
		cr.setRoleLevel(orderform.getRoleLevel());
		cr.setGoodsId(orderform.getItemId());
		cr.setGoodsName(orderform.getItemName());
		cr.setGoodsCount(orderform.getItemCount());
		cr.setDeviceOS(orderform.getDeviceOS());
		cr.setDeviceImei(orderform.getDeviceImei());
		cr.setPrice(orderform.getItemPrice());
		cr.setIdfa(orderform.getIdfa());
		ChargeDatabaseHeartbeat.getInstance().execute(new AddChargeRun(cr));
		return RESULT_1_ORDER_OK;
	}

	public void printParameter(HttpRequestMessage request) {
		List<String> names = request.getParameterNames();
		HashMap<String, String> params = new HashMap<String, String>();
		for (String k : names) {
			params.put(k, request.getParameter(k));
			LoggerError.warn("k=" + k + ", v=" + request.getParameter(k));
		}
	}
}
