package mmo.common.module.clazz.callback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.module.clazz.callback.chukong.ChukongManager;
import mmo.common.module.clazz.callback.doupo.ValidateTencentDoupoRun;
import mmo.common.module.clazz.thread.http.Validate91Run;
import mmo.common.module.clazz.thread.http.ValidateAnysdkRun;
import mmo.common.module.clazz.thread.http.ValidateAppStoreRun;
import mmo.common.module.clazz.thread.http.ValidateItoolsRun;
import mmo.common.module.clazz.thread.http.ValidatePPRun;
import mmo.common.module.clazz.thread.http.ValidateTencentRun;
import mmo.common.module.clazz.thread.http.ValidateTongBuTuiRun;
import mmo.common.module.clazz.thread.http.ValidateXYRun;
import mmo.common.module.clazz.thread.token.TokenValidateDoupoRun;
import mmo.common.module.clazz.thread.token.TokenValidateRun;
import mmo.common.module.sdk.server.ThreadManager;
import mmo.common.module.sdk.token.AClientData;
import mmo.common.module.sdk.token.ILoginSDKCallback;
import mmo.common.module.sdk.token.TokenHeartbeat;
import mmo.common.module.sdk.token.TokenManager;
import mmo.extension.application.ApplicationConfig;
import mmo.http.AContextHandle;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.FileUtil;

import org.apache.mina.core.session.IoSession;

public class LoginSDKCallback implements ILoginSDKCallback {
	/** 验证触控账号登录 */
	private static final String V_503_chukong = "v503";
	private static final String V_DOU_PO_500 = "v500";
	private static final String V_501 = "501";
	private static final String V_502 = "502";
	private static final String LOAD_SERVER = "loadserver";
	private static final String LOAD_CONFIG = "loadconfig";
	private static final String RELOADCLASS = "reloadclass";
	private ChukongManager chukong = null;
	private ValidateTencentDoupoRun tencentDoupo = null;
	private TokenValidateDoupoRun validateDoupo = null;
	private Map<String, Class> allClass = new HashMap<String, Class>();
	private ContextReloadCalss reloadClazz = null;
	private AContextHandle loadServer = null;
	private AContextHandle loadConfig = null;
	private AContextHandle codeVersion = null;
	private AContextHandle loadCodeVerison = null;

	public void reloadClasses() {
		initChannenSDK();
	}

	public Class getClass(String fullName) {
		return allClass.get(fullName);
	}

	public void loadClasses() {
		initChannenSDK();
	}

