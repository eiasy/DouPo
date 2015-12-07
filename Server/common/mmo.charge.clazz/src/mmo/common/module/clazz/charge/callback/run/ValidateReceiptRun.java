package mmo.common.module.clazz.charge.callback.run;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.module.service.charge.appstore.AppStoreGoods;
import mmo.common.module.service.charge.appstore.AppStoreShopManager;
import mmo.common.module.service.charge.http.HttpHandlerLogin;
import mmo.common.module.service.charge.thread.AppStoreHeartbeat;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.common.module.service.charge.thread.run.IValidateReceipt;
import mmo.common.module.service.charge.thread.run.UpdateStoreReceiptDBRun;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.db.DBConnection;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ValidateReceiptRun implements IValidateReceipt {
	private final static String APP_STORE_TEST = "app_store_test";
	private final static String APP_STORE_BUY = "app_store_buy";

	private RoleStoreReceipt receipt;
	private int requestCount = 0;

	public ValidateReceiptRun() {
	}

	@Override
	public void run() {
		if (!receipt.getProid().startsWith("com.y2game.dpcq")) {
			updateReceiptStatus(409);
			LoggerError.error(409 + receipt.getProid());
			return;
		}
		String address = null;
		boolean isTest = true;
		if ("test".equalsIgnoreCase(ProjectCofigs.getParameter("app_store_mode"))) {
			address = ProjectCofigs.getParameter(APP_STORE_TEST);
			isTest = true;
		} else {
			address = ProjectCofigs.getParameter(APP_STORE_BUY);
			isTest = false;
		}
		if (address == null) {
			LoggerError.error("未设置APP_STORE购买验证地址");
		} else {
			validateReceipt(address, isTest);
		}
	}

	public int validateReceipt(String urlString, boolean test) {
		if (requestCount++ > 2) {
			return 10400;
		}
		int status = -1;
		String str = "";
		String chargeType = "charge@";
		if (test) {
			chargeType = "test@";
			// receipt.setChargeType("test");
			// } else {
			// receipt.setChargeType("charge");
		}
		try {
			String rep = String.format(Locale.CHINA, "{\"receipt-data\":\"" + receipt.getReceipt() + "\"}");
			str = HttpsUtil.request(urlString, rep.getBytes());
			receipt.setCheckResult(str);
			JSONObject result = JSONObject.fromObject(str);
			LoggerError.warn(receipt.getRoleName() + " receipt result=" + result);
			status = result.getInt("status");
			switch (status) {
				case 0: {
					if (result.containsKey("product_id") && receipt.getProid().equals(result.getString("product_id"))) {
						AppStoreGoods goods = AppStoreShopManager.getInstance().getAppStoreGoods(receipt.getProid());
						if (goods != null) {
							receipt.setGoodsId(goods.getGoodsId());
							receipt.setGoodsName(goods.getGoodsName());
							receipt.setPrice(goods.getPrice());
							receipt.setCount(1);
						}
						receipt.setStatus(RoleStoreReceipt.STAUS_1_UNCHARGE);
						ChargeDatabaseHeartbeat.getInstance().execute(new UpdateStoreReceiptDBRun(receipt));
						NewAppStoreReceiptRun run = (NewAppStoreReceiptRun) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.run.NewAppStoreReceiptRun").newInstance();
						run.setReceipt(receipt);
						ThreadManager.pushAppOrder(run);
					} else if (result.containsKey("receipt")) {
						boolean isFind = false;
						JSONObject jsonReceipt = result.getJSONObject("receipt");
						if (jsonReceipt != null && jsonReceipt.containsKey("in_app")) {
							JSONArray in_app = jsonReceipt.getJSONArray("in_app");
							if (in_app != null) {
								for (int ai = 0; ai < in_app.size(); ai++) {
									JSONObject jsonIa = in_app.getJSONObject(ai);
									if (jsonIa != null && receipt.getProid().equals(jsonIa.getString("product_id")) && !isTransactionIdExist(chargeType + jsonIa.getString("transaction_id"))) {
										AppStoreGoods goods = AppStoreShopManager.getInstance().getAppStoreGoods(receipt.getProid());
										if (goods != null) {
											isFind = true;
											receipt.setGoodsId(goods.getGoodsId());
											receipt.setGoodsName(goods.getGoodsName());
											receipt.setPrice(goods.getPrice());
											receipt.setCount(1);
											receipt.setStatus(RoleStoreReceipt.STAUS_1_UNCHARGE);
											receipt.setChargeType(chargeType + jsonIa.getString("transaction_id"));
											LoggerError.warn("chargeType:"+chargeType + jsonIa.getString("transaction_id"));
											ChargeDatabaseHeartbeat.getInstance().execute(new UpdateStoreReceiptDBRun(receipt));

											NewAppStoreReceiptRun run = (NewAppStoreReceiptRun) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.run.NewAppStoreReceiptRun").newInstance();
											run.setReceipt(receipt);
											ThreadManager.pushAppOrder(run);
											break;
										}
									}
								}
							}
						}
						if (!isFind) {
							updateReceiptStatus(400);
						}
					} else {
						updateReceiptStatus(401);
						return 401;
					}
					break;
				}
				case 21000: {
					LoggerError.error(status + ":App Store不能读取你提供的JSON对象");
					updateReceiptStatus(status);
					break;
				}
				case 21002: {
					LoggerError.error(status + ":receipt-data域的数据有问题");
					updateReceiptStatus(status);
					break;
				}
				case 21003: {
					LoggerError.error(status + ":receipt无法通过验证");
					updateReceiptStatus(status);
					break;
				}
				case 21004: {
					LoggerError.error(status + ":提供的shared secret不匹配你账号中的shared secret");
					updateReceiptStatus(status);
					break;
				}
				case 21005: {
					LoggerError.error(status + ":receipt服务器当前不可用");
					updateReceiptStatus(receipt.getStatus());
					AppStoreHeartbeat.getInstance().execute(new ReceiptEnterQueueRne(receipt));
					break;
				}
				case 21006: {
					updateReceiptStatus(status);
					LoggerError.error(status + ":receipt合法，但是订阅已过期。服务器接收到这个状态码时，receipt数据仍然会解码并一起发送");
					break;
				}
				case 21007: {
					LoggerError.error(status + ":receipt是Sandbox receipt，但却发送至生产系统的验证服务");
					String address = ProjectCofigs.getParameter(APP_STORE_TEST);
					if (address == null) {
						LoggerError.error("未设置APP_STORE测试验证地址");
					} else {
						status = validateReceipt(address, true);
					}
					break;
				}
				case 21008: {
					LoggerError.error(status + ":receipt是生产receipt，但却发送至Sandbox环境的验证服务");
					String address = ProjectCofigs.getParameter(APP_STORE_BUY);
					if (address == null) {
						LoggerError.error("未设置APP_STORE测试验证地址");
					} else {
						status = validateReceipt(address, false);
					}
					break;
				}
				default: {
					updateReceiptStatus(status);
					LoggerError.error(status + ":App Store 票据未知状态");
				}
			}
		} catch (Exception e) {
			updateReceiptStatus(receipt.getStatus());
			AppStoreHeartbeat.getInstance().execute(new ReceiptEnterQueueRne(receipt));
			LoggerError.error("验证AppStore票据异常", e);
		}
		return status;
	}

	private void updateReceiptStatus(int status) {
		AppStoreGoods goods = AppStoreShopManager.getInstance().getAppStoreGoods(receipt.getProid());
		if (goods != null) {
			receipt.setGoodsId(goods.getGoodsId());
			receipt.setGoodsName(goods.getGoodsName());
			receipt.setPrice(goods.getPrice());
		}
		receipt.setCount(1);
		receipt.setStatus(status);
		ChargeDatabaseHeartbeat.getInstance().execute(new UpdateStoreReceiptDBRun(receipt));
	}

	@Override
	public void setReceipt(RoleStoreReceipt receipt) {
		this.receipt = receipt;
	}

	static String sqlExist = "select id from zone_0_store_receipt where chargeType=? limit 0,1";

	public static boolean isTransactionIdExist(String transaction_id) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sqlExist);
			stmt.setString(1, transaction_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				if (id > 0) {
					return true;
				}
			}
			try {
				stmt.close();
			} catch (Exception e) {
				LoggerError.error("关闭stmt时异常", e);
			}

			return false;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			LoggerError.error("添加AppStore票据异常", e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
		return false;
	}
}
