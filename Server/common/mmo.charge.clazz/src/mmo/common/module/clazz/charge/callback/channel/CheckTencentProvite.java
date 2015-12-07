package mmo.common.module.clazz.charge.callback.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.bean.role.QQChargeRecord;
import mmo.common.module.clazz.charge.callback.run.NewQQChargeRun;
import mmo.common.module.clazz.charge.callback.run.UpdateQQCharge2DBRun;
import mmo.common.module.service.charge.http.HttpHandlerLogin;
import mmo.common.module.service.charge.tencent.ACheckProvited;
import mmo.common.module.service.charge.tencent.QQCheckChargeHearbeat;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.sha.HMACSHA1;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class CheckTencentProvite extends ACheckProvited {

	@Override
	public void run() {
		if (cr == null || cr.getStatus() != QQChargeRecord.STATUS_0_NEW) {
			return;
		}
		
		cr.setCheckCount(cr.getCheckCount() + 1);
		int remain = tencentOrderId();
		if (remain >= cr.getSaveNum()) {
			if (tencentCost()) {
				cr.setStatus(QQChargeRecord.STATUS_1_UNCONFIRM);
				cr.setHandleAppId(ApplicationConfig.getInstance().getAppId());
				cr.setCdata("支付成功-未确认");
				ChargeDatabaseHeartbeat.getInstance().execute(new UpdateQQCharge2DBRun(cr));
				try {
					NewQQChargeRun run = (NewQQChargeRun) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.run.NewQQChargeRun").newInstance();
					run.setQQChargeRecord(cr);
					ThreadManager.pushAppOrder(run);
				} catch (Exception e) {
					LoggerError.error("推送qq订单异常", e);
				}
				return;
			}
		}
		if (cr.isOvertime()) {
			cr.setStatus(QQChargeRecord.STATUS_102_OVERTIME);
			cr.setCdata("超时");
		}else{
			QQCheckChargeHearbeat.getInstance().enterQueue(cr);
		}
		ChargeDatabaseHeartbeat.getInstance().execute(new UpdateQQCharge2DBRun(cr));
	}

	private int tencentOrderId() {
		try {
			if (cr.getActionType().toLowerCase().startsWith("qq")) {
				return tencentQQ();
			} else if (cr.getActionType().toLowerCase().startsWith("wx")) {
				return tencentWX();
			}
		} catch (Exception e) {
			LoggerError.error("腾讯充值-1", e);
		}
		return -1;
	}

	private int tencentQQ() {
		URL url = null;
		HttpURLConnection conn = null;
		try {

			String QQ_CHECK_PROVIDE = null;
			String appKey = null;
			if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("qq_charge_test"))) {
				QQ_CHECK_PROVIDE = ProjectCofigs.getParameter("qq_check_charge_test");
				appKey = ProjectCofigs.getParameter("qq_Appkey_test");
			} else {
				QQ_CHECK_PROVIDE = ProjectCofigs.getParameter("qq_check_charge");
				appKey = ProjectCofigs.getParameter("qq_Appkey");
			}
			String openid = cr.getOpenid();
			String openKey = URLEncoder.encode(cr.getOpenkey(), "UTF-8");
			String payKey = URLEncoder.encode(cr.getPayToken(), "UTF-8");
			String pf = URLEncoder.encode(cr.getPf(), "UTF-8");
			String pfkey = URLEncoder.encode(cr.getPfkey(), "UTF-8");

			long time = System.currentTimeMillis() / 1000;
			String uri = URLEncoder.encode("/mpay/get_balance_m", "UTF-8");
			String method = "GET";

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("openid", openid);
			parameters.put("openkey", openKey);
			parameters.put("pay_token", payKey);
			parameters.put("appid", ProjectCofigs.getParameter("qq_AppId"));
			parameters.put("ts", "" + time);
			parameters.put("pf", pf);
			parameters.put("pfkey", pfkey);
			parameters.put("zoneid", "" + cr.getZoneId());

			List<String> keyList = new ArrayList<String>(parameters.keySet());
			Collections.sort(keyList);
			StringBuilder sb = new StringBuilder();
			for (int li = 0; li < keyList.size(); li++) {
				if (li > 0) {
					sb.append("&");
				}
				sb.append(keyList.get(li)).append("=").append(parameters.get(keyList.get(li)));
			}
			String parameter = sb.toString();

			sb.setLength(0);
			sb.append(method).append("&").append(uri).append("&").append(URLEncoder.encode(parameter, "UTF-8"));

			String sign = new BASE64Encoder().encode(HMACSHA1.getHmacSHA1(sb.toString(), appKey + "&"));
			sb.setLength(0);
			sb.append(QQ_CHECK_PROVIDE).append("?");
			for (int li = 0; li < keyList.size(); li++) {
				if (li > 0) {
					sb.append("&");
				}
				sb.append(keyList.get(li)).append("=").append(URLEncoder.encode(parameters.get(keyList.get(li)), "UTF-8"));
			}

			sb.append("&sig=").append(URLEncoder.encode(sign, "UTF-8"));
			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "close");

			conn.setRequestProperty("Cookie", "session_id=openid;session_type=kp_actoken;org_loc=" + uri);
			conn.setDoInput(true);
			String line = null;
			String result = null;
			BufferedReader bufferedReader;
			InputStreamReader streamReader = null;
			sb.setLength(0);
			try {
				streamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
			} catch (IOException e) {
				streamReader = new InputStreamReader(conn.getErrorStream(), "UTF-8");
			} finally {
				if (streamReader != null) {
					bufferedReader = new BufferedReader(streamReader);
					sb = new StringBuilder();
					while ((line = bufferedReader.readLine()) != null) {
						sb.append(line);
					}
					result = sb.toString();
				}
			}
			LoggerError.warn("qq_check_result=" + result);
			JSONObject jsonResult = JSONObject.fromObject(result);
			if (jsonResult.getInt("ret") == 0) {
				return jsonResult.getInt("balance");
			} else {
				LoggerError.error(jsonResult.getString("msg"));
			}
		} catch (Exception e) {
			LoggerError.error("验证qq到帐异常", e);
		}
		return -1;
	}

	private int tencentWX() {
		URL url = null;
		HttpURLConnection conn = null;
		try {
			String WX_CHECK_PROVIDE = null;
			String appKey = null;
			if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("wx_charge_test"))) {
				WX_CHECK_PROVIDE = ProjectCofigs.getParameter("wx_check_charge_test");
				appKey = ProjectCofigs.getParameter("wx_Appkey_test");
			} else {
				WX_CHECK_PROVIDE = ProjectCofigs.getParameter("wx_check_charge");
				appKey = ProjectCofigs.getParameter("wx_Appkey");
			}
			String openid = cr.getOpenid();
			String accesstoken = URLEncoder.encode(cr.getOpenkey(), "UTF-8");
			String pf = URLEncoder.encode(cr.getPf(), "UTF-8");
			String pfkey = URLEncoder.encode(cr.getPfkey(), "UTF-8");

			long time = System.currentTimeMillis() / 1000;
			String uri = URLEncoder.encode("/mpay/get_balance_m", "UTF-8");
			String method = "GET";

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("openid", openid);
			parameters.put("openkey", accesstoken);
			parameters.put("appid", ProjectCofigs.getParameter("wx_AppId"));
			parameters.put("ts", "" + time);
			parameters.put("pf", pf);
			parameters.put("pfkey", pfkey);
			parameters.put("zoneid", "" + cr.getZoneId());
			parameters.put("format", "json");

			List<String> keyList = new ArrayList<String>(parameters.keySet());
			Collections.sort(keyList);
			StringBuilder sb = new StringBuilder();
			for (int li = 0; li < keyList.size(); li++) {
				if (li > 0) {
					sb.append("&");
				}
				sb.append(keyList.get(li)).append("=").append(parameters.get(keyList.get(li)));
			}
			String parameter = sb.toString();

			sb.setLength(0);
			sb.append(method).append("&").append(uri).append("&").append(URLEncoder.encode(parameter, "UTF-8"));

			String sign = new BASE64Encoder().encode(HMACSHA1.getHmacSHA1(sb.toString(), appKey + "&"));
			sb.setLength(0);
			sb.append(WX_CHECK_PROVIDE).append("?");
			for (int li = 0; li < keyList.size(); li++) {
				if (li > 0) {
					sb.append("&");
				}
				sb.append(keyList.get(li)).append("=").append(URLEncoder.encode(parameters.get(keyList.get(li)), "UTF-8"));
			}

			sb.append("&sig=").append(URLEncoder.encode(sign, "UTF-8"));
			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "close");

			conn.setRequestProperty("Cookie", "session_id=hy_gameid;session_type=wc_actoken;org_loc=" + uri);
			conn.setDoInput(true);
			String line = null;
			String result = null;
			BufferedReader bufferedReader;
			InputStreamReader streamReader = null;
			sb.setLength(0);
			try {
				streamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
			} catch (IOException e) {
				streamReader = new InputStreamReader(conn.getErrorStream(), "UTF-8");
			} finally {
				if (streamReader != null) {
					bufferedReader = new BufferedReader(streamReader);
					sb = new StringBuilder();
					while ((line = bufferedReader.readLine()) != null) {
						sb.append(line);
					}
					result = sb.toString();
				}
			}
			LoggerError.warn("wx_check_result=" + result);
			JSONObject jsonResult = JSONObject.fromObject(result);
			if (jsonResult.getInt("ret") == 0) {
				return jsonResult.getInt("balance");
			} else {
				LoggerError.error(jsonResult.getString("msg"));
			}
		} catch (Exception e) {
			LoggerError.error("验证wx到帐异常", e);
		}
		return -1;
	}

	private boolean tencentCost() {
		try {
			if (cr.getActionType().toLowerCase().startsWith("qq")) {
				return tencentQQCost();
			} else if (cr.getActionType().toLowerCase().startsWith("wx")) {
				return tencentWXCost();
			}
		} catch (Exception e) {
			LoggerError.error("腾讯充值-1", e);
		}
		return false;
	}

	private boolean tencentQQCost() {
		URL url = null;
		HttpURLConnection conn = null;
		try {
			boolean isTest = true;
			String tencentCost = null;
			String appKey = null;
			if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("qq_charge_test"))) {
				tencentCost = ProjectCofigs.getParameter("qq_cost_test");
				appKey = ProjectCofigs.getParameter("qq_Appkey_test");
			} else {
				isTest = false;
				tencentCost = ProjectCofigs.getParameter("qq_cost");
				appKey = ProjectCofigs.getParameter("qq_Appkey");
			}
			String openid = cr.getOpenid();
			String openKey = URLEncoder.encode(cr.getOpenkey(), "UTF-8");
			String payKey = URLEncoder.encode(cr.getPayToken(), "UTF-8");
			String pf = URLEncoder.encode(cr.getPf(), "UTF-8");
			String pfkey = URLEncoder.encode(cr.getPfkey(), "UTF-8");

			long time = System.currentTimeMillis() / 1000;
			String uri = URLEncoder.encode("/mpay/pay_m", "UTF-8");
			String method = "GET";

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("openid", openid);
			parameters.put("openkey", openKey);
			parameters.put("pay_token", payKey);
			parameters.put("appid", ProjectCofigs.getParameter("qq_AppId"));
			parameters.put("ts", "" + time);
			parameters.put("pf", pf);
			parameters.put("zoneid", "" + cr.getZoneId());
			parameters.put("amt", "" + (cr.getSaveNum() * 10));
			parameters.put("pfkey", pfkey);

			List<String> keyList = new ArrayList<String>(parameters.keySet());
			Collections.sort(keyList);
			StringBuilder sb = new StringBuilder();
			for (int li = 0; li < keyList.size(); li++) {
				if (li > 0) {
					sb.append("&");
				}
				sb.append(keyList.get(li)).append("=").append(parameters.get(keyList.get(li)));
			}
			String parameter = sb.toString();

			sb.setLength(0);
			sb.append(method).append("&").append(uri).append("&").append(URLEncoder.encode(parameter, "UTF-8"));

			String sign = new BASE64Encoder().encode(HMACSHA1.getHmacSHA1(sb.toString(), appKey + "&"));
			sb.setLength(0);
			sb.append(tencentCost).append("?");
			for (int li = 0; li < keyList.size(); li++) {
				if (li > 0) {
					sb.append("&");
				}
				sb.append(keyList.get(li)).append("=").append(URLEncoder.encode(parameters.get(keyList.get(li)), "UTF-8"));
			}

			sb.append("&sig=").append(URLEncoder.encode(sign, "UTF-8"));
			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "close");

			conn.setRequestProperty("Cookie", "session_id=openid;session_type=kp_actoken;org_loc=" + uri);
			conn.setDoInput(true);
			String line = null;
			String result = null;
			BufferedReader bufferedReader;
			InputStreamReader streamReader = null;
			sb.setLength(0);
			try {
				streamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
			} catch (IOException e) {
				streamReader = new InputStreamReader(conn.getErrorStream(), "UTF-8");
			} finally {
				if (streamReader != null) {
					bufferedReader = new BufferedReader(streamReader);
					sb = new StringBuilder();
					while ((line = bufferedReader.readLine()) != null) {
						sb.append(line);
					}
					result = sb.toString();
				}
			}
			LoggerError.warn("qq_cost_result=" + result);
			JSONObject jsonResult = JSONObject.fromObject(result);
			if (jsonResult.getInt("ret") == 0) {
				cr.setChannelOrderId(jsonResult.getString("billno"));
				if (isTest) {
					cr.setChargeType("test");
				} else {
					cr.setChargeType("charge");
				}
				return true;
			} else {
				LoggerError.error(jsonResult.getString("msg"));
			}
		} catch (Exception e) {
			LoggerError.error("qq扣除消耗异常", e);
		}
		return false;
	}

	private boolean tencentWXCost() {
		URL url = null;
		HttpURLConnection conn = null;
		try {
			String tencentCost = null;
			String appKey = null;
			boolean isTest = true;
			if ("true".equalsIgnoreCase(ProjectCofigs.getParameter("wx_charge_test"))) {
				tencentCost = ProjectCofigs.getParameter("wx_cost_test");
				appKey = ProjectCofigs.getParameter("wx_Appkey_test");
			} else {
				isTest = false;
				tencentCost = ProjectCofigs.getParameter("wx_cost");
				appKey = ProjectCofigs.getParameter("wx_Appkey");
			}
			String openid = cr.getOpenid();
			String openKey = URLEncoder.encode(cr.getOpenkey(), "UTF-8");
			String pf = URLEncoder.encode(cr.getPf(), "UTF-8");
			String pfkey = URLEncoder.encode(cr.getPfkey(), "UTF-8");

			long time = System.currentTimeMillis() / 1000;
			String uri = URLEncoder.encode("/mpay/pay_m", "UTF-8");
			String method = "GET";

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("openid", openid);
			parameters.put("openkey", openKey);
			parameters.put("appid", ProjectCofigs.getParameter("wx_AppId"));
			parameters.put("ts", "" + time);
			parameters.put("pf", pf);
			parameters.put("zoneid", "" + cr.getZoneId());
			parameters.put("amt", "" + (cr.getSaveNum() * 10));
			parameters.put("pfkey", pfkey);

			List<String> keyList = new ArrayList<String>(parameters.keySet());
			Collections.sort(keyList);
			StringBuilder sb = new StringBuilder();
			for (int li = 0; li < keyList.size(); li++) {
				if (li > 0) {
					sb.append("&");
				}
				sb.append(keyList.get(li)).append("=").append(parameters.get(keyList.get(li)));
			}
			String parameter = sb.toString();

			sb.setLength(0);
			sb.append(method).append("&").append(uri).append("&").append(URLEncoder.encode(parameter, "UTF-8"));

			String sign = new BASE64Encoder().encode(HMACSHA1.getHmacSHA1(sb.toString(), appKey + "&"));
			sb.setLength(0);
			sb.append(tencentCost).append("?");
			for (int li = 0; li < keyList.size(); li++) {
				if (li > 0) {
					sb.append("&");
				}
				sb.append(keyList.get(li)).append("=").append(URLEncoder.encode(parameters.get(keyList.get(li)), "UTF-8"));
			}

			sb.append("&sig=").append(URLEncoder.encode(sign, "UTF-8"));
			url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "close");

			conn.setRequestProperty("Cookie", "session_id=hy_gameid;session_type=wc_actoken;org_loc=" + uri);
			conn.setDoInput(true);
			String line = null;
			String result = null;
			BufferedReader bufferedReader;
			InputStreamReader streamReader = null;
			sb.setLength(0);
			try {
				streamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
			} catch (IOException e) {
				streamReader = new InputStreamReader(conn.getErrorStream(), "UTF-8");
			} finally {
				if (streamReader != null) {
					bufferedReader = new BufferedReader(streamReader);
					sb = new StringBuilder();
					while ((line = bufferedReader.readLine()) != null) {
						sb.append(line);
					}
					result = sb.toString();
				}
			}
			LoggerError.warn("wx_cost_result=" + result);
			JSONObject jsonResult = JSONObject.fromObject(result);
			if (jsonResult.getInt("ret") == 0) {
				cr.setChannelOrderId(jsonResult.getString("billno"));
				if (isTest) {
					cr.setChargeType("test");
				} else {
					cr.setChargeType("charge");
				}
				return true;
			} else {
				LoggerError.error(jsonResult.getString("msg"));
			}
		} catch (Exception e) {
			LoggerError.error("wx扣除消耗异常", e);
		}
		return false;
	}
}
