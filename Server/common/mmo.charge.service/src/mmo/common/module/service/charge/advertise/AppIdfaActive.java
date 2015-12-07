package mmo.common.module.service.charge.advertise;

import java.util.HashMap;
import java.util.Map;

import mmo.common.bean.advertise.IdfaActive;
import mmo.common.bean.advertise.IdfaEvent;
import mmo.common.http.parameter.HttpParameter;
import mmo.common.module.service.charge.http.HttpHandlerLogin;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.ThreadManager;
import mmo.common.module.service.charge.thread.run.ANotifyIdfaActiveRune;
import mmo.common.module.service.charge.thread.run.AddIdfaActiveDBRun;
import mmo.common.module.service.charge.thread.run.UpdateIdfaActiveDBRun;
import mmo.module.logger.charge.LoggerCharge;
import mmo.tools.log.LoggerError;

public class AppIdfaActive {
	private static String MAC_FILTER_1 = "020000000000";
	private Map<Long, IdfaActive> idfaActives = new HashMap<Long, IdfaActive>();
	private Map<String, Long> mac2id = new HashMap<String, Long>();
	private Map<String, Long> idfa2id = new HashMap<String, Long>();
	private Map<String, Long> imei2id = new HashMap<String, Long>();

	public boolean validateIdfaEvent(IdfaEvent event) {
		IdfaActive idfa = null;
		boolean firstActive = false;
		if (event.getIdfa() != null) {
			idfa = getIdfaActiveByIdfa(event.getIdfa());
			if (idfa == null) {
				idfa = new IdfaActive(event);
				firstActive = true;
			}
		} else {
			if (event.getDeviceMac() != null) {
				idfa = getIdfaActiveByMac(event.getDeviceMac());
				if (idfa == null) {
					idfa = getIdfaActiveByImei(event.getDeviceImei());
					if (idfa == null) {
						idfa = new IdfaActive(event);
						firstActive = true;
					}
				}
			} else {
				idfa = getIdfaActiveByImei(event.getDeviceImei());
				if (idfa == null) {
					idfa = new IdfaActive(event);
					firstActive = true;
				}
			}
		}
		if (firstActive) {
			idfa.setId(IdfaManager.getInstance().nextId());
			LoggerCharge.idfaActive(idfa);
			initIdfaActive(idfa);
			ChargeDatabaseHeartbeat.getInstance().execute(new AddIdfaActiveDBRun(idfa));
		} else {
			if (IdfaEvent.TYPE_GAME_EVENT.equalsIgnoreCase(event.getEventType())) {
				if (IdfaEvent.EVENT_APP_START.equalsIgnoreCase(event.getEventTag())) {
					if (idfa.getAppStart() < 1) {
						idfa.setAppStartTime(System.currentTimeMillis());
					}
					idfa.setAppStart(idfa.getAppStart() + 1);
					ChargeDatabaseHeartbeat.getInstance().execute(new UpdateIdfaActiveDBRun(idfa));
				} else if (IdfaEvent.EVENT_CREATE_ROLE.equalsIgnoreCase(event.getEventTag())) {
					if (idfa.getRoleCreate() < 1) {
						idfa.setRoleCreateTime(System.currentTimeMillis());
						idfa.setRoleCreateChannel(event.getChannelTag());
						idfa.setRoleUserId(event.getValue(HttpParameter.GameEvent.user_id));
					}
					idfa.setRoleCreate(idfa.getRoleCreate() + 1);
					ChargeDatabaseHeartbeat.getInstance().execute(new UpdateIdfaActiveDBRun(idfa));
				}
			}
		}
		if (!IdfaEvent.CHANNEL_NATURE.equalsIgnoreCase(idfa.getChannelTag())) {
			if (idfa.getStatus() < 1 && IdfaEvent.TYPE_GAME_EVENT.equalsIgnoreCase(event.getEventType())) {
				try {
					ANotifyIdfaActiveRune run = (ANotifyIdfaActiveRune) HttpHandlerLogin.getSdkCallback().getClass("mmo.common.module.clazz.charge.callback.run.NotifyIdfaActiveRune").newInstance();
					run.setIdfa(idfa);
					ThreadManager.requestHttp("idfa", run);
				} catch (Exception e) {
					LoggerError.error("构建对象异常：mmo.common.module.clazz.charge.callback.run.NotifyIdfaActiveRune", e);
				}
			}
		}
		return firstActive;
	}

	public IdfaActive getIdfaActiveByMac(String mac) {
		if (mac == null || mac.equals("") || mac.equals(MAC_FILTER_1)) {
			return null;
		}
		Long id = mac2id.get(mac);
		if (id == null) {
			return null;
		}
		return idfaActives.get(id);
	}

	public IdfaActive getIdfaActiveByIdfa(String idfa) {
		if (idfa == null || idfa.equals("")) {
			return null;
		}
		Long id = idfa2id.get(idfa);
		if (id == null) {
			return null;
		}
		return idfaActives.get(id);
	}

	public IdfaActive getIdfaActiveByImei(String imei) {
		if (imei == null || imei.equals("")) {
			return null;
		}
		Long id = imei2id.get(imei);
		if (id == null) {
			return null;
		}
		return idfaActives.get(id);
	}

	public boolean isIdfaActive(String idfa) {
		IdfaActive idfaObj = getIdfaActiveByIdfa(idfa);
		if (idfaObj != null) {
			return idfaObj.getRoleCreate() > 0;
		}
		return false;
	}

	public boolean isIdfaExist(String idfa) {
		return getIdfaActiveByIdfa(idfa) != null;
	}

	public boolean isMacActive(String mac) {
		return getIdfaActiveByMac(mac) != null;
	}

	public boolean isImeiActive(String imei) {
		return getIdfaActiveByImei(imei) != null;
	}

	public void initIdfaActive(IdfaActive idfa) {
		idfaActives.put(idfa.getId(), idfa);
		if (!(idfa.getDeviceMac().equals("") || idfa.getDeviceMac().equals(MAC_FILTER_1))) {
			mac2id.put(idfa.getDeviceMac(), idfa.getId());
		}
		if (!idfa.getIdfa().equals("")) {
			idfa2id.put(idfa.getIdfa(), idfa.getId());
		}
		if (!idfa.getDeviceImei().equals("")) {
			imei2id.put(idfa.getDeviceImei(), idfa.getId());
		}
	}

}
