package mmo.common.module.clazz.charge.callback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.module.clazz.charge.callback.channel.AppStore_229_order;
import mmo.common.module.clazz.charge.callback.channel.Charge91;
import mmo.common.module.clazz.charge.callback.channel.ChargeClassload;
import mmo.common.module.clazz.charge.callback.channel.ChargeConfirm;
import mmo.common.module.clazz.charge.callback.channel.ChargeITools;
import mmo.common.module.clazz.charge.callback.channel.ChargeLoadConfigs;
import mmo.common.module.clazz.charge.callback.channel.ChargePP;
import mmo.common.module.clazz.charge.callback.channel.ChargePatch104;
import mmo.common.module.clazz.charge.callback.channel.ChargeTencent;
import mmo.common.module.clazz.charge.callback.channel.ChargeTongBu;
import mmo.common.module.clazz.charge.callback.channel.ChargeXY;
import mmo.common.module.clazz.charge.callback.channel.LoadServerCharges;
import mmo.common.module.clazz.charge.callback.chukong.ChargeChukong;
import mmo.common.module.service.charge.IChargeSDKCallback;
import mmo.extension.application.ApplicationConfig;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.FileUtil;

import org.apache.mina.core.session.IoSession;

public class ChargeSDKCallback implements IChargeSDKCallback {
	

	private static final String SERVER_CHARGES = "10001";
	/** 请求订单号 */
	private static final String D_10002 = "10002";
	private static final String C_10003 = "10003";
	private static final String E_10004 = "10004";
	private static final String E_10005 = "10005";
	private static final String E_10006 = "10006";
	private static final String E_10007 = "10007";
	private static final String E_10008 = "10008";
	private static final String E_10009 = "10009";
	private static final String E_10010 = "10010";
	private static final String E_10011 = "10011";
	private static final String CHARGE_CLASSLOAD = "charges/classload";
	private static final String LOAD_CONFIGS = "charges/loadconfigs";

	private static final String C_100_PP = "charges/c100";
	private static final String C_101_91 = "charges/c101";
	private static final String C_102_TONG_BU = "charges/c102";
	private static final String C_103_ITOOLS = "charges/c103";
	private static final String C_104_PATCH = "charges/c104";
	private static final String C_105_XY = "charges/c105";
	private static final String C_106_TENCENT = "charges/c106";

	/*************************************************************************/
	private static final String C_201_UC = "201";
	private static final String C_202_360 = "202";
	private static final String C_206_baidu = "206";
	private static final String C_210_lianxiang = "210";
	private static final String C_203_xiaomi = "203";
	private static final String C_204_vivo = "204";
	private static final String C_205_vivo = "205";
	private static final String C_207_huawei = "207";
	private static final String C_208 = "208";
	private static final String C_209 = "209";
	private static final String C_211 = "211";
	private static final String C_227 = "227";
	private static final String A_228 = "228";
	private static final String A_229 = "229";
	private static final String C_230 = "230";
	private static final String C_231 = "231";
	/** 充值反馈-触控 */
	private static final String C_232 = "232";

	private ChargeChukong chukong = null;
	private Charge91 charge91 = null;
	private ChargeLoadConfigs loadConfigs = null;
	private ChargeITools chargeITools = null;
	private ChargePatch104 chargePatch = null;
	private LoadServerCharges serverCharges = null;
	private ChargePP chargePP = null;
	private ChargeClassload chargeClassload = null;
	private ChargeTongBu chargeTongBu = null;
	private ChargeXY chargeXY = null;
	private ChargeConfirm chargeConfirm = null;
	private OrderformGenerator orderform = null;
	private ChargeTencent chargeTencent = null;

	private AChargeContextHandle c_201 = null;
	private AChargeContextHandle c_202 = null;
	private AChargeContextHandle c_206 = null;
	private AChargeContextHandle c_210 = null;
	private AChargeContextHandle c_203 = null;
	private AChargeContextHandle c_205 = null;
	private AChargeContextHandle c_204 = null;
	private AChargeContextHandle c_207 = null;
	private AChargeContextHandle c_208 = null;
	private AChargeContextHandle c_209 = null;
	private AChargeContextHandle c_211 = null;
	private AChargeContextHandle c_227 = null;
	private AChargeContextHandle a_228 = null;
	private AppStore_229_order a_229 = null;
	private AChargeContextHandle c_230 = null;
	private AChargeContextHandle c_231 = null;
	private AChargeContextHandle e_10004 = null;
	private AChargeContextHandle e_10005 = null;
	private AChargeContextHandle e_10006 = null;
	private AChargeContextHandle e_10007 = null;
	private AChargeContextHandle e_10008 = null;
	private AChargeContextHandle e_10009 = null;
	private AChargeContextHandle e_10010 = null;
	private AChargeContextHandle e_10011 = null;
	private Map<String, Class> allClass = new HashMap<String, Class>();