	private void initChannenSDK() {
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

			chukong = (ChukongManager) allClass.get("mmo.common.module.clazz.callback.chukong.ChukongManager").newInstance();
			tencentDoupo = (ValidateTencentDoupoRun) allClass.get("mmo.common.module.clazz.callback.doupo.ValidateTencentDoupoRun").newInstance();
			validateDoupo = (TokenValidateDoupoRun) allClass.get("mmo.common.module.clazz.thread.token.TokenValidateDoupoRun").newInstance();
			reloadClazz = (ContextReloadCalss) allClass.get("mmo.common.module.clazz.callback.ContextReloadCalss").newInstance();
			loadServer = (ContextReloadServerList) allClass.get("mmo.common.module.clazz.callback.ContextReloadServerList").newInstance();
			loadConfig = (ContextReloadConfig) allClass.get("mmo.common.module.clazz.callback.ContextReloadConfig").newInstance();
			codeVersion = (AContextHandle) allClass.get("mmo.common.module.clazz.callback.Context_501_codeVersion").newInstance();
			loadCodeVerison = (AContextHandle) allClass.get("mmo.common.module.clazz.callback.ContextCodeVersion").newInstance();
		} catch (Exception e) {
			LoggerError.error("重新加载类库异常", e);
		}
	}

	public HttpResponseMessage callback(IoSession session, String context, HttpRequestMessage request) {
		try {
			if (context != null) {
				switch (context) {
					case V_503_chukong: {
						// return chukong.callback(session, request);
						ThreadManager.requestHttp("anysdk", new ValidateAnysdkRun(chukong, session, request));
						return null;
					}
					case V_DOU_PO_500: {
						return validateDoupo.handle(session, request, this);
					}
					case V_501: {
						return codeVersion.callback(session, request);
					}
					case V_502: {
						return loadCodeVerison.callback(session, request);
					}
					case RELOADCLASS: {
						return reloadClazz.handle(session, request);
					}
					case LOAD_CONFIG: {
						return loadConfig.callback(session, request);
					}
					case LOAD_SERVER: {
						return loadServer.callback(session, request);
					}
				}
			}
		} catch (Exception e) {
			LoggerError.error("context=" + context, e);
		}
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain");
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
		return response;
	}

	@Override
	public void validateToken(AClientData clientData) {
		try {
			TokenValidateRun tv = (TokenValidateRun) allClass.get("mmo.common.module.clazz.thread.token.TokenValidateRun").newInstance();
			tv.setSdk(this);
			tv.setClientData(clientData);
			TokenHeartbeat.getInstance().execute(tv);
		} catch (Exception e) {
			LoggerError.error("验证TOKEN异常", e);
		}
	}

	public void validatePP(AClientData clientData) {
		try {
			ValidatePPRun validate = (ValidatePPRun) allClass.get("mmo.common.module.clazz.thread.http.ValidatePPRun").newInstance();
			validate.setClientData(clientData);
			ThreadManager.requestHttp("pp", validate);
		} catch (Exception e) {
			LoggerError.error("验证PP异常", e);
		}
	}

	public void validateItools(AClientData clientData) {
		try {
			ValidateItoolsRun validate = (ValidateItoolsRun) allClass.get("mmo.common.module.clazz.thread.http.ValidateItoolsRun").newInstance();
			validate.setClientData(clientData);
			ThreadManager.requestHttp("ITools", validate);
		} catch (Exception e) {
			LoggerError.error("验证ITools异常", e);
		}
	}

	public void validate91(AClientData clientData) {
		try {
			Validate91Run validate = (Validate91Run) allClass.get("mmo.common.module.clazz.thread.http.Validate91Run").newInstance();
			validate.setClientData(clientData);
			ThreadManager.requestHttp("91", validate);
		} catch (Exception e) {
			LoggerError.error("验证91异常", e);
		}
	}

	public void validateTongBuTui(AClientData clientData) {
		try {
			ValidateTongBuTuiRun validate = (ValidateTongBuTuiRun) allClass.get("mmo.common.module.clazz.thread.http.ValidateTongBuTuiRun").newInstance();
			validate.setClientData(clientData);
			ThreadManager.requestHttp("TongBuTui", validate);
		} catch (Exception e) {
			LoggerError.error("验证同步推异常", e);
		}
	}

	public void validateAppStore(AClientData clientData) {
		try {
			ValidateAppStoreRun validate = (ValidateAppStoreRun) allClass.get("mmo.common.module.clazz.thread.http.ValidateAppStoreRun").newInstance();
			validate.setClientData(clientData);
			ThreadManager.requestHttp("AppStore", validate);
		} catch (Exception e) {
			LoggerError.error("验证APPSTORE异常", e);
		}

	}

	public void validateXY(AClientData clientData) {
		try {
			ValidateXYRun validate = (ValidateXYRun) allClass.get("mmo.common.module.clazz.thread.http.ValidateXYRun").newInstance();
			validate.setClientData(clientData);
			ThreadManager.requestHttp("XY", validate);
		} catch (Exception e) {
			LoggerError.error("验证XY异常", e);
		}
	}

	public void validateTencent(AClientData clientData) {
		try {
			ValidateTencentRun validate = (ValidateTencentRun) allClass.get("mmo.common.module.clazz.thread.http.ValidateTencentRun").newInstance();
			validate.setClientData(clientData);
			ThreadManager.requestHttp("qq", validate);
		} catch (Exception e) {
			LoggerError.error("验证腾讯异常", e);
		}
	}

	public boolean validateTencentDoupo(AClientData clientData) {
		try {
			if (tencentDoupo != null) {
				return tencentDoupo.handle(clientData);
			}
		} catch (Exception e) {
			LoggerError.error("DOU_PO 验证腾讯异常", e);
		}
		return false;
	}
}
