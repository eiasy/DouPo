package mmo.common.module.sdk.http;

import mmo.common.module.sdk.token.ILoginSDKCallback;
import mmo.common.module.sdk.token.run.ITokenRun;
import mmo.extension.application.ApplicationConfig;
import mmo.tools.java.MyClassLoader;
import mmo.tools.log.LoggerError;

public class ClassloaderRun implements ITokenRun {

	public ClassloaderRun() {
		super();
	}

	@Override
	public void run() {
		try {
			MyClassLoader cl = new MyClassLoader();
			cl.setClassPath(ApplicationConfig.getClassDir());
			Class callbackClass = cl.loadClass("mmo.common.module.clazz.callback.LoginSDKCallback");
			ILoginSDKCallback callbackInstance = (ILoginSDKCallback) callbackClass.newInstance();
			callbackInstance.reloadClasses();
			HttpHandlerLogin.setSdkCallback(callbackInstance);
			LoggerError.warn("完成热加载类库");
		} catch (Exception ex) {
			LoggerError.error("热加载类库异常", ex);
		}
	}
}
