package mmo.common.module.service.charge.advertise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import mmo.common.bean.advertise.IdfaActive;
import mmo.common.bean.advertise.IdfaEvent;
import mmo.common.module.service.charge.service.Service;

public class IdfaManager {
	private final static IdfaManager instance = new IdfaManager();

	public final static IdfaManager getInstance() {
		return instance;
	}

	private AtomicLong                 idGenerator = new AtomicLong();
	private Map<String, AppIdfaActive> idfaActives = new HashMap<String, AppIdfaActive>();

	private IdfaManager() {

	}

	public long nextId() {
		return idGenerator.incrementAndGet();
	}

	public void initIdfaActive() {
		List<IdfaActive> list = Service.getInstance().loadAllIdfaActive();
		for (int li = 0; li < list.size(); li++) {
			initIdfaActive(list.get(li));
		}
	}

	public void initIdfaActive(IdfaActive idfa) {
		if (idfa.getId() > idGenerator.get()) {
			idGenerator.set(idfa.getId());
		}
		AppIdfaActive appIdfa = idfaActives.get(idfa.getAppId());
		if (appIdfa == null) {
			appIdfa = new AppIdfaActive();
			idfaActives.put(idfa.getAppId(), appIdfa);
		}
		appIdfa.initIdfaActive(idfa);
	}

	public boolean validateIdfaEvent(IdfaEvent event) {
		AppIdfaActive appIdfa = idfaActives.get(event.getAppId());
		if (appIdfa == null) {
			appIdfa = new AppIdfaActive();
			idfaActives.put(event.getAppId(), appIdfa);
		}
		return appIdfa.validateIdfaEvent(event);
	}

	public boolean isIdfaActive(String appId, String idfa) {
		AppIdfaActive appIdfa = idfaActives.get(appId);
		if (appIdfa == null) {
			return false;
		}
		return appIdfa.isIdfaActive(idfa);
	}

	public boolean isIdfaExist(String appId, String idfa) {
		AppIdfaActive appIdfa = idfaActives.get(appId);
		if (appIdfa == null) {
			return false;
		}
		return appIdfa.isIdfaExist(idfa);
	}

	public boolean isMacActive(String appId, String mac) {
		AppIdfaActive appIdfa = idfaActives.get(appId);
		if (appIdfa == null) {
			return false;
		}
		return appIdfa.isMacActive(mac);
	}

	public boolean isImeiActive(String appId, String imei) {
		AppIdfaActive appIdfa = idfaActives.get(appId);
		if (appIdfa == null) {
			return false;
		}
		return appIdfa.isImeiActive(imei);
	}
}
