package mmo.common.module.clazz.account;

import java.util.Map;

import mmo.tools.config.ProjectCofigs;
import mmo.tools.thread.runnable.RequestHttpRun;

public class PushBIEventRun extends RequestHttpRun {

	public PushBIEventRun(Map<String, String> parameter) {
		super(parameter);
	}

	@Override
	public void run() {
		request(ProjectCofigs.getParameter("push_event_url"));
	}

}
