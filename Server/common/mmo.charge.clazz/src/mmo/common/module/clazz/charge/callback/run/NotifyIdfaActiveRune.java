package mmo.common.module.clazz.charge.callback.run;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import mmo.common.bean.advertise.IdfaActive;
import mmo.common.module.service.charge.thread.ChargeDatabaseHeartbeat;
import mmo.common.module.service.charge.thread.run.ANotifyIdfaActiveRune;
import mmo.common.module.service.charge.thread.run.UpdateIdfaActiveDBRun;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;

public class NotifyIdfaActiveRune extends ANotifyIdfaActiveRune {
	private IdfaActive idfa;

	public void setIdfa(IdfaActive idfa) {
		this.idfa = idfa;
	}

	@Override
	public void run() {
		try {
			if (idfa != null && idfa.getChannelTag() != null) {
				switch (idfa.getChannelTag()) {
					case "shike": {
						if (idfa.getRoleCreate() > 0) {
							Map<String, String> param = new HashMap<String, String>();
							param.put("idfa", idfa.getIdfa());
							param.put("appid", idfa.getAppId());
							param.put("mac", idfa.getDeviceMac());
							param.put("source", ProjectCofigs.getParameter("ad_shike_source"));
							String result = HttpsUtil.request(ProjectCofigs.getParameter("ad_shike_notify_url") + "?"
							        + HttpsUtil.httpBuildQuery(param));
							JSONObject json = JSONObject.fromObject(result);
							if ("true".equalsIgnoreCase(json.getString("success"))) {
								idfa.setStatus((byte) 1);
								idfa.setDesc(result);
								ChargeDatabaseHeartbeat.getInstance().execute(new UpdateIdfaActiveDBRun(idfa));
							}
						}
						break;
					}
					case "zhangyue": {
						if (idfa.getRoleCreate() > 0) {
							// Map<String, String> param = new HashMap<String, String>();
							// param.put("idfa", idfa.getIdfa());
							// param.put("channel", idfa.getChannelSub());
							// param.put("media", idfa.getMedia());
							// param.put("mac", idfa.getDeviceMac());
							// param.put("source", ProjectCofigs.getParameter("ad_shike_source"));
							String result = HttpsUtil.request(idfa.getCallback());
							LoggerError.warn("NoticeZhangyue#" + result);
							JSONObject json = JSONObject.fromObject(result);
							if ("true".equalsIgnoreCase(json.getString("success"))) {
								idfa.setStatus((byte) 1);
								idfa.setDesc(result);
								ChargeDatabaseHeartbeat.getInstance().execute(new UpdateIdfaActiveDBRun(idfa));
							}
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			LoggerError.error("NotifyIdfaActiveRune", e);
		}
	}

}