	@SuppressWarnings("rawtypes")
	public Class getClass(String fullName) {
		return allClass.get(fullName);
	}

	public void reloadClasses() {
		initClasses();
	}

	private void initClasses() {
		try {
			Map<String, Class> allClass = new HashMap<String, Class>();
			ClassLoader cl = getClass().getClassLoader();
			List<String> resultList = new ArrayList<String>();
			String dir = ApplicationConfig.getClassDir();
			FileUtil.filterDirFileByType(new File(dir), ".class", resultList);
			String sub_1 = null;
			String sub_2 = null;
			for (int ri = 0; ri < resultList.size(); ri++) {
				sub_1 = resultList.get(ri).substring(dir.length() + 1);
				sub_2 = sub_1.substring(0, sub_1.indexOf('.')).replace('/', '.').replace('\\', '.');
				if (!sub_2.contains("$")) {
					allClass.put(sub_2, cl.loadClass(sub_2));
				}
			}
			this.allClass = allClass;
			/**********************************************************************************/
			chargeConfirm = (ChargeConfirm) allClass.get("mmo.common.module.clazz.charge.callback.channel.ChargeConfirm").newInstance();
			orderform = (OrderformGenerator) allClass.get("mmo.common.module.clazz.charge.callback.OrderformGenerator").newInstance();
			loadConfigs = (ChargeLoadConfigs) allClass.get("mmo.common.module.clazz.charge.callback.channel.ChargeLoadConfigs").newInstance();
			/**********************************************************************************/
			chukong = (ChargeChukong) allClass.get("mmo.common.module.clazz.charge.callback.chukong.ChargeChukong").newInstance();
			charge91 = (Charge91) allClass.get("mmo.common.module.clazz.charge.callback.channel.Charge91").newInstance();
			chargeITools = (ChargeITools) allClass.get("mmo.common.module.clazz.charge.callback.channel.ChargeITools").newInstance();
			chargeClassload = (ChargeClassload) allClass.get("mmo.common.module.clazz.charge.callback.channel.ChargeClassload").newInstance();
			chargePP = (ChargePP) allClass.get("mmo.common.module.clazz.charge.callback.channel.ChargePP").newInstance();
			chargeTongBu = (ChargeTongBu) allClass.get("mmo.common.module.clazz.charge.callback.channel.ChargeTongBu").newInstance();
			serverCharges = (LoadServerCharges) allClass.get("mmo.common.module.clazz.charge.callback.channel.LoadServerCharges").newInstance();
			chargePatch = (ChargePatch104) allClass.get("mmo.common.module.clazz.charge.callback.channel.ChargePatch104").newInstance();
			chargeXY = (ChargeXY) allClass.get("mmo.common.module.clazz.charge.callback.channel.ChargeXY").newInstance();
			chargeTencent = (ChargeTencent) allClass.get("mmo.common.module.clazz.charge.callback.channel.ChargeTencent").newInstance();
			/**********************************************************************************/
			c_201 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_201_UC").newInstance();
			c_202 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_202_360").newInstance();
			c_203 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_203_xiaomi").newInstance();
			c_204 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_204_oppo").newInstance();
			c_205 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_205_vivo").newInstance();
			c_206 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_206_baidu").newInstance();
			c_207 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_207_huawei").newInstance();
			c_208 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_208_jinli").newInstance();
			c_209 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_209_kupai").newInstance();
			c_210 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_210_lianxiang").newInstance();
			c_211 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_211_yiyou").newInstance();
			c_227 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_227_yijie").newInstance();
			a_228 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.channel.AppStore_228_receipt").newInstance();
			a_229 = (AppStore_229_order) allClass.get("mmo.common.module.clazz.charge.callback.channel.AppStore_229_order").newInstance();
			c_230 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_230_qq").newInstance();
			c_231 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.doupo.Charge_231_QQOrder").newInstance();

			e_10004 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.bi.BI_10004_event").newInstance();
			e_10005 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.bi.BI_10005_shikeClick").newInstance();
			e_10006 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.bi.BI_10006_shikeCheck").newInstance();
			e_10007 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.bi.BI_10007_qiankaClick").newInstance();
			e_10008 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.bi.BI_10008_zhangyueClick").newInstance();
			e_10009 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.bi.BI_10009_qiankaCheck").newInstance();
			e_10010 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.bi.BI_10010_duomengClick").newInstance();
			e_10011 = (AChargeContextHandle) allClass.get("mmo.common.module.clazz.charge.callback.bi.BI_10011_duomengCheck").newInstance();

		} catch (Exception e) {
			LoggerError.error("重新加载类库异常", e);
		}
	}

