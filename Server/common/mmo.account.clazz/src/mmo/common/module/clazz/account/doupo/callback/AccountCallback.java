package mmo.common.module.clazz.account.doupo.callback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.extension.application.ApplicationConfig;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.java.AClassLoader;
import mmo.tools.log.LoggerError;
import mmo.tools.util.FileUtil;
import mmo.tools.util.StringUtil;

import org.apache.mina.core.session.IoSession;

public class AccountCallback extends AClassLoader {
	private final static int   PRO_20001     = 20001;
	private final static int   PRO_20002     = 20002;
	private final static int   PRO_20003     = 20003;
	private final static int   PRO_20004     = 20004;
	private final static int   PRO_20005     = 20005;
	private final static int   PRO_20006     = 20006;
	private final static int   PRO_20007     = 20007;
	private final static int   PRO_20008     = 20008;
	private final static int   PRO_20009     = 20009;
	private final static int   PRO_20010     = 20010;
	private final static int   PRO_20011     = 20011;
	private final static int   PRO_20012     = 20012;
	private final static int   PRO_20013     = 20013;
	private final static int   PRO_20014     = 20014;
	private final static int   PRO_20015     = 20015;
	private final static int   PRO_20016     = 20016;
	private final static int   PRO_20017     = 20017;
	private final static int   PRO_20018     = 20018;
	private final static int   PRO_20019     = 20019;
	private final static int   PRO_20020     = 20020;
	private final static int   PRO_20021     = 20021;
	private final static int   PRO_20022     = 20022;
	private final static int   PRO_20023     = 20023;
	private final static int   PRO_20024     = 20024;
	private final static int   PRO_20025     = 20025;
	private final static int   PRO_20026     = 20026;
	private final static int   PRO_20027     = 20027;
	private final static int   PRO_20028     = 20028;
	private final static int   PRO_20029     = 20029;
	private final static int   PRO_20030     = 20030;
	private final static int   PRO_20031     = 20031;

