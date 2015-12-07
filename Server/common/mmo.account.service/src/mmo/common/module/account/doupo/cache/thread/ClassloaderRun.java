package mmo.common.module.account.doupo.cache.thread;

import mmo.common.module.account.doupo.http.HttpHandlerLogin;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.java.AClassLoader;
import mmo.tools.java.MyClassLoader;
import mmo.tools.log.LoggerError;

public class ClassloaderRun implements IAccountValidate {

	public ClassloaderRun() {
		super();
	}

	@Override
	public void run() {

		try {
			MyClassLoader cl = new MyClassLoader();
			cl.setClassPath(ApplicationConfig.getClassDir());
			Class callbackClass = cl.loadClass("mmo.common.module.clazz.account.doupo.callback.AccountCallback");
			AClassLoader callbackInstance = (AClassLoader) callbackClass.newInstance();
			callbackInstance.reloadClasses();
			callbackInstance.execute();
			HttpHandlerLogin.setSdkCallback(callbackInstance);
			LoggerError.warn("完成热加载类库");
		} catch (Exception ex) {
			LoggerError.error("热加载类库异常", ex);
		}
	}
}
