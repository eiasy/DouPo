package mmo.common.module.clazz.event.callback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.module.clazz.event.context.AEventContextHandle;
import mmo.common.module.clazz.event.context.ChargeClassload;
import mmo.common.module.clazz.event.context.ChargeLoadConfigs;
import mmo.common.module.clazz.event.run.HandleContextRun;
import mmo.common.service.eventcenter.IEventCallback;
import mmo.common.service.eventcenter.thread.ThreadManager;
import mmo.extension.application.ApplicationConfig;
import mmo.http.httpserver.HttpRequestMessage;
import mmo.http.httpserver.HttpResponseMessage;
import mmo.tools.log.LoggerError;
import mmo.tools.util.FileUtil;

import org.apache.mina.core.session.IoSession;

public class EventCallback implements IEventCallback {

	private static final String EVENT_TRIGGER = "datacenter/event";
	private static final String SELECT_CREATE_ORDER = "datacenter/selectCreateOrder";
	private static final String SELECT_PLAYER = "datacenter/selectPlayer";
	private static final String SELECT_FINISH_CHARGE = "datacenter/selectFinishCharge";
	private static final String SELECT_GET_GOLD = "datacenter/selectGetGold";
	private static final String SELECT_ROLE = "datacenter/selectRole";
	private static final String SELECT_CHARGE = "datacenter/selectCharge";
	private static final String OPERATE_APP_CONFIG = "datacenter/operateAppConfig";
	private static final String SELECT_GOODS = "datacenter/selectGoods";
	private static final String SELECT_APP_CONFIG = "datacenter/selectAppConfig";
	private static final String ADMIN_LOGIN = "datacenter/adminLogin";
	private static final String CHARGE_CLASSLOAD = "eventcenter/classload";
	private static final String LOAD_CONFIGS = "eventcenter/loadconfigs";

	private ChargeLoadConfigs loadConfigs = null;
	private ChargeClassload chargeClassload = null;
	private AEventContextHandle eventTrigger = null;
	private AEventContextHandle adminLogin = null;
	private AEventContextHandle selectCharge = null;
	private AEventContextHandle selectGoods = null;
	private AEventContextHandle selectRole = null;
	private AEventContextHandle selectCreateOrder = null;
	private AEventContextHandle selectFinishCharge = null;
	private AEventContextHandle selectGetGold = null;
	private AEventContextHandle selectPlayer = null;
	private AEventContextHandle selectAppConfig = null;
	private AEventContextHandle operateAppConfig = null;

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
			loadConfigs = (ChargeLoadConfigs) allClass.get("mmo.common.module.clazz.event.context.ChargeLoadConfigs").newInstance();
			/**********************************************************************************/
			chargeClassload = (ChargeClassload) allClass.get("mmo.common.module.clazz.event.context.ChargeClassload").newInstance();
			/**********************************************************************************/
			eventTrigger = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.Event_Trigger").newInstance();
			adminLogin = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminLogin").newInstance();
			selectCharge = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminSelectCharge").newInstance();
			selectGoods = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminSelectGoods").newInstance();
			selectRole = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminSelectRole").newInstance();
			selectCreateOrder = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminSelectCreateOrder").newInstance();
			selectFinishCharge = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminSelectFinishCharge").newInstance();
			selectGetGold = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminSelectGetGold").newInstance();
			selectPlayer = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminSelectPlayer").newInstance();
			selectAppConfig = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminSelectAppConfig").newInstance();
			operateAppConfig = (AEventContextHandle) allClass.get("mmo.common.module.clazz.event.context.AdminOperateAppConfig").newInstance();

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
				case CHARGE_CLASSLOAD: {
					return chargeClassload.callback(request);
				}
				case LOAD_CONFIGS: {
					return loadConfigs.callback(request);
				}
				case EVENT_TRIGGER: {
					ThreadManager.handleContext(new HandleContextRun(eventTrigger, session, request));
					return null;
				}
				case ADMIN_LOGIN: {
					return adminLogin.callback(session, request);
				}
				case SELECT_CHARGE: {
					return selectCharge.callback(session, request);
				}
				case SELECT_GOODS: {
					return selectGoods.callback(session, request);
				}
				case SELECT_ROLE: {
					return selectRole.callback(session, request);
				}
				case SELECT_CREATE_ORDER: {
					return selectCreateOrder.callback(session, request);
				}
				case SELECT_FINISH_CHARGE: {
					return selectFinishCharge.callback(session, request);
				}
				case SELECT_GET_GOLD: {
					return selectGetGold.callback(session, request);
				}
				case SELECT_PLAYER: {
					return selectPlayer.callback(session, request);
				}
				case SELECT_APP_CONFIG: {
					return selectAppConfig.callback(session, request);
				}
				case OPERATE_APP_CONFIG: {
					return operateAppConfig.callback(session, request);
				}
			}
		}
		HttpResponseMessage response = new HttpResponseMessage();
		response.setContentType("text/plain");
		response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
		return response;
	}

}