	private AContextHandle     account_20001 = null;
	private AContextHandle     account_20002 = null;
	private AContextHandle     account_20003 = null;
	private AContextHandle     account_20004 = null;
	private AContextHandle     account_20005 = null;
	private AContextHandle     account_20006 = null;
	private AContextHandle     account_20007 = null;
	private AContextHandle     account_20008 = null;
	private AContextHandle     account_20009 = null;
	private AContextHandle     account_20010 = null;
	private AContextHandle     account_20011 = null;
	private AContextHandle     account_20012 = null;
	private AContextHandle     account_20013 = null;
	private AContextHandle     account_20014 = null;
	private AContextHandle     account_20015 = null;
	private AContextHandle     account_20016 = null;
	private AContextHandle     account_20017 = null;
	private AContextHandle     account_20018 = null;
	private AContextHandle     account_20019 = null;
	private AContextHandle     account_20020 = null;
	private AContextHandle     account_20021 = null;
	private AContextHandle     account_20022 = null;
	private AContextHandle     account_20023 = null;
	private AContextHandle     account_20024 = null;
	private AContextHandle     account_20025 = null;
	private AContextHandle     account_20026 = null;
	private AContextHandle     account_20027 = null;
	private AContextHandle     account_20028 = null;
	private AContextHandle     account_20029 = null;
	private AContextHandle     account_20030 = null;
	private AContextHandle     account_20031 = null;
	private Map<String, Class> allClass      = new HashMap<String, Class>();

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
			account_20001 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20001_login").newInstance();
			account_20002 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20002_securityCode").newInstance();
			account_20003 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20003_registerDirect").newInstance();
			account_20004 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20004_htmlLogin").newInstance();
			account_20005 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20005_registerAccount").newInstance();
			account_20006 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20006_channelLogin").newInstance();
			account_20007 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20007_alterPassword").newInstance();
			account_20008 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20008_unresetPassword").newInstance();
			account_20009 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20009_roleAlterPassword").newInstance();
			account_20010 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20010_bindPhoneCheck").newInstance();
			account_20011 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20011_bindPhone").newInstance();
			account_20012 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20012_resetPassword").newInstance();
			account_20013 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20013_loadclasses").newInstance();
			account_20014 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20014_serverRoleCount").newInstance();
			account_20015 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20015_selectAccount").newInstance();
			account_20016 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20016_selectFreezedAccount").newInstance();
			account_20017 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20017_freezeAccount").newInstance();
			account_20018 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20018_gmAlterAccountPwd").newInstance();
			account_20019 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20019_gmFreezeDevice").newInstance();
			account_20020 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20020_selectFreezedDevice").newInstance();
			account_20021 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20021_checkDeviceFreezed").newInstance();
			account_20022 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20022_gmChargePatch").newInstance();
			account_20023 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20023_lastEnterServer").newInstance();
			account_20024 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20024_loadConfigs").newInstance();
			account_20025 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20025_loadAccountCharges").newInstance();
			account_20026 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20026_refreshAccountData").newInstance();
			account_20027 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20027_reloadActiveCode").newInstance();
			account_20028 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20028_resetActiveCode").newInstance();
			account_20029 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20029_loadWhiteList").newInstance();
			account_20030 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20030_accountLoginLog").newInstance();
			account_20031 = (AContextHandle) allClass.get("mmo.common.module.clazz.account.doupo.context.A20031_tokenData").newInstance();
		} catch (Exception e) {
			LoggerError.error("重新加载类库异常", e);
		}
	}

	public void loadClasses() {
		initClasses();
	}

	public HttpResponseMessage callback(IoSession session, String context, HttpRequestMessage request) {
		if (context != null) {
			String[] array = StringUtil.splitString(context, '/');
			if (array.length > 1) {
				int pro = Integer.parseInt(array[1]);
				switch (pro) {
					case PRO_20001: {
						AContextHandle callback = this.account_20001;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20002: {
						AContextHandle callback = this.account_20002;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20003: {
						AContextHandle callback = this.account_20003;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20004: {
						AContextHandle callback = this.account_20004;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20005: {
						AContextHandle callback = this.account_20005;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20006: {
						AContextHandle callback = this.account_20006;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20007: {
						AContextHandle callback = this.account_20007;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20008: {
						AContextHandle callback = this.account_20008;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20009: {
						AContextHandle callback = this.account_20009;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20010: {
						AContextHandle callback = this.account_20010;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20011: {
						AContextHandle callback = this.account_20011;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20012: {
						AContextHandle callback = this.account_20012;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20013: {
						AContextHandle callback = this.account_20013;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20014: {
						AContextHandle callback = this.account_20014;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20015: {
						AContextHandle callback = this.account_20015;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20016: {
						AContextHandle callback = this.account_20016;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20017: {
						AContextHandle callback = this.account_20017;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20018: {
						AContextHandle callback = this.account_20018;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20019: {
						AContextHandle callback = this.account_20019;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20020: {
						AContextHandle callback = this.account_20020;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20021: {
						AContextHandle callback = this.account_20021;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20022: {
						AContextHandle callback = this.account_20022;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20023: {
						AContextHandle callback = this.account_20023;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20024: {
						AContextHandle callback = this.account_20024;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20025: {
						AContextHandle callback = this.account_20025;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20026: {
						AContextHandle callback = this.account_20026;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20027: {
						AContextHandle callback = this.account_20027;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20028: {
						AContextHandle callback = this.account_20028;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20029: {
						AContextHandle callback = this.account_20029;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20030: {
						AContextHandle callback = this.account_20030;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
					case PRO_20031: {
						AContextHandle callback = this.account_20031;
						if (callback != null) {
							return callback.callback(session, request);
						}
						break;
					}
				}
			}
		}
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain");
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
		return response;
	}
}
