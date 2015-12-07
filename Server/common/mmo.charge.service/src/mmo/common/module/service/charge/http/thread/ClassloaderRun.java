package mmo.common.module.service.charge.http.thread;

import mmo.common.module.service.charge.IChargeSDKCallback;
import mmo.common.module.service.charge.http.HttpHandlerLogin;
import mmo.common.module.service.charge.thread.IChargeDatabase;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.java.MyClassLoader;
import mmo.tools.log.LoggerError;

public class ClassloaderRun implements IChargeDatabase {

	public ClassloaderRun() {
		super();
	}

	@Override
	public void run() {
		try {
			MyClassLoader cl = new MyClassLoader();
			cl.setClassPath(ApplicationConfig.getClassDir());
			Class callbackClass = cl.loadClass("mmo.common.module.clazz.charge.callback.ChargeSDKCallback");
			IChargeSDKCallback callbackInstance = (IChargeSDKCallback) callbackClass.newInstance();
			callbackInstance.reloadClasses();
			HttpHandlerLogin.setSdkCallback(callbackInstance);
			LoggerError.warn("完成热加载类库");
		} catch (Exception ex) {
			LoggerError.error("热加载类库异常", ex);
		}
	}
}