	public void loadClasses() {
		initClasses();
	}

	public HttpResponseMessage callback(IoSession session, String context, HttpRequestMessage request) {
		if (context != null) {
			switch (context) {
				case C_232: {
					ChargeChukong chukong = this.chukong;
					if (chukong != null) {
						chukong.printParameter(request);
						return chukong.s_101(request);
					}
				}
				case D_10002: {
					return orderform.callback(session, request, this);
				}
				case C_101_91: {
					return charge91.callback(session, request);
				}
				case C_100_PP: {
					return chargePP.callback(request);
				}
				case C_102_TONG_BU: {
					return chargeTongBu.callback(request);
				}
				case C_103_ITOOLS: {
					return chargeITools.callback(request);
				}
				case C_105_XY: {
					return chargeXY.callback(request);
				}
				case C_104_PATCH: {
					return chargePatch.callback(request);
				}
				case SERVER_CHARGES: {
					return serverCharges.callback(request);
				}
				case C_10003: {
					return chargeConfirm.callback(session, request);
				}
				case CHARGE_CLASSLOAD: {
					return chargeClassload.callback(request);
				}
				case LOAD_CONFIGS: {
					return loadConfigs.callback(request);
				}
				case C_106_TENCENT: {
					return chargeTencent.callback(session, request);
				}
				case C_201_UC: {
					c_201.printParameter(request);
					return c_201.callback(session, request);
				}
				case C_206_baidu: {
					c_206.printParameter(request);
					return c_206.callback(session, request);
				}
				case C_210_lianxiang: {
					c_210.printParameter(request);
					return c_210.callback(session, request);
				}
				case C_202_360: {
					c_202.printParameter(request);
					return c_202.callback(session, request);
				}
				case C_203_xiaomi: {
					c_203.printParameter(request);
					return c_203.callback(session, request);
				}
				case C_204_vivo: {
					c_204.printParameter(request);
					return c_204.callback(session, request);
				}
				case C_205_vivo: {
					c_205.printParameter(request);
					return c_205.callback(session, request);
				}
				case C_207_huawei: {
					c_207.printParameter(request);
					return c_207.callback(session, request);
				}
				case C_208: {
					c_208.printParameter(request);
					return c_208.callback(session, request);
				}
				case C_209: {
					c_209.printParameter(request);
					return c_209.callback(session, request);
				}
				case C_211: {
					c_211.printParameter(request);
					return c_211.callback(session, request);
				}
				case C_227: {
					c_227.printParameter(request);
					return c_227.callback(session, request);
				}
				case A_228: {
					a_228.printParameter(request);
					return a_228.callback(session, request);
				}
				case A_229: {
					a_229.printParameter(request);
					return a_229.callback(request, this);
				}
				case C_230: {
					c_230.printParameter(request);
					return c_230.callback(session, request);
				}
				case C_231: {
					c_231.printParameter(request);
					return c_231.callback(session, request);
				}
				case E_10004: {
					e_10004.printParameter(request);
					return e_10004.callback(session, request);
				}
				case E_10005: {
					e_10005.printParameter(request);
					return e_10005.callback(session, request);
				}
				case E_10006: {
					e_10006.printParameter(request);
					return e_10006.callback(session, request);
				}
				case E_10007: {
					e_10007.printParameter(request);
					return e_10007.callback(session, request);
				}
				case E_10008: {
					e_10008.printParameter(request);
					return e_10008.callback(session, request);
				}
				case E_10009: {
					e_10009.printParameter(request);
					return e_10009.callback(session, request);
				}
				case E_10010: {
					e_10010.printParameter(request);
					return e_10010.callback(session, request);
				}
				case E_10011: {
					e_10011.printParameter(request);
					return e_10011.callback(session, request);
				}
			}
		}
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain");
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
		return response;
	}
}
